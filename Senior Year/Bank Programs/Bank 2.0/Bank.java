/**
 * Bank.java  1/10/2010
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

public class Bank
{
	String name;
	ArrayList<Person> list;
	MergeSort sort=new MergeSort();
	//for sorting purposes
	int n,f,l,t;
    public Bank(String g)
    {
		name=g;
		list=new ArrayList<Person>();
    }
    public void add(Person s)
    {
    	list.add(s);
    }
    public void add(String name,double i)
    {
    	list.add(new Person(name,i));
    }
    public void add(String name)
    {
    	list.add(new Person(name,0));
    }
    public void sortbyName()
    {
    	if(n%2==0)
    	{
    		sort.sort(0,list);
    	}
    	else
    	{
    		n=-1;
    		sort.sort(1,list);
    	}
    	n++;
    }
    public void sortbyFirstName()
    {
    	if(f%2==0)
    	{
    		sort.sort(2,list);
    	}
    	else
    	{
    		f=-1;
    		sort.sort(3,list);
    	}
    	f++;
    }
    public void sortbyLastName()
    {
    	if(l%2==0)
    	{
    		sort.sort(4,list);
    	}
    	else
    	{
    		l=-1;
    		sort.sort(5,list);
    	}
    	l++;
    }
    public void sortbyTotal()
    {
    	if(t%2==0)
    	{
    		sort.sort(6,list);
    	}
    	else
    	{
    		t=-1;
    		sort.sort(7,list);
    	}
    	t++;
    }
    public String getName()
    {
    	return "Bank Period "+name;
    }
}