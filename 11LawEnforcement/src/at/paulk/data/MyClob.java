package at.paulk.data;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.Clob;
import java.sql.SQLException;

public class MyClob implements Clob
{
	private String message;

	public MyClob()
	{
		this("");
	}
	
	public MyClob(String message)
	{
		this.message = message;
	}

	@Override
	public long length() throws SQLException
	{
		return message.length();
	}

	@Override
	public String getSubString(long pos, int length) throws SQLException
	{
		return message.substring((int) pos, (int) pos+length);
	}

	@Override
	public Reader getCharacterStream() throws SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getAsciiStream() throws SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long position(String searchstr, long start) throws SQLException
	{
		// TODO Auto-generated method stub
		return message.indexOf(searchstr, (int) start);
	}

	@Override
	public long position(Clob searchstr, long start) throws SQLException
	{
		return 0;
	}

	@Override
	public int setString(long pos, String str) throws SQLException
	{
		if (pos < 1)
			throw new SQLException("Pos too short");
		
		int changedChars = message.length();
		
		String toRemove = message.substring((int)pos-1, str.length());
		message = message.replace(toRemove, str);
		
		changedChars = message.length() - changedChars;
		
		return changedChars;
	}

	@Override
	public int setString(long pos, String str, int offset, int len) throws SQLException
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public OutputStream setAsciiStream(long pos) throws SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Writer setCharacterStream(long pos) throws SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void truncate(long len) throws SQLException
	{
		// TODO Auto-generated method stub
		message = message.substring(0, (int) len);
	}

	@Override
	public void free() throws SQLException
	{
		
	}

	@Override
	public Reader getCharacterStream(long pos, long length) throws SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}

}
