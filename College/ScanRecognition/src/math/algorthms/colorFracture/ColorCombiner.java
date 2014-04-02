package math.algorthms.colorFracture;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import math.algorthms.Algorithm;

import Image.Grapher2;

public class ColorCombiner extends SimpleFracture {
	CyclicBarrier barrier;
	Map<Color,ArrayList<Color>> colorMap2;
	public static double DIFFERENCE_THRESHOLD=20;
	public ColorCombiner(BufferedImage img, double intensity) {
		super(img, intensity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void fracturePresolve() {
		// TODO Auto-generated method stub

	}

	@Override
	public void FractureColors() {
		System.out.println("COLORING");
		//
		colorMap2=new HashMap<Color,ArrayList<Color>>();
		
		/*
		Map<Color,ArrayList<Point>> colorMap2=new HashMap<Color,ArrayList<Point>>();
		ArrayList<Color> colors=new ArrayList<Color>();
		colors.addAll(getColorMap().keySet());
		for(Color c:getColorMap().keySet())
		{
			for(int k=0;k<colors.size();k++)
			{
				Color c2=colors.get(k);
				if(!c2.equals(c)&&isSameColor(c2,c,compare))
				{
					ArrayList<Point> points;
					if(colorMap2.containsKey(c))
					{
						points=colorMap2.get(c);
					}else
					{
						points=new ArrayList<Point>();
					}
					points.addAll(getColorMap().get(c));
					colorMap2.put(c, points);
				}
			}
		}
		*/
		int x=0;
		
		int size=getColorMap().keySet().size();
		int divide=100;
		int rows=(int)Math.ceil(((double)size)/((double)divide));
		int counter=0;
		ArrayList<Color> s=new ArrayList<Color>();
		barrier = new CyclicBarrier(rows);
		System.out.println("Total size is "+rows);
		for(Color c:getColorMap().keySet())
		{
			if(counter>divide)
			{
				s.add(c);
				Thread d=new MyThread(s,x);
				d.setDaemon(true);
				d.start();
				counter=0;
				x+=1;
				s=new ArrayList<Color>();
				
			}else
			{
				counter++;
				s.add(c);
			}
		}
		System.out.println("Finished with threads "+x);
		while(x<rows)
		{
			Thread d=new Thread(){
				public void run()
				{
					try {
						System.out.println("My Row is finished:-1 rows left="+(barrier.getParties()-barrier.getNumberWaiting()));
						barrier.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			d.setDaemon(true);
			d.start();
			x+=1;
			System.out.println("Adding extra "+x);
		}
	//	System.out.println("Finished with threads");
		System.out.println("Total size is "+rows);
		try {
			barrier.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("I AM DONE");
	}

	@Override
	public void postsolve() {
		// TODO Auto-generated method stub

	}
	public void copyData(Algorithm s)
	{
		System.out.println("COPYING");
		if(s instanceof ColorFractureAlgorithm)
		{
			this.setColorMap(((ColorFractureAlgorithm) s).getColorMap());
		}
	}
	class MyThread extends Thread
	{
		ArrayList<Color> c;
		int x;
		public MyThread(ArrayList<Color> s,int x)
		{
			c=s;
			this.x=x;
		}
		ArrayList<Color> colorList=new ArrayList<Color>();
		
		public void run()
		{
			int x2=0;
			for(Color mine:c)
			{
				for(Color c2:getColorMap().keySet())
				{
					if(!c2.equals(mine)&&isSameColor(c2,mine,compare))
					{
					//	System.out.println("COLORING");
						colorList.add(c2);
					}
				}
				colorMap2.put(mine, colorList);
			//	System.out.println("set "+x+"row "+x2+" is finished");
			//	x2+=1;
			}
			System.out.println("My Row is finished:"+x+" rows left="+(barrier.getParties()-barrier.getNumberWaiting()));
			
			try {
				barrier.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}