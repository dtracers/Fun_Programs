import java.awt.*;
import java.applet.*;
import java.io.*;
import java.net.*;
import java.text.*;
import javax.swing.*;
import java.lang.*;
import java.util.*;
public class tetrisworksshow extends Applet
{
	static int levelhardness=500;
	/*
	 *this is the level hardeness 1000 is esiest
	and 100 is the hardest*/
	static Graphics g1;
	static int[][] origanlMap;//this the original map
	static int[][] newMap;//this will be the map that changes
	static private boolean next;//this will be used to decide if the next block will be sent down
	final static private int bottom=1,side=3,top=2;//sets variables for sides
	static private int blnkspce=0,score1=0,count=0,lvlcount=0,count2=0;
	static String leveldata = "out.dat";
	static int caseblock;//this is which block has been chosen
	static int xcord2,ycord2,xxcor2,yycor2,xxcor3,yycor3;//these are coordinates
	//public void init()
	//{
//			paint(g1);
//	}
	public void paint(Graphics g)
	{
		//g1=g;
		try
		{
			callme(g);
		}
		catch (IOException e)
		{
	    	System.out.println("Exception: " + e.toString() );
	    }
	}
	public static void main(String args[])
	{
		try
		{
			callme(g1);
		}
		catch (IOException e)
		{
	    	System.out.println("Exception: " + e.toString() );
	    }
	}
	public static void callme(Graphics g) throws IOException
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
		//showscreen(newMap,rows,colum2);
		//System.out.println("above is a blank map");
		check wit=new check();
		b.close();
		addblock(origanlMap,newMap,rows,colum2);
	 	setmap(origanlMap,newMap,rows,colum2);
	 	int blocks=returncase();
	 	next=true;
	 	blocks1d draw=new blocks1d(g,false,origanlMap);
	 	g.setColor(Color.white);
	 	g.fillRect(0,0,1000,1000);
	 	draw.setmatrix();
	 	do
	 	{
	 		int count1=wit.level(lvlcount);
	 		score1=0;
	 		do
	 		{
	 			if(count==0)
	 			{
	 				//addblock(origanlMap,newMap,rows,colum2);
	 				moveblock(blocks,newMap,xcord2,ycord2,levelhardness,rows,colum2,score1,lvlcount,g);
	 				score1=wit.score(newMap);
	 				newMap=wit.mapscore(newMap);
	 				//newMap=wit.mapset(newMap);
	 			}
	 			next=wit.sendnext(newMap);
	 			if(next&&count!=0)
	 			{
	 				blocks=addblock(origanlMap,newMap,rows,colum2);
	 				moveblock(blocks,newMap,xcord2,ycord2,levelhardness,rows,colum2,score1,lvlcount,g);
	 				score1=wit.score(newMap);
	 				newMap=wit.mapscore(newMap);
	 				//newMap=wit.mapset(newMap);
	 			}
	 			newMap=wit.mapscore(newMap);
	 			xcord2=0;ycord2=0;blocks=0;
	 			next=wit.sendnext(newMap);
	 			count++;
	 			//System.out.println("your score is " +score1);
	 		}while(score1<count1&&next);
	 		if(next)
	 		{
	 			lvlcount++;
	 			score1=0;
	 			newMap=origanlMap;
	 			System.out.println("you win!!! next level");
	 		}
	 		else
	 		{
	 			System.out.println("you lost!!!");
	 			next=false;
	 		}
	 		reset();
	 	}while(next);
	}
	//
	//
	//this now the methods that are called in main
	//
	//
//this method delays time
	private static void delay(int n)
 	{
 		long startDelay = System.currentTimeMillis();
		long endDelay = 0;
		while (endDelay - startDelay < n)
		endDelay = System.currentTimeMillis();
 	}
//this method will return case # (the # that choose what block it will be)
	private static int returncase()
	{
		return caseblock;
	}
//this method will return the map
	private static int[][] returnmap()
	{
		return newMap;
	}
	private static int getx2()
	{
		return xcord2;
	}
	private static int gety2()
	{
		return ycord2;
	}
	private static int getxx()
	{
		return xxcor2;
	}
	private static int getyy()
	{
		return yycor2;
	}
	private static int addxx(int add)
	{
		int w=xxcor2+add;
		return w;
	}
	private static int addyy(int add)
	{
		int w=yycor2+add;
		return w;
	}
	private static void setxx(int set)
	{
		xxcor2=set;
	}
	private static void setyy(int set)
	{
		yycor2=set;
	}
//this mothed will show the sceen
	private static void showscreen(int[][] newMap,int rows,int colums)
	{
		for(int r=0; r<rows; r++)//this loop shows the matrix
			{
				System.out.print("\t");
				for(int c=0; c<colums; c++)
				{
				//System.out.print(newMap[r][c]);
				}
				System.out.println();
			}
	}
	public boolean keyDown(Event e,int id)
	{
		int x1,y1;
		int x2,y2;
		if (id == Event.LEFT)
			{
				x1=getxx();
				x2=addxx(-1);
				setxx(x2);
			}
			else if (id == Event.RIGHT)
			{
				x1=getxx();
				x2=addxx(1);
				setxx(x2);
			}
			else if (id == Event.UP)
			{

			}
			else if (id == Event.DOWN)
			{
				y1=getyy();
				y2=addyy(1);
				setyy(y2);
			}
			return true;
	}
/*
 *those above were helper methods
 */

//	this method will make the origanl map
	public static void setmap(int[][] origanlMap,int[][] newmap,int ylength,int xlength) throws IOException
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
							    origanlMap[r][c]=blnkspce;
							    newmap[r][c]=blnkspce;
							    break;
					case '|' :  origanlMap[r][c]=side;//this is for the side walls
							    newmap[r][c]=side;
							    break;
					case '=' :  origanlMap[r][c]=bottom;//this is for the bottom
							    newmap[r][c]=bottom;
							    break;
					case '^' :  origanlMap[r][c]=top;//this is for the top
							    newmap[r][c]=top;
							    break;
					default :   break;
				}
			}
		}
		System.out.println("done making map");
	}
//
//this method will choose a block
//
	public static int addblock(int[][] origanlMap,int[][] newmap,int ylength,int xlength)
	{
		check c=new check();
		//System.out.println("Sending block");
		boolean check=false;
		int count=0;
		int blk;
		Random rand=new Random();
		int whichone=(rand.nextInt(10))+5;
		caseblock=whichone;
		int where;
		do
		{
			where=rand.nextInt(xlength-2)+1;
			check=c.returnloop(whichone,newmap,where,1);
		//	System.out.println(count+" ");
			count++;
		}
		while(!check&&count<100);
		if(count>100)
		{
			where=1;
		}
		//where=xlength/2;
		xcord2=where;
		ycord2=1;
		blk=setb(whichone);
		switch(whichone)
		{
			case 5  :   makeblock(5,newmap,where,1,blk);break;// four by one
			case 6  :   makeblock(6,newmap,where,1,blk);break;// a four by four square
			case 7  :   makeblock(7,newmap,where,1,blk);break;//t shape
			case 8  :   makeblock(8,newmap,where,1,blk);break;//L shape
			case 9  :   makeblock(9,newmap,where,1,blk);break;//inverse L shape (Rshape)
			//case 10 : 	makeblock(10,newmap,where,1,blk);break; //a basic block
			case 12	:	makeblock(12,newmap,1,1,blk);break; //a line
			default :   //makeblock(12,newmap,1,1,blk);break; //a linebreak;
		}
		return whichone;
	}
//
//this will set the number for the matrix
//
	public static int setb(int block)
	{
		int blk;
		switch(block)
		{
			case 5  :   blk=5; break;// four by one
			case 6  :   blk=6; break;// a four by four square
			case 7  :   blk=7; break;//t shape
			case 8  :   blk=8; break;//L shape
			case 9  :   blk=9; break;//inverse L shape (Rshape)
			case 10 : 	blk=11; break; //a basic block
			case 12 :   blk=12; break;//a full line
			default :   blk=4; break;
		}
		return blk;
	}
//
//this method will make a block
//
	public static void makeblock(int block,int[][] newmap,int xcord,int ycord,int blk)
	{
		blocks1 s=new blocks1(newmap);
		blocks2 w=new blocks2(newmap);
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
			//case 10 : 	s.setblock(blk);
			//			s.basicsquare(xcord,ycord);break; //a basic block
			case 12 :	w.setblock(blk);
						w.fulline(1,ycord);break; // a full line
		}
	}
//
//this will move the block around the matrix
//
	public static void moveblock(int block,int[][] newmap,int xcord,int ycord,int speed,int rows,int colums,int score1,int lvl,Graphics g)
	{
		blocks1d draw=new blocks1d(g,false,newmap);
		check s=new check();
		blocks1 set=new blocks1();
		//int x,y;
		xxcor2=xcord;
		setxx(xxcor2);
		yycor2=ycord;
		setyy(yycor2);
		int blck;
		boolean lazy=false;
		boolean notlazy=true;
		boolean check8=true;
		boolean check6=true;
		boolean check7=true;
		//check8=s.returnloop(block,newmap,x,y);
		xxcor2=getxx();
		yycor2=getyy();
		check6=s.returnloopd(block,newmap,xxcor2,yycor2);
		if(check6&&check7)
		do
		{
			//check8=s.returnloop(block,newmap,x,y);
			xxcor2=getxx();
			yycor2=getyy();
			check6=s.returnloopd(block,newmap,xxcor2,yycor2);
			if(check6&&check7)
			{
				blck=setb(block);
				set.setblock(blck);
				xxcor2=getxx();
				yycor2=getyy();
				eraseblock(block,newmap,xxcor2,yycor2);
				makeblock(block,newmap,xxcor2,yycor2,blck);
				showscreen(newmap,rows,colums);
				//draw.hidedata(x,y,score1,lvl);
				//draw.showdata(x,y,score1,lvl);
				draw.setmap(newmap);
				draw.setmatrix();
				System.out.println("y: "+yycor2 +" x: "+ xxcor2+" score: " + score1);
				System.out.println("level: "+lvl);
			}
			else
			{
				System.out.println("this is over");
			}
			if(check6&&check7)
			{
					xxcor2=getxx();
					yycor2=getyy();
					yycor2++;
					//check8=s.returnloop(block,newmap,x,y);
					check6=s.returnloopd(block,newmap,xxcor2,yycor2);
			}
			delay(speed);

		}
		while(check6&&check7);
		if(check8&&!check6)
		{
			//System.out.println("deadblock");
			xxcor2=getxx();
			yycor2=getyy();
			eraseblock(block,newmap,xxcor2,yycor2);
			blck=s.checkdown(newmap,xxcor2,yycor2,block);
			blck=4;
			set.setblock(blck);
			makeblock(block,newmap,xxcor2,yycor2,blck);
			showscreen(newmap,rows,colums);
			//draw.hidedata(x,y,score1,lvl);
			//draw.showdata(x,y,score1,lvl);
			draw.setmap(newmap);
			draw.setmatrix();
		}
	}
//
//this method will erase a block
//
	public static void eraseblock(int block,int[][] newmap,int xcord,int ycord)
	{
		blocks1 s=new blocks1(newmap);
		blocks2 w=new blocks2(newmap);
		if(ycord<2)
		{
			switch(block)
			{
				case 5  :   s.setblock(0);
							s.Long4(xcord,ycord);break;// four by one
				case 6  :   s.setblock(0);
							s.bigsquare(xcord,ycord);break;// a four by four square
				case 7  :   s.setblock(0);
							s.tshape(xcord,ycord);break;//t shape
				case 8  :   s.setblock(0);
							s.Lshape(xcord,ycord);break;//L shape
				case 9  :   s.setblock(0);
							s.Rshape(xcord,ycord);break;//inverse L shape (Rshape)
				case 10 : 	s.setblock(0);
							s.basicsquare(xcord,ycord);break; //a basic block
				case 12	:	w.setblock(0);
							w.fulline(xcord,ycord);break;//a full line
			}
		}
		else
		{
		switch(block)
		{
			case 5  :   s.setblock(0);
						s.Long4(xcord,ycord-1);break;// four by one
			case 6  :   s.setblock(0);
						s.bigsquare(xcord,ycord-1);break;// a four by four square
			case 7  :   s.setblock(0);
						s.tshape(xcord,ycord-1);break;//t shape
			case 8  :   s.setblock(0);
						s.Lshape(xcord,ycord-1);break;//L shape
			case 9  :   s.setblock(0);
						s.Rshape(xcord,ycord-1);break;//inverse L shape (Rshape)
			case 10 : 	s.setblock(0);
						s.basicsquare(xcord,ycord-1);break; //a basic block
			case 12	:	w.setblock(0);
						w.fulline(xcord,ycord-1);break;//a full line
		}
		}
	}
//
//this will reset everything
//
	public static void reset()
	{
		xcord2=0;ycord2=0;
		caseblock=0;
		newMap=origanlMap;
	}
}
//
//this is class blocks this class
//
//
//
//
//
class blocks1
{
	int [][] changemap;
	int rows,colums;
	int bolcknumber;
	public blocks1(int[][]newmap)
	{
		changemap=newmap;
		rows=changemap.length;
		colums=changemap[0].length;
	}
	public blocks1()
	{
	}
	public void setblock(int blcknmbr)
	{
		bolcknumber=blcknmbr;
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
	int [][] changemap;
	int rows,colums,xcord,ycord;
	int bolcknumber;
	public makeblocks()
	{
	}
	public void block1(int block,int[][] newmap,int xcord,int ycord,int blk)
	{
		blocks1 s=new blocks1(newmap);
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
			default :   block2(block,newmap,xcord,ycord,blk); break;
		}
	}
	public void block2(int block,int[][] newmap,int xcord,int ycord,int blk)
	{
		blocks2 w=new blocks2(newmap);
		switch(block)
		{
			case 12 :	w.setblock(blk);
						w.fulline(xcord,ycord);break; // a full line
			default :   break;
		}
	}
	public void eraseblock1(int block,int[][] newmap,int xcord,int ycord)
	{
		blocks1 s=new blocks1(newmap);
		if(ycord<2)
		{
			switch(block)
			{
				case 5  :   block1(block,newmap,xcord,ycord,0);break;// four by one
				case 6  :   block1(block,newmap,xcord,ycord,0);break;// a four by four square
				case 7  :   block1(block,newmap,xcord,ycord,0);break;//t shape
				case 8  :   block1(block,newmap,xcord,ycord,0);break;//L shape
				case 9  :   block1(block,newmap,xcord,ycord,0);break;//inverse L shape (Rshape)
				case 10 : 	block1(block,newmap,xcord,ycord,0);break; //a basic block
				default :	eraseblock2(block,newmap,xcord,ycord);break;
			}
		}
		else
		{
			switch(block)
			{
				case 5  :   block1(block,newmap,xcord,ycord-1,0);break;// four by one
				case 6  :   block1(block,newmap,xcord,ycord-1,0);// a four by four square
				case 7  :   block1(block,newmap,xcord,ycord-1,0);break;//t shape
				case 8  :   block1(block,newmap,xcord,ycord-1,0);break;//L shape
				case 9  :   block1(block,newmap,xcord,ycord-1,0);break;//inverse L shape (Rshape)
				case 10 : 	block1(block,newmap,xcord,ycord-1,0);break; //a basic block
				default :	eraseblock2(block,newmap,xcord,ycord);break;
			}
		}
	}
	public void eraseblock2(int block,int[][] newmap,int xcord,int ycord)
	{
	}
	public void ghostblock1(int block,int[][] newmap,int xcord,int ycord)
	{
		int blk=0;
		blocks1 s=new blocks1(newmap);
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
			default :   block2(block,newmap,xcord,ycord,blk); break;
		}
	}
	public void ghostblock2(int block,int[][] newmap,int xcord,int ycord)
	{
	}
}
class blockset
{
	Graphics g2;
	int block,blk,blck,block2;
	public blockset()
	{
	}
	public blockset(Graphics g)
	{
	}
	public int list0(int block)
	{
		switch(block)
		{
			case 0  :   blk=0; break;//blankspace
			case 1  :   blk=1; break;// the bottom
			case 2  :   blk=7; break;//the top
			case 3  :   blk=8; break;//the walls
		//	case 9  :   blk=9; break;//inverse L shape (Rshape)
		//	case 10 : 	blk=11; break; //a basic block
		//	case 4  :   blk=4; break;//a dead block
			default :   blk=list1(block); break;
		}
		return blk;
	}
	public int list1(int block)
	{
		switch(block)
		{
			case 5  :   blk=5; break;// four by one
			case 6  :   blk=6; break;// a four by four square
			case 7  :   blk=7; break;//t shape
			case 8  :   blk=8; break;//L shape
			case 9  :   blk=9; break;//inverse L shape (Rshape)
			case 10 : 	blk=11; break; //a basic block
			case 4  :   blk=4; break;//a dead block
			default :   blk=list2(block); break;
		}
		return blk;
	}
	public int list2(int block)
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
/*
 *this class below will check for blocks and stuff like that
 *
 *
 *
 *
 */
class check
{
	int blocks,x,y,xcord,ycord,ylength,xlength,blk,blck,block,q;
	int score=0,seecsore,setscore,basescore,area,count;
	boolean check,check2,check3,check4,check5;
	int [][] newMap, oldMap;
	public check()
	{
	}
//
//this method will do a check for wich block it is then it will return where
//it is in the matrix for the method check to find
//
	public boolean returnloop(int block,int[][] newmap,int xcord,int ycord1)
	{
		check3=false;
		check=false;
		check2=false;
		check4=false;
		ycord=1;
		boolean check5=false;
		switch(block)
		{
			case 5  :   check=checklr(newmap,xcord,ycord);// four by one
						check2=checklr(newmap,xcord,ycord+1);
						check3=checklr(newmap,xcord,ycord+2);
						check4=checklr(newmap,xcord,ycord+3);
						break;
			case 6  :   check=checklr(newmap,xcord,ycord);// a four by four square
						check2=checklr(newmap,xcord+1,ycord);
						check3=checklr(newmap,xcord,ycord+1);
						check4=checklr(newmap,xcord+1,ycord+1);
						break;
			case 7  :   check=checklr(newmap,xcord,ycord);//t shape
						check2=checklr(newmap,xcord+1,ycord);
						check3=checklr(newmap,xcord-1,ycord);
						check4=checklr(newmap,xcord,ycord+1);
						break;
			case 8  :   check=checklr(newmap,xcord,ycord);//L shape
						check2=checklr(newmap,xcord+1,ycord+2);
						check3=checklr(newmap,xcord,ycord+1);
						check4=checklr(newmap,xcord,ycord+2);
						break;
			case 9  :   check=checklr(newmap,xcord,ycord);//inverse L shape (Rshape)
						check2=checklr(newmap,xcord-1,ycord+2);
						check3=checklr(newmap,xcord,ycord+1);
						check4=checklr(newmap,xcord,ycord+2);
						break;
			case 10 : 	check3=checklr(newmap,xcord,ycord);//a basic block
						break;
			case 12 :   int w=0;
						do
						{
							check=checklr(newmap,x+w,ycord);//a full line
							w++;
							check2=true;
							check4=true;
							check3=true;
						}
						while((w<(newmap[0].length)-1)&&check);
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
	public boolean checklr(int[][] newmap,int xcord1,int ycord1)
	{
		xcord=xcord1;
		ycord=ycord1;
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
	public boolean returnloopd(int block,int[][] newmap,int xcord,int ycord)
	{
		check3=false;
		check=false;
		check2=false;
		check4=false;
		boolean showblock=false;
		switch(block)
		{
			case 5  :   check=checkd(newmap,xcord,ycord);// four by one
						check2=checkd(newmap,xcord,ycord+1);
						check3=checkd(newmap,xcord,ycord+2);
						check4=checkd(newmap,xcord,ycord+3);
						break;
			case 6  :   check=checkd(newmap,xcord,ycord);// a four by four square
						check2=checkd(newmap,xcord+1,ycord+1);
						check3=checkd(newmap,xcord,ycord+1);
						check4=checkd(newmap,xcord+1,ycord);
						break;
			case 7  :   check=checkd(newmap,xcord,ycord);//t shape
						check2=checkd(newmap,xcord+1,ycord);
						check3=checkd(newmap,xcord-1,ycord);
						check4=checkd(newmap,xcord,ycord+1);
						break;
			case 8  :   check=checkd(newmap,xcord,ycord);//L shape
						check2=checkd(newmap,xcord,ycord+1);
						check3=checkd(newmap,xcord,ycord+2);
						check4=checkd(newmap,xcord+1,ycord+2);
						break;
			case 9  :   check=checkd(newmap,xcord,1);//inverse L shape (Rshape)
						check2=checkd(newmap,xcord,ycord+1);
						check3=checkd(newmap,xcord,ycord+2);
						check4=checkd(newmap,xcord-1,ycord+2);
						break;
			case 10 : 	check=checkd(newmap,xcord,ycord);//a basic block
						break;
			case 12 :   int w=1;
						do
						{
							check=checkd(newmap,0+w,ycord);//a full line
							w++;
							check2=true;
							check4=true;
							check3=true;
						}
						while((w<(newmap[0].length)-2)&&check);
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
	public boolean checkd(int[][] newmap,int xcord1,int ycord1)
	{
		xcord=xcord1;
		ycord=ycord1;
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
			check=false;
			return check;
		}
	}
//
//checks to see if the next block canbe sent down
//
	public boolean sendnext(int[][] newmap)
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
	public int score(int[][] Newmap)
	{
		boolean wht=true;
		ylength=Newmap.length;
		xlength=Newmap[0].length;
		setscore=0;
		for (y=1; y<ylength-1; y++)
		{
			setscore=0;
			x=0;
			do
			{
				setscore+=Newmap[y][x];
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
	public int[][] mapscore(int[][] Newmap)
	{
		boolean wht=true;
		ylength=Newmap.length;
		xlength=Newmap[0].length;
		setscore=0;
		for (y=1; y<ylength-1; y++)
		{
			setscore=0;
			x=0;
			do
			{
				setscore+=Newmap[y][x];
				basescore=(4*(xlength-2))+6;
				if(setscore==basescore)
				{
					wht=false;
					for(int sew=1; sew<xlength-1; sew++)
					{
						Newmap[y][sew]=0;

					}
					Newmap=mapset(Newmap,y);
				}
				x++;
			}while(x<xlength&&wht);
		}
		return Newmap;
	}
	public int[][] mapset(int[][] Newmap,int ycord)
	{
		ylength=Newmap.length;
		xlength=Newmap[0].length;
		int oldcord=0;
		int[][] nextmap;
		nextmap=Newmap;
		for (y=ycord-1; y>1; y--)
		{
			oldcord=0;
			x=0;
			for(x=0; x<xlength; x++)
			{
				oldcord=Newmap[y][x];
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
		Newmap=nextmap;
		return Newmap;
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
/*
 *this class below will do graphics
 *exe: show map, make block, kill block, (extras), stuff....
 */
class blocks1d//these are the original tetris blocks
{
	Graphics g;
	private int[][] newmap,oldmap,middlemap;
	private int x,y,size=16,blk,xlength,ylength;
	private Color c;
	private boolean rndm=false;//this will decide if the color is random or not
	public blocks1d(Graphics g2,boolean random,int[][] map)
	{
		xlength=map[0].length;
		ylength=map.length;
		newmap=map;
		oldmap=map;
		middlemap=map;
		g=g2;
		rndm=random;
	}
	public void setmap(int[][] Newmap)
	{
		Newmap=newmap;
	}
	public void showdata(int xd,int yd,int scored,int lvld)
	{
		g.setColor(Color.black);
		g.drawString("x: "+ xd+ " y: "+yd,0,20);
		g.drawString("score: "+scored+" level: "+lvld,0,30);
	}
	public void hidedata(int xd,int yd,int scored,int lvld)
	{
		g.setColor(Color.white);
		g.drawString("x: "+ xd+ " y: "+yd,0,20);
		g.drawString("score: "+scored+" level: "+lvld,0,30);
		int datalength=("score: "+scored+" level: "+lvld).length();
		String s1="",s2="",s3="",s4="",s5="",s6="",s7="",s8="",s9="",s0="";
		for(int wer=0; wer<datalength;wer++)
		{
			s1+="1";
			s2+="2";
			s3+="3";
			s4+="4";
			s5+="5";
			s6+="6";
			s7+="7";
			s8+="8";
			s9+="9";
			s0+="0";
		}
		g.drawString(s1,0,30);
		g.drawString(s2,0,30);
		g.drawString(s3,0,30);
		g.drawString(s4,0,30);
		g.drawString(s5,0,30);
		g.drawString(s6,0,30);
		g.drawString(s7,0,30);
		g.drawString(s8,0,30);
		g.drawString(s9,0,30);
		g.drawString(s0,0,30);
		datalength=("x: "+ xd+ " y: "+yd).length();
		//s1,s2,s3,s4,s5,s6,s7,s8,s9,s0;
		for(int wer=0; wer<datalength;wer++)
		{
			s1+="1";
			s2+="2";
			s3+="3";
			s4+="4";
			s5+="5";
			s6+="6";
			s7+="7";
			s8+="8";
			s9+="9";
			s0+="0";
		}
		g.drawString(s1,0,20);
		g.drawString(s2,0,20);
		g.drawString(s3,0,20);
		g.drawString(s4,0,20);
		g.drawString(s5,0,20);
		g.drawString(s6,0,20);
		g.drawString(s7,0,20);
		g.drawString(s8,0,20);
		g.drawString(s9,0,20);
		g.drawString(s0,0,20);
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
	private int setblock(int[][] NewMap,int xcord,int ycord)
	{
		int blockcord=NewMap[ycord][xcord];
		blockset b=new blockset();
		blockcord=b.list0(blockcord);
		return blockcord;
	}
	private Color choosecolor(int block)
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
				blk=setblock(newmap,x,y);
				c=choosecolor(blk);
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
	public void choosewhich(int x1,int y1,int block)
	{
		x=x1+100;
		y=y1+50;
		blk=block;
		c=choosecolor(blk);
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
	}
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
class message2
{
	//this is to seperate messages it does nothing
}
/*class blocks2d
{
	Graphics g;
	int x,y,size;
	public blocks2d(Graphics g2)
	{
		g=g2;
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
	public void choosewhich(int x1,int y1,Color c)
	{

	}
	public void basicsquare(int size,Color c)
	{

		g.setColor(c);
		g.fillRect(x,y,size,size);
		g.setColor(Color.black);
		g.drawRect(x,y,size,size);
	}
	public void Long5(int x,int y,int size,Color c)
	{
		basicsquare(x,y,size,c);
		basicsquare(x,y+size,size,c);
		basicsquare(x,y+size+size,size,c);
		basicsquare(x,y+size+size+size,size,c);
		basicsquare(x,y+size+size+size+size,size,c);
	}
}*/// these some of the common one that are not in the original
class message
{
	//this is to seperate messages it does nothing
}
// these are wierd
/*class blocks3d
{
	Graphics g;
	int x,y,size;
	public blocks3d(Graphics g2)
	{
		g=g2;
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
	public void basictriangler(int size,Color c)
	{
		Polygon t = new Polygon();
		t.addPoint(x,y);
		t.addPoint(x+size,y);
		t.addPoint(x,y+size);
		g.setColor(c);
		g.fillPolygon(t);
		g.setColor(Color.black);
		g.drawPolygon(t);
	}
	public void arrowdiaganal(int x,int y,int size,Color c)
	{
		basicsquare(x,y,size,c);
		basicsquare(x+size,y+size,size,c);
		basicsquare(x+size+size,y+size+size,size,c);
		basicsquare(x+size+size+size,y+size+size+size,size,c);
		basictrianglel(x+size,y+size,size,c);
		basictrianglel(x+size+size,y+size+size,size,c);
		basictrianglel(x+size+size+size,y+size+size+size,size,c);
		basictrianglel(x+size+size+size+size,y+size+size+size+size,size,c);
		basictrianglel(x+size,y+size,-size,c);
		basictrianglel(x+size+size,y+size+size,-size,c);
		basictrianglel(x+size+size+size,y+size+size+size,-size,c);
		basictrianglel(x+size+size+size+size,y+size+size+size+size,-size,c);
	}
}*/
