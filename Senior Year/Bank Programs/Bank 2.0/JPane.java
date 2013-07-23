/**
 * JPane.java  1/15/2010
 *
 * @author - Jane Doe
 * @author - Period n
 * @author - Id nnnnnnn
 *
 * @author - I received help from ...
 *
 */

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class JPane extends JFrame implements WindowListener
{
	public String password="";
	boolean saved=false;
	public combiner s;
	public JPane(combiner s2)
	{
		super();
		addWindowListener(this);
		s=s2;
		setSize(800,500);
	}
	public JPane(String g,combiner s2)
	{
		super(g);
		addWindowListener(this);
		s=s2;
		setSize(800,500);
	}
	public static void main(String[] args)
	{
	}
	public void windowActivated(WindowEvent e)
	{

	}
	public void windowClosed(WindowEvent e)
	{
		System.out.println("Exit2");
		if(!saved&&s!=null)
		{
			saved=s.exit();
		}
	}
	public void windowClosing(WindowEvent e)
	{
		System.out.println("Exit1");
		if(!saved&&s!=null)
		{
			saved=s.exit();
		}
	}
	public void windowDeactivated(WindowEvent e)
	{

	}
	public void windowDeiconified(WindowEvent e)
	{

	}
	public void windowIconified(WindowEvent e)
	{

	}
	public void windowOpened(WindowEvent e)
	{

	}
	public void setExit(combiner s)
	{
		this.s=s;
	}
	public JPane(String g)
	{
		super(g);
		addWindowListener(this);
		setSize(800,500);
	}
}