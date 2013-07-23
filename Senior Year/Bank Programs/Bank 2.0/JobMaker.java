import java.awt.*;
import java.applet.*;
import java.io.*;
import java.net.*;
import java.text.*;
import javax.swing.*;
import java.lang.*;
import java.util.*;
import sun.audio.*;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
public class JobMaker extends JPanel implements ActionListener
{
	JButton done=new JButton("DONE");
	{
		done.setMnemonic(KeyEvent.VK_D);
        done.setActionCommand("done");
        done.addActionListener(this);
	}
	JFrame frame;
	JPanel before;
	Date current;
	Datemaker date;
	Bank b;
    public JobMaker(JFrame f,JPanel b2,Bank b1)
    {    	
    	super(new GridLayout(2,1));
    	frame=f;
    	before=b2;
    	b=b1;
    	date = new Datemaker();
    	add(date);
    	add(done);
    }
    public void actionPerformed(ActionEvent e)
    {
        if ("done".equals(e.getActionCommand()))
        {
        	Date d=date.getValue();
        	for(int k=0;k<b.list.size();k++)
    		{
	    		Person f=b.list.get(k);
	    		String name=f.getName();
	    		f=PersonDecoder.getCompletePerson(name,b.name);
	    		f.ApplyJob(d);
	    		f.stateChanged(b);
    		}
            before.setOpaque(true);
        	frame.setContentPane(before);
        	frame.pack();
        	frame.setLocation(1020/2-frame.getWidth()/2,819/2-frame.getHeight()/2);
        	frame.setVisible(true);
        }
    }
}