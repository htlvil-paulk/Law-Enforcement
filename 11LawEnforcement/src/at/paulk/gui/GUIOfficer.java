package at.paulk.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Blob;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;

import at.paulk.data.Database;
import at.paulk.data.EnumGender;
import at.paulk.data.EnumRank;
import at.paulk.data.Officer;
import at.paulk.data.TableModelOfficers;
import at.paulk.misc.NotAuthorizedException;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class GUIOfficer extends JFrame implements ActionListener
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
	private DefaultComboBoxModel<EnumGender> modGender = new DefaultComboBoxModel<>();
	private JLabel lblAddress;
	private JTextField txtAddress;

	private JLabel lblRank;
	private JComboBox<EnumRank> comboBoxRank;
	private DefaultComboBoxModel<EnumRank> modRank = new DefaultComboBoxModel<>();
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
	private JLabel lblIDCardNumber;
	private JFormattedTextField frmtdtxtfldIDCardNumber;
	private JMenuBar menuBar;
	private DefaultComboBoxModel<Officer> modOfficer = new DefaultComboBoxModel<>();
	private JButton btnDeleteOfficer;
	private JButton btnLoadOfficer;
	private JButton btnAddOfficer;
	private JButton btnSaveChanges;
	private JMenu mnOfficer;
	private JMenuItem mntmSaveAsXml;
	private JMenuItem mntmLoadLoggedinOfficer;
	private JButton btnSelectNewPicture;
	private JLabel lblPassword;
	private JPasswordField pwdPassword;

	// Non-GUI Components
	private Database db;
	private Officer currentOfficer;
	private Officer selectedOfficer;
	private JTable table;
	private JScrollPane scrollPane;

	private TableModelOfficers modOfficers = new TableModelOfficers();

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */
	public GUIOfficer(Officer officer) throws Exception
	{
		if (officer == null)
		{
			throw new Exception("Permission denied: The officer must be specified!");
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 840);
		setJMenuBar(getMenuBar_1());

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getInfoPanel());
		contentPane.add(getChangePasswordPanel());
		contentPane.add(getAdminPanel());
		setTitle("Officers - " + Settings.POLICE_DEPARTMENT_NAME);

		initializeNonGUIComponents(officer);

		setVisible(true);
	}

	private void initializeNonGUIComponents(Officer officer) throws Exception
	{
		db = Database.createInstance();
		currentOfficer = officer;
		doLoadOfficer(officer);
		doFillList();
		if (officer.getRank() != EnumRank.GENERAL)
		{
			adminPanel.setVisible(false);
		}
		doEnterMode(false);
	}

	private JPanel getInfoPanel() throws ParseException
	{
		if (infoPanel == null)
		{
			infoPanel = new JPanel();
			infoPanel.setBounds(12, 11, 862, 314);
			infoPanel.setLayout(null);
			infoPanel.add(getLblInformation());
			infoPanel.add(getLblPicture());
			infoPanel.add(getBtnSelectNewPicture());
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
			infoPanel.add(getBtnSaveChanges());
			infoPanel.add(getLblPassword());
			infoPanel.add(getPwdPassword());
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
			lblInformation.setBounds(12, 13, 841, 34);
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

	private JFormattedTextField getFrmtdtxtfldDateOfBirth() throws ParseException
	{
		if (frmtdtxtfldDateOfBirth == null)
		{
			String mask = "**.**.****";

			MaskFormatter mf = new MaskFormatter(mask);
			mf.setValidCharacters("1234567890.");

			frmtdtxtfldDateOfBirth = new JFormattedTextField(mf);
			frmtdtxtfldDateOfBirth.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.uuuu")));
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
			adminPanel.setBounds(12, 529, 862, 300);
			adminPanel.setLayout(null);
			adminPanel.add(getLblHeaderAdmin());
			adminPanel.add(getBtnDeleteOfficer());
			adminPanel.add(getBtnLoadOfficer());
			adminPanel.add(getBtnAddOfficer());
			adminPanel.add(getTable());
			adminPanel.add(getScrollPane());
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
			lblHeaderAdmin.setBounds(12, 12, 840, 34);
		}
		return lblHeaderAdmin;
	}

	private JPanel getChangePasswordPanel()
	{
		if (changePasswordPanel == null)
		{
			changePasswordPanel = new JPanel();
			changePasswordPanel.setBounds(14, 338, 860, 179);
			changePasswordPanel.setLayout(null);
			changePasswordPanel.add(getLblHeaderChangePassword());
			changePasswordPanel.add(getLblOldPassword());
			changePasswordPanel.add(getPwdOldPassword());
			changePasswordPanel.add(getLblNewPassword());
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
			lblHeaderChangePassword.setBounds(12, 12, 838, 34);
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
			btnChangePassword.addActionListener(this);
			btnChangePassword.setBounds(466, 58, 200, 34);
		}
		return btnChangePassword;
	}

	private JLabel getLblIDCardNumber()
	{
		if (lblIDCardNumber == null)
		{
			lblIDCardNumber = new JLabel("ID Card Number");
			lblIDCardNumber.setBounds(554, 68, 149, 37);
		}
		return lblIDCardNumber;
	}

	private JFormattedTextField getTxtIDCardNumber() throws ParseException
	{
		if (frmtdtxtfldIDCardNumber == null)
		{
			String mask = "********";

			MaskFormatter mf = new MaskFormatter(mask);
			mf.setValidCharacters("1234567890");

			frmtdtxtfldIDCardNumber = new JFormattedTextField(mf);
			frmtdtxtfldIDCardNumber.setText("01234567");
			frmtdtxtfldIDCardNumber.setColumns(10);
			frmtdtxtfldIDCardNumber.setBounds(704, 69, 149, 34);

		}
		return frmtdtxtfldIDCardNumber;
	}

	private JMenuBar getMenuBar_1()
	{
		if (menuBar == null)
		{
			menuBar = new JMenuBar();
			menuBar.add(getMnOfficer());

		}
		return menuBar;
	}

	private JButton getBtnDeleteOfficer()
	{
		if (btnDeleteOfficer == null)
		{
			btnDeleteOfficer = new JButton("Delete Officer");
			btnDeleteOfficer.setBounds(156, 208, 134, 25);
			btnDeleteOfficer.addActionListener(this);
		}
		return btnDeleteOfficer;
	}

	private JButton getBtnLoadOfficer()
	{
		if (btnLoadOfficer == null)
		{
			btnLoadOfficer = new JButton("Load Officer");
			btnLoadOfficer.setBounds(10, 208, 134, 25);
			btnLoadOfficer.addActionListener(this);
		}
		return btnLoadOfficer;
	}

	private JButton getBtnAddOfficer()
	{
		if (btnAddOfficer == null)
		{
			btnAddOfficer = new JButton("Add Officer");
			btnAddOfficer.setBounds(302, 208, 117, 25);
			btnAddOfficer.addActionListener(this);
		}
		return btnAddOfficer;
	}

	private JButton getBtnSaveChanges()
	{
		if (btnSaveChanges == null)
		{
			btnSaveChanges = new JButton("Save changes");
			btnSaveChanges.setBounds(554, 279, 149, 36);
			btnSaveChanges.setVisible(false);
			btnSaveChanges.addActionListener(this);
		}
		return btnSaveChanges;
	}

	private JMenu getMnOfficer()
	{
		if (mnOfficer == null)
		{
			mnOfficer = new JMenu("Officer");
			mnOfficer.add(getMntmSaveAsXml());
			mnOfficer.add(getMntmLoadLoggedinOfficer());
		}
		return mnOfficer;
	}

	private JMenuItem getMntmSaveAsXml()
	{
		if (mntmSaveAsXml == null)
		{
			mntmSaveAsXml = new JMenuItem("Save as XML");
			mntmSaveAsXml.addActionListener(this);
		}
		return mntmSaveAsXml;
	}

	private JMenuItem getMntmLoadLoggedinOfficer()
	{
		if (mntmLoadLoggedinOfficer == null)
		{
			mntmLoadLoggedinOfficer = new JMenuItem("Load logged-in officer");
			mntmLoadLoggedinOfficer.addActionListener(this);
		}
		return mntmLoadLoggedinOfficer;
	}

	private JButton getBtnSelectNewPicture()
	{
		if (btnSelectNewPicture == null)
		{
			btnSelectNewPicture = new JButton("Select new picture");
			btnSelectNewPicture.setBounds(12, 234, 149, 34);
			btnSelectNewPicture.addActionListener(this);
		}
		return btnSelectNewPicture;
	}

	private JLabel getLblPassword()
	{
		if (lblPassword == null)
		{
			lblPassword = new JLabel("Password:");
			lblPassword.setBounds(173, 277, 143, 34);
			lblPassword.setVisible(false);
		}
		return lblPassword;
	}

	private JPasswordField getPwdPassword()
	{
		if (pwdPassword == null)
		{
			pwdPassword = new JPasswordField();
			pwdPassword.setBounds(322, 277, 149, 34);
			pwdPassword.setVisible(false);
		}
		return pwdPassword;
	}
	
	private JTable getTable()
	{
		if (table == null)
		{
			table = new JTable();
			table.setBounds(12, 57, 840, 140);
			table.setModel(modOfficers);
		}
		return table;
	}

	private JScrollPane getScrollPane()
	{
		if (scrollPane == null)
		{
			scrollPane = new JScrollPane();
			scrollPane.setBounds(12, 57, 840, 140);
			scrollPane.setViewportView(table);
		}
		return scrollPane;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			if (e.getSource() == btnLoadOfficer)
			{
				doEnterMode(false);
//				doLoadOfficer((Officer) modOfficers.);
			}
			else if (e.getSource() == btnAddOfficer)
			{
				doLoadOfficer(null);
				doEnterMode(true);
				btnSaveChanges.setVisible(true);
			}
			else if (e.getSource() == btnSaveChanges)
			{
				doEnterMode(false);
				db.addOfficer(doCreateOfficerFromInput(true));
				doFillList();
			}
			else if (e.getSource() == btnChangePassword)
			{
				db.changePassword(selectedOfficer, pwdOldPassword.getPassword(), pwdNewPassword.getPassword());
			}
			else if (e.getSource() == btnDeleteOfficer)
			{
				Officer toDelete = (Officer) modOfficer.getSelectedItem();
				if (toDelete == currentOfficer)
				{
					throw new NotAuthorizedException("You cannot delete that account in which you are logged in!");
				}
				else if (toDelete == null)
				{
					throw new Exception("Please select an entry!");
				}
				doDeleteOfficer(toDelete);
			}
			else if (e.getSource() == btnSelectNewPicture)
			{
				File f = doSelectPicture();
				// TODO Bild in lblPicture einfügen
			}
			else if (e.getSource() == btnChangePassword)
			{
				db.changePassword(currentOfficer, pwdOldPassword.getPassword(), pwdNewPassword.getPassword());
				System.out.println("PW changed");
			}
			else if (e.getSource() == mntmLoadLoggedinOfficer)
			{
				doEnterMode(false);
				doLoadOfficer(currentOfficer);
			}
		}
		catch (NotAuthorizedException notAuthEx)
		{
			JOptionPane.showMessageDialog(this, notAuthEx.getMessage(), "Error - " + Settings.APPLICATION_NAME,
					JOptionPane.ERROR_MESSAGE);
		}
		catch (Exception e2)
		{
			JOptionPane.showMessageDialog(this, e2.getMessage(), "Error - " + Settings.APPLICATION_NAME,
					JOptionPane.ERROR_MESSAGE);
			e2.printStackTrace();
		}

	}

	private void doLoadOfficer(Officer o)
	{
		if (o == null)
		{
			txtName.setText("");
			txtLastName.setText("");
			frmtdtxtfldDateOfBirth.setText("01.01.1970");
			txtBirthplace.setText("");
			frmtdtxtfldIDCardNumber.setText("");
			txtNationality.setText("");
			txtAddress.setText("");
			comboBoxGender.setSelectedItem(EnumGender.MALE);

			txtUsername.setText("");
			comboBoxRank.setSelectedItem(EnumRank.ASPIRANT);
		}
		else
		{
			txtName.setText(o.getFirstName());
			txtLastName.setText(o.getLastName());
			frmtdtxtfldDateOfBirth.setText(o.getDateOfBirthAsString());
			txtBirthplace.setText(o.getBirthplace());
			frmtdtxtfldIDCardNumber.setText("" + o.getIdCardNumber());
			txtNationality.setText(o.getNationality());
			txtAddress.setText(o.getAddress());
			comboBoxGender.setSelectedItem(o.getGender());

			txtUsername.setText(o.getUsername());
			comboBoxRank.setSelectedItem(o.getRank());
		}
	}

	private void doDeleteOfficer(Officer selectedItem) throws Exception
	{
		db.deleteOfficer(selectedItem);
		doFillList();
	}

	private void doFillList() throws Exception
	{
		modOfficer.removeAllElements();

		for (Officer o : db.selectOfficers())
		{
			modOfficer.addElement(o);
		}
	}

	private void doFillComboBoxGender()
	{
		modGender.removeAllElements();

		for (EnumGender gender : EnumGender.values())
		{
			modGender.addElement(gender);
		}
	}

	private void doFillComboBoxRank()
	{
		modRank.removeAllElements();

		for (EnumRank rank : EnumRank.values())
		{
			modRank.addElement(rank);
		}
	}

	private void doEnterMode(boolean isEditMode)
	{
		txtUsername.setEditable(isEditMode);
		frmtdtxtfldIDCardNumber.setEditable(isEditMode);
		txtNationality.setEditable(isEditMode);
		frmtdtxtfldDateOfBirth.setEditable(isEditMode);
		txtBirthplace.setEditable(isEditMode);
		txtAddress.setEditable(isEditMode);
		txtName.setEditable(isEditMode);
		txtLastName.setEditable(isEditMode);
		comboBoxRank.setEnabled(isEditMode);
		comboBoxGender.setEnabled(isEditMode);

		btnSaveChanges.setVisible(isEditMode);
		pwdPassword.setVisible(isEditMode);
		lblPassword.setVisible(isEditMode);
		btnSelectNewPicture.setVisible(isEditMode);
	}

	private Officer doCreateOfficerFromInput(boolean isNewOfficer) throws Exception
	{
		Officer o;

		int idCardNumber = Integer.parseInt(frmtdtxtfldIDCardNumber.getText());
		String nationality = txtNationality.getText();
		Blob picture = null;
		String firstName = txtName.getText();
		String lastName = txtLastName.getText();
		EnumGender gender = (EnumGender) comboBoxGender.getSelectedItem();
		String address = txtAddress.getText();
		String dateOfBirth = frmtdtxtfldDateOfBirth.getText();
		String birthplace = txtBirthplace.getText();
		String username = txtUsername.getText();
		char[] password = pwdPassword.getPassword();
		EnumRank rank = (EnumRank) comboBoxRank.getSelectedItem();

		o = new Officer(idCardNumber, nationality, picture, firstName, lastName, gender, address, dateOfBirth,
				birthplace, username, password, rank);

		return o;
	}

	private File doSelectPicture() throws Exception
	{
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png");
		chooser.setFileFilter(filter);
		chooser.showOpenDialog(this);
		System.out.println(chooser.getSelectedFile());
		return chooser.getSelectedFile();
	}
}