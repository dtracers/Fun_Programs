import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.Random;

import javax.swing.Timer;


public class Mole
{
	int x,y;
	int score;
	Random r=new Random();
	Timer t;
	Display disp;
	public Mole(Timer t, Display display)
	{
		disp=display;
		this.t=t;
		reset();
	}
	public void actionPerformed(ActionEvent e)
	{
		reset();
	}
	public void reset()
	{
		x=r.nextInt(40)*20+100;
		y=r.nextInt(40)*20+100;
		score=r.nextInt(10)*10+10;
	}
	public void isClicked(int x2, int y2)
	{
		//System.out.println(" x2= "+x2+ " y2= "+y2+" x="+x+"y="+y);
		if(x2>x-10&&x2<x+10&&y2>y-10&&y2<y+10)
		//if(Math.sqrt(Math.pow(x2-x,2)+Math.pow(y2-y, 2))<10)
		{
			disp.score+=score;
			//System.out.println("clicked");
			reset();
			disp.paintagain();
			t.restart();
		}
	}
	public void draw(Graphics g)
	{
		g.setColor(Color.black);
		g.fillOval(x-10, y-10, 20, 20);
	}
}
