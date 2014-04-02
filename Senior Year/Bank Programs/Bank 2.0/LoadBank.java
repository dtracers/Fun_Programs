/**
 * LoadBank.java  1/10/2010
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
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
public class LoadBank extends JPanel
                        implements PropertyChangeListener,ActionListener
{
	static String password="";
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
    public LoadBank(JPane f)
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
				if(!f.ex())
				{
					t=1;
					JOptionPane.showMessageDialog(this,"I'm sorry but this file does not exist\n*loser*\nPlease try again.");
				}
				print f2=new print();
				f2.setfile(period+".pass");
				boolean j=false;
				if(!f2.ex())
				{
					System.out.println("file here doesnt exist");
			//		f2.createFile(period+".pass");
					j=true;
				}else
				{
					String g=f2.readline();
					if(g==null||g.equals("")||g.indexOf("password:")==-1)
					{
						j=true;
						System.out.println("password doesnt exist");
					}else
					{
						System.out.println("password does exist");
						f2=new print();
						f2.setfile(period+".pass");
					}
				}
				if(j)
				{
					String pass=JOptionPane.showInputDialog(this,"Enter new password with numbers and letters only it is case sensitive");
					if(pass.equals(""))
						return;
					pass=encode(pass);
					f2=new print();
					f2.setfilewrite(period+".pass");
					f2.println("password:"+pass);
					JOptionPane.showMessageDialog(this,"reset");
					frame.setVisible(false);
    				MainPanel.main(null);
    				password="password:"+pass;
    				System.out.println("password written");
    				return;
				}else
				{
					String g=f2.readline();
					String pass=JOptionPane.showInputDialog(this,"Enter password with numbers and letters only");
					String[] s=g.split(":");
					if(pass.equals(decode(s[1])))
					{
						//do nothing it was correct
					}else
					{
						System.out.println(decode(s[1]));
						JOptionPane.showMessageDialog(this,"wrong password");
						frame.setVisible(false);
        				MainPanel.main(null);
        				return;
					}
				}
			}catch(NullPointerException e2)
			{
				t=1;
			}
			if(t==0&&period!=0)
			{
				Bank b=PersonDecoder.getBank(f,period+".bank");
	        	frame.setVisible(false);
	        	frame = new JPane("Bank");
	        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            BankScreen l=new BankScreen(b,frame);
	            l.setOpaque(true); //content panes must be opaque
	        	frame.setContentPane(l);
	        	frame.pack();
	        	frame.setSize(1020,819);
	        	frame.setLocation(1020/2-frame.getWidth()/2,819/2-frame.getHeight()/2);
	        	frame.setExit((combiner)l);
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
    public String encode(String g)
    {
    	String i="";
		for(int k=0;k<g.length();k++)
		{
			char c=g.charAt(k);
			int w=(int)c;
			w+=6;
			if(k<g.length()-1)
			i+=""+w+";";
			else
			i+=""+w;
		}
		return i;
    }
    public String decode(String g)
    {
		String[] g2=g.split(";");
		String i="";
		for(int k=0;k<g2.length;k++)
		{
			int w=Integer.parseInt(g2[k]);
			w-=6;
			char c=(char)w;
			i+=""+c;
		}
		return i;
    }
}