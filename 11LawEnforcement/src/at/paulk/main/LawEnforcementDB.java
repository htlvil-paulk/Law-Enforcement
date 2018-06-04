package at.paulk.main;

import javax.swing.JFrame;

import at.paulk.gui.GUIMain;

public class LawEnforcementDB
{
	public static void main(String[] args) 
	{
		try
		{
			//new Officer(1, 998213541, "", "Kevin", "Paul", EnumGender.MALE, "nowhere", LocalDate.now(), "Villach", "paulk", "password".toCharArray(), EnumRank.GENERAL)
			JFrame start = new GUIMain();
			start.setVisible(true);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
