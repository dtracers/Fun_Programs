import java.util.ArrayList;

import javax.swing.JOptionPane;


public class TempMain
{
	public static void main(String args[])
	{
		Schedule s=new Schedule();
		enterclasses(s);
		System.out.println(s);
	};
	public static void enterclasses(Schedule s)
	{
		String name="dumby";
		while(!name.equals("done"))
		{
			name=JOptionPane.showInputDialog("enter name of class");
			if(!name.equals("done"))
			{
				MyDate[] dates=getDates();
				s.addClass(new ClassBlock(name,dates));
			}
		}
	}
	public static MyDate[] getDates()
	{
		ArrayList<MyDate>list=new ArrayList<MyDate>();
		String name="dumby";
		int startindex=0;
		while(!name.equals("done"))
		{
			int endindex=startindex;
			name=JOptionPane.showInputDialog("enter dates in format of day/hour:mminutes/*/length\nwhere * means you put AM or PM in that place and length is in minutes");
			String[]w=name.split("/");
			if(!name.equals("done"))
			{
				String swatch=w[2];
				int length=Integer.parseInt(w[3]);
				for(int k=0;k<w[0].length();k++)
				{
					switch(w[0].charAt(k))
					{
						case 'M':
						case 'm':
						{
							endindex++;
							list.add(new MyDate(1));
						}break;
						case 'T':
						case 't':
						{
							endindex++;
							list.add(new MyDate(2));
						}break;
						case 'W':
						case 'w':
						{
							endindex++;
							list.add(new MyDate(3));
						}break;
						case 'R':
						case 'r':
						{
							endindex++;
							list.add(new MyDate(4));
						}break;
						case 'F':
						case 'f':
						{
							endindex++;
							list.add(new MyDate(5));
						}break;
					}
				}
				w=w[1].split(":");
				int hour=Integer.parseInt(w[0]);
				int minute=Integer.parseInt(w[1]);
				for(int k=startindex;k<endindex;k++)
				{
					list.get(k).hour=hour;
					list.get(k).minute=minute;
					list.get(k).length=length;
					list.get(k).pm=swatch.equals("PM");
				}
				startindex=endindex;
			}
		}
		MyDate[] list2=new MyDate[list.size()];
		for(int k=0;k<list2.length;k++)
		{
			list2[k]=list.get(k);
		}
		return list2;
	}
	public void save()
	{
		PrintStream
	}
}
