/**
 * piecewisetree.java  9/19/2009
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

public class piecewisetree extends expressiontree
{
    public Tree getTree(String g)
	{
		return makeTree(g);
	}
    public static Tree makeTree(String g)
    {
    	String[] pieces2=g.split(";");
    	boolean donesir=false;
    	Tree[] list;
    	conditional[] conditions;
    	if(pieces2.length==0)
    	{
    		donesir=true;
    	}
    	if(donesir)
    	{
	    	return expressiontree.makeTree(g);
    	}else
    	{
    		String[] pieces=g.split(";");
	    	list=new Tree[pieces.length/2+((pieces.length%2==1)?1:0)];
	    	conditions=new conditional[pieces.length/2];
	    	for(int k=0;k<pieces.length;k++)
	    	{
	    		if(k%2==0)
	    			list[k/2]=new expressiontree().getTree(pieces[k]);
	    		else
	    		{
	    			System.out.println(conditions.length+" l "+k);
	    			conditions[k/2]=makeConditional(pieces[k]);
	    		}
	    	}
	    	return new PieceTree(list,conditions);
    	}

    }
    public static conditional makeConditional(String g)
    {
    	return new conditional(new expressiontree().getTree(g));
    }
}
class PieceTree extends Tree
{
	public PieceTree(Tree[] l,conditional[] c)
	{
		trees=l;conditions=c;
	}
	public boolean set(String g,double x)
	{
		for(int k=0;k<trees.length;k++)
		{
			trees[k].set(g,x);
			if(k<conditions.length)
			{
				conditions[k].set(g,x);
			}
		}
		return true;
	}
	public double getnum()
	{
		for(int k=0;k<trees.length;k++)
		{
			if(k<conditions.length)
			{
				if(conditions[k].getnum())
				{
					return trees[k].getnum();
				}
			}else
			{
				return trees[k].getnum();
			}
		}
		return 1/0.;
	}
	Tree[] trees;
	conditional[] conditions;
	public String toString2()
	{
		String y="";
		for(int k=0;k<trees.length;k++)
		{
			y+=trees[k].toString2()+";";
			if(k<conditions.length)
			{
				y+=conditions[k].toString2()+";";
			}
		}
		return y;
	}
}
class conditional
{
	public conditional(Tree t)
	{
		condition=t;
	}
	Tree condition;
	boolean c=false,do1=false;
	public boolean set(String g,double x)
	{
		condition.set(g,x);
		do1=true;
		return c=getnum();
	}
	public boolean getnum()
	{
		if(do1)
		{
			do1=false;
			return condition.getnum()==1;
		}
		else
		{
			return c;
		}
	}
	public String toString2()
	{
		return " if "+condition.toString2();
	}
	public String toString3()
	{
		return condition.toString2();
	}
}

class NoPointException extends Exception
{
	public NoPointException()
	{

	}
	public NoPointException(String message)
    {
        super(message);
    }
    public String toString()
    {
    	return super.toString()+"Point does not exist";
    }
}