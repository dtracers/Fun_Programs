import static java.lang.System.*;
import java.util.*;
import java.io.*;

public class Maze
{
	//array that makes looking in all directions easy
	//done so that if you do 4-dir, you'll get the backward
	//direction
	//straight directions
	//  1
	//4   0
	//  3
	static final int[] allDirs = {1,0,3,4};
	//diagonal directions
	//4   1
	//
	//12  3
	static graphics gb=new graphics();
	public static void main(String[] args)throws IOException
	{
		//basic stuff to build the maze
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
		//determine the row and col of the start and end
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
		//get a copy of the maze so the original maze
		//wont get messed up
		String[][] mazetmp1 = copyMaze(maze,r,c);
		//loop to call findDeadEnds and findCorners alternating
		//each time until there is no change in the maze
		//then call findDeadEnds one more time to clean any
		//dead-ends resulting from the call to corner
		boolean done = false;
//		do
//		{
//			String[][] mazetmp2=copyMaze(mazetmp1,r,c);
//			mazetmp2 = findDeadEnds(mazetmp2,r,c);
//			mazetmp2 = findCorners(mazetmp2,r,c);
//			if(equal(mazetmp2,mazetmp1))
//			{
//				done = true;
//			}
//			else
//			{
//				mazetmp1 = copyMaze(mazetmp2,r,c);
//			}
//		}while(!done);
		mazetmp1 = findDeadEnds(mazetmp1,r,c);
		printMaze(mazetmp1);
		//get a preliminary set of endpoints for the solve
		//method
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
		//solve the maze
		ArrayList<Endpoint> ends = solve(mazetmp1,endP);
		//get the final display form for the maze
		String[][] mazefinal = displaySolution(maze,ends);
		printMaze(mazefinal);
	}
	//method that returns a copy of maze
	public static String[][] copyMaze(String[][] maze, int rows, int cols)
	{
		String[][] mazeTmp = new String[rows][cols];
		for(int x = 0;x<rows;x++)
		{
			for(int y = 0;y<cols;y++)
			{
				mazeTmp[x][y] = maze[x][y];
			}
		}
		return mazeTmp;
	}
	//checks if maze1 and maze2 are the same
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
	//prints maze
	public static void printMaze(String[][] maze)
	{
//		out.println();
//		for(int x = 0;x<maze.length;x++)
//		{
//			for(int y = 0;y<maze[0].length;y++)
//			{
//				out.printf("%2s",maze[x][y]);
//			}
//			out.println();
//		}
		gb.paint(maze);
	}
	//method to display solved maze
	//goes through endPoints and puts a "*" in the location of the current endPoint and moves on to
	//the parent
	public static String[][] displaySolution(String[][] mazeOriginal, ArrayList<Endpoint> endPoints)
	{
		out.println(endPoints.get(0).num);
		do
		{
			ArrayList<Endpoint> tmp = new ArrayList<Endpoint>();
			for(Endpoint e:endPoints)
			{
				mazeOriginal[e.x][e.y]="*";
				if(!tmp.contains(e.e)&&e.e!=null)
					tmp.add(e.e);
			}
			endPoints.clear();
			endPoints.addAll(tmp);
		}while(!endPoints.isEmpty());
		return mazeOriginal;
	}
	//method to get the next element in maze in direction dir of (row,col)
	//or null if next location is unavailable
	public static String getNext(String[][] maze, int row, int col, int dir)
	{
		if(dir==1)
		{
			if((row-1)>=0)
				return maze[row-1][col];
		}
		if(dir==0)
		{
			if((col+1)<maze[row].length)
				return maze[row][col+1];
		}
		if(dir==3)
		{
			if((row+1)<maze.length)
				return maze[row+1][col];
		}
		if(dir==4)
		{
			if((col-1)>=0)
				return maze[row][col-1];
		}
		return "null";
	}
	//goes through the maze from top left corner to bottom right corner searching
	//for dead-ends, then acts upon them
	public static String[][] findDeadEnds(String[][] maze, int rows, int cols)
	{
		for(int x = 0;x<rows;x++)
		{
			for(int y = 0;y<cols;y++)
			{
				int dir = isDeadEnd(maze,x,y);
				if(dir!=-1)
				{
					maze = fillInDeadEnds(maze,x,y,dir);
				}
				else if(isDeadEndSpecialCase(maze,x,y))
				{
					maze[x][y]="O";
				}
			}
		}
		return maze;
	}
	//determines whether or not (row,col) is a dead-end
	//dead-end if surrounded on three sides by a wall character (maze default "X",
	//program defined dead-end "O", program defined corner "C")
	//returns direction toward unwalled side or -1 if not dead-end
	public static int isDeadEnd(String[][] maze, int row, int col)
	{
		int numWalls = 0;
		int dirs = 0;
		if(maze[row][col].matches("[OXCSE]"))
		{
			return -1;
		}
		for(int dir:allDirs)
		{
			if(getNext(maze,row,col,dir).matches("[XCO]"))
			{
				numWalls++;
				dirs+=dir;
			}
		}
		if(numWalls>=3)
		{
			return 8-dirs;
		}
		return -1;
	}
	//determines if (row,col) is the special case dead end, where it is a number
	//surround on all sides by numbers less than it
	//can only occur after maze is solved
	public static boolean isDeadEndSpecialCase(String[][] maze, int row, int col)
	{
		if(row<=0||row>=maze.length||col<=0||col>=maze[row].length)
		{
			return false;
		}
		if(!maze[row][col].matches("\\d*"))
		{
			return false;
		}
		int current=Integer.parseInt(maze[row][col]);
		for(int dir:allDirs)
		{
			String g=getNext(maze,row,col,dir);
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
	//fills in dead-ends and paths leading to them
	//(row,col) is guaranteed to be a dead-end
	//puts an "O" in (row,col) and goes checking in only available direction
	public static String[][] fillInDeadEnds(String[][] maze, int row, int col, int dir)
	{
		maze[row][col]="O";
		if(dir==1)
		{
			if(row-1>=0)
			{
				int dir2 = isDeadEnd(maze,row-1,col);
				if(dir2!=-1)
					return fillInDeadEnds(maze,row-1,col,dir2);
			}
		}
		if(dir==0)
		{
			if(col+1<maze[0].length)
			{
				int dir2 = isDeadEnd(maze,row,col+1);
				if(dir2!=-1)
					return fillInDeadEnds(maze,row,col+1,dir2);
			}
		}
		if(dir==3)
		{
			if(row+1<maze.length)
			{
				int dir2 = isDeadEnd(maze,row+1,col);
				if(dir2!=-1)
					return fillInDeadEnds(maze,row+1,col,dir2);
			}
		}
		if(dir==4)
		{
			if(col-1>=0)
			{
				int dir2 = isDeadEnd(maze,row,col-1);
				if(dir2!=-1)
					return fillInDeadEnds(maze,row,col-1,dir2);
			}
		}
		//printMaze(maze);
		return maze;
	}
	//goes through the maze from top left corner to bottom right corner searching
	//for corners, then acts upon them
	public static String[][] findCorners(String[][] maze, int row, int col)
	{
		for(int x = 0;x<row;x++)
		{
			for(int y = 0;y<col;y++)
			{
				int cor = isCorner(maze,x,y);
				if(cor==1)
				{
					maze = fillInCorners(maze,x,y,1);
					maze = fillInCorners(maze,x,y,0);
				}
				else if(cor==3)
				{
					maze = fillInCorners(maze,x,y,0);
					maze = fillInCorners(maze,x,y,3);
				}
				else if(cor==12)
				{
					maze = fillInCorners(maze,x,y,3);
					maze = fillInCorners(maze,x,y,4);
				}
				else if(cor==4)
				{
					maze = fillInCorners(maze,x,y,4);
					maze = fillInCorners(maze,x,y,1);
				}
			}
		}
		return maze;
	}
	//determines if (row,col) is a corner
	//corner if surrounded on two sides by wall character (see lines 233-234) and
	//empty on remaining two sides and the diagonal between them
	//returns diagonal direction toward unwalled side
	public static int isCorner(String[][] maze, int row, int col)
	{
		if(row<=0||row>=maze.length||col<=0||col>=maze[row].length)
		{
			return -1;
		}
		if(maze[row][col].matches("[XSEOC]"))
		{
			return -1;
		}
		if((getNext(maze,row,col,1).matches("[XCO]"))&&(getNext(maze,row,col,0).matches("[XCO]")))
		{
			if(getNext(maze,row,col,3).matches("\\s*\\d*")&&maze[row+1][col-1].matches("\\s*\\d*")&&getNext(maze,row,col,4).matches("\\s*\\d*"))
			{
				return 12;
			}
		}
		if((getNext(maze,row,col,0).matches("[XCO]"))&&(getNext(maze,row,col,3).matches("[XCO]")))
		{
			if(getNext(maze,row,col,4).matches("\\s*\\d*")&&maze[row-1][col-1].matches("\\s*\\d*")&&getNext(maze,row,col,1).matches("\\s*\\d*"))
			{
				return 4;
			}
		}
		if((getNext(maze,row,col,3).matches("[XCO]"))&&(getNext(maze,row,col,4).matches("[XCO]")))
		{
			if(getNext(maze,row,col,1).matches("\\s*\\d*")&&maze[row-1][col+1].matches("\\s*\\d*")&&getNext(maze,row,col,0).matches("\\s*\\d*"))
			{
				return 1;
			}
		}
		if((getNext(maze,row,col,4).matches("[XCO]"))&&(getNext(maze,row,col,1).matches("[XCO]")))
		{
			if(getNext(maze,row,col,0).matches("\\s*\\d*")&&maze[row+1][col+1].matches("\\s*\\d*")&&getNext(maze,row,col,3).matches("\\s*\\d*"))
			{
				return 3;
			}
		}
		return -1;
	}
	//fills in corners and corners resulting from "new wall"
	//(row,col) is guaranteed to be a corner
	//puts a "C" in (row,col) then goes checking in the two open directions
	public static String[][] fillInCorners(String[][] maze, int row, int col, int dir)
	{
		maze[row][col]="C";
		if(dir==1)
		{
			if(row-1>=0)
			{
				int dir2 = isCorner(maze,row-1,col);
				if(dir2==1)
				{
					maze = fillInCorners(maze,row-1,col,1);
					maze = fillInCorners(maze,row-1,col,0);
				}
				else if(dir2==3)
				{
					maze = fillInCorners(maze,row-1,col,0);
					maze = fillInCorners(maze,row-1,col,3);
				}
				else if(dir2==12)
				{
					maze = fillInCorners(maze,row-1,col,3);
					maze = fillInCorners(maze,row-1,col,4);
				}
				else if(dir2==4)
				{
					maze = fillInCorners(maze,row-1,col,4);
					maze = fillInCorners(maze,row-1,col,1);
				}
			}
		}
		if(dir==0)
		{
			if(col+1<maze[0].length)
			{
				int dir2 = isCorner(maze,row,col+1);
				if(dir2==1)
				{
					maze = fillInCorners(maze,row,col+1,1);
					maze = fillInCorners(maze,row,col+1,0);
				}
				else if(dir2==3)
				{
					maze = fillInCorners(maze,row,col+1,0);
					maze = fillInCorners(maze,row,col+1,3);
				}
				else if(dir2==12)
				{
					maze = fillInCorners(maze,row,col+1,3);
					maze = fillInCorners(maze,row,col+1,4);
				}
				else if(dir2==4)
				{
					maze = fillInCorners(maze,row,col+1,1);
					maze = fillInCorners(maze,row,col+1,4);
				}
			}
		}
		if(dir==3)
		{
			if(row+1<maze.length)
			{
				int dir2 = isCorner(maze,row+1,col);
				if(dir2==1)
				{
					maze = fillInCorners(maze,row+1,col,1);
					maze = fillInCorners(maze,row+1,col,0);
				}
				else if(dir2==3)
				{
					maze = fillInCorners(maze,row+1,col,0);
					maze = fillInCorners(maze,row+1,col,3);
				}
				else if(dir2==12)
				{
					maze = fillInCorners(maze,row+1,col,3);
					maze = fillInCorners(maze,row+1,col,4);
				}
				else if(dir2==4)
				{
					maze = fillInCorners(maze,row+1,col,1);
					maze = fillInCorners(maze,row+1,col,4);
				}
			}
		}
		if(dir==4)
		{
			if(col-1>=0)
			{
				int dir2 = isCorner(maze,row,col-1);
				if(dir2==1)
				{
					maze = fillInCorners(maze,row,col-1,1);
					maze = fillInCorners(maze,row,col-1,0);
				}
				else if(dir2==3)
				{
					maze = fillInCorners(maze,row,col-1,0);
					maze = fillInCorners(maze,row,col-1,3);
				}
				else if(dir2==12)
				{
					maze = fillInCorners(maze,row,col-1,3);
					maze = fillInCorners(maze,row,col-1,4);
				}
				else if(dir2==4)
				{
					maze = fillInCorners(maze,row,col-1,1);
					maze = fillInCorners(maze,row,col-1,4);
				}
			}
		}
		//printMaze(maze);
		return maze;
	}
	//solves the maze
	//checks in all directions
	//	if no possible moves, current cell becomes an "O"
	//	else adds new endpoint into arraylist
	//repeats until end is reached
	public static ArrayList<Endpoint> solve(String[][] maze, ArrayList<Endpoint> endPoints)
	{
		boolean done = false;
		do
		{
			ArrayList<Endpoint> tmp = new ArrayList<Endpoint>();
			ArrayList<Endpoint> tmp2 = new ArrayList<Endpoint>();
			for(Endpoint e:endPoints)
			{
				int row = e.x;
				int col = e.y;
				int numMove = e.num;
				int d = e.dir;
				int moves = 0;
				for(int dire:allDirs)
				{
					if(d+dire!=4&&getNext(maze,row,col,dire).equals("E"))
					{
						for(Endpoint e2:endPoints)
						{
							if(!e2.equals(e))
							{
								maze[e2.x][e2.y]="O";
								tmp2.add(e2);
							}
						}
						done = true;
						break;
					}
				}
				for(int dire:allDirs)
				{
					if(d+dire!=4)
						if(!done&&movePossible(maze,row,col,dire,numMove+1))
						{
							tmp.add(new Endpoint(getRow(maze.length,row,dire),getCol(maze[row].length,col,dire),numMove+1,dire,e));
							maze[getRow(maze.length,row,dire)][getCol(maze[row].length,col,dire)]=""+(int)(numMove+1);
							moves++;
						}
				}
				if(!done&&moves==0)
				{
					maze[row][col]="O";
					tmp2.add(e);
				}
				if(done)
					break;
			}
			if(!done)
				done = tmp.isEmpty();
			if(!done)
			{
				endPoints.clear();
				endPoints.addAll(tmp);
				printMaze(maze);
			}
			else
			{
				endPoints.removeAll(tmp2);
			}
		}while(!done);
		return endPoints;
	}
	//determines if a move is possible
	//move is possible if the cell in direction dir of (row,col) is empty, or the number in
	//said cell is equal to the number about to be put in that cell and the next cell is adjacent
	//to the end
	public static boolean movePossible(String[][] maze, int row, int col, int dir, int num)
	{
		String g=getNext(maze,row,col,dir);
		if(g.matches("[XCO]"))
			return false;
		else if(g.matches(" "))
			return true;
		else if((g.matches("\\d+")&&Integer.parseInt(g)==num)&&closeToEnd(maze,row,col,dir))
		{
			return true;
		}
		else
			return false;
	}
	//checks if the cell in direction dir of (row,col) is adjacent to the end
	public static boolean closeToEnd(String[][] maze, int row, int col, int dir)
	{
		for(int d:allDirs)
		{
			if(d+dir!=4&&getNext(maze,getRow(maze.length,row,dir),getCol(maze[row].length,col,dir),d).equals("E"))
				return true;
		}
		return false;
	}
	//helper method to get new row in direction d of r
	public static int getRow(int lim,int r, int d)
	{
		if(d==1)
			return (r-1>=0)?r-1:r;
		if(d==3)
			return (r+1<lim)?r+1:r;
		return r;
	}
	//helper method to get new col in direction d of c
	public static int getCol(int lim,int c, int d)
	{
		if(d==0)
			return (c+1<lim)?c+1:c;
		if(d==4)
			return (c-1>=0)?c-1:c;
		return c;
	}
}
/*
class Endpoint
{
	int x,y,num,dir;
	Endpoint p;
	public Endpoint(int a, int b, int n, int d, Endpoint e)
	{
		x = a;
		y = b;
		num = n;
		dir = d;
		p = e;
	}
	public boolean equals(Endpoint s)
	{
		return x==s.x&&y==s.y;
	}
}
*/
