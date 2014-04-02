package equations;

import java.util.ArrayList;

public class Tree
{
	int highness=0;
	Tree left;
	Tree right;
	/**
	 *the symbol it is to make it easier to decide what the symbol is
	 */
	Symbol s;
	/**
	 * if left and right are null then the Variable can't be
	 * if the variable is a number then the name must be the number and the value 
	 */
	Variable v;
	/**
	each tree will share the same root
	*/
	Root t=new Root();
	public Tree(Symbol s2)
	{
//		System.out.println("setting symbol "+s2);
		s=s2;
		if(s!=null)
			switch(s)
			{
				case all:	highness=-2;break;
				case plus:
				case minus:
				case add:	highness=0;break;
				case mult:
				case div:
				case mod:	highness=1;break;
				default:	highness=10;
			}
		else
			highness=-2;
	}
	/**
	 * this will replace the given variable with the new one
	 * and will replace each instance in the tree
	 * @param name
	 * @param v
	 */
	public void replace(String name,Variable v)
	{
		Variable temp=t.get(name);
//		System.out.println(temp);
		if(temp!=v&&temp!=null)
		{
			t.remove(temp);
			t.add(v);
		}else if(temp==null)
		{
			t.add(v);
		}
	//	System.out.println(v.name);
		if(this.v!=null
			&&this.v.name!=null&&
			this.v.name.equals(v.name))
		{
			this.v=v;
		}
		if(left!=null)
		{
			left.replace(name, v);
		}
		if(right!=null)
		{
			right.replace(name, v);
		}
	}
	/**
	 * this will replace all instances of the name with the given tree
	 * @param name
	 * @param g
	 */
	public void replace(String name,Tree g)
	{
		Variable temp=t.get(name);
		t.remove(temp);
		if(left!=null)
		{
			if(left.v!=null&&left.v.name!=null&&left.v.name.equals(name))
			{
				setLeft(g);
			}else
			{
				left.replace(name, g);
			}
		}
		if(right!=null)
		{
			if(right.v!=null&&right.v.name!=null&&right.v.name.equals(name))
			{
				setRight(g);
			}else
			{
				right.replace(name, g);
			}
		}
	}
	public Tree(Variable v)
	{
//		System.out.println("setting variable "+v);
		this.v=v;
		t.add(v);
	}
	public void setLeft(Tree t)
	{
		left=t;
		combine(left.t);
		left.t=this.t;
		left.fix();
	}
	public void setRight(Tree t)
	{
		right=t;
		combine(right.t);
		right.t=this.t;
		right.fix();
	}
	public String toString()
	{
		String y="";
		if(left!=null)
			y+=parenthesize(left);
		if(right==left&&right==null)
		{
			y+=""+v;
		}else
		{
			y+=""+s.getName();
		}
		if(right!=null)
			y+=parenthesize(right);
		return y;
	}
	private String parenthesize(Tree g)
	{
		if(g.highness<this.highness&&g.v==null)
			return "("+g+")";
		else
			return ""+g;
	}
	/**
	 * this is to make sure that they all share the same root
	 */
	public void fix()
	{
		if(v!=null)
		{
			v=t.add(v);
		}
		if(left!=null)
		{
			left.fix();
		}
		if(right!=null)
		{
			right.fix();
		}
	}
	public void compileRoots(Tree supers)
	{
		if(supers!=null)
		{
			combine(supers.t);
			supers.combine(supers.t);
			t=supers.t;
		}else
		{
			supers=this;
		}
		if(left!=null)
		{
			left.compileRoots(supers);
		}
		if(right!=null)
		{
			right.compileRoots(supers);
		}
	}
	/**
	 * this will set the variable name to the given value
	 * @param g the name of the variable
	 * @param d the double value
	 */
	public void setVariable(String g,double d)
	{
		System.out.println("list of variables "+t);
		t.get(g).setValue(d);
	}
	/**
	 * will set all variables to the double given
	 * @param d
	 */
	public void setAllVariables(double d)
	{
		for(int k=0;k<t.v.size();k++)
		{
			t.v.get(k).setValue(d);
		}
	}
	/**
	*@deprecated bad style
	*@see #getNum()
	*/
	public double getnum()
	{
		return getNum();
	}
	/**
	 * this will go through the equation evaluating it
	 * @return a double representation of the equation
	 */
	public double getNum()
	{
		if(s==null)
			return v.getValue();
		switch(s)
		{
			case all:return v.getValue();
			case plus: return (left.getnum()+right.getnum());
			case minus: return (left.getnum()-right.getnum());
			case mult: return (left.getnum()*right.getnum());
			case div: return (left.getnum()/right.getnum());
			case mod: return (left.getnum()%right.getnum());
			case pow: return (Math.pow(left.getnum(),right.getnum()));
			
			case Int:return (int)right.getnum();
			case bita: 
				return ((int)left.getnum()<<(int)right.getnum());
			case bitb: 
				return ((int)left.getnum()>>(int)right.getnum());
			case bitc: 
				return ((int)left.getnum()>>>(int)right.getnum());
		//	case '~': return (v2=(~(int)right.getnum()));
			
			case ln:return Math.log(right.getnum());
			case log:
			{
				return Math.log(right.getnum())/Math.log(left.getnum());
			}
			case root:
			{
				double d = right.getnum();
				double d1 = left.getnum();
				if(d <0.0&&d1 % 2 != 0)
				{
					d = -1* d;
					d1 = 1.0/d1;
					d1 = Math.pow(d,d1)*-1;
				}else
				{
					d1 = 1.0/d1;
					d1 = Math.pow(d,d1);
				}
				return d1;
			}
			case sin:
				return Math.sin(right.getnum());
			case cos: // '\001'
				return Math.cos(right.getnum());

			case tan: // '\002'
				return Math.tan(right.getnum());

			case asin: // '\003'
				return Math.asin(right.getnum());

			case acos: // '\004'
				return Math.acos(right.getnum());

			case atan: // '\005'
				return Math.atan(right.getnum());
			
			case todeg: // '\0'
				return Math.toDegrees(right.getnum());

			case torads: // '\001'
				return Math.toRadians(right.getnum());
			case abs:
			{
				return Math.abs(right.getnum());
			}
			case factorial: return factorial(left.getnum());
			case lessthan: return left.getnum() < right.getnum() ? 0.0D : 1.0D;
			case greaterthan: return left.getnum() > right.getnum() ? 0.0D : 1.0D;
			case notequal: return left.getnum() != right.getnum() ? 0.0D : 1.0D;
			case equal: return left.getnum() == right.getnum() ? 0.0D : 1.0D;
			case lessthanequal: return left.getnum() >= right.getnum() ? 0.0D : 1.0D;
			case greaterthanequal: return left.getnum() <= right.getnum() ? 0.0D : 1.0D;
			case or: return left.getnum() != 1.0D && right.getnum() != 1.0D ? 0.0D : 1.0D;
			case and: return left.getnum() != 1.0D || right.getnum() != 1.0D ? 0.0D : 1.0D;
		}
		return 0;
	}
	private static double factorial(double d)
	{
		for(double d1 = d - 1.0D; d1 > 1.0D; d1--)
			d *= d1;
	
		return d;
	}
	public void combine(Root t)
	{
		if(t.v.size()==0)
			return;
		for(int k=0;k<t.v.size();k++)
		{
			this.t.add(t.v.get(k));
		}
	}
	public Tree clone()
	{
		Tree r=new Tree(s);
		r.t=t.clone();
		if(v!=null)
		{
			r.v=v.clone();
			r.v=r.t.add(r.v);
		}
		if(left!=null)
			r.setLeft(left.clone());
		if(right!=null)
			r.setRight(right.clone());
		r.highness=this.highness;
		return r;
	}
	public void setTo(Tree g)
	{
		t=g.t.clone();
		if(left!=null)
		{
			left.setTo(g.left);
			this.setLeft(left);
		}else
		{
			setLeft(g.clone());
		}
		if(right!=null)
		{
			right.setTo(g.right);
			this.setLeft(right);
		}else
		{
			setRight(g.clone());
		}
		if(g!=null)
			v=g.v.clone();
		else
			v=g.v;
		highness=g.highness;
	}
	public String toString2()
	{
		String y="";
		if(left!=null)
			y+=""+left.toString2();
		if(right==left&&right==null)
		{
			y+=""+v.toString2();
		}else
		{
			y+=""+s.getName();
		}
		if(right!=null)
			y+=""+right.toString2();
		return y;
	}
}
//this will hold names that will be common throughout everything to make changing variables to be easier
class Root
{
	ArrayList<Variable> v=new ArrayList<Variable>();
	public Variable add(Variable b)
	{
//		System.out.println("adding the variable "+b);
		if(v.contains(b))
		{
			int index=0;
			while(!v.get(index).equals(b))
			{
				index++;
			}
			return v.get(index);
		}else
		{
			v.add(b);
			return b;
		}
			
	}
	public Variable get(String g)
	{
		int index=0;
		int size=v.size();
		if(size==0)
			return null;
		while(!v.get(index).name.equals(g))
		{
			index++;
			if(index>=size)
				return null;
		}
		try
		{
			return v.get(index);
		}catch(Exception e)
		{
			return null;
		}
	}
	public void remove(String g)
	{
		v.remove(get(g));
	}
	public void remove(Variable v)
	{
		this.v.remove(v);
	}
	public Root clone()
	{
		Root r=new Root();
		for(int k=0;k<v.size();k++)
		{
			r.add(v.get(k).clone());
		}
		return r;
	}
	public String toString()
	{
		return ""+v;
	}
}
