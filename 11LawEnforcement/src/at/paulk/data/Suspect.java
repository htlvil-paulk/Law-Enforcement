package at.paulk.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.TreeSet;

public class Suspect extends Person
{
	private TreeSet<EnumFlag> flags = new TreeSet<>();

	public Suspect(int id, String pathToPicture, String firstName,
			String lastName, EnumGender gender, String address,
			LocalDate dateOfBirth, String birthplace, Collection<EnumFlag> flags)
	{
		super(id, pathToPicture, firstName, lastName, gender, address,
				dateOfBirth, birthplace);
		addFlags(flags);
	}

	public Suspect(int id, String pathToPicture, String firstName,
			String lastName, EnumGender gender, String address,
			LocalDate dateOfBirth, String birthplace)
	{
		this(id, pathToPicture, firstName, lastName, gender, address,
				dateOfBirth, birthplace, null);
	}

	public Suspect(int id, String pathToPicture, String firstName,
			String lastName, EnumGender gender, String address,
			String dateOfBirth, String birthplace)
	{
		this(id, pathToPicture, firstName, lastName, gender, address,
				LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern(DATE_FORMAT)),
				birthplace);
	}

	public Suspect(String pathToPicture, String firstName, String lastName,
			EnumGender gender, String address, LocalDate dateOfBirth,
			String birthplace)
	{
		this(-99, pathToPicture, firstName, lastName, gender, address, dateOfBirth,
				birthplace);
	}

	public Suspect(String pathToPicture, String firstName, String lastName,
			EnumGender gender, String address, String dateOfBirth,
			String birthplace)
	{
		this(pathToPicture, firstName, lastName, gender, address,
				LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern(DATE_FORMAT)),
				birthplace);
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
}