package Gameplay.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import WormStuff.Worm;


public class KeyMouse implements KeyListener,MouseListener
{
	public Worm w;
	public KeyMouse(Worm m)
	{
		w=m;
	}
	@Override
	public void keyPressed(KeyEvent arg0)
	{
		if(arg0.getKeyCode()==KeyEvent.VK_DOWN&&w.getDirection()!=0)
			w.setDirection(2);
		if(arg0.getKeyCode()==KeyEvent.VK_UP&&w.getDirection()!=2)
			w.setDirection(0);
		if(arg0.getKeyCode()==KeyEvent.VK_LEFT&&w.getDirection()!=3)
			w.setDirection(1);
		if(arg0.getKeyCode()==KeyEvent.VK_RIGHT&&w.getDirection()!=1)
			w.setDirection(3);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
