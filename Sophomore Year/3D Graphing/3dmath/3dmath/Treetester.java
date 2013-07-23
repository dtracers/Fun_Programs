/**
 * Treetester.java  10/22/2009
 *
 * @author - Jane Doe
 * @author - Period n
 * @author - Id nnnnnnn
 *
 * @author - I received help from ...
 *
 */


import java.util.*;
public class Treetester
{
    public static void main(String[] args)
    {
    	Scanner s=new Scanner(System.in);
    	while(true)
    	{
    		String g=s.nextLine();
    		Tree x=new expressiontree().getTree(g);
    		levelmaker l1=new Resolve(x);
    		l1.organize();
    		levelmaker l2=new Resolve2(l1.getTree());
    		l2.organize();
    		System.out.println("the tree"+l2.getTree().toString2());
    	}
    }
}