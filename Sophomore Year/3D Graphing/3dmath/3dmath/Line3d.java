/**
 * Line3d.java  7/27/2008
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

public class Line3d extends singular
{
    Points v;
	Points p;
    public Line3d()
    {
    	this(new Points(),new Points());
    	setColor(Color.blue);
    }
    public Line3d(Points p,Points v)
    {
    	setColor(Color.blue);
    	setTo(p,v);
    }
    public Line3d(double x,double y,double z,double x2,double y2,double z2)
    {
    	setColor(Color.blue);
    	setTo(x,y,z,x2,y2,z2);
    }
    public Line3d(Points p,double x,double y,double z)
    {
    	setColor(Color.blue);
    	setTo(p.x,p.y,p.z,x,y,z);
    }
    public Line3d(double x,double y,double z,Points p)
    {
    	setColor(Color.blue);
    	setTo(x,y,z,p.x,p.y,p.z);
    }
    public void setTo(double x,double y,double z,double x2,double y2,double z2)
    {
    	p=new Points(x,y,z);
    	v=new Points(x2,y2,z2);
    }
    public void setTo(Points p,Points v)
    {
    	setTo(p.x,p.y,p.z,v.x,v.y,v.z);
    }
    public void setpicz(double z1,double z2)
    {
    	p.z=z1;v.z=z2;
    	recalculate();
    }
    public boolean equals(Object obj)
    {
    	Line3d r=(Line3d)obj;
    	return r.v.equals(v)&&r.p.equals(p);
    }
    public void rotateX(transform xform)
    {
    	p.rotateX(xform);
    	v.rotateX(xform);
    }
    public void goback()
    {
    	p.goback();
    	v.goback();
    }
    public void rotateY(transform xform)
    {
    	p.rotateY(xform);
    	v.rotateY(xform);
    }
    public void rotateZ(transform xform)
    {
    	p.rotateZ(xform);
    	v.rotateZ(xform);
    }
    public double getdistance0()
	{
		Points temp=new Points(v.x,v.y,v.z);
		temp.add(p);
		return Math.sqrt(temp.x*temp.x+temp.y*temp.y+temp.z*temp.z);
	}
    public boolean equals(double px,double py, double pz,double vx,double vy, double vz)
    {
    	return(vx==v.x&&vy==v.y&&vz==v.z)&&(px==p.x&&py==p.y&&pz==p.z);
    }
    public void draw(Graphics2D g)
    {
    //	System.out.println(t);
    	g.setColor(t);
   // 	System.out.println("location"+p.getfakex()+" "+p.getfakey()+" "+v.getfakex()+" "+v.getfakey());
    	if(p.z+panz<=0&&v.z+panz<=0)
    	g.drawLine(p.getfakex(),p.getfakey(),v.getfakex(),v.getfakey());
    }
    public void setColor(Color r)
    {
    	t=r;
    }
    public void zoomOut()
    {
    	p.zoomOut();
    	v.zoomOut();
    }
    public void zoomIn()
    {
    	p.zoomIn();
    	v.zoomIn();
    }
    public void recalculate()
    {
    	p.recalculate();
    	v.recalculate();
    }
    public Points getv()
    {
    	return v;
    }
    public Points getp()
    {
    	return p;
    }
}