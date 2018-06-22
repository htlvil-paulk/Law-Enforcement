package at.paulk.thread;

import javax.swing.JOptionPane;

import at.paulk.data.Database;
import at.paulk.gui.Settings;

public class ThLoadTimer extends Thread
{
	private Database reference;
	private static final int TIMEOUT = 5000;
	
	public ThLoadTimer(Database db)
	{
		this.reference = db;
	}

	@Override
	public void run()
	{
		try
		{
			Thread.sleep(TIMEOUT);
			if(!(reference != null && !reference.isInitialized()))
			{
				JOptionPane.showMessageDialog(null, "The initialization can take longer than usual", "Warning - " + Settings.APPLICATION_NAME, JOptionPane.WARNING_MESSAGE);
			}
			
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
