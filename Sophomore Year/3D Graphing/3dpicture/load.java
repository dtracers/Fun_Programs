import static java.lang.System.*;
import java.awt.*;
import static java.awt.Color*;
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

public class load extends JFrame implements KeyListener,EventListener,
						MouseListener,MouseMotionListener,MouseWheelListener
{
	Image dbg,dbg2;
	Graphics g,g2;
	boolean run;
	int sizew=1024;
	int sizeh=760;
	long delay=0,startdelay=0;
	public static void main(String[] args)
    {
    	load v=new load();
    }
    public load()
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
      	dbg = createImage (this.getSize().width, this.getSize().height);
      	dbg2 = createImage (this.getSize().width, this.getSize().height);
		g = dbg.getGraphics ();
		g2 = dbg2.getGraphics ();
		repaint();
    }
    public void init()
    {
    }
    public void paint(Graphics g3)
    {
    	run=true;
    	g2.drawImage(dbg,0,0,this);
    	g3.drawImage(dbg2,0,0,this);
    	action1();
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