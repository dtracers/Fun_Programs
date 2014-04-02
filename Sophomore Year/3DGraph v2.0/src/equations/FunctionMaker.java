package equations;

public class FunctionMaker extends EqMaker
{
	public FunctionTree getFunction(String g)//f:(x)=
	{
		int index=g.indexOf(':');
		FunctionTree T=new FunctionTree(g.substring(0,index));
		int index2=g.indexOf(')');
		int index3=g.indexOf('(');
		String[] variables=g.substring(index3+1,index2).split(",");
		for(int k=0;k<variables.length;k++)
		{
			T.variableNames.add(variables[k]);
			T.t.add(new Variable(variables[k],0));
		}
		spaceEq(g.substring(g.indexOf("=")+1));
		T.setLeft(super.getTree());
		System.out.println("the new tree "+T);
		T.compileRoots(null);
		return T;
	}

}
