import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Schedule
{
	Map<String,ArrayList<ClassBlock>> classes=new TreeMap<String,ArrayList<ClassBlock>>();
	ArrayList<String> objex;
	int minchange=0;
	public void addClass(ClassBlock k)
	{
		ArrayList<ClassBlock>temp=classes.get(k.className);
		if(temp==null)
		{
			temp=new ArrayList<ClassBlock>();
			temp.add(k);
			classes.put(k.className, temp);
		}else
		{
			temp.add(k);
		}
	}
	public Schedule clone()
	{
		Schedule copy=new Schedule();
		Set<String> sets=classes.keySet();
		Iterator<String> goth=sets.iterator();
		while(goth.hasNext())
		{
			ArrayList<ClassBlock> school=classes.get(goth.next());
			for(int k=0;k<school.size();k++)
			{
				copy.addClass(school.get(k));
			}
		}
		return copy;
	}
	public ArrayList<ClassBlock> getSuperBlock()
	{
		ArrayList<ClassBlock> superlist=new ArrayList<ClassBlock>();
		Set<String> sets=classes.keySet();
		Iterator<String> goth=sets.iterator();
		objex=new ArrayList<String>();
		while(goth.hasNext())
		{
			String g=goth.next();
			ArrayList<ClassBlock> school=classes.get(g);
			objex.add(g);
			for(int k=0;k<school.size();k++)
			{
				superlist.add(school.get(k));
				//counter++;
			}
		}
		return superlist;
	}
	public ArrayList<Schedule> generateGoodSchedule(int... rules)
	{
		Schedule start=this.clone();
		ArrayList<Schedule> list;//done in the order of first removing conflicts with rules
								//then each schedule goes through one iteration of the first class and all the others
		ArrayList<ClassBlock> superlist=getSuperBlock();
		if(rules.length>2)
			for(int k=0;k<superlist.size();k++)
			{
				if(conflict(superlist.get(k),rules[0],rules[1]))
				{
					superlist.remove(k);k-=1;
				}
			}
		
		return generateRecursiveSchedule(new ArrayList<ClassBlock>(),new ArrayList<Schedule>(),objex,0);
	}
	public ArrayList<Schedule> generateRecursiveSchedule(ArrayList<ClassBlock> cs,ArrayList<Schedule> handy
			,ArrayList<String> objects,int index)
	{
		String key=objects.get(index);
		ArrayList<ClassBlock> list=classes.get(key);
		for(int k=0;k<list.size();k++)
		{
			ClassBlock c=list.get(k);
			boolean conflict=false;
			for(int t=0;t<cs.size();t++)
			{
				if(conflict(c,cs.get(t)))
				{
					conflict=true;
					t=cs.size();
				}
			}
			if(!conflict)
			{
				if(index+1==objects.size())
				{
					Schedule mine=new Schedule();
					//mine.addClass(c);
					cs.add(c);
					System.out.println(cs);
					for(int q=0;q<cs.size();q++)
					{
						mine.addClass(cs.get(q));
					}
					handy.add(mine);
					cs.remove(cs.size()-1);
				}else
				{
					cs.add(c);
					handy.addAll(generateRecursiveSchedule(cs,handy,objects,index+1));
					cs.remove(cs.size()-1);
				}
			}
		}
		return handy;
	}
	public boolean conflict(ClassBlock one,ClassBlock two)
	{
		for(MyDate date: one.d)
		{
			for(MyDate date2: two.d)
			{
				if(date.dayofweek==date2.dayofweek)
				{
					double time1=date.pm?12:0+date.hour+((double)date.minute)/60.;
					double time2=date2.pm?12:0+date2.hour+((double)date2.minute)/60.;
					if(time1<=time2&&time1+date.length+5>=time2)
						return true;
					else if(time1>=time2&&time1+date.length+5<=time2)
						return true;
					else if(time1==time2)
						return true;
				}
			}
		}
		return false;
	}
	public boolean conflict(ClassBlock one,int starttime,int endtime)
	{
		for(MyDate date: one.d)
		{
			int addition=date.pm?12:0;
			if(date.hour+addition<starttime)
				return true;
			else if(date.hour+addition>endtime)
				return true;
		}
		return false;
	}
	public String toString()
	{
		return ""+classes;
	}
}
