/**
 * help.java  9/20/2009
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

public class help
{
    public static void paint(Graphics g,int x,int y)
    {
    	g.setColor(Color.red);
    	g.drawString("to Move:",x,y);
    	g.drawString("x: rotate around x-axis",x,y+10);
    	g.drawString("y: rotate around y-axis",x,y+20);
    	g.drawString("z: rotate around z-axis",x,y+30);
    	g.drawString("left: move screen left",x,y+40);
    	g.drawString("ctrl+left: move graph left",x,y+50);
    	g.drawString("right: move screen right",x,y+60);
    	g.drawString("ctrl+right: move graph right",x,y+70);
    	g.drawString("plus or scroll up: zoom in",x,y+80);
    	g.drawString("minus or scroll down: zoom out",x,y+90);
    	g.drawString("b: go back to orignal graph",x,y+100);
    	g.drawString("enter of tab: change selected graph",x,y+110);
    	g.drawString("#:change selected graph to that number",x,y+120);
    	g.drawString("0:unselect all graphs",x,y+130);
    	int w=30;
    	g.drawString("mouse scroll wheel=MSW",x,y+120+w);
    	g.drawString("MSW: moves the graph with the mouse",x,y+130+w);
    	g.drawString("MSW: along x and y axis",x,y+140);
    	g.drawString("alt+MSW: zoomIn, and rotate around z",x,y+150+w);
    	g.drawString("left click(lc): if on a point that point",x,y+160+w);
    	g.drawString("lc: is selected",x,y+170+w);
    	g.drawString("lc+move: multiple points are selected",x,y+180+w);
    	g.drawString("ctrl+lc+move: multiple points are selected and saved",x,y+190+w);
    	g.drawString("h: hide/show instructions",x,y+200+w);
    	
    }
}