
public class MyDate
{
	int dayofweek;
	int hour;
	boolean pm;
	int minute;
	int length;
	public MyDate()
	{
		
	}
	public MyDate(int k)
	{
		dayofweek=k;
	}
	public String toString()
	{
		return dayOfWeek()+" "+time();
	}
	String dayOfWeek()
	{
		return DayOfWeek.getDayOfWeek(dayofweek).name();
	}
	String time()
	{
		return ""+hour+":"+minute+""+(pm?"PM":"AM")+" length is "+minute;
	}
	public DayOfWeek getDayOfWeek()
	{
		return DayOfWeek.getDayOfWeek(dayofweek);
	}
}
enum DayOfWeek
{	
	Monday(1),Tuesday(2),Wednesday(3),Thursday(4),Friday(5),Saturday(6),Sunday(7);
	int w;
	DayOfWeek(int k)
	{
		k=w;
	}
	public static DayOfWeek getDayOfWeek(int k)
	{
		switch(k)
		{
		case 1:return Monday;
		case 2:return Tuesday;
		case 3:return Wednesday;
		case 4:return Thursday;
		case 5:return Friday;
		case 6:return Saturday;
		case 7:
		default:return Sunday;
		}
	}
	
}