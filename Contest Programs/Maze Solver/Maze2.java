import static java.lang.System.*;

import java.util.*;
import java.io.*;

public class Maze2
{
	static final int[] allDirs = {1,0,3,4};
	static graphics gb=new graphics();
	public static void main(String[] args)throws IOException
	{
		Scanner s  = new Scanner(new File("input62.txt"));
		int r = s.nextInt();
		int c = s.nextInt();
		String[][] maze = new String[r][c];
		s.nextLine();
		for(int x = 0;x<r;x++)
		{
			String in = s.nextLine();
			for(int y = 0;y<c;y++)
			{
				maze[x][y]=in.charAt(y)+"";
			}
		}
		int srow = 0;
		int scol = 0;
		int erow = 0;
		int ecol = 0;
		for(int x = r-1;x>=0;x--)
		{
			for(int y = c-1;y>=0;y--)
			{
				if(maze[x][y].equals("S"))
				{
					srow = x;
					scol = y;
					break;
				}
			}
		}
		for(int x = 0;x<r;x++)
		{
			for(int y = 0;y<c;y++)
			{
				if(maze[x][y].equals("E"))
				{
					erow = x;
					ecol = y;
					break;
				}
			}
		}
		String[][] mazetmp1 = copyMaze(maze,r,c);
		boolean done = false;
		do
		{
			String[][] mazetmp2=copyMaze(mazetmp1,r,c);
			mazetmp2 = findDeadEnds(mazetmp2,r,c);
			mazetmp2 = findCorners(mazetmp2,r,c);
			printMaze(mazetmp2);
			if(equal(mazetmp2,mazetmp1))
			{
				done = true;
			}
			else
			{
				mazetmp1 = copyMaze(mazetmp2,r,c);
			}
			printMaze(mazetmp1);
		}while(!done);
		mazetmp1 = findDeadEnds(mazetmp1,r,c);
		ArrayList<Endpoint> endP = new ArrayList<Endpoint>();
		if(movePossible(mazetmp1,srow,scol,1,1))
		{
			endP.add(new Endpoint(srow-1,scol,1,1,null));
			mazetmp1[srow-1][scol]="1";
		}
		if(movePossible(mazetmp1,srow,scol,0,1))
		{
			endP.add(new Endpoint(srow,scol+1,1,0,null));
			mazetmp1[srow][scol+1]="1";
		}
		if(movePossible(mazetmp1,srow,scol,3,1))
		{
			endP.add(new Endpoint(srow+1,scol,1,3,null));
			mazetmp1[srow+1][scol]="1";
		}
		if(movePossible(mazetmp1,srow,scol,4,1))
		{
			endP.add(new Endpoint(srow,scol-1,1,4,null));
			mazetmp1[srow][scol-1]="1";
		}
		mazetmp1 = solve(mazetmp1,endP);
		done = false;
		do
		{
			String[][] mazetmp2=copyMaze(mazetmp1,r,c);
			mazetmp2 = findDeadEnds(mazetmp2,r,c);
			mazetmp2 = findCorners(mazetmp2,r,c);
			if(equal(mazetmp2,mazetmp1))
			{
				done = true;
			}
			else
			{
				mazetmp1 = copyMaze(mazetmp2,r,c);
			}
		}while(!done);
		mazetmp1 = findDeadEnds(mazetmp1,r,c);
		String[][] mazefinal = displaySolution(maze,mazetmp1);
		printMaze(mazefinal);
	}
	public static boolean equal(String[][] maze1,String[][] maze2)
	{
			for(int k = 0;k<maze1.length;k++)
			{
				for(int l = 0;l<maze1[k].length;l++)
				{
					if(!maze1[k][l].equals(maze2[k][l]))
						return false;
				}
			}
			return true;
	}
	public static String[][] copyMaze(String[][] mazeO, int row, int col)
	{
		String[][] mazeTmp = new String[row][col];
		for(int x = 0;x<row;x++)
		{
			for(int y = 0;y<col;y++)
			{
				mazeTmp[x][y] = mazeO[x][y];
			}
		}
		return mazeTmp;
	}
	public static void printMaze(String[][] maze1)
	{
//		out.println();
//		for(int x = 0;x<maze1.length;x++)
//		{
//			for(int y = 0;y<maze1[0].length;y++)
//			{
//				out.printf("%2s",maze1[x][y]);
//			}
//			out.println();
//		}
		long s=System.currentTimeMillis();
		while(System.currentTimeMillis()-s<=0)
		{
		}
		gb.paint(maze1);
	}
	public static String[][] displaySolution(String[][] maze21, String[][] maze22)
	{
		ArrayList<Integer> al = new ArrayList<Integer>();
		for(int r=0;r<maze21.length;r++)
		{
			for(int c =0;c<maze21[r].length;c++)
			{
				if(maze22[r][c].matches("\\d+"))
				{
					al.add(Integer.parseInt(maze22[r][c]));
					maze21[r][c]="*";
				}
			}
		}
		Collections.sort(al);
		out.println(al.get(al.size()-1));
		return maze21;
	}
	private static String getNext(String[][] maze2, int x, int y, int dir)
	{
		if(dir==1)
		{
			if((x-1)>=0)
				return maze2[x-1][y];
		}
		if(dir==0)
		{
			if((y+1)<maze2[x].length)
				return maze2[x][y+1];
		}
		if(dir==3)
		{
			if((x+1)<maze2.length)
				return maze2[x+1][y];
		}
		if(dir==4)
		{
			if((y-1)>=0)
				return maze2[x][y-1];
		}
		return "null";
	}
	public static String[][] findDeadEnds(String[][] maze3, int row, int col)
	{
		for(int x = 0;x<row;x++)
		{
			for(int y = 0;y<col;y++)
			{
				int dir = isDeadEnd(maze3,x,y);
				if(dir!=-1)
				{
					maze3 = fillInDeadEnds(maze3,x,y,dir);
				}
				else if(isDeadEndSpecialCase(maze3,x,y))
				{
					maze3[x][y]="O";
				}
			}
		}
		return maze3;
	}
	public static int isDeadEnd(String[][] maze4, int x, int y)
	{
		int numWalls = 0;
		int dir = 0;
		if(maze4[x][y].matches("[OXCSE]"))
		{
			return -1;
		}
		if(getNext(maze4,x,y,1).matches("[XCO]"))
		{
			numWalls++;
			dir+=1;
		}
		if(getNext(maze4,x,y,0).matches("[XCO]"))
		{
			numWalls++;
			dir+=0;
		}
		if(getNext(maze4,x,y,3).matches("[XCO]"))
		{
			numWalls++;
			dir+=3;
		}
		if(getNext(maze4,x,y,4).matches("[XCO]"))
		{
			numWalls++;
			dir+=4;
		}
		if(numWalls>=3)
		{
			return 8-dir;
		}
		return -1;
	}
	public static String[][] fillInDeadEnds(String[][] maze5, int x, int y, int dir)
	{
		maze5[x][y]="O";
		if(dir==1)
		{
			if(x-1>=0)
			{
				int dir2 = isDeadEnd(maze5,x-1,y);
				if(dir2!=-1)
					return fillInDeadEnds(maze5,x-1,y,dir2);
			}
		}
		if(dir==0)
		{
			if(y+1<maze5[0].length)
			{
				int dir2 = isDeadEnd(maze5,x,y+1);
				if(dir2!=-1)
					return fillInDeadEnds(maze5,x,y+1,dir2);
			}
		}
		if(dir==3)
		{
			if(x+1<maze5.length)
			{
				int dir2 = isDeadEnd(maze5,x+1,y);
				if(dir2!=-1)
					return fillInDeadEnds(maze5,x+1,y,dir2);
			}
		}
		if(dir==4)
		{
			if(y-1>=0)
			{
				int dir2 = isDeadEnd(maze5,x,y-1);
				if(dir2!=-1)
					return fillInDeadEnds(maze5,x,y-1,dir2);
			}
		}
		//printMaze(maze5);
		return maze5;
	}
	public static String[][] findCorners(String[][] maze6, int row, int col)
	{
		for(int x = 0;x<row;x++)
		{
			for(int y = 0;y<col;y++)
			{
				int cor = isCorner(maze6,x,y);
				if(cor==1)
				{
					maze6 = fillInCorners(maze6,x,y,1);
					maze6 = fillInCorners(maze6,x,y,0);
				}
				else if(cor==3)
				{
					maze6 = fillInCorners(maze6,x,y,0);
					maze6 = fillInCorners(maze6,x,y,3);
				}
				else if(cor==12)
				{
					maze6 = fillInCorners(maze6,x,y,3);
					maze6 = fillInCorners(maze6,x,y,4);
				}
				else if(cor==4)
				{
					maze6 = fillInCorners(maze6,x,y,4);
					maze6 = fillInCorners(maze6,x,y,1);
				}
			}
		}
		return maze6;
	}
	public static int isCorner(String[][] maze7, int x, int y)
	{
		if(x<=0||x>=maze7.length||y<=0||y>=maze7[x].length)
		{
			return -1;
		}
		if(maze7[x][y].matches("[XSEOC]"))
		{
			return -1;
		}
		if((getNext(maze7,x,y,1).matches("[XCO]"))
				&&(getNext(maze7,x,y,0).matches("[XCO]")))
		{
			if(getNext(maze7,x,y,3).matches("\\s*\\d*")&&maze7[x+1][y-1].matches("\\s*\\d*")&&getNext(maze7,x,y,4).matches("\\s*\\d*"))
			{
				return 12;
			}
		}
		if((getNext(maze7,x,y,0).matches("[XCO]"))
				&&(getNext(maze7,x,y,3).matches("[XCO]")))
		{
			if(getNext(maze7,x,y,4).matches("\\s*\\d*")&&maze7[x-1][y-1].matches("\\s*\\d*")&&getNext(maze7,x,y,1).matches("\\s*\\d*"))
			{
				return 4;
			}
		}
		if((getNext(maze7,x,y,3).matches("[XCO]"))
				&&(getNext(maze7,x,y,4).matches("[XCO]")))
		{
			if(getNext(maze7,x,y,1).matches("\\s*\\d*")&&maze7[x-1][y+1].matches("\\s*\\d*")&&getNext(maze7,x,y,0).matches("\\s*\\d*"))
			{
				return 1;
			}
		}
		if((getNext(maze7,x,y,4).matches("[XCO]"))
				&&(getNext(maze7,x,y,1).matches("[XCO]")))
		{
			if(getNext(maze7,x,y,0).matches("\\s*\\d*")&&maze7[x+1][y+1].matches("\\s*\\d*")&&getNext(maze7,x,y,3).matches("\\s*\\d*"))
			{
				return 3;
			}
		}
		return -1;
	}
	public static boolean isDeadEndSpecialCase(String[][] maze7, int x, int y)
	{
		if(x<=0||x>=maze7.length||y<=0||y>=maze7[x].length)
		{
			return false;
		}
		if(!maze7[x][y].matches("\\d*"))
		{
			return false;
		}
		int current=Integer.parseInt(maze7[x][y]);
		for(int dir:allDirs)
		{
			String g=getNext(maze7,x,y,dir);
			if(g.matches("\\d+"))
			{
				if(Integer.parseInt(g)>current)
					return false;
			}
			else if(g.matches("E"))
				return false;
		}
		return true;
	}
	public static String[][] fillInCorners(String[][] maze8, int x, int y, int dir)
	{
		maze8[x][y]="C";
		if(dir==1)
		{
			if(x-1>=0)
			{
				int dir2 = isCorner(maze8,x-1,y);
				if(dir2==1)
				{
					maze8 = fillInCorners(maze8,x-1,y,1);
					maze8 = fillInCorners(maze8,x-1,y,0);
				}
				else if(dir2==3)
				{
					maze8 = fillInCorners(maze8,x-1,y,0);
					maze8 = fillInCorners(maze8,x-1,y,3);
				}
				else if(dir2==12)
				{
					maze8 = fillInCorners(maze8,x-1,y,3);
					maze8 = fillInCorners(maze8,x-1,y,4);
				}
				else if(dir2==4)
				{
					maze8 = fillInCorners(maze8,x-1,y,4);
					maze8 = fillInCorners(maze8,x-1,y,1);
				}
			}
		}
		if(dir==0)
		{
			if(y+1<maze8[0].length)
			{
				int dir2 = isCorner(maze8,x,y+1);
				if(dir2==1)
				{
					maze8 = fillInCorners(maze8,x,y+1,1);
					maze8 = fillInCorners(maze8,x,y+1,0);
				}
				else if(dir2==3)
				{
					maze8 = fillInCorners(maze8,x,y+1,0);
					maze8 = fillInCorners(maze8,x,y+1,3);
				}
				else if(dir2==12)
				{
					maze8 = fillInCorners(maze8,x,y+1,3);
					maze8 = fillInCorners(maze8,x,y+1,4);
				}
				else if(dir2==4)
				{
					maze8 = fillInCorners(maze8,x,y+1,1);
					maze8 = fillInCorners(maze8,x,y+1,4);
				}
			}
		}
		if(dir==3)
		{
			if(x+1<maze8.length)
			{
				int dir2 = isCorner(maze8,x+1,y);
				if(dir2==1)
				{
					maze8 = fillInCorners(maze8,x+1,y,1);
					maze8 = fillInCorners(maze8,x+1,y,0);
				}
				else if(dir2==3)
				{
					maze8 = fillInCorners(maze8,x+1,y,0);
					maze8 = fillInCorners(maze8,x+1,y,3);
				}
				else if(dir2==12)
				{
					maze8 = fillInCorners(maze8,x+1,y,3);
					maze8 = fillInCorners(maze8,x+1,y,4);
				}
				else if(dir2==4)
				{
					maze8 = fillInCorners(maze8,x+1,y,1);
					maze8 = fillInCorners(maze8,x+1,y,4);
				}
			}
		}
		if(dir==4)
		{
			if(y-1>=0)
			{
				int dir2 = isCorner(maze8,x,y-1);
				if(dir2==1)
				{
					maze8 = fillInCorners(maze8,x,y-1,1);
					maze8 = fillInCorners(maze8,x,y-1,0);
				}
				else if(dir2==3)
				{
					maze8 = fillInCorners(maze8,x,y-1,0);
					maze8 = fillInCorners(maze8,x,y-1,3);
				}
				else if(dir2==12)
				{
					maze8 = fillInCorners(maze8,x,y-1,3);
					maze8 = fillInCorners(maze8,x,y-1,4);
				}
				else if(dir2==4)
				{
					maze8 = fillInCorners(maze8,x,y-1,1);
					maze8 = fillInCorners(maze8,x,y-1,4);
				}
			}
		}
	//	printMaze(maze8);
		return maze8;
	}
	public static String[][] solve(String[][] maze16, ArrayList<Endpoint> endPoints)
	{
		boolean done = false;
		do
		{
			ArrayList<Endpoint> tmp = new ArrayList<Endpoint>();
			for(Endpoint e:endPoints)
			{
				int row = e.x;
				int col = e.y;
				int numMove = e.num;
				int d = e.dir;
				int moves = 0;
				for(int dire:allDirs)
				{
					if(d+dire!=4&&getNext(maze16,row,col,dire).equals("E"))
					{
						for(Endpoint e2:endPoints)
						{
							if(!e2.equals(e))
							maze16[e2.x][e2.y]="O";
						}
						return maze16;
					}
				}
				for(int dire:allDirs)
				{
					if(d+dire!=4)
						if(movePossible(maze16,row,col,dire,numMove+1))
						{
							tmp.add(new Endpoint(getRow(maze16.length,row,dire),getCol(maze16[row].length,col,dire),numMove+1,dire,e));
							maze16[getRow(maze16.length,row,dire)][getCol(maze16[row].length,col,dire)]=""+(int)(numMove+1);
							moves++;
						}
				}
				if(moves==0)
					maze16[row][col]="O";
				if(done)
					break;
			}
			endPoints.clear();
			endPoints.addAll(tmp);
			//printMaze(maze16);
			pause();
		}while(!done);
		return maze16;
	}
	public static boolean movePossible(String[][] maze17, int row, int col, int dir, int num)
	{
		String g=getNext(maze17,row,col,dir);
		if(g.matches("[XCO]"))
			return false;
		else if(g.matches(" "))
			return true;
		else if((getNext(maze17,row,col,dir).matches("\\d+")&&Integer.parseInt(getNext(maze17,row,col,dir))==num)&&closeToEnd(maze17,row,col,dir))
		{
			return true;
		}
		else
			return false;
	}
	public static boolean closeToEnd(String[][] maze18, int row, int col, int dir)
	{
		for(int d:allDirs)
		{
			if(d+dir!=4&&getNext(maze18,getRow(maze18.length,row,dir),getCol(maze18[row].length,col,dir),d).equals("E"))
				return true;
		}
		return false;
	}
	public static int getRow(int lim,int r, int d)
	{
		if(d==1)
			return (r-1>=0)?r-1:r;
		if(d==3)
			return (r+1<lim)?r+1:r;
		return r;
	}
	public static int getCol(int lim,int c, int d)
	{
		if(d==0)
			return (c+1<lim)?c+1:c;
		if(d==4)
			return (c-1>=0)?c-1:c;
		return c;
	}
	public static void pause()
	{
//		Scanner s = new Scanner(in);
//		s.nextLine();
	}
}
class Endpoint
{
	int x,y,num,dir;
	Endpoint e;
	public Endpoint(int a, int b, int n, int d, Endpoint p)
	{
		x = a;
		y = b;
		num = n;
		dir = d;
		e=p;
	}
	public boolean equals(Endpoint s)
	{
		return x==s.x&&y==s.y;
	}
}