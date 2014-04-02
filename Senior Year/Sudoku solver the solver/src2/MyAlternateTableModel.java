package main.tables;

import java.awt.Color;

import javax.swing.JTable;

import main.tables.MyTableModel;
import Variables.StaticV;

public class MyAlternateTableModel extends MyTableModel
{
    private String[] columnNames = new String[StaticV.gridSize];
    private JTable[][] data=new JTable[StaticV.squareNumber][StaticV.squareNumber];
    public MyAlternateTableModel()
    {
    	int counter=0;
    	for(int k=0;k<StaticV.squareNumber;k++)
    	{
    		for(int q=0;q<StaticV.squareNumber;q++)
    		{
    			data[k][q]=new JTable(new MyTableModel());
    			if(counter%2==0)
    			{
    				data[k][q].setGridColor(Color.black);
    				data[k][q].setForeground(Color.black);
    			}
    		}
    	}
    }
    public int getColumnCount()
    {
        return data.length*StaticV.squareSize;
    }

    public int getRowCount()
    {
        return data.length*StaticV.squareSize;
    }

    public String getColumnName(int col)
    {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col)
    {
        return data[row/StaticV.squareSize][col/StaticV.squareSize].getModel().getValueAt(row%StaticV.squareSize, col%StaticV.squareSize);
    }

    /*
     * JTable uses this method to determine the default renderer/
     * editor for each cell.  If we didn't implement this method,
     * then the last column would contain text ("true"/"false"),
     * rather than a check box.
     */
    public Class getColumnClass(int c)
    {
        return data[0][StaticV.squareNumber/StaticV.squareSize].getModel().getColumnClass(c%StaticV.squareSize);
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    public boolean isCellEditable(int row, int col)
    {
       return true;
    }

    /*
     * Don't need to implement this method unless your table's
     * data can change.
     */
    public void setValueAt(Object value, int row, int col)
    {
    	data[row/StaticV.squareSize][col/StaticV.squareSize].getModel().setValueAt(value,row%StaticV.squareSize, col%StaticV.squareSize);
        fireTableCellUpdated(row, col);
    }
}