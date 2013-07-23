/**
 * levelmaker.java  10/22/2009
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
abstract class levelmaker
{
	private Tree g;
	ArrayList<Tree> level=new ArrayList<Tree>();
	ArrayList<String> sign=new ArrayList<String>();
    public levelmaker(Tree g2)
    {
    	g=g2;
    }    
    public ArrayList<Tree> level()
    {
    	getlevel(g.copy(),g);
    	return level;
    }
    /**
     *@param: g is a copy, g2 is the orignal
     */
    public abstract void getlevel(Tree g,Tree g2);
    public abstract void cancel();
    public abstract void combine();
    public void organize()
    {
    	level();
    	cancel();
    	combine();
    }
    public Tree getTree()
    {
    	return g;
    }
    public void setTree(Tree g)
    {
    	this.g=g;
    }
}
/*class AddSub extends levelmaker
{
	public AddSub(Tree g2)
    {
    	super(g2);
    }
    public void getlevel(Tree g,Tree g2)
    {
    	char s=getchar(g);
    	if(s=='-')
    	{
    		getlevel(g.left,g2.left);
    		sign.add(""+g);
    		if(getchar(g.right)=='+')
    		{
    			g.right.ex="-";
    		}
    		getlevel(g.right,g2.right);
    	}
    	else if(s=='+')
    	{
    		getlevel(g.left,g2.left);
    		sign.add(""+g);
    		getlevel(g.right,g2.right);
    	}
    	else
    	{
    		level.add(g2);
    	}
//    	sign.add(""+g);
    }
    private char getchar(Tree g)
    {
    	if(g.ex!=null&&!g.ex.equals(""))
    	{
	    	char s=g.ex.charAt(0);
	    	return s;
    	}
    	return ' ';
    }
    public void cancel()
    {
    	ArrayList<Tree> p=new ArrayList<Tree> (),n=new ArrayList<Tree> ();
    	p.add(level.get(0));
    	for(int k=1;k<level.size();k++)
    	{
    		if(sign.get(k-1).equals("+"))
    		{
    			p.add(level.get(k));
    		}else
    		{
    			n.add(level.get(k));
    		}
    	}
    	cancel(p,n);
    }
    public void cancel(ArrayList<Tree> p,ArrayList<Tree> n)
    {
    	boolean found=false;
    	for(int k=0;k<p.size();k++)
    	{
    		for(int q=0;q<n.size();q++)
	    	{
	    		if(k>=0)
	    		{
		    		Tree one=p.get(k);
		    		Tree two=n.get(q);
		    	//	System.out.println("+ "+one+" - "+two);
		    		if(p.get(k).equals(n.get(q),0))
		    		{
		    			int o=level.indexOf(p.get(k)),t=level.indexOf(n.get(q));
		    			level.remove(p.get(k));
		    			level.remove(n.get(q));
		    			/*if(o==0&&sign.get(0).equals("+"))
		    			{
		    				sign.remove(0);
		    			}else*/
//		    			if(found)
//		    			{
//		    				//found is true when there is a minus at the begining and
//		    				//level and sign is the same size meaning that at the first index of sign
//		    				//represtend by sign.get(0) will equal "-"
//		    				if(t==0&&sign.get(0).equals("-"))
//		    				{
//	    						sign.remove(0);
//	    						if(o==1&&sign.get(0).equals("+"))
//	    						{
//	    							sign.remove(0);
//	    							found=false;
//	    						}
//		    				}
//		    				if(t!=0)
//		    				{
//		    					sign.remove(t);
//		    					if(t>o&&o>0)
//		    					{
//		    						o--;		    						
//		    					}
//		    					sign.remove(o);
//		    				}
//		    			}else
//		    			{
//		    				//if found is false (normal) meaning x-y or x+y not -y
//		    				if(t==0&&sign.get(0).equals("-"))
//		    				{
//	    						found=true;
//		    				}else
//		    				{
//		    					if(t>0)
//		    					sign.remove(t-1);
//		    				}
//		    				if(o==0&&sign.get(0).equals("+"))
//		    				{
//		    					sign.remove(0);
//		    				}else
//		    				{
//		    					if(t<o)
//		    					{
//		    						o--;
//		    					}
//		    					if(o-1>=0)
//		    					sign.remove(o-1);
//		    				}
//		    			}
//		    			p.remove(p.get(k));
//		    			n.remove(n.get(q));
//		    			k--;
//		    			q--;
//		    		}
//	    		}else
//	    		{
//	    		}
//	    	}
//    	}
//    {
//    	String g="";
//    	int cs=0;
//    	for(int k=0;k<level.size();k++)
//    	{
//    		if(k==0&&level.size()==sign.size()&&sign.get(0).equals("-"))
//    		{
//    			g+="0"+sign.remove(0)+level.get(0).toString2();
//    		}else if(k!=0)
//    		{
//    			g+=sign.get(k-1)+level.get(k).toString2();
//    		}else if(k==0)
//    		{
//    			g+=level.get(k).toString2();
//    		}
//    	}
//    //	System.out.println(g);
//    	setTree(new expressiontree().getTree(g));
//    }
//
//}
///*class DivMult extends levelmaker
//{
//	public DivMult(Tree g2)
//    {
//    	super(g2);
//    }
//    public void getlevel(Tree g,Tree g2)
//    {
//    	if(g.ex!=null&&!g.ex.equals(""))
//    	{
//	    	char s=g.ex.charAt(0);
//	    	if(s=='*'||s=='/')
//	    	{
//	    		getlevel(g.left,g2.left);
//	    		sign.add(""+g);
//	    		getlevel(g.right,g2.right);
//	    	}
//	    	else
//	    	{
//	    		level.add(g);
//	    	}
//    	}else
//    	{	    	
//	    	level.add(g2);
//    	}
//   // 	sign.add(""+g);
//    }
//    public void cancel()
//    {
//    	
//    }
//    public void combine()
//    {
//    	
//    }
//}
//    	}else
//    	{
//    		return g2;
//    	}
//    }
//    private static String fixm2(ArrayList<String> g,String g2,int beg)
//    {
//    	System.out.println("in: "+g2+" si: "+beg+" at "+g.get(beg));
//    	ArrayList<String> tg=new ArrayList<String>();
//    	for(int k=beg;k<g.size();k++)
//    	{
//    		tg.add(g.get(k));
//    	}
//    	int firstindex=0;
//    	int index=firstindex=tg.indexOf("-");
//    	if(index!=-1)
//    	{
//    		int oldindex=index;
//    		while(index!=-1&&index<tg.size()&&oldindex+2<tg.size())
//    		{
//    			index=tg.get(oldindex+2).equals("-")?oldindex+2:/*tg.get(oldindex+3).equals("-")?oldindex+3:*/-1;//if its a minus at the next place
//	    		if(index!=-1&&(oldindex+2==index/*||oldindex+3==index*/))
//    			{
//    				tg.set(index,"+");
//    			}
//    			if(index!=-1)
//    			oldindex=index;
//    		}    		
//    		if(index==-1)
//    			index=oldindex;
//    		/*int i2=firstIndexOf(tg,index+2,")");//if there is a -()
//    		int i3=firstIndexOf(tg,index+1,"(")-index+1;//if there is a -()
//    		if(i3==0&&i2!=-1)//if there is a -()
//    		{
//    			tg.set(i2,tg.get(i2)+" )");
//    		}else*/
//    		{
//    			tg.set(index+1,tg.get(index+1)+" )");
//    		}
//    		tg.set(firstindex+1,"( "+tg.get(firstindex+1));
//    		g2="";    		
//    		for(int k=0;k<beg;k++)
//    		{
//    			g2+=g.get(k)+" ";
//    		}
//    		for(int k=0;k<tg.size();k++)
//    		{
//    			g2+=tg.get(k)+" ";
//    		}
//    		g2=g2.trim();
//    		java.util.List stupid=Arrays.asList(g2.split(" "));
//    		return fixm2(new ArrayList<String>(stupid),g2,firstindex+beg+1);
//    	}
//    	else
//    	{
//    		return g2;
//    	}
//    }
//    private static String fixd2(ArrayList<String> g,String g2,int beg)
//    {
//    	ArrayList<String> tg=new ArrayList<String>();
//    	for(int k=beg;k<g.size();k++)
//    	{
//    		tg.add(g.get(k));
//    	}
//    	int firstindex=0;
//    	int index=firstindex=tg.indexOf("/");
//    	if(index!=-1)
//    	{
//    		int oldindex=index;
//    		while(index!=-1&&index<tg.size()&&oldindex+2<tg.size())
//    		{
//    			index=g.get(oldindex+2).equals("/")?oldindex+2:g.get(oldindex+3).equals("/")?oldindex+3:-1;//if its a minus at the next place
//	    		if(index!=-1&&(oldindex+2==index||oldindex+3==index))
//    			{
//    				tg.set(index,"*");
//    			}
//    			if(index!=-1)
//    			oldindex=index;
//    		}
//    		tg.set(firstindex+1,"( "+tg.get(firstindex+1));
//    		if(index==-1)
//    			index=oldindex;
//    		tg.set(index+1,tg.get(index+1)+" )");
//    		g2="";    		
//    		fs="CXTPControlButton" Id="4220" Caption="User Tool 9" TooltipText="User Tool 9" DescriptionText="User Tool 9"/><Control Class="CXTPControlButton" Id="4221" Caption="User Tool 10" TooltipText="User Tool 10" DescriptionText="User Tool 10"/><Control Class="CXTPControlButton" Id="3204" BeginGroup="1" Caption="Stops the active tool" TooltipText="Stops the active tool" DescriptionText="Terminates the active tool"/></OriginalControls></Controls></CommandBar><CommandBar Class="CXTPToolBar" BarID="2208" Flags="63" Style="4194304" Title="Standard" MRUWidth="32767"><Controls OriginalControls="1"><Control Class="CXTPControlPopup" Type="4" Id="2846" Caption="New File..." TooltipText="New File" DescriptionText="Create a new file" CommandBarId="16777216"/><Control Class="CXTPControlButton" Id="57601" BeginGroup="1" Caption="Open" TooltipText="Open" DescriptionText="Open an existing document"/><Control Class="CXTPControlButton" Id="57603" Caption="Save" TooltipText="Save" DescriptionText="Save the active document"/><Control Class="CXTPControlButton" Id="3210" Caption="Save all files" TooltipText="Save all files" DescriptionText="saves all open files"/><Control Class="CXTPControlButton" Id="57634" BeginGroup="1" Caption="Copy selection" TooltipText="Copy selection" DescriptionText="Copies the selection to the clipboard"/><Control Class="CXTPControlButton" Id="57637" Caption="Paste" TooltipText="Paste" DescriptionText="Insert Clipboard contents"/><Control Class="CXTPControlPopup" Type="4" Id="57643" BeginGroup="1" Caption="Undo" TooltipText="Undo" DescriptionText="Undo the last action" CommandBarId="16777217"/><Control Class="CXTPControlPopup" Type="4" Id="57644" Caption="Redo" TooltipText="Redo" DescriptionText="Redo the previously undone action" CommandBarId="16777218"/><Control Class="CXTPControlButton" Id="57636" BeginGroup="1" Caption="Find" TooltipText="Find" DescriptionText="Find the specified text"/><Control Class="CFindControlComboBox" Type="5" Id="4501" Caption="Find" TooltipText="Find" DescriptionText="Find the specified text" CommandBarId="16777219" Width="175"/><Control Class="CXTPControlButton" Id="3206" Caption="Find in Files" TooltipText="Find in Files" DescriptionText="Searches for a string in multiple files"/><Control Class="CXTPControlPopup" Type="4" Id="2911" BeginGroup="1" Caption="Run" TooltipText="Execute" DescriptionText="Executes the active project" CommandBarId="16777220"/><Control Class="CXTPControlButton" Id="2908" BeginGroup="1" Caption="Compile File" TooltipText="Compile File" DescriptionText="Compile the active JAVA file"/><Control Class="CXTPControlButton" Id="2909" Caption="Compile Project" TooltipText="Compile Project" DescriptionText="Compile the active project"/><Control Class="CXTPControlButton" Id="3204" BeginGroup="1" Caption="Stops the active tool" TooltipText="Stops the active tool" DescriptionText="Terminates the active tool"/><Control Class="CXTPControlButton" Id="33139" BeginGroup="1" Caption="Back" TooltipText="Back" DescriptionText="Goes back one step"/><Control Class="CXTPControlButton" Id="33140" HideFlags="2" Caption="Forward" TooltipText="Forward" DescriptionText="Goes forward one step"/><Control Class="CXTPControlButton" Id="7010" BeginGroup="1" HideFlags="2" Caption="File View" TooltipText="File View" DescriptionText="File View"/><Control Class="CXTPControlButton" Id="7011" HideFlags="2" Caption="Class View" TooltipText="Class View" DescriptionText="Class View"/><Control Class="CXTPControlButton" Id="7014" HideFlags="2" Caption="Package View" TooltipText="Package View" DescriptionText="Package View"/><Control Class="CXTPControlButton" Id="57670" BeginGroup="1" HideFlags="2" Caption="Help" TooltipText="Help" DescriptionText="Display Help"/><OriginalControls><Control Class="CXTPControlPopup" Type="4" Id="2846" Caption="New File..." TooltipText="New File" DescriptionText="Create a new file" CommandBarId="1677722


class AddSub extends levelmaker
{

    public AddSub(Tree tree)
    {
        super(tree);
    }

    public void getlevel(Tree tree, Tree tree1)
    {
        char c = getchar(tree);
        if(c == '-')
        {
            getlevel(tree.left, tree1.left);
            sign.add((new StringBuilder()).append("").append(tree).toString());
            if(getchar(tree.right) == '+')
                tree.right.ex = "-";
            getlevel(tree.right, tree1.right);
        } else
        if(c == '+')
        {
            getlevel(tree.left, tree1.left);
            sign.add((new StringBuilder()).append("").append(tree).toString());
            getlevel(tree.right, tree1.right);
        } else
        {
            level.add(tree1);
        }
    }

    private char getchar(Tree tree)
    {
        if(tree.ex != null && !tree.ex.equals(""))
        {
            char c = tree.ex.charAt(0);
            return c;
        } else
        {
            return ' ';
        }
    }

    public void cancel()
    {
        ArrayList<Tree> arraylist = new ArrayList<Tree>();
        ArrayList<Tree> arraylist1 = new ArrayList<Tree>();
        arraylist.add(level.get(0));
        for(int i = 1; i < level.size(); i++)
            if(sign.get(i - 1).equals("+"))
                arraylist.add(level.get(i));
            else
                arraylist1.add(level.get(i));

        cancel(arraylist, arraylist1);
    }

    public void cancel(ArrayList<Tree> arraylist, ArrayList<Tree> arraylist1)
    {
        boolean flag = false;
        for(int i = 0; i < arraylist.size(); i++)
        {
            for(int j = 0; j < arraylist1.size() && i >= 0; j++)
            {
                Tree tree = arraylist.get(i);
                Tree tree1 = arraylist1.get(j);
                if(!arraylist.get(i).equals(arraylist1.get(j), 0))
                    continue;
                int k = level.indexOf(arraylist.get(i));
                int l = level.indexOf(arraylist1.get(j));
                level.remove(arraylist.get(i));
                level.remove(arraylist1.get(j));
                if(flag)
                {
                    if(l == 0 && sign.get(0).equals("-"))
                    {
                        sign.remove(0);
                        if(k == 1 && sign.get(0).equals("+"))
                        {
                            sign.remove(0);
                            flag = false;
                        }
                    }
                    if(l != 0)
                    {
                        sign.remove(l);
                        if(l > k && k > 0)
                            k--;
                        sign.remove(k);
                    }
                } else
                {
                    if(l == 0 && sign.get(0).equals("-"))
                        flag = true;
                    else
                    if(l > 0)
                        sign.remove(l - 1);
                    if(k == 0 && sign.get(0).equals("+"))
                    {
                        sign.remove(0);
                    } else
                    {
                        if(l < k)
                            k--;
                        if(k - 1 >= 0)
                            sign.remove(k - 1);
                    }
                }
                arraylist.remove(arraylist.get(i));
                arraylist1.remove(arraylist1.get(j));
                i--;
                j--;
            }

        }

    }

    public void combine()
    {
        String s = "";
        boolean flag = false;
        for(int i = 0; i < level.size(); i++)
        {
            if(i == 0 && level.size() == sign.size() && sign.get(0).equals("-"))
            {
                s = (new StringBuilder()).append(s).append("0").append(sign.remove(0)).append(level.get(0).toString2()).toString();
                continue;
            }
            if(i != 0)
            {
                s = (new StringBuilder()).append(s).append(sign.get(i - 1)).append(level.get(i).toString2()).toString();
                continue;
            }
            if(i == 0)
                s = (new StringBuilder()).append(s).append(level.get(i).toString2()).toString();
        }

        if(level.size() == 0)
            s = "0";
        setTree((new expressiontree()).getTree(s));
    }
}
class Resolve extends levelmaker
{
	public static boolean isconstant(Tree g)
	{
		if((g.ex==null||g.ex.equals(""))&&(g.var==null||g.var.equals(""))&&g.isGreaterLength(0))
		{
		//	System.out.println("im a constant "+g);
			return true;
		}else
		{
			/*
			System.out.println("im not a constant "+g+"\nbecause");
			boolean t=false;
			t=(g.ex==null||g.ex.equals(""));
			if(!t)
			{
				System.out.println("symbol is "+false);
				return false;
			}
			t=(g.var==null||g.var.equals(""));
			if(!t)
			{
				System.out.println("variable is "+false);
				return false;
			}
			t=g.isGreaterLength(0);
			if(!t)
			{
				System.out.println("the length is "+false);
				return false;
			}
			System.out.println("Im true and alfse at the same time");
			*/
			return false;
		}
	}
    public Resolve(Tree tree)
    {
        super(tree);
    }
    public void getlevel(Tree tree, Tree tree1)
    {
        getlevel(tree);
        setTree(tree);
    }
    protected boolean getlevel(Tree g)
    {
        Tree left = g.left;
        Tree right = g.right;
        if(isconstant(g))
            return true;
        if(left!=null&&right!=null)
        {
        	boolean l1 = getlevel(left);
            boolean r1 = getlevel(right);
            if(l1&&r1)
            {
            //	System.out.println("constant "+g.toString2());
            	g.reset(g.getnum());
            	return true;
            }           
        }
        return false;
    }
    public void cancel()
    {
    }

    public void combine()
    {
    }
}
class Resolve2 extends Resolve
{

    public Resolve2(Tree tree)
    {
        super(tree);
    }

    public void getlevel(Tree tree, Tree tree1)
    {
        super.getlevel(tree);
        getlevel(tree);
        super.getlevel(tree);
        setTree(tree);
    }

    protected boolean getlevel(Tree tree)
    {
        Tree left = tree.left;
        Tree right = tree.right;
        if(isconstant(tree) && tree.v == 0.0D)
            return true;
        if(left != null && right != null)
        {
            getlevel(left);
            getlevel(right);
            boolean flag = isconstant(left);
            boolean flag1 = isconstant(right);
            if(flag)
                if(left.v == 0.0D)
                {
                //    System.out.println("L0 "+tree.ex.charAt(0));
                //    System.out.println(tree.toString2());
                    switch(tree.ex.charAt(0))
                    {
	                    case '*': // '*'
	                        tree.reset(0.0D);
	                        return true;
	
	                    case '/': // '/'
	                        tree.reset(0.0D);
	                        return true;
	
	                    case '+': // '+'
	                        tree.reset(right);
	                        return true;
	                    case '^': // '+'
	                        tree.reset(0);
	                        return true;
                    }
                } else
                if(left.v == 1.0D)
                {
                //    System.out.println("L1 "+tree.ex.charAt(0));
                //    System.out.println(tree.toString2());
                    switch(tree.ex.charAt(0))
                    {
	                    case '*': // '*'
	                        tree.reset(right);
	                        return true;
	                    case '^': // '*'
	                        tree.reset(1);
	                        return true;
                    }
                }
            if(flag1)
                if(right.v == 0.0D)
                {
                	//if the right side is 0 then for the following replace or make equals to a number
                //    System.out.println("R0 "+tree.ex.charAt(0));
                //    System.out.println(tree.toString2());
                    switch(tree.ex.charAt(0))
                    {
	                    case '*': // '*'
	                        tree.reset(0.0D);
	                        return true;
	                    case '-': // '-'
	                    case '+': // '+'
	                        tree.reset(left);
	                        return true;
	                    case '^': // '^'
	                        tree.reset(1);
	                        return true;
                    }
                } else
                if(right.v == 1.0D)
                {
                	//if the right side is 1 then for the following make equalt to the left ex:
                	//3*1=3,5/1=5,6^1=6
                	
              //      System.out.println("R1 "+tree.ex.charAt(0));
              //      System.out.println(tree.toString2());
                    switch(tree.ex.charAt(0))
                    {
	                    case '*': // '*'
	                        tree.reset(left);
	                    case '^': // '*'
	                        tree.reset(left);
	                    case '/': // '/'
	                        tree.reset(left);
	                        return true;
                    }
                }
        }
        return false;
    }

    public void cancel()
    {
    }

    public void combine()
    {
    }
}