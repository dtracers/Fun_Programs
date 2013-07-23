/**
 * Matrix.java  10/14/2009
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

public class Matrix
{
	int length;
	boolean tempmax=false;
	int minx,maxx,miny,maxy;
	boolean end=true;
	private ArrayList<ArrayList<Pointsrectolinier>> n=new ArrayList<ArrayList<Pointsrectolinier>>();
    public Matrix()
    {
    }
    /**
     *adds the point in the matrix
     */
    public void add(Pointsrectolinier E)
    {
    	add(E,E.graphx,E.graphy);
    }
    /**
     *adds a point at the specified cords
     */
    public void add(Pointsrectolinier E,double x,double y)
    {
    	if(n.size()==0)
    	{
    		ArrayList<Pointsrectolinier> s=new ArrayList<Pointsrectolinier>();
    		n.add(s);
    		s.add(E);
    		length++;
    	}else
    	{
    		ArrayList<Pointsrectolinier> s=get2(y,0,n.size()-1);
    		add(E,s,x);
    	}
    }
    /**
     *adds the given point in the given list in the correct index the 
     *given value would fall into
     */
    public void add(Pointsrectolinier E,ArrayList<Pointsrectolinier> list,double x)
    {
    	if(list.contains(E))
    	{
    		return;
    	}
    	end=false;
		if(list.size()==0)
		{
			list.add(E);
		}else
		{
	    	int l=getspot2(list,x,0,list.size()-1);
	    	if(!end)
	    		list.add(l,E);
	    	else
	    		list.add(E);
		}
    	length++;
    }
    /**
     *gets the point at the cord
     */
    public Pointsrectolinier getPoint(int x,int y)
    {
    	ArrayList<Pointsrectolinier> list=getlist(y);
    	return list.get(x);
    }
    /**
     *gets the point at the cords of the given point
     */
    public Pointsrectolinier get(Pointsrectolinier E)
    {
    	return get(E.graphx,E.graphy);
    }
    /**
     *gets the point at the cords
     */
    public Pointsrectolinier get(double x,double y)
    {
    	ArrayList<Pointsrectolinier> t=get(y,0,n.size()-1);
    	return (get(t,x,0,t.size()-1));
    }
    /**
     *gets the point at the list index like a normal linier list
     */
    public Pointsrectolinier get(int k)
    {
    	int backk=k;
    	int counter=0;
    	while(backk>=0)
    	{
    		k=backk;
    		backk-=n.get(counter).size();
    		counter++;
    	}
    	return n.get(counter-1).get(k);
    }
    /**
     *gets the point at the x point using a recursive binary search
     *if it doesnt exist it returns null
     */
    public Pointsrectolinier get(ArrayList<Pointsrectolinier> list,double x,int bottom,int top)
    {
    	if(bottom==top)
    	{
    		if(list.get(bottom).graphx==x)
    		{
    			return list.get(bottom);
    		}
    		return null;
    	}if(bottom>top)
    	{
    		if(bottom<list.size()&&list.get(bottom).graphx==x)
    		{
    			return list.get(bottom);
    		}
    		if(top<list.size()&&list.get(top).graphx==x)
    		{
    			return list.get(top);
    		}
    		return null;
    	}
    	int l=(bottom+top)/2;
    	double x2=list.get(l).graphx;
    	if(x2==x)
    	{
    		return list.get(l);
    	}else if(x2>x)//go closer to value zero on list
    	{
    		return get(list,x,bottom,l);
    	}else //go closer to value infinity on list
    	{
    		return get(list,x,l+1,top);
    	}
    }
    /**
     *gets the list at the y point using a recursive binary search
     *if it doesnt exist it returns null
     */
    public ArrayList<Pointsrectolinier> get(double y,int bottom,int top)
    {
    	
  //  	System.out.println("y "+y+" b "+bottom+" t "+top);
    	if(bottom==top)
    	{
    		if(n.get(bottom).get(0).graphy==y)
    		{
    			return n.get(bottom);
    		}
    		return null;
    	}if(bottom>top)
    	{
    		if(bottom<n.size()&&n.get(bottom).get(0).graphy==y)
    		{
    			return n.get(bottom);
    		}
    		if(top<n.size()&&n.get(top).get(0).graphy==y)
    		{
    			return n.get(top);
    		}
    		return null;
    	}
    	int l=(bottom+top)/2;
    	double y2=n.get(l).get(0).graphy;
    	//	System.out.println(y2);
    	if(y2==y)
    	{
    		return n.get(l);
    	}   
    	else if(y2>y)//go closer to value zero on list
    	{
    		return get(y,bottom,l);
    	}else //go closer to value infinity on list
    	{
    		return get(y,l+1,top);
    	}
    }
    /**
     *gets the list at the y point using a recursive binary search
     *if it doesnt exist it makes one
     */
    public ArrayList<Pointsrectolinier> get2(double y,int bottom,int top)
    {
    	tempmax=true;
    	end=false;
    	int l=getspot(y,bottom,top);
    	if(!tempmax)
    	{
    		ArrayList<Pointsrectolinier> temp=new ArrayList<Pointsrectolinier>();
    		if(!end)
    			n.add(l,temp);
    		else
    			n.add(temp);	
    		return temp;
    	}else
    	{
    		return n.get(l);
    	}
    }
    /**
     *gets the list at the y point using a recursive binary search
     *if it doesnt exist it makes one and makes t true
     */
    public ArrayList<Pointsrectolinier> get2(double y,int bottom,int top,StringBuffer t)
    {
    	tempmax=true;
    	end=false;
    	int l=getspot(y,bottom,top);
    	if(!tempmax)
    	{
    		t.reverse();
    		ArrayList<Pointsrectolinier> temp=new ArrayList<Pointsrectolinier>();
    		if(!end)
    			n.add(l,temp);
    		else
    			n.add(temp);	
    		return temp;
    	}else
    	{
    		return n.get(l);
    	}
    }
    /**
     *returns the list at the given y value
     */
    public ArrayList<Pointsrectolinier> getlist(int t)
    {
    	return n.get(t);
    }
    /**
     *gets the index at the given y using a binary search
     *if it doesnt exist it will return the spot that it would be added to 
     *using the method ArrayList.add(index,Element)
     */
    private int getspot(double y,int bottom,int top)
    {
    	int l=(bottom+top)/2;
    	double y2=n.get(l).get(0).graphy;
   // 	System.out.println("l: "+l+" Y: "+y2+" y: "+y+" b: "+bottom+" t: "+top);
    	if(y2==y)
    	{
    		return l;
    	}
    	if(bottom==top)
    	{
    		if(y>y2)
    		{
    			end=true;
    		}
    		tempmax=false;
    		return l;
    	}
    	if(y2>y)
    	{
    		return getspot(y,bottom,l);
    	}else
    	{
    		return getspot(y,l+1,top);
    	}
    }
    /**
     *gets the spot at the given index
     */
    private int getspot2(ArrayList<Pointsrectolinier> list,double x,int bottom,int top)
    {
    	int l=(bottom+top)/2;
    	double x2=list.get(l).graphx;
    	if(bottom==top)
    	{
    		if(x>x2)
    		{
    			end=true;
    		}
    		return l;
    	}
    	if(x2>x)
    	{
    		return getspot2(list,x,bottom,l);
    	}else
    	{
    		return getspot2(list,x,l+1,top);
    	}
    }
    /**
     *gets the spot at the given index
     */
    private int getspot3(ArrayList<Pointsrectolinier> list,double x,int bottom,int top)
    {
    	int l=(bottom+top)/2;
    	double x2=list.get(l).graphx;
    	if(bottom==top)
    	{
    		if(x>x2)
    		{
    			end=true;
    		}
    		if(x!=x2)
    		{
    			tempmax=false;
    		}
    		return l;
    	}
    	if(x2>x)
    	{
    		return getspot2(list,x,bottom,l);
    	}else
    	{
    		return getspot2(list,x,l+1,top);
    	}
    }
    /**
     *gets the row index of the given value
     */
    public int getRowIndex(double y)
    {
    	return getspot(y,0,n.size());
    }
    /**
     *gets the column index of the given x value in the given y list
     */
    public int getColumnIndex(double x,double y)
    {
    	int l=getspot(y,0,n.size());
    	ArrayList<Pointsrectolinier> s=n.get(l);    	
    	return l=getspot3(s,x,0,s.size());
    }
    /**
     *gets the index of that point
     */
    public int getIndexof(Pointsrectolinier E)
    {
    	tempmax=true;
    	int counter=0;
    	int l=getspot(E.graphy,0,n.size());
    	if(!tempmax)
    	{
    		return -1;
    	}
    	for(int k=0;k<l;k++)
    	{
    		counter+=n.get(k).size();
    	}
    	ArrayList<Pointsrectolinier> s=n.get(l);
    	tempmax=true;
    	l=getspot3(s,E.graphx,0,s.size());
    	if(!tempmax)
    	{
    		return -1;
    	}
    	counter+=l;
    	return counter;
    }
    /**
     *gets the y value at the index
     */
    public double getY(int y)
    {
    	return getlist(y).get(0).graphy;
    }
    /**
     *gets the x value at the cord
     */
    public double getX(int x,int y)
    {
    	ArrayList<Pointsrectolinier> list=getlist(y);
    	return list.get(x).graphx;
    }
    /**
     *gets the length
     */
    public int length()
    {
    	return length;
    }
    /**
     *gets the length
     */
    public int size()
    {
    	return length;
    }
    /**
     *gets the size of the vertical list
     */
    public int nlength()
    {
    	return n.size();
    }
    /**
     *gets the size of the horizontal list at the vertical index
     */
    public int length(int t)
    {
    	return n.get(t).size();
    }
    /**
     *removes the list at the given index
     */
    public void removelist(int y)
    {
    	length-=length(y);
    	n.remove(y);    	
    }
    /**
     *removes the point at the given index
     */
    public void remove(int k)
    {
    	remove(get(k));
    }
    /**
     *removes that point
     */
    public void remove(Pointsrectolinier E)
    {
    	remove(E.graphx,E.graphy);
    }
    /**
     *removes the point at those cords
     */
    public void remove(double x,double y)
    {
    	ArrayList<Pointsrectolinier> t=get(y,0,n.size()-1);
    	if(t.size()<=1)
    	{
    		n.remove(t);
    		return;
    	}
    	t.remove(get(t,x,0,t.size()-1));
    	length--;    	
    }
    /**
     *really you need one  of these for this
     */
    public String toString()
    {
    	String g="";
    	for(int k=0;k<n.size();k++)
    	{
    		g+="row #: "+k+" ";
    		ArrayList<Pointsrectolinier> temp=n.get(k);
    		for(int q=0;q<temp.size();q++)
    		{
    			g+=temp.get(q)+" ";
    		}
    		g+="\n";
    	}
    	return g;
    }
    /**
     *resets the size if issues
     */
     public int resetSize()
     {
     	int length2=0;
     	for(int k=0;k<n.size();k++)
     	{
     		length2+=n.get(k).size();
     	}
     	return length=length2;
     }
}