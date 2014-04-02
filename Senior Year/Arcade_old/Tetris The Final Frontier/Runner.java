import graphics.ColorChanger;
import graphics.FlashyText;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

import javax.swing.JMenuBar;
import javax.swing.Timer;

import tetris.Engine;
import tetris.Map.map;
import tetris.Movement.Direction;
import tetris.Pieces.BackGroundPiece;
import tetris.Pieces.FutureBlock;
import tetris.Pieces.LivePiece;
import tetris.Pieces.Piece;
import tetris.types.CoolEngine;

@SuppressWarnings("serial")
public class Runner extends Game
{
	public static String FILESTART = "";
	boolean first=false;
	long oldtime=System.currentTimeMillis();
	long oldtime2=System.currentTimeMillis();
	int diff2=0;
	ColorChanger f=new ColorChanger(true,3000,Color.blue,Color.green,Color.darkGray,Color.yellow,Color.gray,Color.red);
	FlashyText d=new FlashyText(3000,500,500,"Press Start",Color.yellow,Color.black);
	Engine one=new CoolEngine();
	Timer tetris;
	Timer graphics;
	private int leveltime=1000;
	private boolean multiple = false;
	int x=24,y=24;
	int xs=24,ys=24;
	@Override	
	public void about()
	{
	}
	@Override
	public void controls()
	{
	}
	public JMenuBar createMenuBar()
	{
		return super.createMenuBar();
	}
	private void updateGraphics()
	{
		long currtime=System.currentTimeMillis();
		int diff=(int)(currtime-oldtime);
		d.update(diff);
		f.update(diff);
		oldtime=currtime;
		repaint();
	}
	private void updateTetris()
	{
		long currtime=System.currentTimeMillis();
		diff2=(int)(currtime-oldtime2);
		if(diff2>=leveltime)
		{
			oldtime2=currtime;
			System.out.println("Updating");
			one.update(diff2);
		}
		repaint();
	}
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.black);
		g.fillRect(0, 0, 1000, 1000);		
		diff2=0;
		paint2(g);
		d.draw(g);
		// super.paint(g);
	}
	public void paint2(Graphics g)
	{
		int width=one.getDeadMap().getWidth();
		int height=one.getDeadMap().getHeight();
		this.drawboundries(g, width, height);
		this.drawblocks(g, width, height);
		this.drawNext(g,width*xs+x,y, 5,5);
	//	this.drawblocks2(g, x, y, xs, ys, width, height);
	}
	public void drawblocks(Graphics g,int width,int height)
	{
		ArrayList<Piece>p=one.getLiveBlocks();
		for(int k=0;k<p.size();k++)
		{
			p.get(k).draw(g,x,y,xs,ys);
		}
		p=one.getDeadBlocks(); 
		for(int k=0;k<p.size();k++)
		{
			p.get(k).draw(g,x,y,xs,ys);
		}
		p=one.getFakeDeadBlocks(); 
		for(int k=0;k<p.size();k++)
		{
			p.get(k).draw(g,x,y,xs,ys);
		}
	}
	public void drawblocks2(Graphics g,int width,int height)
	{
		int[][]map=one.getDeadMap().getBoard();
		BackGroundPiece temp=new BackGroundPiece();
		temp.c=Color.pink;
		for(int k=0;k<height;k++)
		{
			for(int q=0;q<width;q++)
			{
				temp.setX(q);
				temp.setY(k);
				if(map[k][q]==1)
				temp.draw(g, x, y, xs, ys);
			}
		}
	}
	public void drawboundries(Graphics g,int width,int height)
	{
		g.setColor(f.getCurrentColor());
		g.fillRect(x-xs, y, xs*(width+1)-xs+xs/12, ys*(height-2)+ys/2);		
		Piece temp=new BackGroundPiece();
		//background
		for(int k=0;k<height;k++)
		{
			for(int q=0;q<width;q++)
			{
				temp.setX(q);
				temp.setY(k);
				temp.draw(g, x, y, xs, ys);
			}
		}
		
		//walls
		temp=new LivePiece();
		((LivePiece)temp).setColor(Color.green.darker());
		int[][] supertemp=new int[1][1];
		supertemp[0][0]=1;
		temp.setMap(new map(supertemp));
		supertemp=null;
		for(int q=0;q<=width+1;q++)
		{
			temp.setY(0);
			temp.setX(q);
			temp.draw(g, x-xs, y, xs, ys);
		}
		((LivePiece)temp).setColor(Color.blue.darker());
		for(int q=0;q<height;q++)
		{	
			temp.setX(0);
			temp.setY(q);
			temp.draw(g, x-xs, y, xs, ys);
		}
		((LivePiece)temp).setColor(Color.blue.darker());
		for(int q=0;q<height;q++)
		{
			temp.setX(width);
			temp.setY(q);
			temp.draw(g, x, y, xs, ys);
		}
		((LivePiece)temp).setColor(Color.gray.darker());
		for(int q=-1;q<=width;q++)
		{
			temp.setX(q);
			temp.setY(height);
			temp.draw(g, x, y, xs, ys);
		}
	}
	public void drawNext(Graphics g,int x,int y,int width,int height)
	{
		g.setColor(f.getCurrentColor());
		g.fillRect(x-2,y-2,width*(xs-1),height*(ys-1));
		Piece p=one.peek();
		if(p==null)
			return;
		p=p.copy();
		int[][]map=p.getMap().getBoard();
		p.setX((width-map[0].length)/2);
		p.setY((height-map.length)/2);
		if(p instanceof FutureBlock)
			((FutureBlock) p).drawfuture=false;
		BackGroundPiece back=new BackGroundPiece();
		for(int k=0;k<5;k++)
		{
			for(int q=0;q<5;q++)
			{
				back.setX(q);
				back.setY(k);
				back.draw(g, x, y, xs, ys);
			}
		}
		p.draw(g,x,y,xs,ys);
	}
	@Override
	public void keyPressed(KeyEvent e)
	{
		int key=e.getKeyCode();
		if(key==e.VK_LEFT)
		{
			one.moveLeft(0);
		}else if(key==e.VK_RIGHT)
		{
			one.moveRight(0);
		}else if(key==e.VK_DOWN)
		{
			one.moveDown(0);
		}
		else if(key==e.VK_UP)
		{
			while(one.moveDown(0));
			one.update(0);
		}
		else if(key==e.VK_SPACE&&!multiple)
		{
			one.Rotate(Direction.CounterClockWise,0);
		}
		if(multiple)
		{
			if(key==e.VK_A)
			{
				one.moveLeft(1);
			}else if(key==e.VK_D)
			{
				one.moveRight(1);
			}else if(key==e.VK_S)
			{
				one.moveDown(1);
			}
			else if(key==e.VK_W)
			{
				while(one.moveDown(1));
				one.update(1);
			}
			else if(key==e.VK_SPACE)
			{
				one.Rotate(Direction.CounterClockWise,1);
			}
			else if(key==e.VK_SHIFT)
			{
				one.Rotate(Direction.CounterClockWise,0);
			}
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void quit()
	{
		tetris.stop();
		graphics.stop();
	}

	@Override
	public void runGame()
	{
		one.extension = this.getPathExtension();
		FILESTART = this.getPathExtension();
		graphics=new Timer(0,this);
		graphics.setActionCommand("g");
		graphics.start();
		tetris=new Timer(1000,this);
		tetris.setActionCommand("tetrisupdate");
		tetris.start();
		repaint();
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean action(ActionEvent e)
	{
		if(e.getActionCommand().equals("g"))
		{
			updateGraphics();
			return true;
		}
		else if(e.getActionCommand().equals("tetrisupdate"))
		{
			updateTetris();
			return true;
		}
		return false;
			
	}
}
