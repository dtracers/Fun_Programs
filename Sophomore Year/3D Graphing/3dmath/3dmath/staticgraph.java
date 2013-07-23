/**
 * staticgraph.java  7/27/2008
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
import static java.lang.Math.*;

public class staticgraph extends variables implements objectproperties
{
	double xsize=100;
	double ysize=100;
	double zsize=100;
	ViewWindow view=new ViewWindow();
	ArrayList<Extendable> objects=new ArrayList<Extendable>();
    public staticgraph()
    {
    	add();
    }
    public void converToPolygon()
    {
    	for(int k=0;k<objects.size();k++)
    	{
    		Extendable t=objects.get(k);
    		if(t instanceof Polys)
    		{
    		}else
    		{
    			if(t instanceof singular)
    			{
    				if(t instanceof Line3d)
    				{
    					objects.set(k,convertLine((Line3d)t));
    				}else
    				if(t instanceof Ray3d)
    				{
    					objects.set(k,convertRay((Ray3d)t));
    				}
    				if(t instanceof Points)
    				{
    					objects.set(k,convertPoint((Points)t));
    				}
    			}
    		}
    	}
    }
    public Polygon3d convertLine(Line3d t)
    {
    	return null;
    }
    public Polygon3d convertRay(Ray3d t)
    {
		return null;
    }
    public Polygon3d convertPoint(Points t)
    {
		return null;
    }
    public void add(Extendable h)
    {
    	objects.add(h);
    }
    public void add()
    {
//    	objects.add(new Points());//0,0,0 ///1
//
//    	objects.add(new Points(0,xsize*1,0));//0,100,0 ///2
//    	objects.add(new Points(0,-xsize*1,0));//0,-100,0 ///3
//
//    	objects.add(new Points(ysize*1,0,0));//100,0,0 ///4
//    	objects.add(new Points(-ysize*1,0,0));//-100,0,0 ///5
//
//		objects.add(new Points(ysize*1,xsize*1,0));//100,100,0 ///16
//    	objects.add(new Points(ysize*1,-xsize*1,0));//100,-100,0 ///17
//    	objects.add(new Points(-ysize*1,xsize*1,0));//-100,100,0 ///18
//    	objects.add(new Points(-ysize*1,-xsize*1,0));//-100,-100,0 ///19
//
//    	objects.add(new Points(0,0,zsize*1));//0,0,100 ///6
//    	objects.add(new Points(0,0,-zsize*1));//0,0,-100 ///7
//
//    	objects.add(new Points(0,xsize*1,zsize*1));//0,100,100 ///8
//    	objects.add(new Points(0,xsize*1,-zsize*1));//0,100,-100 ///9
//    	objects.add(new Points(0,-xsize*1,zsize*1));//0,-100,100 ///10
//    	objects.add(new Points(0,-xsize*1,-zsize*1));//0,-100,-100 ///11
//
//    	objects.add(new Points(ysize*10,0,zsize*10));//100,0,100 ///12
//    	objects.add(new Points(ysize*1,0,-zsize*1));//100,0,-100 ///13
//    	objects.add(new Points(-ysize*1,0,zsize*1));//-100,0,100 ///14
//    	objects.add(new Points(-ysize*1,0,-zsize*1));//-100,0,-100 ///15
//
//
//
//    	objects.add(new Points(ysize*1,xsize*1,zsize*1));//100,100,100 ///20
//    	objects.add(new Points(ysize*1,-xsize*1,zsize*1));//100,-100,100 ///21
//
//    	objects.add(new Points(ysize*1,xsize*1,-zsize*1));//100,100,-100 ///22
//    	objects.add(new Points(ysize*1,-xsize*1,-zsize*1));//100,-100,-100 ///23
//
//    	objects.add(new Points(-ysize*1,xsize*1,zsize*1));//-100,100,100 ///24
//    	objects.add(new Points(-ysize*1,-xsize*1,zsize*1));//-100,-100,100 ///25
//
//    	objects.add(new Points(-ysize*1,xsize*1,-zsize*1));//-100,100,-100 ///26
//    	objects.add(new Points(-ysize*1,-xsize*1,-zsize*1));//-100,-100,-100 ///27
//
//		double x=0,y=0,z=0;
//		double x2=0,y2=0,z2=0;
//    	for(double t=-100;t<100;t++)
//    	{
//    		y=10*Math.cos(t);
//    		x=t;
//    		z=10*Math.sin(t);
//    		if(t+1<100)
//    		{
//    			y2=10*Math.cos(t+1);//(int)Math.pow(t+1,2);
//    			x2=t+1;
//    			z2=10*Math.sin(t+1);
//    		}else
//    		{
//    			y2=y;
//    			x2=x;
//    			z2=z;
//    		}		
//    		Line3d f=new Line3d(x,y,z,x2,y2,z2);
//    		f.setColor(Color.red);
//    		objects.add(f);
//    	}
//		double next=.5;//Math.PI/1.5;
//    	for(double t=-100;t<100;t+=next)
//    	{
//    		y=20*Math.cos(t);
//    		x=2*t;//20*Math.tan(t/100);
//    		z=20*Math.sin(t);
//    		if(t+next<100)
//    		{
//    			y2=20*Math.cos(t+next);//(int)Math.pow(t+1,2);
//    			x2=2*(t+next);//20*Math.tan((t+next)/100);
//    			z2=20*Math.sin(t+next);
//    		}else
//    		{
//    			y2=y;
//    			x2=x;
//    			z2=z;
//    		}		
//    		Line3d f=new Line3d(x,y,z,x2,y2,z2);
//    		f.setColor(Color.red);
//    		objects.add(f);
//    	}
	//	objects=new ArrayList<Extendable>();
//		Random q=new Random(123);
//		for(int k=0;k<1;k++)
//		{
//			Arc3d h=new Arc3d(new Points(q.nextInt(200)-100,q.nextInt(200)-100,q.nextInt(200)-100),
//		//	q.nextInt(360),0,
//			  360,360,
//			q.nextInt(100),q.nextInt(100),q.nextInt(100));
//			h.setColor(new Color(q.nextInt(255),q.nextInt(255),q.nextInt(255)));
//			h.setwhich(3);
//		//	q.nextInt(6);
//			objects.add(h);
//		}
    //	objects.add(new Ray3d(new Points(0,0,1),new Vector3d((float)ysize,(float)xsize,(float)zsize)));//-100,-100,-100 ///27
    }
    public void order()
    {

    }
    public staticgraph(double x1,double y1,double z1)
    {
    	xsize=x1;ysize=y1;zsize=z1;
		add();
    }
    public void rotate(double x,double y,double z)
    {
    }
    public void zoomIn()
	{
		if(panz<=-9||panz>0||objects.size()>10000)
		{
			double d=finald-panz;
		//	System.out.print(d+" ");
			d=Math.pow(.999999,(d*d)/5)-.1;
	//		System.out.print(d+" ");
			panz+=d;
	//		System.out.println(panz);
		}else
		{
			double d=finald-panz;
			d=pow(panz/10.,(int)abs(d))+abs(panz)/100;
	//		System.out.print(d+" ");
			panz+=d;
	//		System.out.println(panz);
		}
		objects.get(0).recalculate();
		for(int k=1;k<objects.size();k++)
		{
			objects.get(k).recalculate();
		}		
	}
	public void zoomOut()
	{
		double d=finald-panz;
//		System.out.print(d+" ");
		d=Math.pow(.999999,(d*d)/5)-.1;
//		System.out.println(d);
		panz-=d;
		objects.get(0).recalculate();		
		for(int k=1;k<objects.size();k++)
		{
			objects.get(k).recalculate();
		}
	}
	public void setSize(int t,int y)
	{
		view=new ViewWindow(0,0,t,y,0);
	}
	public void draw(Graphics2D g)
	{

	}
}