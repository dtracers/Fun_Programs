package main.tables;

import javax.swing.table.AbstractTableModel;

import Items.Box;
import Variables.StaticV;

public class NumberChooserTableModel extends AbstractTableModel
{
    private String[][] data=new String[StaticV.squareSize][StaticV.squareSize];
    public int getColumnCount()
    {
        return data.length;
    }

    public int getRowCount()
    {
        return data.length;
    }
    /*
    public String getColumnName(int col)
    {
        return columnNames[col];
    }
*/
    public Object getValueAt(int row, int col)
    {
        return data[row][col];
    }

    /*
     * JTable uses this method to determine the default renderer/
     * editor for each cell.  If we didn't implement this method,
     * then the last column would contain text ("true"/"false"),
     * rather than a check box.
     *
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
    */

    /*
     * Don't need to implement this method unless your table's
     * editable.
     *
    public boolean isCellEditable(int row, int col)
    {
       return false;
    }

    /*
     * Don't need to implement this method unless your table's
     * data can change.
     */
    public void setValueAt(Object value, int row, int col)
    {
    	data[row][col]=(""+value);
        fireTableCellUpdated(row, col);
    }
    // */

}
