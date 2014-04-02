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
//this is for depositing for multiple people at once
public class Deposit2 extends JPanel
                        implements PropertyChangeListener,ActionListener
{
	Person[] b;
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
	private JLabel N1=new JLabel("Amount");
	private JFormattedTextField Money;
	private JLabel N3=new JLabel("Date");
	private Datemaker date;
	private JLabel N2=new JLabel("Enter Description");
	private JTextField De;
	
    public Deposit2(JFrame f,BankScreen b2,Person[] b3)
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
    	
    	date = new Datemaker(); 

        //Layout the text fields in a panel.
        fieldPane.add(Money);
        fieldPane.add(De);
        fieldPane.add(date);        

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
        	String descript=De.getText();
        	System.out.println(descript);
        	Tansaction p=new Tansaction(descript,F,((Date)(date.getValueAt(0,0))).getTime());
        	for(int k=0;k<b.length;k++)
        	{
        		b[k].add(p);
        	}
        	em(true);
        	before.statechanged();
            before.setOpaque(true);
        	frame.setContentPane(before);
        	frame.pack();
        	frame.setSize(1020,819);
        	frame.setLocation(1020/2-frame.getWidth()/2,819/2-frame.getHeight()/2);
        	frame.setTitle(before.b.getName());
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
    public void em(boolean t)
    {
    	for(int k=0;k<b.length;k++)
    	{
    		Person smart=b[k];
//	    	done=true;
	    	smart.getTotal2();
	    	ArrayList<Tansaction>list=smart.list;
	    //	System.out.println(list);
	    	print p=new print();
	    	p.setfilewrite(smart.getName()+before.b.name);
	    	p.println(""+smart.getInitial());
	    	for(int q=0;q<list.size();q++)
	    	{
	    		System.out.println(list.get(q));
	    		p.println(""+list.get(q));
	    	}
    	}
    }
}