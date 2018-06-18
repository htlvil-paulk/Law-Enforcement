package at.paulk.data;

import java.sql.Blob;
import java.sql.Clob;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.TreeSet;

public class Suspect extends Person
{
	private MyClob description;
	private TreeSet<EnumFlag> flags = new TreeSet<>();

	public Suspect(int id, int idCardNumber, String nationality, Blob picture, String firstName,
			String lastName, EnumGender gender, String address,
			LocalDate dateOfBirth, String birthplace, Clob description, Collection<EnumFlag> flags)
	{
		super(id, idCardNumber, nationality, picture, firstName, lastName, gender, address,
				dateOfBirth, birthplace);
		addFlags(flags);
	}

	public Suspect(int id, int idCardNumber, String nationality, Blob picture, String firstName,
			String lastName, EnumGender gender, String address,
			LocalDate dateOfBirth, String birthplace, Clob description)
	{
		this(id, idCardNumber, nationality, picture, firstName, lastName, gender, address,
				dateOfBirth, birthplace, description, new TreeSet<EnumFlag>());
	}

	public Suspect(int id, int idCardNumber, String nationality, Blob picture, String firstName,
			String lastName, EnumGender gender, String address,
			String dateOfBirth, String birthplace, Clob description)
	{
		this(id, idCardNumber, nationality, picture, firstName, lastName, gender, address,
				LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern(DATE_FORMAT)),
				birthplace, description);
	}

	public Suspect(int idCardNumber, String nationality, Blob picture, String firstName, String lastName,
			EnumGender gender, String address, LocalDate dateOfBirth,
			String birthplace, Clob description)
	{
		this(-99, idCardNumber, nationality, picture, firstName, lastName, gender, address, dateOfBirth,
				birthplace, description);
	}

	public Suspect(int idCardNumber, String nationality, Blob picture, String firstName, String lastName,
			EnumGender gender, String address, String dateOfBirth,
			String birthplace, Clob description)
	{
		this(idCardNumber, nationality, picture, firstName, lastName, gender, address,
				LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern(DATE_FORMAT)),
				birthplace, description);
	}
	
	public void addFlag(EnumFlag f)
	{
		flags.add(f);
	}
	
	public void removeFlag(EnumFlag f)
	{
		flags.remove(f);
	}
	
	public void addFlags(Collection<EnumFlag> toAdd)
	{
		for(EnumFlag f : toAdd)
		{
			flags.add(f);
		}
	}
	
	public void removeFlags(Collection<EnumFlag> toRemove)
	{
		for(EnumFlag f : toRemove)
		{
			flags.remove(f);
		}
	}
	
	public boolean hasFlag(EnumFlag f)
	{
		return flags.contains(f);
	}

	@Override
	public String toString()
	{
		return "Suspect [flags=" + flags + ", getId()=" + getId() + ", getIdCardNumber()=" + getIdCardNumber()
				+ ", getPicture()=" + getPicture() + ", getFirstName()=" + getFirstName() + ", getLastName()="
				+ getLastName() + ", getGender()=" + getGender() + ", getAddress()=" + getAddress()
				+ ", getDateOfBirth()=" + getDateOfBirth() + ", getDateOfBirthAsString()=" + getDateOfBirthAsString()
				+ ", getBirthplace()=" + getBirthplace() + ", getNationality()=" + getNationality() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	
}
