/**
 * expressiontree.java  3/5/2009
 *
 * @author - Jane Doe
 * @author - Period n
 * @author - Id nnnnnnn
 *
 * @author - I received help from ...
 *
 */

import java.util.*;
import java.lang.*;
import java.text.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
//TODO: fix for every case i.e. 3-4-(3-4-2)
public class expressiontree
{
	public Tree getTree(String g)
	{
		if(g.indexOf("Series")!=-1&&g.indexOf("then")!=-1)
		{
			return new taylorexpressions().getTree(g);
		}
		if(g.indexOf("Series")!=-1)
		{
			return new expressionseries().getTree(g);
		}
		if(g.indexOf(";")!=-1)
		{
			return new piecewisetree().getTree(g);
		}else
		{
			g=space(g);
			return makeTree(g);
		}
	}
    public static void main(String[] args) throws IOException
    {

    }
    public static int o(String g,String y)
    {
    	int count=0;
    	int index=0;
    	while(g.indexOf(y,index)!=-1)
    	{
    		count++;
    		index=g.indexOf(y,index)+1;
    	}
    	return count;
    }
    public static String space(String g) //spaces out all the non numbers from 65+6 to 65 + 6
    {
    	//TODO: make it so that it doesnt space out multi name variables using [xyz] not x y z
    	g=g.replaceAll("asin","3t");
    	g=g.replaceAll("\\[euler\\]","3d0");
    	g=g.replaceAll("\\[pi\\]","4d0");
    //	g=g.replaceAll(">>",".");
    //	g=g.replaceAll("<<",",");
    //	g=g.replaceAll(">>>","?");
    	g=g.replaceAll("~","0~");
    	g=g.replaceAll("acos","4t");
    	g=g.replaceAll("atan","5t");
    	g=g.replaceAll("root","r");
    	g=g.replaceAll("sqrt","2r");
    	g=g.replaceAll("ln","0l");
    	g=g.replaceAll("log","l");
    	g=g.replaceAll("trig","t");
    	g=g.replaceAll("sin","0t");
    	g=g.replaceAll("cos","1t");
    	g=g.replaceAll("tan","2t");
    	g=g.replaceAll("toDegrees","0d");
    	g=g.replaceAll("toRads","1d");
    	g=g.replaceAll("abs","0a");
    	g=g.replaceAll("int","2a");
//    	System.out.println("help:"+help);
    	g=g.replaceAll("<=","_");
    	g=g.replaceAll(">=","`");
    	g=g.replaceAll("&&","&");
    	g=g.replaceAll("[|][|]","|");
    	g=g.replaceAll("!=","@");
    	g=g.replaceAll("!","!0");
    	g=g.replaceAll(" ","");
    	g=fixneg(g);
    //	System.out.println(g);
    	String temp="";
    	boolean check=true;
    	boolean bracket=false;
    	for(int k=0;k<g.length();k++)
    	{
    		if(!bracket&&g.charAt(k)=='[')
    		{
    			bracket=true;
    			temp+=" ";
    		}
    		if(bracket)
    		{
    			if(g.charAt(k)==']')
    				bracket=false;
    			else if(g.charAt(k)!='[')
    			{
    				temp+=""+g.charAt(k);
    			}
    		}else
    		{
	    		try
	    		{
	    			Double.parseDouble(""+g.charAt(k));
	    			if(check)
	    			temp+=""+g.charAt(k);
	    			else
	    				temp+=" "+g.charAt(k);
	    			check=true;
	    		}catch(Exception e)
	    		{
	    			if(k!=0&&g.charAt(k)!='.')
	    				temp+=" "+g.charAt(k);
	    			else
	    				temp+=""+g.charAt(k);
	    			if(g.charAt(k)!='.')
	    			check=false;
	    		}
    		}
    	}
    	//java.util.List stupid=Arrays.asList(temp.split(" "));
//    	temp=fix.fixm(/*new ArrayList<String>(stupid),*/temp);
    //	System.out.println("fixm "+temp);
//    	stupid=Arrays.asList(temp.split(" "));
//    	temp=fix.fixd(/*new ArrayList<String>(stupid),*/temp);
    	System.out.println("fixd "+temp);
    	return temp;
    }
    public static int iom(int count,String g) // get index of matching ( ( ) )+( )
    {
    	String y=g;
    	int q=0;
    	while(count>=1)
    	{
	   		q+=y.indexOf("(")+1;
    		y=y.substring(y.indexOf("(")+1);
    		count--;
    	}
    	count=1;
    	while(count>0)
    	{
    		if((""+g.charAt(q)).equals("("))
    		{
    			count++;
    		}else if((""+g.charAt(q)).equals(")"))
    		{
    			count--;
    		}
    		q++;
    	}
    	return q-1;
    }
    public static boolean check(int index,int[][] p)
    {
    	for(int k=0;k<p[0].length;k++)
    	{
    		if(index>p[0][k]&&index<p[1][k])
    		{
    			return false;
    		}
    	}
    	return true;
    }
    public static int check2(String g,String regix,int[][] p,boolean ch)
    {
    	int index=0;
    	while(true)
    	{
    		index=g.indexOf(regix,index);
    		if(index!=-1)
    		{
    			if(check(index,p)||ch)
    			{
    				return index;
    			}else
    			{
    				index++;
    			}
    		}else
    		{
    			return -1;
    		}
    	}
    }
    public static Tree makeTree(String g)
    {
    	//works on the basis of sadmep but requires a desicion if order matters
    	//and is on the same level of order ex - and + then then non order one goes first
    	//you get asmdmep
    	//bigger to smaller
    	Scanner t;
    	Tree root=null;
    	String temp="";
    	int beg,end;
    	System.out.println(g);
    	try//if there is only one thing left
    	{
    		double y=Double.parseDouble(g);
    		return new Tree(y);
    	}catch(Exception e)
    	{
    		if(g.trim().split(" ").length==1)
    		{
    		//	System.out.println("variable tree");
    			return new Tree(true,g);
    		}
    	}
    	int[][]pairs=new int[2][o(g,"(")];
    	boolean ch=true;
    	boolean ch2=true;
    	if(pairs[0].length>0) //if ( then you have to count them
    	{
    		while(ch2)
    		{
	    		if(g.indexOf("(")==0&&iom(1,g)==g.length()-1)//if there are () one all sides of the equation
		    	{
		    		g=g.substring(2,g.length()-2);//removes the parenthiesis
		    		System.out.println(g+" after");
		    	}else
		    	{
		    		ch2=false;//its false stop the loop
		    	}
		    	if(g.indexOf("(")!=-1)//if there ae still (
		    	{
		    		pairs=new int[2][o(g,"(")];//the number of ( and two
		    		int index=0;
		    		for(int k=0;k<pairs[0].length;k++)
		    		{
		    			index=g.indexOf("(",index);//gets the index of it
		    			pairs[0][k]=index;//adds it into th pairs
		    			index++;
		    			try
		    			{
		    				pairs[1][k]=iom(k+1,g);//it will get the index of the closing )
		    			}catch(Exception e)
		    			{
		    				
		    			}
		    		}
		    		ch=false;
		    	}else
		    	{
		    		ch=true;
		    	}
    		}
    	}
    	//copy and paste
    	try//if there is only one thing left
    	{
    		double y=Double.parseDouble(g);
    		return new Tree(y);
    	}catch(Exception e)
    	{
    		if(g.trim().length()==1)
    		{
    			return new Tree(true,g);
    		}
    	}
    	int index=0;
    	//if(g.indexOf("+")!=-1&&(ch||check(g.indexOf("+"),pairs))) //add
    	if((index=check2(g,"&",pairs,ch))!=-1)//and
    	{
    		root=make(g,"&",index);
    	}else if((index=check2(g,"|",pairs,ch))!=-1)//or
    	{
    		root=make(g,"|",index);
    	}else if((index=check2(g,"<",pairs,ch))!=-1)//less than
    	{
    		root=make(g,"<",index);
    	}else if((index=check2(g,">",pairs,ch))!=-1)//greater than
    	{
    		root=make(g,">",index);
    	}else if((index=check2(g,"=",pairs,ch))!=-1)//equal to
    	{
    		root=make(g,"=",index);
    	}else if((index=check2(g,"@",pairs,ch))!=-1)//not equal to
    	{
    		root=make(g,"@",index);
    	}else if((index=check2(g,"_",pairs,ch))!=-1)//less than equal to
    	{
    		root=make(g,"_",index);
    	}else if((index=check2(g,"`",pairs,ch))!=-1)//greater than equal to
    	{
    		root=make(g,"`",index);
    	}else if((index=check2(g,"+",pairs,ch))!=-1)//add
    	{
    		root=make(g,"+",index);
    	}else if((index=check2(g,"-",pairs,ch))!=-1)//sub
    	{
    		root=make(g,"-",index);
    	}
    /*	else if((index=check2(g,".",pairs,ch))!=-1)//sub
    	{
    		root=make(g,".",index);
    	}*/
    	else if((index=check2(g,",",pairs,ch))!=-1)//sub
    	{
    		root=make(g,",",index);
    	}else if((index=check2(g,"?",pairs,ch))!=-1)//sub
    	{
    		root=make(g,"?",index);
    	}else if((index=check2(g,"*",pairs,ch))!=-1)//mult
    	{
    		root=make(g,"*",index);
    	}else if((index=check2(g,"/",pairs,ch))!=-1)//divide
    	{
    		root=make(g,"/",index);
    	}else if((index=check2(g,"%",pairs,ch))!=-1)//mod
    	{
    		root=make(g,"%",index);
    	}else if((index=check2(g,"^",pairs,ch))!=-1)//exp
    	{
    		root=make(g,"^",index);
    	}
    	else if((index=check2(g,"~",pairs,ch))!=-1)//exp
    	{
    		root=make(g,"~",index);
    	}else if((index=check2(g,"l",pairs,ch))!=-1)//log b l num
    	{
    		root=make(g,"l",index);
    	}else if((index=check2(g,"r",pairs,ch))!=-1)//root either 2 r num or 3 r num
    	{
    		root=make(g,"r",index);
    	}else if((index=check2(g,"t",pairs,ch))!=-1)//trig func either 0 sin 1 cos 2 tan 3 asin 4 acos 5 atan
    	{
    		root=make(g,"t",index);
    	}
    	else if((index=check2(g,"d",pairs,ch))!=-1)//degrees or not degrees either 0 to degrees or 1 to to rads
    	{
    		root=make(g,"d",index);
    	}else if((index=check2(g,"!",pairs,ch))!=-1)//factorial
    	{
    		root=make(g,"!",index);
    	}else if((index=check2(g,"a",pairs,ch))!=-1)//abs
    	{
    		root=make(g,"a",index);
    	}else //user defined
    	{

    	}
    	return root;
    }
    public static Tree make(String g,String regix,int index)//this will decode the item
    {
    	Tree root=null;//makes a null root
    	Scanner t;//for gettting stuff
    	root=new Tree(regix);//makes a new tree with the regix ex: + - * / ^ % ect
		int pop=index;//gets where the symbol is
		double q=getlastInt(g.substring(0,pop-1));//gets the in right before the symbol
		boolean where=((pop-(""+q).length()-1)==g.indexOf(""+q));//if the number is where it is supposed to be
		boolean where2=g.substring(0,pop-1).length()==(""+q).length();//checks if there is stuff before it
		if(where&&where2)// if both are true
		{
			root.setLeft(new Tree(q));//make a tree with number
		}else //if one is false
		{
			root.setLeft(makeTree(g.substring(0,pop-1)));//goes to check other possibilities
		}
		String tempor=g.substring(pop+2);//gets the stuff after symbol
		t=new Scanner(tempor);//makes a scanner of string
		double yell=0;//no error yay!!!!!!!!!!!!!!
		try//lets try something
		{
			yell=t.nextDouble();//gets the first int
			//checks to see if the number is in the right place and if it is the only thing left
			if(g.indexOf(""+yell)==pop+2&&(""+yell).length()==tempor.length())
			{
				root.setRight(new Tree(yell));//makes the number into a tree
			}
			else
			{
				root.setRight(makeTree(tempor));//goes to other possibilities
			}
		}catch(Exception e)
		{
			root.setRight(makeTree(tempor));//if there are others
		}
		return root;//returns the root of the tree
    }
    public static double getlastInt(String g)//gets the last int in the string
    {
    	Scanner t=new Scanner(g);
    	double w=0;
    	while(t.hasNextDouble())
    	{
    		w=t.nextDouble();
    	}
    	return w;
    }
    public static String fixneg(String g)
    {
    	System.out.println(g);
    	String g2=g;
    	int in=0;
    	try
    	{
	    	while((in=g.indexOf("-"))!=-1)
	    	{
	    		g=g.substring(in+1);
	    		if((""+g2.charAt(in+1)).matches("\\w"))
	    		{
	    			System.out.println("im at a word");
	    			if(in==0)
	    			{
	    				g2="0"+g2;
	    			}else if((""+g2.charAt(in-1)).matches("[+(*^%~@|&`_]"))
	    			{
	    				g2=g2.substring(0,in)+"0"+g2.substring(in);
	    			}else if((""+g2.charAt(in-1)).equals("-"))
	    			{
	    				g2=g2.substring(0,in-1)+"+"+g2.substring(in+1);
	    			}
	    		}
	    	}
    	}catch(Exception e)
    	{
    		System.out.println(g2);
    		e.printStackTrace();
    	}
    	System.out.println(g2);
    	return g2;
    }
}
//TODO: completely redo the fix method to make it pretty//its almost pretty
//TODO: fix it so that it will not change ones that are done correctly
class fix
{
	public static String fixm(String g2)
    {
    	int fi=g2.indexOf("-");
    	if(fi<0)
    		return g2;
    	ArrayList<String> parent=new ArrayList<String>();//the arraylist for ()
    	parent=getparent(g2);//this makes the () from the string
    	g2=parent.get(0);//gets the new string
 //   	System.out.println("the new String is: "+g2);
    	java.util.List<String> stupid=Arrays.asList(g2.split(" "));//list of things or something like that
    	g2=fixmp2(new ArrayList<String>(stupid),g2);//fixes it for those items
    	int counter=0;
    	for(int k=1;k<parent.size();k++)
    	{
    		parent.set(k,fixm(parent.get(k)));//recusive for the () too!
    	}
    	while((counter=g2.indexOf("q",counter))!=-1)
    	{
    		int t=get(g2,counter);
    		g2=g2.replaceAll("q"+t,"( "+parent.get(t)+" )");//adds them back in
    	}
    	return g2;
    }
    public static String fixmp2(ArrayList<String> g,String g2)
    {
    	int firstindex=0;
    	int index=firstindex=g.indexOf("-");
    	boolean needed=false;
    	if(index!=-1)//if there is not a minus
    	{
    		int oldindex=index;
    		while(index!=-1&&index<g.size()&&oldindex+2<g.size())
    		{
    			index=g.get(oldindex+2).equals("-")?oldindex+2:-1;//if its a minus at the next place
	    		if(index!=-1&&(oldindex+2==index))
    			{
    				needed=true;
    				g.set(index,"+");
    			}
    			if(index!=-1)
    			oldindex=index;
    		}
    		if(index==-1)
    			index=oldindex;

//    	 int i2=firstIndexOf(tg,index+2,")");//if there is a -()
//    		int i3=firstIndexOf(tg,index+1,"(")-index+1;//if there is a -()
//    		if(i3==0&&i2!=-1)//if there is a -()
//    		{
//    			tg.set(i2,tg.get(i2)+" )");
//    		}else

    		if(needed)
    		{
    			g.set(index+1,g.get(index+1)+" )");
    			g.set(firstindex+1,"( "+g.get(firstindex+1));
    		}
    		g2="";
    		for(int k=0;k<g.size();k++)
    		{
    			g2+=g.get(k)+" ";
    		}
    		g2=g2.trim();
    		java.util.List<String> stupid=Arrays.asList(g2.split(" "));
    		return fixm2(new ArrayList<String>(stupid),g2,firstindex+1);
    	}
    	else
    	{
    		return g2;
    	}
    }
    /**
     *it will remove all parenthesis and the first index will be the replaced String
     *NOTE: if there are mutliples of the same thing it will only get the first one and the second is automaticly copied
    */
    public static ArrayList<String> getparent(String g2)
    {//gets the parenthesis out and then adds them back in
    	ArrayList<String> parent=new ArrayList<String>();
    	int first=g2.indexOf("(");
    	int c=1;
    	while(first!=-1)
    	{
    		int last=expressiontree.iom(c,g2);
    		String get=g2.substring(first,last+1);
    		parent.add(get.substring(2,get.length()-2));
    		g2=g2.replace(get,"q"+c);
    		first=g2.indexOf("(",first+1);
    		c++;
    	}
    	parent.add(0,g2);
    	return parent;
    }
    public static int get(String g,int index)//it will get the numberthat follows the letter q
    {
    	g=g.substring(g.indexOf("q",index)+1);
    	Scanner s=new Scanner(g);
    	return s.nextInt();
    }
    public static String fixd(ArrayList<String> g,String g2)
    {
    	int firstindex=0;
    	int index=firstindex=g.indexOf("/");
    	if(index!=-1)
    	{
    		if(!g.get(index+1).equals("("))
    		{
	    		int oldindex=index;
	    		while(index!=-1&&index<g.size()&&oldindex+2<g.size())
	    		{
	    			index=g.get(oldindex+2).equals("/")?oldindex+2:g.get(oldindex+3).equals("/")?oldindex+3:-1;//if its a minus at the next place
	    			if(index!=-1&&(oldindex+2==index||oldindex+3==index))
	    			{
	    				g.set(index,"*");
	    			}
	    			if(index!=-1)
	    			oldindex=index;
	    		}
	    		g.set(firstindex+1,"( "+g.get(firstindex+1));
	    		if(index==-1)
	    			index=oldindex;
	    		g.set(index+1,g.get(index+1)+" )");
	    		g2="";
	    		for(int k=0;k<g.size();k++)
	    		{
	    			g2+=g.get(k)+" ";
	    		}
    		}
    		g2=g2.trim();
    		java.util.List<String> stupid=Arrays.asList(g2.split(" "));
    		return fixd2(new ArrayList<String>(stupid),g2,firstindex+1);
    	}else
    	{
    		return g2;
    	}
    }
    private static String fixm2(ArrayList<String> g,String g2,int beg)
    {
    //	System.out.println("in: "+g2+" si: "+beg+" at "+g.get(beg));
    	ArrayList<String> tg=new ArrayList<String>();
    	for(int k=beg;k<g.size();k++)
    	{
    		tg.add(g.get(k));
    	}
    	int firstindex=0;
    	int index=firstindex=tg.indexOf("-");
    	boolean needed=false;
    	if(index!=-1)
    	{
    		int oldindex=index;
    		while(index!=-1&&index<tg.size()&&oldindex+2<tg.size())
    		{
    			index=tg.get(oldindex+2).equals("-")?oldindex+2:/*tg.get(oldindex+3).equals("-")?oldindex+3:*/-1;//if its a minus at the next place
	    		if(index!=-1&&(oldindex+2==index/*||oldindex+3==index*/))
    			{
    				needed=true;
    				tg.set(index,"+");
    			}
    			if(index!=-1)
    			oldindex=index;
    		}
    		if(index==-1)
    			index=oldindex;
    		/*int i2=firstIndexOf(tg,index+2,")");//if there is a -()
    		int i3=firstIndexOf(tg,index+1,"(")-index+1;//if there is a -()
    		if(i3==0&&i2!=-1)//if there is a -()
    		{
    			tg.set(i2,tg.get(i2)+" )");
    		}else*/
    		if(needed){
    			tg.set(index+1,tg.get(index+1)+" )");
    			tg.set(firstindex+1,"( "+tg.get(firstindex+1));
    		}
    		g2="";
    		for(int k=0;k<beg;k++)
    		{
    			g2+=g.get(k)+" ";
    		}
    		for(int k=0;k<tg.size();k++)
    		{
    			g2+=tg.get(k)+" ";
    		}
    		g2=g2.trim();
    		java.util.List<String> stupid=Arrays.asList(g2.split(" "));
    		return fixm2(new ArrayList<String>(stupid),g2,firstindex+beg+1);
    	}
    	else
    	{
    		return g2;
    	}
    }
    private static String fixd2(ArrayList<String> g,String g2,int beg)
    {
    	ArrayList<String> tg=new ArrayList<String>();
    	for(int k=beg;k<g.size();k++)
    	{
    		tg.add(g.get(k));
    	}
    	int firstindex=0;
    	int index=firstindex=tg.indexOf("/");
    	if(index!=-1)
    	{
    		int oldindex=index;
    		while(index!=-1&&index<tg.size()&&oldindex+2<tg.size())
    		{
    			index=g.get(oldindex+2).equals("/")?oldindex+2:g.get(oldindex+3).equals("/")?oldindex+3:-1;//if its a minus at the next place
	    		if(index!=-1&&(oldindex+2==index||oldindex+3==index))
    			{
    				tg.set(index,"*");
    			}
    			if(index!=-1)
    			oldindex=index;
    		}
    		tg.set(firstindex+1,"( "+tg.get(firstindex+1));
    		if(index==-1)
    			index=oldindex;
    		tg.set(index+1,tg.get(index+1)+" )");
    		g2="";
    		for(int k=0;k<beg;k++)
    		{
    			g2+=g.get(k)+" ";
    		}
    		for(int k=0;k<tg.size();k++)
    		{
    			g2+=tg.get(k)+" ";
    		}
    		g2=g2.trim();
    		java.util.List<String> stupid=Arrays.asList(g2.split(" "));
    		return fixd2(new ArrayList<String>(stupid),g2,firstindex+beg+1);
    	}
    	else
    	{
    		return g2;
    	}
    }
    private static int firstIndexOf(ArrayList<String> g,int start,String g2)
    {
    	ArrayList<String> temp=new ArrayList<String>();
    	for(int k=start;k<g.size();k++)
    	{
    		temp.add(g.get(k));
    	}
    	int in=temp.indexOf(g2);
    	if(in==-1)
    	{
    		return in;
    	}
    	return in+start;
    }
    public static String remove(String g,int length,String...list)
    {
    	for(int k=0;k<list.length;k++)
    	{
    		g=g.replaceAll(""+list[k],"");
    	}
    	return g;
    }
}