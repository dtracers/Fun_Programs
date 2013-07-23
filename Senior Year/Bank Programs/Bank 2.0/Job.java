/**
 * Tansaction.java  1/10/2010
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
import sun.audio.*;

public class Job extends Tansaction
{
	public Job(Tansaction t)
	{
		super(t.getDescript(),t.getChange(),t.getTimeMillis());
	}
    public Job()
    {
		super("sanatation engineer",1.50,1);
    }
    public Job(String d,double c,long s)
    {
    	super(d,c,s);
    }
    public boolean equals(Object j)
    {
    	if((""+j).equalsIgnoreCase(getDescript()))
    		return true;
    		return false;
    }
}