package main.display;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import main.display.AlternateDisplayer;
import main.tables.BoxEditor;
import main.tables.BoxRenderer;

import Variables.StaticV;

import Items.Box;
import Items.Grid;

public class AlternateDisplayer2 extends AlternateDisplayer
{
	JPanel panels;
	JPanel scrollPanel;
	JPanel insidepanels[]=new JPanel[StaticV.gridSize];
	JTable[] tables=new JTable[StaticV.gridSize];
	public AlternateDisplayer2(Grid g, JPanel p)
	{
		super();
		this.p=p;
		this.g=g;
		tablestuff();
	}
	public void resizeTable(Grid g)
	{
		this.g=g;
		p.remove(save);
		insidepanels=new JPanel[StaticV.gridSize];
		tables=new JTable[StaticV.gridSize];
		tablestuff();
		save.setPreferredSize(p.getPreferredSize());
	//	scrollPanel.setPreferredSize(p.getPreferredSize());
	}
	public void repaint()
	{
		for(int k=0;k<tables.length;k++)
		{
			tables[k].repaint();
		}
	}
	public void tablestuff()
	{
		GridLayout rider;
		panels=new JPanel(rider=new GridLayout(StaticV.squareNumber,StaticV.squareNumber));
		for(int k=0;k<tables.length;k++)
		{
			MyLinkedTableModel temp=new MyLinkedTableModel();
			tables[k]=new JTable(temp);
			tables[k].setDefaultRenderer(Box.class,new BoxRenderer());
			tables[k].setDefaultEditor(Box.class,new BoxEditor());
			tables[k].setFillsViewportHeight(true);
			temp.addTableModelListener(this);
			insidepanels[k]=new JPanel();
			setSize(tables[k]);
			insidepanels[k].add(tables[k]);
			panels.add(insidepanels[k]);
		}
		addBoxesToGrid();
		JScrollPane scrollPane = new JScrollPane(panels);
		save=scrollPane;
		p.add(scrollPane);
	}
	public void addBoxesToGrid()
	{
		int rowc=0;
		int colc=0;
		Box[][] grid2=g.getBoxesAsMatrix();
		for(int te=0;te<tables.length;te++)
		{
			TableModel s=tables[te].getModel();
			for(int k=0;k<StaticV.squareSize;k++)
			{
				for(int q=0;q<StaticV.squareSize;q++)
				{
					s.setValueAt(grid2[k+rowc*StaticV.squareNumber][q+colc*StaticV.squareNumber],k,q);
				}
			}
			colc++;
			if(colc>=StaticV.squareNumber)
			{
				rowc++;
				colc=0;
			}
		}
	}
}
class MyLinkedTableModel extends AbstractTableModel
{
	private String[] columnNames = new String[StaticV.squareSize];
    private Object[][] data=new Box[StaticV.squareSize][StaticV.squareSize];

    public int getColumnCount()
    {
        return data.length;
    }

    public int getRowCount()
    {
        return data.length;
    }

    public String getColumnName(int col)
    {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col)
    {
        return data[row][col];
    }

    /*
     * JTable uses this method to determine the default renderer/
     * editor for each cell.  If we didn't implement this method,
     * then the last column would contain text ("true"/"false"),
     * rather than a check box.
     */
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
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
        data[row][col] =value;
        fireTableCellUpdated(row, col);
    }
}