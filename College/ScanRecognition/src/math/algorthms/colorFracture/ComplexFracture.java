package math.algorthms.colorFracture;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import Image.Grapher2;

public class ComplexFracture extends SimpleFracture
{
	int split=8;
	int quadSplitY;
	int quadSplitX;
	boolean simple;
	ArrayList<Point> allPoints;
	ArrayList<Point>[][] allSplitPoints;
	int skip=1;
	public ComplexFracture(BufferedImage img, double intensity)
	{
		super(img, intensity);
	}
	public void fracturePresolve()
	{
		super.fracturePresolve();
		setColorMap(new HashMap<Color,ArrayList<Point>>());
//		differentColors=new ArrayList<Color>();
		allPoints=new ArrayList<Point>();
		createSplit();
		allSplitPoints=new ArrayList[split][split];
	}
	public void createSplit()
	{
		split=height/50;
		quadSplitY=height/split;
		quadSplitX=width/split;
		if(quadSplitY==height%split&&quadSplitX==width%split)
			simple=true;
		split=split+(simple?0:1);
	}
	public void split()
	{
		if(simple)
		{
			for(int k=0;k<split;k++)
			{
				for(int q=0;q<split;q++)
				{
					ArrayList<Point> tempList=new ArrayList<Point>();
					for(int y=quadSplitY*k;y<quadSplitY*(k+1);y+=skip)
					{
						for(int x=quadSplitX*q;x<quadSplitX*(q+1);x+=skip)
						{
							Point p=new Point(x,y);
							tempList.add(p);
			//				pointBuffer[y][x]=p;
						}
					}
					allSplitPoints[k][q]=tempList;
				}
			}
		}
		else
		{
			for(int k=0;k<split;k++)
			{
				
				for(int q=0;q<split;q++)
				{
				//	System.out.println("Adding the next grid area "+(k*split+q)+"out of "+split*split);
					ArrayList<Point> tempList=new ArrayList<Point>();
					int maxY=Math.min(quadSplitY*(k+1), height);
					int maxX=Math.min(quadSplitX*(q+1),width);
					for(int y=quadSplitY*k;y<maxY;y+=1)
					{
						for(int x=quadSplitX*q;x<maxX;x+=1)
						{
							Point p=new Point(x,y);
							tempList.add(p);
			//				pointBuffer[y][x]=p;
						}
					}
					allSplitPoints[k][q]=tempList;
				}
			}
		}
	}
	public void FractureColors()
	{
		split();
	//	pointBuffer=new Point[height][width];
		
		System.out.println("Starting");
		
		//allPoints.add(pointBuffer[0][0]);
		allPoints.add(new Point(0,0));
	//	long time1=System.currentTimeMillis();
		while(allPoints.size()>0)
		{
			Point p=allPoints.get(0);
			Color c=getColor((int)p.getX(),(int)p.getY());
			allPoints=FractureColorsIterative(p,c);
		}
	//	long time2=System.currentTimeMillis();
	//	System.out.println("DONE! Total Time = "+(time2-time1));
	//	differentColors.addAll(colorMap.keySet());
	//	System.setOut(backup);
	//	System.out.println("Done!");
	}
	public ArrayList<Point> FractureColorsIterative(Point currentPoint,Color c)
	{
		ArrayList<Point> deadPoints=new ArrayList<Point>();
		ArrayList<Point> usedPoints=new ArrayList<Point>();
		Stack<DirectedPoint> stack=new Stack<DirectedPoint>();
		stack.add(new DirectedPoint(currentPoint,-1));
		long time1=System.currentTimeMillis();
		while(!stack.isEmpty())
		{
			DirectedPoint direct=stack.pop();
			currentPoint=direct.p;
	//		int counter=stack.size()/10;
			int direction=direct.direction;
			direct=null;
			int x=(int)currentPoint.getX();
			int y=(int)currentPoint.getY();
			int quadX=x/quadSplitX;
			int quadY=y/quadSplitY;
			if((x<0||x>=width||y<0||y>=height))
			{
			//	Grapher.Graph(counter,Color.green);
			//	deadPoints.add(currentPoint);
				continue;
			}
			if(deadPoints.contains(currentPoint))
			{
			//	Grapher.Graph(counter,Color.blue);
				continue;
			}
			if(!allSplitPoints[quadY][quadX].contains(currentPoint))
			{
			//	Grapher.Graph(counter,Color.red);
				continue;
			}
			Color c2=getColor(x,y);
			if(isSameColor(c2,c,compare))
			{
				c2=null;
				usedPoints.add(currentPoint);
				if(grapher2)
					Grapher2.Graph(currentPoint, c);
				if(direction!=2)
				{
					//if(y-skip>0)
					{
						DirectedPoint p2=new DirectedPoint(new Point(x,y-skip),3);//y-1
						stack.push(p2);
					}
				}
				if(direction!=3)
				{
				//	if(y+skip<height)
					{
						DirectedPoint p2=new DirectedPoint(new Point(x,y+skip),2);//y+1
						stack.push(p2);
					}
				}
				if(direction!=0)
				{
					//if(x-skip>0)
					{
						DirectedPoint p2=new DirectedPoint(new Point(x-skip,y),1);//x-1
						stack.push(p2);
					}
				}
				if(direction!=1)
				{
				//	if(x+skip<width)
					{
						DirectedPoint p2=new DirectedPoint(new Point(x+skip,y),0);//x+1
						stack.push(p2);
					}
				}
			}else
			{
				c2=null;
			//	Grapher.Graph(counter,Color.orange);
				deadPoints.add(currentPoint);
				continue;
			}
			//do stuff
		//	Grapher.Graph(counter,Color.black);
			allSplitPoints[quadY][quadX].remove(currentPoint);
		}
		long time2=System.currentTimeMillis();
				//after finish
		if(getColorMap().containsKey(c))
		{
			ArrayList<Point> p=getColorMap().get(c);
			p.add(new Point(-1,-1));
			p.addAll(usedPoints);
		}else
		{
			getColorMap().put(c, usedPoints);
		}
	//	allSplitPoints[quadY][quadX].remove(given);
		ArrayList<Point> newPoints=new ArrayList<Point>();
		if(time2-time1>50||usedPoints.size()>50||allPoints.size()<2)
		{
			
			for(int k=0;k<allSplitPoints.length;k++)
			{
				for(int q=0;q<allSplitPoints[k].length;q++)
				{
					newPoints.addAll(allSplitPoints[k][q]);
				}
			}
		}else
		{
			allPoints.removeAll(usedPoints);
			newPoints=allPoints;
		}
	//	allPoints=newPoints;
	//	long time3=System.currentTimeMillis();
	//	if(time3-time1>50)
	//		System.out.println("Color = "+c+"Total Time= "+(time3-time1)+" First Half= "+(time2-time1)+" Second Half= "+(time3-time2)+" Total Size = "+usedPoints.size());
		return newPoints;
	}
	class DirectedPoint
	{
		Point p;
		int direction;
		public DirectedPoint(Point p,int direction)
		{
			this.p=p;
			this.direction=direction;
		}
	}
}
