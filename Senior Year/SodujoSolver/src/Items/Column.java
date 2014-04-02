package Items;

import java.util.ArrayList;

import Variables.StaticV;

public class Column extends Sod
{
	int x;
	Box[] list=new Box[StaticV.gridSize];
	public Column(int x)
	{
		this.x=x;
	}
	public void addBox(Box b)
    {
          list[b.getY()]=b;
          b.setCol(this);
    }
	public ArrayList<String> getPens()
	{
		ArrayList<String> j=new ArrayList<String>();
		for(int k=0;k<list.length;k++)
		{
			j.add(list[k].getPen());
		}
		return j;
	}
	public Box getBox(int x,int y)
    {
          return list[y];
    }
	public void check()
	{
		
	}
	@Override
	public ArrayList<Box> getBoxAsArray() {
		ArrayList<Box>temp=new ArrayList<Box>();
		for(int k=0;k<list.length;k++)
		{
			temp.add(list[k]);
		}
		return temp;
	}
}