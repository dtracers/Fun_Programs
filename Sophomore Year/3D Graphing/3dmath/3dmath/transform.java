/**
 * transform.java  7/28/2008
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

public class transform extends variables
{
	protected Points location;
	protected Vector3d loc;
	private double cosAngleX;
	private double sinAngleX;
	private double cosAngleY;
	private double sinAngleY;
	private double cosAngleZ;
	private double sinAngleZ;
	public transform()
	{
		this(0,0,0);
	}
	public transform(Polygon3d poly)
	{

	}
	public transform(double x,double y,double z)
	{
		location=new Points(x,y,z);
		loc=Vector3d.change(location);
	}
	public transform(float x,float y,float z)
	{
		loc=new Vector3d(x,y,z);
		location=Points.change(loc);
	}
	public Object clone()
	{
		return new transform(this);
	}
	public transform(transform v)
	{
		loc=new Vector3d();
		location=new Points();
		setTo(v);
	}
	public void setTo(transform v)
	{
		location.setTo(v.location);
		loc.setTo(v.loc);
		this.cosAngleX=v.cosAngleX;
		this.sinAngleX=v.sinAngleX;
		this.cosAngleX=v.cosAngleY;
		this.sinAngleX=v.sinAngleY;
		this.cosAngleX=v.cosAngleZ;
		this.sinAngleX=v.sinAngleZ;
	}
	public Points getLocation()
	{
		return location;
	}
	public Vector3d getLoc()
	{
		return loc;
	}
	public double getCosAngleX()
	{
		return cosAngleX;
	}
	public double getSinAngleX()
	{
		return sinAngleX;
	}
	public double getCosAngleY()
	{
		return cosAngleY;
	}
	public double getSinAngleY()
	{
		return sinAngleY;
	}
	public double getCosAngleZ()
	{
		return cosAngleZ;
	}
	public double getSinAngleZ()
	{
		return sinAngleZ;
	}
	public double getAngleX()
	{
		return Math.atan2(sinAngleX,cosAngleX);
	}
	public double getAngleY()
	{
		return Math.atan2(sinAngleY,cosAngleY);
	}
	public double getAngleZ()
	{
		return Math.atan2(sinAngleZ,cosAngleZ);
	}
	public void setAngleX(double x)
	{
		sinAngleX=Math.sin(x);
		cosAngleX=Math.cos(x);
	}
	public void setAngleY(double x)
	{
		sinAngleY=Math.sin(x);
		cosAngleY=Math.cos(x);
	}
	public void setAngleZ(double x)
	{
		sinAngleZ=Math.sin(x);
		cosAngleZ=Math.cos(x);
	}
	public void setAngle(double x,double y,double z)
	{
		setAngleX(x);
		setAngleY(y);
		setAngleZ(z);
	}
	public void rotateAngleX(double x)
	{
		if(x!=0)
		{
			setAngleX(getAngleX()+x);
		}
	}
	public void rotateAngleY(double x)
	{
		if(x!=0)
		{
			setAngleY(getAngleY()+x);
		}
	}
	public void rotateAngleZ(double x)
	{
		if(x!=0)
		{
			setAngleZ(getAngleZ()+x);
		}
	}
	public void rotateAngle(double x,double y,double z)
	{
		rotateAngleZ(z);
		rotateAngleY(y);
		rotateAngleZ(x);
	}
}