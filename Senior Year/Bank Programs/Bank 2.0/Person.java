/**
 * Person.java  1/10/2010
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
import sun.audio.*;

public class Person implements Sorter
{
	private String FName,LName;
	private int order=0;
	private double Total;
	private double initial;
	ArrayList<Tansaction> list;
	MergeSort sort=new MergeSort();
	//for sorting purposes
	int date,de,amount;
	ArrayList<Job> jobs=new ArrayList<Job>();
    public Person()
    {
    }
    public Person(String name,double i)
    {
    	initial=Total=i;
    	String[] n=name.split(" ");
    	FName=n[0];
    	LName=n[n.length-1];
    	list=new ArrayList<Tansaction>();
    }
    public String getName()
    {
    	return FName+" "+LName;
    }
    public String getFirstName()
    {
    	return FName;
    }
    public String getLastName()
    {
    	return LName;
    }
    public double getTotal()
    {
    	return Total;
    }
    public double getInitial()
    {
    	return initial;
    }
    public ArrayList<Tansaction> getList()
    {
    	return list;
    }
    public ArrayList<Tansaction> getPositive()
    {
    	ArrayList<Tansaction> list2=new ArrayList<Tansaction>();
    	for(int k=0;k<list.size();k++)
    	{
    		if(list.get(k).getChange()>0)
    			list2.add(list.get(k));
    	}
    	return list2;
    }
    public void add(Tansaction t)
    {
    	if(list==null)
    	{
    		list=new ArrayList<Tansaction>();
    	}
    	list.add(t);
    	Total+=t.getChange();
    	Collections.sort(list);
    }
    public void add(String d,double change)
    {
    	list.add(new Tansaction(d,change,System.currentTimeMillis()));
    }
    public void add(String d,double change,long s)
    {
    	list.add(new Tansaction(d,change,s));
    }
    public void sortbyDate()
    {
    	if(date%2==0)
    	{
    		sort.sort(0,list);
    	}
    	else
    	{
    		date=-1;
    		sort.sort(1,list);
    	}
    	date++;
    }
    public void sortbyDescription()
    {
    	if(de%2==0)
    	{
    		sort.sort(2,list);
    	}
    	else
    	{
    		de=-1;
    		sort.sort(3,list);
    	}
    	de++;
    }
    public void sortbyAmount()
    {
    	if(amount%2==0)
    	{
    		sort.sort(4,list);
    	}
    	else
    	{
    		amount=-1;
    		sort.sort(5,list);
    	}
    	amount++;
    }
    public int compareTo(Sorter j,int k)
    {
    	Person s=(Person)j;
    	switch(k)
    	{
    		case 0:
    		{
    			return getName().compareTo(s.getName());
    		}
    		case 1:
    		{
    			return -getName().compareTo(s.getName());
    		}
    		case 2:
    		{
    			return getFirstName().compareTo(s.getFirstName());
    		}
    		case 3:
    		{
    			return -getFirstName().compareTo(s.getFirstName());
    		}
    		case 4:
    		{
    			return getLastName().compareTo(s.getLastName());
    		}
    		case 5:
    		{
    			return -getLastName().compareTo(s.getLastName());
    		}
    		case 6:
    		{
    			return new Double(Total).compareTo(new Double(s.getTotal()));
    		}
    		case 7:
    		{
    			return -new Double(Total).compareTo(new Double(s.getTotal()));
    		}
    		default: return 1;
    	}
    }
    public String toString()
    {
    	return FName+" "+LName+" "+getTotal2();
    }
    public String toString2()
    { 
    	return FName+" "+LName;
    }
    public double getTotal2()
    {
    	double t2=initial;
    	for(int k=0;k<list.size();k++)
    	{
    		t2+=list.get(k).getChange();
    	}
    	return Total=t2;
    }
    public double getTotal2(int end)
    {
    	double t2=initial;
    	end=Math.min(list.size(),end);
    	for(int k=0;k<end;k++)
    	{
    		t2+=list.get(k).getChange();
    	}
    	return t2;
    }
    public void setTotal(double d)
    {
    	Total=d;
    }
    public void addJob(Job j)
    {
    	jobs.add(j);
    }
    public void clearJobs()
    {
    	jobs=new ArrayList<Job>();
    }
    public void removeJob(Job j)
    {
    	jobs.remove(j);
    }
    public void ApplyJob()
    {
    	for(int k=0;k<jobs.size();k++)
    	{
    		Job j=jobs.get(k);
    		list.add(new Tansaction(j.getDescript(),j.getChange(),j.getTimeMillis()));
    	}
    }
    public void ApplyJob(Date d)
    {
    	for(int k=0;k<jobs.size();k++)
    	{
    		Job j=jobs.get(k);
    		list.add(new Tansaction(j.getDescript(),j.getChange(),d.getTime()));
    	}
    }
    public void stateChanged(Bank b2)
    {
    	getTotal2();
    	print p=new print();
    	p.setfilewrite(StaticDirectory.Directory+"\\"+getName()+b2.name);
    	p.println(""+getInitial());
    	for(int k=0;k<list.size();k++)
    	{
    		p.println(""+list.get(k));
    	}
    	p.println("Job");
    	for(int k=0;k<jobs.size();k++)
    	{
    		System.out.println(jobs.get(k));
    		p.println(""+jobs.get(k));
    	}
    }
}