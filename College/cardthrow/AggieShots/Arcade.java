import static java.lang.System.*;
import static java.awt.Color.*;
import static java.lang.Integer.*;
import static java.lang.Math.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent.*;
import java.awt.Event.*;
import java.awt.image.*;
import java.applet.*;
import java.io.*;
import java.net.*;
import java.text.*;
import javax.swing.*;
import javax.swing.event.*;

import java.lang.*;
import java.util.*;
import java.sql.*;
import sun.audio.*;

public class Arcade extends JPanel implements ActionListener {
	JButton onePlayer, twoPlayer, load, online, quit;
	JFrame frame;
	Runtime r;
	FileLoader fl;
	public static void main(String[] args)
	{
		new Arcade();
	}
	public Arcade()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		setSize(843,701);
		setBackground(gray.brighter());
      	setOpaque(true);
      	setVisible(true);
      	setLayout(new GridLayout(5,1));

      	fl = new FileLoader();

      	try
      	{
          	createButtons();
      	}catch(Exception e){}

		frame = new JFrame();
		frame.setSize(843,701);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      	frame.setJMenuBar(createMenuBar());
      	frame.validate();
		frame.setBackground(gray.brighter());
	//	frame.setResizable(false);
      	frame.setTitle("Arcade");
      	frame.setContentPane(this);
      	frame.setVisible(true);
      	frame.createBufferStrategy(2);
	}
	
	public void createButtons()throws Exception
	{
		Scanner s = new Scanner(new File("Games.list"));
		while(s.hasNext())
		{
			String game = s.nextLine();
			System.out.println(game);
			File f=new File(game+"/"+game+".game");
			System.out.println(f.getAbsolutePath());
			Scanner s2 = new Scanner(f);
			//if(s2==null||!s2.hasNext())
			//	s2 = new Scanner(new File(game+".game"));
			String mainClass = s2.nextLine();
			String pic = s2.nextLine();
			String name = s2.nextLine();
			System.out.println(mainClass);
			ImageIcon ii = fl.imageIcon(game+"/"+pic);
			onePlayer = new JButton(name,ii);
			onePlayer.setToolTipText(name);
			f = fl.file(game+"\\"+mainClass+".class");
			f=f.getAbsoluteFile();
			System.out.println(f);
			onePlayer.setActionCommand(f.toURI().toURL().toString());
			onePlayer.addActionListener(this);
			add(onePlayer);
		}
	}/*
	public void createButtons()throws Exception
	{
		//Scanner s = new Scanner(new File("Games.list"));
		BufferedReader s=fl.datFile("Games.list");
		String g=s.readLine();
		while(g!=null&&!(g.equals("")))
		{
			String game="";
			String game2="";
			try
			{
				String[] split=g.split(" ");
				if(split.length>1)
				{
					game=split[0];
					game2=split[1];
					if(game2.equalsIgnoreCase("null"))
						game2="";
					else
						game2+="\\";
					System.out.println("first "+game+" second "+game2);
				}
			}catch(Exception e)
			{
				e.printStackTrace();
				game=game2=g;
			}
			Scanner s2 = new Scanner(new File(game2+game+".game"));
		//	BufferedReader s2=fl.datFile(game+".game");
		//	if(s2==null||!s2.hasNext())
		//	Scanner	s2 = new Scanner(new File(game+".game"));
			String mainClass = s2.nextLine();
			String pic = s2.nextLine();
			String name = s2.nextLine();
			ImageIcon ii = fl.imageIcon(game2+pic);
			onePlayer = new JButton(name,ii);
			onePlayer.setToolTipText(name);
			File f = fl.file(game2+mainClass+".class");
			onePlayer.setActionCommand(f.toURI().toURL().toString());
			onePlayer.addActionListener(this);
			add(onePlayer);
			g=s.readLine();
		}
	}*/
	public JMenuBar createMenuBar()
    {
		JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem load = new JMenuItem("Load");
        load.setMnemonic(KeyEvent.VK_L);
        load.setContentAreaFilled(false);
        load.setOpaque(false);
        load.addActionListener(this);

        fileMenu.add(load);
        fileMenu.setContentAreaFilled(false);
        fileMenu.setBorderPainted(false);
        fileMenu.setOpaque(false);
        menuBar.add(fileMenu);
        return menuBar;
    }
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			String str = e.getActionCommand();
			int i = e.getActionCommand().lastIndexOf("/");
			URL[] url = {new URL(str.substring(0,i+1))};
			URLClassLoader ucl = new URLClassLoader(url);
			Class c = ucl.loadClass(str.substring(i+1,str.length()-6));
			Game g = (Game)c.newInstance();
			frame.setVisible(false);
			frame.setContentPane(g);
			frame.setJMenuBar(g.createMenuBar());
			frame.addKeyListener(g);
			frame.setVisible(true);
			g.init();
		}
		catch(Exception e2){e2.printStackTrace();}
	}
}
