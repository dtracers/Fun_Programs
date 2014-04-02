package tetris.Pieces;

import java.awt.Color;
import java.awt.Graphics;

import tetris.Map.map;
import tetris.Movement.Direction;

public class LivePiece implements Piece
{
	private Color c;
	public map map=new map();
	int x,y;
	boolean underpiece;
	public LivePiece()
	{
		
	}

	@Override
	public map getMap()
	{
		return map;
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
	public void setMap(map p)
	{
		map.setTo(p);
	}

	@Override
	public void setPieceUnderneath(boolean b)
	{
		underpiece=b;
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
	public void update(long time)
	{
	}

	@Override
	public void draw(Graphics g,int offx,int offy,int xs,int ys)
	{
		int[][]bo=map.getBoard();
		int onex=xs/12;
		int twox=xs/6;
		int oney=ys/12;
		int twoy=ys/6;
		int x2=0;
		int y2=y*(ys-oney);
		for(int k=0;k<bo.length;k++)
		{
			x2=x*(xs-onex);
			for(int q=0;q<bo[k].length;q++)
			{
				if(bo[k][q]==1)
				{
					g.setColor(getColor());
					g.fillRect(offx+x2,offy+y2,xs-twox,ys-twoy);
					g.setColor(Color.black);
					g.drawRect(offx+x2,offy+y2,xs-onex,ys-oney);
				}
				x2+=(xs-onex);
			}			
			y2+=(ys-oney);
		}
	}
	
	@Override
	public void draw(Graphics g)
	{
		draw(g,0,0,5,5);
	}

	@Override
	public boolean isPiceUnderneath() {
		return underpiece;
	}

	@Override
	public Piece die(map p)
	{
		int[][] b=p.getBoard();
		int[][] bo=map.getBoard();
		for(int k=0;k<bo.length;k++)
		{
			for(int q=0;q<bo[k].length;q++)
			{
				if(bo[k][q]==1)
				{
					b[k+y][q+x]=1;
				}
			}
		}
		DeadPiece de=new DeadPiece();
		de.setX(x);
		de.setY(y);
		de.setMap(map);
		de.setColor(getColor().darker());
		return de;
	}
	
	public void Rotate(Direction d)
	{
		int[][] bo=map.getBoard();
		int[][]bo2=new int[bo[0].length][bo.length];
		if(d==Direction.CounterClockWise)
		{
			for(int k=0;k<bo.length;k++)
			{
				for(int q=0;q<bo[0].length;q++)
				{
					if(bo[k][q]==1)
					{
						bo2[bo2.length-1-q][k]=1;
					}
				}
			}
		}else
		{
			for(int k=0;k<bo.length;k++)
			{
				for(int q=0;q<bo[0].length;q++)
				{
					if(bo[k][q]==1)
					{
						bo2[q][bo.length-1-k]=1;
					}
				}
			}
		}
	//	System.out.println("Before Rotation\n"+map.printBoard());
		map.setBoard(bo2);
	//	System.out.println("After Rotation\n"+map.printBoard());
	}
	
	public Piece rotate(Direction d)
	{
		LivePiece p=(LivePiece)copy();
		p.Rotate(d);
		return p;
	}
	
	public boolean equals(Object b)
	{
		//FIXME add it so it is more precise
		LivePiece p=(LivePiece)b;
		if(p.getX()==x&&p.getY()==y)
			return true;
		return false;
	}

	public void setColor(Color c) {
		this.c = c;
	}

	public Color getColor() {
		return c;
	}
	
	@Override
	public Piece copy()
	{
		LivePiece p=new LivePiece();
		copyTo(p);
		return p;
	}

	@Override
	public void copyTo(Piece p)
	{
		if(p==null)
			return;
		p.setX(x);
		p.setY(y);
		p.setMap(map);
		((LivePiece)p).setColor(c);
		p.setPieceUnderneath(underpiece);
	}
}
