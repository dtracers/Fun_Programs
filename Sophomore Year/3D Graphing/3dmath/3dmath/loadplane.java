/**
 * loadplane.java  10/16/2009
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

public class loadplane extends loadpic
{
	graph gr=new graphplane();
	{
		gr.second=super.gr.second;
	}
    public loadplane()
    {
    }
    public loadplane(String g)
    {
    	super(g);
    }
    public graph getgraph()
	{
		return gr;
	}
	public void draw(Graphics g)
	{
		gr.drawgraph((Graphics2D)g);
	}
	public graph getgraph(equationset c,double ct,double cm)
    {
    	gr.setformloc(new Points(-100,100,-100));
    	gr.init(c.getX(),"x",c.getY(),"y",c.getZ(),"z",ct,cm);
    	return gr;
    }
}