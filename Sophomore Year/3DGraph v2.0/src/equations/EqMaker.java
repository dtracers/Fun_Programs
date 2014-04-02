package equations;


import java.util.*;

public class EqMaker
{
	ArrayList<Parenth> parenth=new ArrayList<Parenth>();
	String gf="";
	public EqMaker()
	{
		// TODO Auto-generated constructor stub
	}
	public EqMaker(String g)
	{
		spaceEq(g);
	}
	/**	 
	 * </p>this will space the equation and replace all human registered functions into one the computer can decode</p>
	 * </p>it first will replace all functions for example sin will change to [othersin]</p>
	 * </p>the use of brackets is so that it won't space them</p>
	 * </p>this method will remove all spaces so that the spacing is even
	 * and will space between each number and symbol and variable</p>
	 * @param g
	 */
	public void spaceEq(String g)
	{
		g=g.replaceAll(" ","");
		g=replaceAllVarags(g);
		System.out.println("the first equation "+g);
		//g=unparenth(g,0);
		g=this.perenthesis(g);
		String second="";
		int counter=0;
		boolean number=false;
		for(int k=0;k<g.length();k++)
		{
			String gtemp=""+g.charAt(k);
			if(gtemp.equals("[")&&counter==0)
			{
				if(number)
				{
					number=false;
					second+=" ";
				}
				counter=1;
			}else if(counter==1&&gtemp.equals("]"))
			{
			//	System.out.println("spaceing here is a problem");
				counter=0;
				second+=" ";
			}
			else
			{
				//System.out.println(gtemp+" matches "+gtemp.matches("\\D"));
				if(gtemp.matches("\\D")&&counter==0)
				{
					if(number)
					{
						number=false;
						second+=" ";
					}
					second+=gtemp+" ";
				}
				else 
				{
					if(!gtemp.matches("\\D"))
					number=true;
					second+=gtemp;
				}
			}
		}
		g=second.trim();
	//	System.out.println("spaced g "+g);
		gf=g;
		System.out.println(g);
	}
	/**
	 * it is identical to the public method spaceEq 
	 * @see #spaceEq(String)
	 * @param g
	 * @param t is only here to separate it from the other one
	 * @return a string
	 */
	private String spaceEq(String g,String t)
	{
		g=g.replaceAll(" ","");
		g=replaceAllVarags(g);
		//g=unparenth(g,0);
		g=this.perenthesis(g);
		String second="";
		int counter=0;
		boolean number=false;
		for(int k=0;k<g.length();k++)
		{
			String gtemp=""+g.charAt(k);
			if(gtemp.equals("[")&&counter==0)
			{
				if(number)
				{
					number=false;
					second+=" ";
				}
				counter=1;
			}else if(counter==1&&gtemp.equals("]"))
			{
			//	System.out.println("spaceing here is a problem");
				counter=0;
				second+=" ";
			}
			else
			{
				//System.out.println(gtemp+" matches "+gtemp.matches("\\D"));
				if(gtemp.matches("\\D")&&counter==0)
				{
					if(number)
					{
						number=false;
						second+=" ";
					}
					second+=gtemp+" ";
				}
				else 
				{
					if(!gtemp.matches("\\D"))
					number=true;
					second+=gtemp;
				}
			}
		}
		g=second.trim();
	//	System.out.println("spaced g "+g);
		return g;
	}
	
	public Tree getTree()
	{
		Tree temp=makeTree(gf);
		temp.compileRoots(null);
		temp.fix();
		return temp;
	}
	/**
	 *@param g this is the current string
	 *</p>works on the basis of sadmep but requires a decision if order matters</p>
	 *</p>and is on the same level of order ex - and + then then non order one goes first</p>
	 *</p>you get asmdmep</p>
	 *</p>bigger to smaller</p>
	 */
    public Tree makeTree(String g)
    {
    	//works on the basis of sadmep but requires a decision if order matters
    	//and is on the same level of order ex - and + then then non order one goes first
    	//you get asmdmep
    	//bigger to smaller
    	Tree root=null;
   // 	System.out.println("this part is "+g);
    	g=g.trim();
    	if(g.split(" ").length==1)
    	{
	    	try//if there is only one thing left
	    	{
	    		double y=Double.parseDouble(g);
	    		return new Tree(new Variable(g,y));
	    	}catch(Exception e)
	    	{
	    		if(!g.startsWith("imaparent"))
	    		{
	    		//	System.out.println("variable tree");
	    			return new Tree(new Variable(g,0));
	    		}else
	    		{
	    //			System.out.println("before"+g);
	    			g=getParenth(g);
	    			g=spaceEq(g,"");//only to change methods signature
	    			Tree temp=makeTree(g);
	    			temp.highness=-1;
	    			return temp;
	    		}
	    	}
    	}
    	int index=0;
    	//if(g.indexOf("+")!=-1&&(ch||check(g.indexOf("+"),pairs))) //add
    	
    	//covers less than, greater than, equal, to not equal to, less than or equals to, greater than or equal to
    	if((index=g.indexOf("and"))!=-1)
    	{
    		root=make(g,"and",index);
    	}else if((index=g.indexOf("or"))!=-1)//or
    	{
    		root=make(g,"or",index);
    	}else if((index=g.indexOf("equality"))!=-1)//less than
    	{
    		String fnal=g.substring(index,g.indexOf(" ",index));//takes the beginning of the word to the next space
    		root=make(g,fnal,index);
    	}
	    	/*else if((index=g.indexOf(">"))!=-1)//greater than
	    	{
	    		root=make(g,">",index);
	    	}else if((index=g.indexOf("="))!=-1)//equal to
	    	{
	    		root=make(g,"=",index);
	    	}else if((index=g.indexOf("&"))!=-1)//not equal to
	    	{
	    		root=make(g,"@",index);
	    	}else if((index=check2(g,"_",pairs,ch))!=-1)//less than equal to
	    	{
	    		root=make(g,"_",index);
	    	}else if((index=check2(g,"`",pairs,ch))!=-1)//greater than equal to
	    	{
	    		root=make(g,"`",index);
	    	
	    	}*/
    	else if((index=g.indexOf("+"))!=-1)//add
    	{
    		root=make(g,"+",index);
    	}else if((index=g.indexOf("-"))!=-1)//sub
    	{
    		root=make(g,"-",index);
    	}
    	else if((index=g.indexOf("bita"))!=-1)//>>
    	{
    		root=make(g,"bita",index);
    	}
    	else if((index=g.indexOf("bitb"))!=-1)//<<
    	{
    		root=make(g,"bitb",index);
    	}else if((index=g.indexOf("bitc"))!=-1)//>>>
    	{
    		root=make(g,"bitc",index);
    	}else if((index=g.indexOf("*"))!=-1)//mult
    	{
    		root=make(g,"*",index);
    	}else if((index=g.indexOf("/"))!=-1)//divide
    	{
    		root=make(g,"/",index);
    	}else if((index=g.indexOf("%"))!=-1)//mod
    	{
    		root=make(g,"%",index);
    	}else if((index=g.indexOf("^"))!=-1)//exp
    	{
    		root=make(g,"^",index);
    	}else if((index=g.indexOf(":"))!=-1)
    	{
    		root=makeFunction(g,":",index);
    	}
    	else if((index=g.indexOf("othervariable"))!=-1)//less than
    	{
    		String fnal=g.substring(index,g.indexOf(" ",index));//takes the beginning of the word to the next space
    		root=make(g,fnal,index);
    	}
    //	root.compileRoots(null);
    	return root;
    }
    private Tree makeFunction(String g, String regix, int index)
    {
    	Tree root=new FunctTree(new Variable(""+g.charAt(index-2)+"funct",0));
    	root.s=Symbol.getSymbol(regix);
    	((FunctTree)root).name=g.substring(0,index).trim();
    	System.out.println("making a function "+g);
    	String g2=g.substring(index+2);
    	if(g2.startsWith("imaparent"))
    	{
    		g2=getParenth(g2);
    		System.out.println("inside "+g2);
    	}
    	String[] variables;
    	if(g2.indexOf(':')==-1)
    	{
    		variables=g2.split(",");
    	}else
    	{
    		variables=g2.split(",");
    		//run and scream like a little girl
    		try
    		{
    			throw new Exception("run and scream like a little girl");
    		}catch(Exception e)
    		{
    			e.printStackTrace();
    		}
    	}
    	for(int k=0;k<variables.length;k++)
		{
    		EqMaker s=new EqMaker();
    		s.spaceEq(variables[k]);
    		((FunctTree)(root)).variableTrees.add(s.getTree());
    		
    	//	System.out.println("the variable tree is "+((FunctTree)(root)).variableTrees.get(k));
		}
    	//System.out.println("made a function tree and it is "+root);
    	((FunctTree)root).addFunctions();
    	root.compileRoots(null);
    	root.fix();
		return root;
	}
	public Tree make(String g,String regix,int index)//this will decode the item
    {
    	Tree root=null;//makes a null root
    	Scanner t;//for getting stuff
    	root=new Tree(Symbol.getSymbol(regix));//makes a new tree with the regix ex: + - * / ^ % ect
		int pop=index;//gets where the symbol is
		int slength=regix.length();
		double q=getlastInt(g.substring(0,pop-1));//gets the in right before the symbol
		boolean where=((pop-(""+q).length()-1)==g.indexOf(""+q));//if the number is where it is supposed to be
		boolean where2=g.substring(0,pop-1).length()==(""+q).length();//checks if there is stuff before it
		if(where&&where2)// if both are true
		{
			root.setLeft(new Tree(new Variable(""+q,q)));//make a tree with number
		}else//if one is false
		{
			root.setLeft(makeTree(g.substring(0,pop-1)));//goes to check other possibilities
		}
		String tempor=g.substring(pop+slength+1);//gets the stuff after symbol VERY IMPORTANT
		t=new Scanner(tempor);//makes a scanner of string
		double yell=0;
		try//lets try something
		{
			yell=t.nextDouble();//gets the first int
			//checks to see if the number is in the right place and if it is the only thing left
			if(g.indexOf(""+yell)==pop+slength+1&&(""+yell).length()==tempor.length())
			{
				root.setRight(new Tree(new Variable(""+yell,yell)));//makes the number into a tree
			}
			else
			{
				if(regix.matches("[/-]"))//IF IT IS A MINUS OR DIVIDE
				{
					if(regix.equals("-")&&tempor.indexOf("-")!=-1)
					{
						tempor=tempor.replaceAll("-","+");
					}else if(regix.equals("/")&&tempor.indexOf("/")!=-1)
					{
						tempor=tempor.replaceAll("/","*");
					}
				}
				root.setRight(makeTree(tempor));//goes to other possibilities
			}
				
		}catch(Exception e)
		{
			root.setRight(makeTree(tempor));//if there are others
		}
		return root;//returns the root of the tree
    }
	
    /**
     * @deprecated
	 *this will remove parenthesis and change them to a single variable
	 */
	private String unparenth(String g,int level)
	{
		g=removeParenth(g);//it will remove all parenthesis that are the first and last index
		int first=g.indexOf("(");
	//	System.out.println("the first indexOf "+first);
		if(first!=-1)//if a parenthesis exist only open for a close only will crash it
		{
	//		System.out.println("first "+first);
			int counter=1;
			int index=first+1;
			while(counter>0&&index<g.length())//adds ( subtracts ) for cases like (()())
			{
				String at=""+g.charAt(index);
				if(at.equals(")"))
					counter-=1;
				else if(at.equals("("))
					counter+=1;
				index+=1;
			}
			boolean once=false;
			if(counter>0)
				once=true;
			while(counter>0)
			{
				g+=")";
				counter-=1;
			}
			if(once)
			{
				return unparenth(g,level);
			}
			String inside=g.substring(first,index);
	//		System.out.println(inside);
			String name="imaparent"+level;
			Parenth s=new Parenth(name,unparenth(inside,level+1));
			if(!parenth.contains(s))
			{
				parenth.add(s);
			}
	//		System.out.println("first2 "+first+" index "+index+" the string "+g);
			if(index+1>=g.length())
			{
				g=g.substring(0,first)+"["+name+"]";
			}else
			{
				g=g.substring(0,first)+"["+name+"]"+g.substring(index+1);
			}
			
	//		System.out.println("unparenthesis "+g);
			return g;
		}else
		{
	//		System.out.println("im returning because im negative");
			return g;
		}
	}
	public String getParenth(String g)
	{
	//	System.out.println("what its looking for "+g);
	//	System.out.println(parenth);
		for(int k=0;k<parenth.size();k++)
		{
			if(g.equals(parenth.get(k).name))
				return parenth.get(k).insides;
		}
	//	System.out.println("im null");
		return null;
	}
	public static String removeParenth(String g)//will remove out side ()
	{
//		System.out.println("the current String "+g);
		int first=g.indexOf("(");
		while(first==0&&g.lastIndexOf(")")==g.length()-1)
		{
			g=g.substring(1,g.length()-1);
			first=g.indexOf("(");
		}
	//	System.out.println("the final String "+g);
		return g;
	}
	/**
	 * this will change all parenthesis in string g to a single variable
	 * @param g
	 * @return
	 */
	public String perenthesis(String g)
	{
		int counter=0;
		int parent=0;
		ArrayList<Parents> list=new ArrayList<Parents>();
		int psize=-1;
		boolean again=false;
		do
		{
			again=false;
			counter=0;
			list=new ArrayList<Parents>();
			psize=parenth.size();
			for(int k=0;k<g.length();k++)
			{
				if(g.charAt(k)=='(')
				{
					list.add(new Parents(counter,k,-1));
					counter+=1;
				}
				if(g.charAt(k)==')')
				{
					counter-=1;
					for(Parents p:list)
					{
						if(p.counter==counter)
						{
							p.end=k;
							g=replace(g,p.start+1,k,parent);
							k=0;
							counter=0;
							list=new ArrayList<Parents>();
							parent+=1;
						}
					}
				}
			}
			for(Parents p:list)
			{
				if(p.end<=0)
				{
					g+=")";
					again=true;
				}
			}
		}while(psize!=parenth.size()||again);
		return g;
	}
	/**
	 * will replace the string g from start to end with a singale variable
	 * @param g the input string
	 * @param start the beginning index
	 * @param end the ending index
	 * @param parent the number of how many parenthesis it is on the list
	 * @return the replaced string
	 */
	public String replace(String g,int start,int end,int parent)
	{
		String replacer=g.substring(start,end);
		parenth.add(new Parenth("imaparent"+parent,replacer));
		int index=0;
		while((index=g.indexOf("("+replacer+")"))!=-1)
		{
			int back=index+1+replacer.length();
			g=g.substring(0,index)+"[imaparent"+parent+"]"+g.substring(back+1);
		//	System.out.println("the new string is "+g);
		}
		return g;
	}

	public static String replaceAllVarags(String...strings)
	{
		String g=strings[0];
		g=g.replaceAll("asin","0[othervariableasin]");
    	g=g.replaceAll(">>","[bita]");//bita
    	g=g.replaceAll("<<","[bitb]");//bitb
    	g=g.replaceAll(">>>","[bitc]");//bitc
    	g=g.replaceAll("~","0[othervariable]`");
    	g=g.replaceAll("acos","0[othervariableacos]");
    	g=g.replaceAll("atan","0[othervariableatan]");
    	g=g.replaceAll("root","0[othervariableroot]");
    	g=g.replaceAll("sqrt","2[othervariableroot]");
    	g=g.replaceAll("ln","[othervariableln]");
    	g=g.replaceAll("log","[othervariablelog]");
    	g=g.replaceAll("trig","othervariable[t]");
    	g=g.replaceAll("sin","0[othervariablesin]");
    	g=g.replaceAll("cos","0[othervariablecos]");
    	g=g.replaceAll("tan","0[othervariabletan]");
    	g=g.replaceAll("toDegrees","0[othervariabletoDegrees]");
    	g=g.replaceAll("toRads","0[othervariabletoRads]");
    	g=g.replaceAll("abs","0[othervariableabs]");
    	g=g.replaceAll("int","0[othervariableint]");
    	if(g.indexOf("-")==0)
    		g="0"+g;
    	g=g.replaceAll("<=","[equality<=]");
    	g=g.replaceAll(">=","[equality>=]");
    	g=g.replaceAll("&&","[and]");
    	g=g.replaceAll("[|][|]","[or]");
    	g=g.replaceAll("!=","[equality!=]");
   // 	g=g.replaceAll("!","[other!]0");
    	g=g.replaceAll(" ","");
    	g=change(g,"<");
    	g=change(g,">");
    	g=change(g,"!");
		return g;
	}
	
	/**
	 * @param g
	 * @return a string where it replaces all symbols like sin or log with the name [coefficient]
	 */
	public static String change(String g,String regex)
	{
		int index=0;
		while((index=g.indexOf(regex,index))!=-1)
		{
			try
			{
				char w=g.charAt(index+1);
				switch(w)
				{
					case '=':break;
					case '>':break;
					case '<':break;
					default:
					{
						if(regex.equals("!"))
							g=g.substring(0,index)+"[other!]0"+g.substring(index+1);
						else
						{
							g=g.substring(0,index)+"[equality"+regex+"]"+g.substring(index+1);
						}
					}
				}
			}catch(Exception e)
			{
				if(regex.equals("!"))
					g=g.substring(0,index)+"[other!]0";
				else
				{
					g=g.substring(0,index)+"[equality"+regex+"]"+g.substring(index+1);
				}
			}
			index+=1;
		}
		return g;
	}
	/**
	 * @param g the string
	 * @return the last double in the string
	 */
	public static double getlastInt(String g)
    {
    	Scanner t=new Scanner(g);
    	double w=0;
    	while(t.hasNextDouble())
    	{
    		w=t.nextDouble();
    	}
    	return w;
    }
	/**
	 * this class is used in the removing of parenthesis
	 */
	class Parents
	{
		int start;
		int counter;
		int end;
		public Parents(int c,int s,int e)
		{
			counter=c;start=s;end=e;
		}
	}
}
