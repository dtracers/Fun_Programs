
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.text.Segment;


public class Display extends JFrame implements ActionListener
{
	KeyMouse input;
	Mole m;
	public static int distance=5;
	public BufferedImage img;
	public Graphics g;
	Timer t;
	static int score=0;
	public Display(String title)
	{
		super(title);
		t=new Timer(100, this);
		m=new Mole(t,this);
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
		this.g.fillRect(0, 0, 2000, 2000);
		this.g.setColor(Color.black);
		this.g.drawString("Score = "+score, 50, 50);
		m.draw(this.g);
		g.drawImage(img, 0,0,null);
	}
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		m.actionPerformed(arg0);
		repaint();
	}
	public void paintagain() {
		repaint();
		// TODO Auto-generated method stub
		
	}
}