package at.paulk.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import at.paulk.data.Crime;
import at.paulk.data.Database;
import at.paulk.data.Officer;
import at.paulk.data.Suspect;
import at.paulk.misc.NotAuthorizedException;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JTextPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class GUICrime extends JFrame implements ActionListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblCrimeDetails;
	private JLabel lblFileNumber;
	private JLabel lblDate;
	private JLabel lblShortText;
	private JTextField txtFileNumber;
	private JTextField txtShortText;
	private JFormattedTextField frmtdtxtfldDate;
	private JLabel lblTime;
	private JFormattedTextField frmtdtxtfldTime;
	private JTextField txtCrimeScene;
	private JLabel lblCrimeScene;
	private JLabel lblLongText;
	private JTextPane txtpnLongText;
	private JMenuBar menuBar;
	private JMenu mnCrime;
	private JMenuItem mntmSaveChanges;

	// Non-GUI components
	private Officer loggedInOfficer;
	private Suspect mainSuspect;
	private Database db;
	private boolean isNewCrime = true;
	private Crime committedCrime = null;

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */
	public GUICrime(Officer loggedInOfficer, Suspect s, Crime committedCrime) throws Exception
	{
		if (loggedInOfficer == null)
		{
			throw new NotAuthorizedException("Access denied! You need to log in!");
		}
		if (s == null)
		{
			throw new Exception("Suspect not defined!");
		}
		if (s.getId() == -99)
		{
			throw new Exception("Suspect is not in the database!");
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 550);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getPanel());
		initializeNonGUIComponents(loggedInOfficer, s, committedCrime);
		setVisible(true);
		this.setTitle("Crimes - " + Settings.APPLICATION_NAME);
	}

	public GUICrime(Officer loggedInOfficer, Suspect s) throws Exception
	{
		this(loggedInOfficer, s, null);
	}

	private void initializeNonGUIComponents(Officer o, Suspect s, Crime c) throws SQLException
	{
		db = Database.createInstance();
		loggedInOfficer = o;
		mainSuspect = s;
		committedCrime = c;
		txtFileNumber.setText(Crime.createFileNumber(mainSuspect));
		if (committedCrime != null)
		{
			doLoadCrime(committedCrime);
			isNewCrime = false;
		}
	}

	private JPanel getPanel() throws ParseException
	{
		if (panel == null)
		{
			panel = new JPanel();
			panel.setBounds(12, 42, 876, 370);
			panel.setLayout(null);
			panel.add(getLblCrimeDetails());
			panel.add(getLblFileNumber());
			panel.add(getTxtFileNumber());
			panel.add(getLblShortText());
			panel.add(getTxtShortText());
			panel.add(getLblDate());
			panel.add(getFrmtdtxtfldDate());
			panel.add(getLblTime());
			panel.add(getFrmtdtxtfldTime());
			panel.add(getLblCrimeScene());
			panel.add(getTxtCrimeScene());
			panel.add(getLblLongText());
			panel.add(getTxtpnLongText());
		}
		return panel;
	}

	private JLabel getLblCrimeDetails()
	{
		if (lblCrimeDetails == null)
		{
			lblCrimeDetails = new JLabel("CRIME DETAILS");
			lblCrimeDetails.setBackground(Settings.SECONDARY_COLOR);
			lblCrimeDetails.setOpaque(true);
			lblCrimeDetails.setBounds(12, 12, 852, 30);
		}
		return lblCrimeDetails;
	}

	private JLabel getLblFileNumber()
	{
		if (lblFileNumber == null)
		{
			lblFileNumber = new JLabel("File Number:");
			lblFileNumber.setLabelFor(getTxtFileNumber());
			lblFileNumber.setBounds(22, 54, 200, 20);
		}
		return lblFileNumber;
	}

	private JLabel getLblShortText()
	{
		if (lblShortText == null)
		{
			lblShortText = new JLabel("Short Text:");
			lblShortText.setLabelFor(getTxtShortText());
			lblShortText.setBounds(22, 84, 200, 20);
		}
		return lblShortText;
	}

	private JTextField getTxtFileNumber()
	{
		if (txtFileNumber == null)
		{
			txtFileNumber = new JTextField();
			txtFileNumber.setEnabled(false);
			txtFileNumber.setText("AZ-????-??");
			txtFileNumber.setBounds(240, 52, 200, 20);
			txtFileNumber.setColumns(10);
		}
		return txtFileNumber;
	}

	private JTextField getTxtShortText()
	{
		if (txtShortText == null)
		{
			txtShortText = new JTextField();
			txtShortText.setText("Summary");
			txtShortText.setColumns(10);
			txtShortText.setBounds(240, 85, 200, 20);
		}
		return txtShortText;
	}

	private JLabel getLblDate() throws ParseException
	{
		if (lblDate == null)
		{
			lblDate = new JLabel("Date:");
			lblDate.setLabelFor(getFrmtdtxtfldDate());
			lblDate.setBounds(22, 116, 180, 20);
		}
		return lblDate;
	}

	private JFormattedTextField getFrmtdtxtfldDate() throws ParseException
	{
		if (frmtdtxtfldDate == null)
		{
			String mask = "**.**.****";

			MaskFormatter mf = new MaskFormatter(mask);
			mf.setValidCharacters("1234567890.");

			frmtdtxtfldDate = new JFormattedTextField(mf);
			frmtdtxtfldDate.setText("01.01.2018");
			frmtdtxtfldDate.setBounds(240, 119, 200, 20);
		}
		return frmtdtxtfldDate;
	}

	private JLabel getLblTime() throws ParseException
	{
		if (lblTime == null)
		{
			lblTime = new JLabel("Time:");
			lblTime.setLabelFor(getFrmtdtxtfldTime());
			lblTime.setBounds(22, 150, 200, 20);
		}
		return lblTime;
	}

	private JFormattedTextField getFrmtdtxtfldTime() throws ParseException
	{
		if (frmtdtxtfldTime == null)
		{
			String mask = "**:**";

			MaskFormatter mf = new MaskFormatter(mask);
			mf.setValidCharacters("1234567890:");

			frmtdtxtfldTime = new JFormattedTextField(mf);
			frmtdtxtfldTime.setText("00:00");
			frmtdtxtfldTime.setBounds(240, 151, 200, 20);
		}
		return frmtdtxtfldTime;
	}

	private JTextField getTxtCrimeScene()
	{
		if (txtCrimeScene == null)
		{
			txtCrimeScene = new JTextField();
			txtCrimeScene.setText("Crime Scene");
			txtCrimeScene.setColumns(10);
			txtCrimeScene.setBounds(240, 182, 200, 20);
		}
		return txtCrimeScene;
	}

	private JLabel getLblCrimeScene()
	{
		if (lblCrimeScene == null)
		{
			lblCrimeScene = new JLabel("Crime Scene:");
			lblCrimeScene.setLabelFor(getTxtCrimeScene());
			lblCrimeScene.setBounds(22, 183, 180, 20);
		}
		return lblCrimeScene;
	}

	private JLabel getLblLongText()
	{
		if (lblLongText == null)
		{
			lblLongText = new JLabel("Detailed Report:");
			lblLongText.setLabelFor(getTxtpnLongText());
			lblLongText.setBounds(22, 215, 180, 20);
		}
		return lblLongText;
	}

	private JTextPane getTxtpnLongText()
	{
		if (txtpnLongText == null)
		{
			txtpnLongText = new JTextPane();
			txtpnLongText.setText("long Text");
			txtpnLongText.setBounds(240, 208, 500, 125);
		}
		return txtpnLongText;
	}

	private JMenuBar getMenuBar_1()
	{
		if (menuBar == null)
		{
			menuBar = new JMenuBar();
			menuBar.add(getMnCrime());
		}
		return menuBar;
	}

	private JMenu getMnCrime()
	{
		if (mnCrime == null)
		{
			mnCrime = new JMenu("Crime");
			mnCrime.add(getMntmSaveChanges());
		}
		return mnCrime;
	}

	private JMenuItem getMntmSaveChanges()
	{
		if (mntmSaveChanges == null)
		{
			mntmSaveChanges = new JMenuItem("Save Changes");
			mntmSaveChanges.addActionListener(this);
		}
		return mntmSaveChanges;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			if (e.getSource() == mntmSaveChanges)
			{
				if (isNewCrime)
				{
					Crime c = doCreateCrimeFromInput();
					db.insertCrime(c);
					committedCrime = c;
					JOptionPane.showMessageDialog(this, "Crime '" + committedCrime.getFileNumber() + "' added!",
							"Crime added - " + Settings.APPLICATION_NAME, JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					committedCrime.setCrimeScene(txtCrimeScene.getText());
					committedCrime.setCrimeTime(LocalTime
							.parse(frmtdtxtfldTime.getText(), DateTimeFormatter.ISO_LOCAL_TIME).atDate(LocalDate
									.parse(frmtdtxtfldDate.getText(), DateTimeFormatter.ofPattern("dd.MM.uuuu"))));
					committedCrime.setLongText(txtpnLongText.getText());
					db.updateCrime(committedCrime);
					JOptionPane.showMessageDialog(this, "Crime '" + committedCrime.getFileNumber() + "' updated!",
							"Crime updated - " + Settings.APPLICATION_NAME, JOptionPane.INFORMATION_MESSAGE);
				}
				this.dispose();
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}

	}

	private Crime doCreateCrimeFromInput()
	{
		String fileNumber = txtFileNumber.getText();
		String shortText = txtShortText.getText();
		LocalDate date = LocalDate.parse(frmtdtxtfldDate.getText(), DateTimeFormatter.ofPattern("dd.MM.uuuu"));
		LocalDateTime dateTime = LocalTime.parse(frmtdtxtfldTime.getText(), DateTimeFormatter.ISO_LOCAL_TIME)
				.atDate(date);
		String crimeScene = txtCrimeScene.getText();
		String longText = txtpnLongText.getText();

		return new Crime(fileNumber, shortText, dateTime, crimeScene, longText, mainSuspect);
	}

	private void doLoadCrime(Crime c)
	{
		txtFileNumber.setText(c.getFileNumber());
		txtShortText.setText(c.getShortText());
		String[] dateParts = c.getCrimeTimeAsString().split(" ");
		frmtdtxtfldDate.setText(dateParts[0]);
		frmtdtxtfldTime.setValue(dateParts[1]);
		txtCrimeScene.setText(c.getCrimeScene());
		txtpnLongText.setText(c.getLongText());
	}
}
