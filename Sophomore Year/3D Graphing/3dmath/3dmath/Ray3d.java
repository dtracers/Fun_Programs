/**
 * Ray3d.java  7/27/2008
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

public class Ray3d extends singular
{
	Vector3d v;
	Points p;
    public Ray3d()
    {
    	this(new Points(),new Vector3d());
    	setColor(Color.green);
    }
    public Ray3d(Points p,Vector3d v)
    {
    	setColor(Color.green);
    	setTo(p,v);
    }
    public Ray3d(double x,double y,double z,float x2,float y2,float z2)
    {
    	setColor(Color.green);
    	setTo(x,y,z,x2,y2,z2);
    }
    public Ray3d(Points p,float x,float y,float z)
    {
    	setColor(Color.green);
    	setTo(p.x,p.y,p.z,x,y,z);
    }
    public Ray3d(double x,double y,double z,Vector3d p)
    {
    	setColor(Color.green);
    	setTo(x,y,z,p.x,p.y,p.z);
    }
    public void goback()
    {
    	p.goback();
    	v.goback();
    }
    public void rotateX(transform xform)
    {
    	p.rotateX(xform);
    	v.rotateX(xform);
    }
    public void rotateY(transform xform)
    {
    	p.rotateY(xform);
    	v.rotateY(xform);
    }
    public void rotateZ(transform xform)
    {
    	p.rotateY(xform);
    	v.rotateY(xform);
    }
    public void setTo(double x,double y,double z,float x2,float y2,float z2)
    {
    	p=new Points(x,y,z);
    	v=new Vector3d(x2,y2,z2);
    }
    public void setpicz(double z1,float z2)
    {
    	p.z=z1;v.z=z2;
    }
    public void setTo(Points p,Vector3d v)
    {
    	setTo(p.x,p.y,p.z,v.x,v.y,v.z);
    }
    public boolean equals(Object obj)
    {
    	Ray3d r=(Ray3d)obj;
    	return r.v.equals(v)&&r.p.equals(p);
    }
    public double getdistance0()
	{
		Points temp=Points.change(v);
		temp.add(p);
		return Math.sqrt(temp.x*temp.x+temp.y*temp.y+temp.z*temp.z);
	}
    public boolean equals(double px,double py, double pz,float vx,float vy, float vz)
    {
    	return(vx==v.x&&vy==v.y&&vz==v.z)&&(px==p.x&&py==p.y&&pz==p.z);
    }
    public void draw(Graphics2D g)
    {
    	Points z=Points.change(v);z.add(p);
    	g.setColor(Color.green);
    	g.drawLine((int)Math.round(p.getfakex()),(int)Math.round(p.getfakey()),(int)Math.round(z.getfakex()),(int)Math.round(z.getfakey()));
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
}