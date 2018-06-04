package at.paulk.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class GUICrime extends JFrame
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

	/**
	 * Create the frame.
	 */
	public GUICrime()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 550);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getPanel());
	}

	private JPanel getPanel()
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

	private JLabel getLblDate()
	{
		if (lblDate == null)
		{
			lblDate = new JLabel("Date:");
			lblDate.setLabelFor(getFrmtdtxtfldDate());
			lblDate.setBounds(22, 116, 180, 20);
		}
		return lblDate;
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
			txtFileNumber.setText("File Number");
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

	private JFormattedTextField getFrmtdtxtfldDate()
	{
		if (frmtdtxtfldDate == null)
		{
			frmtdtxtfldDate = new JFormattedTextField();
			frmtdtxtfldDate.setText("01.01.2018");
			frmtdtxtfldDate.setBounds(240, 119, 200, 20);
		}
		return frmtdtxtfldDate;
	}

	private JLabel getLblTime()
	{
		if (lblTime == null)
		{
			lblTime = new JLabel("Time:");
			lblTime.setLabelFor(getFrmtdtxtfldTime());
			lblTime.setBounds(22, 150, 200, 20);
		}
		return lblTime;
	}

	private JFormattedTextField getFrmtdtxtfldTime()
	{
		if (frmtdtxtfldTime == null)
		{
			frmtdtxtfldTime = new JFormattedTextField();
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
		}
		return mntmSaveChanges;
	}
}
