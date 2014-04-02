package Items;

import java.util.ArrayList;

import Variables.StaticV;

public class Square extends Sod
{
	int y,x;
	Box[][] s=new Box[StaticV.squareSize][StaticV.squareSize];
	public Square(int y,int x)
	{
		this.y=y;this.x=x;
	}
	public Box getBox(int x,int y)
    {
          return s[y%StaticV.squareSize][x%StaticV.squareSize];
    }
	public void addBox(Box b)
    {
		s[b.getY()%StaticV.squareSize][b.getX()%StaticV.squareSize]=b;
    	b.setSquare(this);
    }
	public ArrayList<String> getPens()
	{
		ArrayList<String> j=new ArrayList<String>();
		for(int k=0;k<s.length;k++)
		{
			for(int q=0;q<s[k].length;q++)
				j.add(s[k][q].getPen());
		}
		return j;
	}
	public void check()
	{
		
	}
	@Override
	public ArrayList<Box> getBoxAsArray() {
		ArrayList<Box>temp=new ArrayList<Box>();
		for(int q=0;q<s.length;q++)
		for(int k=0;k<s[q].length;k++)
		{
			temp.add(s[q][k]);
		}
		return temp;
	}
}
