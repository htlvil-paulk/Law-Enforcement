package at.paulk.data;

import java.sql.Blob;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Person
{
	private int id;
	private int idCardNumber;
	private String nationality;
	private Blob picture;
	private String firstName, lastName;
	private EnumGender gender;
	private String address;
	private LocalDate dateOfBirth;
	private String birthplace;
	protected final static String DATE_FORMAT = "dd.MM.uuuu";

	public Person(int id, int idCardNumber, String nationality, Blob picture, String firstName, String lastName,
			EnumGender gender, String address, LocalDate dateOfBirth, String birthplace)
	{
		super();
		setId(id);
		setIdCardNumber(idCardNumber);
		setNationality(nationality);
		setPicture(picture);
		setFirstName(firstName);
		setLastName(lastName);
		setGender(gender);
		setAddress(address);
		setDateOfBirth(dateOfBirth);
		setBirthplace(birthplace);
	}

	// CTOR with String as dateOfBirth
	public Person(int id, int idCardNumber, String nationality, Blob picture, String firstName, String lastName,
			EnumGender gender, String address, String dateOfBirth, String birthplace)
	{
		this(id, idCardNumber, nationality, picture, firstName, lastName, gender, address,
				LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern(DATE_FORMAT)), birthplace);
	}

	// CTOR without id
	public Person(int idCardNumber, String nationality, Blob picture, String firstName, String lastName,
			EnumGender gender, String address, LocalDate dateOfBirth, String birthplace)
	{
		this(-99, idCardNumber, nationality, picture, firstName, lastName, gender, address, dateOfBirth, birthplace);
	}

	// CTOR without id and String as dateOfBirth
	public Person(int idCardNumber, String nationality, Blob picture, String firstName, String lastName,
			EnumGender gender, String address, String dateOfBirth, String birthplace)
	{
		this(-99, idCardNumber, nationality, picture, firstName, lastName, gender, address,
				LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern(DATE_FORMAT)), birthplace);
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getIdCardNumber()
	{
		return idCardNumber;
	}

	public void setIdCardNumber(int idCardNumber)
	{
		this.idCardNumber = idCardNumber;
	}

	public Blob getPicture()
	{
		return picture;
	}

//	public void setPicture(File picture) throws IOException
//	{
//		byte[] fileContent = new byte[(int) picture.length()];
//		FileInputStream inputStream = null;
//		try
//		{
//			// create an input stream pointing to the file
//			inputStream = new FileInputStream(picture);
//			// read the contents of file into byte array
//			inputStream.read(fileContent);
//		}
//		catch (IOException e)
//		{
//			throw new IOException("Unable to convert file to byte array. " + e.getMessage());
//		}
//		finally
//		{
//			// close input stream
//			if (inputStream != null)
//			{
//				inputStream.close();
//			}
//		}
//		this.picture = fileContent;
//	}

	public void setPicture(Blob picture)
	{
		this.picture = picture;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName.toUpperCase();
	}

	public EnumGender getGender()
	{
		return gender;
	}

	public void setGender(EnumGender gender)
	{
		this.gender = gender;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public LocalDate getDateOfBirth()
	{
		return dateOfBirth;
	}

	public String getDateOfBirthAsString()
	{
		return dateOfBirth.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
	}

	public void setDateOfBirth(LocalDate dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth)
	{
		setDateOfBirth(LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern(DATE_FORMAT)));
	}

	public String getBirthplace()
	{
		return birthplace;
	}

	public void setBirthplace(String birthplace)
	{
		this.birthplace = birthplace;
	}

	public String getNationality()
	{
		return nationality;
	}

	public void setNationality(String nationality)
	{
		this.nationality = nationality;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + idCardNumber;
		result = prime * result + ((nationality == null) ? 0 : nationality.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		if (idCardNumber != other.idCardNumber)
			return false;
		if (nationality == null)
		{
			if (other.nationality != null)
				return false;
		}
		else if (!nationality.equals(other.nationality))
			return false;
		return true;
	}
}
