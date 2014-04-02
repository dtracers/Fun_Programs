import java.util.Date;


public class ClassBlock
{
	MyDate d[];
	String className;
	public ClassBlock(String className,MyDate... dates)
	{
		d=dates;
		this.className=className;
	}
	public boolean equals(Object c)
	{
		return ((ClassBlock)c).className.equals(className);
	}
	public String toString()
	{
		String g=className;
		for(int k=0;k<d.length;k++)
		{
			g+=" : "+d[k];
		}
		return g;
	}
}