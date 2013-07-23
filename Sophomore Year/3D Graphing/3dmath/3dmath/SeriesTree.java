/**
 * SeriesTree.java  3/9/2010
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

public class SeriesTree extends Tree
{
	seriestree2 thex;
	Tree nexta;
    public SeriesTree()
    {
    }
    int numof=0;
    int start=0;
    boolean treemade=false;
    public void setSeries(int g)
    {
    	numof=g;
    }
    public SeriesTree(boolean h,String g)//makes a tree with a variable
	{
		super(h,g);
	}
	public SeriesTree(String g)//makes a tree with a symbol
	{
		super(g);
	}
	public SeriesTree(double u)//makes a tree with a constant
	{
		super(u);
	}
	public void addup()
	{
		String g="";
		for(int k=start;k<=numof;k++)
		{
			g+="("+thex.getString(k)+")";
		}
		System.out.println(g);
		nexta=new expressiontree().getTree(g);
	}
	public void setLeft(Tree t)
	{
		nexta.setLeft(t);
	}
	public void setRight(Tree t)
	{
		nexta.setRight(t);
	}
	public String toString2()
	{
		String y="";
		if(left!=null)
		{
			if(left.isGreaterLength(1))
			{
				y+=parent(left.toString2());
			}
			else
			{
				y+=left.toString2();
			}
		}
		y+=""+this;
		if(right!=null)
		{
			if(right.isGreaterLength(1))
			{
				y+=parent(right.toString2());
			}
			else
			{
				y+=right.toString2();
			}
		}
		return y;
	}
	public boolean isGreaterLength(int k)//this checks to see if the length of the tree is grater than k
	{
		return nexta.isGreaterLength(k);
	}
	public String toString()
	{
		return "Series"+thex.toString2();
	}
	public boolean set(String y,double g)
	{
		return nexta.set(y,g);
	}
	public double getnum()//returns a number
	{
		return nexta.getnum();
	}
	public boolean set(Tree tree, String s, double d)
	{
		return nexta.set(tree,s,d);
	}
	public Tree copy()
	{
		return nexta.copy();
	}
	public boolean equals(Object obj)
	{
		return equals((Tree)obj, 0);
	}
	public boolean equals(Tree tree, int i)
	{
		if(tree instanceof SeriesTree)
			return nexta.equals(((SeriesTree)tree).nexta,i);
		return nexta.equals(tree,i);
	}
	private String parent(Tree tree)
	{
		return "("+tree.toString2()+")";
	}
	private String parent(String s)
	{
		return "("+s+")";
	}
	public void reset()
	{
		nexta.reset();
	}
	public void reset(boolean h,String g)//resets a tree with a variable
	{
		nexta.reset(h,g);
	}
	public void reset(String g)//resets a tree with a symbol
	{
		nexta.reset(g);
	}
	public void reset(double u)//resets a tree with a constant
	{
		nexta.reset(u);
	}
	public void reset(Tree g)//resets a tree with a constant
	{
		nexta.reset(g);
	}	
}
class seriestree2 extends Tree
{
	public seriestree2(Tree g)
	{
		if(g.left!=null)			
		left=new seriestree2(g.left);
		if(g.right!=null)			
		right=new seriestree2(g.right);
		var=g.var;
		ex=g.ex;
		v=g.v;
		v2=g.v2;
		t=g.t;
		change=g.change;
		setyet=g.setyet;
	}
	int next=0;
	public String getString(int y)
	{
		next=y;
		return this.toString2();
	}
	public String toString2()
	{
		if(left!=null)
		{
			((seriestree2)left).next=this.next;
		}
		if(right!=null)
		{
			((seriestree2)right).next=this.next;
		}
		return super.toString2();
	}
	public String toString()
	{
		System.out.println(next);
		if(var.equalsIgnoreCase("n"))
		{
			System.out.println(next);
			return ""+next;
		}
		return super.toString();
	}
}
