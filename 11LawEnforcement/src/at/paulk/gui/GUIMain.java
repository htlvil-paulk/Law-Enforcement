package at.paulk.gui;

import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import at.paulk.data.Officer;

public class GUIMain extends JFrame implements ActionListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel loginPanel;
	private JTextField txtUsername;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JPasswordField pwdPassword;
	private JButton btnLogin;
	private JLabel lblBanner;
	private JPanel mainPanel;
	private JButton btnAddRecord;
	private JButton btnSearch;
	private JButton btnOfficerManagement;
	private JButton btnLogout;
	private JLabel lblWelcome;
	private JLabel label;

	private Officer currentOfficer;

	/**
	 * Create the frame.
	 */
	public GUIMain()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 540);
		setTitle(Settings.APPLICATION_NAME);
		contentPane = new JPanel();
		contentPane.setBackground(Settings.PRIMARY_COLOR);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLoginPanel());
		contentPane.add(getBtnLogin());
		contentPane.add(getLblBanner());
		contentPane.add(getMainPanel());
		contentPane.add(getLblWelcome());
		contentPane.add(getLabel());
		doLogout();
	}

	private JPanel getLoginPanel()
	{
		if (loginPanel == null)
		{
			loginPanel = new JPanel();
			loginPanel.setBounds(12, 103, 876, 100);
			loginPanel.setLayout(new GridLayout(2, 2, 0, 0));
			loginPanel.add(getLblUsername());
			loginPanel.add(getTxtUsername());
			loginPanel.add(getLblPassword());
			loginPanel.add(getPwdPassword());
			// loginPanel.setVisible(false);
		}
		return loginPanel;
	}

	private JLabel getLblUsername()
	{
		if (lblUsername == null)
		{
			lblUsername = new JLabel("Username");
			lblUsername.setLabelFor(getTxtUsername());
		}
		return lblUsername;
	}

	private JTextField getTxtUsername()
	{
		if (txtUsername == null)
		{
			txtUsername = new JTextField();
			txtUsername.setText("Username");
			txtUsername.setColumns(10);
		}
		return txtUsername;
	}

	private JLabel getLblPassword()
	{
		if (lblPassword == null)
		{
			lblPassword = new JLabel("Password");
			lblPassword.setLabelFor(getPwdPassword());
		}
		return lblPassword;
	}

	private JPasswordField getPwdPassword()
	{
		if (pwdPassword == null)
		{
			pwdPassword = new JPasswordField();
			pwdPassword.setText("Password");
		}
		return pwdPassword;
	}

	private JButton getBtnLogin()
	{
		if (btnLogin == null)
		{
			btnLogin = new JButton("Login");
			btnLogin.setBounds(455, 230, 433, 25);
			btnLogin.addActionListener(this);
		}
		return btnLogin;
	}

	private JLabel getLblBanner()
	{
		if (lblBanner == null)
		{
			lblBanner = new JLabel(Settings.POLICE_DEPARTMENT_NAME);
			lblBanner.setBackground(Settings.SECONDARY_COLOR); //SecondaryColor
			lblBanner.setOpaque(true);
			lblBanner.setBounds(12, 30, 876, 61);
		}
		return lblBanner;
	}

	private JPanel getMainPanel()
	{
		if (mainPanel == null)
		{
			mainPanel = new JPanel();
			mainPanel.setBounds(12, 267, 876, 205);
			mainPanel.setLayout(new GridLayout(4, 2, 0, 0));
			mainPanel.add(getBtnSearch());
			mainPanel.add(getBtnAddRecord());
			mainPanel.add(getBtnOfficerManagement());
			mainPanel.add(getBtnLogout());
			mainPanel.setVisible(false);
		}
		return mainPanel;
	}

	private JButton getBtnAddRecord()
	{
		if (btnAddRecord == null)
		{
			btnAddRecord = new JButton("Add record");
			btnAddRecord.addActionListener(this);
		}
		return btnAddRecord;
	}

	private JButton getBtnSearch()
	{
		if (btnSearch == null)
		{
			btnSearch = new JButton("Search");
			btnSearch.addActionListener(this);
		}
		return btnSearch;
	}

	private JButton getBtnOfficerManagement()
	{
		if (btnOfficerManagement == null)
		{
			btnOfficerManagement = new JButton("Officer Management");
			btnOfficerManagement.addActionListener(this);
		}
		return btnOfficerManagement;
	}

	private JButton getBtnLogout()
	{
		if (btnLogout == null)
		{
			btnLogout = new JButton("Logout");
			btnLogout.addActionListener(this);
		}
		return btnLogout;
	}

	private JLabel getLblWelcome()
	{
		if (lblWelcome == null)
		{
			lblWelcome = new JLabel("Please log in!");
			lblWelcome.setBounds(12, 513, 433, 15);
		}
		return lblWelcome;
	}

	private JLabel getLabel()
	{
		if (label == null)
		{
			label = new JLabel(LocalDate.now().format(
					DateTimeFormatter.ofPattern("dd.MM.uuuu"))
					+ " - " + LocalTime.now());
			label.setBounds(448, 513, 440, 15);
		}
		return label;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		try
		{

			if (e.getSource() == btnLogin)
			{
				doLogin();
			}
			else if (e.getSource() == btnAddRecord)
			{
				new GUIOffender(null);
			}
			else if (e.getSource() == btnOfficerManagement)
			{
				new GUIOfficer(null);
			}
			else if (e.getSource() == btnSearch)
			{
				new GUISearch(null);
			}
			else if (e.getSource() == btnLogout)
			{
				doLogout();
			}
		}
		catch (Exception e1)
		{
			lblWelcome.setText("Error!");
		}
	}

	public void doLogin()
	{
		// currentOfficer = db.createOfficer()

//		if(currentOfficer.getRank() != EnumRank.ASPIRANT)
//		{
				btnAddRecord.setEnabled(true);
//		}

		btnSearch.setEnabled(true);
		btnOfficerManagement.setEnabled(true);
		btnLogout.setEnabled(true);
//		lblWelcome.setText("Welcome, " + currentOfficer.getRank().toString() + " " + currentOfficer.getLastName() + "!");
		
		mainPanel.setVisible(true);
	}
	
	private void doLogout()
	{
		currentOfficer = null;
		for(Component cmp : mainPanel.getComponents())
		{
			if(cmp instanceof JButton )
			{
				cmp.setEnabled(false);
			}
		}
		mainPanel.setVisible(false);
		pwdPassword.setText("");
	}
}
