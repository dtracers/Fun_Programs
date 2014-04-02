package math.algorthms.colorFracture;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Stack;

import Image.Grapher2;

public class ComplexFractureRim extends ComplexFracture {

	public ComplexFractureRim(BufferedImage img, double intensity) {
		super(img, intensity);
		// TODO Auto-generated constructor stub
	}
	@Override
	public ArrayList<Point> FractureColorsIterative(Point currentPoint,Color c)
	{
		//System.out.println("hello");
		ArrayList<Point> deadPoints=new ArrayList<Point>();
		ArrayList<Point> usedPoints=new ArrayList<Point>();
		Stack<DirectedPoint> stack=new Stack<DirectedPoint>();
		stack.add(new DirectedPoint(currentPoint,-1));
		long time1=System.currentTimeMillis();
		isPointLegal(currentPoint,deadPoints,c);
		while(!stack.isEmpty())
		{
			DirectedPoint direct=stack.pop();
			int x=(int)currentPoint.getX();
			int y=(int)currentPoint.getY();
			currentPoint=direct.p;
	//		int counter=stack.size()/10;
			int direction=direct.direction;
			direct=null;
			
			boolean notOnEdge=true;
			Point p=null;
			if(direction!=2)
			{
				p=new Point(x,y-skip);
				int i=isPointLegal(p,deadPoints,c);
				if(i>0)
				{
					DirectedPoint p2=new DirectedPoint(p,3);//y-1
					stack.push(p2);
				}else
					notOnEdge=notOnEdge&&i!=-1;
			}
			if(direction!=3)
			{
				p=new Point(x,y+skip);
				int i=isPointLegal(p,deadPoints,c);
				if(i>0)
				{
					DirectedPoint p2=new DirectedPoint(p,2);//y+1
					stack.push(p2);
				}else
					notOnEdge=notOnEdge&&i!=-1;
			}
			if(direction!=0)
			{
				p=new Point(x-skip,y);
				int i=isPointLegal(p,deadPoints,c);
				if(i>0)
				{
					DirectedPoint p2=new DirectedPoint(p,1);//x-1
					stack.push(p2);
				}else
					notOnEdge=notOnEdge&&i!=-1;
			}
			if(direction!=1)
			{
				p=new Point(x+skip,y);
				int i=isPointLegal(p,deadPoints,c);
				if(i>0)
				{
					DirectedPoint p2=new DirectedPoint(p,0);//x+1
					stack.push(p2);
				}else
					notOnEdge=notOnEdge&&i!=-1;
			}
			if(!notOnEdge)
			{
				if(grapher2)
					Grapher2.Graph(currentPoint, c);
				usedPoints.add(currentPoint);
			}
			//do stuff
		//	Grapher.Graph(counter,Color.black);
			
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
		ArrayList<Point> newPoints=new ArrayList<Point>();
		if(time2-time1>50||usedPoints.size()>50||allPoints.size()<2||true)
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
	private int isPointLegal(Point currentPoint,ArrayList<Point> deadPoints,Color c)
	{
		int x=(int)currentPoint.getX();
		int y=(int)currentPoint.getY();
		int quadX=x/quadSplitX;
		int quadY=y/quadSplitY;
		if((x<0||x>=width||y<0||y>=height))
		{
			return -1;
		}
		if(deadPoints.contains(currentPoint))
		{
			return -1;
		}
		if(!allSplitPoints[quadY][quadX].contains(currentPoint))
		{
			return 0;
		}
		Color c2=getColor(x,y);
		if(!isSameColor(c2,c,compare))
		{
			deadPoints.add(currentPoint);
			return -1;
		}
		allSplitPoints[quadY][quadX].remove(currentPoint);
		return 1;
	}
}
