package math.algorthms.colorFracture;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import math.algorthms.Algorithm;
import Image.Grapher2;
import Image.color.MyColor;
/**
 * SimpleFracture, type=1
 * This will separate colors of similar nature out to only more specialized types
 * first it uses the already set of normal colors that exist
 * it will then create an image of the extra colors and tries to move on from there
 * bad version
 **/
public class SimpleFracture extends ColorFractureAlgorithm{

	double compare=0;
	boolean printTime=true;
	boolean debug=false;
	String otherName="";
	PrintStream backup=null;
	public SimpleFracture(BufferedImage img, double intensity) {
		super(img, intensity);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void fracturePresolve() {
		// TODO Auto-generated method stub
		compare=256./4./intensity;
	}
	public void init()
	{
		super.init();
		addColor(0,0,0);
	}
	public boolean printAndsolve()
	{
		PrintStream printing=null;
		try {
			printing = new PrintStream(new FileOutputStream(new File(this.getClass().getSimpleName()+" out"+otherName+".txt")));	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		if(debug)
		{
			backup=System.out;
			System.setOut(printing);
		}
		long Mtime1=System.currentTimeMillis();
		long Ntime1=System.nanoTime();
		FractureColors();
		long Ntime2=System.nanoTime();
		long Mtime2=System.currentTimeMillis();
		try
		{
			printing.println("It took "+(Ntime2-Ntime1)+" Nanoseconds to solve "+(Mtime2-Mtime1)+" Milliseconds to solve");
		}catch(Exception e)
		{
			
		}
		return true;
	}
	@Override
	public boolean solve() {
		if(printTime)
		{
			printAndsolve();
		}else
		{
			FractureColors();
		}
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void postsolve() {
		super.postSolve();
		// TODO Auto-generated method stub
		
	}
	private void addColor(int one,int two,int three)
	{
	//	System.out.println("R: "+one+" G: "+two+" B: "+three);
		
		if(one<256&&two<256&&three<256)
		{
			Color c=new Color(one,two,three);
			if(!quickMethod)
			{
				c=new MyColor(c);
			}
			if(!getColorMap().containsKey(c))
			{
				addColor(c);
				addColor(one+255/8,two,three);
				addColor(one,two+255/8,three);
				addColor(one,two,three+255/8);
			}
		}else
		{
			return;
		}
	}
	public void addColor(Color c)
	{
		if(!getColorMap().containsKey(c))
		{
			getColorMap().put(c, new ArrayList<Point>());
		}
	}
	
	protected void FractureColors()//it is assumed that it ranges from 1 to 10
	{
	//	int height=getImage().getHeight();
	//	int width=getImage().getWidth();
		for(int y=0;y<height;y++)
		{
			for(int x=0;x<width;x++)
			{
				
				Color c2=getColor(x,y);
		//		Grapher.Graph(x+y,c2);
				Point p=new Point(x,y);
				if(grapher2)
					Grapher2.Graph(p, c2);
				handleColor(c2,p);
			}
		}
	}
	protected void handleColor(Color c2, Point p)
	{
		for(Color c:getColorMap().keySet())
		{
			if(isSameColor(c,c2,compare))
			{
				getColorMap().get(c).add(p);
			}
		}
		// TODO Auto-generated method stub
		
	}
	public void copyData(Algorithm s)
	{
		super.copyData(s);
	}
}
