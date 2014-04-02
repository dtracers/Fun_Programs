package tetris.Pieces;

import java.awt.Color;
import java.awt.Graphics;

import tetris.Map.map;
import tetris.Movement.Direction;

public class FutureBlock extends LivePiece
{
	public boolean drawfuture=true;
	private LivePiece future;
	int[]heights=new int[0];
	int[]minheights=new int[0];
	public FutureBlock()
	{
		setFuture(new DeadPiece());
	}
	public void setHeights(int[] heights)
	{
		this.heights=heights;
	}
	public int[] getHeights()
	{
		return heights;
	}
	@Override
	public void setX(int x)
	{
		super.setX(x);
		getFuture().setX(x);
		putdownghost();
	}
	@Override
	public void setColor(Color c)
	{
		super.setColor(c);
		getFuture().setColor(new Color(c.getRed(),c.getGreen(),c.getBlue(),255/2));
	//	future.setColor(c);
	}
	@Override
	public void setMap(map p)
	{
		super.setMap(p);
		getFuture().setMap(p);
		setMinHeight();
		putdownghost();
	}
	private void putdownghost()
	{
		getFuture().setY(getMin(heights,minheights,getFuture().getX())-1);
//		System.out.println("the ghost y "+future.getY()+"the current y "+y);
	}
	private int[] setMinHeight()
	{
		int[][]block=getMap().getBoard();
		minheights=new int[block[0].length];
		/**
		 * this is cool because it traverses by opposite of normal by going y:x instead of x:y
		 */
		for(int q=0;q<block[0].length;q++)
		{
			boolean found=false;
			int counter=block.length-1;
			while(!found&&counter>=0)
			{
				if(block[counter][q]==1)
				{
					found=true;
					if(counter>minheights[q])
						minheights[q]=counter;
				}
				counter-=1;
			}
		}
		return minheights;
	}
	private int getMin(int[]maxheights,int[]minheights,int x)
	{
		if(minheights==null||heights==null)
			return getY();
		int maxy=10000000;
		if(x+minheights.length>maxheights.length)
			return maxy;
		for(int k=0;k<minheights.length;k++)
		{
			maxy=Math.min(maxheights[x+k]-minheights[k], maxy);
		}
		return Math.max(maxy,getY());
	}
	@Override
	public void Rotate(Direction d)
	{
		super.Rotate(d);
		getFuture().Rotate(d);
		setMinHeight();
		putdownghost();
	}
	@Override
	public void copyTo(Piece p)
	{
		if(p==null)
			return;
		FutureBlock p2=((FutureBlock)p);
		getFuture().copyTo(p2.getFuture());
		p2.setHeights(heights);
		super.setMap(getMap());
		super.copyTo(p);
		p2.setMinHeight();
	}
	@Override
	public Piece copy()
	{
		FutureBlock p=new FutureBlock();
		copyTo(p);
		return p;
	}
	@Override
	public void draw(Graphics g,int offx,int offy,int xs,int ys)
	{
		super.draw(g, offx, offy, xs, ys);
		if(drawfuture)
		getFuture().draw(g, offx, offy, xs, ys);
	//	System.out.println("X: "+future.getX()+" Y: "+future.getY());
	}
	public void setFuture(LivePiece future)
	{
		this.future = future;
	}
	public LivePiece getFuture()
	{
		return future;
	}

}
