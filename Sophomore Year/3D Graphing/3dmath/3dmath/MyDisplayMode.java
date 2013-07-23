/**
 * MyDisplaymode.java  8/14/2008
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

public class MyDisplayMode
{
	DisplayMode f;
	String g;
    public MyDisplayMode()
    {

    }
    public MyDisplayMode(DisplayMode mode,String g)
    {
    	f=mode;this.g=g;
    }
    public String getname()
    {
    	return g;
    }
    public DisplayMode getmode()
    {
    	return f;
    }
    public void setmode(DisplayMode g)
    {
    	f=g;
    }
    public void setname(String f)
    {
    	g=f;
    }
}