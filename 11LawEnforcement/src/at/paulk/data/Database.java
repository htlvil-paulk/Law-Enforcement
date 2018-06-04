package at.paulk.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

	private Database() throws SQLException
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
	}

}
