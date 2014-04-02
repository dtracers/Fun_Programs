package graphics;


import java.awt.Color;
import Interface.Updateable;

public class ColorChanger implements Updateable
{
	private Color currentColor;
	Color[] colors;
	private final double timepercolor;
	private int tillnext;
	private int indexat=0;
	int tt=0;
	public ColorChanger(int totaltime,Color... colors)
	{
		tt=totaltime;
		this.colors=colors;
		timepercolor=((double)tt)/((double)colors.length);
	}
	public ColorChanger(boolean num,int totaltime,Color... colors)
	{
		tt=totaltime*colors.length;
		this.colors=colors;
		timepercolor=((double)tt)/((double)colors.length);
	}
	@Override
	public void update(long time)
	{
		tillnext+=time;
		if(tillnext>=timepercolor)
		{
			tillnext%=timepercolor;
			indexat++;
			if(indexat>=colors.length)
				indexat=0;
		}
		changeColor();
	}
	public void changeColor()
	{
		double ratio=((double)timepercolor-(double)tillnext)/((double)timepercolor);
		double goRed=colors[(indexat+1)%colors.length].getRed();
		double goBlue=colors[(indexat+1)%colors.length].getBlue();
		double goGreen=colors[(indexat+1)%colors.length].getGreen();
		
		double oldRed=colors[(indexat)].getRed();
		double oldBlue=colors[(indexat)].getBlue();
		double oldGreen=colors[(indexat)].getGreen();
		
		double currRed=(goRed-oldRed)*(1-ratio)+oldRed;
		double currBlue=(goBlue-oldBlue)*(1-ratio)+oldBlue;
		double currGreen=(goGreen-oldGreen)*(1-ratio)+oldGreen;
		
		setCurrentColor(new Color((int)currRed,(int)currGreen,(int)currBlue));
	}
	public void setCurrentColor(Color currentColor) {
		this.currentColor = currentColor;
	}
	public Color getCurrentColor() {
		return currentColor;
	}
}
