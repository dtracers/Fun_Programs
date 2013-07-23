/**
 * equationset.java  9/16/2009
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

public class equationset
{
	Tree x;
	Tree y;
	Tree z;
	protected equationset()
	{
		
	}
    public equationset(String x,String y,String z)
    {
    	input(x,y,z);
    }
    public equationset(String file)
    {
    	input(file);    	
    }    
    public equationset(String file,int x,int y,int z)
    {
    	input(file,x,y,z);
    }
    public void input(String file)
    {
    	try
    	{
    		BufferedReader s = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(file)));
	    	this.x=new expressiontree().getTree(s.readLine());
	    	this.y=new expressiontree().getTree(s.readLine());
	    	this.z=new expressiontree().getTree(s.readLine());
    	}catch(Exception e)
    	{
    		file=JOptionPane.showInputDialog(null,""+e+"\ninput next file or enter quit to exit","ERROR",0);
    		if(file.equals("quit"))
    			System.exit(0);
    		else
    			input(file);
    	}
    }
    public void input(String x,String y,String z)
    {
    	this.x=new expressiontree().getTree(x);
    	this.y=new expressiontree().getTree(y);
    	this.z=new expressiontree().getTree(z);
    }
    public void input(String file,int x,int y,int z)
    {
    	try
    	{
	    	BufferedReader s = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(file)));
	    	int count=0;
	    	String g="why";
	    	while((g!=null&&!g.equals(""))&&(count<=x||count<=y||count<=z))
	    	{
	    		g=s.readLine();
	    		if((g!=null&&!g.equals("")))
	    		{
		    		if(count==x)
		    		{
		    			this.x=new expressiontree().getTree(g);
		    		}
		    		if(count==y)
		    		{
		    			this.y=new expressiontree().getTree(g);
		    		}
		    		if(count==z)
		    		{
		    			this.z=new expressiontree().getTree(g);
		    		}
		    		count++;
	    		}
	    	}
    	}catch(Exception e)
    	{
    		file=JOptionPane.showInputDialog(null,""+e+"\ninput next file or enter quit to exit","ERROR",0);
    		if(file.equals("quit"))
    			System.exit(0);
    		else
    		{
    			input(file,x,y,z);
    		}
    	}
    }
    public Tree getX()
    {
    	return x;
    }
    public Tree getY()
    {
    	return y;
    }
    public Tree getZ()
    {
    	return z;
    }
    public graph getGraph()
    {
    	graph tempg=new graph();
    	tempg.setformloc(new Points(-100,100,-100));
    	tempg.init(x,"x",y,"x",z,"x",-100,100);
    	return tempg;
    }
    public boolean isregular()
    {
    	return true;
    }
}

class equationplane extends equationset
{
	int w=-1;
	public equationplane(String x,String g)
    {
    	super();
    	isdiff2(g);
    	setTree(new expressiontree().getTree(x));
    }
    public equationplane(String file)
    {
    	super();
    	input(file);
    }
    public equationplane(String file,int x)
    {
    	super();
    	input(file,x);
    }
    public void input(String file)
    {
    	isdiff(file);
    	try
    	{
    		BufferedReader s = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(file)));
	    	setTree(new expressiontree().getTree(s.readLine()));
    	}
	    catch(Exception e)
    	{
    		file=JOptionPane.showInputDialog(null,""+e+"\ninput next file or enter quit to exit","ERROR",0);
    		if(file.equals("quit"))
    			System.exit(0);
    		else
    			input(file);
    	}
    }
    public void input(String file,int x)
    {
    	isdiff(file);
    	try
    	{
    		
	    	BufferedReader s = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(file)));
	    	int count=0;
	    	String g="why";
	    	while((g!=null&&!g.equals(""))&&(count<=x))
	    	{
	    		g=s.readLine();
	    		if((g!=null&&!g.equals("")))
	    		{
		    		if(count==x)
		    		{
		    			setTree(new expressiontree().getTree(g));
		    		}
		    		count++;
	    		}
	    	}
    	}catch(Exception e)
    	{
    		file=JOptionPane.showInputDialog(null,""+e+"\ninput next file or enter quit to exit","ERROR",0);
    		if(file.equals("quit"))
    			System.exit(0);
    		else
    		{
    			input(file,x);
    		}
    	}
    }
	public boolean isdiff(String file)
    {
    	if(file.charAt(file.length()-1)=='x')
    	{
    		w=0;
    		return true;
    	}
    	if(file.charAt(file.length()-1)=='y')
    	{
    		w=1;
    		return true;
    	}
    	if(file.charAt(file.length()-1)=='z')
    	{
    		w=2;
    		return true;
    	}
    	return false;
    }
    public boolean isdiff2(String file)
    {
    	if(file.equals("x"))
    	{
    		w=0;
    		return true;
    	}
    	if(file.equals("y"))
    	{
    		w=1;
    		return true;
    	}
    	if(file.equals("z"))
    	{
    		w=2;
    		return true;
    	}
    	return false;
    }
    public boolean isregular()
    {
    	return false;
    }
    public Tree getX()
    {
    	if(w==0)
    		return x;
    	else
    		return null;
    }
    public Tree getY()
    {
    	if(w==1)
    		return y;
    	else
    		return null;
    }
    public Tree getZ()
    {
    	if(w==2)
    		return z;
    	else
    		return null;
    }
    public void setTree(Tree g)
    {
    	if(w==0)
    	{
    		x=g;
    	}else
    	if(w==1)
    	{
    		y=g;
    	}else if(w==2)
    	{
    		z=g;
    	}
    }
}