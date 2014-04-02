package math.algorthms.colorFracture;


import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import Image.color.*;
import math.algorthms.Algorithm;

public abstract class ColorFractureAlgorithm extends Algorithm
{
	private BufferedImage image;
	private Map<Color,ArrayList<Point>> colorMap;
	Color[][] colorBuffer;
	double intensity;
	int height,width;
	static boolean grapher;
	static boolean grapher2=true;
	static boolean quickMethod=false;
	public static double middle=13.119827142329614;
	
	//DEBUG ONLY
	ArrayList<Double> trues=new ArrayList<Double>();
	ArrayList<Double> falses=new ArrayList<Double>();
	
	
	//
	public ColorFractureAlgorithm(BufferedImage img,double intensity)
	{
		setImage(img);this.intensity=intensity;
	}
	
	public void init()
	{
		height=getImage().getHeight();
		width=getImage().getWidth();
		if(colorMap==null)
			setColorMap(new HashMap<Color,ArrayList<Point>>());
	}
	
	@Override
	public final void presolve()
	{
		if(getImage()==null)
		{
			setImage(new BufferedImage(0,0,BufferedImage.TYPE_INT_RGB));
		}
		createColorBuffer();
		fracturePresolve();
		System.gc();
	}

	/**
	 * Called after init() in presolve
	 */
	public abstract void fracturePresolve();
	
	public void resetImage(BufferedImage out)
	{
		int rbg= new Color(255,255,255).getRGB();
		for(int y=0;y<out.getHeight();y++)
		{
			for(int x=0;x<out.getWidth();x++)
			{
				out.setRGB(x, y,rbg);
			}
		}
	}
	
	public BufferedImage createCleanCopy(BufferedImage in)
	{
		BufferedImage out;
		if(in!=null)
			out=new BufferedImage(in.getWidth(),in.getHeight(),BufferedImage.TYPE_INT_RGB);
		else
			out=new BufferedImage(1000,1000,BufferedImage.TYPE_INT_RGB);
		resetImage(out);
		return out;
	}
	public boolean isSameColor(Color one,Color two,double compare)
	{
		if(quickMethod)
		{
			double red=Math.abs(one.getRed()-two.getRed());
			double green=Math.abs(one.getGreen()-two.getGreen());
			double blue=Math.abs(one.getBlue()-two.getBlue());
			return red<=compare&&green<=compare&&blue<=compare;
		}else
		{
			double red=Math.abs(one.getRed()-two.getRed());
			double green=Math.abs(one.getGreen()-two.getGreen());
			double blue=Math.abs(one.getBlue()-two.getBlue());
			boolean old=red<=compare&&green<=compare&&blue<=compare;
		//	System.out.println("old method "+old);
			double diff=((MyColor)(one)).getDifference((MyColor) two);
			boolean news=diff<=20;//3.118564077924423;
		//	System.out.println("new Method "+diff+" <= 2.4 is "+news);
			if(old)
			{
			//	trues.add(new Double(diff));
			}else
			{
				if(news)
				{
				//	System.out.println("Color one "+one+" Color two "+two);
				}
			//	falses.add(new Double(diff));
			}
			return news;
		}
	}
	public void copyData(Algorithm s)
	{
		if(s instanceof ColorFractureAlgorithm)
		{
			//setImage(((ColorFractureAlgorithm) s).getImage());
		}
	}

	public void addData(Algorithm s)
	{
		if(s instanceof ColorFractureAlgorithm)
		{
			Map t=((ColorFractureAlgorithm) s).getColorMap();
			if(t==null)
			{
				t=new HashMap<Color,ArrayList<Point>>();
			}
			copyMapTo(t);
			((ColorFractureAlgorithm) s).setColorMap(t);
		}
	}
	public void copyMapTo(Map t)
	{
		for(Color c:colorMap.keySet())
		{
			if(t.containsKey(c))
			{
				ArrayList<Point> array=((ArrayList<Point>)t.get(t));
				ArrayList<Point> array2=colorMap.get(c);
				if(array2==null)
				{
					array2=new ArrayList<Point>();
					colorMap.put(c,array2);
				}
				if(array==null)
				{
					array=new ArrayList<Point>();
					array.addAll(array2);
					t.put(c,array);
				}
			}else
				t.put(c, colorMap.get(c));
		}
	}

	public void setColorMap(Map<Color,ArrayList<Point>> colorMap) {
		this.colorMap = colorMap;
	}

	public Map<Color,ArrayList<Point>> getColorMap() {
		return colorMap;
	}

	public void setImage(BufferedImage image) {
		System.out.println("Image!!!!"+image);
		this.image = image;
		height=getImage().getHeight();
		width=getImage().getWidth();
	}

	public BufferedImage getImage() {
		return image;
	}
	public Color getColor(int x,int y)
	{
		return colorBuffer[y][x];
	}
	private void createColorBuffer()
	{
		colorBuffer=new Color[height][width];
		for(int y=0;y<height;y++)
		{
			for(int x=0;x<width;x++)
			{
				if(!quickMethod)
				{
					colorBuffer[y][x]=new MyColor(image.getRGB(x, y));
				}else
				{
					colorBuffer[y][x]=new Color(image.getRGB(x, y));
				}
			}
		}
		image=null;
	}
	
	public void postSolve()
	{
		System.gc();
		System.out.println("TRUE");
		Collections.sort(trues);
		for(Double d:trues)
		{
			System.out.println(d);
		}
		Collections.sort(falses);
		System.out.println("FALSE");
		for(Double d:falses)
		{
			System.out.println("\t"+d);
		}
	}
}
