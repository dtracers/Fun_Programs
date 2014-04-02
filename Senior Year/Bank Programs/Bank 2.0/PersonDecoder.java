/**
 * PersonDecoder.java  1/10/2010
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

public class PersonDecoder
{ 
    public static Person getPerson(String line)
    {
    	String[] linesep=line.split(" ");
    	Person p=new Person(linesep[0]+" "+linesep[1],Double.parseDouble(linesep[2]));
    	return p;
    }
    public static Person getCompletePerson(String line,String name)
    {
    	String[] linesep=line.split(" ");
    	print p2=new print();
    	p2.setfile(StaticDirectory.Directory+"\\"+linesep[0]+" "+linesep[1]+name);
    	String g=p2.readline();
    	Person p=new Person(linesep[0]+" "+linesep[1],0);
    	g=p2.readline();
    	while(g!=null&&!g.equals("Job")&&!g.equals(""))
    	{
    		p.add(getTransaction(g));
    		g=p2.readline();
    	}
    	if(g!=null&&!g.equals(""))
    	{
	    	g=p2.readline();
	    	while(g!=null&&!g.equals(""))
	    	{
	    		p.addJob(new Job(getTransaction(g)));
	    		g=p2.readline();
	    	}
    	}
    	return p;
    }
    public static Tansaction getTransaction(String line)
    {
    	String[] linesep=line.split("`");
    	Tansaction t=new Tansaction(linesep[0],Double.parseDouble(linesep[1]),Long.parseLong(linesep[2]));
    	return t;
    }
    public static Bank getBank(print p,String g2)
    {
    	Bank b=new Bank(g2);
    	String g=p.readline();
    	while(g!=null&&!g.equals(""))
    	{
    		b.add(getPerson(g));
    		g=p.readline();
    	}
    	return b;
    }
}