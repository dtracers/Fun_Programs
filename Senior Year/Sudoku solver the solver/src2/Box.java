package Items;

import java.util.ArrayList;

import Variables.StaticV;

public class Box
{
	Grid g;
	private Row r;
	private Column c;
	private Square s;
	int x,y;
	private String pen="0";
	private ArrayList<String>notes=new ArrayList<String>();
	{
		for(int k=1;k<=StaticV.highestNumber;k++)
		{
			getNotes().add(""+k);
		}
	}
	public Box(int x,int y)
	{
		this.x=x;this.y=y;
	}
	public int getX()
	{
		return x;
	}
	public void setRow(Row row)
	{
		r=row;		
	}
	public void reset()
	{
		pen="0";
		ArrayList<String>notes=new ArrayList<String>();
		{
			for(int k=1;k<=StaticV.highestNumber;k++)
			{
				getNotes().add(""+k);
			}
		}
	}
	public void resetNotes()
	{
		ArrayList<String>notes=new ArrayList<String>();
		{
			for(int k=1;k<=StaticV.highestNumber;k++)
			{
				getNotes().add(""+k);
			}
		}
	}
	public int getY() {
		return y;
	}
	
	public void setCol(Column column)
	{
		c=column;	
	}
	public void setSquare(Square square)
	{
		s=square;
	}
	public void setContainer(Grid grid)
	{
		g=grid;
	}
	public Grid getContainer() {
		// TODO Auto-generated method stub
		return g;
	}
	public Grid getContainer(Sod sod)
	{
		return sod.g;
	}
	public void check()
    {
          if(getNotes().size()==1)
               setPen(getNotes().get(0));
    }
	public boolean removeAll(ArrayList<String> other)
    {
          return getNotes().removeAll(other);
    }
	public void setPen(String pen)
	{
		this.pen = pen;
		if(!pen.equals("0"))
		{
			setNotes(new ArrayList<String>());
			getNotes().add(pen);
		}
	}
	public String getPen() {
		return pen;
	}
	public String toString()
	{
		return "X "+getX()+" Y "+getY()+" number is "+getPen();
	}
	public void setNotes(ArrayList<String> notes) {
		this.notes = notes;
	}
	public ArrayList<String> getNotes() {
		return notes;
	}
	public void setPen(String string, boolean b)
	{
		setPen(string);
		if(string.equals("0"))
			superpen=false;
		else
			superpen=true;
	}
	public boolean isSuperPen()
	{
		return superpen;
	}
	public Column getColumn() {
		return c;
	}
	public Row getRow() {
		return r;
	}
	public Square getSquare() {
		return s;
	}
	private boolean superpen=false;
}
