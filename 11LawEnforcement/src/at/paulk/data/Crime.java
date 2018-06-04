package at.paulk.data;

import java.sql.Clob;
import java.time.LocalDate;
import java.time.LocalTime;

public class Crime
{
	private String fileNumber;
	private String shortText;
	private LocalDate date;
	private LocalTime time;
	private String crimeScene;
	private Clob longText;
	private Suspect mainSuspect;

	public Crime(String fileNumber, String shortText, LocalDate date,
			LocalTime time, String crimeScene, Clob longText,
			Suspect mainSuspect)
	{
		setFileNumber(fileNumber);
		setShortText(shortText);
		setDate(date);
		setTime(time);
		setCrimeScene(crimeScene);
		setMainSuspect(mainSuspect);
		setLongText(longText);
	}

	public Crime(String shortText, LocalDate date, LocalTime time,
			String crimeScene, Clob longText, Suspect mainSuspect)
	{
		this(createFileNumber() ,shortText, date, time, crimeScene, longText, mainSuspect);
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

	public LocalDate getDate()
	{
		return date;
	}
	public void setDate(LocalDate date)
	{
		this.date = date;
	}

	public LocalTime getTime()
	{
		return time;
	}
	public void setTime(LocalTime time)
	{
		this.time = time;
	}

	public String getCrimeScene()
	{
		return crimeScene;
	}
	public void setCrimeScene(String crimeScene)
	{
		this.crimeScene = crimeScene;
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

	private static String createFileNumber()
	{
		return "AZ/" + LocalDate.now().getYear() + "-" + LocalDate.now().getDayOfYear();
	}
}