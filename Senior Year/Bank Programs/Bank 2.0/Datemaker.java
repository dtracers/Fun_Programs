/**
 * @(#)Datemaker.java
 *
 *
 * @author 
 * @version 1.00 2010/4/5
 */

import java.awt.*;
import java.applet.*;
import java.io.*;
import java.net.*;
import java.text.*;
import javax.swing.*;
import java.lang.*;
import java.util.*;
import sun.audio.*;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.event.*;
public class Datemaker extends JPanel implements ChangeListener
{
	JComboBox month;
	JSlider year;
	Date t;
	private JLabel Year=new JLabel("Select Year");
	private JLabel Month=new JLabel("Select Month");
	private JLabel Day=new JLabel("Select Days");
	private JTextField day;
	String Months[]=new String[]{"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    public Datemaker()
    {
    	super(new GridLayout(2,3));
    	t=new Date(System.currentTimeMillis());
    	month=new JComboBox(Months);
    	month.setSelectedIndex(t.getMonth());
    	//end of month
    	JPanel Years=new JPanel(new GridLayout(1,2));
    	year=new JSlider(t.getYear()-10+1900,t.getYear()+10+1900,t.getYear()+1900);
    	year.addChangeListener(this);
    	Year.setText("Current Year: "+year.getValue());
    	//end of year
    	day=new JTextField(""+t.getDay(),3);
    	//end of day
    	add(Year);
    	add(Month);
    	add(Day);
    	add(year);
    	add(month);    	
    	add(day);
    }
    public Date getValue()
    {
    	t=new Date(System.currentTimeMillis());
    	t.setYear(year.getValue()-1900);
    	t.setMonth(month.getSelectedIndex());
    	t.setDate(Integer.parseInt(day.getText()));//set the day not the whole date
   // 	System.out.println(t);
    	return t;
    }
    public Date getValueAt(int in,int out)//this is only here so i dont have to change tables method
    {
    	return getValue();
    }
    public void stateChanged(ChangeEvent e)
    {
    	Year.setText("Current Year: "+year.getValue());
    }
}