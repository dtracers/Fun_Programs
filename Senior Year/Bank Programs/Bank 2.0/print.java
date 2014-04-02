/**
 * print.java  3/27/2008
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
class unstatic
{
	public unstatic()
	{

	}
	public BufferedReader getreader(String fileName)
	{
		try
		{
			System.out.println("try 1");
//			paintgame.out.println(fileName+" the filename");
//			paintgame.out.println(""+getClass().getResourceAsStream(fileName)+" the address");
			BufferedReader bf = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(fileName)));
			if(bf==null)
			{
				int t=1/0;
			}
			System.out.println("made it 1");
			return bf;
		}catch(Exception e)
		{
			try
			{
				System.out.println("try 2");
				System.out.println(e);
				File r=new File(fileName);
				System.out.println(r.getAbsolutePath());
//				paintgame.out.println(""+getClass().getClassLoader().getSystemResourceAsStream(r.getAbsolutePath())+" the address");
				BufferedReader bf = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getSystemResourceAsStream(r.getAbsolutePath())));
				if(bf==null)
				{
					int t=1/0;
				}
				return bf;
				}catch(Exception e2)
				{
					try
					{
					System.out.println("try 3");
					BufferedReader bf = new BufferedReader(new FileReader(fileName));
					return bf;
					}catch(Exception e3)
					{
						return null;
					}
				}
		}
	}
	public BufferedReader getreader(File f)
	{
		return getreader(f.getName());
	}
}
public class print
{
//	static String directory="";
//	ende code=new ende();
	unstatic load=new unstatic();
	BufferedReader r;
	FileOutputStream out;
	PrintStream p;
	FileReader t;
	File f;
	String uop="";
	boolean g=false;
    public static void main(String[] args)
    {
    }
    public print()
    {
    }
    public File file(String fileName)
	{
		try
		{
			URL loc = getClass().getResource(fileName);
			URI loc2 = loc.toURI();
			File f = new File(loc2);
			return f;
		}catch(URISyntaxException e)
		{
			return null;
		}
	}
	public String toString()
	{
		return f+" the file\n"+f.getAbsolutePath()+" the file path\n"+isempty()+" this is fals if empty";
	}
    public boolean canwrite()
    {
    	return f.canWrite();
    }
    public void delete()
    {
    	boolean r=canwrite();
    	rite(true,"");
    	setfile(f);
    	System.out.println("file gone="+true);//+f.delete());
    	rite(r,"");
    }
    private void rite(boolean b,String h)
    {
    	f.setWritable(b);
    }
    public boolean isempty()
    {
    	try
    	{
    	String h=r.readLine();
    	//System.out.println(h);
    	return h!=null||h.length()>3;
    	}catch(Exception e)
    	{
    		return false;
    	}
    }
    public boolean ex()
    {
    	if(g);
    	return f.exists();
    }
    public String readline()
    {
    	try
    	{
    	//if(g)
    	String y=r.readLine();
    //	y=code.decode(y);
    	if(y==null)
    		return null;
    		else
    			return y;
    		//else
    		//	return "no file there ha,ha what a loser dork face well you should die";
    	}catch(Exception e)
    	{
    		return null;//"-1 null NaN ? infinity sqr(-1) ?";
    	}
    }
    /*
    public String readlinecode()
    {
    	try
    	{
    	//if(g)
    	String y=r.readLine();
    	y=code.decode(y);
    	if(y==null)
    		return null;
    		else
    			return y;
    		//else
    		//	return "no file there ha,ha what a loser dork face well you should die";
    	}catch(Exception e)
    	{
    		return null;//"-1 null NaN ? infinity sqr(-1) ?";
    	}
    }
    */
    public String getname()
    {
    	return uop;
    }
    public void close()
    {
    	try
    	{
    	if(g)
    	r.close();
    	}catch(IOException e)
    	{
    	}
    }
    public void rite(boolean b)
    {
    	System.out.println("canwrite="+f.setWritable(b));
    }
    public void red(boolean b)
    {
    	System.out.println("canread="+f.setReadable(b));
    }
    public void setfilewrite(String s)
    {
    	try
    	{
    		f=new File(s);
    		System.out.println(f.getAbsolutePath());
    		System.out.println(f.exists()+" does this exists try1");
    		p=new PrintStream(f);
    	//	r=load.getreader(s);
    		g=true;
    	}
    	catch(Exception e)
    	{
    		System.out.println(e+" error1 while setting write");
    		try
	    	{
	    		f=new File(s);
	    		System.out.println(f.getAbsolutePath());
	    		System.out.println(f.exists()+" does this exists try2");
	    		//out=new FileOutputStream(f);
	    		p=new PrintStream(f);
	    	//	r=load.getreader(s);
	    		g=true;
	    	}
	    	catch(Exception e2)
	    	{
	    		System.out.println(e2+" error2 while setting write");
	    	}
    	}
    }
    public void setfilewrite(File s)
    {
    	try
    	{
    		System.out.println(s.getName()+" the file name while setting write");
    		setfilewrite(s.getName());
    	}
    	catch(Exception e)
    	{
    		System.out.println(e+" error1 while setting write with file");
    	}
    }
    public void setfile(String s)
    {
    	try
    	{
    		//out=new FileOutputStream(f);
    		//p=new PrintStream(out);
    		//t=new FileReader(f);
			f=new File(s);
    		r=load.getreader(s);
    		g=true;
    	}
    	catch(Exception e)
    	{
    		System.out.println(e+" error1 while setting read");
    		try
    		{
	    		r=load.getreader(s);
	    		g=true;
	    	}
	    	catch(Exception e2)
    		{
    			System.out.println(e2+" error2 while setting read");
    		}
    	}
    }
    public void setfile(File s)
    {
    	try
    	{
    		System.out.println(s.getAbsolutePath());
    		setfile(s.getName());
    	}
    	catch(Exception e)
    	{
    		System.out.println(e);
    		try
    		{
	    		r=load.getreader(s);
	    		g=true;
    		}
    		catch(Exception e2)
    		{
    			System.out.println(e2);
    		}
    	}
    }
    /*
    public void printcode(String s)
    {
    	s=code.encode(s);
    	if(g)
    		p.print(s);
    }
    public void printlncode(String s)
    {
    	s=code.encode(s);
    	if(g)
    		p.println(s);
    }
    */
    public void print(String s)
    {
    //	s=code.encode(s);
    	if(g)
    		p.print(s);
    }
    public void println(String s)
    {
    //	s=code.encode(s);
    	if(g)
    		p.println(s);
    }
    public void createFile()
    {
    	f.mkdirs();
    }
    public void createFile(String g)
    {
    	new File(g).mkdirs();
    }
}