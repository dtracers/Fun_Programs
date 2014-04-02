package Items;
//TODO make it check so that if in a certain row there is only two places it can be and those two places are in the same square
//TODO then the rest of the square can't have those two spots
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

import javax.swing.table.TableModel;

import Variables.StaticV;

public class Grid
{
    private Column[] c=new Column[StaticV.gridSize];
    private Row[] r=new Row[StaticV.gridSize];
    private Square[][] s=new Square[StaticV.squareSize][StaticV.squareSize];
    public void createGrid()
    {
          for(int y=0;y<StaticV.squareNumber;y++)
          {
               for(int x=0;x<StaticV.squareNumber;x++)
               {
                     getSquare()[y][x]=new Square(y,x);
                     for(int k=0;k<StaticV.squareSize;k++)
                     {
                          int y2=y*StaticV.squareSize+k;
                          if(getRow()[y2]==null)
                               getRow()[y2]=new Row(y2);
                          for(int q=0;q<StaticV.squareSize;q++)
                          {
                                int x2=x*StaticV.squareSize+q;
                                Box b=new Box(x2,y2);
                                b.setContainer(this);
                                if(getColumn()[x2]==null)
                                     getColumn()[x2]=new Column(x2);
                               getColumn()[x2].addBox(b);
                               getRow()[y2].addBox(b);
                               getSquare()[y][x].addBox(b);
                          }
                    }
               }
          }
    }
    public void createGridFromAStream(BufferedReader r)
    {
    	for(int k=0;k<StaticV.gridSize;k++)
		{
			String s = null;
			try {
				s = r.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//	System.out.println(s);
			for(int q=0;q<StaticV.gridSize;q++)
			{
				getBox(q,k).setPen(""+s.charAt(q),true);
			}
		}
    	System.out.println(this);
    }
    public void createGridFromAStream(BufferedReader r,int j)
    {
    	for(int k=0;k<StaticV.gridSize;k++)
		{
			String s = null;
			try {
				s = r.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//	System.out.println(s);
			for(int q=0;q<StaticV.gridSize;q++)
			{
				getBox(q,k).setPen(""+Integer.parseInt(""+s.charAt(q),j+1),true);
			}
		}
    	System.out.println(this);
    }

    public Box getBox(int x,int y)
    {
    	return getRow()[y].getBox(x, y);
    }
    /**
     * this will slowly remove all the notes by checking with other pens if there is only one note it will change that note to a pen
     * @param place the box where it checks
     */
    public void check(Box place)
    {
    	if(place.getPen().equals("0"))
    	{
    		ArrayList<String> temp=place.getColumn().getPens();
    		place.removeAll(temp);
    		temp=place.getRow().getPens();
    		place.removeAll(temp);
    		temp=place.getSquare().getPens();
    		place.removeAll(temp);
    		place.check();
    	}
    }
    /**
     * this will receive the place then check to see if there is a place where only one number will fit if there is it will change that number to a pen
     * @param place the Sod where it checks could be a row column or square
     */
    public void check(Sod place)
    {
    	class numberBox
    	{
    		public numberBox(int nu,int t,Box b)
    		{
    			num=nu;
    			number=t;
    			this.b=b;
    		}
    		int num;
    		int number;
    		Box b;
    	}
    	ArrayList<Box> boxes=place.getBoxAsArray();
    	numberBox[] s=new numberBox[StaticV.highestNumber];
    	for(int k=1;k<=StaticV.highestNumber;k++)
    	{
    		s[k-1]=new numberBox(k,0,null);
    	}
    	for(int k=0;k<boxes.size();k++)
    	{
    		Box btemp=boxes.get(k);
    		ArrayList<String> notes=btemp.getNotes();
    		for(int q=0;q<notes.size();q++)
    		{
    			numberBox nB=s[Integer.parseInt(notes.get(q))-1];
    			if(nB.number==0)
    			{
    //				System.out.println("number on a box is "+nB.num);
    				nB.number=1;
    				nB.b=btemp;
    			}else
    			{
    	//			System.out.println("number on a box is more "+nB.num);
    				nB.number++;
    				nB.b=null;
    			}
    		}
    	}
    	for(int k=0;k<s.length;k++)
    	{
    	//	System.out.println(s[k].num+" has this many "+s[k].number);
    		if(s[k].number==1)
    		{
    		//	System.out.println("only number is "+s[k].num);
    			s[k].b.setPen(""+s[k].num);
    		//	System.out.println(s[k].b);
    		}
    	}
    }
    /**
     * this will check one individual square for the case where there are multiple spots open but only in one row or column as a result there can not be any
     * more of that number in a different square in that same row or column
     * @param s
     */
    public void check(Square s,boolean h)
    {
    	//System.out.println("I'm checking in the square place right now");
    	Box[][] box=s.s;
    	//this will check every column in the square
    	for(int k=0;k<box[0].length;k++)
    	{
    		checkCol(s,k);
    	}
    	//this will check every row
    	for(int k=0;k<box.length;k++)
    	{
    		checkRow(s,k);
    	}
    	
    }
    public void checkCol(Square s,int n)
    {
    	Box[][] box=s.s;
    	//will add all notes from column n
    	ArrayList<Box> rem=new ArrayList<Box>();
    	ArrayList list=new ArrayList<NumberCounter>();
    	for(int k=0;k<box.length;k++)
    	{
    		rem.add(box[k][n]);
    		for(String j:box[k][n].getNotes())
    		{
    			NumberCounter temp=new NumberCounter(j);
    			if(list.contains(temp))
    			{
    				((NumberCounter)list.get(list.indexOf(temp))).count+=1;
    			}else
    			{
    				temp.count=1;
    				list.add(temp);
    			}
    		}
    	}
    	//checks to make sure that it has enoug numbers to do it with
    	ArrayList<NumberCounter> list2=new ArrayList<NumberCounter>();
    	for(int k=0;k<list.size();k++)
    	{
    		if(((NumberCounter)list.get(k)).count>=2)
    		{
    			list2.add((NumberCounter) list.get(k));
    		}
    	}
    	//if there is not enough numbers there is no point in continuing
    	if(list2.size()<=0)
    		return;
    	
    	
    	///this will remove all numbers that are present in other columns in this square
    	for(int t=0;t<list2.size();t++)
    	{
    		for(int k=0;k<box.length;k++)
    		{
    			for(int q=0;q<box.length;q++)
    			{
    				if(q!=n)
    				{
	    				for(String j:box[k][q].getNotes())
	    	    		{
	    	    			NumberCounter temp=new NumberCounter(j);
	    	    			if(list2.contains(temp))
	    	    			{
	    	    				list2.remove(temp);
	    	    			}
	    	    		}
    				}
    			}
    		}
    	}
    	//if there is not enough numbers there is no point in continuing
    	if(list2.size()<=0)
    		return;
    	
    	//we have now proven then there are multiple spots for this number but it is only present in this one column
    	//therefore it cannot be present in any other squares in this column therefore we must remove these
    	
    	list=new ArrayList<String>();
    	for(int k=0;k<list2.size();k++)
    	{
    		list.add(list2.get(k).number);
    	}
    	list2=null;
    	
    	
    	
    	Column c=box[0][n].getColumn();
    	Box[] removers=c.list;
    	for(int k=0;k<removers.length;k++)
    	{
    		//if it is not one of the does that already has this note needed
    		if(!rem.contains(removers[k]))
    		{
    			removers[k].getNotes().removeAll(list);
    		}
    	}
    }
    public void checkRow(Square s,int n)
    {
    	Box[][] box=s.s;
    	//will add all notes from column n
    	ArrayList<Box> rem=new ArrayList<Box>();
    	ArrayList list=new ArrayList<NumberCounter>();
    	for(int k=0;k<box[0].length;k++)
    	{
    		rem.add(box[n][k]);
    		for(String j:box[n][k].getNotes())
    		{
    			NumberCounter temp=new NumberCounter(j);
    			if(list.contains(temp))
    			{
    				((NumberCounter)list.get(list.indexOf(temp))).count+=1;
    			}else
    			{
    				temp.count=1;
    				list.add(temp);
    			}
    		}
    	}
    	//checks to make sure that it has enoug numbers to do it with
    	ArrayList<NumberCounter> list2=new ArrayList<NumberCounter>();
    	for(int k=0;k<list.size();k++)
    	{
    		if(((NumberCounter)list.get(k)).count>=2)
    		{
    			list2.add((NumberCounter) list.get(k));
    		}
    	}
    	//if there is not enough numbers there is no point in continuing
    	if(list2.size()<=0)
    		return;
    	
    	
    	///this will remove all numbers that are present in other columns in this square
    	for(int t=0;t<list2.size();t++)
    	{
    		for(int k=0;k<box.length;k++)
    		{
    			for(int q=0;q<box.length;q++)
    			{
    				if(q!=n)
    				{
	    				for(String j:box[q][k].getNotes())
	    	    		{
	    	    			NumberCounter temp=new NumberCounter(j);
	    	    			if(list2.contains(temp))
	    	    			{
	    	    				list2.remove(temp);
	    	    			}
	    	    		}
    				}
    			}
    		}
    	}
    	//if there is not enough numbers there is no point in continuing
    	if(list2.size()<=0)
    		return;
    	
    	//we have now proven then there are multiple spots for this number but it is only present in this one column
    	//therefore it cannot be present in any other squares in this column therefore we must remove these
    	
    	list=new ArrayList<String>();
    	for(int k=0;k<list2.size();k++)
    	{
    		list.add(list2.get(k).number);
    	}
    	list2=null;
    	
    	
    	
    	Row c=box[n][0].getRow();
    	Box[] removers=c.list;
    	for(int k=0;k<removers.length;k++)
    	{
    		//if it is not one of the does that already has this note needed
    		if(!rem.contains(removers[k]))
    		{
    			removers[k].getNotes().removeAll(list);
    		}
    	}
    }
    /**
     * this will check in a column if there is a number that can only be in at least two places and those two+ places are in the same square
     * then those can be the only of that number in that square
     * @param col
     */
    public void checkColumn(Column col)
    {
    	ArrayList<NumberCounter> list=new ArrayList<NumberCounter>();
    	int counter=0;
    	for(int k=0;k<StaticV.squareNumber;k++)
    	{
    		for(int q=0;q<StaticV.squareSize;q++)
    		{
    			
    		}
    	}
    }
    public void checkRow(Row row)
    {
    	
    }
    class NumberCounter
	{
		public NumberCounter(String j)
		{
			number=j;
		}
		String number;
		int count;
		int counter2;
		public boolean equals(Object j)
		{
			NumberCounter j2=(NumberCounter)j;
			return j2.number.equals(number);
		}
	}
    public String toString()
    {
    	String g="";
    	for(int k=0;k<StaticV.gridSize;k++)
    	{
    		if(k>0&&k%StaticV.squareSize==0)
			{
    			int comp=StaticV.squareSize*2+StaticV.squareNumber*2+StaticV.gridSize;
    			for(int temp=0;temp<comp;temp++)
    				g+="-";
    			g+="\n";
			}
    		for(int q=0;q<StaticV.gridSize;q++)
    		{
    			if(q+1==StaticV.gridSize)
    			{
    				g+=getBox(q,k).getPen()+"\n";
    			}
    			else if(q>0&&q%StaticV.squareSize==0)
    			{
    				g+="||"+getBox(q,k).getPen()+"|";
    			}else
    			{
    				g+=getBox(q,k).getPen()+"|";
    			}
    		}
    	}
    	return g;
    }
    public String toString2()
    {
    	String g="";
    	for(int k=0;k<StaticV.gridSize;k++)
    	{
    		for(int q=0;q<StaticV.gridSize;q++)
    		{
    			if(q+1==StaticV.gridSize)
    			{
    				g+=getBox(q,k).getPen()+"\n";
    			}
    			else if(q>0&&q%StaticV.squareSize==0)
    			{
    				g+=getBox(q,k).getPen();
    			}else
    			{
    				g+=getBox(q,k).getPen();
    			}
    		}
    	}
    	return g;
    }

    public void setColumn(Column[] c)
	{
		this.c = c;
	}
	public Column[] getColumn()
	{
		return c;
	}
	public void setRow(Row[] r)
	{
		this.r = r;
	}
	public Row[] getRow()
	{
		return r;
	}
	public void setSqruare(Square[][] s)
	{
		this.s = s;
	}
	public Square[][] getSquare()
	{
		return s;
	}
	public Box[][] getBoxesAsMatrix()
	{
		Box[][] g=new Box[StaticV.gridSize][StaticV.gridSize];
		for(int k=0;k<r.length;k++)
		{
			Box[] l=r[k].list;
			for(int q=0;q<l.length;q++)
			{
				g[k][q]=l[q];
			}
		}
		// TODO Auto-generated method stub
		return g;
	}
	public static void checkAll(Grid g)
	{
		//System.out.println("checking all");
		for(int k=0;k<StaticV.gridSize;k++)
		{
			for(int q=0;q<StaticV.gridSize;q++)
			{
				g.check(g.getBox(q,k));
			}
		}	
		for(int k=0;k<StaticV.gridSize;k++)
		{
			g.check(g.getColumn()[k]);
			g.check(g.getRow()[k]);
		}
		for(int k=0;k<3;k++)
		{
			for(int q=0;q<3;q++)
			{
				g.check((Sod)g.getSquare()[k][q]);
			}
		}
		for(int k=0;k<3;k++)
		{
			for(int q=0;q<3;q++)
			{
				g.check(g.getSquare()[k][q],true);
			}
		}
	}
	public void reset()
	{
		for(int k=0;k<StaticV.gridSize;k++)
		{
			for(int q=0;q<StaticV.gridSize;q++)
			{
				getBox(q,k).reset();
			}
		}
	}
	public void resetNotes()
	{
		for(int k=0;k<StaticV.gridSize;k++)
		{
			for(int q=0;q<StaticV.gridSize;q++)
			{
				getBox(q,k).resetNotes();
			}
		}
	}
	public void checkNotes()
	{
		for(int k=0;k<StaticV.gridSize;k++)
		{
			for(int q=0;q<StaticV.gridSize;q++)
			{
				check(getBox(q,k));
			}
		}
	}
	public void checkLocations()
	{
		for(int k=0;k<StaticV.gridSize;k++)
		{
			check(getColumn()[k]);
			check(getRow()[k]);
		}
		for(int k=0;k<3;k++)
		{
			for(int q=0;q<3;q++)
			{
				check((Sod)getSquare()[k][q]);
			}
		}
	}
	public void checkSquares()
	{
		for(int k=0;k<3;k++)
		{
			for(int q=0;q<3;q++)
			{
				check(getSquare()[k][q],true);
			}
		}
	}
	public void checkRowsCols()
	{
		
	}

}
/*
format for soduko
0|0|0|||0|0|0|||0|0|0
0|0|0|||0|0|0|||0|0|0
0|0|0|||0|0|0|||0|0|0
---------------------
0|0|0|||0|0|0|||0|0|0
0|0|0|||0|0|0|||0|0|0
0|0|0|||0|0|0|||0|0|0
---------------------
0|0|0|||0|0|0|||0|0|0
0|0|0|||0|0|0|||0|0|0
0|0|0|||0|0|0|||0|0|0
*/