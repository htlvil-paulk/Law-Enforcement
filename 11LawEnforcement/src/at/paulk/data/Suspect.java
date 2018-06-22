package at.paulk.data;

import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.TreeSet;

public class Suspect extends Person
{
	private String description;
	private TreeSet<EnumFlag> flags = new TreeSet<>();

	public Suspect(int id, int idCardNumber, String nationality, Blob picture, String firstName, String lastName,
			EnumGender gender, String address, LocalDate dateOfBirth, String birthplace, String description,
			Collection<EnumFlag> flags) throws SQLException
	{
		super(id, idCardNumber, nationality, picture, firstName, lastName, gender, address, dateOfBirth, birthplace);
		addFlags(flags);
		setDescription(description);
	}

	public Suspect(int id, int idCardNumber, String nationality, Blob picture, String firstName, String lastName,
			EnumGender gender, String address, LocalDate dateOfBirth, String birthplace, String description)
			throws SQLException
	{
		this(id, idCardNumber, nationality, picture, firstName, lastName, gender, address, dateOfBirth, birthplace,
				description, new TreeSet<EnumFlag>());
	}

	public Suspect(int id, int idCardNumber, String nationality, Blob picture, String firstName, String lastName,
			EnumGender gender, String address, String dateOfBirth, String birthplace, String description)
			throws SQLException
	{
		this(id, idCardNumber, nationality, picture, firstName, lastName, gender, address,
				LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern(DATE_FORMAT)), birthplace, description);
	}

	public Suspect(int idCardNumber, String nationality, Blob picture, String firstName, String lastName,
			EnumGender gender, String address, LocalDate dateOfBirth, String birthplace, String description)
			throws SQLException
	{
		this(-99, idCardNumber, nationality, picture, firstName, lastName, gender, address, dateOfBirth, birthplace,
				description);
	}

	public Suspect(int idCardNumber, String nationality, Blob picture, String firstName, String lastName,
			EnumGender gender, String address, String dateOfBirth, String birthplace, String description)
			throws SQLException
	{
		this(idCardNumber, nationality, picture, firstName, lastName, gender, address,
				LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern(DATE_FORMAT)), birthplace, description);
	}

	public void addFlag(EnumFlag f)
	{
		flags.add(f);
	}

	public void addFlag(String flag)
	{
		EnumFlag f = EnumFlag.valueOf(flag);
		if (f != null)
		{
			addFlag(f);
		}
	}

	public void removeFlag(EnumFlag f)
	{
		flags.remove(f);
	}

	public void addFlags(Collection<EnumFlag> toAdd)
	{
		for (EnumFlag f : toAdd)
		{
			flags.add(f);
		}
	}

	public void removeFlags(Collection<EnumFlag> toRemove)
	{
		for (EnumFlag f : toRemove)
		{
			flags.remove(f);
		}
	}

	public boolean hasFlag(EnumFlag f)
	{
		return flags.contains(f);
	}

	public boolean hasFlag(String flag)
	{
		return hasFlag(EnumFlag.valueOf(flag));
	}

	public TreeSet<EnumFlag> getFlags()
	{
		return flags;
	}

	@Override
	public String toString()
	{
		return "Suspect [ID=" + getId() + ", " + getFirstName()
				+ " " + getLastName() + ", ID Card Number: " + getIdCardNumber()
				+ ", Nationality: " + getNationality() + ", DOB: " + getDateOfBirthAsString() + ", Description: " + getDescription() + " ]";
	}


	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}
}