package WormStuff;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Gameplay.Visual.Display;
import Test.Main;


public class Worm implements ActionListener
{
	int x,y;
	private int d;//0=north,1=west,2=south,3=east
	boolean movedyet=false;
	public static int distance=Display.distance;
	Segment old;
	Food food;
	@Override
	public void actionPerformed(ActionEvent e)
	{
		moveup();
	}
	public Worm(int x,int y,Food d)
	{
		this.food=d;
		this.x=x;
		this.y=y;
	}
	public Worm()
	{
		
	}
	public void moveup()
	{
		if(old!=null)
		{
			moveup(this.x,this.y);
		}
		switch(getDirection())
		{
			case 0:y-=5;break;
			case 1:x-=5;break;
			case 2:y+=5;break;
			case 3:x+=5;break;
		}
		checkeaten(food);
		if(checkdead(null))
		{
			Main.dead();
			//System.out.println("i'm dead");
		}
	}
	public void moveup(int x,int y)
	{
		if(old!=null&&movedyet)
			old.moveup(this.x,this.y);
		this.x=x;
		this.y=y;
		movedyet=true;
	}
	public void draw(Graphics g)
	{
		if(movedyet)
		{
			if(this instanceof Segment)
			{
				if(old!=null)
				g.setColor(Color.black);
				else
					g.setColor(Color.green);
			}
			else
				g.setColor(Color.red);
			g.fillRect(x, y, 5, 5);
			g.setColor(Color.white);
			g.drawRect(x, y, 5, 5);
		}
		if(old!=null)
			old.draw(g);
	}
	public void passback(Segment t)
	{
		if(old==null)
			old=t;
		else
		{
			old.passback(t);
		}
	}
	public void checkeaten(Food d)
	{
		if(d.x==this.x&&d.y==this.y)
		{
			d.reset();
			passback(new Segment());
		}
	}
	public boolean checkdead(Worm head)
	{
		if(head==null)
		{
			if(this.x<0||this.x>1000||this.y<0||this.y>1000)
			{
				System.out.println("im outside boundries");
				return true;
			}
			if(old!=null)
			{
				return old.checkdead(this);
			}
		}else
		{
			if(movedyet)
			{
				if(this.x==head.x&&this.y==head.y)
				{
					System.out.println("im in another segment");
					return true;
				}
				else if(old!=null)
				{
					return old.checkdead(head);
				}
			}
		}
		System.out.println("return false");
		return false;
	}
	public void setDirection(int d) {
		this.d = d;
	}
	public int getDirection() {
		return d;
	}
}
