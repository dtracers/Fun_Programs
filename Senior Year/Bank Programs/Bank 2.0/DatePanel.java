/**
 * DatePanel.java  1/19/2010
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
public class DatePanel extends JPanel implements ActionListener
{
	JButton done=new JButton("DONE");
	{
		done.setMnemonic(KeyEvent.VK_D);
        done.setActionCommand("done");
        done.addActionListener(this);
	}
	JFrame frame;
	JPanel before;
	static Date current;
	Datemaker date;
    public DatePanel(JFrame f,JPanel b)
    {    	
    	super(new GridLayout(2,1));
    	frame=f;
    	before=b;
    	date = new Datemaker();
    	add(date);
    	add(done);
    }
    public void actionPerformed(ActionEvent e)
    {
        if ("done".equals(e.getActionCommand()))
        {
        	current=date.getValue();
            before.setOpaque(true);
        	frame.setContentPane(before);
        	frame.pack();
        	frame.setLocation(1020/2-frame.getWidth()/2,819/2-frame.getHeight()/2);
        	frame.setVisible(true);
        }
    }
}