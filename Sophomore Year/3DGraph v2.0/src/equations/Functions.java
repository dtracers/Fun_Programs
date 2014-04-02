package equations;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Functions
{
	public static Map<String,FunctionTree> database=new TreeMap<String,FunctionTree>();
	static public void addFunction(String treeeq)
	{
		FunctionMaker fm=new FunctionMaker();
		FunctionTree g=fm.getFunction(treeeq);
		addFunction(g.name,g);
	}
	static public void addFunction(String name,FunctionTree g)
	{
		database.put(name, g);
	}
	public static Tree getFunction(String name)
	{
		return database.get(name);
	}
}
class FunctTree extends Tree
{
	String name;
	ArrayList<Tree> variableTrees=new ArrayList<Tree>();
	boolean isadded=false;
	public FunctTree(Variable v) {
		super(v);
		// TODO Auto-generated constructor stub
	}

	public FunctTree(Symbol s2) {
		super(s2);
		// TODO Auto-generated constructor stub
	}
	@Override
	public double getNum()
	{
		if(!isadded)
			addFunctions();
		//change this so it will accept multiple variables
		
		//g.replace(g.t.v.get(0).name,g.left);
		return left.getNum();
	}
	public void addFunctions()
	{
		isadded=true;
		String name=v.name.substring(0,v.name.length()-5);
		FunctionTree g=(FunctionTree)Functions.getFunction(name).clone();
		System.out.println("adding functions and stuff "+g);
		for(int k=0;k<g.variableNames.size();k++)
		{
			g.replace(g.variableNames.get(k),variableTrees.get(k));
			g.combine(variableTrees.get(k).t);
		}
		System.out.println("variable plus is"+name+"funct");
		compileRoots(null);
		t.remove(name+"funct");
		//when it replaces it it needs to change the variable names in g also
		this.setLeft(g);
		System.out.println("the new roots of variable is "+g.t);
	}
	@Override
	public Tree clone()
	{
		FunctTree t=new FunctTree(s);
		t.v=this.v;
		t.t=this.t.clone();
		t.setRight(this.right.clone());
		for(Tree r:variableTrees)
		{
			t.variableTrees.add(r.clone());
		}
		t.setLeft(this.right.clone());
		return t;
	}
	@Override
	public String toString()
	{
		if(!isadded)
			addFunctions();
		return ""+left;
	}
	@Override
	public void compileRoots(Tree supers)
	{
		super.compileRoots(supers);
		/*for(Tree t:variableTrees)
		{
			t.compileRoots(supers);
		}*/
		System.out.println("the t before removing while compiling "+t);
		System.out.println("the tree before removing while compiling "+this);
		t.remove(name+"funct");
	}
	@Override
	public String toString2()
	{
		if(!isadded)
			addFunctions();
		return ""+left.toString2();
	}
}