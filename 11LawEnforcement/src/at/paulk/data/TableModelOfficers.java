package at.paulk.data;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class TableModelOfficers extends AbstractTableModel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] columnNames = { "Rank" , "First name", "Last name", "Gender", "Date of birth", "ID Card Number", "Nationality", "Officer ID", "Username" };
	private Database db;
	private ArrayList<Officer> collOfficers;
	

	public TableModelOfficers() throws Exception
	{
		db = Database.createInstance();
		collOfficers = db.selectOfficers();
	}

	@Override
	public String getColumnName(int col)
	{
		return columnNames[col];
	}

	@Override
	public int getColumnCount()
	{
		return columnNames.length;
	}
	
	@Override
	public int getRowCount()
	{
		return collOfficers.size();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Class getColumnClass(int c)
	{
		return getValueAt(0, c).getClass();
	}

	@Override
	public boolean isCellEditable(int row, int col)
	{
		return col < 7;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		Object returnValue = null;
		
		try
		{
			Officer t = collOfficers.toArray(new Officer[0])[rowIndex];
			switch (columnIndex)
			{
			case 0:
				returnValue = t.getRank();
				break;
				
			case 1:
				returnValue = t.getFirstName();
				break;
				
			case 2:
				returnValue = t.getLastName();
				break;
			
			case 3:
				returnValue = t.getGender().toString();
				break;
				
			case 4:
				returnValue = t.getDateOfBirthAsString();
				break;
				
			case 5:
				returnValue = t.getIdCardNumber();
				break;
				
			case 6:
				returnValue = t.getNationality();
				break;
				
			case 7:
				returnValue = t.getOfficerId();
				break;
				
			case 8:
				returnValue = t.getUsername();
				break;
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
		return returnValue;
	}
	
	@Override
	public void setValueAt(Object val, int row, int col)
	{
		try
		{
			Officer o = collOfficers.toArray(new Officer[0])[row];

			switch (col)
			{
			case 0:
				EnumRank rank = EnumRank.valueOf(val.toString().toUpperCase());
				
				if (rank != null)
				{
					o.setRank(rank);					
				}
				else
				{
					throw new Exception("Rank unknown!");
				}
				break;
				
			case 1:
				o.setFirstName(val.toString());
				break;
				
			case 2:
				o.setLastName(val.toString());
				break;
			
			case 3:
				EnumGender gender = EnumGender.valueOf(val.toString().toUpperCase());
				
				if (gender != null)
				{
					o.setGender(gender);
				}
				else
				{
					throw new Exception("Apache helicopters aren't allowed yet!");
				}
				break;
				
			case 4:
				o.setDateOfBirth(val.toString());
				break;
				
			case 5:
				o.setIdCardNumber(Integer.parseInt(val.toString()));
				break;
				
			case 6:
				o.setNationality(val.toString());
				break;
			}
			
			db.updateOfficer(o);
			this.fireTableDataChanged();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
