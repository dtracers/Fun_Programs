package math.algorthms.colorFracture;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
/**
* SimpleFractureImproved, type=2
* an improvement on alg0
* This will use the colors that have not been stored to improve it
* It takes longer though...
* */
public class SimpleFractureImproved extends SimpleFracture {

	public SimpleFractureImproved(BufferedImage img, double intensity) {
		super(img, intensity);
		// TODO Auto-generated constructor stub
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
			addColor(c2);
			getColorMap().get(c2).add(p);
		}
	}
}
