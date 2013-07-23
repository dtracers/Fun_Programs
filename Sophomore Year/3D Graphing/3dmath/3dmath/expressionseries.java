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

public class expressionseries extends expressiontree
{
	public static void main(String args[])
	{
		String g="Series x^n/(n!)";
		SeriesTree s=(SeriesTree)(new expressiontree().getTree(g));
		s.start=1;
		s.numof=6;
		s.addup();
	}
    public Tree getTree(String g)
	{
		return makeTree(g.substring(6));
	}
    public static Tree makeTree(String g)
    {
    	SeriesTree s=new SeriesTree();
    	s.thex=new seriestree2(new expressiontree().getTree(g));
    	return s;
    }
}