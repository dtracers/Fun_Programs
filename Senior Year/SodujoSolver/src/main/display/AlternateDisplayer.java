package main.display;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import main.tables.BoxEditor;
import main.tables.BoxRenderer;
import main.tables.MyAlternateTableModel;


import Items.Box;
import Items.Grid;

public class AlternateDisplayer extends Displayer
{
	JTable gOut;
	public AlternateDisplayer(Grid g,JPanel p)
	{
		this.p=p;
		this.g=g;
		tablestuff();
	}
	public void resizeTable(Grid g)
	{
		this.g=g;
		p.remove(save);
		tablestuff();
	}
	public void tablestuff()
	{
		MyAlternateTableModel temp=new MyAlternateTableModel();
		grid=new JTable(temp);
		grid.setDefaultRenderer(Box.class,new BoxRenderer());
		grid.setDefaultEditor(Box.class,new BoxEditor());
		addBoxesToGrid();
		grid.setFillsViewportHeight(true);
		//grid.setGridColor(Color.black);
		grid.setIntercellSpacing(new Dimension(6,6));
		setSize();
		temp.addTableModelListener(this);
		JScrollPane scrollPane = new JScrollPane(grid);
		save=scrollPane;
		p.add(scrollPane);
	}
	public AlternateDisplayer()
	{
		
	}
}