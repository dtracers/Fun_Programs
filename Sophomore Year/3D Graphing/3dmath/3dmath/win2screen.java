/**
 * win2screen.java  7/27/2008
 *
 * @author - Jane Doe
 * @author - Period n
 * @author - Id nnnnnnn
 *
 * @author - I received help from ...
 *
 */


public class win2screen extends variables
{
	static boolean t=false;
	public static double converty(double tempy,double z)
	{
		if(!t)
		calcdistance(winsize,angle);
    	return pany-screeny-spacetoviewy(tempy-pany0,z+panz);
	}
	public static double convertx(double tempx,double z)
	{
		if(!t)
		calcdistance(winsize,angle);
		return spacetoviewx(tempx+panx0,z+panz)+panx-screenx;
	}
	public static double spacetoviewx(double x,double z)
	{
		return x*distance/(z);
	}
	public static double spacetoviewy(double y,double z)
	{
		return y*distance/(z);
	}
	public static void calcdistance(double winsize,double angle)
	{
		t=true;
		distance=(winsize/2)/Math.tan(angle/2);
	}
	public static double viewxtospace(double x,double z)
	{
		return x/distance*(z);
	}
	public static double viewytospace(double y,double z)
	{
		return y/distance*(z);
	}
}