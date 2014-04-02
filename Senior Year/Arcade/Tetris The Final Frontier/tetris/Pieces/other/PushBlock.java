package tetris.Pieces.other;

import java.awt.Color;
import java.util.ArrayList;

import tetris.Map.DeadBoard;
import tetris.Map.map;
import tetris.Pieces.DeadPiece;
import tetris.Pieces.Piece;

public class PushBlock extends PictureBlock
{
	Color temp;
	public PushBlock()
	{
		super();
		loadPic("Pictures/Arrow.png");
		setColor(Color.black);
		setFuture(new PushBlock(true));
	}
	public PushBlock(boolean doubletrue)
	{
		super(doubletrue);
		loadPic("Pictures/Arrow.png");
		setColor(Color.black);
	}
	public Piece die(map p)
	{
		boolean bottom=false;
		int y=getY();
		int x=getX();
		int counter=y;
		int[][] dead=p.getBoard();
		do
		{
				counter+=1;
		}
		while(counter<dead.length&&dead[counter][x]!=0);
		System.out.println("final y is "+counter);
		System.out.println("the map length is "+dead.length);
		System.out.println("the block one above is "+dead.length);
		//it will only do it if there is a zero at the bottom
		if(counter<dead.length)
		{
			dead[y][x]=0;
			dead[counter][x]=1;
			/*bottom|=*/movedown2(((DeadBoard) p).getDeadWithin(),x,y,counter);
			/*bottom|=*/movedown2(((DeadBoard) p).getContainedZombies(),x,y,counter);
		}else
		{
			dead[counter-1][x]=1;
			p.setBoard(dead);
		}
		
		/*if the bottom doesn't exist (it can happen)
		if(!bottom)
		{
			DeadPiece needed=new DeadPiece();
			needed.setX(x);
			needed.setY(counter);
			needed.setMap(new map(new int[][]{{1}}));
			needed.setColor(temp);
			((DeadBoard) p).getDeadWithin().add(needed);
		}
		*/
		
		DeadPicture de=new DeadPicture();
		de.setX(x);
		//it will move it down if there is a zero at the bottom or else it will stay where it is
		if(counter<dead.length)
			de.setY(y+1);
		else
			de.setY(y);
		de.setMap(new map(getMap().getBoard()));
		de.setMap(getMap());
		// remove the live one from the map!!!
		de.setPic(getPic());
		return de;
	}
	/**
	 * will return true only if the bottom has been found in pieces
	 **/
	public boolean movedown(ArrayList<Piece> pieces,int x,int min,int max)
	{
		boolean bottomplaced=false;
		for(int w=0;w<pieces.size();w++)
		{
			DeadPiece dead=(DeadPiece)pieces.get(w);
			
			int[][] map=dead.getMap().getBoard();
			if(x>=dead.getX()&&x<dead.getX()+map[0].length)
			{
				int yu=min-dead.getY();
				int yd=max-dead.getY();
				//checks to see if it is at the max
				if(yd==map.length)
				{
					temp=dead.getColor();
				}
				if(yd<map.length&&yd>=0)
				{
					bottomplaced=true;
					map[yd][x]=1;
				}
				//checks to see if it is at the max
				if(yu<map.length&&yu>=0)
				{
					map[yu][x]=1;
				}
				dead.setMap(new map(map));
			}
		}
		return bottomplaced;
	}
	/**
	 * the other way of moving down where it will make it bigger and blah blah blah
	 * @param pieces
	 * @param x
	 * @param min
	 * @param max
	 * @return
	 */
	public boolean movedown2(ArrayList<Piece> pieces,int x,int min,int max)
	{
		for(int w=0;w<pieces.size();w++)
		{
			DeadPiece dead=(DeadPiece)pieces.get(w);
			
			int[][] map=dead.getMap().getBoard();
			int yu=min-dead.getY();
			int yd=max-dead.getY();
			int newx=x-dead.getX();
			//if x bounds are within the block and the y is possible
			System.out.println("yd "+yd+" yu "+yu);
			if(newx>=0&&newx<map[0].length&&yd>0&&yu<map.length)
			{
				System.out.println("im inside bounds ");
				int newy=map.length;
				if(yd>=map.length)
				{
					newy+=1;
				}
				int[][]map2=new int[newy][map[0].length];
				
				for(int k=0;k<map.length;k++)
				{
					for(int q=0;q<map[0].length;q++)
					{
						if(q!=newx)
						{
							map2[k][q]=map[k][q];
						}
					}
				}
				
				int minmake=Math.min(yd, map.length);//this is in-case the max index is after the end of this block
				int maxmake=Math.max(yu,0);//this is in-case the min index is before the start of this block
				System.out.println("the deads y cordinate "+dead.getY());
				System.out.println("the minmake "+minmake);
				System.out.println("the maxmake "+maxmake);
				for(int k=maxmake;k<minmake;k++)
				{
					map2[k+1][newx]=map[k][newx];
				}
				dead.setMap(new map(map2));
			}
			if(map.length==1&&map[0].length==1)
			{
				dead.setY(getY()+1);
				dead.setMap(new map(new int[][]{{1}}));
			}
		}
		return true;
	}
	@Override
	public void copyTo(Piece p)
	{
		if(p==null)
			return;
		PushBlock p2=((PushBlock)p);		
		super.copyTo(p2);
	}
	@Override
	public Piece copy()
	{
		PushBlock p2=new PushBlock();
		copyTo(p2);
		return p2;
	}

}
