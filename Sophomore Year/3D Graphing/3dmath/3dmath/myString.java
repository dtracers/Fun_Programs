/**
 * myString.java  9/22/2008
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

public class myString
{
	public static void main(String args[])
	{
		String one="l;i;h;y;ds;h;sghfgh;;hhsf";
		ArrayList<String> f=new ArrayList<String>();
		String two="the dog ran around the house";
		String on="A cat found and ate the mouse in delaware";
		String three="A mouse ate A cat which ate A dog";
		f.add(on);
		f.add(two);
		f.add(three);
		String find=";";
		String re="-";
		/*System.out.println(one+" the original");
		System.out.println(find+" what you looking for");
		System.out.println(re+" replace with");
		System.out.println(myString.replace(one,find,re)+" the new one");
		System.out.println("\n\n"+on+ " :one");
		System.out.println(two+" :two");
		System.out.println("braiding\n1\n"+myString.braid(on,two)+"\n2\n"+myString.braid(two,on));*/
//		System.out.println("\n\n"+f);
//		System.out.println("orig\n"+on+"\n"+myString.remove(on,'j','r')+"\nafter");
//		System.out.println("orig\n"+on+"\n"+myString.removeallbut(on,'j','r')+"\nafter");
		System.out.println(myString.braid(f));
		System.out.println(myString.braid(f,1));
		System.out.println(myString.braid(f,0,2));
		String[] f2=new String[100];
		myString.toString(f2);
	//	System.out.println(myString.braid(f,2,0));
	}
    public myString()
    {
    }
    public static String[] replaceAll(String[] g,String find,String replace)
    {
    	for(int k=0;k<g.length;k++)
    	{
    		g[k]=replace(g[k],find,replace);
    	}
    	return g;
    }
    public static String[] replaceSome(String[] g,String find,String replace,int[] which)
    {
    	for(int k=0;k<which.length;k++)
    	{
    		g[which[k]]=replace(g[which[k]],find,replace);
    	}
    	return g;
    }
    public static String replace(String orig,String find,String replace)
    {
    	int t=0;
    	do
    	{
	    	t=orig.indexOf(find);
	    	if(t!=-1)
	    	{
		    	String halfone=orig.substring(0,t);
		    	String halftwo=orig.substring(t+find.length(),orig.length());
		    	orig=halfone+replace+halftwo;
		    //	System.out.println(orig+" "+t+" t");
	    	}
    	}
    	while(t!=-1);
    	return orig;
    }
    public static String removeallbut(String orig,char c1,char c2)
    {
    	String temp=""+orig;
    	for(int k=0;k<temp.length();k++)
    	{
    		char q=temp.charAt(k);
    		if(c1>q||q>c2)
    		{
    			temp=temp.substring(0,k)+temp.substring(k+1,temp.length());
				k--;
    		}
    	}
    	return temp;
    }
    public static String remove(String orig,char c1,char c2)
    {
    	String temp=""+orig;
    	for(int k=0;k<temp.length();k++)
    	{
    		char q=temp.charAt(k);
    		if((c1<q&&q<c2))
    		{
    			temp=temp.substring(0,k)+temp.substring(k+1,temp.length());
				k--;
    		}
    	}
    	return temp;
    }
    public static String removeallbut(String orig,ArrayList<Character> c1,ArrayList<Character> c2)
    {
    	String temp=orig;
    	for(int k=0;k<Math.min(c1.size(),c2.size());k++)
    	{
    		temp=removeallbut(temp,c1.get(k),c2.get(k));
    	}
    	return temp;
    }
    public static String remove(String orig,ArrayList<Character> c1,ArrayList<Character> c2)
    {
    	String temp=orig;
    	for(int k=0;k<Math.min(c1.size(),c2.size());k++)
    	{
    		temp=remove(temp,c1.get(k),c2.get(k));
    	}
    	return temp;
    }
    private static String helper1(String g,String other)
    {
    	String temp="";
    	String temp2="";
    //	System.out.println("both strings are equal in length");
    	for(int k=0;k<g.length();k+=2)
    	{
    		temp+=g.charAt(k);
    		if(k+1<g.length())
    		temp2+=g.charAt(k+1);
    	}
    	if(temp.equals(other))
    	{
    		return temp2;
    	}
    	else if(temp2.equals(other))
    	{
    		return temp;
    	}
    	return null;
    }
    private static String helper2(String g,String other)
    {
    	String temp="";
    	String temp2="";
    //	System.out.println("the braided length is even");
    	if(g.length()>other.length()*2)
    	{
    //		System.out.println("the given String is the shorter one");
	    	for(int k=0;k<other.length()*2;k+=2)
	    	{
	    		temp+=g.charAt(k);
	    		if(k+1<g.length())
	    		temp2+=g.charAt(k+1);
	    	}
	    //	System.out.println("\n"+temp+"\ncurrent String");
	    //	System.out.println(temp2+"\ncurrent String");
	    	if(temp.equals(other))
	    	{
	    		for(int k=other.length()*2;k<g.length();k++)
		    	{
		    		temp2+=g.charAt(k);
		    	}
	    	}else if(temp2.equals(other))
	    	{
	    		for(int k=other.length()*2;k<g.length();k++)
		    	{
		    		temp+=g.charAt(k);
		    	}
	    	}
	    	if(temp.equals(other))
	    	{
	    		return temp2;
	    	}
	    	else if(temp2.equals(other))
	    	{
	    		return temp;
	    	}
    	}
    	else return helper3(g,other);
    	return null;
    }
    private static String helper3(String g,String other)
    {
    	String temp="";
    	String temp2="";
    	if(g.length()<other.length()*2)
    	{
    	//	System.out.println("the given String is the longer one");
    		int y=g.length()-other.length();
			//System.out.println("the nongiven String is even");
    		for(int k=0;k<y*2;k+=2)
	    	{
	    		temp+=g.charAt(k);
	    		if(k+1<y*2)
	    		temp2+=g.charAt(k+1);
	    	}
	    	if(temp.equals(other.substring(0,y)))
	    	{
	    		return temp2;
	    	}
	    	else if(temp2.equals(other.substring(0,y)))
	    	{
	    		return temp;
	    	}
    	//	System.out.println("\n"+temp+"\ncurrent String");
	    //	System.out.println(temp2+"\ncurrent String");
	    	if(temp.equals(other))
	    	{
	    		return temp2;
	    	}
	    	else if(temp2.equals(other))
	    	{
	    		return temp;
	    	}
    	}
    	return null;
    }
    private static String helper4(String g,String other)
    {
    	String temp="";
    	String temp2="";
    	if(g.length()>other.length()*2)
    	{
    //		System.out.println("the given String is the shorter one");
	    	for(int k=0;k<other.length()*2;k+=2)
	    	{
	    		temp+=g.charAt(k);
	    		if(k+1<other.length()*2)
	    		temp2+=g.charAt(k+1);
	    	}
	    //	System.out.println("\n"+temp+"\ncurrent String");
	    //	System.out.println(temp2+"\ncurrent String");
	    	if(temp.equals(other))
	    	{
	    		for(int k=other.length()*2;k<g.length();k++)
		    	{
		    		temp2+=g.charAt(k);
		    	}
	    	}else if(temp2.equals(other))
	    	{
	    		for(int k=other.length()*2;k<g.length();k++)
		    	{
		    		temp+=g.charAt(k);
		    	}
	    	}
	    	if(temp.equals(other))
	    	{
	    		return temp2;
	    	}
	    	else if(temp2.equals(other))
	    	{
	    		return temp;
	    	}
    	}
    	else return helper3(g,other);
    	return null;
    }
    private static String helper5(String g,String other)
    {
    	String temp="";
    	String temp2="";
    	if(g.length()>other.length()*2)
    	{
    	//	System.out.println("the given String is the shorter one");
	    	for(int k=0;k<other.length()*2;k+=2)
	    	{
	    		temp+=g.charAt(k);
	    		if(k+1<other.length()*2)
	    		temp2+=g.charAt(k+1);
	    	}
	    //	System.out.println("\n"+temp+"\ncurrent String");
	    //	System.out.println(temp2+"\ncurrent String");
	    	if(temp.equals(other))
	    	{
	    		for(int k=other.length()*2;k<g.length();k++)
		    	{
		    		temp2+=g.charAt(k);
		    	}
	    	}else if(temp2.equals(other))
	    	{
	    		for(int k=other.length()*2;k<g.length();k++)
		    	{
		    		temp+=g.charAt(k);
		    	}
	    	}
	    	if(temp.equals(other))
	    	{
	    		return temp2;
	    	}
	    	else if(temp2.equals(other))
	    	{
	    		return temp;
	    	}
    	}
    	else return helper3(g,other);
    	return null;
    }
    public static String unbraid(String g,String other)
    {
    	String temp="";
    	String temp2="";
    	//System.out.println("\nstarting unbraiding\n"+g+"\nbraided\n"+other+"\nwhat is given");
    	if(g.length()-other.length()==other.length())
    	{
    		return helper1(g,other);
    	}
    	else if(g.length()%2==0&&other.length()%2==0)
    	{
    		return helper2(g,other);
    	}
    	else
    	{
    		if(g.charAt(0)==other.charAt(0)&&g.charAt(1)!=g.charAt(0))
    		{
    		//	System.out.println("the given String starts at zero");
	    		return helper4(g,other);
    		}else if(g.charAt(1)==other.charAt(0)&&g.charAt(1)!=g.charAt(0))
    		{
    		//	System.out.println("the given String starts at one");
    			return helper5(g,other);
    		}else
    		{
    			int counter=0;
				for(int k=0;k<g.length()-1;k++)
				{
					if(g.charAt(k)!=g.charAt(k+1)&&k%2==0)
						break;
					else
						counter=k;
				}
				if(counter==g.length()-1)
				{
					return other;
				}
				if(g.charAt(counter)==other.charAt(counter/2))
	    		{
	    		//	System.out.println("the given String starts at zero");
		    		return helper4(g,other);
	    		}else if(g.charAt(counter)==other.charAt(counter/2+1))
	    		{
	    		//	System.out.println("the given String starts at one");
	    			return helper5(g,other);
	    		}
    		}
    	}
    	return null;
    }
    public static String unbraid(String g,ArrayList<String> others)
    {
    	return null;
    }
    public static String braid(String g,String y)
    {
    	String temp="";
    	int counter=0;
    	try
    	{
    		int t=y.length()-g.length();
    		for(int k=0;k<g.length();k++)
    		{
    			temp+=""+g.charAt(k);
    			counter=k;
    			temp+=""+y.charAt(k);
    		}
    		for(int k=counter+1;k<t+counter+1;k++)
    		{
    			temp+=""+y.charAt(k);
    		}
    		return temp;
    	}catch(Exception e)
    	{
    	//	e.printStackTrace();
    		for(int k=counter+1;k<g.length();k++)
    		{
    			temp+=""+g.charAt(k);
    		}
    		return temp;
    	}
    }
    public static String braid(ArrayList<String> g)
    {
    	String temp="";
    	int length=0;
    	for(int k=0;k<g.size();k++)
    	{
    		if(g.get(k).length()>length)
    			length=g.get(k).length();
    	}
    	for(int k=0;k<length;k++)
    	{
    		for(int q=0;q<g.size();q++)
	    	{
	    		String temp2=g.get(q);
	    		if(temp2.length()>k)
	    			temp+=""+temp2.charAt(k);
	    	}
    	}
    	return temp;
    }
    public static String braid(ArrayList<String> g,int whichone)
    {
    	String temp="";
    	int length=0;
    	for(int k=0;k<g.size();k++)
    	{
    		if(g.get(k).length()>length)
    			length=g.get(k).length();
    	}
    	for(int k=0;k<length;k++)
    	{
    		for(int q=whichone;q<g.size()+whichone;q++)
	    	{
	    		String temp2=g.get(q%(g.size()));
	    		if(temp2.length()>k)
	    			temp+=""+temp2.charAt(k);
	    	}
    	}
    	return temp;
    }
    public static String braid(ArrayList<String> g,int start,int end)
    {
    	String temp="";
    	int length=0;
    	for(int k=0;k<g.size();k++)
    	{
    		if(g.get(k).length()>length)
    			length=g.get(k).length();
    	}
    	for(int k=0;k<length;k++)
    	{
    		for(int q=start;q<end;q++)
	    	{
	    		String temp2=g.get(q%(g.size()));
	    		if(temp2.length()>k)
	    			temp+=""+temp2.charAt(k);
	    	}
    	}
    	return temp;
    }
    private ArrayList shift(ArrayList g,int shiftby)
    {
    	ArrayList g2=new ArrayList();
    	for(int k=shiftby;k<g.size()+shiftby;k++)
    	{
    		g2.add(g.get(k%(g.size())));
    	}
    	return g2;
    }
    public static String toString(int[] something)
    {
    	Integer[] f=new Integer[something.length];
    	for(int k=0;k<something.length;k++)
    	{
   			f[k]=something[k];
    	}
    	return toString(f);
    }
    public static String toString(boolean[] something)
    {
    	Boolean[] f=new Boolean[something.length];
    	for(int k=0;k<something.length;k++)
    	{
   			f[k]=something[k];
    	}
    	return toString(f);
    }
    public static String toString(double[] something)
    {
    	Double[] f=new Double[something.length];
    	for(int k=0;k<something.length;k++)
    	{
   			f[k]=something[k];
    	}
    	return toString(f);
    }
    public static String toString(char[] something)
    {
    	Character[] f=new Character[something.length];
    	for(int k=0;k<something.length;k++)
    	{
   			f[k]=something[k];
    	}
    	return toString(f);
    }
    public static String toString(Object[] something)
    {
    	String temp="[";
    	for(int k=0;k<something.length;k++)
    	{
    		if(k<something.length-1)
    			temp+=""+something[k]+",";
    		else
    			temp+=""+something[k];
    	}
    	temp+="]";
    	return temp;
    }
}
