/**
 * Tansaction.java  1/10/2010
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
import sun.audio.*;

public class Tansaction implements Sorter,Comparable
{
	private String description;
	private double change;
	private Date stamp;
	private long time;
    public Tansaction()
    {

    }
    public Tansaction(String d,double c,long s)
    {
    	time=s;description=d;change=c;
    	stamp=new Date(s);
    }
    public String getDescript()
    {
    	return description;
    }
    public double getChange()
    {
    	return change;
    }
    public Date getDate()
    {
    	return stamp;
    }
    public long getTimeMillis()
    {
    	return time;
    }
    public int compareTo(Sorter j,int k)
    {
    	Tansaction s=(Tansaction)j;
    	switch(k)
    	{
    		case 0:
    		{
    			return stamp.compareTo(s.getDate());
    		}
    		case 1:
    		{
    			return -stamp.compareTo(s.getDate());
    		}
    		case 2:
    		{
    			return description.compareTo(s.getDescript());
    		}
    		case 3:
    		{
    			return -description.compareTo(s.getDescript());
    		}
    		case 4:
    		{
    			return new Double(change).compareTo(new Double(s.getChange()));
    		}
    		case 5:
    		{
    			return -new Double(change).compareTo(new Double(s.getChange()));
    		}
    		default: return 1;
    	}
    }
    public String toString()
    {
    	return description+"`"+change+"`"+time;
    }
    public int compareTo(Object t)
    {
    	Tansaction t2=(Tansaction)t;
    	return this.stamp.compareTo(t2.stamp);
    }
}