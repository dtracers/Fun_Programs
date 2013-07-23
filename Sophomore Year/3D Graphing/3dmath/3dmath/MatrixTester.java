/**
 * MatrixTester.java  10/15/2009
 *
 * @author - Jane Doe
 * @author - Period n
 * @author - Id nnnnnnn
 *
 * @author - I received help from ...
 *
 */



public class MatrixTester
{
	static Matrix t=new Matrix();
    public static void main(String[] args)
    {
    	method1();
    }
    public static void method1()
    {
    	for(int y=-25;y<25;y++)
    	{
    		for(int x=-25;x<25;x++)
    		{
    			t.add(new Pointsrectolinier(x,y));
    		}
    	}
    	for(int k=0;k<t.length();k++)
    	{
    		System.out.println(t.get(k));
    	}
    	for(int k=0;k<t.nlength();k++)
    	{
    		System.out.println(t.getlist(k));
    	}
    }
    public static void method2()
    {
    	t.add(new Pointsrectolinier(0,0));    	
    	t.add(new Pointsrectolinier(1,0));   	
    	t.add(new Pointsrectolinier(0,1));    	
    	t.add(new Pointsrectolinier(4,0));    	
    	System.out.println("1: "+t.get(0,0,t.nlength()));
    	System.out.println("2: "+t.get2(0,0,t.nlength()));
    	System.out.println("3: "+t.get2(2,0,t.nlength()));
    	for(int k=0;k<t.nlength();k++)
    	{
    		System.out.println(t.getlist(k));
    	}
    	System.out.println("4: "+t.get2(1,0,t.nlength()));
    }
}