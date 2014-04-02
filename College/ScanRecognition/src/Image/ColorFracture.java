package Image;

import java.awt.AWTEvent;
import java.awt.Button;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import javax.swing.JFrame;

import math.algorthms.Algorithm;
import math.algorthms.colorFracture.ColorCombiner;
import math.algorthms.colorFracture.ColorFractureAlgorithm;
import math.algorthms.colorFracture.ComplexFracture;
import math.algorthms.colorFracture.ComplexFractureRim;
import math.algorthms.colorFracture.SimpleFracture;
import math.algorthms.colorFracture.SimpleFractureImproved;
import math.algorthms.colorFracture.SimpleFractureSplit;
//TODO create a color swatch so you can see the image for just one color
//TODO add an ability to choose a color to add to the array of color searches and see if it will work better
public class ColorFracture
{
	CyclicBarrier barrier = new CyclicBarrier(2);
	Thread threading;
	ArrayList<JFrame> frames;
	BufferedImage image;
	BufferedImage image2;
	BufferedImage copy;
	Map<Color,BufferedImage> colorsets;//for funsies
	ArrayList<Color> differentColors;
	Map<Color,ArrayList<Point>> colorMap;
	ArrayList<Algorithm> algorithms;
	int intense=5;
	int alg=0;
	String fileName;
	Point[][] pointBuffer;
	static boolean grapher;
	static boolean grapher2=true;
	boolean mix=false;
	public ColorFracture(BufferedImage x,String f) {
		fileName=f;
		image=x;
	//	image2=createCleanCopy(image);
		if(grapher)
		Grapher.createInstance();
		if(grapher2)
		Grapher2.createInstance();
		// TODO Auto-generated constructor stub
	}
	/**
	 * this is for all common algorithms 
	 * return the compare value
	 * @param intensity
	 */
	public void dirtyPicture()
	{
		if(image2==null)
		{
			image2=createCleanCopy(image);
		}
		synchronized(image2)
		{
			resetImage(image2);
			for(Color c:differentColors)
			{
			//	System.out.println("Color! "+c);
			//	MakeImage(c);
				MakeImage(c,image2);
			}
		}
	}
	public void postFracture()
	{	
		if(image2==null)
		{
			
			image2=createCleanCopy(image);
		}
		synchronized(image2)
		{
			resetImage(image2);
		}
		for(Color c:differentColors)
		{
		//	System.out.println("Color! "+c);
		//	MakeImage(c);
			synchronized(image2)
			{
				MakeImage(c,image2);
			}
		}
	}
	public void reset()
	{
		algorithms=new ArrayList<Algorithm>();
	}
	public void createAlgorithms(double intensity,int type)
	{
		mix=false;
		if(algorithms==null)
		{
			algorithms=new ArrayList<Algorithm>();
		}
		Algorithm algy=null;
		switch(type)
		{
			case 1:
			{
				algy=new SimpleFracture(image,intensity);
			}break;
			case 2:
			{
				algy=new SimpleFractureImproved(image,intensity);
			}break;
			case 3:
			{
				algy=new SimpleFractureSplit(image,intensity);
			}break;
			case 4:
			{
				algy=new ComplexFracture(image,intensity);
			}break;
			case 5:
			{
				algy=new ComplexFractureRim(image,intensity);
				
			}break;
			case 6:
			{
				algy=new ColorCombiner(image,intensity);
				
			}break;
			case 7:
			{
				createAlgorithms(intensity,4);
				createAlgorithms(intensity,6);
				createAlgorithms(intensity,1);
				mix=true;
			}break;
			case 8:
			{
				mix=true;
			}break;
		}
		if(algy!=null)
			algorithms.add(algy);
	}
	public void createThread()
	{
		threading=new Thread(new ThreadedDecoding(),"Decode");
		threading.setDaemon(true);
	}
	/**
	 * This will combine all possible algorithms
	 * @param intensity
	 * @param type
	 */
	public void FractureColors()
	{
		if(threading==null||(threading!=null&&!threading.isAlive()))
		{
			createThread();
			try
			{
				threading.start();
			}catch(ThreadDeath e)
			{
				e.printStackTrace();
			//	createAllFrames();
				throw e;
			}
		}
	}
	/**
	 * Alg3
	 * this will do the default matching but then it will get all the left over points
	 * and try and find new colors by the similar regions
	 * it will create a new set of color models using a couple of average colors of the left over image
	 * it will repeat this by starting with a low intensity then increasing it till the averages are accruate then it will seperate them
	 * @param intensity
	 * @param otherPoints 
	 */
	public void MakeImage(Color c)
	{
		BufferedImage setting=colorsets.get(c);
		if(setting==null)
		{
			setting=createCleanCopy(image);
			colorsets.put(c,setting);
		}
		MakeImage(c,setting);
	}
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
	public void MakeImage(Color c,BufferedImage setting)
	{
		if(setting==null)
		{
			setting=createCleanCopy(image);
		}
		ArrayList<Point> points=colorMap.get(c);
		int rbg= c.getRGB();
		for(Point p:points)
		{
			if(p.getX()>=0&&p.getY()>=0)
			setting.setRGB((int)p.getX(),(int)p.getY(),rbg);
		}
	}
	public void createAllFrames()
	{
		if(image!=null)
		{
			createFrame(image,"White Default Image",Color.white);
			createFrame(image,"Black Default Image",Color.black);
		}
		try {
			System.out.println("Starting to wait!");
			barrier.await();
			System.out.println("done waiting!");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(image2!=null)
		{
			createFrame(image2,"White Copied Image "+intense+" Alg"+alg,Color.white);
			createFrame(image2,"Black Copied Image "+intense+" Alg"+alg,Color.black);
		}
		if(copy!=null)
		{
			createFrame(copy,"White Extra Copied Image "+intense+" Alg"+alg,Color.white);
			createFrame(copy,"Black Extra Copied Image "+intense+" Alg"+alg,Color.black);
		}
		if(colorsets!=null)
			for(Color c:differentColors)
			{
				BufferedImage d=colorsets.get(c);
				if(d!=null)
					createFrame(colorsets.get(d),"This color filter is "+c.toString(),
							new Color(255-c.getRed(),255-c.getGreen(),255-c.getBlue()));
			}
		System.out.println("Is it Alive? "+threading.isAlive());
	}
	private void createFrame(BufferedImage g,String st,Color c)
	{
		if(frames==null)
		{
			frames=new ArrayList<JFrame>();
		}
		for(int k=0;k<frames.size();k++)
		{
			if(frames.get(k).getTitle().equals(st))
			{
				return;
			}
		}
		JFrame frame=new ImageDrawer(g,st);
		frame.setVisible(false);
		frame.setBackground(c);
		frame.setSize(g.getWidth()+30, g.getHeight()+30);
		frame.prepareImage(g, g.getWidth(), g.getHeight(), null);
		frame.setVisible(true);
		frames.add(frame);
	}
	public void killAllFrames()
	{
		if(frames!=null)
			for(int k=0;k<frames.size();k++)
			{
				JFrame f=frames.get(k);
				frames.remove(k);
				k-=1;
			//	f.setVisible(false);
				System.out.println("disposed window "+k);
			}
	}
	public void STOP()
	{
		if(threading.isAlive())
		{
			try
			{
				threading.stop();
			}
			catch(ThreadDeath e)
			{
				e.printStackTrace();
			}
			while(barrier.getNumberWaiting()<barrier.getParties()-1)
			{
				Thread d=new Thread(){
					public void run()
					{
						try {
							barrier.await();
							System.out.println("DONE!");
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
			}
			try {
				barrier.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("crisis averted!");
		}
	}

	class ThreadedDecoding implements Runnable
	{
		public void run()
		{
			Algorithm old=null;
			Algorithm save=new SimpleFracture(image,0);
			for(Algorithm algy:algorithms)
			{
				algy.init();
				if(grapher)
					Grapher.reset();
					if(grapher2)
					Grapher2.reset();
				if(mix&&old!=null)
				{
					((ColorFractureAlgorithm)algy).setImage(image2);
					System.out.println("Mixing with image!");
				}
				if(old!=null)
				{
					algy.copyData(old);
				}
				algy.compute();
				algy.addData(save);
				if(mix)
				{
					System.out.println("Mixing!");
					colorMap=((SimpleFracture)save).getColorMap();
	//				System.out.println("colors "+colorMap);
					differentColors=new ArrayList<Color>();
					differentColors.addAll(colorMap.keySet());
					dirtyPicture();
				}
				old=algy;
				
			}
			colorMap=((SimpleFracture)save).getColorMap();
	//		System.out.println("colors "+colorMap);
			differentColors=new ArrayList<Color>();
			differentColors.addAll(colorMap.keySet());
			postFracture();
		//	throw new MyAction("Done");
		//	ActionEvent arg0=new ActionEvent(this, 0,"Done!");
		//	button.processEvent(arg0);
		//   tf1.cutButton.processEvent(arg0); // Here the event is fired
			try {
				barrier.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("DECODING IS DONE!");
		}
	}
}
