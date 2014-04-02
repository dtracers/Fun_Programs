package equations;

public class Parenth
{
	String name;
	String insides;
	public Parenth(String n,String i)
	{
		name=n;insides=i;
	}
	public boolean equals(Object t)
	{
		Parenth s=(Parenth)(t);
		return s.insides.equals(insides);
	}
	public String toString()
	{
		return name+":"+insides;
	}
}
