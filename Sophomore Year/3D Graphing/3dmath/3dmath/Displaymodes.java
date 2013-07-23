/**
 * Displaymodes.java  8/12/2008
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

public class Displaymodes
{
	ArrayList<MyDisplayMode[]> modes=new ArrayList<MyDisplayMode[]>();
	ArrayList<String> names=new ArrayList<String>();
	int lastx=0;
	int lastwidth=0;
	int lasty=40;
	int lastheight=0;
	GraphicsEnvironment environment =
		GraphicsEnvironment.getLocalGraphicsEnvironment();
    GraphicsDevice device = environment.getDefaultScreenDevice();
	DisplayMode goodModes[] = device.getDisplayModes();
	boolean inside=false;
	int index=-1;
    public Displaymodes()
    {
    }
    public void addmodes(MyDisplayMode[] f,String name)
    {
    	modes.add(clone(f));
    	names.add(name);
    }
    public MyDisplayMode[] clone(MyDisplayMode[] p)
    {
    	MyDisplayMode[] temp=new MyDisplayMode[p.length];
    	for(int k=0;k<p.length;k++)
    	{
    		temp[k]=p[k];
    	}
    	return temp;
    }
    public void draw(Graphics g)
    {
    	lastx=0;lastwidth=0;
    	for(int k=0;k<names.size();k++)
    	{
    		box(names.get(k),g);
    	}
    	lastx=0;lastwidth=0;
    //	System.out.println(inside +" inside");
    	if(inside)
    	{
	    	for(int k=0;k<index;k++)
	    	{
	    		boxadvance(names.get(k));
	    	}
	    //	System.out.println(index+" the index ");
	    	MyDisplayMode[] temp=modes.get(index);
	    	for(int k=0;k<temp.length;k++)
	    	{
	    		box2(temp[k].getname(),g,temp[k].getmode());
	    	}
	    	lasty=40;
	    	lastheight=0;
    	}
    }
    public void check(int x,int y)
    {
	    	lastx=0;lastwidth=0;
	    	for(int k=0;k<modes.size();k++)
	    	{
	    		boolean t=boxcheck(names.get(k),x,y);
	    		if(t)
	    		{
		    		inside=true;
		    		index=k;
		    		return;
	    		}
	    	}
	    	if(index!=-1)
	    	if(check2(x,y)==null)
	    	{
		    	index=-1;
		    	inside=false;
		    	lastx=0;lastwidth=0;
	    	}
    }
    public DisplayMode check2(int x,int y)
    {
    	lasty=40;
	    lastheight=0;
		lastx=0;lastwidth=0;
		MyDisplayMode temp[]=modes.get(index);
		for(int k=0;k<index;k++)
		{
			boxadvance(names.get(k));
		}
		for(int k=0;k<temp.length;k++)
	    {
    		DisplayMode t=boxcheck2(temp[k].getname(),x,y,k);
    		if(t!=null)
    		{
    			if(check(t))
	    		return t;
	    		lasty=40;
	   			lastheight=0;
	    		return null;
    		}
	    }
	    lasty=40;
	    lastheight=0;
    	return null;
    }
    public boolean check(DisplayMode temp)
    {
    	for(int k=0;k<goodModes.length;k++)
    	{
    		if(displayModesMatch(temp,goodModes[k]))
    		{
    			return true;
    		}
    	}
    	return false;
    }
    public void boxadvance(String name)
    {
    	int temp=(int)20;
    	int length=10+name.length()*(temp/2);
    	lastx+=lastwidth;
    	lastwidth=length;
    }
    public void boxadvance2(String name)
    {
    	int temp=(int)20;
    	lasty+=20;
	    lastheight=temp;
    }
	public boolean boxcheck(String name,int x,int y)
    {
    	int temp=(int)20;
    	int length=10+name.length()*(temp/2);
    	Rectangle temp3=new Rectangle(lastx+lastwidth,20,length,temp+2);
    	lastx+=lastwidth;
    	lastwidth=length;
    	return temp3.contains(x,y);
    }
    public void box(String name,Graphics g)
    {
    	try
    	{
	    	int u=g.getFont().getSize();
	    	int temp=(int)20;
	    	int length=10+name.length()*(temp/2);
	    	g.setFont(g.getFont().deriveFont((float)temp));
	    	g.setColor(new Color(125,125,125));
	    	g.fillRect(lastx+lastwidth,20,length,temp+2);
	    	g.setColor(Color.black);
	    	g.drawString(name,lastx+lastwidth+5,temp+20);
	    	g.setColor(Color.red);
	    	g.drawRect(lastx+lastwidth,20,length,temp+2);
	    	lastx+=lastwidth;
	    	lastwidth=length;
	    	g.setFont(g.getFont().deriveFont((float)u));
    	}catch(NullPointerException e)
    	{
    		System.out.println(g==null);
    		e.printStackTrace();
    	}
    }
    public DisplayMode boxcheck2(String name,int x,int y,int i)
    {
    	int temp=(int)20;
    	int length=10+name.length()*(temp/2);
    	Rectangle temp3=new Rectangle(lastx+lastwidth,lasty,length,temp+2);
    	if(temp3.contains(x,y))
    	{
    		DisplayMode temp4=modes.get(index)[i].getmode();
			lasty+=20;
	    	lastheight=temp;
	    	System.out.println(temp4);
    		return temp4;
    	}
    	else
    	{
    		lasty+=20;
	  		lastheight=temp;
    		return null;
    	}

    }
    public void box2(String name,Graphics g,DisplayMode f)
    {
    	try
    	{
	    	int u=g.getFont().getSize();
	    	int temp=(int)20;
	    	int length=10+name.length()*(temp/2);
	    	g.setFont(g.getFont().deriveFont((float)temp));
	    	g.setColor(new Color(125,125,125));
	    	g.fillRect(lastx+lastwidth,lasty,length,temp+2);
	    	if(check(f))
		    	g.setColor(Color.black);
	    	else
	    		g.setColor(new Color(175,175,175));
	    	g.drawString(name,lastx+lastwidth+5,lasty+20);
	    	g.setColor(Color.red);
	    	g.drawRect(lastx+lastwidth,lasty,length,temp+2);
	    	lasty+=20;
	    	lastheight=temp;
	    	g.setFont(g.getFont().deriveFont((float)u));
    	}catch(NullPointerException e)
    	{
    		System.out.println(g==null);
    		e.printStackTrace();
    	}
    }
    public boolean displayModesMatch(DisplayMode mode1,
        DisplayMode mode2)

    {
        if (mode1.getWidth() != mode2.getWidth() ||
            mode1.getHeight() != mode2.getHeight())
        {
            return false;
        }

        if (mode1.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI &&
            mode2.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI &&
            mode1.getBitDepth() != mode2.getBitDepth())
        {
            return false;
        }

        if (mode1.getRefreshRate() !=
            DisplayMode.REFRESH_RATE_UNKNOWN &&
            mode2.getRefreshRate() !=
            DisplayMode.REFRESH_RATE_UNKNOWN &&
            mode1.getRefreshRate() != mode2.getRefreshRate())
         {
             return false;
         }

         return true;
    }
}