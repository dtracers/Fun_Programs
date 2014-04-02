package tetris.Pieces.other;

import java.awt.Color;
import java.util.ArrayList;

import tetris.Map.DeadBoard;
import tetris.Map.map;
import tetris.Pieces.DeadPiece;
import tetris.Pieces.Piece;

public class BombBlock extends PictureBlock
{
	//FIXME
	public BombBlock()
	{
		super();
		loadPic("Pictures/bomb.png");
		setColor(Color.white);
		setFuture(new BombBlock(true));
	}
	public BombBlock(boolean doubletrue)
	{
		super(doubletrue);
		loadPic("Pictures/bomb.png");
		setColor(Color.white);
	}
	@Override
	public void copyTo(Piece p)
	{
		if(p==null)
			return;
		BombBlock p2=((BombBlock)p);		
		super.copyTo(p2);
	}
	@Override
	public Piece copy()
	{
		BombBlock p2=new BombBlock();
		copyTo(p2);
		return p2;
	}
	@Override
	public Piece die(map p)
	{
		int[][]board=p.getBoard();
		
		//gets the bounds one away
		int xl2=getX()-1;
		int xr2=getX()+1;
		int yu2=getY()-(int)Math.random()*2;
		int yd2=getY()+(int)Math.random()*2;
		//makes sure bounds are inside the grid
		
		if(xl2<=0)
			xl2=0;
		if(yu2<=0)
			yu2=0;
		if(xr2>=p.getWidth())
			xr2=p.getWidth()-1;
		if(yd2>=p.getHeight())
			yd2=p.getHeight()-1;
		//deletes the blocks from the board
		for(int w=yu2;w<=yd2;w++)
		{
			for(int q=xl2;q<=xr2;q++)
			{
				board[w][q]=0;
			}
		}
		p.setBoard(board);
		
		DeadBoard p2=(DeadBoard)p;		
		ArrayList<Piece>pieces=p2.getDeadWithin();
		
		kill(pieces,xl2,xr2,yu2,yd2);
		
		pieces=p2.getContainedZombies();
		
		kill(pieces,xl2,xr2,yu2,yd2);
		
		return null;
	}
	public void kill(ArrayList<Piece> pieces,int xl2,int xr2,int yu2,int yd2)
	{
		for(int w=0;w<pieces.size();w++)
		{
			DeadPiece dead=(DeadPiece)pieces.get(w);
			int[][] map=dead.getMap().getBoard();
			//resets bounds to the bounds inside the grid
			int xl=xl2-dead.getX();
			int xr=xr2-dead.getX();
			int yu=yu2-dead.getY();
			int yd=yd2-dead.getY();
			//narrows to inside the block grid
			if(xl<0)
				xl=0;
			if(yu<0)
				yu=0;
			if(xr>=map[0].length)
				xr=map[0].length-1;
			if(yd>=map.length)
				yd=map.length-1;
			
			for(int k=yu;k<=yd;k++)
			{
				for(int q=xl;q<=xr;q++)
				{
					map[k][q]=0;
				}
			}
			dead.setMap(new map(map));
		}
	}
}
