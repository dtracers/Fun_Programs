/**
 * binarytester.java  10/16/2009
 *
 * @author - Jane Doe
 * @author - Period n
 * @author - Id nnnnnnn
 *
 * @author - I received help from ...
 *
 */

import java.util.*;

public class binarytester
{
	static ArrayList<Double> l;
    public static void main(String[] args)
    {
    	l=new ArrayList<Double>();
    	for(int k=0;k<10;k++)
    	{
    		l.add(new Double(k));
    	}
    	int y=getspot2(l,.5,0,l.size()-1);
    	System.out.println(y);
    	l.add(y,new Double(.5));
    	y=getspot2(l,4.5,0,l.size()-1);
    	System.out.println(y);
    	l.add(y,new Double(4.5));
    	y=getspot2(l,9,0,l.size()-1);
    	System.out.println(y);
    	l.add(y,new Double(9));
    	System.out.println(l);
    }
    static private int getspot2(ArrayList<Double> list,double x,int bottom,int top)
    {
    	int l=(bottom+top)/2;
    	double x2=list.get(l);
    	System.out.println("l: "+l+" X: "+x2);
    	if(bottom==top)
    	{
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
}