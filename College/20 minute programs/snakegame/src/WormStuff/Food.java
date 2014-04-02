package WormStuff;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import Gameplay.Visual.Display;


public class Food
{
	
	int x,y;
	public static int distance=Display.distance;
	public Food()
	{
		reset();
	}
	public void reset()
	{
		Random r=new Random();
		x=2*distance+r.nextInt(100)*2*distance;
		y=2*distance+r.nextInt(100)*2*distance;
	}
	public void draw(Graphics g)
	{
		g.setColor(Color.green);
		g.fillOval(x, y, distance, distance);
	}
}
