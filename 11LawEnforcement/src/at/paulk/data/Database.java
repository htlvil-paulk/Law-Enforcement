package at.paulk.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import at.paulk.misc.OfficerException;

public class Database
{
	private final String IP_ADDRESS;
	private final String PORT;
	private final String CONNECTION_STRING;
	private static final String USER = "d3c11";
	private static final String PASSWD = "d3c";
	private static boolean useLocalIPAddress = false;

	private Connection conn = null;
	private static Database db;

	public Database() throws SQLException
	{
		IP_ADDRESS = useLocalIPAddress ? "192.168.128.152" : "212.152.179.117";
		PORT = "1521";
		CONNECTION_STRING = "jdbc:oracle:thin:@" + IP_ADDRESS + ":" + PORT + ":ora11g";
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
	 * @return true, if the change has been successfully processed
	 */
	public boolean changePassword(Officer o, char[] oldPassword, char[] newPassword)
	{
		boolean isSuccessful = false;

		try
		{
			o.changePassword(oldPassword, newPassword);
			String select = "SELECT oc.password from OfficerCredential oc"
					+" WHERE id=?";

			PreparedStatement stmt = conn.prepareStatement(select);

			stmt.setInt(1, o.getOfficerId());

			ResultSet rs = stmt.executeQuery();

			if (oldPassword.equals(rs.getString("password").toCharArray()))
			{
				isSuccessful = true;
			}

		}
		catch (Exception ex)
		{
			System.out.println(ex);
		}

		return isSuccessful;
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

	/**
	 * If the officer doesn't have a valid id, it will be added to the db, as it
	 * cannot be updated otherwise, it will be updated
	 * 
	 * @param o
	 * @throws SQLException
	 */
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
				+ " SET idCardNumber=?, nationality=?, name=?, lastName=?, picture=?, dateOfBirth=TO_DATE(?,'DD.MM.YYYY'), birthplace=?, gender=?, address=?"
				+ " WHERE id=?";
		createConnection();

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

	public void addSuspect(Suspect suspect) throws SQLException
	{
		String insert = "INSERT INTO Person VALUES(sqPerson.CURRVAL, ?, ?, ?, ?, ?, TO_DATE(?,'DD.MM.YYYY'), ?, ?, ?, 'SUSPECT', ?,  NULL)";

		createConnection();

		PreparedStatement stmt = conn.prepareStatement(insert);

		stmt.setInt(1, suspect.getIdCardNumber());
		stmt.setString(2, suspect.getNationality());
		stmt.setBlob(3, suspect.getPicture());
		stmt.setString(4, suspect.getFirstName());
		stmt.setString(5, suspect.getLastName());
		stmt.setString(6, suspect.getDateOfBirthAsString());
		stmt.setString(7, suspect.getBirthplace());
		stmt.setString(8, suspect.getGender().toString());
		stmt.setString(9, suspect.getAddress());

		stmt.executeUpdate();

		closeConnection();
	}

	public ArrayList<Crime> selectCrimes(Suspect s) throws SQLException
	{
		ArrayList<Crime> results = new ArrayList<>();

		String select = "SELECT c.fileNumber, c.shortText, c.dateTime, c.crimeScene, c.longText" + "FROM Crime c"
				+ "WHERE c.idMainSuspect = ?";

		createConnection();

		PreparedStatement stmt = conn.prepareStatement(select);

		stmt.setInt(1, s.getId());

		ResultSet rs = stmt.executeQuery();

		while (rs.next())
		{
			String fileNumber = rs.getString("fileNumber");
			String shortText = rs.getString("shortText");
			LocalDate dateTime = rs.getDate("dateTime").toLocalDate();
			String crimeScene = rs.getString("crimeScene");
			Clob longText = rs.getClob("longText");

			results.add(new Crime(fileNumber, dateTime, null, crimeScene, longText, s));
			// LocalDate1, LocalDate2
		}
		return results;
	}

	public void addCrime(Crime c) throws SQLException
	{
		String insert = "INSERT INTO Crime VALUES(?, ?, ?, TO_DATE(?, 'DD.MM.YYYY'), ?, ?)";
		
		createConnection();
		
		PreparedStatement stmt = conn.prepareStatement(insert);
		
		stmt.setString(1, c.getFileNumber());
		stmt.setInt(2, c.getMainSuspect().getId());
		stmt.setString(3, c.getShortText());
		stmt.setString(4, c.getDateAsString());
		stmt.setClob(5, c.getLongText());
		
		stmt.executeUpdate();
		
		closeConnection();
	}

	public void searchSuspects(int id, String name, String lastName, int idCardNumber)
	{
		
	}
	
}
