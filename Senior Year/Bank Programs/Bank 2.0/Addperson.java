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
import java.sql.*;
import sun.audio.*;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
//import javax.swing.JPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
public class Addperson extends JPanel
                        implements PropertyChangeListener,ActionListener
{
	Bank b;
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
	BankScreen before;
	NumberFormat money=NumberFormat.getCurrencyInstance();
	JFrame frame;
	boolean myset=true;
	private JLabel N1=new JLabel("Enter First Name");
	private JFormattedTextField FName;
	private JLabel N2=new JLabel("Enter Last Name");
	private JFormattedTextField LName;
	private JLabel M=new JLabel("Enter Starting Amount");
	private JFormattedTextField Money;
    public Addperson(JFrame f,BankScreen b2,Bank b3)
    {
    	b=b3;
    	before=b2;
    	frame=f;
    	JPanel labelPane = new JPanel(new GridLayout(0,1));
    	labelPane.add(N1);
    	labelPane.add(N2);
    	labelPane.add(M);

    	JPanel fieldPane = new JPanel(new GridLayout(0,1));

    	FName=new JFormattedTextField();
    	myset=true;
    	FName.setValue("Jon");
    	myset=false;
        FName.setColumns(20);
    	FName.addPropertyChangeListener("value", this);

        LName=new JFormattedTextField();
    	myset=true;
    	LName.setValue("Doe");
    	myset=false;
        LName.setColumns(20);
    	LName.addPropertyChangeListener("value", this);

    	Money=new JFormattedTextField(money);
    	myset=true;
    	Money.setValue(new Double(1000));
    	myset=false;
        Money.setColumns(20);
    	Money.addPropertyChangeListener("value", this);

        //Layout the text fields in a panel.
        fieldPane.add(FName);
        fieldPane.add(LName);
        fieldPane.add(Money);

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
        if(source == FName)
        {

        }
    }
    public void actionPerformed(ActionEvent e)
    {
        if ("done".equals(e.getActionCommand()))
        {
        	double F=0;
        	Object c=Money.getValue();
        	if(c instanceof Integer)
        	{
        		F=(Integer)c;
        	}else if(c instanceof Long)
        	{
        		F=(Long)c;
        	}else if(c instanceof Double)
        	{
        		F=(Double)c;
        	}
        	else if(c instanceof Float)
        	{
        		F=(Float)c;
        	}
        	String first=""+FName.getValue();
        	String last=""+LName.getValue();
        	Person p=new Person(first+" "+last,F);
        	b.add(p);
        	
        	int t=0;
			print f=new print();
			try
			{
				f.setfile(StaticDirectory.Directory+"\\"+p.getName()+b.name);
				if(f.ex())
				{
					t=JOptionPane.showConfirmDialog(this,"Are you Sure you want to OVERWRITE the Preivous File");
				}else
				{
					if(!new File(StaticDirectory.Directory).exists())
						new File(StaticDirectory.Directory).mkdirs();
					f.setfile(StaticDirectory.Directory+"\\"+p.getName()+b.name);
				}
			}catch(NullPointerException e2)
			{
				t=1;
			}
			if(t==0)
			{
				f.setfilewrite(StaticDirectory.Directory+"\\"+p.getName()+b.name);
				f.println(""+p.getInitial());
			}else
			{
				return;
			}
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