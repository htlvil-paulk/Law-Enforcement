package at.paulk.gui;

import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import at.paulk.data.Database;
import at.paulk.data.EnumRank;
import at.paulk.data.Officer;
import at.paulk.thread.ThClock;
import at.paulk.thread.ThLoadTimer;

import javax.swing.SwingConstants;

public class GUIMain extends JFrame implements ActionListener, WindowListener
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
	private JLabel lblClock;

	private GUIOffender offenderGUI;
	private GUIOfficer officerGUI;
	private GUISearch searchGUI;

	private Officer currentOfficer;
	private Database db;
	private ThClock clock;

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public GUIMain() throws SQLException
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 575);
		setTitle(Settings.APPLICATION_NAME);
		contentPane = new JPanel();
		contentPane.setBackground(Settings.PRIMARY_COLOR);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getMainPanel());
		contentPane.add(getLoginPanel());
		contentPane.add(getBtnLogin());
		contentPane.add(getLblBanner());
		contentPane.add(getLblWelcome());
		contentPane.add(getLabel());
		initializeNonGUIComponents();
	}

	private void initializeNonGUIComponents() throws SQLException
	{
		db = Database.createInstance();
		doLogout();
		clock = new ThClock(lblClock);
		clock.start();
	}

	private JPanel getLoginPanel()
	{
		if (loginPanel == null)
		{
			loginPanel = new JPanel();
			loginPanel.setBounds(12, 103, 862, 100);
			loginPanel.setLayout(new GridLayout(2, 2, 0, 0));
			loginPanel.add(getLblUsername());
			loginPanel.add(getTxtUsername());
			loginPanel.add(getLblPassword());
			loginPanel.add(getPwdPassword());
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
			txtUsername.setText("kreuzf");
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
			pwdPassword.setText("");
		}
		return pwdPassword;
	}

	private JButton getBtnLogin()
	{
		if (btnLogin == null)
		{
			btnLogin = new JButton("Login");
			btnLogin.setBounds(455, 230, 419, 25);
			btnLogin.addActionListener(this);
		}
		return btnLogin;
	}

	private JLabel getLblBanner()
	{
		if (lblBanner == null)
		{
			lblBanner = new JLabel(Settings.POLICE_DEPARTMENT_NAME);
			lblBanner.setBackground(Settings.SECONDARY_COLOR); // SecondaryColor
			lblBanner.setOpaque(true);
			lblBanner.setBounds(12, 30, 862, 61);
		}
		return lblBanner;
	}

	private JPanel getMainPanel()
	{
		if (mainPanel == null)
		{
			mainPanel = new JPanel();
			mainPanel.setBounds(12, 215, 862, 257);
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
		if (lblClock == null)
		{
			lblClock = new JLabel(
					LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.uuuu")) + " - " + LocalTime.now());
			lblClock.setHorizontalAlignment(SwingConstants.RIGHT);
			lblClock.setHorizontalTextPosition(SwingConstants.CENTER);
			lblClock.setBounds(448, 513, 426, 15);
		}
		return lblClock;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			if (e.getSource() == btnLogin)
			{
				doLogin(txtUsername.getText(), pwdPassword.getPassword());
			}
			else if (e.getSource() == btnAddRecord)
			{
				openGUI(GUIOffender.class);
			}
			else if (e.getSource() == btnOfficerManagement)
			{
				openGUI(GUIOfficer.class);
			}
			else if (e.getSource() == btnSearch)
			{
				openGUI(GUISearch.class);
			}
			else if (e.getSource() == btnLogout)
			{
				closeGUI(offenderGUI);
				closeGUI(searchGUI);
				closeGUI(officerGUI);
				doLogout();
			}
		}
		catch (SQLException sqlex)
		{
			lblWelcome.setText("Error: " + sqlex.getMessage());
			JOptionPane.showMessageDialog(this, sqlex.getMessage(), "Error " + Settings.APPLICATION_NAME,
					JOptionPane.ERROR_MESSAGE);
			sqlex.printStackTrace();
		}
		catch (Exception e1)
		{
			lblWelcome.setText("Error: " + e1.getMessage());
			JOptionPane.showMessageDialog(this, e1.getMessage(), "Error " + Settings.APPLICATION_NAME,
					JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
	}

	public void doLogin(String username, char[] password) throws Exception
	{
		currentOfficer = db.login(username, password);

		if (currentOfficer.getRank() != EnumRank.ASPIRANT)
		{
			btnAddRecord.setEnabled(true);
		}

		btnSearch.setEnabled(true);
		btnOfficerManagement.setEnabled(true);
		btnLogout.setEnabled(true);
		lblWelcome
				.setText("Welcome, " + currentOfficer.getRank().toString() + " " + currentOfficer.getLastName() + "!");
		btnLogin.setVisible(false);
		mainPanel.setVisible(true);
		txtUsername.setEnabled(false);
		pwdPassword.setEnabled(false);
	}

	private void doLogout()
	{
		currentOfficer = null;
		for (Component cmp : mainPanel.getComponents())
		{
			if (cmp instanceof JButton)
			{
				cmp.setEnabled(false);
			}
		}
		mainPanel.setVisible(false);
		pwdPassword.setText("secret");
		btnLogin.setVisible(true);
		txtUsername.setEnabled(true);
		pwdPassword.setEnabled(true);
		lblWelcome.setText("Please login!");
	}

	private void openGUI(Class f) throws Exception
	{
		if (f == GUIOffender.class && offenderGUI == null)
		{
			offenderGUI = new GUIOffender(currentOfficer);
			offenderGUI.addWindowListener(this);
		}
		else if (f == GUISearch.class && searchGUI == null)
		{
			searchGUI = new GUISearch(currentOfficer);
			searchGUI.addWindowListener(this);
		}
		else if (f == GUIOfficer.class && officerGUI == null)
		{
			officerGUI = new GUIOfficer(currentOfficer);
			officerGUI.addWindowListener(this);
		}
	}

	private void closeGUI(JFrame f)
	{
		if (f != null)
		{
			if (f instanceof GUIOffender)
			{
				offenderGUI.dispose();
				offenderGUI = null;
			}
			else if (f instanceof GUISearch)
			{
				searchGUI.dispose();
				searchGUI = null;
			}
			else if (f instanceof GUIOfficer)
			{
				officerGUI.dispose();
				officerGUI = null;
			}
		}
	}

	@Override
	public void windowOpened(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e)
	{
		if(e.getSource() == offenderGUI)
		{
			offenderGUI = null;
		}
		else if(e.getSource() == officerGUI)
		{
			officerGUI = null;
		}
		else if(e.getSource() == searchGUI)
		{
			searchGUI = null;
		}
		
	}

	@Override
	public void windowIconified(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}
}