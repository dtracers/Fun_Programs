/**
 * Polys.java  7/28/2008
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

public class Polys extends Extendable
{
	int which=5;
	boolean points=false;
	public static final int DRAW=1;
	public static final int FILL=2;
	public static final int DRAWVERTS=3;
	public static final int FILLVERTS=4;
	public Polys(int w2)
	{
		which=w2;
	}
	public Polys(Polys t)
	{
		which=t.which;
		points=t.points;
	}
	public void addPoint(Points p)
	{

	}
	public void setTo(Polys g)
	{
	}
	public void set(boolean t)
	{
		points=t;
	}
	public Polys()
	{
		which=0;
	}
	public void set(int t)
	{
		which=t;
	}
	public Polys newPolys()
	{
		Polys y=new Polys();
		y.which=which;
		y.points=points;
		return y;
	}
	public void draw(Graphics2D g)
	{
		switch(which)
		{
			case 1:draw1(g);break;
			case 3:fill(g);break;
			case 2:drawVerts(g);break;
			case 4:fillVerts(g);break;
			case 5:fill2(g);break;
			default: draw1(g);
		}
	}
    public void draw1(Graphics2D g)
	{

	}
	public void fill(Graphics2D g)
	{
		draw1(g);
	}
	public void fill2(Graphics2D g)
	{
		draw1(g);
	}
	public void drawVerts(Graphics2D g)
	{
		draw1(g);
	}
	public void fillVerts(Graphics2D g)
	{
		draw1(g);
	}
}