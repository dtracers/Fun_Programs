package equations;
public class Variable
{
	String name;
	double value;
	public Variable(String n,double v)
	{
		name=n;value=v;
	}
	public static final Variable ZERO=new Variable("0",0);
	public String toString()
	{
		return name;
	}
	public boolean equals(Object c)
	{
		Variable c2=(Variable)c;
		return c2.name.equals(this.name);
	}
	public void setValue(double d)
	{
		value=d;
	}
	public double getValue()
	{
		return value;
	}
	public void copy(Variable v)
	{
		this.name=v.name;
		this.value=v.value;
	}
	public Variable clone()
	{
		return new Variable(name,value);
	}
	public String toString2()
	{
		// TODO Auto-generated method stub
		return "{"+name+"="+value+"}";
	}
}
