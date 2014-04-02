import static java.awt.Color.gray;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Arcade extends JPanel implements ActionListener {
	public static JFrame MAINFRAME;
	JButton onePlayer;
	JFrame frame;
	Runtime r;
	FileLoader fl;
	ArrayList<String> games;
	static int type = -1;
	String extenders;

	public static void reseting(JFrame frame2)
	{
		new Arcade(frame2);
	}
	public static void main(String[] args)
	{
		try
		{
			PrintStream log=new PrintStream(new File("log.txt"));
			PrintStream err = new PrintStream(new File("err.txt"));
			System.setOut(log);
			System.setErr(err);
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		Arcade r = new Arcade();
		r.reset(r.MAINFRAME);
	}
	public Arcade()
	{
		if (type == -1)
		{
			String option = JOptionPane.showInputDialog("Enter 1 for windows 2 for mac");
			if (option != null && !option.equals("")) type = Integer.parseInt(option);
			switch (type)
			{
				case 1:
					extenders = "\\";// windows
				break;
				case 2:
					extenders = "/"; // mac
				break;
			}
		}
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
      	games = new ArrayList<String>();
      	try
      	{
          	createButtons();
      	}catch(Exception e){e.printStackTrace();}

		JFrame frame2 = new GameWindow();
		// reset(frame);
		MAINFRAME = frame2;
		frame = frame2;
	}

	public void reset(JFrame m)
	{
		frame = m;
		frame.setSize(843, 701);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.validate();
		frame.setBackground(gray.brighter());
		frame.setResizable(false);
		frame.setTitle("Arcade");
		frame.setContentPane(this);
		frame.setJMenuBar(createMenuBar());
		frame.setVisible(true);
		frame.createBufferStrategy(2);
	}
	public Arcade(JFrame frame2)
	{
		this();
		if (frame2 == null)
		{
			if (MAINFRAME == null)
			{
				frame2 = new GameWindow();
				MAINFRAME = frame2;
			} else
			{
				frame2 = MAINFRAME;
			}

		}
		reset(frame2);
	}
	public void createButtons()throws Exception
	{
		BufferedReader s = fl.datFile("Games.list");
		String game="";
		while((game=s.readLine())!=null&&!game.equals(""))
		{
			System.out.println("the game is " + game);
			JButton t = makeButton(game);
			if(t!=null)
			add(t);
		}
	}
	public void createButtons(File g)throws Exception
	{
		BufferedReader s = fl.datFile(g.getAbsolutePath());
		String game="";
		while((game=s.readLine())!=null&&!game.equals(""))
		{
			JButton t = makeButton(game);
			if(t!=null)
			add(t);
		}
	}
	private JButton makeButton(String game)throws Exception
	{
		if(!games.contains(game))
		{
			games.add(game);
			BufferedReader s2;
			if(game.endsWith(".game"))
			{
				s2=fl.datFile(game);
			}
			else
			{
				s2 = fl.datFile(game + extenders + game + ".game");
			}
			String mainClass = s2.readLine();
			String pic = s2.readLine();
			String name = s2.readLine();
			ImageIcon ii = fl.imageIcon(game + extenders + pic);
			onePlayer = new MyJButton(name, ii);
			onePlayer.setToolTipText(name);
			File f = fl.file(game + extenders + mainClass + ".class");
			onePlayer.setActionCommand(f.toURI().toURL().toString());
			onePlayer.addActionListener(this);
			String extra = s2.readLine();
			while (extra != null && !extra.equals(""))
			{
				((MyJButton) onePlayer).addInfo(extra);
				extra = s2.readLine();
			}
			return onePlayer;
		}
		return null;
	}
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

        load = new JMenuItem("Import Game");
        load.setMnemonic(KeyEvent.VK_I);
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
	public void load()
	{
		JFileChooser localJFileChooser = new JFileChooser();
		localJFileChooser.setDialogType(1);
		localJFileChooser.setMultiSelectionEnabled(false);
		localJFileChooser.setFileFilter(new FileNameExtensionFilter("*.list","list"));
		localJFileChooser.showOpenDialog(this);
		if(localJFileChooser.getSelectedFile()==null)
		{
		}
		else
		{
			 File f = localJFileChooser.getSelectedFile().getAbsoluteFile();
	    	frame.setVisible(false);
	    	try
	    	{
		    	createButtons(f);
	    	}catch(Exception e){e.printStackTrace();}
	    	frame.setVisible(true);
		}
	}
	public void importGame()
	{
		JFileChooser localJFileChooser = new JFileChooser();
		localJFileChooser.setDialogType(1);
		localJFileChooser.setMultiSelectionEnabled(false);
		localJFileChooser.setFileFilter(new FileNameExtensionFilter("*.game","game"));
		localJFileChooser.showOpenDialog(this);
		if(localJFileChooser.getSelectedFile()==null)
		{
		}
		else
		{
			 File f = localJFileChooser.getSelectedFile().getAbsoluteFile();
	    	frame.setVisible(false);
	    	try
	    	{
		    	createButtons(f);
	    	}catch(Exception e){e.printStackTrace();}
	    	frame.setVisible(true);
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		String str = e.getActionCommand();
		if(str.equals("Load"))
		{
			load();
		}else if(str.equals("Import Game"))
		{
			importGame();
		}
		else
		{
			Object source=e.getSource();
			startGame(e.getActionCommand(), source instanceof MyJButton ? ((MyJButton) source) : null);
		}
	}

	public void startGame(String str, MyJButton jello)
	{
		Game g = loadGame(str);
		frame.setVisible(false);
		frame.setContentPane(g);
		frame.setJMenuBar(g.createMenuBar());
		// frame.getJMenuBar().setOpaque(true);
		// System.out.println("ignores repaint??" +
		// frame.getJMenuBar().getIgnoreRepaint());
		frame.addKeyListener(g);
		frame.setDefaultCloseOperation(frame.DO_NOTHING_ON_CLOSE);
		Info(jello, g);
		frame.setVisible(true);
		// frame.setJMenuBar(g.createMenuBar());
		g.setBack(this);
		g.init();
		System.out.println("SUCCESS!!!");
	}

	public void Info(MyJButton jello, Game g)
	{
		// the later in the index the earlier in the switch statement
		switch (jello.InfoSize())
		{
			// this is index 0
			case 1:
			{
				Object info = jello.getInfo(0);
				if (info instanceof String)
				{
					String str = "" + info;
					String[] s = str.split(":");
					frame.setSize(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
				}
			}
			default:
			{
				// this should be the last one and should follow 1
				// 1 should follow 2 and ect...
			}
		}
	}
	public Game loadGame(String str)
	{
		Game g = null;
		try
		{
			int i = str.lastIndexOf("/");
			URL[] url = {new URL(str.substring(0,i+1))};
			System.out.println("loading the class from: " + url[0].getPath());
			URLClassLoader ucl = new URLClassLoader(url);
			// findAllClasses(url[0].getPath());
			String className = str.substring(i + 1, str.length() - 6);
			System.out.println("loading class " + className);
			Class c = ucl.loadClass(className);
			g = (Game) c.newInstance();
			g.setFileExtension(url[0].getPath());
		}
		catch(Exception e2){e2.printStackTrace();}
		return g;
	}

	public Class loadClass(String str)
	{
		Class c = null;
		try
		{
			int i = str.lastIndexOf("/");
			URL[] url = { new URL(str.substring(0, i + 1)) };
			System.out.println("loading the class from: " + url[0].getPath());
			URLClassLoader ucl = new URLClassLoader(url);
			String className = str.substring(i + 1, str.length() - 6);
			System.out.println("loading class " + className);
			c = ucl.loadClass(className);
		} catch (Exception e2)
		{
			e2.printStackTrace();
		}
		return c;
	}
	public ArrayList<String> findAllClasses(String path)
	{
		path = path.replaceAll("%20", " ");
		File f = new File(path);
		System.out.println("is idrectory? " + f.isDirectory());
		ArrayList<File> searching = new ArrayList<File>();
		ArrayList<File> classes = new ArrayList<File>();
		File[] files = f.listFiles();
		for (File fdir : files)
		{
			System.out.println("the inside files are " + fdir);
			if (fdir.isDirectory())
			{
				searching.add(fdir);
			}
			if (fdir.getName().endsWith(".class"))
			{
				classes.add(fdir);
			}
		}
		while (searching.size() > 0)
		{
			File current = searching.remove(0);
			System.out.println("current" + current);
			files = current.listFiles();
			for (File fdir : files)
			{
				if (fdir.isDirectory())
				{
					searching.add(fdir);
				}
				if (fdir.getName().endsWith(".class"))
				{
					classes.add(fdir);
				}
			}
			System.out.println(classes);
		}
		return null;
	}
}