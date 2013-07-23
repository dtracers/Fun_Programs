/**
 * Line2D.java  10/1/2009
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

public class Line2D
{
	double x,y;
	double mag;
    public Line2D()
    {
    }
    public Line2D(double... l)
    {
    	x=l[2]-l[0];y=l[3]-l[1];
    	mag=Math.sqrt((x*x+y*y));
    }
    public double getangle(Line2D o)
    {
    	double t=Math.acos((x*o.x+y*o.y)/(mag*o.mag));
    	if(x!=0&&o.x!=0)
    	{
    		if(y/x<o.y/o.x)
    			t*=-1;
    	}else if(x==0&&o.x!=0)
    	{
    		if(o.y/o.x>0)
    		{
    			t*=-1;
    		}
    	}else if(x!=0&&o.x==0)
    	{
    		if(y/x<0)
    		{
    			t*=-1;
    		}
    	}
    	t=Math.toDegrees(t);
    	return t;
    }
    public double getX()
    {
    	return x;
    }
    public double getY()
    {
    	return y;
    }
}