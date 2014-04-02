package tetris.Map;

import java.util.ArrayList;

import tetris.Pieces.Piece;

public class map
{
	/**
	 * this will contain the pieces for each board
	 * 1 is there 0 is not there
	 */
	protected int[][] board;
	public map()
	{
		
	}
	public map(int[][]board)
	{
		this.board=board;
	}
	public void setBoard(int[][] b)
	{
		int[][] btemp=b;
		board=new int[btemp.length][btemp[0].length];
		for(int k=0;k<btemp.length;k++)
		{
			for(int q=0;q<btemp[0].length;q++)
			{
				board[k][q]=btemp[k][q];
			}
		}
	}
	public int[][] getBoard()
	{
		return board;
	}
	public void setTo(map p)
	{
		setBoard(p.getBoard());
	}
	public int getWidth()
	{
		return board[0].length;
	}
	public int getHeight()
	{
		return board.length;
	}
	public String printBoard()
	{
		String g="";
		for(int k=0;k<board.length;k++)
		{
			for(int q=0;q<board[0].length;q++)
			{
				g+=board[k][q];
			}
			g+="\n";
		}
		return g;
	}
}
