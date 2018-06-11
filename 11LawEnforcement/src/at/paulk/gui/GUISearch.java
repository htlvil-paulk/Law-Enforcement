package at.paulk.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JButton;

import at.paulk.data.Officer;

public class GUISearch extends JFrame implements ActionListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblSearchSuspect;
	private JLabel lblFileNumber;
	private JLabel lblName;
	private JLabel lblLastName;
	private JLabel lblIdCardNumber;
	private JTextField txtFileNumber;
	private JTextField txtName;
	private JTextField txtLastName;
	private JTextField txtIDCardNumber;
	private JButton btnSearchForSuspects;
	private JPanel panel_1;
	private JLabel lblHeaderResults;

	private Officer currentOfficer;
	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public GUISearch(Officer officer) throws Exception
	{
		if (officer == null)
		{
			throw new Exception("Permission denied: The officer must be specified!");
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getPanel());
		contentPane.add(getPanel_1());
		setVisible(true);
		initializeNonGUIComponents(officer);
	}
	
	private void initializeNonGUIComponents(Officer officer)
	{
		currentOfficer = officer;
	}

	private JPanel getPanel()
	{
		if (panel == null)
		{
			panel = new JPanel();
			panel.setBounds(12, 12, 876, 241);
			panel.setLayout(null);
			panel.add(getLblSearchSuspect());
			panel.add(getLblFileNumber());
			panel.add(getLblName());
			panel.add(getLblLastName());
			panel.add(getLblIdCardNumber());
			panel.add(getTxtFileNumber());
			panel.add(getTxtName());
			panel.add(getTxtLastName());
			panel.add(getTxtIDCardNumber());
			panel.add(getBtnSearchForSuspects());
		}
		return panel;
	}

	private JLabel getLblSearchSuspect()
	{
		if (lblSearchSuspect == null)
		{
			lblSearchSuspect = new JLabel("SEARCH SUSPECT");
			lblSearchSuspect.setBackground(new Color(0, 255, 255));
			lblSearchSuspect.setOpaque(true);
			lblSearchSuspect.setBounds(12, 12, 852, 30);
		}
		return lblSearchSuspect;
	}

	private JLabel getLblFileNumber()
	{
		if (lblFileNumber == null)
		{
			lblFileNumber = new JLabel("File Number");
			lblFileNumber.setLabelFor(getTxtFileNumber());
			lblFileNumber.setBounds(22, 54, 200, 30);
		}
		return lblFileNumber;
	}

	private JLabel getLblName()
	{
		if (lblName == null)
		{
			lblName = new JLabel("Name:");
			lblName.setLabelFor(getTxtName());
			lblName.setBounds(22, 84, 200, 30);
		}
		return lblName;
	}

	private JLabel getLblLastName()
	{
		if (lblLastName == null)
		{
			lblLastName = new JLabel("Last name:");
			lblLastName.setLabelFor(getTxtLastName());
			lblLastName.setBounds(22, 113, 200, 30);
		}
		return lblLastName;
	}

	private JLabel getLblIdCardNumber()
	{
		if (lblIdCardNumber == null)
		{
			lblIdCardNumber = new JLabel("ID card number:");
			lblIdCardNumber.setLabelFor(lblIdCardNumber);
			lblIdCardNumber.setBounds(22, 140, 200, 30);
		}
		return lblIdCardNumber;
	}

	private JTextField getTxtFileNumber()
	{
		if (txtFileNumber == null)
		{
			txtFileNumber = new JTextField();
			txtFileNumber.setText("File Number");
			txtFileNumber.setBounds(225, 54, 200, 30);
			txtFileNumber.setColumns(10);
		}
		return txtFileNumber;
	}

	private JTextField getTxtName()
	{
		if (txtName == null)
		{
			txtName = new JTextField();
			txtName.setText("Name");
			txtName.setBounds(225, 84, 200, 30);
			txtName.setColumns(10);
		}
		return txtName;
	}

	private JTextField getTxtLastName()
	{
		if (txtLastName == null)
		{
			txtLastName = new JTextField();
			txtLastName.setText("Last Name");
			txtLastName.setBounds(225, 113, 200, 30);
			txtLastName.setColumns(10);
		}
		return txtLastName;
	}

	private JTextField getTxtIDCardNumber()
	{
		if (txtIDCardNumber == null)
		{
			txtIDCardNumber = new JTextField();
			txtIDCardNumber.setText("Name");
			txtIDCardNumber.setColumns(10);
			txtIDCardNumber.setBounds(225, 140, 200, 30);
		}
		return txtIDCardNumber;
	}

	private JButton getBtnSearchForSuspects()
	{
		if (btnSearchForSuspects == null)
		{
			btnSearchForSuspects = new JButton("Search For Suspects");
			btnSearchForSuspects.setBounds(505, 78, 270, 43);
			btnSearchForSuspects.addActionListener(this);
		}
		return btnSearchForSuspects;
	}

	private JPanel getPanel_1()
	{
		if (panel_1 == null)
		{
			panel_1 = new JPanel();
			panel_1.setBounds(12, 265, 876, 194);
			panel_1.setLayout(null);
			panel_1.add(getLblHeaderResults());
		}
		return panel_1;
	}

	private JLabel getLblHeaderResults()
	{
		if (lblHeaderResults == null)
		{
			lblHeaderResults = new JLabel("RESULTS");
			lblHeaderResults.setOpaque(true);
			lblHeaderResults.setBackground(Settings.SECONDARY_COLOR);
			lblHeaderResults.setBounds(12, 12, 852, 30);
		}
		return lblHeaderResults;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			if (e.getSource() == btnSearchForSuspects)
			{
				
			}
			else
			{
				
			}
		}
		catch (Exception e2)
		{
			e2.printStackTrace();
		}
		
	}
}
