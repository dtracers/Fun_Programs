/**
 * absolute.java  10/18/2009
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

public class absolute
{
	loadpic c;
    public absolute(boolean t)
    {
    	if(!t)
    		c=new loadplane("object1.dat");
    	else
    		c=new loadpic("object1.dat");
    }
    public loadpic getpic()
    {
    	return c;
    }
}
class absoluteequation
{
	equationset c;
    public absoluteequation(String g)
    {
    	System.out.println(g);
    	char g2=g.charAt(g.length()-1);
    	if(g2=='q')
    		c=new equationset(g);
    	else
    		c=new equationplane(g);
    }
    public equationset geteq()
    {
    	return c;
    }
}