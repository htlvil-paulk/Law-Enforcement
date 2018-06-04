package at.paulk.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;

import java.awt.Color;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

import at.paulk.data.EnumGender;
import at.paulk.data.Officer;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextPane;

public class GUIOffender extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private JPanel infoPanel;
	private JLabel lblInformation;
	private JLabel lblPicture;
	private JLabel lblName;
	private JTextField txtName;
	private JLabel lblLastName;
	private JTextField txtLastName;
	private JLabel lblDateOfBirth;
	private JFormattedTextField frmtdtxtfldDateOfBirth;
	private JLabel lblBirthplace;
	private JTextField txtBirthplace;
	private JLabel lblNationality;
	private JTextField txtNationality;
	private JLabel lblGender;
	private JComboBox<EnumGender> comboBoxGender;
	private DefaultComboBoxModel<EnumGender> modGender = new DefaultComboBoxModel<EnumGender>();
	private JLabel lblAddress;
	private JTextField txtAddress;

	private JPanel flagPanel;
	private JLabel lblFlags;
	private JCheckBox chckbxIsViolent;
	private JCheckBox chckbxHasADrugProblem;
	private JCheckBox chckbxIsFugitive;
	private JCheckBox chckbxIsASexOffender;
	private JCheckBox chckbxIsMentallyIll;
	private JCheckBox chckbxIsSuicidal;
	private JCheckBox chckbxIsArmed;
	private JCheckBox chckBxIsPartOfIllegalOrganizations;

	private JPanel committedCrimesPanel;
	private JLabel lblCommittedCrimes;

	// Non-GUI Components
	private Officer currentOfficer;
	private JTextField txtIDCardNumber;
	private JLabel lblIDCardNumber;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmExportCurrentFile;
	private JMenuItem mntmDeleteFile;
	private JLabel lblDescription;
	private JTextPane txtpnDescription;

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */
	public GUIOffender(Officer officer) throws Exception
	{
		// if(officer == null)
		// {
		// throw new
		// Exception("Permission denied: The officer must be specified!");
		// }

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 780);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getInfoPanel());
		contentPane.add(getFlagPanel());
		contentPane.add(getMenuBar_1());
		contentPane.add(getCommittedCrimesPanel());
		setVisible(true);

		initializeNonGUIComponents(officer);
	}

	private void initializeNonGUIComponents(Officer officer)
	{
		currentOfficer = officer;
	}

	private JPanel getInfoPanel() throws ParseException
	{
		if (infoPanel == null)
		{
			infoPanel = new JPanel();
			infoPanel.setBounds(12, 26, 878, 333);
			infoPanel.setLayout(null);
			infoPanel.add(getLblInformation());
			infoPanel.add(getLblPicture());
			infoPanel.add(getLblName());
			infoPanel.add(getTxtName());
			infoPanel.add(getLblLastName());
			infoPanel.add(getTxtLastName());
			infoPanel.add(getLblDateOfBirth());
			infoPanel.add(getFrmtdtxtfldDateOfBirth());
			infoPanel.add(getLblGender());
			infoPanel.add(getComboBoxGender());
			infoPanel.add(getLblAddress());
			infoPanel.add(getTxtAddress());
			infoPanel.add(getLblNationality());
			infoPanel.add(getTxtNationality());
			infoPanel.add(getLblBirthplace());
			infoPanel.add(getTxtBirthplace());
			infoPanel.add(getLblIDCardNumber());
			infoPanel.add(getTxtIDCardNumber());
			infoPanel.add(getLblDescription());
			infoPanel.add(getTxtpnDescription());
		}
		return infoPanel;
	}

	private JLabel getLblInformation()
	{
		if (lblInformation == null)
		{
			lblInformation = new JLabel("INFORMATION");
			lblInformation.setOpaque(true);
			lblInformation.setBackground(Settings.SECONDARY_COLOR);
			lblInformation.setBounds(12, 12, 854, 34);
		}
		return lblInformation;
	}

	private JLabel getLblPicture()
	{
		if (lblPicture == null)
		{
			lblPicture = new JLabel();
			lblPicture.setBounds(12, 58, 149, 164);
			lblPicture.setOpaque(true);
			lblPicture.setBackground(new Color(192, 192, 192));
		}
		return lblPicture;
	}

	private JLabel getLblName()
	{
		if (lblName == null)
		{
			lblName = new JLabel("Name:");
			lblName.setLabelFor(getTxtName());
			lblName.setBounds(173, 68, 149, 34);
		}
		return lblName;
	}

	private JLabel getLblLastName()
	{
		if (lblLastName == null)
		{
			lblLastName = new JLabel("Last Name:");
			lblLastName.setLabelFor(getTxtLastName());
			lblLastName.setBounds(173, 105, 149, 34);
		}
		return lblLastName;
	}

	private JLabel getLblDateOfBirth() throws ParseException
	{
		if (lblDateOfBirth == null)
		{
			lblDateOfBirth = new JLabel("Date of Birth");
			lblDateOfBirth.setLabelFor(getFrmtdtxtfldDateOfBirth());
			lblDateOfBirth.setBounds(554, 105, 149, 34);
		}
		return lblDateOfBirth;
	}

	private JLabel getLblBirthplace()
	{
		if (lblBirthplace == null)
		{
			lblBirthplace = new JLabel("Birthplace");
			lblBirthplace.setLabelFor(getTxtBirthplace());
			lblBirthplace.setBounds(554, 140, 149, 37);
		}
		return lblBirthplace;
	}

	private JLabel getLblNationality()
	{
		if (lblNationality == null)
		{
			lblNationality = new JLabel("Nationality");
			lblNationality.setLabelFor(getTxtNationality());
			lblNationality.setBounds(173, 142, 149, 34);
		}
		return lblNationality;
	}

	private JLabel getLblGender()
	{
		if (lblGender == null)
		{
			lblGender = new JLabel("Gender:");
			lblGender.setLabelFor(getComboBoxGender());
			lblGender.setBounds(173, 176, 149, 34);
		}
		return lblGender;
	}

	private JLabel getLblAddress()
	{
		if (lblAddress == null)
		{
			lblAddress = new JLabel("Address");
			lblAddress.setLabelFor(getTxtAddress());
			lblAddress.setBounds(554, 180, 149, 34);
		}
		return lblAddress;
	}

	private JTextField getTxtAddress()
	{
		if (txtAddress == null)
		{
			txtAddress = new JTextField();
			txtAddress.setText("Address");
			txtAddress.setBounds(704, 178, 149, 36);
			txtAddress.setColumns(10);
		}
		return txtAddress;
	}

	private JTextField getTxtName()
	{
		if (txtName == null)
		{
			txtName = new JTextField();
			txtName.setText("name");
			txtName.setBounds(322, 68, 149, 34);
			txtName.setColumns(10);
		}
		return txtName;
	}

	private JTextField getTxtLastName()
	{
		if (txtLastName == null)
		{
			txtLastName = new JTextField();
			txtLastName.setText("last name");
			txtLastName.setBounds(322, 105, 149, 34);
			txtLastName.setColumns(10);
		}
		return txtLastName;
	}

	private JTextField getTxtNationality()
	{
		if (txtNationality == null)
		{
			txtNationality = new JTextField();
			txtNationality.setText("Nationality");
			txtNationality.setBounds(322, 142, 149, 34);
			txtNationality.setColumns(10);
		}
		return txtNationality;
	}

	private JFormattedTextField getFrmtdtxtfldDateOfBirth()
			throws ParseException
	{
		if (frmtdtxtfldDateOfBirth == null)
		{
			String mask = "**.**.****";

			MaskFormatter mf = new MaskFormatter(mask);
			mf.setValidCharacters("1234567890.");

			frmtdtxtfldDateOfBirth = new JFormattedTextField(mf);
			frmtdtxtfldDateOfBirth.setText(LocalDate.now().format(
					DateTimeFormatter.ofPattern("dd.MM.uuuu")));
			frmtdtxtfldDateOfBirth.setBounds(704, 105, 149, 34);
		}
		return frmtdtxtfldDateOfBirth;
	}

	private JTextField getTxtBirthplace()
	{
		if (txtBirthplace == null)
		{
			txtBirthplace = new JTextField();
			txtBirthplace.setText("birthplace");
			txtBirthplace.setBounds(704, 141, 149, 34);
			txtBirthplace.setColumns(10);
		}
		return txtBirthplace;
	}

	private JPanel getFlagPanel()
	{
		if (flagPanel == null)
		{
			flagPanel = new JPanel();
			flagPanel.setBounds(12, 371, 876, 195);
			flagPanel.setLayout(null);
			flagPanel.add(getLblFlags());
			flagPanel.add(getChckbxIsViolent());
			flagPanel.add(getChckbxHasADrugProblem());
			flagPanel.add(getChckbxIsFugitive());
			flagPanel.add(getChckbxIsASexOffender());
			flagPanel.add(getChckbxIsMentallyIll());
			flagPanel.add(getChckbxIsSuicidal());
			flagPanel.add(getChckbxIsArmed());
			flagPanel.add(getChckBxIsPartOfIllegalOrganizations());
		}
		return flagPanel;
	}

	private JPanel getCommittedCrimesPanel()
	{
		if (committedCrimesPanel == null)
		{
			committedCrimesPanel = new JPanel();
			committedCrimesPanel.setBounds(14, 578, 876, 162);
			committedCrimesPanel.setLayout(null);
			committedCrimesPanel.add(getLblCommittedCrimes());
		}
		return committedCrimesPanel;
	}

	private JLabel getLblCommittedCrimes()
	{
		if (lblCommittedCrimes == null)
		{
			lblCommittedCrimes = new JLabel("COMMITTED CRIMES");
			lblCommittedCrimes.setBackground(Settings.SECONDARY_COLOR);
			lblCommittedCrimes.setOpaque(true);
			lblCommittedCrimes.setBounds(12, 12, 852, 30);
		}
		return lblCommittedCrimes;
	}

	private JLabel getLblFlags()
	{
		if (lblFlags == null)
		{
			lblFlags = new JLabel("FLAGS");
			lblFlags.setOpaque(true);
			lblFlags.setBackground(Settings.SECONDARY_COLOR);
			lblFlags.setBounds(12, 12, 852, 30);
		}
		return lblFlags;
	}

	private JCheckBox getChckbxIsViolent()
	{
		if (chckbxIsViolent == null)
		{
			chckbxIsViolent = new JCheckBox("is violent");
			chckbxIsViolent.setBounds(8, 46, 200, 20);
		}
		return chckbxIsViolent;
	}

	private JCheckBox getChckbxHasADrugProblem()
	{
		if (chckbxHasADrugProblem == null)
		{
			chckbxHasADrugProblem = new JCheckBox("has a drug problem");
			chckbxHasADrugProblem.setBounds(8, 70, 200, 20);
		}
		return chckbxHasADrugProblem;
	}

	private JCheckBox getChckbxIsFugitive()
	{
		if (chckbxIsFugitive == null)
		{
			chckbxIsFugitive = new JCheckBox("is fugitive");
			chckbxIsFugitive.setBounds(8, 94, 200, 20);
		}
		return chckbxIsFugitive;
	}

	private JCheckBox getChckbxIsASexOffender()
	{
		if (chckbxIsASexOffender == null)
		{
			chckbxIsASexOffender = new JCheckBox("is a sex offender");
			chckbxIsASexOffender.setBounds(8, 118, 200, 20);
		}
		return chckbxIsASexOffender;
	}

	private JCheckBox getChckbxIsMentallyIll()
	{
		if (chckbxIsMentallyIll == null)
		{
			chckbxIsMentallyIll = new JCheckBox("is mentally ill");
			chckbxIsMentallyIll.setBounds(8, 142, 200, 20);
		}
		return chckbxIsMentallyIll;
	}

	private JCheckBox getChckbxIsSuicidal()
	{
		if (chckbxIsSuicidal == null)
		{
			chckbxIsSuicidal = new JCheckBox("is suicidal");
			chckbxIsSuicidal.setBounds(8, 166, 200, 20);
		}
		return chckbxIsSuicidal;
	}

	private JCheckBox getChckbxIsArmed()
	{
		if (chckbxIsArmed == null)
		{
			chckbxIsArmed = new JCheckBox("is armed");
			chckbxIsArmed.setBounds(284, 46, 200, 20);
		}
		return chckbxIsArmed;
	}

	private JCheckBox getChckBxIsPartOfIllegalOrganizations()
	{
		if (chckBxIsPartOfIllegalOrganizations == null)
		{
			chckBxIsPartOfIllegalOrganizations = new JCheckBox(
					"is part of illegal organizations/groups/unions");
			chckBxIsPartOfIllegalOrganizations.setBounds(284, 70, 345, 20);
		}
		return chckBxIsPartOfIllegalOrganizations;
	}

	private JTextField getTxtIDCardNumber()
	{
		if (txtIDCardNumber == null)
		{
			txtIDCardNumber = new JTextField();
			txtIDCardNumber.setText("ID Card Number");
			txtIDCardNumber.setColumns(10);
			txtIDCardNumber.setBounds(704, 69, 149, 34);
		}
		return txtIDCardNumber;
	}

	private JLabel getLblIDCardNumber()
	{
		if (lblIDCardNumber == null)
		{
			lblIDCardNumber = new JLabel("ID Card Number");
			lblIDCardNumber.setLabelFor(getTxtIDCardNumber());
			lblIDCardNumber.setBounds(554, 68, 149, 37);
		}
		return lblIDCardNumber;
	}

	private JMenuBar getMenuBar_1()
	{
		if (menuBar == null)
		{
			menuBar = new JMenuBar();
			menuBar.setBounds(0, 0, 888, 21);
			menuBar.add(getMnFile());
		}
		return menuBar;
	}

	private JMenu getMnFile()
	{
		if (mnFile == null)
		{
			mnFile = new JMenu("File");
			mnFile.add(getMntmExportCurrentFile());
			mnFile.add(getMntmDeleteFile());
		}
		return mnFile;
	}

	private JMenuItem getMntmExportCurrentFile()
	{
		if (mntmExportCurrentFile == null)
		{
			mntmExportCurrentFile = new JMenuItem("Export current file");
		}
		return mntmExportCurrentFile;
	}

	private JMenuItem getMntmDeleteFile()
	{
		if (mntmDeleteFile == null)
		{
			mntmDeleteFile = new JMenuItem("Delete File");
		}
		return mntmDeleteFile;
	}

	private void doFillComboBoxGender()
	{
		modGender.removeAllElements();

		for (EnumGender gender : EnumGender.values())
		{
			modGender.addElement(gender);
		}
	}

	private JComboBox<EnumGender> getComboBoxGender()
	{
		if (comboBoxGender == null)
		{
			comboBoxGender = new JComboBox<EnumGender>();
			comboBoxGender.setBounds(322, 175, 149, 34);
			comboBoxGender.setModel(modGender);
			doFillComboBoxGender();
		}
		return comboBoxGender;
	}

	private JLabel getLblDescription() {
		if (lblDescription == null) {
			lblDescription = new JLabel("Description");
			lblDescription.setLabelFor(getTxtpnDescription());
			lblDescription.setBounds(12, 234, 149, 34);
		}
		return lblDescription;
	}
	private JTextPane getTxtpnDescription() {
		if (txtpnDescription == null) {
			txtpnDescription = new JTextPane();
			txtpnDescription.setBounds(173, 234, 695, 87);
		}
		return txtpnDescription;
	}
}
