package at.paulk.main;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import at.paulk.gui.GUIMain;

public class LawEnforcementDB
{
	public static void main(String[] args) 
	{
		try
		{
			System.out.println("Starting police database... please wait");
			JFrame start = new GUIMain();
			start.setVisible(true);
		}
		catch (SQLException e)
		{
	        JOptionPane.showMessageDialog(null, "Error: Connection to the database cannot be established!", "Initialization failed - " + at.paulk.gui.Settings.APPLICATION_NAME, JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		catch (Exception e)
		{
	        JOptionPane.showMessageDialog(null, e.getMessage(), "Initialization failed - " + at.paulk.gui.Settings.APPLICATION_NAME, JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}
