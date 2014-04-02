/**
 * MainPanel.java  1/10/2010
 *
 * @author - Jane Doe
 * @author - Period n
 * @author - Id nnnnnnn
 *
 * @author - I received help from ...
 *
 */
import java.awt.*;
import java.io.*;
import java.net.*;
import java.text.*;
import javax.swing.*;
import java.util.*;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
//import javax.swing.JFrame;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
public class MainPanel extends JPanel
                        implements ActionListener
{
	static JFrame frame;
	protected JButton b1, b2;
	public static void main(String args[])
	{
		MainPanel.createAndShowGUI();
	}
    public MainPanel()
    {
        b1 = new JButton("New Bank");
        b1.setMnemonic(KeyEvent.VK_N);
        b1.setActionCommand("New");

        b2 = new JButton("Load Bank");
        b2.setMnemonic(KeyEvent.VK_L);
        b2.setActionCommand("Load");

        //Listen for actions on buttons 1 and 3.
        b1.addActionListener(this);
        b2.addActionListener(this);

        b1.setToolTipText("Click this button Make a new bank");
        b2.setToolTipText("Click this button to Load an old bank");

        //Add Components to this container, using the default FlowLayout.
        add(b1);
        add(b2);
    }
    public void actionPerformed(ActionEvent e)
    {
        if ("New".equals(e.getActionCommand()))
        {
        	System.out.println("NewBank");
        	frame.setVisible(false);
        	frame = new JPane("Making Bank");
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            NewBank n=new NewBank((JPane)frame);
            n.setOpaque(true); //content panes must be opaque
        	frame.setContentPane(n);
        	frame.pack();
        //	frame.setSize(1020,819);
        	frame.setLocation(1020/2-frame.getWidth()/2,819/2-frame.getHeight()/2);
        	frame.setVisible(true);
        }else
        {
        	System.out.println("LoadBank");
        	frame.setVisible(false);
        	frame = new JPane("Loading Bank");
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            LoadBank l=new LoadBank((JPane)frame);
            l.setOpaque(true); //content panes must be opaque
        	frame.setContentPane(l);
        	frame.pack();
        //	frame.setSize(1020,819);
        	frame.setLocation(1020/2-frame.getWidth()/2,819/2-frame.getHeight()/2);
        	frame.setVisible(true);
        }
    }
    private static void createAndShowGUI()
    {
    	try
    	{
    		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    	}catch(Exception e)
    	{
    		
    	}
        //Create and set up the window.
        frame = new JPane("Main Menu");
   //     frame.setSize(1020,819);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        MainPanel newContentPane = new MainPanel();
   //    	Datemaker newContentPane = new Datemaker();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
		
        //Display the window.
       	frame.pack();
       	frame.setLocation(1020/2-frame.getWidth()/2,819/2-frame.getHeight()/2);
        frame.setVisible(true);
    }
}