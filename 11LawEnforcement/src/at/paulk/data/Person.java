package at.paulk.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Person
{
	private int id;
	private int idCardNumber;
	private String pathToPicture;
	private String firstName, lastName;
	private EnumGender gender;
	private String address;
	private LocalDate dateOfBirth;
	private String birthplace;
	protected final static String DATE_FORMAT = "dd.MM.uuuu";
	
	
	public Person(int id, int idCardNumber, String pathToPicture, String firstName,
			String lastName, EnumGender gender, String address,
			LocalDate dateOfBirth, String birthplace)
	{
		super();
		setId(id);
		setIdCardNumber(idCardNumber);
		setPathToPicture(pathToPicture);
		setFirstName(firstName);
		setLastName(lastName);
		setGender(gender);
		setAddress(address);
		setDateOfBirth(dateOfBirth);
		setBirthplace(birthplace);
	}
	
	//CTOR with String as dateOfBirth
	public Person(int id, int idCardNumber, String pathToPicture, String firstName,
			String lastName, EnumGender gender, String address,
			String dateOfBirth, String birthplace)
	{
		this(id, idCardNumber, pathToPicture, firstName, lastName, gender, address, LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern(DATE_FORMAT)), birthplace);
	}

	//CTOR without id
	public Person(int idCardNumber, String pathToPicture, String firstName,
			String lastName, EnumGender gender, String address,
			LocalDate dateOfBirth, String birthplace)
	{
		this(-99, idCardNumber, pathToPicture, firstName, lastName, gender, address, dateOfBirth, birthplace);
	}
	
	//CTOR without id and String as dateOfBirth
	public Person(int idCardNumber, String pathToPicture, String firstName,
			String lastName, EnumGender gender, String address,
			String dateOfBirth, String birthplace)
	{
		this(-99, idCardNumber, pathToPicture, firstName, lastName, gender, address, LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern(DATE_FORMAT)), birthplace);
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

	public String getPathToPicture()
	{
		return pathToPicture;
	}
	public void setPathToPicture(String pathToPicture)
	{
		this.pathToPicture = pathToPicture;
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

	
}
