/**
 * Vector3d.java  7/27/2008
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

public class Vector3d extends singular
{
	private float fx2,fy2,fz2;
	public float x;
	public float y;
	public float z;
	//float zpic=0;
	public float fx;
	public float fy;
    public Vector3d()
    {
    	this(0,0,0);
    	fx2=x;fy2=y;fz2=z;
    	setColor(Color.red);
    }
    public Vector3d(Vector3d v)
    {
    	this(v.x,v.y,v.z);
    	fx2=x;fy2=y;fz2=z;
    	setColor(Color.red);
    }
    public Vector3d(float x,float y,float z)
    {
    	setColor(Color.red);
    	setTo(x,y,z);
    	fx2=x;fy2=y;fz2=z;
    }
    public void setTo(float x,float y,float z)
    {
    	this.x=x;this.y=y;this.z=z;//zpic=z+(float)finald;
    	recalculate();
    }
    public void rotateX(transform xform)
    {
    	rotateX((float)xform.getCosAngleX(),(float)xform.getSinAngleX());
    	recalculate();
    }
    public void rotateY(transform xform)
    {
    	rotateY((float)xform.getCosAngleY(),(float)xform.getSinAngleY());
    	recalculate();
    }
    public void rotateZ(transform xform)
    {
    	rotateZ((float)xform.getCosAngleZ(),(float)xform.getSinAngleZ());
    	recalculate();
    }
    public void goback()
    {
    	x=fx2;y=fy2;z=fz2;
    	recalculate();
    }
    public float getDotProduct(Vector3d p)
    {
    	return x*p.x+y*p.y+z*p.z;
    }
    public void setToCrossProduct(Vector3d u,Vector3d v)
    {
    	float x=u.y*v.z-u.z*v.y;
    	float y=u.z*v.x-u.x*v.z;
    	float z=u.x*v.y-u.y*v.x;
    	this.x=x;this.y=y;this.z=z;
    }
    public void setTo(Vector3d v)
    {
    	setTo(v.x,v.y,v.z);
    }
    public void transfer(Points v)
    {
    	setTo((float)v.x,(float)v.y,(float)v.z);
    }
    public static Vector3d change(Points v)
    {
    	return new Vector3d((float)v.x,(float)v.y,(float)v.z);
    }
    public static Vector3d addpoints(Vector3d p1,Vector3d p2)
    {
    	Vector3d temp=new Vector3d();
    	temp.setTo(p1);
    	temp.add(p2);
    	return temp;
    }
    public boolean equals(Object obj)
    {
    	Vector3d v=(Vector3d)obj;
    	return equals(v.x,v.y,v.z);
    }
    public boolean equals(float vx,float vy, float vz)
    {
    	return(vx==x&&vy==y&&vz==z);
    }
    public void add(float x2,float y2,float z2)
    {
    	setTo(x+x2,y+y2,z+z2);
    }
    public void add(Vector3d v)
    {
    	add(v.x,v.y,v.z);
    }
    public void subtract(float x,float y,float z)
    {
    	add(-x,-y,-z);
    }
    public void subtract(Vector3d v)
    {
    	add(-v.x,-v.y,-v.z);
    }
    public void multiply(float x2)
    {
    	setTo(x*x2,y*x2,z*x2);
    }
    public void divide(float x2)
    {
    	setTo(x/x2,y/x2,z/x2);
    }
    public float length()
    {
    	return (float)Math.sqrt(x*x+y*y+z*z);
    }
    public double getdistance0()
	{
		return Math.sqrt(x*x+y*y+z*z);
	}
    public void normalize()
    {
    	divide(length());
    }
    public String toString()
    {
    	return "("+x+","+y+","+z+") vector";
    }
    public void zoomOut()
    {
//    	zpic++;
    	recalculate();
    }
    public void zoomIn()
    {
    //	zpic--;
    	recalculate();
    }
    public void draw(Graphics2D g)
    {
		g.setColor(t);
    }
    public int getfakex()
    {
    	return (int)Math.round(fx);
    }
    public int getfakey()
    {
    	return(int)Math.round(fy);
    }
    public void recalculate()
    {
    	fx=(float)win2screen.convertx((double)x,(double)z);
    	fy=(float)win2screen.converty((double)y,(double)z);
    }
    public void rotateX(float cosAngle,float sinAngle)
    {
    	float newY=y*cosAngle-z*sinAngle;
    	float newZ=y*sinAngle+z*cosAngle;
    	y=newY;
    	z=newZ;
    }
    public void rotateY(float cosAngle,float sinAngle)
    {
    	float newZ=z*cosAngle-x*sinAngle;
    	float newX=z*sinAngle+x*cosAngle;
    	x=newX;
    	z=newZ;
    }
    public void rotateZ(float cosAngle,float sinAngle)
    {
    	float newX=x*cosAngle-y*sinAngle;
    	float newY=x*sinAngle+y*cosAngle;
    	x=newX;
    	y=newY;
    }
    //rotated then tranlated
    public void add(transform xform)
    {
    	addRotation(xform);
    	add(xform.getLoc());
    }
    public void subtract(transform xform)
    {
    	subtract(xform.getLoc());
    	subtractRotation(xform);
    }
    public void addRotation(transform xform)
    {
   		rotateX((float)xform.getCosAngleX(),(float)xform.getSinAngleX());
   		rotateY((float)xform.getCosAngleY(),(float)xform.getSinAngleY());
   		rotateZ((float)xform.getCosAngleZ(),(float)xform.getSinAngleZ());
    }
    public void subtractRotation(transform xform)
    {
    	//note that sin(-x) == -sin(x)  and  cos(-x) == cos(x)
    	rotateZ((float)xform.getCosAngleZ(),(float)-xform.getSinAngleZ());
   		rotateY((float)xform.getCosAngleY(),(float)-xform.getSinAngleY());
   		rotateX((float)xform.getCosAngleX(),(float)-xform.getSinAngleX());
    //	rotateX();
    }
}