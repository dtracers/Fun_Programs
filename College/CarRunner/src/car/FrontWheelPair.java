package car;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.Graphic;

public class FrontWheelPair extends Wheel implements KeyListener
{
	Wheel left,right;
	int turn;
	double wheeldist;
	Point temp=new Point();
	public static final double MAXTURN=Math.PI/3;
	public static final double MINTURN=-Math.PI/3;
	public static final double TURNVELO=Math.PI/5.;
	public FrontWheelPair(Point p,double wheeldistfromp,double angleJ)
	{
		System.out.println(wheeldistfromp);
		double b[]=getAngle(angleJ);
		Point w=new Point();w.setLocation(wheeldistfromp,0);
		rotate(w,b[0],b[1]);
		w.setLocation(w.getX()+p.getX(),w.getY()+p.getY());
		right=new Wheel(w);
		
		w=new Point();w.setLocation(-wheeldistfromp,0);
		rotate(w,b[0],b[1]);
		w.setLocation(w.getX()+p.getX(),w.getY()+p.getY());
		left=new Wheel(w);
		this.p=p;
		wheeldist=wheeldistfromp;
	}
	public void setLocation(Point p,double cosAngle,double sinAngle)
	{
		temp=new Point();temp.setLocation(wheeldist,0);
		rotate(temp,cosAngle,sinAngle);
		temp.setLocation(temp.getX()+p.getX(),temp.getY()+p.getY());
		right.p=temp;
		
		temp=new Point();temp.setLocation(-wheeldist,0);
		rotate(temp,cosAngle,sinAngle);
		temp.setLocation(temp.getX()+p.getX(),temp.getY()+p.getY());
		left.p=temp;
		temp=new Point();
		this.p=p;
	}
	public void setDegreesTurn(double d)
	{
		left.setDegreesTurn(d);
		right.setDegreesTurn(d);
	}
	public void UpdateAngle()
	{
		super.UpdateAngle();
		left.UpdateAngle();
		right.UpdateAngle();
	}
	public void setVelocity(double d)
	{
		velocity=d;
		left.setVelocity(d);
		right.setVelocity(d);
	}
	public void Move(double time,Car c)
	{
		double d=degreesturn+c.degreesturn2;
		//System.out.println("front wheels angle"+Math.toDegrees(d)+" turn angle "+Math.toDegrees(degreesturn));
		double[] b=getAngle(d);
		left.setAngles(d,b[0],b[1]);
		right.setAngles(d,b[0],b[1]);
		
		
		temp.setLocation(0,-c.carlength2);
		rotate(temp,c.cos,c.sin);
		p.setLocation(c.p.getX()+temp.getX(),c.p.getY()+temp.getY());
		setLocation(p,c.cos,c.sin);
	}
	public void Update(double time)
	{
		double degreesmove=turn*time*TURNVELO;
		degreesmove+=degreesturn;
		degreesturn=degreesmove>MAXTURN?MAXTURN:degreesmove<MINTURN?MINTURN:degreesmove;
	}
	public void draw(Graphics g)
	{
		left.draw(g);
		right.draw(g);
		g.drawLine((int)left.p.getX(),(int)left.p.getY(),(int)right.p.getX(),(int)right.p.getY());
		Point temp=new Point(0,-100);
		rotate(temp,left.cos,left.sin);
		g.drawLine((int)p.getX(),(int)p.getY(),(int)(p.getX()+temp.getX()),(int)(p.getY()+temp.getY()));
	}
	@Override
	public void keyTyped(KeyEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		int id=e.getKeyCode();
		if(id==e.VK_RIGHT)
		{
			turn=1;
		}
		if(id==e.VK_LEFT)
		{
			turn=-1;
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		
		int id=e.getKeyCode();
		if(id==e.VK_RIGHT)
		{
			turn=0;
		}
		if(id==e.VK_LEFT)
		{
			turn=0;
		}
	}
}
