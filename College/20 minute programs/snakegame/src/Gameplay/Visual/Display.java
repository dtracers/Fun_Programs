package Gameplay.Visual;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.Timer;

import Gameplay.Input.KeyMouse;
import WormStuff.Food;
import WormStuff.Segment;
import WormStuff.Worm;


public class Display extends JFrame implements ActionListener
{
	KeyMouse input;
	Worm m;
	Food d;
	public static int distance=5;
	public BufferedImage img;
	public Graphics g;
	Timer t;
	public Display(String title)
	{
		super(title);
		m=new Worm(50,50,d=new Food());
		for(int k=0;k<5;k++)
		{
			m.passback(new Segment());
		}
		t=new Timer(500, this);
		input=new KeyMouse(m);
		addKeyListener(input);
		addMouseListener(input);
		setVisible(false);
		setSize(1500,1000);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		img=new BufferedImage(3000,2000,1);
		g=img.getGraphics();
		t.start();
	}
	public void paint(Graphics g)
	{
		this.g.setColor(Color.white);
		this.g.fillRect(0, 0, 1000, 1000);
		d.draw(this.g);
		m.draw(this.g);
		g.drawImage(img, 7, 23,null);
	}
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		m.actionPerformed(arg0);
		repaint();
	}
}