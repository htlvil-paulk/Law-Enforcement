package at.paulk.data;

import javax.swing.table.AbstractTableModel;

public class TableModelOfficers extends AbstractTableModel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] colNames = { "first name", "last name", "gender", "date of birth", "Officer ID", "Username", "Rank" };

	@Override
	public int getRowCount()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getColumnCount()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
