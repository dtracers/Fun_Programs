/**
 * main.java  8/29/2008
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
import java.awt.*;
import java.awt.event.*;
public class main
{
    public static void main(String[] args)
    {
    	Lab24ast.main(args);
    }
    
}

// Lab24ast.java
// This is the student version of the lab 24a assignment.

class Lab24ast
{
	public static void main(String args[])
	{
		GfxApp gfx = new GfxApp();
		gfx.setSize(900,700);
		gfx.addWindowListener(new WindowAdapter() {public void
		windowClosing(WindowEvent e) {System.exit(0);}});
		gfx.makepolygons();
		gfx.show();
	}

}



class GfxApp extends Frame
{
	public void paint(Graphics g) 
	{     
		gfx.draw((Graphics2D)g);
	}
	graph gfx;
	public void makepolygons()
	{
		gfx = new graph();
		gfx.setSize(900,700);
		Polygon3d temp=new Polygon3d();
		int centerX = 400;
		int centerY = 300;
		int radius = 150;
		int points=8;
		double twoPI = 2 * Math.PI;
		for(int k=0;k<points;k++)
		{
			int x = (int) Math.round(Math.cos(twoPI * k/points) * radius) + centerX;
			int y = (int) Math.round(Math.sin(twoPI * k/points) * radius) + centerY;
			temp.add((new Points(x,y,0)));
		}
		gfx.add(temp);
	}
}
