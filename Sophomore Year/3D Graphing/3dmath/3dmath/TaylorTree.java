/**
 * TaylorTree.java  3/9/2010
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

public class TaylorTree extends SeriesTree
{
    public TaylorTree()
    {
    }
    public TaylorTree(boolean h,String g)//makes a tree with a variable
	{
		super(h,g);
	}
	public TaylorTree(String g)//makes a tree with a symbol
	{
		super(g);
	}
	public TaylorTree(double u)//makes a tree with a constant
	{
		super(u);
	}
}
class TaylorSeries extends seriestree2
{
	double value=0;
	double value2=0;
	ArrayList<Tree> trees=new ArrayList<Tree>();
	public TaylorSeries(Tree g)
	{
		super(g);
		if(g.left!=null)			
		left=new TaylorSeries(g.left);
		if(g.right!=null)			
		right=new TaylorSeries(g.right);
	}	
	public void setFunction(Tree g)
	{
		trees=new ArrayList<Tree>();
		trees.add(g);
	}
	int next=0;
	public String getString(int y)
	{
		next=y;
		Tree x=getderive(y);
		x.set("x",value);
		value2=x.getnum();
		return this.toString2();
	}
	public Tree getderive(int y)
	{
		if(trees.size()<y)
		{
			Tree x=getderive(y-1);
			derivative d=new derivative(x);
			x=new expressiontree().getTree(d.cleanDerive());
			trees.add(x);
			return x;
		}
		return trees.get(y-1);
	}
	public String toString2()
	{
		if(left!=null)
		{
			((TaylorSeries)left).next=this.next;
			((TaylorSeries)left).value=this.value;
			((TaylorSeries)left).value2=this.value2;
		}
		if(right!=null)
		{
			((TaylorSeries)right).next=this.next;
			((TaylorSeries)right).value=this.value;
			((TaylorSeries)right).value2=this.value2;
		}
		return super.toString2();
	}
	public String toString()
	{
		if(var.equalsIgnoreCase("n"))
		{
			return ""+next;
		}
		if(var.equalsIgnoreCase("w"))
		{
			return ""+value2;
		}
		return super.toString();
	}
}