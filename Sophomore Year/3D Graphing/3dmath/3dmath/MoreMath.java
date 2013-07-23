/**
 * MoreMath.java  7/29/2008
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

public class MoreMath
{
    public MoreMath()
    {
    }
    public static int ceil(float f)
    {
    	if(f>0)
    	{
    		return (int)f+1;
    	}else
    		return (int)f;
    }
    public static int ceil(double f)
    {
    	if(f>0)
    	{
    		return (int)f+1;
    	}else
    		return (int)f;
    }
}