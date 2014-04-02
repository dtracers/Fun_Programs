package main.display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.tables.*;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.text.TableView.TableRow;

import Variables.StaticV;

import Items.Box;
import Items.Grid;

public class Displayer implements TableModelListener
{
	Grid g;
	JPanel p;
	JTable grid;
	JScrollPane save;
	public static boolean autocheck=true;
	public Displayer(Grid g,JPanel p)
	{
		this.p=p;
		this.g=g;
		MyTableModel temp=new MyTableModel();
		grid=new JTable(temp);
		grid.setDefaultRenderer(Object.class,new BoxRenderer());
		grid.setDefaultEditor(Object.class,new BoxEditor());
		addBoxesToGrid();
		grid.setFillsViewportHeight(true);
		grid.setGridColor(Color.black);
		setSize();
		temp.addTableModelListener(this);
		JScrollPane scrollPane = new JScrollPane(grid);
		save=scrollPane;
		p.add(scrollPane);
	}
	public void resizeTable(Grid g)
	{
		this.g=g;
		p.remove(save);
		tablestuff();
	}
	public void tablestuff()
	{
		MyTableModel temp=new MyTableModel();
		grid=new JTable(temp);
		grid.setDefaultRenderer(Object.class,new BoxRenderer());
		grid.setDefaultEditor(Object.class,new BoxEditor());
		addBoxesToGrid();
		grid.setFillsViewportHeight(true);
		grid.setGridColor(Color.black);
		setSize(StaticV.gridSize);
		temp.addTableModelListener(this);
		JScrollPane scrollPane = new JScrollPane(grid);
		setSize(scrollPane,grid);
		save=scrollPane;
		p.add(scrollPane);
	}
	public void setSize(JScrollPane sp,JTable jt)
	{
		int smallsize=StaticV.squareSize*15;
		int bigsize=smallsize*StaticV.gridSize;
		sp.setPreferredSize(new Dimension(bigsize,700));
		jt.setPreferredSize(new Dimension(bigsize,bigsize));
	}
	public void addBoxesToGrid()
	{
		TableModel s=grid.getModel();
		Box[][] grid2=g.getBoxesAsMatrix();
		for(int k=0;k<StaticV.gridSize;k++)
		{
			for(int q=0;q<StaticV.gridSize;q++)
			{
				s.setValueAt(grid2[k][q],k,q);
			}
		}
	}
	public void setSize()
	{
		TableColumn column;
		for(int k=0;k<StaticV.gridSize;k++)
		{
			column=grid.getColumnModel().getColumn(k);
			column.setPreferredWidth(50);
		}
		grid.setRowHeight(50);
	}
	public void setSize(JTable grid)
	{
		TableColumn column;
		for(int k=0;k<grid.getColumnCount();k++)
		{
			column=grid.getColumnModel().getColumn(k);
			column.setPreferredWidth(50);
		}
		grid.setRowHeight(50);
//		grid.setRowSelectionAllowed(true);
//		grid.setColumnSelectionAllowed(true);
	}
	public void setSize(int num)
	{
		TableColumn column;
		for(int k=0;k<StaticV.gridSize;k++)
		{
			column=grid.getColumnModel().getColumn(k);
			column.setPreferredWidth(6*num);
		}
		grid.setRowHeight(num*6);
//		grid.setRowSelectionAllowed(true);
//		grid.setColumnSelectionAllowed(true);
	}
	@Override
	public void tableChanged(TableModelEvent e)
	{
		if(autocheck)
		g.checkAll(g);
		p.repaint();
	}
	public void MoveSelectionTo(int row,int col)
	{
		grid.changeSelection(row, col, false, false);
	}
	public void setSelectionEnabled(boolean t)
	{
		grid.setCellSelectionEnabled(t);
	}
	public void setCrossSelection(boolean t)
	{
		grid.setRowSelectionAllowed(t);
		grid.setColumnSelectionAllowed(t);
	}
	public void removeSelf(JPanel p)
	{
		p.remove(save);
	}
	public Displayer()
	{
	
	}
	public void repaint()
	{
		
	}
}