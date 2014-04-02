package main.graphic;

import static java.awt.Color.gray;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import main.Runner;
import main.display.AlternateDisplayer;
import main.display.AlternateDisplayer2;
import main.display.Displayer;
import main.tables.BoxRenderer;
import Items.Grid;
import Variables.StaticV;
//TODO make a 3rd menu where you can turn on turn off notes and can do each check independantly and stuff like that
public class GraphicsRunner extends JPanel implements ActionListener{

	/**
	 * @param args
	 */
	static Grid g;
	JFrame frame;
	static Displayer displayer;
	int view=0;
	private JMenuItem Noteson;
	private JMenuItem AutomaticChecking;
	public static void main(String[] args) throws FileNotFoundException
	{
		try
		{
		GraphicsRunner th=new GraphicsRunner();
		g=new Grid();
		g.createGrid();
		//BufferedReader r=new BufferedReader(new FileReader(new File("Runner6.txt")));
		//g.createGridFromAStream(r);
		displayer=new Displayer(g,th);
		System.out.println("I made it right");
		th.addSelf();
		Runner.checkOnce(g);
		displayer.addBoxesToGrid();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public GraphicsRunner()
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
      	frame = new JFrame();
		frame.setSize(843,701);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setJMenuBar(createMenuBar());
      	frame.validate();
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
        
        JMenuItem save = new JMenuItem("Save");
        save.setMnemonic(KeyEvent.VK_S);
        save.setContentAreaFilled(false);
        save.setOpaque(false);
        save.addActionListener(this);
        
        JMenuItem create = new JMenuItem("Create New Puzzle");
        create.setMnemonic(KeyEvent.VK_C);
        create.setContentAreaFilled(false);
        create.setOpaque(false);
        create.addActionListener(this);

        
        fileMenu.add(create);
        fileMenu.add(save); 
        fileMenu.add(load);
        fileMenu.setContentAreaFilled(false);
        fileMenu.setBorderPainted(false);
        fileMenu.setOpaque(false);
        
        
        
        JMenu OtherMenu = new JMenu("Game Play");
        JMenuItem check = new JMenuItem("Check All Possible");
        check.setMnemonic(KeyEvent.VK_P);
        check.setContentAreaFilled(false);
        check.setOpaque(false);
        check.addActionListener(this);
        
        JMenuItem otherview = new JMenuItem("Switch View");
        otherview.setMnemonic(KeyEvent.VK_V);
        otherview.setContentAreaFilled(false);
        otherview.setOpaque(false);
        otherview.addActionListener(this);
        
        Noteson = new JMenuItem("Turn Notes Off");
        Noteson.setMnemonic(KeyEvent.VK_T);
        Noteson.setContentAreaFilled(false);
        Noteson.setOpaque(false);
        Noteson.addActionListener(this);
        
        AutomaticChecking = new JMenuItem("Turn Automatic Checking Off");
        AutomaticChecking.setMnemonic(KeyEvent.VK_A);
        AutomaticChecking.setContentAreaFilled(false);
        AutomaticChecking.setOpaque(false);
        AutomaticChecking.addActionListener(this);
        
        
        OtherMenu.add(Noteson);
        OtherMenu.add(AutomaticChecking);
        OtherMenu.add(otherview);
        OtherMenu.add(check);
        OtherMenu.setContentAreaFilled(false);
        OtherMenu.setBorderPainted(false);
        OtherMenu.setOpaque(false);
        
        
        
        
        JMenu Debugging=new JMenu("Debug");
        JMenuItem reset=new JMenuItem("Reset");
        reset.setMnemonic(KeyEvent.VK_R);
        reset.setContentAreaFilled(false);
        reset.setOpaque(false);
        reset.addActionListener(this);
        
        JMenuItem chkNotes=new JMenuItem("Check Notes");
        chkNotes.setMnemonic(KeyEvent.VK_K);
        chkNotes.setContentAreaFilled(false);
        chkNotes.setOpaque(false);
        chkNotes.addActionListener(this);
        
        JMenuItem chkLocations=new JMenuItem("Check Locations");
        chkLocations.setMnemonic(KeyEvent.VK_L);
        chkLocations.setContentAreaFilled(false);
        chkLocations.setOpaque(false);
        chkLocations.addActionListener(this);
        
        JMenuItem chkSquares=new JMenuItem("Check Squares");
        chkSquares.setMnemonic(KeyEvent.VK_S);
        chkSquares.setContentAreaFilled(false);
        chkSquares.setOpaque(false);
        chkSquares.addActionListener(this);
        
        JMenuItem chkRowCol=new JMenuItem("Check Rows and Columns");
        chkRowCol.setMnemonic(KeyEvent.VK_S);
        chkRowCol.setContentAreaFilled(false);
        chkRowCol.setOpaque(false);
        chkRowCol.addActionListener(this);
        
        
        Debugging.add(reset);
        Debugging.add(chkNotes);
        Debugging.add(chkLocations);
        Debugging.add(chkSquares);
        Debugging.add(chkRowCol);
        Debugging.setContentAreaFilled(false);
        Debugging.setBorderPainted(false);
        Debugging.setOpaque(false);
        
        
        
        menuBar.add(fileMenu);
        menuBar.add(OtherMenu);
        menuBar.add(Debugging);
        
        return menuBar;
    }
	public void addSelf()
	{
		frame.setVisible(false);
		frame.setContentPane(this);
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String id=e.getActionCommand();
		if(FileMenuItems(e))
		{
			
		}else if(GamePlayMenuItems(e))
		{
			
		}
		else if(OffOnItems(e))
		{
			
		}else if(DebuggingMenuItems(e))
		{
			
		}
		// TODO Auto-generated method stub
		
	}
	public boolean GamePlayMenuItems(ActionEvent e)
	{
		boolean b=false;
		String id=e.getActionCommand();
		if(id.equals("Check All Possible"))
		{
			b=true;
			Runner.checkOnce(g);
			System.out.println(g);
		}else if(id.equals("Switch View"))
		{
			b=true;
			view++;
			if(view>=3)
				view=0;
			frame.setVisible(false);
			displayer.removeSelf(this);
			switch(view)
			{
				case 0:
				{
					displayer=new Displayer(g,this);
				}break;
				case 1:
				{
					System.out.println("case 1");
					displayer=new AlternateDisplayer(g,this);
				}break;
				case 2:
				{
					System.out.println("case 2");
					displayer=new AlternateDisplayer2(g,this);
				}break;
			}
			frame.setVisible(true);
		}
		return b;
	}
	public boolean FileMenuItems(ActionEvent e)
	{
		boolean b=false;
		String id=e.getActionCommand();
		if(id.equals("Load"))
		{
			b=true;
			System.out.println("I want to load");
			//input
			
			String number=JOptionPane.showInputDialog("Insert grid Size");
			String fileName=JOptionPane.showInputDialog("Insert filename");
			//resizing
			
			
			int num=Integer.parseInt(number);
			StaticV.highestNumber=num;
			StaticV.gridSize=num;
			StaticV.squareNumber=StaticV.squareSize=(int)Math.sqrt(StaticV.gridSize);
			//file
			
			
			BufferedReader r = null;
			try {
				r = new BufferedReader(new FileReader(new File(fileName+".txt")));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			g=new Grid();
			g.createGrid();
			g.createGridFromAStream(r,StaticV.highestNumber);
			
			//graphics
			frame.setVisible(false);
			int smallsize=StaticV.squareSize*15;
			int bigsize=smallsize*StaticV.gridSize;
		//	frame.setSize(bigsize, bigsize);
			displayer.resizeTable(g);
			frame.setVisible(true);
			Runner.checkOnce(g);
		}
		else if(id.equals("Create New Puzzle"))
		{
			b=true;
			String number=JOptionPane.showInputDialog("Insert grid Size");
			
			//resizing
			int num=Integer.parseInt(number);
			StaticV.highestNumber=num;
			StaticV.gridSize=num;
			StaticV.squareNumber=StaticV.squareSize=(int)Math.sqrt(StaticV.gridSize);
			
			g=new Grid();
			g.createGrid();
			
			//graphics
			frame.setVisible(false);
			int smallsize=StaticV.squareSize*15;
			int bigsize=smallsize*StaticV.gridSize;
		//	frame.setSize(bigsize, bigsize);
			displayer.resizeTable(g);
			frame.setVisible(true);
		}else if(id.equals("Save"))
		{
			b=true;
			String number=JOptionPane.showInputDialog("File Name");
			
			try {
				PrintStream p=new PrintStream(new File(number+".txt"));
				System.out.println(g.toString2());
				p.println(g.toString2());
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//graphics
			frame.setVisible(false);
			int smallsize=StaticV.squareSize*15;
			int bigsize=smallsize*StaticV.gridSize;
		//	frame.setSize(bigsize, bigsize);
			displayer.resizeTable(g);
			frame.setVisible(true);
		}
		return b;
	}
	public boolean OffOnItems(ActionEvent e)
	{
		boolean b=false;
		String id=e.getActionCommand();
		if(id.equals("Turn Notes Off"))
		{
			b=true;
			Noteson.setActionCommand("Turn Notes On");
			Noteson.setText("Turn Notes On");
			BoxRenderer.Noteson=false;
			displayer.repaint();
		}else if(id.equals("Turn Notes On"))
		{
			b=true;
			Noteson.setActionCommand("Turn Notes Off");
			Noteson.setText("Turn Notes Off");
			BoxRenderer.Noteson=true;
			displayer.repaint();
		}else if(id.equals("Turn Automatic Checking Off"))
		{
			b=true;
			AutomaticChecking.setActionCommand("Turn Automatic Checking On");
			AutomaticChecking.setText("Turn Automatic Checking On");
			Displayer.autocheck=false;
		}
		else if(id.equals("Turn Automatic Checking On"))
		{
			b=true;
			AutomaticChecking.setActionCommand("Turn Automatic Checking Off");
			AutomaticChecking.setText("Turn Automatic Checking Off");
			Displayer.autocheck=true;
		}
		return b;
	}
	public boolean DebuggingMenuItems(ActionEvent e)
	{
		
		boolean b=false;
		String id=e.getActionCommand();
		if(id.equals("Reset"))
		{
			b=true;
			g.resetNotes();
			displayer.repaint();
		}
		else if(id.equals("Check Notes"))
		{
			b=true;
			g.checkNotes();
			displayer.repaint();
		}
		else if(id.equals("Check Locations"))
		{
			b=true;
			g.checkLocations();
			displayer.repaint();
		}
		else if(id.equals("Check Squares"))
		{
			b=true;
			g.checkSquares();
			displayer.repaint();
		}
		else if(id.equals("Check Rows and Columns"))
		{
			b=true;
			g.checkRowsCols();
			displayer.repaint();
		}
		return b;
	}
}
