package at.paulk.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class Officer extends Person
{
	private String username;
	private char[] password;
	private EnumRank rank;
	
	//Main CTOR
	public Officer(int id, int idCardNumber, String pathToPicture, String firstName,
			String lastName, EnumGender gender, String address,
			LocalDate dateOfBirth, String birthplace, String username, char[] password, EnumRank rank) throws Exception
	{
		super(id, idCardNumber, pathToPicture, firstName, lastName, gender, address,
				dateOfBirth, birthplace);
		this.username = username;
		changePassword(null, password);
		setRank(rank);
	}

	public Officer(int id, int idCardNumber, String pathToPicture, String firstName,
			String lastName, EnumGender gender, String address,
			String dateOfBirth, String birthplace, String username, char[] password, EnumRank rank) throws Exception
	{
		this(id, idCardNumber, pathToPicture, firstName, lastName, gender, address,
				LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("dd.MM.uuuu")), birthplace, username, password, rank);
	}

	public Officer(int idCardNumber, String pathToPicture, String firstName, String lastName,
			EnumGender gender, String address, LocalDate dateOfBirth,
			String birthplace, String username, char[] password, EnumRank rank) throws Exception
	{
		this(-99, idCardNumber, pathToPicture, firstName, lastName, gender, address,
				dateOfBirth, birthplace, username, password, rank);
	}

	public Officer(int idCardNumber, String pathToPicture, String firstName, String lastName,
			EnumGender gender, String address, String dateOfBirth,
			String birthplace, String username, char[] password, EnumRank rank) throws Exception
	{
		this(-99, idCardNumber, pathToPicture, firstName, lastName, gender, address,
				LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("dd.MM.uuuu")), birthplace, username, password, rank);
	}

	public String getUsername()
	{
		return username;
	}

	public int getPasswordHash()
	{
		return password.hashCode(); 
		// Ordentlichen Hash-Algorithmus implementieren!
	}	
	public void changePassword(char[] oldPassword, char[] newPassword) throws Exception
	{
		if(password == null || password.equals(oldPassword))
		{
			oldPassword = newPassword;
		}
		else
		{
			throw new Exception("the password doesn't match the old password ");
		}
	}

	public EnumRank getRank()
	{
		return rank;
	}
	public void setRank(EnumRank rank)
	{
		this.rank = rank;
	}

}