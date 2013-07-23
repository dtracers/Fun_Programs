/**
 * test.java  9/15/2009
 *
 * @author - Jane Doe
 * @author - Period n
 * @author - Id nnnnnnn
 *
 * @author - I received help from ...
 *
 */

import java.util.*;

public class test
{
	public static void main(String[] args)
	{
		String g="x*sin(x);(sin(x)<(0-1)*sin(x))&&(x<0);x*cos(x);(sin(x)>=(0-1)*sin(x))&&(x>0);0";
		Tree t=new expressiontree().getTree(g);
		System.out.println("tree equals "+t.toString2());
		for(int k=0;k<20;k++)
		{
			t.set("x",k);
			System.out.println("tree equals "+t.toString2());
			System.out.println(t.getnum()+" "+k);
		}
	}
}