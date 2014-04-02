/**
 * NewPassword.java  11/7/2010
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
public class NewPassword extends JPanel
                        implements PropertyChangeListener,ActionListener
{
	JPane frame;
	boolean myset=true;
	private JLabel Period=new JLabel("Enter Your Period");
	JButton back=new JButton("BACK");
	public NewPassword(String filename,LoadBank back,JPane f2)
    {
    	super(new GridLayout(1,1));
    	f2.setTitle("Bank Period "+f.name);
    	frame=f2;
    	addMouseListener(this);
    	JPanel tabelPane = new JPanel(new GridLayout(1,1));
    	b=f;
    	table= new JTable(mt=new MyTableModel(f,this));
    	table.addMouseListener(this);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        table.setDefaultRenderer(Double.class,new MoneyRenderer(true,money));

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        tabelPane.add(scrollPane);

        JPanel ButtonPane = new JPanel(new GridLayout(0,1));
       // ButtonPane.setSize(100,100);
        //Adds the buttons
        add = new JButton("ADD");
        add.setMnemonic(KeyEvent.VK_A);
        add.setActionCommand("add");
        add.setAlignmentX(Component.LEFT_ALIGNMENT);
        add.addActionListener(this);
        ButtonPane.add(add);

		trans = new JButton("TRANS");
        trans.setMnemonic(KeyEvent.VK_T);
        trans.setActionCommand("trans");
        trans.setAlignmentX(Component.LEFT_ALIGNMENT);
        trans.addActionListener(this);
        ButtonPane.add(trans);

        trans = new JButton("MULTI TRANS");
        trans.setMnemonic(KeyEvent.VK_T);
        trans.setActionCommand("mtrans");
        trans.setAlignmentX(Component.LEFT_ALIGNMENT);
        trans.addActionListener(this);
        ButtonPane.add(trans);

        JButton apply = new JButton("APPLY JOB");
        apply.setMnemonic(KeyEvent.VK_T);
        apply.setActionCommand("apply");
        apply.setAlignmentX(Component.LEFT_ALIGNMENT);
        apply.addActionListener(this);
        ButtonPane.add(apply);
		/*
        search = new JButton("SEARCH");
        search.setMnemonic(KeyEvent.VK_S);
        search.setActionCommand("search");
        search.setAlignmentX(Component.CENTER_ALIGNMENT);
        search.addActionListener(this);
        ButtonPane.add(search);

        remove = new JButton("REMOVE");
        remove.setMnemonic(KeyEvent.VK_R);
        remove.setActionCommand("remove");
        remove.setAlignmentX(Component.RIGHT_ALIGNMENT);
        remove.addActionListener(this);
        ButtonPane.add(remove);
		*/

        exit = new JButton("EXIT");
        exit.setMnemonic(KeyEvent.VK_E);
        exit.setActionCommand("exit");
        exit.setAlignmentX(Component.RIGHT_ALIGNMENT);
        exit.addActionListener(this);
        ButtonPane.add(exit);

        main = new JButton("MAIN");
        main.setMnemonic(KeyEvent.VK_M);
        main.setActionCommand("main");
        main.setAlignmentX(Component.RIGHT_ALIGNMENT);
        main.addActionListener(this);
        ButtonPane.add(main);

        JButton printButton = new JButton("PRINT");
        printButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        printButton.setMnemonic(KeyEvent.VK_P);
        printButton.setActionCommand("print");
        printButton.addActionListener(this);
		ButtonPane.add(printButton);

        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(tabelPane, BorderLayout.PAGE_START );
        add(ButtonPane, BorderLayout.SOUTH);
    }
}