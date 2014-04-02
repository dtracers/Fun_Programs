package tetris.Map;

import java.util.ArrayList;

import tetris.Pieces.Piece;

public class LiveMap extends map
{
	ArrayList<Piece> list;
	public LiveMap()
	{
		super();
	}
	public LiveMap(int[][]board)
	{
		super(board);
	}
	public LiveMap(int[][]board,ArrayList<Piece> list)
	{
		super(board);
		this.list=list;
	}
	public int[][] getBoard()
	{
		board=new int[board.length][board[0].length];
		if(list.size()>0)
		{
			for(Piece p:list)
			{
				int x=p.getX();
				int y=p.getY();
				int[][] bo2=p.getMap().getBoard();
				for(int k=0;k<bo2.length;k++)
				{
					for(int q=0;q<bo2[k].length;q++)
					{
						if(bo2[k][q]==1)
						board[k+y][q+x]=1;
					}
				}
			}
		}
		return board;
	}
	public String printBoard()
	{
		getBoard();
		return super.printBoard();
	}
}
