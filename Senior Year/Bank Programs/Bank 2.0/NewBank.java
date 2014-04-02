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
//TODO add back button
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
public class NewBank extends JPanel
                        implements PropertyChangeListener,ActionListener
{
	JPane frame;
	boolean myset=true;
	private JLabel Period=new JLabel("Enter Your Period");
	JButton back=new JButton("BACK");
	{
		back.setMnemonic(KeyEvent.VK_D);
        back.setActionCommand("back");
        back.addActionListener(this);
	}
	private JFormattedTextField amountField;
    public NewBank(JPane f)
    {
    	frame=f;
    	amountField=new JFormattedTextField();
    	myset=true;
    	amountField.setValue(new Integer(0));
    	myset=false;
        amountField.setColumns(10);
    	amountField.addPropertyChangeListener("value", this);
    	JPanel labelPane = new JPanel(new GridLayout(0,1));
        labelPane.add(Period);

        //Layout the text fields in a panel.
        JPanel fieldPane = new JPanel(new GridLayout(0,1));
        fieldPane.add(amountField);

        //Put the panels in this panel, labels on left,
        //text fields on right.
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(labelPane, BorderLayout.CENTER);
        add(fieldPane, BorderLayout.LINE_END);
        add(back);
    }
    public void propertyChange(PropertyChangeEvent e)
    {
    	if(myset)
    	{
    		myset=false;
    		return;
    	}
        Object source = e.getSource();
        if (source == amountField)
        {
        	int t=0;
        	int period=((Integer)amountField.getValue());
			print f=new print();
			try
			{
				StaticDirectory.Directory=""+period;
				f.setfile(period+".bank");
				if(f.ex())
				{
					t=JOptionPane.showConfirmDialog(this,"Are you Sure you want to OVERWRITE the Preivous File");
				}
			}catch(NullPointerException e2)
			{
				t=0;
			}
			if(t==0&&period!=0)
			{
				f.setfilewrite(period+".bank");
	        	frame.setVisible(false);
	        	frame = new JPane("Bank");
	        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            BankScreen l=new BankScreen(new Bank(period+".bank"),frame);
	            l.setOpaque(true); //content panes must be opaque
	            frame.setExit((combiner)l);
	        	frame.setContentPane(l);
	        	frame.pack();
	        	frame.setSize(1020,819);
	        	frame.setLocation(1020/2-frame.getWidth()/2,819/2-frame.getHeight()/2);
	        	frame.setVisible(true);
			}else
			{
				myset=true;
				amountField.setValue(new Integer(0));
			}
        }
    }
    public void actionPerformed(ActionEvent e)
    {
        if ("back".equals(e.getActionCommand()))
        {
        	frame.setVisible(false);
        	MainPanel.main(null);
        }
    }
}