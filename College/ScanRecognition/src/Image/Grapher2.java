package Image;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JFrame;

public class Grapher2 extends JFrame
{
	private static Grapher2 graphing;
	Graphics g;
	public int lastCounter;
	public int number=0;
	Color c=Color.black;
	protected Grapher2()
	{
		
	}
	public static void createInstance()
	{
		if(graphing==null)
		{
			graphing=new Grapher2();
			graphing.setTitle("GRAPHING 2");
			graphing.setVisible(false);
			graphing.setSize(1024,1280);
			graphing.setLocation(0, 0);
	//		graphing.setAlwaysOnTop(true);
			graphing.setVisible(true);
			graphing.setResizable(true);
			graphing.setBackground(Color.black);
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
	protected void draw()
	{
		g.setColor(c);
		g.drawLine(number+20,lastCounter+30, number+20,lastCounter+30);
	}
	public static void Graph(Point p,Color c)
	{
		graphing.number=(int)p.getX();
		graphing.Graph((int)p.getY(),c,true);
		graphing.repaint();
	}
	public void Graph(int newCounter,Color c,boolean f)
	{
		lastCounter=newCounter;
		this.c=c;
		//System.out.println("Painting");
		draw();
	//	System.out.println(newCounter);
	}
	public static void reset()
	{
		graphing.getGraphics().clearRect(0, 0,graphing.getWidth(),graphing.getHeight());
		graphing.lastCounter=0;
		graphing.number=0;
		graphing.c=Color.black;
	}
}
