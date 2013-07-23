/**
 * Pointsrectolinier.java  9/30/2009
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

public class Pointsrectolinier extends Points
{
	double graphy=0;
	int row=0;
	private static double d4=10.*(200.-settings)/100.,d3=7.*(200.-settings)/100.;
	int xyz=-1;//-1= sepreate x,y,z
	//0 is x=...
	//1 is y=...
	//2 is z=...
    public Pointsrectolinier()
    {
    }
    public Pointsrectolinier(double x,double y)
    {
    	super();
    	graphx=x;graphy=y;
    }
/*    private int recalculateud(Points p,ArrayList<Trans> trans,ArrayList<Integer> width,int k)
    {
    	p.recalculate();
    	double d=visualDistance(this,p);
    	boolean fish=false;
    	double gy=0;
    	if(!onscreen&&k!=0&&k!=list.size()-1)
    	{
    		list.remove(k);
    		return -1;
    	}else if(onscreen)
    	{
	    	if(d<d3&&list.size()>4000*settings/100.)
	    	{
	    		if(k==1)
	    		{
	    			list.remove(k);
	    		}else if(k>1)
	    		{
	    			list.remove(k-1);
	    		}
	    		return -1;
	    	}else if(d>d4&&list.size()<10000*settings/100.)
	    	{
	    		double gy=(this.graphy+p.graphy)/2.;
	    		set(x,s1,s2,gx,gy);
	    		double x2=getxn(x,gx,gy),y2=getyn(x,gx,gy),z2=getzn(x,gx,gy);
	    		boolean cont=false;
	    		if(isfake(x2)||isfake(y2)||isfake(z2))
	    		{
	    			cont=true;
	    		}
	    		if(!cont)
	    		{
		    		Points temp=new Points(x2,y2,z2,gx,gy);
		    		temp.xyz=xyz;
		    		temp.recalculate(trans);
		    		if(p==d)
		    		{
		    			list.add(k+width.get(row),temp);
		    		}
		    		if(p==d)
		    		{
		    			list.add(k-width.get(row-1),temp);
		    			
		    		}
		    		//adds afterwords
		    		return 1;
		    		//d=visualDistance(temp,p);
		    		/*if(d>d4&&list.size()<8000*settings/100.&&temp.onscreen)
		    		{
		    			fish=true;
		    		}*//*
	    		}
	    	}
    	}
    	else if(!onscreen&&k-2>=0&&k==list.size()-1&&p.onscreen)
    	{
    		if(d>(double)4.*(200.-settings)&&list.size()<10000*settings/100.)
    		{
				double gy=p.graphy-((Points)list.get(k-2)).graphy;
				gx+=p.graphx;
				set(x,s1,s2,gx,gy);
	    		double x2=getxn(x,gx,gy),y2=getyn(x,gx,gy),z2=getzn(x,gx,gy);
	    		boolean cont=false;
	    		if(isfake(x2)||isfake(y2)||isfake(z2))
	    		{
	    			cont=true;
	    		}
	    		if(!cont)
	    		{
		    		Points temp=new Points(x2,y2,z2,gx,gy);
		    		temp.xyz=xyz;
		    		temp.recalculate(trans);
		    		list.add(k,temp);
	    		}
    		}
    	}
    	if(fish)
    	{
    		k-=1;
    	}
    	return k;
    }*/
    private void set(Tree x,String s1,String s2,double gx,double gy)
    {
    	x.set(s1,gx);
    	x.set(s2,gy);
    }
    private double getxn(Tree x,double gx, double gy)
    {
    	switch(xyz)
    	{
    		case 0:
    		{
    			return x.getnum();
    		}
    		case 1:
    		{
    			return gx;
    		}
    		case 2:
    		{
    			return gx;
    		}
    		default: return 1/0.;
    	}
    }
    private double getyn(Tree x,double gx, double gy)
    {
    	switch(xyz)
    	{
    		case 0:
    		{
    			return gx;
    		}
    		case 1:
    		{
    			return x.getnum();
    		}
    		case 2:
    		{
    			return gy;
    		}
    		default: return 1/0.;
    	}
    }
    private double getzn(Tree x,double gx, double gy)
    {
    	switch(xyz)
    	{
    		case 0:
    		{
    			return gy;
    		}
    		case 1:
    		{
    			return gy;
    		}
    		case 2:
    		{
    			return x.getnum();
    		}
    		default: return 1/0.;
    	}
    }
    public String toString()//delete after testing
    {
    	return "x: "+graphx+" y: "+graphy;
    }
    public void draw(Graphics2D g)
    {
    	if(onscreen)
    	{
    		if(isdifferent())
	    		g.setColor(color);
	    	else
	    		g.setColor(Color.black);
	    	g.fillRect((int)Math.round(x2d)-2,(int)Math.round(y2d)-2,4,4);
    	}
    }
    public static double getD3()
    {
    	return d3;
    }
    public static double getD4()
    {
    	return d4;
    }
}