/**
 * sortedgraph.java  12/7/2009
 *
 * @author - Jane Doe
 * @author - Period n
 * @author - Id nnnnnnn
 *
 * @author - I received help from ...
 *
 */
import java.awt.*;
import java.applet.*;
import java.io.*;
import java.net.*;
import java.text.*;
import javax.swing.*;
import java.lang.*;
import java.util.*;
import java.sql.*;
import sun.audio.*;

public class sortedgraph extends graph
{
	TreeSet<Extendable> sorted=new TreeSet<Extendable>();
    public sortedgraph()
    {
    }
    public void recalculate()
	{
		for(int k=1;k<objects.size();k++)
		{
			Extendable s=objects.get(k);
			if(s instanceof Points)
				((Points)s).recalculate((Points)objects.get(k-1),objects,k,x,xs,y,ys,z,zs,transforms);
			sorted.add(s);
		}
		if(second||tempsecond)
		{
			for(int k=0;k<axis.size();k++)
		{
			axis.get(k).recalculate();
		}
		}
		origin.recalculate();
	}
	public void drawgraph(Graphics2D g)
	{
		if(!(oldxcamera==xcamera&&oldpanx==panx&&
		oldycamera==ycamera&&oldpany==pany&&
		oldzcamera==zcamera&&oldpany==pany&&oldd==distance))
		{
			oldxcamera=xcamera;oldpanx=panx;
			oldycamera=ycamera;oldpany=pany;
			oldzcamera=zcamera;oldpany=pany;oldd=distance;
		/*	for(int k=0;k<objects.size();k++)
			{
			//	System.out.println("recalculateing");
				objects.get(k).recalculate();
			}
			if(second)
			{
				for(int k=0;k<axis.size();k++)
				{
					axis.get(k).recalculate();
				}
			}*/
		}
		Iterator<Extendable> i=sorted.iterator();
		try
		{
			while(!i.hasNext())
			{
					Extendable s=i.next();
					if(s instanceof Points)
					{
						if(((Points)s).z+panz<=0)
						{
					//		s.setColor(clor);
							s.draw(g);
						}
					}

			}
		}catch(Exception e)
		{

		}
	}
}