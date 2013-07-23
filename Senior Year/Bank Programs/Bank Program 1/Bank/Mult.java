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
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
//this is for depositing for multiple people at once
public class Mult extends JPanel
                        implements ActionListener
{
	Person[] b;
	JButton dep=new JButton("DEPOSIT");
	JButton wit=new JButton("WITHDRAW");
	JButton can=new JButton("CANCEL");
	{
		dep.setMnemonic(KeyEvent.VK_D);
        dep.setActionCommand("deposit");
        dep.addActionListener(this);
        wit.setMnemonic(KeyEvent.VK_W);
        wit.setActionCommand("withdraw");
        wit.addActionListener(this);
        can.setMnemonic(KeyEvent.VK_C);
        can.setActionCommand("can");
        can.addActionListener(this);
	}
	BankScreen before;
	JFrame frame;	
    public Mult(JFrame f,BankScreen b2,Person[] b3)
    {
    	b=b3;
    	before=b2;
    	frame=f;    	
        add(dep);
        add(wit);
        add(can);
    }
    public void actionPerformed(ActionEvent e)
    {
        if ("deposit".equals(e.getActionCommand()))
        {
        	Deposit2 n=new Deposit2(frame,before,b);
            n.setOpaque(true);
        	frame.setContentPane(n);
        	frame.pack();
        	frame.setVisible(true);
        }else if ("withdraw".equals(e.getActionCommand()))
        {
        	Withdraw2 n=new Withdraw2(frame,before,b);
            n.setOpaque(true);
        	frame.setContentPane(n);
        	frame.pack();
        	frame.setVisible(true);
        }
        else if("can".equals(e.getActionCommand()))
        {
        	before.setOpaque(true);
        	frame.setContentPane(before);
        	frame.pack();
        	frame.setSize(1020,819);
        	frame.setLocation(1020/2-frame.getWidth()/2,819/2-frame.getHeight()/2);
        	frame.setTitle(before.b.getName());
        	frame.setVisible(true);
        }
    }
}