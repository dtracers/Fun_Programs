/**
 * myScanConverter.java  8/26/2008
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

public class myScanConverter
{
    public myScanConverter()
    {
    }
    public myScanConverter(ViewWindow wind)
    {
    	window=wind;
    }
    int top;
    int bottom;
    protected Scan[] scans;
    static ViewWindow window;
    public static class Scan {
        public int left;
        public int right;

        /**
            Sets the left and right boundary for this scan if
            the x value is outside the current boundary.
        */
        public void setBoundary(int x) {
            if (x < left) {
                left = x;
            }
            if (x-1 > right) {
                right = x-1;
            }
        }


        /**
            Clears this scan line.
        */
        public void clear() {
            left = Integer.MAX_VALUE;
            right = Integer.MIN_VALUE;
        }
                /**
            Sets this scan.
        */
        public void setTo(int left, int right) {
            this.left = left;
            this.right = right;
        }


        /**
            Checks if this scan is equal to the specified values.
        */
        public boolean equals(int left, int right) {
            return (this.left == left && this.right == right);
        }
    }
    public boolean convert(Polygon3d polygon)
    {
    	int minX = window.getLeftOffset();
        int maxX = window.getLeftOffset() + window.getWidth() - 1;
        int minY = window.getTopOffset();
        int maxY = window.getTopOffset() + window.getHeight() - 1;
    	for(int k=0;k<=polygon.getNumVerts();k++)
    	{
    		int temp=k%polygon.getNumVerts();
    		int temp2=k+1%polygon.getNumVerts();
    		Points p1=polygon.getVertex(temp);
    		Points p2=polygon.getVertex(temp2);
    		if (p1.y2d > p2.y2d)
    		{
                Points temp3 = p1;
                p1 = p2;
                p2 = temp3;
            }
            double dy = p2.y2d - p1.y2d;
            // ignore horizontal lines
            if (dy == 0) {
                continue;
            }
            int startY = Math.max((int)Math.ceil(p1.y2d), minY);
            int endY = Math.min((int)Math.ceil(p2.y2d)-1, maxY);
            top =(int) Math.min(top, startY);
            bottom =(int) Math.max(bottom, endY);
            double dx = p2.x2d - p1.x2d;
            if(dx==0)
            {
            		int x = (int)Math.ceil(p1.x2d);
	                // ensure x within window bounds
	                x =(int) Math.min(maxX+1, Math.max(x, minX));
	                for (int y=startY; y<=endY; y++)
                    scans[y].setBoundary(x);
            }else
            {
            	double gradient = dx / dy;
            	for (int y=startY; y<=endY; y++)
            	{
                    int x = (int)Math.ceil(p1.x2d + (y - p1.y2d) * gradient);
                    x = Math.min(maxX+1, Math.max(x, minX));
                    scans[y].setBoundary(x);
            	}
	    	}
	    }
	    return true;
    }
}
