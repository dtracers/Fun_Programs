package graphics;

import java.awt.Color;
import java.awt.Graphics;
import Interface.Drawable;

public class FlashyText extends ColorChanger implements Drawable
{
	int x,y;
	String str;
	public FlashyText(int totaltime,int x,int y,String text,Color... colors)
	{
		super(totaltime,colors);
		str=text;
		this.x=x;
		this.y=y;
	}
	@Override
	public void draw(Graphics g)
	{
		g.setColor(getCurrentColor());
		g.drawString(str, x, y);
	}
}
