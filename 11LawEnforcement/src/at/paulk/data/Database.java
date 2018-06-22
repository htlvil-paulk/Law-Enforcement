package at.paulk.data;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import at.paulk.misc.OfficerException;
import at.paulk.thread.ThLoadTimer;

public class Database
{
	private final String IP_ADDRESS;
	private final String PORT;
	private final String CONNECTION_STRING;
	private static final String USER = "d3c11";
	private static final String PASSWD = "d3c";
	private static boolean useLocalIPAddress = false;
	private boolean isInitialized = false;

	private Connection conn = null;
	private static Database db;

	public Database() throws SQLException
	{
		IP_ADDRESS = useLocalIPAddress ? "192.168.128.152" : "212.152.179.117";
		PORT = "1521";
		CONNECTION_STRING = "jdbc:oracle:thin:@" + IP_ADDRESS + ":" + PORT + ":ora11g";
		isInitialized = true;
		createConnection();
	}

	public static Database createInstance() throws SQLException
	{
		if (db == null)
			db = new Database();
		return db;
	}

	private void createConnection() throws SQLException
	{
		if (conn == null)
		{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		}
		conn = DriverManager.getConnection(CONNECTION_STRING, USER, PASSWD);
		conn.setAutoCommit(false);
	}

	private void closeConnection() throws SQLException
	{
		if (conn != null)
		{
			conn.commit();
			conn.close();
		}
	}

	public boolean isInitialized()
	{
		return isInitialized;
	}

	public Officer login(String username, char[] password) throws Exception
	{
		Officer o = null;

		String select = "SELECT p.id, p.idcardnumber, p.citizenship, p.picture, p.name, p.lastname, p.dateofbirth, p.birthplace, p.gender, p.address, oc.id \"officerId\", oc.rank FROM Person p"
				+ " INNER JOIN OfficerCredential oc ON p.idOfficerCredential = oc.id"
				+ " WHERE username LIKE ? AND password LIKE ?";
		createConnection();

		PreparedStatement stmt = conn.prepareStatement(select, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		stmt.setString(1, username);
		stmt.setString(2, String.valueOf(password)); // bald nur den Hashwert

		System.out.println(username + " " + String.valueOf(password) + " " + password.hashCode());

		ResultSet rs = stmt.executeQuery();

		if (rs.next())
		{
			int id = rs.getInt("id");
			int idCardNumber = rs.getInt("idcardnumber");
			String citizenship = rs.getString("citizenship");
			Blob picture = rs.getBlob("picture");
			String firstName = rs.getString("name");
			String lastName = rs.getString("lastName");
			EnumGender gender = EnumGender.valueOf(rs.getString("gender"));
			String address = rs.getString("address");
			String birthplace = rs.getString("birthplace");
			LocalDate dateOfBirth = rs.getDate("dateOfBirth").toLocalDate();

			int officerId = rs.getInt("officerId");
			EnumRank rank = EnumRank.valueOf(rs.getString("rank"));

			o = new Officer(id, idCardNumber, citizenship, picture, firstName, lastName, gender, address, dateOfBirth,
					birthplace, officerId, username, password, rank);
		}

		closeConnection();

		if (o == null)
		{
			throw new Exception("Wrong credentials!");
		}

		return o;
	}

	/**
	 * For security reasons, it first creates a connection to the database and
	 * checks the old password. After that, it makes an update and replaces the
	 * password, if the password is correct. Later on, hashes will be checked
	 * instead of passwords
	 * 
	 * @param o
	 *            the officer whose password should be changed
	 * @param oldPassword
	 * @param newPassword
	 * @throws Exception
	 * @throws SQLException
	 */
	public void changePassword(Officer o, char[] oldPassword, char[] newPassword) throws SQLException, Exception
	{
		o.changePassword(oldPassword, newPassword);
		String select = "SELECT oc.password from OfficerCredential oc" + " WHERE id=?";

		createConnection();

		PreparedStatement stmt = conn.prepareStatement(select);

		stmt.setInt(1, o.getOfficerId());

		ResultSet rs = stmt.executeQuery();

		if (!rs.next() || !String.valueOf(oldPassword).equals(rs.getString("password")))
		{
			throw new Exception("password doesn't match the old one!");
		}

		String update = "UPDATE OfficerCredential " + " SET password=? " + " WHERE id=?";

		PreparedStatement stmt2 = conn.prepareStatement(update);

		stmt2.setString(1, String.valueOf(newPassword));
		stmt2.setInt(2, o.getId());

		System.out.println(o.getId());

		stmt2.executeUpdate();

		closeConnection();
	}

	public ArrayList<Officer> selectOfficers() throws Exception
	{
		ArrayList<Officer> officers = new ArrayList<>();
		String select = "SELECT p.id, p.idcardnumber, p.citizenship, p.picture, p.name, p.lastname, p.dateofbirth, p.birthplace, p.gender, p.address, oc.id \"officerId\", oc.username, oc.password, oc.rank "
				+ " FROM Person p" + " INNER JOIN OfficerCredential oc ON p.idOfficerCredential = oc.id"
				+ " WHERE flagPerson LIKE 'OFFICER'";

		createConnection();

		PreparedStatement stmt = conn.prepareStatement(select, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

		ResultSet rs = stmt.executeQuery();

		while (rs.next())
		{
			int id = rs.getInt("id");
			int idCardNumber = rs.getInt("idcardnumber");
			String citizenship = rs.getString("citizenship");
			Blob picture = rs.getBlob("picture");
			String firstName = rs.getString("name");
			String lastName = rs.getString("lastName");
			EnumGender gender = EnumGender.valueOf(rs.getString("gender"));
			String address = rs.getString("address");
			String birthplace = rs.getString("birthplace");
			LocalDate dateOfBirth = rs.getDate("dateOfBirth").toLocalDate();

			int officerId = rs.getInt("officerId");
			String username = rs.getString("username");
			char[] password = rs.getString("password").toCharArray();
			EnumRank rank = EnumRank.valueOf(rs.getString("rank"));

			officers.add(new Officer(id, idCardNumber, citizenship, picture, firstName, lastName, gender, address,
					dateOfBirth, birthplace, officerId, username, password, rank));
		}

		closeConnection();

		return officers;
	}

	public void addOfficer(Officer o) throws Exception
	{
		String insert1 = "INSERT INTO OfficerCredential VALUES(sqPerson.NEXTVAL, ?, ?, ?)";

		String insert2 = "INSERT INTO Person VALUES(sqPerson.CURRVAL, ?, ?, ?, ?, ?, TO_DATE(?,'DD.MM.YYYY'), ?, ?, ?, 'OFFICER', NULL,  sqPerson.CURRVAL)";

		createConnection();

		PreparedStatement stmt1 = conn.prepareStatement(insert1);
		PreparedStatement stmt2 = conn.prepareStatement(insert2);

		stmt1.setString(1, o.getUsername());
		stmt1.setString(2, String.valueOf(o.getPassword()));
		stmt1.setString(3, o.getRank().toString());

		stmt2.setInt(1, o.getIdCardNumber());
		stmt2.setString(2, o.getNationality());
		stmt2.setBlob(3, o.getPicture());
		stmt2.setString(4, o.getFirstName());
		stmt2.setString(5, o.getLastName());
		stmt2.setString(6, o.getDateOfBirthAsString());
		stmt2.setString(7, o.getBirthplace());
		stmt2.setString(8, o.getGender().toString());
		stmt2.setString(9, o.getAddress());

		int returnValue = stmt1.executeUpdate();
		if (returnValue == 0)
		{
			throw new OfficerException("No Officer generated");
		}

		returnValue = stmt2.executeUpdate();
		if (returnValue == 0)
		{
			conn.rollback();
			throw new OfficerException("No Officer generated");
		}

		closeConnection();
	}

	public void updateOfficer(Officer o) throws SQLException
	{
		String update = "UPDATE Person"
				+ " SET idCardNumber=?, citizenship=?, name=?, lastName=?, picture=?, dateOfBirth=TO_DATE(?,'DD.MM.YYYY'), birthplace=?, gender=?, address=?"
				+ " WHERE id=?";
		createConnection();

		PreparedStatement stmt = conn.prepareStatement(update);

		stmt.setInt(1, o.getIdCardNumber());
		stmt.setString(2, o.getNationality());
		stmt.setString(3, o.getFirstName());
		stmt.setString(4, o.getLastName());
		stmt.setBlob(5, o.getPicture());
		stmt.setString(6, o.getDateOfBirthAsString());
		stmt.setString(7, o.getBirthplace());
		stmt.setString(8, o.getGender().toString());
		stmt.setString(9, o.getAddress());
		stmt.setInt(10, o.getId());

		stmt.executeUpdate();

		closeConnection();
	}

	public void uploadPicture(Person p, File f) throws Exception
	{
		String update = "UPDATE Person" + " SET picture=?" + " WHERE id=?";

		String fileExtension = f.getName().substring(f.getName().lastIndexOf('.') + 1).toLowerCase();
		System.out.println(fileExtension);
		if (!(fileExtension.contentEquals("jpg") && fileExtension.contentEquals("png")))
		{
			throw new Exception("Invalid type!");
		}

		createConnection();

		try (FileInputStream fis = new FileInputStream(f))
		{

			PreparedStatement stmt1 = conn.prepareStatement(update);

			stmt1.setBinaryStream(1, fis, f.length());
			stmt1.setInt(2, p.getId());

			stmt1.executeUpdate();

			System.out.println("UPLOADED!");
		}

		closeConnection();
	}

	public void deleteOfficer(Officer selectedItem) throws SQLException, OfficerException
	{
		String delete1 = "DELETE FROM Person" + " WHERE id=?";
		String delete2 = "DELETE FROM OfficerCredential " + "WHERE id=?";

		createConnection();

		PreparedStatement stmt1 = conn.prepareStatement(delete1);
		PreparedStatement stmt2 = conn.prepareStatement(delete2);

		stmt1.setInt(1, selectedItem.getId());

		stmt2.setInt(1, selectedItem.getOfficerId());

		int returnValue = stmt1.executeUpdate();
		if (returnValue == 0)
		{
			throw new OfficerException("Officer has not been deleted properly!");
		}

		returnValue = stmt2.executeUpdate();
		if (returnValue == 0)
		{
			conn.rollback();
			throw new OfficerException("Officer has not been deleted properly!");
		}

		closeConnection();
	}

	public void insertSuspect(Suspect suspect) throws Exception
	{
		String insertPerson = "INSERT INTO Person VALUES(sqPerson.NEXTVAL, ?, ?, ?, ?, ?, TO_DATE(?,'DD.MM.YYYY HH24:MI:SS'), ?, ?, ?, 'SUSPECT', TO_CLOB(?),  NULL)";
		String insertFlags = "INSERT INTO HasRemark VALUES (sqPerson.CURRVAL, ?)";

		createConnection();

		PreparedStatement stmt = conn.prepareStatement(insertPerson);

		stmt.setInt(1, suspect.getIdCardNumber());
		stmt.setString(2, suspect.getNationality());
		stmt.setBlob(3, suspect.getPicture());
		stmt.setString(4, suspect.getFirstName());
		stmt.setString(5, suspect.getLastName());
		stmt.setString(6, suspect.getDateOfBirthAsString());
		stmt.setString(7, suspect.getBirthplace());
		stmt.setString(8, suspect.getGender().toString());
		stmt.setString(9, suspect.getAddress());
		stmt.setString(10, suspect.getDescription());

		int returnValue = stmt.executeUpdate();

		if (returnValue == 0)
		{
			conn.rollback();
			throw new SQLException("Failure while adding the Suspect to the database");
		}

		for (EnumFlag f : suspect.getFlags())
		{
			PreparedStatement stmt2 = conn.prepareStatement(insertFlags);

			stmt2.setInt(1, f.ordinal() + 1);
			stmt2.executeUpdate();
			if (returnValue == 0)
			{
				conn.rollback();
				throw new SQLException("Failure while adding the Flags of the Suspect to the database");
			}
		}

		closeConnection();
	}

	public ArrayList<Suspect> searchSuspects(String name, String lastName, int idCardNumber) throws SQLException
	{
		ArrayList<Suspect> results = new ArrayList<>();

		String search = "SELECT p.id, p.idcardnumber, p.citizenship, p.picture, p.name, p.lastname, p.dateofbirth, p.birthplace, p.gender, p.address, NVL(p.description, 'unknown') \"description\" FROM Person p"
				+ " WHERE (TO_CHAR(idCardNumber) LIKE ? AND UPPER(name) LIKE UPPER(?) AND UPPER(lastName) LIKE UPPER(?)) AND p.flagPerson LIKE 'SUSPECT'";
		createConnection();

		PreparedStatement stmt = conn.prepareStatement(search);

		stmt.setString(1, (idCardNumber == 0) ? "%" : "%" + idCardNumber + "%");
		stmt.setString(2, (name != null && name.length() > 0) ? "%" + name + "%" : "%");
		stmt.setString(3, (lastName != null && lastName.length() > 0) ? "%" + lastName + "%" : "%");

		ResultSet rs = stmt.executeQuery();

		while (rs.next())
		{
			int id = rs.getInt("id");
			int fullIDCardNumber = rs.getInt("idcardnumber");
			String citizenship = rs.getString("citizenship");
			Blob picture = rs.getBlob("picture");
			String fullFirstName = rs.getString("name");
			String fullLastName = rs.getString("lastName");
			LocalDate dateOfBirth = rs.getDate("dateOfBirth").toLocalDate();
			String birthPlace = rs.getString("birthplace");
			EnumGender gender = EnumGender.valueOf(rs.getString("gender"));
			String address = rs.getString("address");

			Clob clob = rs.getClob("description");
			String description = clob.getSubString(1, (int) clob.length());

			Suspect newSuspect = new Suspect(id, fullIDCardNumber, citizenship, picture, fullFirstName, fullLastName,
					gender, birthPlace, dateOfBirth, address, description);
			selectFlags(newSuspect);

			results.add(newSuspect);
		}

		closeConnection();

		return results;
	}

	private void selectFlags(Suspect s) throws SQLException
	{
		String queryFlags = "SELECT r.description FROM HasRemark hr" + " INNER JOIN Remark r ON hr.idRemark = r.id"
				+ " WHERE idPerson=?";
		PreparedStatement stmt2 = conn.prepareStatement(queryFlags);

		stmt2.setInt(1, s.getId());

		ResultSet rsFlags = stmt2.executeQuery();

		while (rsFlags.next())
		{
			s.addFlag(rsFlags.getString("description"));
		}
	}

	public ArrayList<Crime> selectCrimes(Suspect mainSuspect) throws SQLException
	{
		ArrayList<Crime> results = new ArrayList<>();

		String select = "SELECT c.fileNumber, c.shortText, TO_CHAR(c.dateTime, 'DD.MM.YYYY HH24:MI:SS') \"dateTime\", c.crimeScene, c.longText FROM Crime c"
				+ " WHERE c.idMainSuspect = ?" + " ORDER BY c.dateTime DESC";

		createConnection();

		PreparedStatement stmt = conn.prepareStatement(select);

		stmt.setInt(1, mainSuspect.getId());

		ResultSet rs = stmt.executeQuery();

		while (rs.next())
		{
			String fileNumber = rs.getString("fileNumber");
			String shortText = rs.getString("shortText");
			LocalDateTime dateTime = LocalDateTime.parse(rs.getString("dateTime"),
					DateTimeFormatter.ofPattern("dd.MM.uuuu HH:mm:ss"));
			String crimeScene = rs.getString("crimeScene");
			Clob clob = rs.getClob("longText");
			String longText = clob.getSubString(1, (int) clob.length());

			results.add(new Crime(fileNumber, shortText, dateTime, crimeScene, longText, mainSuspect));
		}
		return results;
	}

	public void insertCrime(Crime c) throws SQLException
	{
		String insert = "INSERT INTO Crime VALUES(?, ?, ?, TO_DATE(?, 'DD.MM.YYYY HH24:MI:SS'), ?, TO_CLOB(?))";

		createConnection();

		PreparedStatement stmt = conn.prepareStatement(insert);

		stmt.setString(1, c.getFileNumber());
		stmt.setInt(2, c.getMainSuspect().getId());
		stmt.setString(3, c.getShortText());
		stmt.setString(4, c.getCrimeTimeAsString());
		stmt.setString(5, c.getCrimeScene());
		stmt.setString(6, c.getLongText());

		stmt.executeUpdate();

		closeConnection();
	}

	public void updateCrime(Crime committedCrime) throws SQLException
	{
		String update = "UPDATE Crime"
				+ " SET shortText=?, dateTime=TO_DATE(?,'DD.MM.YYYY HH24:MI:SS'), crimeScene=?, longText=TO_CLOB(?)"
				+ " WHERE fileNumber LIKE ?";
		createConnection();

		PreparedStatement stmt = conn.prepareStatement(update);

		stmt.setString(1, committedCrime.getShortText());
		stmt.setString(2, committedCrime.getCrimeTimeAsString());
		stmt.setString(3, committedCrime.getCrimeScene());
		stmt.setString(4, committedCrime.getLongText());
		stmt.setString(5, committedCrime.getFileNumber());

		System.out.println(stmt.executeUpdate());

		closeConnection();
	}
}
