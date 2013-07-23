/**
 * Tree.java  10/6/2009
 *
 * @author - Jane Doe
 * @author - Period n
 * @author - Id nnnnnnn
 *
 * @author - I received help from ...
 *
 */

class Tree
{
	String var="";//the variable
	String ex="";//the symbol
	double v;//the number
	double v2;//other number
	Tree left;//left
	Tree right;//right
	boolean t=false;
	boolean change=true;
	boolean setyet=false;
	public Tree()
	{
	}
	public Tree(boolean h,String g)//makes a tree with a variable
	{
	//	System.out.println("making!!!!!"+g);
		var=g;
	}
	public Tree(String g)//makes a tree with a symbol
	{
		ex=g;
	}
	public Tree(double u)//makes a tree with a constant
	{
		v=u;
	}
	public void setLeft(Tree t)
	{
		left=t;
	}
	public void setRight(Tree t)
	{
		right=t;
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
		Tree temp=this;
		while(k>0)
		{
			temp=temp.left;
			if(temp==null)
				return false;
			k--;
		}
		return true;
	}
	public String toString()
	{
		if(ex.equals("")&&var.equals(""))
		{
			return ""+v;
		}else if(var.equals(""))
		{
			switch(ex.charAt(0))
			{
				case ',':return "<<";
				case '.':return ">>";
				case '?':return ">>>";
				case '`':return ">=";
				case '_':return "<=";
				default: return ex;

			}
		}
		else
			return var;
	}
	public boolean set(String y,double g)
	{
		boolean temp=true;
		if(var!=null)
		{
			if(var.trim().
				equalsIgnoreCase(y.trim()))
			{
		//		System.out.println("setting "+g);
				setyet=true;
				if(v==g)
				{
					temp=true;
				}
				v=g;
			}else
			{
				temp=false;
		//		System.out.println(y+" the given "+var+" the string");
			}
		}else
		{

		}
		if(right!=null)
		{
		//	System.out.println("right");
			if(!right.set(y,g))
			{
				temp=false;
			}
		}
		if(left!=null)
		{
		//	System.out.println("left");
		//	System.out.println("setting "+left);
			if(!left.set(y,g))
			{
				temp=false;
			}
		}
		if(!temp)
		{
			change=true;
		}else
		{
		//	change=false;
		}
	//	System.out.println(change+"ch"+temp+"t");
		return temp;
	}
	public double getnum()//returns a number
	{
	//	System.out.println(change+"changing");
		if(change&&!ex.equals(""))//if ther is no symbol
		{
			change=false;
		//	System.out.println("yay symbol");
			char x=ex.charAt(0);//gets the symbol
			switch(x)
			{
				//does the symbol
				case '+': return (v2=left.getnum()+right.getnum());
				case '-': return (v2=left.getnum()-right.getnum());
				case '*': return (v2=left.getnum()*right.getnum());
				case '/': return (v2=left.getnum()/right.getnum());
				case '%': return (v2=left.getnum()%right.getnum());
				case ',': return (v2=(int)left.getnum()<<(int)right.getnum());
				case '.': return (v2=(int)left.getnum()>>(int)right.getnum());
				case '?': return (v2=(int)left.getnum()>>>(int)right.getnum());
				case '~': return (v2=(~(int)right.getnum()));
				case '^': return (v2=Math.pow(left.getnum(),right.getnum()));
				case 'l':
				{
					if(left.getnum()==0)
					{
						return v2=Math.log(right.getnum());
					}else
					{
						return v2=Math.log(right.getnum())/Math.log(left.getnum());
					}
				}
				case 'r':
				{
					double d = right.getnum();
					double d1 = left.getnum();
					if(d <0.0&&(int)d1 % 2 != 0)
        			{
						d = -1* d;
						d1 = 1.0/d1;
						d1 = Math.pow(d,d1)*-1;
        			}else
        			{
						d1 = 1.0/d1;
						d1 = Math.pow(d,d1);
        			}
					return v2 = d1;
				}
    			case 't':
    			{
					switch((int)left.getnum())
    				{
						case 0: // sin
							return v2 = Math.sin(right.getnum());

						case 1: // cos
							return v2 = Math.cos(right.getnum());

						case 2: // tan
							return v2 = Math.tan(right.getnum());

						case 3: // csc
							return v2 = Math.sin(right.getnum());

						case 4: // sec
							return v2 = Math.cos(right.getnum());

						case 5: // cotan
							return v2 = Math.tan(right.getnum());

						case 6: // antisin
							return v2 = Math.asin(right.getnum());

						case 7: // anticos
							return v2 = Math.acos(right.getnum());

						case 8: // antitan
							return v2 = Math.atan(right.getnum());

						case 9: // anticsc
							return v2 = Math.asin(1/right.getnum());

						case 10: // antisec
							return v2 = Math.acos(1/right.getnum());

						case 11: // anticotan
							return v2 = Math.atan(1/right.getnum());
	        		}
					return Math.log(0.0D);
    			}
				case 'd':
				{
					switch((int)left.getnum())
	        		{
					case 0: // '\0'
						return v2 = Math.toDegrees(right.getnum());

					case 1: // '\001'
						return v2 = Math.toRadians(right.getnum());

					case 3: // '\001'
						return v2 = Math.E;

					case 4: // '\001'
						return v2 = Math.PI;
	        		}
					return Math.log(0.0D);
				}
				case 'a':
				{
					if(left.getnum()==2)
						return Math.floor(right.getnum());
					return v2 = Math.abs(right.getnum());
				}
				case '!': return v2 = factorial(left.getnum());
				case '`': return v2 = left.getnum() < right.getnum() ? 0.0D : 1.0D;
				case '_': return v2 = left.getnum() > right.getnum() ? 0.0D : 1.0D;
				case '=': return v2 = left.getnum() != right.getnum() ? 0.0D : 1.0D;
				case '@': return v2 = left.getnum() == right.getnum() ? 0.0D : 1.0D;
				case '<': return v2 = left.getnum() >= right.getnum() ? 0.0D : 1.0D;
				case '>': return v2 = left.getnum() <= right.getnum() ? 0.0D : 1.0D;
				case '|': return v2 = left.getnum() != 1.0D && right.getnum() != 1.0D ? 0.0D : 1.0D;
				case '&': return v2 = left.getnum() != 1.0D || right.getnum() != 1.0D ? 0.0D : 1.0D;
	        }
		}else if(!change)
		{
			change = false;
			return v2;
		}
		return v;
	}
	public boolean set(Tree tree, String s, double d)
	{
		if(var.trim().equalsIgnoreCase(s.trim()))
		{
			change = true;
			return true;
		}
		if(right != null && right.set(tree.copy(), s, d))
		{
			change = true;
			setRight(tree);
		}
		if(left != null && left.set(tree.copy(), s, d))
		{
			change = true;
			setLeft(tree);
		}
		return false;
	}
	public Tree copy()
	{
		Tree tree = new Tree();
		tree.var = var;
		tree.ex = ex;
		tree.v = v;
		change = true;
		if(left != null)
			tree.setLeft(left.copy());
		if(right != null)
			tree.setRight(right.copy());
		return tree;
	}
	public boolean equals(Object obj)
	{
		return equals((Tree)obj, 0);
	}
	public boolean equals(Tree tree, int i)
	{
		Tree tree1 = tree;
		if(var != tree1.var && (var == null || tree1.var == null || !var.equals(tree1.var)))
			return false;
		if(ex != tree1.ex && (ex == null || tree1.ex == null || !ex.equals(tree1.ex)))
			return false;
		if(v != tree1.v)
			return false;
		if(tree1.left != left && (left == null || tree1.left == null))
			return false;
		if(tree1.right != right && (right == null || tree1.right == null))
			return false;
		if(left != null && !left.equals(tree1.left))
			return false;
		return right == null || right.equals(tree1.right);
	}
	private static double factorial(double d)
	{
		if(d==0)
			return 1;
		for(double d1 = d - 1.0D; d1 > 1.0D; d1--)
			d *= d1;

		return d;
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
		var="";//the variable
		ex="";//the symbol
		v=0;//the number
		v2=0;//other number
		left=null;//left
		right=null;//right
		t=false;
		change=true;
		setyet=false;
	}
	public void reset(boolean h,String g)//resets a tree with a variable
	{
		reset();
		var=g;
	}
	public void reset(String g)//resets a tree with a symbol
	{
		reset();
		ex=g;
	}
	public void reset(double u)//resets a tree with a constant
	{
		reset();
		v=u;
	}
	public void reset(Tree g)//resets a tree with a constant
	{
		reset();
		var=g.var;//the variable
		ex=g.ex;//the symbol
		v=g.v;//the number
		v2=g.v2;//other number
		left=g.left;//left
		right=g.right;//right
		t=g.t;
		change=g.change=true;
		setyet=g.setyet;
	}
}