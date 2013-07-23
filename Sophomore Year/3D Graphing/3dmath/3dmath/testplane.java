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

public class testplane extends JFrame implements KeyListener,EventListener,
						MouseListener,MouseMotionListener,MouseWheelListener
{
	Image dbg,dbg2;
	Graphics g,g2;
	boolean run;
	int sizew=1024;
	int sizeh=760;
	long delay=0,startdelay=0;
	transform form,form2;
	Points p2,p;
	equationset set=new equationset("coolestever.eq");
	Tree x=set.getX();
	Tree y=set.getY();
	Tree z=set.getZ();
	loadpic load=new loadplane("object1.dat");
	graph gr=load.getgraph();
	boolean shift=false;
	boolean zp=false;
	boolean xp=false;
	boolean yp=false;
	boolean pp=false;
	boolean pm=false;
	boolean up=false;
	boolean ct=false;
	boolean down=false;
	boolean left=false;
	boolean right=false;
	boolean mouseb1=false;
	boolean mouseb2=false;
	boolean mouseb3=false;
	boolean dragged=false;
	boolean pc=true;
	public static void main(String[] args)
    {
    	testplane v=new testplane();
    }
    public testplane()
    {
    	gr.init(x,"x",y,"x",z,"x",-100,100);
    	form=new transform(0,0,-variables.panz);
    	form2=new transform(0,0,-variables.panz);
    	form.rotateAngleY(.01);
    	form.rotateAngleX(.01);
    	form.rotateAngleZ(.01);
    	form2.rotateAngleY(-.01);
    	form2.rotateAngleX(-.01);
    	form2.rotateAngleZ(-.01);
   // 	p=new Points(10,10,10);
   // 	p2=new Points(10,10,10);
   // 	gr.add(p);
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
    	try
    	{
    		run=true;
	    	g.setColor(Color.white);
	    	g.fillRect(0,0,this.getSize().width, this.getSize().height);
	    	load.draw(g);
	    	g2.drawImage(dbg,0,0,this);
	    	g3.drawImage(dbg2,5,24,this);
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
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
	    	boolean change=false;
	    	boolean t=test();
	    	if(t)
			{
				if(shift)
				{
					if(yp)
					{
						change=true;
						gr.backrotatey(0);
					}
					if(xp)
					{
						change=true;
							gr.backrotatex(0);
					}
					if(zp)
					{
						change=true;
						gr.backrotatez(0);
					}
				}else
				{
					if(yp)
					{
						change=true;
						gr.rotatey(0);
					}
					if(xp)
					{
						change=true;
						gr.rotatex(0);
					}
					if(zp)
					{
						change=true;
						gr.rotatez(0);
					}
				}
				if(pm)
				{

					if(!ct)
					{
						gr.zoomOut(0);
					}
					graph tempg=gr;
					tempg.zoomOut(0);
					tempg.zoomOut(0);
					tempg.zoomOut(0);
					change=true;
				//	System.out.println(variables.panz);
				}
				else if(pp)
				{
					if(!ct)
					{
						gr.zoomIn(0);
					}
					graph tempg=gr;
					tempg.zoomIn(0);
					tempg.zoomIn(0);
					tempg.zoomIn(0);
					change=true;
			//		System.out.println(variables.panz);
				}
				if(ct)
				{
					if(up||down)
					{
						change=true;
						gr.Moveupdown(down,0);
					}
					if(left||right)
					{
						change=true;
						gr.Moveleftright(right,0);
					}
				}else
				{
					if(up||down)
					{
						change=true;
						gr.panupdown(down);
					}
					if(left||right)
					{
						change=true;
						gr.panleftright(right);
					}
				}
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
    	if(e.getKeyCode()==61||e.getKeyCode()==107)
    	{
    		pp=true;
    	}
    	else if(e.getKeyCode()==45||e.getKeyCode()==109)
    	{
    		pm=true;
    	}
    	if(e.getKeyCode()==e.VK_X)
    	{
    		xp=true;
    	}
    	if(e.getKeyCode()==e.VK_UP)
    	{
    		up=true;
    	}
    	if(e.getKeyCode()==e.VK_LEFT)
    	{
    		left=true;
    	}
    	if(e.getKeyCode()==e.VK_DOWN)
    	{
    		down=true;
    	}
    	if(e.getKeyCode()==e.VK_RIGHT)
    	{
    		right=true;
    	}
    	if(e.getKeyCode()==e.VK_Y)
    	{
    		yp=true;
    	}
    	if(e.getKeyCode()==e.VK_Z)
    	{
    		zp=true;
    	}
    	if(e.getKeyCode()==e.VK_B)
    	{
    		gr.goback();
    	}
    	if(e.getKeyCode()==e.VK_SHIFT)
    	{
    		shift=true;
    		
    	}
    	if(e.getKeyCode()==e.VK_CONTROL)
    	{
    		ct=true;
    	}
    	if(e.getKeyCode()==e.VK_P&&pc)
    	{
    		pc=false;
    		ArrayList<Extendable> objects=(ArrayList<Extendable>)gr.objects;
    		objects.get(0).recalculate();
    		for(int k=1;k<objects.size();k++)
			{
				k=((Points)objects.get(k)).recalculate((Points)objects.get(k-1),
				objects,k,x,"x",y,"x",z,"x",gr.getTransforms());
				objects.get(k).setColor(blue);
			}
    	}
    	repaint();
    }
    public void keyReleased(KeyEvent e)
    {
    	if(e.getKeyCode()==61||e.getKeyCode()==107)
    	{
    		pp=false;
    		repaint();
    	}
    	else if(e.getKeyCode()==45||e.getKeyCode()==109)
    	{
    		pm=false;
    		repaint();
    	}
    	if(e.getKeyCode()==e.VK_UP)
    	{
    		up=false;
    		repaint();
    	}
    	if(e.getKeyCode()==e.VK_LEFT)
    	{
    		left=false;
    		repaint();
    	}
    	if(e.getKeyCode()==e.VK_DOWN)
    	{
    		down=false;
    		repaint();
    	}
    	if(e.getKeyCode()==e.VK_RIGHT)
    	{
    		right=false;
    		repaint();
    	}
    	if(e.getKeyCode()==e.VK_X)
    	{
    		xp=false;
    		repaint();
    	}
    	if(e.getKeyCode()==e.VK_Y)
    	{
    		yp=false;
    		repaint();
    	}
    	if(e.getKeyCode()==e.VK_Z)
    	{
    		zp=false;
    		repaint();
    	}
    	if(e.getKeyCode()==e.VK_SHIFT)
    	{
    		shift=false;
    		repaint();
    	}
    	if(e.getKeyCode()==e.VK_CONTROL)
    	{
    		ct=false;
    	}
    	if(e.getKeyCode()==e.VK_P&&!pc)
    	{
    		pc=true;
    		ArrayList<Extendable> objects=(ArrayList<Extendable>)gr.objects;
    		for(int k=0;k<objects.size();k++)
			{
				objects.get(k).setColor(black);
			}
    	}
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
    	int t=e.getWheelRotation();
    	for(int k=0;k<(int)Math.abs(t*10);k++)
    	{
    		if(t<0)
    		{
    			gr.zoomIn();
    		}else
    		{
    			gr.zoomOut();
    		}
    	}
    }
}