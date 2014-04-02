package tetris.Pieces;

import java.awt.Color;
import java.awt.Graphics;

import tetris.Map.map;
import tetris.Movement.Direction;

public class BackGroundPiece implements Piece
{
	int x,y;
	public Color c=Color.black;
	@Override
	public void draw(Graphics g, int offx, int offy, int xs, int ys)
	{
		int y2=y*(ys-(ys/12));
		int x2=x*(xs-(xs/12));
		g.setColor(c);
		g.fillRect(offx+x2,offy+y2,xs-(xs/6),ys-(ys/6));
		g.setColor(Color.black);
		g.drawRect(offx+x2,offy+y2,xs-(xs/12),ys-(ys/12));
	}

	@Override
	public int getX()
	{
		return x;
	}

	@Override
	public int getY()
	{
		return y;
	}
	
	@Override
	public void setX(int x)
	{
		this.x=x;
	}

	@Override
	public void setY(int y)
	{
		this.y=y;
	}
	
	@Override
	public Piece die(map p){return null;}

	@Override
	public boolean isPiceUnderneath(){return false;}
	
	@Override
	public map getMap(){return null;}

	@Override
	public Piece rotate(Direction d){return null;}

	@Override
	public void setMap(map p) {}

	@Override
	public void setPieceUnderneath(boolean b){}

	@Override
	public void update(long time){}

	@Override
	public void draw(Graphics g){}

	@Override
	public Piece copy(){return null;}

	@Override
	public void copyTo(Piece p)
	{
		// TODO Auto-generated method stub
		
	}

}
