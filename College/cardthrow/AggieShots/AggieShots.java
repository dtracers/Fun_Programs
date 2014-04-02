import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.BufferedReader;





public class AggieShots extends Game
{
	int x=0,x1;
	int y=0,y1;
	int xmove=1,xmove1=1,ymove1=1;
	int ymove=1;
	@Override
	public void runGame()
	{
		super.addTimer(50,"paint",this);
		super.addTimer(100,"paint1",this);
		super.startAllTimers();
		System.out.println(super.time);
		System.out.println("start");
	}
	public void paintComponent(Graphics g)
	{
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.black);
		g.fillRect(x1, y1, this.getWidth()-x-x1, this.getHeight()-y-y1);
		
	}
	@Override
	public boolean action(ActionEvent e)
	{
		String g=e.getActionCommand();
		//System.out.println(g);
		if(g.equals("paint"))
		{
		//	System.out.println("updateing");
			if(x>this.getWidth()||x<0)
				xmove*=-1;
			if(y>this.getHeight()||y<0)
				ymove*=-1;
			x+=xmove;
			y+=ymove;
			repaint();
		}
		else if(g.equals("paint1"))
		{
		//	System.out.println("updateing");
			if(x1>this.getWidth()||x1<0)
				xmove1*=-1;
			if(y1>this.getHeight()||y1<0)
				ymove1*=-1;
			x1+=xmove1;
			y1+=ymove1;
		//	repaint();
		}
		return false;
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void quit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controls() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void about() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
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
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		
	}

}
