package classes;
import static java.lang.System.*;
import java.awt.*;
import static java.awt.Color.*;
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
public class test extends JFrame implements KeyListener,EventListener,
						MouseListener,MouseMotionListener,MouseWheelListener
{
	Image dbg,dbg2;
	Graphics g,g2;
	boolean run;
	int sizew=1024;
	int sizeh=760;
	long delay=0,startdelay=0;
	private ViewWindow s;
	private PolygonRenderer polys;
	ScreenManager screen;
	public static void main(String[] args)
    {
    	test v=new test();
    }
    public test()
    {
    	screen = new ScreenManager();
        DisplayMode displayMode =
        screen.findFirstCompatibleMode(MID_RES_MODES);
        screen.setFullScreen(displayMode,this);
      	setBackground(Color.white);
      	setDefaultCloseOperation(s2.EXIT_ON_CLOSE);
      	setVisible(true);
      	addKeyListener(this);
      	addMouseListener(this);
      	addMouseMotionListener(this);
      	addMouseWheelListener(this);
      	dbg = createImage (getSize().width, getSize().height);
      	dbg2 = createImage (getSize().width, getSize().height);
		g = dbg.getGraphics ();
		g2 = dbg2.getGraphics ();
		action1();
    }
    public void init()
    {
    }
    public void paint(Graphics g3)
    {
    	run=true;
    	g.setColor(white);
    	g.fillRect(0,0,1000,1000);
    	g.setColor(black);
    	g.fillRect(0,0,100,100);
    	System.out.println(" painting");
    	g2.drawImage(dbg,0,0,s2);
    	g3.drawImage(dbg2,0,0,s2);
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
    protected static final DisplayMode[] ALL_MODES=
    {
    	new DisplayMode(1280, 720, 16, 0),
        new DisplayMode(1280, 720, 32, 0),
        new DisplayMode(1280, 720, 24, 0),
    	new DisplayMode(1280, 768, 16, 0),
        new DisplayMode(1280, 768, 32, 0),
        new DisplayMode(1280, 768, 24, 0),
        new DisplayMode(1280, 960, 16, 0),
        new DisplayMode(1280, 960, 32, 0),
        new DisplayMode(1280, 960, 24, 0),
        new DisplayMode(1280, 1024, 16, 0),
        new DisplayMode(1280, 1024, 32, 0),
        new DisplayMode(1280, 1024, 24, 0),
    	new DisplayMode(1024, 768, 16, 0),
        new DisplayMode(1024, 768, 32, 0),
        new DisplayMode(1024, 768, 24, 0),
        new DisplayMode(1052, 864, 16, 0),
        new DisplayMode(1052, 864, 32, 0),
        new DisplayMode(1052, 864, 24, 0),
        new DisplayMode(1280, 600, 16, 0),
        new DisplayMode(1280, 600, 32, 0),
        new DisplayMode(1280, 600, 24, 0),
        new DisplayMode(1280, 720, 16, 0),
        new DisplayMode(1280, 720, 32, 0),
        new DisplayMode(1280, 720, 24, 0),
    	new DisplayMode(800, 600, 16, 0),
        new DisplayMode(800, 600, 32, 0),
        new DisplayMode(800, 600, 24, 0),
        new DisplayMode(640, 480, 16, 0),
        new DisplayMode(640, 480, 32, 0),
        new DisplayMode(640, 480, 24, 0),
        new DisplayMode(1024, 768, 16, 0),
        new DisplayMode(1024, 768, 32, 0),
        new DisplayMode(1024, 768, 24, 0),
    	new DisplayMode(640, 480, 16, 0),
        new DisplayMode(640, 480, 32, 0),
        new DisplayMode(640, 480, 24, 0),
        new DisplayMode(800, 600, 16, 0),
        new DisplayMode(800, 600, 32, 0),
        new DisplayMode(800, 600, 24, 0),
        new DisplayMode(1024, 768, 16, 0),
        new DisplayMode(1024, 768, 32, 0),
        new DisplayMode(1024, 768, 24, 0),
    	new DisplayMode(320, 240, 16, 0),
        new DisplayMode(400, 300, 16, 0),
        new DisplayMode(512, 384, 16, 0),
        new DisplayMode(640, 480, 16, 0),
        new DisplayMode(800, 600, 16, 0),
    };
    protected static final DisplayMode[] VERY_HIGH_RES_MODES =
    {
    	new DisplayMode(1280, 720, 16, 0),
        new DisplayMode(1280, 720, 32, 0),
        new DisplayMode(1280, 720, 24, 0),
    	new DisplayMode(1280, 768, 16, 0),
        new DisplayMode(1280, 768, 32, 0),
        new DisplayMode(1280, 768, 24, 0),
        new DisplayMode(1280, 960, 16, 0),
        new DisplayMode(1280, 960, 32, 0),
        new DisplayMode(1280, 960, 24, 0),
        new DisplayMode(1280, 1024, 16, 0),
        new DisplayMode(1280, 1024, 32, 0),
        new DisplayMode(1280, 1024, 24, 0),


    };
    protected static final DisplayMode[] HIGH_RES_MODES =
    {
    	new DisplayMode(1024, 768, 16, 0),
        new DisplayMode(1024, 768, 32, 0),
        new DisplayMode(1024, 768, 24, 0),
        new DisplayMode(1052, 864, 16, 0),
        new DisplayMode(1052, 864, 32, 0),
        new DisplayMode(1052, 864, 24, 0),
        new DisplayMode(1280, 600, 16, 0),
        new DisplayMode(1280, 600, 32, 0),
        new DisplayMode(1280, 600, 24, 0),
        new DisplayMode(1280, 720, 16, 0),
        new DisplayMode(1280, 720, 32, 0),
        new DisplayMode(1280, 720, 24, 0),

    };
    protected static final DisplayMode[] MID_RES_MODES =
    {
        new DisplayMode(800, 600, 16, 0),
        new DisplayMode(800, 600, 32, 0),
        new DisplayMode(800, 600, 24, 0),
        new DisplayMode(640, 480, 16, 0),
        new DisplayMode(640, 480, 32, 0),
        new DisplayMode(640, 480, 24, 0),
        new DisplayMode(1024, 768, 16, 0),
        new DisplayMode(1024, 768, 32, 0),
        new DisplayMode(1024, 768, 24, 0),
    };
    protected static final DisplayMode[] LOW_RES_MODES =
    {
        new DisplayMode(640, 480, 16, 0),
        new DisplayMode(640, 480, 32, 0),
        new DisplayMode(640, 480, 24, 0),
        new DisplayMode(800, 600, 16, 0),
        new DisplayMode(800, 600, 32, 0),
        new DisplayMode(800, 600, 24, 0),
        new DisplayMode(1024, 768, 16, 0),
        new DisplayMode(1024, 768, 32, 0),
        new DisplayMode(1024, 768, 24, 0),
    };
    protected static final DisplayMode[] VERY_LOW_RES_MODES =
    {
    	new DisplayMode(320, 240, 16, 0),
        new DisplayMode(400, 300, 16, 0),
        new DisplayMode(512, 384, 16, 0),
        new DisplayMode(640, 480, 16, 0),
        new DisplayMode(800, 600, 16, 0),
    };
}