package Image.color;

import java.awt.Color;
import java.util.ArrayList;

public class ColorTree
{
	/**
	 * This color
	 */
	Color c;
	/**
	 * This will be a list of colors between this and the parent
	 */
	ArrayList<Color> colorLine;
	public boolean isSameType(Color c)
	{
		return false;
	}
	/**
	 * This will return the color ratio
	 * for example (255,0,0) will be
	 * R->G = Different = G->R
	 * R->B = Different = B->R
	 * B->G = the same  = G->B
	 * 
	 * (R->G)->(R->B)= The same  = (R->B)->(R->G)
	 * (R->G)->(B->G)= Different = (B->G)->(R->G)
	 * (R->B)->(B->G)= Different = (B->G)->(R->B)
	 * They will be given values for degrees of difference:
	 * Example 2 (255,125,0)
	 * R->G = Different = G->R
	 * R->B = Different = B->R
	 * B->G = Different = G->B
	 * but
	 * (R->G)
	 * @param c
	 * @return
	 */
	public double[][] generateColorRatio(Color c)
	{
		return null;
	}
	public double getDifference()
	{
		return 0;
	}
}