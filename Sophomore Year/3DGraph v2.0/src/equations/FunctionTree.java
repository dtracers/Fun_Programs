package equations;

import java.util.ArrayList;

public class FunctionTree extends Tree
{
	String name;
	ArrayList<String> variableNames=new ArrayList<String>();
	public FunctionTree(Variable v)
	{
		super(v);
		// TODO Auto-generated constructor stub
	}

	public FunctionTree(Symbol s2)
	{
		super(s2);
		// TODO Auto-generated constructor stub
	}
	public FunctionTree(String name)
	{
		super(Symbol.all);
		this.name=name;
	}
	@Override
	public Tree clone()
	{
		FunctionTree t=new FunctionTree(s);
		t.variableNames=(ArrayList)this.variableNames.clone();
		t.name=this.name;
		t.t=this.t.clone();
		if(v!=null)
		t.v=this.v.clone();
		t.setLeft(left.clone());
		return t;
	}
	@Override
	public double getNum()
	{
		return left.getNum();
	}
	public String toString()
	{
		return ""+left;
	}
	public String toString2()
	{
		return name+variableNames+"="+left.toString2();
	}
}
