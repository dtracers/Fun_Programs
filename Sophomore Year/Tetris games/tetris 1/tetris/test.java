import java.awt.*;
import java.applet.*;
import java.io.*;
import java.net.*;
import java.text.*;
import javax.swing.*;
import java.lang.*;
import java.util.*;
import java.util.EventObject;
import java.awt.AWTEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class test extends Applet implements Runnable
{
	static int levelhardness=500;
	/*
	 *this is the level hardeness 1000 is esiest
	and 100 is the hardest*/
	makeblocks block =new makeblocks();
	Random rand=new Random();
	check c=new check();
	blocks1d draw=new blocks1d();
	Thread th = new Thread (this);
	static int[][] origanlMap;//this the original map
	static int[][] middleMap;
	static int[][] newMap;//this will be the map that changes
	boolean check;
	static boolean next,notlose=true,gamestart=false;//this will be used to decide if the next block will be sent down
	final static private int bottom=1;//sets variables for the walls
	final static private int side=3;//here to
	final static private int top=2;//here to
	final static private int blnkspce=0;//this for a blnkspace
	final static private int[] dead={4,5};
	final static private int deadb=4;
	static int whichone;
	static boolean down;//used to check downward
	static boolean sidecheck; //used to check side
	static int score1=0;//score and other counting stuff
	static int count=0;
	static int lvlcount=0;
	static int count2=0;
	static String leveldata = "out.dat";
	static String otherdata ="credits.dat";
	static int xlength,ylength;
	static int caseblock;//this is which block has been chosen
	static int xcord2,ycord2=1,xcord3,ixcord,ycord3,iycord;//these are coordinates
	public void init()
	{
		delay(10000);
		try
		{
			callme();
		}
		catch (IOException e)
		{
	    	System.out.println("Exception: " + e.toString() );
	    }
	    th.start();
	}
	public void paint(Graphics g)
	{

		dotetris();
		draw.setg(g);
		draw.setmap(newMap);
		draw.setxy(xcord3,ycord3);
		draw.setmatrix();
	}
	public void dotetris()
	{
		block.eraseblock1(whichone,xcord3,ycord3);
		block.setblock(whichone);
		block.makeblock(whichone,xcord3,ycord3);
		newMap=block.returnmap();
		showscreen();
		System.out.println("x: "+xcord3+" y: "+ycord3);
	}
	public void callme() throws IOException
	{
		BufferedReader s = new BufferedReader(new FileReader(leveldata));
		Scanner b = new Scanner(new File(leveldata));
		System.out.println("this is how tetris works");
		String test;
		int rows=-1;
		int colum2=b.nextInt();
		s.readLine();
		System.out.println(colum2);
			//this will set the length and hieght for the matrix
		do
		{
			test = s.readLine();
			rows++;
		}
		while(!(test.equals("")));
		System.out.println("this is a " +rows+" by " +colum2+" map");
		origanlMap= new int[rows][colum2];//here is the origanl matrix
		newMap= new int[rows][colum2];//here is the new matrix
		middleMap=new int[rows][colum2];
		xlength=origanlMap[0].length;
		ylength=origanlMap.length;
		//showscreen(newMap,rows,colum2);
		//System.out.println("above is a blank map");
		check wit=new check();
		b.close();
	 	setmap();
	}
	public void setmap() throws IOException
	{
		System.out.println("making map");
		Scanner b = new Scanner(new File(leveldata));
		BufferedReader s = new BufferedReader(new FileReader(leveldata));
		String test;
		s.readLine();
		String row;
		char choose;
		for(int r=0; r<ylength; r++)
		{
			row = s.readLine();//this the row where the line in the file is read
			int x=row.length();
			//System.out.println(x);
			for (int c=0; c<x; c++)
			{
				//System.out.println("this is c"+c);
 				choose=row.charAt(c);//this sperates each one
				switch(choose)
				{
					case '_':   //this is for a blank space
							    middleMap[r][c]=blnkspce;
							    newMap[r][c]=blnkspce;
							    break;
					case '|' :  middleMap[r][c]=side;//this is for the side walls
							    newMap[r][c]=side;
							    break;
					case '=' :  middleMap[r][c]=bottom;//this is for the bottom
							    newMap[r][c]=bottom;
							    break;
					case '^' :  middleMap[r][c]=top;//this is for the top
							    newMap[r][c]=top;
							    break;
					case '#' :	middleMap[r][c]=deadb;//this if for a dead block
								newMap[r][c]=deadb;
					default :   break;
				}
			}
		}
		System.out.println("done making map");
	}
	private static void showscreen()
	{
		for(int r=0; r<ylength; r++)//this loop shows the matrix
			{
				System.out.print("\t");
				for(int c=0; c<xlength; c++)
				{
				System.out.print(newMap[r][c]);
				}
				System.out.println();
			}
	}
	public int addblock()
	{
		//System.out.println("Sending block");
		check=false;
		int count=0;
		//int blk;
		whichone=(rand.nextInt(10))+6;
		caseblock=whichone;
		int where;
		do
		{
			where=(rand.nextInt(xlength-2))+1;
			check=c.returnloop();
		//	System.out.println(count+" ");
			count++;
		}
		while(!check&&count<100);
		if(count>100)
		{
			where=1;
		}
		check=true;
		ycord2=1;
		xcord2=where;
		block.setmap(newMap);
		switch(whichone)
		{
			case 5  :   block.makeblock(5,where,1);break;// four by one
			case 6  :   block.makeblock(6,where,1);break;// a four by four square
			case 7  :   block.makeblock(7,where,1);break;//t shape
			case 8  :   block.makeblock(8,where,1);break;//L shape
			case 9  :   block.makeblock(9,where,1);break;//inverse L shape (Rshape)
			case 10 :	block.makeblock(11,where,1);break; //a basic block
			case 12	:	block.makeblock(12,where,1);break; //a line
			default :   //makeblock(12,newmap,1,1,blk);break; //a linebreak;
		}
		newMap=block.returnmap();
		xcord3=xcord2;
		ycord3=ycord2;
		return whichone;
	}
	public boolean keyDown(Event e,int key)
    {
    	checkd();
    	if(key == Event.LEFT)
    	{
    		//xcord2--;
    		block.eraseblock1(whichone,xcord3,ycord3);
    		xcord3--;
    		block.eraseblock1(whichone,xcord3+1,ycord3);
    		dotetris();
    	}
    	if(key == Event.RIGHT)
    	{
    		//xcord2++;
    		block.eraseblock1(whichone,xcord3,ycord3);
    		xcord3++;
    		block.eraseblock1(whichone,xcord3-1,ycord3);
    		dotetris();
    	}
    	if(key == Event.UP)
    	{
    		do
    		{
    			block.eraseblock1(whichone,xcord3,ycord3);
    			ycord3++;
    			dotetris();
    			checkd();
    		}while(down);
    	}
    	if(key == Event.DOWN)
    	{
    		//ycord2++;
    		block.eraseblock1(whichone,xcord3,ycord3);
    		ycord2++;
    		dotetris();
    	}
    	if(key == 32)
    	{
    		gamestart = true;
    	}
    	repaint();
    	return true;
    }
    private static void delay(int n)
 	{
 		long startDelay = System.currentTimeMillis();
		long endDelay = 0;
		while (endDelay - startDelay < n)
		endDelay = System.currentTimeMillis();
 	}
    public void run()
	{
		do
		{
			notlose=true;
			checkd();
			do
			{
				//newMap=block.returnmap();
				checkd();
				c.setmap(newMap);
				if(c.sendnext())
				{
					addblock();
					checkd();
				}
				if(down)
				{
				dotetris();
				repaint();
				}
				if(down)
				{
				ycord3++;
				checkd();
				}
				try
				{
					Thread.sleep(1000);
				}
				catch(InterruptedException e)
				{
				}
			}
			while(down);
			if(!down)
			{
				block.setblock(4);
				block.makeblock(whichone,xcord3,ycord3);
			}
		}while(notlose);
	}
	public void checkl()
	{
		c.setmap(newMap);
		c.setblock(caseblock);
		c.setxy(xcord3,ycord3);
		sidecheck=c.returnloop();
		//down=c.returnloopd();
	}
	public void checkd()
	{
		c.setmap(newMap);
		c.setblock(caseblock);
		c.setxy(xcord3,ycord3);
		//sidecheck=c.returnloop();
		down=c.returnloopd();
	}
}
class blocks1
{
	int [][] changemap;
	int [][] nextmap;
	int rows,colums;
	int bolcknumber;
	public blocks1()
	{
	}
	public void setblock(int blcknmbr)
	{
		bolcknumber=blcknmbr;
	}
	public void setmap(int[][] newMap)
	{
		changemap=newMap;
		rows=changemap.length;
		colums=changemap[0].length;
	}
	public void basicsquare(int x,int y)
	{
		changemap[y][x]=bolcknumber;
	}
	public void Long4(int x,int y)
	{
		changemap[y][x]=bolcknumber;
		changemap[y+1][x]=bolcknumber;
		changemap[y+2][x]=bolcknumber;
		changemap[y+3][x]=bolcknumber;
	}
	public void bigsquare(int x,int y)
	{
		changemap[y][x]=bolcknumber;
		changemap[y][x+1]=bolcknumber;
		changemap[y+1][x]=bolcknumber;
		changemap[y+1][x+1]=bolcknumber;
	}
	public void tshape(int x,int y)
	{
		changemap[y][x]=bolcknumber;
		changemap[y][x+1]=bolcknumber;
		changemap[y+1][x]=bolcknumber;
		changemap[y][x-1]=bolcknumber;
	}
	public void Lshape(int x,int y)
	{
		changemap[y][x]=bolcknumber;
		changemap[y+1][x]=bolcknumber;
		changemap[y+2][x]=bolcknumber;
		changemap[y+2][x+1]=bolcknumber;
	}
	public void Rshape(int x,int y)
	{
		changemap[y][x]=bolcknumber;
		changemap[y+1][x]=bolcknumber;
		changemap[y+2][x]=bolcknumber;
		changemap[y+2][x-1]=bolcknumber;
	}
	public int[][] returnmap()
	{
		return changemap;
	}
	public void showmap()
	{
		for(int r=0; r<rows; r++)//this loop shows the matrix
		{
			System.out.print("\t");
				for(int c=0; c<colums; c++)
			{
				System.out.print(changemap[r][c]);
			}
			System.out.println();
		}
	}
}
class blocks2
{
	int [][] changemap;
	int rows,colums;
	int blocknumber;
	public blocks2(int[][]newmap)
	{
		changemap=newmap;
		rows=changemap.length;
		colums=changemap[0].length;
	}
	public blocks2()
	{
	}
	public void setblock(int blcknmbr)
	{
		blocknumber=blcknmbr;
	}
	public void fulline(int x,int y)
	{
		for(int w=1; w<(changemap[0].length)-1; w++)
		{
			changemap[y][0+w]=blocknumber;
		}
	}
}
class makeblocks
{
	blockset set=new blockset();
	int [][] changemap;
	int rows,colums,xcord,ycord;
	int block;
	int blk;
	public makeblocks()
	{
	}
	public int[][] returnmap()
	{
		return changemap;
	}
	public void setmap(int[][] newmap)
	{
		changemap=newmap;
	}
	public void setblock(int b)
	{
		blk=set.setblock(b);
	}
	public void makeblock(int b,int xcord1,int ycord1)
	{
		block=b;
		xcord=xcord1;
		ycord=ycord1;
		block1();
	}
	public void hideblock(int b,int xcord1,int ycord1)
	{
		blk=0;
		block=b;
		xcord=xcord1;
		ycord=ycord1;
		block1();
	}
	public void specialblock(int blck)
	{
		blk=blck;
	}
	private void block1()
	{
		blocks1 s=new blocks1();
		s.setmap(changemap);
		switch(block)
		{
			case 5  :   s.setblock(blk);
						s.Long4(xcord,ycord);break;// four by one
			case 6  :   s.setblock(blk);
						s.bigsquare(xcord,ycord);break;// a four by four square
			case 7  :   s.setblock(blk);
						s.tshape(xcord,ycord);break;//t shape
			case 8  :   s.setblock(blk);
						s.Lshape(xcord,ycord);break;//L shape
			case 9  :   s.setblock(blk);
						s.Rshape(xcord,ycord);break;//inverse L shape (Rshape)
			case 10 : 	s.setblock(blk);
						s.basicsquare(xcord,ycord);break; //a basic block
			default :   block2(); break;
		}
	}
	private void block2()
	{
		blocks2 w=new blocks2(changemap);
		switch(block)
		{
			case 12 :	w.setblock(blk);
						w.fulline(xcord,ycord);break; // a full line
			default :   break;
		}
	}
	public void eraseblock1(int b,int xcor,int ycor)
	{
		xcord=xcor;
		ycord=ycor;
		blk=0;
		if(ycord<2)
		{
			hideblock(b,xcor,ycor);
		}
		else
		{
			hideblock(b,xcor,ycor-1);
		}
	}
}
class check
{
	int blocks,x,y,xcord,ycord,ylength,xlength,blk,blck,block,q;
	int score=0,seecsore,setscore,basescore,area,count;
	boolean check,check2,check3,check4,check5;
	int [][] newmap, oldMap;
	public check()
	{
	}
	public void setmap(int[][] newMap)
	{
		newmap=newMap;
	}
	public void setblock(int block2)
	{
		block=block2;
	}
	public void setxy(int x,int y)
	{
		xcord=x;
		ycord=y;
	}
//
//this method will do a check for wich block it is then it will return where
//it is in the matrix for the method check to find
//
	public boolean returnloop()
	{
		check3=false;
		check=false;
		check2=false;
		check4=false;
		boolean check5=false;
		switch(block)
		{
			case 5  :   check=checklr();// four by one
						ycord++;
						check2=checklr();
						ycord++;
						check3=checklr();
						ycord++;
						check4=checklr();
						break;
			case 6  :   check=checklr();// a four by four square
						xcord++;
						check2=checklr();
						ycord++;
						check3=checklr();
						xcord--;
						check4=checklr();
						break;
			case 7  :   check=checklr();//t shape
						ycord++;
						check2=checklr();
						ycord--;
						xcord++;
						check3=checklr();
						xcord-=2;
						check4=checklr();
						break;
			case 8  :   check=checklr();//L shape
						ycord++;
						check2=checklr();
						ycord++;
						check3=checklr();
						xcord++;
						check4=checklr();
						break;
			case 9  :   check=checklr();//inverse L shape (Rshape)
						ycord++;
						check2=checklr();
						ycord++;
						check3=checklr();
						xcord++;
						check4=checklr();
						break;
			case 10 : 	check3=checklr();//a basic block
						break;
			case 12 :   int xcord=1;
						do
						{
							check=checklr();//a full line
							xcord++;
							check2=true;
							check4=true;
							check3=true;
						}
						while((xcord<(newmap[0].length)-1)&&check);
						break;
			default :	check3=false;
						break;
		}
		if(!check||!check2||!check3||!check4)
		{
			check5=false;
		}
		else
		{
			check5=true;
		}
		return check5;
	}
//
//checks to see if any move is alowed
//
	public boolean checklr()
	{
		check2=true;
		int xlength=newmap[0].length;
		if(xcord-1>0)
		{
			if(xcord+1<xlength)
			{
				int xleft=newmap[ycord][xcord-1];//this checks to see if it can go left
				int xright=newmap[ycord][xcord+1];//this checks to see if it can go right
				if(xleft==3)
				{
					check2=false;
				}
				else if(xright==3)
				{
					check2=false;
				}
				else
				{
					check2=true;
				}
			}
			else
			{
				check2=false;
			}
		}
		else
		{
			check2=false;
		}
		return check2;
	}
//
//checks to if a certain block can move down
//
	public boolean returnloopd()
	{
		check3=false;
		check=false;
		check2=false;
		check4=false;
		boolean showblock=false;
		switch(block)
		{
			case 5  :   check=checkd();// four by one
						ycord++;
						check2=checkd();
						ycord++;
						check3=checkd();
						ycord++;
						check4=checkd();
						break;
			case 6  :   check=checkd();// a four by four square
						xcord++;
						check2=checkd();
						ycord++;
						check3=checkd();
						xcord--;
						check4=checkd();
						break;
			case 7  :   check=checkd();//t shape
						ycord++;
						check2=checkd();
						ycord--;
						xcord++;
						check3=checkd();
						xcord-=2;
						check4=checkd();
						break;
			case 8  :   check=checkd();//L shape
						ycord++;
						check2=checkd();
						ycord++;
						check3=checkd();
						xcord++;
						check4=checkd();
						break;
			case 9  :   check=checkd();//inverse L shape (Rshape)
						ycord++;
						check2=checkd();
						ycord++;
						check3=checkd();
						xcord++;
						check4=checkd();
						break;
			case 10 : 	check3=checkd();//a basic block
						break;
			case 12 :   int xcord=1;
						do
						{
							check=checkd();//a full line
							xcord++;
							check2=true;
							check4=true;
							check3=true;
						}
						while((xcord<(newmap[0].length)-1)&&check);
						break;
			default :	check=false;
						break;
		}
		if(!check||!check2||!check3||!check4)
		{
			showblock=false;
		}
		else
		{
			showblock=true;
		}
		return showblock;
	}
//
//checks to see if it can move down
//
	public boolean checkd()
	{
		check=true;
		int ylength=newmap.length;
		if(ycord+1<ylength)
		{
			int ydown=newmap[ycord+1][xcord];//this checks to see if it can go down
			if(ydown==1)
			{
				check=false;
			}
			else if(ydown==4)
			{
				check=false;
			}
			else
			{
			}
		}
		else
		{
			check=false;
		}
		return check;
	}
//
//this will eturn the xcord
//
	public int returnx()
	{
		return xcord;
	}
//
//this will eturn the ycord
//
	public int returny()
	{
		return ycord;
	}
//
//checks to see if a dead block is requiered is alowed
//
	public int checkdown(int[][] newmap,int xcord,int ycord,int block)
		{
		int blck;
		blck=block;
		int ylength=newmap.length;
		if(ylength>=ycord+1)
		{
			int ydown=newmap[ycord+1][xcord];//this checks to see if it can go down
			if(ydown==1)
			{
				blck=4;
			}
			else if(ydown==4)
			{
				blck=4;
			}
			else
			{
			}
			return blck;
		}
		else
		{
			return block;
		}
	}
//
//this checks to see if you lost
//
	public boolean lose(int[][] newmap,int xcord,int ycord)
	{
		boolean check=false;
		int y=0;
		if(ycord-1>0)
		{
			int yup=newmap[ycord-1][xcord];
			if(yup==2)
			{
				check=true;
			}
			return check;
		}
		else
		{
			if(y+1==4)
			{
				check=false;
				return check;
			}
			else
			{
				check=true;
				return check;
			}
		}
	}
//
//checks to see if the next block can be sent down
//
	public boolean sendnext()
	{
		check4=false;
		ylength=newmap.length;
		xlength=newmap[0].length;
		count=0;
		area=ylength*xlength;
		for (y=0; y<ylength; y++)
		{
			for(x=0; x<xlength; x++)
			{
				q=newmap[y][x];
				if(q==3||q==2||q==1||q==0||q==4)
				{
					count++;
				}
			}
		}
		if (count==area)
		{
			check4=true;
			//System.out.println("sending the next block");
		}
		return check4;
	}
////
////
////this will be used to count score please don't change this to make it easier
////that will be cheating!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
////
	public int score()
	{
		boolean wht=true;
		ylength=newmap.length;
		xlength=newmap[0].length;
		setscore=0;
		for (y=1; y<ylength-1; y++)
		{
			setscore=0;
			x=0;
			do
			{
				setscore+=newmap[y][x];
				//System.out.println(setscore);
				//delay(1000);
				basescore=(4*(xlength-2))+6;
				if(setscore==basescore)
				{
					//System.out.println(basescore);
					//delay(1000);
					score++;
					wht=false;
				}
				x++;
			}while(x<xlength&&wht);
		}
		return score;
	}
	public int[][] mapscore()
	{
		boolean wht=true;
		ylength=newmap.length;
		xlength=newmap[0].length;
		setscore=0;
		for (y=1; y<ylength-1; y++)
		{
			setscore=0;
			x=0;
			do
			{
				setscore+=newmap[y][x];
				basescore=(4*(xlength-2))+6;
				if(setscore==basescore)
				{
					wht=false;
					for(int sew=1; sew<xlength-1; sew++)
					{
						newmap[y][sew]=0;

					}
					newmap=mapset(y);
				}
				x++;
			}while(x<xlength&&wht);
		}
		return newmap;
	}
	public int[][] mapset(int ycord)
	{
		ylength=newmap.length;
		xlength=newmap[0].length;
		int oldcord=0;
		int[][] nextmap;
		nextmap=newmap;
		for (y=ycord-1; y>1; y--)
		{
			oldcord=0;
			x=0;
			for(x=0; x<xlength; x++)
			{
				oldcord=newmap[y][x];
				basescore=4;
				if(setscore==basescore)
				{
						nextmap[y+1][x]=4;
						//System.out.println("mathch found");
						nextmap[y][x]=0;
						delay(1000);
				}

			}
		}
		newmap=nextmap;
		return newmap;
	}
	public int level(int level)
	{
		int count1=level;
		count=0;
		basescore=5;
		if(level>=1&&level<3)
		{
			count=10;
		}
		else if(level>3)
		{
			count1=count1-3;
			x=count1*5;
			count=basescore+x;
		}
		return count;
	}
	private static void delay(int n)
 	{
 		long startDelay = System.currentTimeMillis();
		long endDelay = 0;
		while (endDelay - startDelay < n)
		endDelay = System.currentTimeMillis();
 	}
}
class blockset
{
	Graphics g2;
	int block,blk,blck,block2;
	public blockset()
	{
	}
	public int setblock(int blok)
	{
		block=blok;
		blck=list0();
		return blck;
	}
	public int list0()
	{
		switch(block)
		{
			case 0  :   blk=0; break;//blankspace
			case 1  :   blk=1; break;// the bottom
			case 2  :   blk=2; break;//the top
			case 3  :   blk=3; break;//the walls
		//	case 9  :   blk=9; break;//inverse L shape (Rshape)
		//	case 10 : 	blk=11; break; //a basic block
			case 4  :   blk=4; break;//a dead block
			default :   blk=list1(); break;
		}
		return blk;
	}
	public int list1()
	{
		switch(block)
		{
			case 5  :   blk=5; break;// four by one
			case 6  :   blk=6; break;// a four by four square
			case 7  :   blk=7; break;//t shape
			case 8  :   blk=8; break;//L shape
			case 9  :   blk=9; break;//inverse L shape (Rshape)
			case 10 : 	blk=10; break; //a basic block
			case 4  :   blk=4; break;//a dead block
			default :   blk=list2(); break;
		}
		return blk;
	}
	public int list2()
	{
		switch(block)
		{
			case 12	:	blk=12; break;//full line
			case 13	:	blk=13; break;//sucks for you cause it's random
			case 14	:	blk=14; break;//
			case 15	:	blk=15; break;//
			case 16	:	blk=16; break;//
			case 17	:	blk=17; break;//
			case 18	:	blk=18; break;//
			default	 :	break;
		}
		return blk;
	}

}
class blocks1d//these are the original tetris blocks
{
	Graphics g;
	private int[][] newmap,oldmap,middlemap;
	private int x,y,size=16,blk,xlength,ylength,block;
	private Color c;
	private boolean rndm=false;//this will decide if the color is random or not
	public void setg(Graphics g2)
	{
		g=g2;
	}
	public blocks1d()
	{
	}
	public void setmap(int[][] Newmap)
	{
		newmap=Newmap;
	}
	public void normal(int special)
	{
		switch(special)
		{
			case 1	: size=16; break;//this is the normal size
			case 2	: size=16*4; break;//this is the super large size
			case 3	: size=4; break;// this is the tiny size*
			default	: size=16; break;
		}
	}
	public void setrndm(boolean rndma)
	{
		rndm=rndma;
	}
	public void setxy(int xcord,int ycord)
	{
		x=xcord;
		y=ycord;
	}
	private void setblock()
	{
		 block=newmap[y][x];
	}
	private Color choosecolor()
	{
		int r,g,b;
		Random rand=new Random();
		int r1,g1,b1;
		r1=rand.nextInt(255);
		g1=rand.nextInt(255);
		b1=rand.nextInt(255);
		switch(block)
		{
			case 0  :   r=0;g=0;b=0;break;
			case 1  :   r=50;g=50;b=50;break;
			case 2	:   r=25;g=25;b=25;break;
			case 3  :   r=50;g=50;b=50;break;
			case 4	:   r=255;g=106;b=130;break;
			case 5  :   r=50;g=250;b=250;break;// four by one
			case 6  :   r=255;g=0;b=0;break;// a four by four square
			case 7  :   r=0;g=0;b=255;;break;//t shape
			case 8  :	r=75;g=249;b=51;break;//L shape
			case 9  :	r=233;g=52;b=248;break;//inverse L shape (Rshape)
			case 10 : 	r=255;g=255;b=0;break; //a basic block
			case 12 :   r=255;g=255;b=255;break;//a full line
			default :   r=0;g=0;b=0;break;
		}
		if(!rndm)
		{
			c=new Color(r,g,b);
		}
		else
		{
			c=new Color(r1,g1,b1);
		}
		switch(block)
		{
			case 0  :   r=0;g=0;b=0;c=new Color(r,g,b); break;
			case 1  :   r=70;g=70;b=70;c=new Color(r,g,b);break;
			case 2	:   r=50;g=50;b=50;c=new Color(r,g,b);break;
			case 3  :   r=50;g=50;b=50;c=new Color(r,g,b);break;
			case 4	:   r=255;g=106;b=130;c=new Color(r,g,b);break;
		}
		return c;
	}
	public void setmatrix()
	{
		g.setColor(Color.white);
		for(y=0; y<ylength; y++)
		{
			for(x=0; x<xlength; x++)
			{
				setblock();
				c=choosecolor();
				drawmatrix(x,y,c);
			}
		}
	}
	public void drawmatrix(int xcord,int ycord, Color co)
	{
		g.setColor(co);
		g.fillRect(xcord+(size*(xcord-1))+150,ycord+(size*(ycord-1))+20,size,size);
		g.setColor(Color.black);
		g.drawRect(xcord+(size*(xcord-1))+150,ycord+(size*(ycord-1))+20,size+1,size+1);
	}
	/*public void choosewhich(int x1,int y1,int block)
	{
		x=x1+100;
		y=y1+50;
		blk=block;
		c=choosecolor();
		switch(blk)
		{
			case 5  :   Long4(x,y,size,c);break;// four by one
			case 6  :   bigsquare(x,y,size,c);break;// a four by four square
			case 7  :   tshapedown(x,y,size,c);break;//t shape
			case 8  :	Lshape(x,y,size,c);break;//L shape
			case 9  :	RLshape(x,y,size,c);break;//inverse L shape (Rshape)
			case 10 : 	basicsquare(x,y,size,c);break; //a basic block
			default :   break;
		}
	}*/
	public void basicsquare(int x,int y,int size,Color c)
	{
		g.setColor(c);
		g.fillRect(x,y,size,size);
		g.setColor(Color.black);
		g.drawRect(x,y,size,size);
	}
	public void bigsquare(int x,int y,int size,Color c)
	{
		basicsquare(x,y,size,c);
		basicsquare(x+size,y,size,c);
		basicsquare(x+size,y+size,size,c);
		basicsquare(x,y+size,size,c);
	}
	public void tshapedown(int x,int y,int size,Color c)
	{
		basicsquare(x,y,size,c);
		basicsquare(x,y+size,size,c);
		basicsquare(x+size,y+size,size,c);
		basicsquare(x-size,y+size,size,c);
	}
	public void RLshape(int x,int y,int size,Color c)
	{
		basicsquare(x,y,size,c);
		basicsquare(x+size,y,size,c);
		basicsquare(x+size+size,y,size,c);
		basicsquare(x+size+size,y+size,size,c);
	}
	public void Lshape(int x,int y,int size,Color c)
	{
		basicsquare(x,y,size,c);
		basicsquare(x-size,y,size,c);
		basicsquare(x-size-size,y,size,c);
		basicsquare(x-size-size,y+size,size,c);
	}
	public void Long4(int x,int y,int size,Color c)
	{
		basicsquare(x,y,size,c);
		basicsquare(x,y+size,size,c);
		basicsquare(x,y+size+size,size,c);
		basicsquare(x,y+size+size+size,size,c);
	}
	public void Long5(int x,int y,int size,Color c)
	{
		basicsquare(x,y,size,c);
		basicsquare(x,y+size,size,c);
		basicsquare(x,y+size+size,size,c);
		basicsquare(x,y+size+size+size,size,c);
		basicsquare(x,y+size+size+size+size,size,c);
	}
}
