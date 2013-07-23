/**
 * Text3d.java  11/18/2008
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

public class Text3d extends Polys
{	
	ArrayList p;
	String which;
    public Text3d()
    {
    	setColor(new Color(255/2,255,255/2));
    }
    public Text3d(String which)
    {
    	setColor(new Color(255/2,255,255/2));
    }
    public void setColor(Color t)
    {
    	super.setColor(t);
    	for(int k=0;k<p.size();k++)
    	{
    		((Text3d) p.get(k)).setColor(t);
    	}
    }
}