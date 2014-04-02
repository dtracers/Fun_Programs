package tetris.Pieces;

import java.awt.Graphics;

import tetris.Map.map;
import tetris.Movement.Direction;
import Interface.Drawable;
import Interface.Updateable;

/**
 * the map of a piece must touch every single wall at least once
 * @author New User
 *
 */
public interface Piece extends Updateable,Drawable
{
	public int getX();
	public int getY();
	public void setX(int x);
	public void setY(int y);
	public map getMap();
	public void setMap(map p);
	/**
	 * this is for the very specific case in-case there is a live piece underneath it
	 * @param b
	 */
	public void setPieceUnderneath(boolean b);
	public boolean isPiceUnderneath();
	public Piece die(map p);
	public Piece rotate(Direction d);
	public void draw(Graphics g,int offx,int offy,int xs,int ys);
	public Piece copy();
	public void copyTo(Piece p);
}
