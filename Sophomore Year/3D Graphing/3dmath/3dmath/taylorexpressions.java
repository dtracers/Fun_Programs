/**
 * expressionseries.java  3/9/2010
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

public class taylorexpressions extends expressionseries
{
	public static void main(String args[])
	{
		String g="Series .5 then x^2";
		TaylorTree s=(TaylorTree)(new expressiontree().getTree(g));
		s.start=1;
		s.numof=6;
		s.addup();
	}
    public Tree getTree(String g)
	{
		return makeTree(g.substring(6).split("then"));
	}
    public static Tree makeTree(String[] g)
    {
    	TaylorTree s=new TaylorTree();
    	TaylorSeries temp=new TaylorSeries(new expressiontree().getTree("w*(x-"+g[0]+")^n/(n!)"));
    	s.thex=temp;
    	temp.setFunction(new expressiontree().getTree(g[1]));
    	return s;
    }
}