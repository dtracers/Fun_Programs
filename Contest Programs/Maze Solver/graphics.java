import static java.lang.System.*;
import static java.awt.Color.*;
import static java.lang.Integer.*;
import static java.lang.Math.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent.*;
import java.awt.Event.*;
import java.awt.image.*;
import java.applet.*;
import java.io.*;
import java.net.*;
import java.text.*;
import javax.swing.*;
import java.lang.*;
import java.util.*;
import java.sql.*;
import sun.audio.*;

public class graphics extends JFrame implements KeyListener,EventListener,
						MouseListener,MouseMotionListener,MouseWheelListener
{
	Image dbg,dbg2;
	Graphics g,g2;
	boolean run;
	int sizew=1400;
	int sizeh=1000;
	long delay=0,startdelay=0;
	boolean enterpressed=false;
	public static void main(String[] args)
    {
    	graphics v=new graphics();
    }
    public graphics()
    {
		frameInit();
		setSize(sizew,sizeh);
      	setBackground(Color.white);
      	setDefaultCloseOperation(EXIT_ON_CLOSE);
      	setVisible(true);
      	addKeyListener(this);
      	addMouseListener(this);
      	addMouseMotionListener(this);
      	addMouseWheelListener(this);
      	try
    	{
      	dbg = createImage (this.getSize().width, this.getSize().height);
      	}catch(Exception e)
    	{
			e.printStackTrace();
    	}
      	try
    	{
      	dbg2 = createImage (this.getSize().width, this.getSize().height);
      	}catch(Exception e)
    	{
			e.printStackTrace();
    	}
      	try
    	{
		g = dbg.getGraphics ();
		}catch(Exception e)
    	{
			e.printStackTrace();
    	}
		try
    	{
		g2 = dbg2.getGraphics ();
		}catch(Exception e)
    	{
			e.printStackTrace();
    	}
		repaint();
    }
    public void init()
    {
    }
    public void paint(Graphics g3)
    {
    	run=true;
    	try
    	{
    //		System.out.println("flag 82");
	    	g2.drawImage(dbg,0,0,this);
	    	g3.drawImage(dbg2,7,23,this);
    	}catch(Exception e)
    	{

    	}
    }
    public void paint(String m[][])
    {
    	int xd=2,yd=2;
    	int x=xd;
    	int y=yd;
    	for(int k=0;k<m.length;k++)
    	{
    		x=xd;
    		for(int q=0;q<m[k].length;q++)
    		{
    			g.setColor(Color.white);
    			if(m[k][q].matches("X"))
    				g.setColor(Color.black);
    			else if(m[k][q].matches("C"))
    				g.setColor(new Color(255,255,0));
    			else if(m[k][q].matches("O"))
    				g.setColor(new Color(0,255,255));
    			else if(m[k][q].equals("E"))
    				g.setColor(Color.red);
    			else if(m[k][q].equals("S"))
    				g.setColor(Color.green);
    			else if(m[k][q].equals("*"))
    				g.setColor(new Color(255,0,255));
    			g.fillRect(x,y,xd,yd);
    			if(m[k][q].matches("\\d+"))
    			{
    				g.setColor(Color.blue);
    				g.fillRect(x,y,xd,yd);
    				g.setColor(Color.white);
    				g.drawString(m[k][q],x,y+yd*3/4);
    			}
    			x+=xd;

    		}y+=yd;
    	}
    	/*
    	for(int k=0;k<10000000;k++)
    	{
    		for(int q=0;q<10;q++)
    		{
    		}
    	}
    	*/
    	while(enterpressed)
    	{
    	}
    	repaint();
    }
    public void update(Graphics g3)
    {
    	paint(g3);
    }
    public void action1()
    {
	    do
	    {
	    	boolean t=test();
	    	if(t)
			{
				action2();
			}
	    }while(run);
    }
    public void action2()
    {
    	repaint();
    	run=false;
    }
    public boolean test()
    {

		long endDelay=System.currentTimeMillis();
		if(startdelay+delay<=endDelay)
		{
			startdelay=System.currentTimeMillis();
			return true;
		}
		else
			return false;
    }
    public void keyPressed(KeyEvent e)
    {
    }
    public void keyReleased(KeyEvent e)
    {
    }
    public void keyTyped(KeyEvent e)
    {
    }
    public void mousePressed(MouseEvent e)
    {
    	if(e.getClickCount()%2==0)
    		enterpressed=true;
    	else
    		enterpressed=false;
    }
    public void mouseReleased(MouseEvent e)
    {
    }
    public void mouseEntered(MouseEvent e)
    {
    }
    public void mouseExited(MouseEvent e)
    {
    }
    public void mouseClicked(MouseEvent e)
    {
    }
    public void mouseDragged(MouseEvent e)
    {
    }
    public void mouseMoved(MouseEvent e)
    {
    }
    public void mouseWheelMoved(MouseWheelEvent e)
    {
    }
}