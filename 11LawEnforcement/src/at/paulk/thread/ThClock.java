package at.paulk.thread;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;

public class ThClock extends Thread
{
	private LocalDateTime clock = null;
	private JLabel reference = null;
	private boolean isFinished = false;
	private static final int REFRESH_TIME = 1000;
	
	public ThClock(JLabel ref)
	{
		clock = LocalDateTime.now();
		this.reference = ref;
	}

	public void setFinished()
	{
		isFinished = true;
	}
	
	public JLabel getReference()
	{
		return reference;
	}
	
	public void setReference(JLabel ref)
	{
		this.reference = ref;
	}

	@Override
	public void run()
	{
		try
		{
			while(!isFinished)
			{
				reference.setText(clock.format(DateTimeFormatter.ofPattern("dd.MM.uuuu HH:mm:ss")));
				clock = clock.plusSeconds(1);
				sleep(REFRESH_TIME);
			}
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
	}
}
