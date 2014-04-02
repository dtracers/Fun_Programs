/**
 *@author	Paul Abraham and David Turner
 */
import static java.lang.System.*;
import java.util.*;
import java.io.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.text.*;
import javax.swing.*;
import sun.audio.*;
import sun.awt.image.*;

/**
 * <p>This class is used to input various types of files from both normal Java classes
 * and from within Jar files. Filenames passed <b>must</b> include file suffixes and 
 * must match <b>exactly</b> with filename of desired file.</p>
 */
public class FileLoader
{
	private Map<String,File> files;
	private Map<String,BufferedReader> datFiles;
	private Map<String,Image> images;
	private Map<String,ImageIcon> imageIcons;
	private Map<String,AudioStream> audioStreams;

	public FileLoader()
	{
		files = new HashMap<String,File>();
		datFiles = new HashMap<String,BufferedReader>();
		images = new HashMap<String,Image>();
		imageIcons = new HashMap<String,ImageIcon>();
		audioStreams = new HashMap<String,AudioStream>();
	}
	/**
 	* Returns the generic <code>File</code> with the specified filename
 	* and has <b>not</b> yet been inputed by this class.
 	* 
 	* @param	fileName A <code>String</code> that 
 	*			represents the name of the desired file.
 	*			
 	* @return	The <code>File</code> with the specified filename, if it
 	*			exists, <code>null</code> otherwise.
 	*
 	* @throws	URISyntaxException If location of file can't
 	*			be parsed as a <code>URI</code>.
 	*/
	public File file(String fileName) throws URISyntaxException
	{
		File f = null;
		try
		{
			URL loc = getClass().getResource(fileName);
			URI loc2 = loc.toURI();
			out.println(loc+"\n"+loc2);
			f = new File(loc2);
			if(f==null)
			{
				throw new Exception("ERROR");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			f = new File(fileName);
		}
		if(f!=null)
			files.put(fileName,f);
		return f;
	}
	/**
 	* Returns the generic <code>File</code> that has the specified filename
 	* and has <b>already</b> been inputed by this class.
 	* 
 	* @param	fileName A <code>String</code> that 
 	*			represents the name of the desired file.
 	*			
 	* @return	The <code>File</code> with the specified filename, if it
 	*			exists, <code>null</code> otherwise.
 	*/
	public File getFile(String fileName)
	{
		return files.get(fileName);
	}

	/**
 	* Returns a <code>BufferedReader</code> that is initialized
 	* to a data file with the specified filename and has <b>not</b>
 	* yet been inputed by this class.
 	* 
 	* @param	fileName A <code>String</code> that 
 	*			represents the name of the desired data file.
 	*			
 	* @return	A <code>BufferedReader</code> that is initialized
 	* 			to a data file with the specified filename, if it
 	*			exists, <code>null</code> otherwise.
 	*/
	public BufferedReader datFile(String fileName)
	{
		BufferedReader bf = null;
		try
		{
			bf = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(fileName)));
			if(bf==null)
			{
				throw new Exception("ERROR");
			}
		}catch(Exception e1)
		{
			try
			{
				bf = new BufferedReader(new FileReader(fileName));
				if(bf==null)
				{
					throw new Exception("ERROR");
				}
			}catch(Exception e2)
			{
				File r = null;
				try
				{
					r=new File(fileName);
					bf = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getSystemResourceAsStream(r.getAbsolutePath())));
					if(bf==null)
					{
						throw new Exception("ERROR");
					}
				}catch(Exception e3)
				{
					try
					{
						r=file(fileName);
					}catch(Exception e4)
					{

					}finally
					{
						if(r!=null)
							bf = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getSystemResourceAsStream(r.getAbsolutePath())));
					}
				}
			}
		}
		if(bf!=null)
			datFiles.put(fileName,bf);
		return bf;
	}
	/**
 	* Returns a <code>BufferedReader</code> that is initialized
 	* to a data file with the specified filename and has 
 	* <b>already</b> been inputed by this class.
 	* 
 	* @param	fileName A <code>String</code> that 
 	*			represents the name of the desired data file.
 	*			
 	* @return	A <code>BufferedReader</code> that is initialized
 	* 			to a data file with the specified filename, if it
 	*			exists, <code>null</code> otherwise.
 	*/
	public BufferedReader getDatFile(String fileName)
	{
		return datFiles.get(fileName);
	}

	/**
 	* Returns a <code>BufferedImage</code> that is initialized
 	* to an image file with the specified filename and has <b>not</b>
 	* yet been inputed by this class.
 	* 
 	* @param	fileName A <code>String</code> that 
 	*			represents the name of the desired image file.
 	*			
 	* @return	A <code>BufferedImage</code> that is initialized
 	* 			to an image file with the specified filename, if it
 	*			exists, <code>null</code> otherwise.
 	*/
	public Image image(String fileName)
	{
		ImageIcon ii = imageIcon(fileName);
		ToolkitImage ti = (ToolkitImage)ii.getImage();
		Image bi = ti.getBufferedImage();
		images.put(fileName,bi);
		return bi;
	}
	/**
 	* Returns a <code>BufferedImage</code> that is initialized
 	* to an image file with the specified filename and has 
 	* <b>already</b> been inputed by this class.
 	* 
 	* @param	fileName A <code>String</code> that 
 	*			represents the name of the desired image file.
 	*			
 	* @return	A <code>BufferedReader</code> that is initialized
 	* 			to an image file with the specified filename, if it
 	*			exists, <code>null</code> otherwise.
 	*/
	public Image getImage(String fileName)
	{
		return images.get(fileName);
	}

	/**
 	* Returns an <code>ImageIcon</code> that is initialized
 	* to an image file with the specified filename and has <b>not</b>
 	* yet been inputed by this class.
 	* 
 	* @param	fileName A <code>String</code> that 
 	*			represents the name of the desired image file.
 	*			
 	* @return	An <code>ImageIcon</code> that is initialized
 	* 			to an image file with the specified filename, if it
 	*			exists, <code>null</code> otherwise.
 	*/
	public ImageIcon imageIcon(String fileName)
	{
		ImageIcon ii = null;
		try
		{
			URL loc = getClass().getResource(fileName);
			ii = new ImageIcon(loc);
			if(ii==null)
			{
				throw new Exception("ERROR");
			}
		}catch(Exception e1)
		{
			try
			{
				ii= new ImageIcon(fileName);
				if(ii==null)
				{
					throw new Exception("ERROR");
				}
			}catch(Exception e2)
			{
				try
				{
					Scanner s=getScanner(fileName);
					if(s==null)
					{
						throw new Exception("ERROR");
					}
					ArrayList<Byte> f=new ArrayList<Byte>();
					try
					{
						while(s.hasNext())
						{
							f.add(s.nextByte());
						}
					}catch(NullPointerException en)
					{
					}
					byte[]f2=new byte[f.size()];
					for(int k=0;k<f.size();k++)
					{
						f2[k]=(byte)f.get(k);
					}
					ii = new ImageIcon(f2);
				}catch(Exception e3)
				{
				}
			}
		}
		if(ii!=null)
			imageIcons.put(fileName,ii);
		return ii;
	}
	/**
 	* Returns an <code>ImageIcon</code> that is initialized
 	* to an image file with the specified filename and has 
 	* <b>already</b> been inputed by this class.
 	* 
 	* @param	fileName A <code>String</code> that 
 	*			represents the name of the desired image file.
 	*			
 	* @return	An <code>ImageIcon</code> that is initialized
 	* 			to an image file with the specified filename, if it
 	*			exists, <code>null</code> otherwise.
 	*/
	public ImageIcon getImageIcon(String fileName)
	{
		return imageIcons.get(fileName);
	}
	private Scanner getScanner(String fileName)
	{
		Scanner bf = null;
		try
		{
			bf = new Scanner(new InputStreamReader(getClass().getResourceAsStream(fileName)));
			if(bf==null)
			{
				throw new Exception("ERROR");
			}
		}catch(Exception e1)
		{
			try
			{
				bf = new Scanner(new FileReader(fileName));
				if(bf==null)
				{
					throw new Exception("ERROR");
				}
			}catch(Exception e2)
			{
				File r = null;
				try
				{
					r=new File(fileName);
					bf = new Scanner(new InputStreamReader(getClass().getClassLoader().getSystemResourceAsStream(r.getAbsolutePath())));
					if(bf==null)
					{
						throw new Exception("ERROR");
					}
				}catch(Exception e3)
				{
					try
					{
						r=file(fileName);
					}catch(Exception e4)
					{

					}finally
					{
						if(r!=null)
							bf = new Scanner(new InputStreamReader(getClass().getClassLoader().getSystemResourceAsStream(r.getAbsolutePath())));
					}
				}
			}
		}
		return bf;
	}
	
	/**
 	* Returns an <code>AudioStream</code> that is initialized
 	* to a music file with the specified filename and has <b>not</b>
 	* yet been inputed by this class. Music files <b>must</b> .au 
 	* format. When using <code>AudioStream</code>, warnings will be 
 	* reported on compile. Ignore these as they do not prevent program
 	* execution.
 	* 
 	* @param	fileName A <code>String</code> that 
 	*			represents the name of the desired music file.
 	*			
 	* @return	An <code>AudioStream</code> that is initialized
 	* 			to a music file with the specified filename, if it
 	*			exists, <code>null</code> otherwise.
 	*
 	* @throws	IOException If file could not be found or filename was sent 
 	*			incorrectly.
 	*/
    public AudioStream music(String fileName) throws IOException
	{
		AudioStream as = new AudioStream(getClass().getResourceAsStream(fileName));
		audioStreams.put(fileName,as);
		return as;
	}
	/**
 	* Returns an <code>AudioStream</code> that is initialized
 	* to a music file with the specified filename and has 
 	* <b>already</b> been inputed by this class. When using <code>AudioStream</code>, 
 	* warnings will be reported on compile. Ignore these as they do not prevent 
 	* program execution.
 	* 
 	* @param	fileName A <code>String</code> that 
 	*			represents the name of the desired music file.
 	*			
 	* @return	An <code>AudioStream</code> that is initialized
 	* 			to a music file with the specified filename, if it
 	*			exists, <code>null</code> otherwise.
 	*/
	public AudioStream getMusic(String fileName)
	{
		return audioStreams.get(fileName);
	}
}