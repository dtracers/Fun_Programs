package graphics;

import static java.awt.Color.gray;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import materials.Disk;
import materials.WrongSizeException;
import algorythm.Mine;
import algorythm.MineAlt;
import algorythm.Thiers;
import algorythm.algy;

public class GraphicsRunner extends JPanel implements ActionListener,KeyListener,Runnable
{
	ArrayList<Disk> d;
	JFrame frame;
	int Lsize;
	int pegn;
	int diskn;
	int X,N;
	int offx,offy=-20;
	boolean first=true,first2=true;;
	String NMoves="";
	private String NMoves2="";
	algy alg;
	int vertsize=3;
	public GraphicsRunner(ArrayList<Disk> d2)
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		d=d2;
		setSize(650,800);
		setBackground(gray.brighter());
      	setOpaque(true);
      	setVisible(true);
      	frame = new JFrame("Tower of Hanoi");
		frame.setSize(650,800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setJMenuBar(createMenuBar());
      	frame.validate();
      	frame.addKeyListener(this);
      	frame.setVisible(true);
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
        
        /*
        
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
        
        */
        
        menuBar.add(fileMenu);
     //   menuBar.add(OtherMenu);
     //   menuBar.add(Debugging);
        
        return menuBar;
    }
	public void addSelf()
	{
		frame.setVisible(false);
		frame.setContentPane(this);
		frame.setVisible(true);
	}
	public static GraphicsRunner main(String[] args,algy alg,int diskn,int pegn,int X,int N) throws WrongSizeException
	{
		MineAlt x;
		ArrayList<Disk> d=new ArrayList<Disk>();
		double Lsize=300./(double)(pegn);
		double Lsize2=Lsize*2.-3;
		double sSize=5;
		double stepsize=(Lsize2-sSize)/((double)diskn);
		for(int k=1;k<=diskn;k++)
		{
			d.add(new Disk(X,k,(int)Math.max(sSize,1)));
			sSize+=stepsize;
		}
		GraphicsRunner r=new GraphicsRunner(d);
		r.Lsize=(int)(Lsize*2.);
		r.X=X;
		r.N=N;
		r.pegn=pegn;
		r.diskn=diskn;
		r.addSelf();
		r.alg=alg;
		r.NMoves=""+alg.numberOfMoves(d.size(), pegn, X, N);
		alg.setDisk(d);
		alg.setPanel(r);
		while(r.first)
		{
			String g=""+r.first;
		}
		System.out.println();
		return r;
		//alg.solve(d.size(), pegn,X,N);
	}
	public static void main(String[] args) throws WrongSizeException, AWTException
	{
		int diskn=Integer.parseInt(JOptionPane.showInputDialog("Enter Intial Number of Disks"));
		int pegn=Integer.parseInt(JOptionPane.showInputDialog("Enter Intial Number of Pegs"));
		int X=Integer.parseInt(JOptionPane.showInputDialog("Enter Starting Peg"));
		int N=Integer.parseInt(JOptionPane.showInputDialog("Enter Ending Peg"));
		GraphicsRunner one=main(args,new Mine(),diskn,pegn,X,N);
		GraphicsRunner two=main(args,new Thiers(),diskn,pegn,X,N);
	//	GraphicsRunner three=main(args,new MineAlt(),diskn,pegn,X,N);
	//	threads(one,two,three);
		threads(one,two);
	//	threads(three);
	}
	public static void threads(GraphicsRunner...first)
	{
		Thread[] aww=new Thread[first.length];
		for(int k=0;k<first.length;k++)
		{
			aww[k]=new Thread(first[k]);
		}
		Thread temp=new Thread(new seperate(aww));
		JOptionPane.showMessageDialog(null, "Start");
		temp.start();
	}
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, getWidth(), getHeight());
		drawTowers(g);
		drawPegs(g);
		first=false;
		g.setColor(new Color(255,0,255));
		g.drawString(NMoves, offx+20, offy+40);
		g.drawString(getNMoves2(), offx+20, offy+60);
		//System.out.println("ready "+first);
	}
	public void drawTowers(Graphics g)
	{
		int height=(diskn)*(vertsize+2)+5;
		int top=getHeight()-height+offy;
		for(int k=0;k<pegn;k++)
		{
			int left=Lsize+k*Lsize-3+offx;
			g.setColor(Color.gray);
			g.fillRect(left,top,6,height);
			g.setColor(Color.black);
			g.fillRect(left-Lsize-3, getHeight()+offy,Lsize*2+12, 20);
		}
	}
	public void drawPegs(Graphics g)
	{
		g.setColor(Color.blue);
		int top2=getHeight()-diskn*(vertsize+2)+offy;
		for(int k=0;k<d.size();k++)
		{
			Disk t=d.get(k);
			int left=Lsize+(t.getPeg()-1)*Lsize+offx-t.getSize()+offx;
			int top=top2+(t.getDisknumber()-1)*(vertsize+2);
			g.fillRect(left,top,t.getSize()*2,vertsize);
		//	System.out.println(" y "+top+" x "+left+" number "+t.getDisknumber());
		}
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	public void setNMoves2(String nMoves2) {
		NMoves2 = nMoves2;
	}
	public String getNMoves2() {
		return NMoves2;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e)
	{
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void run()
	{
		if(first2)
		{
			first2=false;
			try {
				System.out.println("Solving");
		//		JOptionPane.showMessageDialog(this, "Solving");
				alg.solve(diskn, pegn,X,N);
			} catch (WrongSizeException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
class seperate implements Runnable
{
	Thread [] os;
	public seperate(Thread...o)
	{
		os=o;
	}
	@Override
	public void run()
	{
		for(Thread h:os)
		{
			h.start();
		}
	}
	
}
