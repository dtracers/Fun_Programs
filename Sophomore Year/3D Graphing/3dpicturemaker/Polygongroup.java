package classes;
/**
 * Polygongroup.java  8/11/2008
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

public class Polygongroup
{
	String name;
	ViewWindow p;
	int counter=0;
	private ArrayList objects;
    public Polygongroup()
    {
    	objects=new ArrayList();
    	p=new ViewWindow(0,0,0,0,0);
    }
    public int getsize()
    {
    	return objects.size();
    }
    public Polygongroup(String name)
    {
    	this.name=name;
    	objects=new ArrayList();
    	p=new ViewWindow(0,0,0,0,0);
    }
    public Polygongroup(ViewWindow p2)
    {
    	p=new ViewWindow(0,0,0,0,0);
    	objects=new ArrayList();
    	p.setTo(p2);
    }
    public Polygongroup(ViewWindow p2,String name)
    {
    	p=new ViewWindow(0,0,0,0,0);
    	this.name=name;
    	objects=new ArrayList();
    	p.setTo(p2);
    }

    public void addpolygon(Polygon3D o)
    {
    	objects.add(o);
    }
    public ViewWindow getview()
    {
    	return p;
    }
    public void setwindow(ViewWindow v)
    {
    	p.setTo(v);
    }
    public void addpolygongroup(Polygongroup p)
    {
    	p.addwindow(this.p);
    	objects.add(p);
    }
    public void addwindow(ViewWindow t)
    {
    	p.addx(t.getx());
    	p.addy(t.gety());
    	p.addz(t.getz());
    }
    public void subtractwindow(ViewWindow t)
    {
    	p.addx(-t.getx());
    	p.addy(-t.gety());
    	p.addz(-t.getz());
    }
    public String getname()
    {
    	return name;
    }
    public Polygongroup getgroup(String name)
    {
    	if(this.name!=null&&this.name.equals(name))
    	{
    		return this;
    	}
    	for(int k=0;k<objects.size();k++)
    	{
    		Object temp=objects.get(k);
    		if(temp instanceof Polygongroup)
    		{
    			Polygongroup temp2=(Polygongroup)temp;
    			if(temp2.getname().equals(name))
    			{
    				return temp2;
    			}
    			temp2.getgroup(name);
    		}
    	}
    	return null;
    }
    public ViewWindow getsameview()
    {
    	if(counter>=objects.size())
    	{
    		return null;
    	}
    	if(objects.get(counter) instanceof Polygon3D)
    	{
    		return p;
    	}
    	Polygongroup temp=(Polygongroup)objects.get(counter);
    	return temp.getsameview();
    }
    public Polygon3D getnext()
    {
    	if(counter>=objects.size())
    	{
    		reset();
    		return null;
    	}
    	if(objects.get(counter) instanceof Polygon3D)
    	{
    		counter++;
    		return (Polygon3D)objects.get(counter);
    	}
    	Polygongroup temp=(Polygongroup)objects.get(counter);
    	if(temp.getsize()>=temp.counter)
    		counter++;
    	return temp.getnext();
    }
    public void reset()
    {
    	counter=0;
    	for(int k=0;k<objects.size();k++)
    	{
    		Object temp=objects.get(k);
    		if(temp instanceof Polygongroup)
    		{
    			Polygongroup temp2=(Polygongroup)temp;
    			temp2.reset();
    		}
    	}
    }
}