package resourcces;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.ImageIcon;

import sun.audio.AudioStream;
public class FileLoader
{
	public BufferedReader datFile(String fileName)
	{
		try
		{
			BufferedReader bf = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(fileName)));
			return bf;
		}catch(Exception e)
		{
			File r=new File(fileName);
			BufferedReader bf = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getSystemResourceAsStream(r.getAbsolutePath())));
		//	datFiles.add(bf);
			return bf;
		}
	}
	public File file(String fileName) throws URISyntaxException
	{
		URL loc = getClass().getResource(fileName);
		URI loc2 = loc.toURI();
		File f = new File(loc2);
		//files.add(f);
		return f;
	}
	public Image image(String fileName)
	{
		URL loc = getClass().getResource(fileName);
		ImageIcon ii = new ImageIcon(loc);
		return ii.getImage();
	}
	public AudioStream music(String fileName) throws IOException
	{
		AudioStream as = new AudioStream(getClass().getResourceAsStream(fileName));
		return as;
	}
}
