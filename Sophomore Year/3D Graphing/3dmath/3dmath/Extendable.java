/**
 * Extendable.java  7/27/2008
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

public class Extendable extends variables implements objectproperties,Comparable
{
	Color t=Color.black;
	boolean onscreen=true;
    public void rotate(double x,double y,double z)
    {
    }
    public void rotateX(transform xform)
    {
    }
    public void rotateY(transform xform)
    {
    }
    public void rotateZ(transform xform)
    {
    }
    public void moveUp(transform xform)
    {
    }
    public void moveDown(transform xform)
    {
    }
    public void moveLeft(transform xform)
    {
    }
    public void moveRight(transform xform)
    {
    }
	public void zoomOut()
	{
	}
	public void goback()
    {
    }
	public void zoomIn()
	{
	}
	public void draw(Graphics2D g)
	{
	}
	public void recalculate()
	{
	}
	public ArrayList copy(ArrayList t)
	{
		return t;
	}
	public void setColor(Color y)
	{
		t=y;
	}
	public Color getColor()
	{
		return t;
	}
    public Extendable()
    {
    }
    public int compareTo(Object j)
    {
    	return 0;
    }
}