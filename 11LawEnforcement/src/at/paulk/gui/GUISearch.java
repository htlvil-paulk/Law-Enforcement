package at.paulk.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import at.paulk.data.Database;
import at.paulk.data.Officer;
import at.paulk.data.Suspect;

import javax.swing.JList;
import javax.swing.JFormattedTextField;

public class GUISearch extends JFrame implements ActionListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel inputPanel;
	private JLabel lblSearchSuspect;
	private JLabel lblName;
	private JLabel lblLastName;
	private JLabel lblIdCardNumber;
	private JTextField txtName;
	private JTextField txtLastName;
	private JFormattedTextField txtIDCardNumber;
	private JButton btnSearchForSuspects;
	private JPanel resultsPanel;
	private JLabel lblHeaderResults;

	private Officer currentOfficer;
	private JList<Suspect> list;

	private Database db;
	private DefaultComboBoxModel<Suspect> modSuspects = new DefaultComboBoxModel<>();
	private JPanel actionPanel;
	private JLabel lblHeaderActions;
	private JButton btnSelectSuspect;

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */
	public GUISearch(Officer officer) throws Exception
	{
		if (officer == null)
		{
			throw new Exception("Permission denied: The officer must be specified!");
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getInputPanel());
		contentPane.add(getResultsPanel());
		contentPane.add(getActionPanel());
		setVisible(true);
		initializeNonGUIComponents(officer);
		
		this.setTitle("Suspect Search - " + Settings.APPLICATION_NAME);
	}

	private void initializeNonGUIComponents(Officer officer) throws SQLException
	{
		db = Database.createInstance();
		currentOfficer = officer;
	}

	private JPanel getInputPanel() throws ParseException
	{
		if (inputPanel == null)
		{
			inputPanel = new JPanel();
			inputPanel.setBounds(12, 12, 862, 165);
			inputPanel.setLayout(null);
			inputPanel.add(getLblSearchSuspect());
			inputPanel.add(getLblName());
			inputPanel.add(getLblLastName());
			inputPanel.add(getLblIdCardNumber());
			inputPanel.add(getTxtName());
			inputPanel.add(getTxtLastName());
			inputPanel.add(getTxtIDCardNumber());
			inputPanel.add(getBtnSearchForSuspects());
		}
		return inputPanel;
	}

	private JLabel getLblSearchSuspect()
	{
		if (lblSearchSuspect == null)
		{
			lblSearchSuspect = new JLabel("SEARCH SUSPECT");
			lblSearchSuspect.setBackground(new Color(0, 255, 255));
			lblSearchSuspect.setOpaque(true);
			lblSearchSuspect.setBounds(12, 12, 840, 30);
		}
		return lblSearchSuspect;
	}

	private JLabel getLblName()
	{
		if (lblName == null)
		{
			lblName = new JLabel("Name:");
			lblName.setLabelFor(getTxtName());
			lblName.setBounds(12, 53, 200, 30);
		}
		return lblName;
	}

	private JLabel getLblLastName()
	{
		if (lblLastName == null)
		{
			lblLastName = new JLabel("Last name:");
			lblLastName.setLabelFor(getTxtLastName());
			lblLastName.setBounds(12, 82, 200, 30);
		}
		return lblLastName;
	}

	private JLabel getLblIdCardNumber()
	{
		if (lblIdCardNumber == null)
		{
			lblIdCardNumber = new JLabel("ID card number:");
			lblIdCardNumber.setLabelFor(lblIdCardNumber);
			lblIdCardNumber.setBounds(12, 109, 200, 30);
		}
		return lblIdCardNumber;
	}

	private JTextField getTxtName()
	{
		if (txtName == null)
		{
			txtName = new JTextField();
			txtName.setText("Name");
			txtName.setBounds(215, 53, 200, 30);
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
			txtLastName.setBounds(215, 82, 200, 30);
			txtLastName.setColumns(10);
		}
		return txtLastName;
	}

	private JFormattedTextField getTxtIDCardNumber() throws ParseException
	{
		if (txtIDCardNumber == null)
		{
			String mask = "********";

			MaskFormatter mf = new MaskFormatter(mask);
			mf.setValidCharacters("1234567890");

			txtIDCardNumber = new JFormattedTextField(mf);
			txtIDCardNumber.setText("00000000");
			txtIDCardNumber.setColumns(10);
			txtIDCardNumber.setBounds(215, 109, 200, 30);
		}
		return txtIDCardNumber;
	}

	private JButton getBtnSearchForSuspects()
	{
		if (btnSearchForSuspects == null)
		{
			btnSearchForSuspects = new JButton("Search For Suspects");
			btnSearchForSuspects.setBounds(505, 76, 270, 43);
			btnSearchForSuspects.addActionListener(this);
		}
		return btnSearchForSuspects;
	}

	private JPanel getResultsPanel()
	{
		if (resultsPanel == null)
		{
			resultsPanel = new JPanel();
			resultsPanel.setBounds(12, 188, 862, 194);
			resultsPanel.setLayout(null);
			resultsPanel.add(getLblHeaderResults());
			resultsPanel.add(getList());
		}
		return resultsPanel;
	}

	private JLabel getLblHeaderResults()
	{
		if (lblHeaderResults == null)
		{
			lblHeaderResults = new JLabel("RESULTS");
			lblHeaderResults.setOpaque(true);
			lblHeaderResults.setBackground(Settings.SECONDARY_COLOR);
			lblHeaderResults.setBounds(12, 12, 840, 30);
		}
		return lblHeaderResults;
	}

	private JList<Suspect> getList()
	{
		if (list == null)
		{
			list = new JList<Suspect>();
			list.setBounds(12, 48, 840, 119);
			list.setModel(modSuspects);
		}
		return list;
	}
	

	private JPanel getActionPanel()
	{
		if (actionPanel == null)
		{
			actionPanel = new JPanel();
			actionPanel.setBounds(12, 393, 862, 107);
			actionPanel.setLayout(null);
			actionPanel.add(getLblHeaderActions());
			actionPanel.add(getBtnSelectSuspect());
		}
		return actionPanel;
	}

	private JLabel getLblHeaderActions()
	{
		if (lblHeaderActions == null)
		{
			lblHeaderActions = new JLabel("ACTIONS");
			lblHeaderActions.setOpaque(true);
			lblHeaderActions.setBackground(Color.CYAN);
			lblHeaderActions.setBounds(10, 11, 840, 30);
		}
		return lblHeaderActions;
	}

	private JButton getBtnSelectSuspect()
	{
		if (btnSelectSuspect == null)
		{
			btnSelectSuspect = new JButton("Select suspect");
			btnSelectSuspect.setBounds(10, 52, 160, 44);
			btnSelectSuspect.addActionListener(this);
		}
		return btnSelectSuspect;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			if (e.getSource() == btnSearchForSuspects)
			{
				doSearchSuspects();
			}
			else if (e.getSource() == btnSelectSuspect)
			{
				Suspect selectedSuspect = (Suspect)list.getSelectedValue();
				System.out.println(selectedSuspect);
				new GUIOffender(currentOfficer, selectedSuspect);
			}
		}
		catch (Exception e2)
		{
			e2.printStackTrace();
		}

	}

	private void doSearchSuspects() throws SQLException
	{
		String name = txtName.getText();
		String lastName = txtLastName.getText();
		String idAsString = txtIDCardNumber.getText();
		System.out.println(idAsString + "  " + idAsString.length());
		int idCardNumber = (idAsString.equals("        ")) ? 0 : Integer.parseInt(idAsString);

		ArrayList<Suspect> results = db.searchSuspects(name, lastName, idCardNumber);
		doFillList(results);
		lblHeaderResults.setText(results.size() + (results.size() == 1 ? " Result" : " Results"));
	}

	private void doFillList(ArrayList<Suspect> results)
	{
		modSuspects.removeAllElements();
		for (Suspect s : results)
		{
			modSuspects.addElement(s);
		}
	}
}
