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
import at.paulk.data.EnumRank;
import at.paulk.data.Officer;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JTextPane;

public class GUIOfficer extends JFrame
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

	// Non-GUI Components
	private Officer currentOfficer;
	private JLabel lblRank;
	private JComboBox<EnumRank> comboBoxRank;
	private DefaultComboBoxModel<EnumRank> modRank = new DefaultComboBoxModel<EnumRank>();
	private JLabel lblUsername;
	private JTextField txtUsername;
	private JPanel adminPanel;
	private JLabel lblHeaderAdmin;
	private JPanel changePasswordPanel;
	private JLabel lblHeaderChangePassword;
	private JLabel lblOldPassword;
	private JLabel lblNewPassword;
	private JPasswordField pwdOldPassword;
	private JPasswordField pwdNewPassword;
	private JButton btnChangePassword;
	private JTextPane txtpnImplementationOfA;
	private JLabel lblIDCardNumber;
	private JTextField txtIDCardNumber;

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */
	public GUIOfficer(Officer officer) throws Exception
	{
		// if(officer == null)
		// {
		// throw new
		// Exception("Permission denied: The officer must be specified!");
		// }

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 730);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getInfoPanel());
		contentPane.add(getChangePasswordPanel());
		contentPane.add(getAdminPanel());
		setTitle("Officers - " );
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
			infoPanel.setBounds(12, 34, 878, 286);
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
			infoPanel.add(getLblRank());
			infoPanel.add(getComboBoxRank());
			infoPanel.add(getLblUsername());
			infoPanel.add(getTxtUsername());
			infoPanel.add(getLblIDCardNumber());
			infoPanel.add(getTxtIDCardNumber());
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

	private void doFillComboBoxGender()
	{
		modGender.removeAllElements();

		for (EnumGender gender : EnumGender.values())
		{
			modGender.addElement(gender);
		}
	}

	private JLabel getLblRank()
	{
		if (lblRank == null)
		{
			lblRank = new JLabel("Rank");
			lblRank.setLabelFor(getComboBoxRank());
			lblRank.setBounds(554, 239, 149, 37);
		}
		return lblRank;
	}

	private JComboBox<EnumRank> getComboBoxRank()
	{
		if (comboBoxRank == null)
		{
			comboBoxRank = new JComboBox<>();
			comboBoxRank.setBounds(704, 240, 149, 34);
			comboBoxRank.setModel(modRank);
			doFillComboBoxRank();
		}
		return comboBoxRank;
	}

	private void doFillComboBoxRank()
	{
		modRank.removeAllElements();

		for (EnumRank rank : EnumRank.values())
		{
			modRank.addElement(rank);
		}
	}

	private JLabel getLblUsername()
	{
		if (lblUsername == null)
		{
			lblUsername = new JLabel("Username");
			lblUsername.setLabelFor(getTxtUsername());
			lblUsername.setBounds(173, 240, 149, 34);
		}
		return lblUsername;
	}

	private JTextField getTxtUsername()
	{
		if (txtUsername == null)
		{
			txtUsername = new JTextField();
			txtUsername.setEditable(false);
			txtUsername.setBounds(322, 240, 149, 36);
			txtUsername.setText("Username");
			txtUsername.setColumns(10);
		}
		return txtUsername;
	}

	private JPanel getAdminPanel()
	{
		if (adminPanel == null)
		{
			adminPanel = new JPanel();
			adminPanel.setBounds(12, 529, 878, 179);
			adminPanel.setLayout(null);
			adminPanel.add(getLblHeaderAdmin());
			adminPanel.add(getTxtpnImplementationOfA());
		}
		return adminPanel;
	}

	private JLabel getLblHeaderAdmin()
	{
		if (lblHeaderAdmin == null)
		{
			lblHeaderAdmin = new JLabel("ADMIN-PANEL");
			lblHeaderAdmin.setOpaque(true);
			lblHeaderAdmin.setBackground(Settings.SECONDARY_COLOR);
			lblHeaderAdmin.setBounds(12, 12, 854, 34);
		}
		return lblHeaderAdmin;
	}

	private JPanel getChangePasswordPanel()
	{
		if (changePasswordPanel == null)
		{
			changePasswordPanel = new JPanel();
			changePasswordPanel.setBounds(14, 338, 876, 179);
			changePasswordPanel.setLayout(null);
			changePasswordPanel.add(getLblHeaderChangePassword());
			changePasswordPanel.add(getLblOldPassword());
			changePasswordPanel.add(getLblNewPassword());
			changePasswordPanel.add(getPwdOldPassword());
			changePasswordPanel.add(getPwdNewPassword());
			changePasswordPanel.add(getBtnChangePassword());
		}
		return changePasswordPanel;
	}

	private JLabel getLblHeaderChangePassword()
	{
		if (lblHeaderChangePassword == null)
		{
			lblHeaderChangePassword = new JLabel("CHANGE PASSWORD");
			lblHeaderChangePassword.setOpaque(true);
			lblHeaderChangePassword.setBackground(Settings.SECONDARY_COLOR);
			lblHeaderChangePassword.setBounds(12, 12, 854, 34);
		}
		return lblHeaderChangePassword;
	}

	private JLabel getLblOldPassword()
	{
		if (lblOldPassword == null)
		{
			lblOldPassword = new JLabel("Old password:");
			lblOldPassword.setLabelFor(getPwdOldPassword());
			lblOldPassword.setBounds(12, 58, 200, 34);
		}
		return lblOldPassword;
	}

	private JLabel getLblNewPassword()
	{
		if (lblNewPassword == null)
		{
			lblNewPassword = new JLabel("New password:");
			lblNewPassword.setLabelFor(getPwdNewPassword());
			lblNewPassword.setBounds(12, 104, 200, 34);
		}
		return lblNewPassword;
	}

	private JPasswordField getPwdOldPassword()
	{
		if (pwdOldPassword == null)
		{
			pwdOldPassword = new JPasswordField();
			pwdOldPassword.setBounds(213, 58, 200, 34);
		}
		return pwdOldPassword;
	}

	private JPasswordField getPwdNewPassword()
	{
		if (pwdNewPassword == null)
		{
			pwdNewPassword = new JPasswordField();
			pwdNewPassword.setBounds(213, 104, 200, 34);
		}
		return pwdNewPassword;
	}

	private JButton getBtnChangePassword()
	{
		if (btnChangePassword == null)
		{
			btnChangePassword = new JButton("Change password");
			btnChangePassword.setBounds(466, 58, 200, 34);
		}
		return btnChangePassword;
	}
	private JTextPane getTxtpnImplementationOfA() {
		if (txtpnImplementationOfA == null) {
			txtpnImplementationOfA = new JTextPane();
			txtpnImplementationOfA.setText("Implementation of a JTable, an \"Edit\" button and a \"Delete\" button");
			txtpnImplementationOfA.setBounds(12, 52, 856, 117);
		}
		return txtpnImplementationOfA;
	}
	private JLabel getLblIDCardNumber() {
		if (lblIDCardNumber == null) {
			lblIDCardNumber = new JLabel("ID Card Number");
			lblIDCardNumber.setBounds(554, 68, 149, 37);
		}
		return lblIDCardNumber;
	}
	private JTextField getTxtIDCardNumber() {
		if (txtIDCardNumber == null) {
			txtIDCardNumber = new JTextField();
			txtIDCardNumber.setText("ID Card Number");
			txtIDCardNumber.setColumns(10);
			txtIDCardNumber.setBounds(704, 69, 149, 34);
		}
		return txtIDCardNumber;
	}
}
