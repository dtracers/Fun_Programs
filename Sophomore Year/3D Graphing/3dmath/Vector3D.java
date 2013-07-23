/**
 * Vector3D.java  7/27/2008
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

public class Vector3D implements Transformable
{
	public float x;
	public float y;
	public float z;
    public Vector3D()
    {
    	this(0,0,0);
    }
    public Vector3D(Vector3D v)
    {
    	this(v.x,v.y,v.z);
    }
    public Vector3D(float x,float y,float z)
    {
    	setTo(x,y,z);
    }
    public void setTo(float x,float y,float z)
    {
    	this.x=x;this.y=y;this.z=z;
    }
    public void setTo(Vector3D v)
    {
    	setTo(v.x,v.y,v.z);
    }
    public boolean equals(Object obj)
    {
    	Vector3D v=(Vector3D)obj;
    	return equals(v.x,v.y,v.z);
    }
    public boolean equals(float vx,float vy, float vz)
    {
    	return(vx==x&&vy==y&&vz==z);
    }
    
}