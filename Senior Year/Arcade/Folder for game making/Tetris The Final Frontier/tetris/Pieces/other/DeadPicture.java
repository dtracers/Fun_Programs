package tetris.Pieces.other;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import files.FileLoader;

import tetris.Map.map;
import tetris.Pieces.DeadPiece;
import tetris.Pieces.FutureBlock;
import tetris.Pieces.Piece;

public class DeadPicture extends DeadPiece
{
	private Image pic;
	public DeadPicture()
	{
	}
	public void setPic(Image pic)
	{
		this.pic = pic;
	}
	public Image getPic()
	{
		return pic;
	}
	public void loadPic(String name)
	{
		pic=new FileLoader().image(name);
	}
	@Override
	public void draw(Graphics g,int offx,int offy,int xs,int ys)
	{
		int onex=xs/12;
		int twox=xs/6;
		int oney=ys/12;
		int twoy=ys/6;
		int x2=getX()*(xs-onex);
		int y2=getY()*(ys-oney);
		g.drawImage(pic, offx+x2, offy+y2, xs-twox, ys-twoy, null);
	}
	@Override
	public void copyTo(Piece p)
	{
		if(p==null)
			return;
		PictureBlock p2=((PictureBlock)p);		
		p2.setPic(getPic());
		super.copyTo(p2);
	}
	@Override
	public Piece copy()
	{
		PictureBlock p2=new PictureBlock();
		copyTo(p2);
		return p2;
	}
	@Override
	public Piece die(map p)
	{
		super.die(p);
		return copy();
	}
}
