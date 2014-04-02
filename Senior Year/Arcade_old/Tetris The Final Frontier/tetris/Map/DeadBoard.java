package tetris.Map;

import java.util.ArrayList;

import tetris.Pieces.Piece;

public class DeadBoard extends map
{
	private ArrayList<Piece> containedDead=new ArrayList<Piece>();
	private ArrayList<Piece> containedZombies=new ArrayList<Piece>();
	public DeadBoard(int[][] is)
	{
		super(is);
	}
	public void setBoard(int[][] b)
	{
		board=b;
	}
	public void setDeadWithin(ArrayList<Piece> deadones)
	{
		this.containedDead = deadones;
	}
	public ArrayList<Piece> getDeadWithin()
	{
		return containedDead;
	}
	public void setContainedZombies(ArrayList<Piece> containedZombies)
	{
		this.containedZombies = containedZombies;
	}
	public ArrayList<Piece> getContainedZombies()
	{
		return containedZombies;
	}
}
