import java.awt.Color;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Runner
{
	public static void main(String args[])
	{
		JFrame f=new JFrame();
		f.setVisible(false);
		f.setSize(1400,1000);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int[] arr=new int[1200];
		Pannal p=new Pannal(arr);
		f.add(p);
		f.setVisible(true);
		fill(arr,1000000);
		try
		{
			Thread.sleep(1000);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		//bubblysort(arr,p);
		 RadixSort(arr,p);
		System.out.println("DONE!");
	}
	public static void fill(int[] arr,int f)
	{
		Random r=new Random();
		for(int k=0;k<arr.length;k++)
		{
			arr[k]=r.nextInt(f);
		}
	}
	public static void bubblysort(int[] arr,Pannal p)
	{
		for(int k=0;k<arr.length;k++)
		{
			for(int q=0;q<arr.length-1;q++)
			{
				p.setNumber(k, q);
				if(q!=k&&arr[q]>arr[q+1])
				{
					switchs(arr,q,q+1);
					p.mypaint();
					try
					{
						Thread.sleep(50);
					} catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
			}
		}
	}
	public static void RadixSort(int[] list,Pannal p)
	{
		 String temp;
	      int digit, num;
	      Queue<Integer>[] digitQueues =
	         (Queue<Integer>[])(new LinkedList[10]);

	      for (int digitVal = 0; digitVal <= 9; digitVal++)
	         digitQueues[digitVal] = new LinkedList<Integer>();

	      // sort the list
	      for (int position=0; position <= 5; position++)
	      {
	         for (int scan = 0; scan < list.length; scan++)
	         {
	        //    temp = String.valueOf (list[scan]);
	            DecimalFormat form=new DecimalFormat("000000");
	            temp=form.format(list[scan]);
	            digit = Character.digit (temp.charAt(5-position), 10);
	            LinkedList l=new LinkedList();
	            ((LinkedList<Integer>) digitQueues[digit]).add(list[scan]);
	         }

	         // gather numbers back into list
	         num = 0;
	         for (int digitVal = 0; digitVal <= 9; digitVal++)
	         {
	        	 p.setNumber(num, -1);
	            while (!(digitQueues[digitVal].isEmpty()))
	            {
	            	p.setNumber(-1, num);
					list[num] = digitQueues[digitVal].poll().intValue();
					num++;
					p.mypaint();
					try
					{
						Thread.sleep(5);
					} catch (InterruptedException e)
					{
						e.printStackTrace();
					}
	            }
	         }
	      }
	}
	public static void Randomlysort(int[] arr,Pannal p)
	{
		Random r=new Random();
		for(int k=0;k<arr.length;k++)
		{
			for(int q=0;q<arr.length-1;q++)
			{
				p.setNumber(k, q);
				if(q!=k&&arr[q]>arr[q+1])
				{
					switchs(arr,q,q+1);
					p.mypaint();
					try
					{
						Thread.sleep(50);
					} catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
				if(r.nextInt()<10)
				{
					//switchs(arr,r.nextInt(arr.length),r.nextInt(arr.length));
				}
				if(r.nextInt(arr.length)==arr.length/2)
				{
					switchs(arr,r.nextInt(arr.length),r.nextInt(arr.length));
				}
			}
		}
		if(!sorted(arr))
			Randomlysort(arr,p);
	}
	public static void switchs(int[] arr,int k,int y)
	{
		int tem=arr[k];
		arr[k]=arr[y];
		arr[y]=tem;
	}
	public static boolean sorted(int[]arr)
	{
		for(int k=0;k<arr.length-1;k++)
		{
			if(arr[k+1]<arr[k])
			{
				return false;
			}
		}
		return true;
	}
}

class Pannal extends JPanel
{
	int[] arr;
	int outer,inner;
	public Pannal(int[] arr)
	{
		super();
		this.arr=arr;
	}
	public void setNumber(int o,int i)
	{
		if(o!=-1)
		outer=o;
		if(i!=-1)
		inner=i;
	}
	public void mypaint()
	{
		this.repaint();
	}
	public void paint(Graphics g)
	{
		g.setColor(Color.white);
		g.fillRect(0,0,1400,1000);
		g.setColor(Color.black);
		double width=(int)(((double)1200.)/((double)arr.length));
		double height=(int)(((double)1000000)/((double)this.getHeight()));
		for(int k=0;k<arr.length;k++)
		{
			//System.out.println(arr[k]);
			g.fillRect((int)(((double)k)*width), 100,(int)width, (int)(((double)arr[k])/height/2.));
		}
		g.setColor(Color.green);
		g.fillRect((int)(outer*width), 80, (int)width, 20);
		g.setColor(Color.blue);
		g.fillRect((int)(inner*width), 80, (int)width, 20);
	}
}