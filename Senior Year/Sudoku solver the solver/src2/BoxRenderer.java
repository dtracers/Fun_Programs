package main.tables;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import Variables.StaticV;

import Items.Box;

public class BoxRenderer implements TableCellRenderer
{
	float Superpointsize=26;
	float Normalpointsize=20;
	public static boolean Noteson=true;
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column)
	{
		Box j=(Box)value;
		if(!j.getPen().equals("0"))
		{
			JTextField jtemp=new JTextField(j.getPen());
			if(j.isSuperPen())
			{
				jtemp.setFont(jtemp.getFont().deriveFont(Superpointsize));
				jtemp.setFont(jtemp.getFont().deriveFont(2));
			}else
			{
				jtemp.setFont(jtemp.getFont().deriveFont(Superpointsize));
			}
			return jtemp;
		}
		else if(Noteson)
		{
			JTable temp=new JTable(StaticV.squareSize,StaticV.squareSize);
			TableModel s=temp.getModel();
			int counterup=1;
			for(int k=0;k<StaticV.squareSize;k++)
			{
				for(int q=0;q<StaticV.squareSize;q++)
				{
					if(j.getNotes().contains(""+counterup))
					{
						s.setValueAt(""+counterup, k, q);
					}else
					{
						s.setValueAt(" ", k, q);
					}
					counterup++;
				}
			}
			temp.setGridColor(Color.black);
			return temp;
		}else
		{
			JTextField jtemp=new JTextField("0");
			jtemp.setFont(jtemp.getFont().deriveFont(Normalpointsize));
			return jtemp;
		}
	}
}
