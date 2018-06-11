package at.paulk.data;

import java.sql.Blob;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public final class Officer extends Person
{
	private String username;
	private char[] password;
	private EnumRank rank;
	
	//Main CTOR
	public Officer(int id, int idCardNumber, String nationality, Blob picture, String firstName,
			String lastName, EnumGender gender, String address,
			LocalDate dateOfBirth, String birthplace, String username, char[] password, EnumRank rank) throws Exception
	{
		super(id, idCardNumber, nationality, picture, firstName, lastName, gender, address,
				dateOfBirth, birthplace);
		this.username = username;
		changePassword(null, password);
		setRank(rank);
	}

	public Officer(int id, int idCardNumber, String nationality, Blob picture, String firstName,
			String lastName, EnumGender gender, String address,
			String dateOfBirth, String birthplace, String username, char[] password, EnumRank rank) throws Exception
	{
		this(id, idCardNumber, nationality, picture, firstName, lastName, gender, address,
				LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("dd.MM.uuuu")), birthplace, username, password, rank);
	}

	public Officer(int idCardNumber, String nationality, Blob picture, String firstName, String lastName,
			EnumGender gender, String address, LocalDate dateOfBirth,
			String birthplace, String username, char[] password, EnumRank rank) throws Exception
	{
		this(-99, idCardNumber, nationality, picture, firstName, lastName, gender, address,
				dateOfBirth, birthplace, username, password, rank);
	}

	public Officer(int idCardNumber, String nationality, Blob picture, String firstName, String lastName,
			EnumGender gender, String address, String dateOfBirth,
			String birthplace, String username, char[] password, EnumRank rank) throws Exception
	{
		this(-99, idCardNumber, nationality, picture, firstName, lastName, gender, address,
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

	@Override
	public String toString()
	{
		return "Officer [ID=" + getId() + " - " + rank + " "
				+ getFirstName() + " " + getLastName() + ", " + getDateOfBirthAsString() + "]";
	}

	public char[] getPassword()
	{
		// TODO Auto-generated method stub
		return password;
	}

}