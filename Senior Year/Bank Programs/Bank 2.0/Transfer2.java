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
import javax.swing.table.AbstractTableModel;
public class Transfer2 extends JPanel
                        implements PropertyChangeListener,ActionListener
{
	Person b;
	Person[] b2;
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
	private Datemaker date;
	NumberFormat money=NumberFormat.getCurrencyInstance();
	JFrame frame;
	JTable table;
	boolean myset=true;
	private JLabel N1=new JLabel("Amount");	
	private JFormattedTextField Money;
	private JLabel N3=new JLabel("Date");
	private JLabel N2=new JLabel("Enter Description");
	private JTextField De;
	private JComboBox one;
	private JTable two;
    public Transfer2(JFrame f,BankScreen b2)
    {
    	super(new GridLayout(2,1));
    	before=b2;
    	frame=f;
    	ArrayList<Person> s=b2.b.list;
    	Object[] k=new Object[s.size()];
    	for(int q=0;q<k.length;q++)
    	{
    		k[q]=s.get(q).toString2();
    	}
    	one=new JComboBox(k);
    	JPanel toppane = new JPanel(new GridLayout(4,2));
    	

    	//JPanel fieldPane = new JPanel(new GridLayout(0,1));         
    	Money=new JFormattedTextField(money);
    	myset=true;
    	Money.setValue(new Double(1500));
    	myset=false;
        Money.setColumns(20);
    	Money.addPropertyChangeListener("value", this);
    	
    	De=new JTextField("This is where you will put the description");
        De.setColumns(20);
    	De.addPropertyChangeListener("value", this);
    	
        //Layout the text fields in a panel.
        toppane.add(N1);
        toppane.add(Money);
        toppane.add(N2);
        toppane.add(De);
        date= new Datemaker(); 
        toppane.add(N3);
        toppane.add(date);

		JPanel ComboPane = new JPanel(new GridLayout(0,4));  
		ComboPane.add(new JLabel("To:"));
		ComboPane.add(one);
		ComboPane.add(new JLabel("From:"));
		two=new JTable(b2.mt);
		ComboPane.add(new JScrollPane(two));
	//	fieldPane.add(ComboPane);
        //Put the panels in this panel, labels on left,
        //text fields on right.
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(ComboPane, BorderLayout.LINE_END);
        toppane.add(done);
        toppane.add(can);
        add(toppane, BorderLayout.CENTER);
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
        	b=getPerson(one);
        	String descript=De.getText();
        	System.out.println(descript);
        	double addition=(F*(double)two.getSelectedRows().length);
        	System.out.println("depositing... "+addition);
        	Tansaction p=new Tansaction(descript,addition,((Date)(date.getValue())).getTime());
        	b.add(p);
        	PersonScreen.em(b,before.b);
        	b2=getPerson(two);
        	p=new Tansaction(descript,-F,((Date)(date.getValue())).getTime());
        	for(int k=0;k<b2.length;k++)
        	{
        		b2[k].add(p);
        	}
        	PersonScreen.em(b,before.b);
        	for(int k=0;k<b2.length;k++)
        	{
        		PersonScreen.em(b2[k],before.b);
        	}
        	before.statechanged();
            before.setOpaque(true);
        	frame.setContentPane(before);
        	frame.pack();
        	frame.setSize(1020,819);
    		frame.setLocation(1020/2-frame.getWidth()/2,819/2-frame.getHeight()/2);
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
    public Person getPerson(JComboBox j)
    {
    	String g=""+j.getSelectedItem();
    	return PersonDecoder.getCompletePerson(g,before.b.name);
    }
    public Person[] getPerson(JTable j)
    {
    	int[] j2=j.getSelectedRows();
    	Person[] lis=new Person[j2.length];
		for(int k=0;k<lis.length;k++)
		{
    		Person f=before.b.list.get(j2[k]);
    		String name=f.getName();
    		f=PersonDecoder.getCompletePerson(name,before.b.name);
    		lis[k]=f;
		}
    	return lis;
    }
}