package at.paulk.data;

import java.sql.Clob;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Crime
{
	private String fileNumber;
	private String shortText;
	private LocalDateTime crimeTime;
	private String crimeScene;
	private Clob longText;
	private Suspect mainSuspect;

	public Crime(String fileNumber, String shortText, LocalDateTime crimeTime, String crimeScene, Clob longText,
			Suspect mainSuspect)
	{
		setFileNumber(fileNumber);
		setShortText(shortText);
		setCrimeTime(crimeTime);
		setCrimeScene(crimeScene);
		setMainSuspect(mainSuspect);
		setLongText(longText);
	}

	public Crime(String shortText, LocalDateTime crimeTime,
			String crimeScene, Clob longText, Suspect mainSuspect)
	{
		this(createFileNumber(mainSuspect) ,shortText, crimeTime, crimeScene, longText, mainSuspect);
	}
	
	public Crime(String shortText, LocalDate date, LocalTime time,
			String crimeScene, Clob longText, Suspect mainSuspect)
	{
		this(createFileNumber(mainSuspect) ,shortText, time.atDate(date), crimeScene, longText, mainSuspect);
	}

	public String getFileNumber()
	{
		return fileNumber;
	}
	public void setFileNumber(String fileNumber)
	{
		this.fileNumber = fileNumber;
	}

	public String getShortText()
	{
		return shortText;
	}
	public void setShortText(String shortText)
	{
		this.shortText = shortText;
	}

	public String getCrimeScene()
	{
		return crimeScene;
	}
	public void setCrimeScene(String crimeScene)
	{
		this.crimeScene = crimeScene;
	}

	public LocalDateTime getCrimeTime()
	{
		return crimeTime;
	}
	
	public String getCrimeTimeAsString()
	{
		return crimeTime.format(DateTimeFormatter.ofPattern("dd.MM.uuuu")) + crimeTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
	}

	public void setCrimeTime(LocalDateTime crimeTime)
	{
		this.crimeTime = crimeTime;
	}

	public Clob getLongText()
	{
		return longText;
	}
	public void setLongText(Clob longText)
	{
		this.longText = longText;
	}

	public Suspect getMainSuspect()
	{
		return mainSuspect;
	}
	public void setMainSuspect(Suspect mainSuspect)
	{
		this.mainSuspect = mainSuspect;
	}

	public static String createFileNumber(Suspect s)
	{
		return "AZ/" + LocalDate.now().getYear() + "-" + LocalDate.now().getDayOfYear() + LocalTime.now().toSecondOfDay()/60 + "-" + s.getLastName().replace(' ', '_') + s.getId();
	}
}