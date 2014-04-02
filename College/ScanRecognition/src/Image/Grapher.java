package Image;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Grapher extends JFrame
{
	private static Grapher graphing;
	public int lastCounter;
	public int number=0;
	Color c=Color.black;
	Graphics g;
	protected Grapher()
	{
		
	}
	public static void createInstance()
	{
		if(graphing==null)
		{
			graphing=new Grapher();
			graphing.setTitle("GRAPHING 1");
			graphing.setVisible(false);
			graphing.setSize(3000,3000);
			graphing.setLocation(0, 0);
		//	graphing.setAlwaysOnTop(true);
			graphing.setVisible(true);
			graphing.setResizable(true);
			graphing.setBackground(Color.white);
		//	graphing.copy=new BufferedImage(1000,1000,BufferedImage.TYPE_INT_RGB);
		//	graphing.g=graphing.copy.getGraphics();
			graphing.g=graphing.getGraphics();
		}
	}
	public void paint(Graphics g)
	{
		//super.paint(getGraphics());
	//	g.drawImage(copy, 0, 0, null);
	}
	public static void Graph(int newCounter,Color c)
	{
		graphing.Graph(newCounter,c,true);
		graphing.repaint();
	}
	public void Graph(int newCounter,Color c,boolean f)
	{
		lastCounter=newCounter;
		this.c=c;
		while(number>this.getWidth()-20)
		{
			number-=this.getWidth()-20;
		}
		//System.out.println("Painting");
		draw();
		graphing.number=number+1;
	//	System.out.println(newCounter);
	}
	protected void draw()
	{
		g.setColor(c);
		g.drawLine(number+10, this.getHeight()-lastCounter-10, number+10,this.getHeight()-lastCounter-10);
	}
	public static void reset()
	{
		graphing.getGraphics().clearRect(0, 0,graphing.getWidth(),graphing.getHeight());
		graphing.lastCounter=0;
		graphing.number=0;
		graphing.c=Color.black;
	}
}
