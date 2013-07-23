/**
 * NewBank.java  1/10/2010
 *
 * @author - Jane Doe
 * @author - Period n
 * @author - Id nnnnnnn
 *
 * @author - I received help from ...
 *
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
public class Tax extends Withdraw
                        implements PropertyChangeListener,ActionListener
{
	Person b;
	JButton done=new JButton("DONE");
	JButton can=new JButton("CANCEL");
	{
		done.setMnemonic(KeyEvent.VK_D);
        done.setActionCommand("done");
        done.addActionListener(this);
        can.setMnemonic(KeyEvent.VK_D);
        can.setActionCommand("can");
        can.addActionListener(this);
	}
	PersonScreen before;
	NumberFormat money=NumberFormat.getCurrencyInstance();
	JFrame frame;
	boolean myset=true;
	private JLabel N1=new JLabel("Amount (Postive only)");
	private JFormattedTextField Money;
	private JLabel N2=new JLabel("Enter Description");
	private JTextField De;
	private JLabel N3=new JLabel("Date");
	private JTable date;
	
    public Tax(JFrame f,PersonScreen b2,Person b3)
    {
    	b=b3;
    	before=b2;
    	frame=f;
    	JPanel labelPane = new JPanel(new GridLayout(0,1));
    	labelPane.add(N1);
    	labelPane.add(N2);
    	labelPane.add(N3);

    	JPanel fieldPane = new JPanel(new GridLayout(0,1));        

    	Money=new JFormattedTextField(money);
    	myset=true;
    	Money.setValue(new Double(1000));
    	myset=false;
        Money.setColumns(20);
    	Money.addPropertyChangeListener("value", this);
    	
    	De=new JTextField("This is where you will put the description");
        De.setColumns(20);
       // De.setValue("This is where you will put the description");
    	De.addPropertyChangeListener("value", this);
    	
    	JPanel Tabel = new JPanel(new GridLayout(0,1));  
    	date=new JTable(new Depwit());
    	myset=true;
    	date.setValueAt(new Date(System.currentTimeMillis()),0,0);
    	myset=false;
    	Tabel.add(date);

        //Layout the text fields in a panel.
        fieldPane.add(Money);
        fieldPane.add(De);
        fieldPane.add(Tabel);        

        //Put the panels in this panel, labels on left,
        //text fields on right.
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(labelPane, BorderLayout.CENTER);
        add(fieldPane, BorderLayout.LINE_END);
        add(done);
        add(can);
    }
    public void propertyChange(PropertyChangeEvent e)
    {
    	if(myset)
    	{
    		myset=false;
    		return;
    	}
        Object source = e.getSource();
    }
    public void actionPerformed(ActionEvent e)
    {
        if ("done".equals(e.getActionCommand()))
        {
        	double F=0;
        	Object c=Money.getValue();
        	if(c instanceof Integer)
        	{
        		F=-(Integer)c;
        	}else if(c instanceof Long)
        	{
        		F=-(Long)c;
        	}else if(c instanceof Double)
        	{
        		F=-(Double)c;
        	}
        	else if(c instanceof Float)
        	{
        		F=-(Float)c;
        	}
        	String descript=De.getText();
        	System.out.println(descript);
        	Tansaction p=new Tansaction(descript,F,((Date)(date.getValueAt(0,0))).getTime());
        	b.add(p);
        	before.statechanged();
            before.setOpaque(true);
        	frame.setContentPane(before);
        	frame.pack();
        	frame.setVisible(true);
        }
        else if("can".equals(e.getActionCommand()))
        {
        	before.setOpaque(true);
        	frame.setContentPane(before);
        	frame.pack();
        	frame.setVisible(true);
        }
    }
}