package tetris.Movement;

import java.util.ArrayList;

import tetris.Map.map;
import tetris.Pieces.Piece;

public class PlaceChecker
{
	map deads;
	ArrayList<Piece>lives;
	public PlaceChecker(map deadpiece,ArrayList<Piece> otherlive)
	{
		deads=deadpiece;
		lives=otherlive;
	}
	/**
	 * <\p>this will check to see if the piece can move in the direction given by two steps</p>
	 * <\p>1:it compares it to all the dead pieces in the vicinity to see if they able to run into the dude or not</p>
	 * <\p>2:it will go through the arrayList of all live pieces and compare positions to make sure that they are not 
	 * running into it</p>
	 * @param current
	 * @param d
	 * @return
	 */
	public boolean canMove(Direction d,Piece current)
	{
		current.setPieceUnderneath(false);
		int x=current.getX();
		int y=current.getY();
		int[][] bo=current.getMap().getBoard();
		int[][]de=deads.getBoard();
		int maxX=bo[0].length+x;
		int maxY=bo.length+y;
		if(maxY+d.y-1>=de.length)
			return false;
		if(y+d.y<0)
			return false;
		if(x+d.x<0)
			return false;
		if(maxX+d.x-1>=de[y+d.y].length)
			return false;
		for(int k=y;k<maxY;k++)
		{
			for(int q=x;q<maxX;q++)
			{
			//	System.out.println("y: "+y+" K "+k+" index "+(k-y));
			//	System.out.println("x: "+x+" q "+q+" index "+(q-x));
				if(bo[k-y][q-x]==1)
				{
					if(de[k+d.y][q+d.x]==1)
						return false;
				}
			}
		}
		//step two
		if(lives.size()>1)
		for(int w=0;w<lives.size();w++)
		{
			Piece p=lives.get(w);
			if(!p.equals(current)&&p!=null)
			{
				int[][] b2=p.getMap().getBoard();
				int ty=p.getY();
				int tx=p.getX();
				for(int k=0;k<bo.length;k++)
				{
					for(int q=0;q<bo[0].length;q++)
					{
						if(bo[k][q]==1)
						{
							int x2=x+q-tx+d.x;
							int y2=y+k-ty+d.y;
							if(x2>=0&&y2>=0&&y2<b2.length&&x2<b2[y2].length)
							{
								if(b2[y2][x2]==1)
								{
									if(d==Direction.Down)
										current.setPieceUnderneath(true);
									return false;
								}
							}
						}
					}
				}
			}
		}
		return true;
	}
	public boolean canRotate(Direction d,Piece current)
	{
		Piece p=current.rotate(d);
		if(canMove(Direction.Stay,p))
		{
			System.out.println("I Can ROTAtE");
			return true;
		}
		else if(canMove(Direction.Left,p))
		{
			System.out.println("I Can ROTAtE\nIf i move left");
			current.setX(current.getX()-1);
			return true;
		}else if(canMove(Direction.Right,p))
		{
			System.out.println("I Can ROTAtE\nIf i move right");
			current.setX(current.getX()+1);
			return true;
		}
		return false;
	}

}