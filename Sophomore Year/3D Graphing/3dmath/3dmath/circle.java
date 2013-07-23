/**
 * circle.java  9/9/2008
 *
 * @author - Jane Doe
 * @author - Period n
 * @author - Id nnnnnnn
 *
 * @author - I received help from ...
 *
 */
import java.awt.*;
import java.applet.*;
import java.io.*;
import java.net.*;
import java.text.*;
import javax.swing.*;
import java.lang.*;
import java.util.*;
import java.sql.*;
import sun.audio.*;

public class circle
{
	int x,y;
	double x2,y2;
	boolean down,right;
	double randy,randx;
	Random rand=new Random();
	Color b=new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
    public circle()
    {
    }
    public circle(int x3,int y3)
    {
    	x2=x3;y2=y3;
    	x=x3;y=y3;
    }
    public void draw(Graphics g)
    {
    	g.setColor(b);
    	g.drawRect(x,y,20,20);
    }
    public void increase(int w,int h)
    {
    	if(right)
    	{
    		x2+=1*randx;
    		x=(int)Math.round(x2);
    	}
    	else
    	{
    		x2-=1*randx;
    		x=(int)Math.round(x2);
    	}
    	if(down)
    	{
    		y2+=1*randy;
    		y=(int)Math.round(y2);
    	}
    	else
    	{
    		y2-=1*randy;
    		y=(int)Math.round(y2);
    	}
    	if((down&&y+20>=h)||(!down&&y<=0))
    	{
    		down=!down;
    		randy=Math.random();
    	}
    	if((right&&x+20>=w)||(!right&&x<=0))
    	{
    		right=!right;
    		randx=Math.random();
    	}
    }
}