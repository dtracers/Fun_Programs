package math.algorthms.colorFracture;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
/**
* SimpleFractureSplit, type=3
* this will do the default matching but then it will get all the left over points
* and try and find new colors by the similar regions
* @param intensity
*/
public class SimpleFractureSplit extends SimpleFractureImproved
{
	ArrayList<Point> otherPoints;
	public SimpleFractureSplit(BufferedImage img, double intensity) {
		super(img, intensity);
		// TODO Auto-generated constructor stub
	}
	public void initColors()
	{
		super.init();
		if(otherPoints==null)
		{
			otherPoints=new ArrayList<Point>();
		}
	}
	protected void handleColor(Color c2, Point p)
	{
		boolean isMapped=false;
		for(Color c:getColorMap().keySet())
		{
			if(isSameColor(c,c2,compare))
			{
				isMapped=true;
				getColorMap().get(c).add(p);
			}
		}
		if(!isMapped)
		{
			otherPoints.add(p);
		}
	}
	
}
