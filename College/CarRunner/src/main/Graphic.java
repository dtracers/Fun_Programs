package main;

import static java.awt.Color.gray;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;

import car.Car;
import car.functions.PixelInch;

public class Graphic extends JPanel implements ActionListener
{
	static long cTime;
	static long oTime;
	static int x=200;
	static int y=500;
	public static long timeDiff=100;
	public JFrame frame;
	Graphics mine;
	/*Car kar=new Car(new Point(20+x,20+y),
			        new Point(100+x,20+y),
			        new Point(20+x,200+y),
			        new Point(100+x,200+y),
			        0);*/
	Car kar=new Car(new Point(40+x,20+y),
	        new Point(100+x,20+y),
	        new Point(20+x,200+y),
	        new Point(120+x,200+y),
	        0);
	{
		PixelInch.setPIR(180./4.12);
	}
	public Graphic()
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
      	frame.addKeyListener(kar.Front);
      	frame.addKeyListener(kar.Back);
	}
	@Override
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.white);
		g.fillRect(0,0,1000,1000);
		g.setColor(Color.black);
		kar.draw(g);
		Update();
	}
	public void Update(long time)
	{
		kar.Update(time);
	}
	public void Update()
	{
		do
		{
			
		}while((cTime=(System.currentTimeMillis()-oTime))<timeDiff);
		oTime=System.currentTimeMillis();
		Update(cTime);
		repaint();
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
        
        
        fileMenu.add(save); 
        fileMenu.add(load);
        fileMenu.setContentAreaFilled(false);
        fileMenu.setBorderPainted(false);
        fileMenu.setOpaque(false);
        
        
        
        menuBar.add(fileMenu);
        
        return menuBar;
    }
	
	public void addSelf()
	{
		frame.setVisible(false);
		frame.setContentPane(this);
		frame.setVisible(true);
		mine=this.getGraphics();
	}
	public JFrame getFrame()
	{
		addSelf();
		return frame;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
