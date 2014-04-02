package car;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import car.functions.AccelBrake;
import static car.functions.PixelInch.*;
//TODO fix the turn thing where it lines the axel up  (?) maybe do reverse of rotate method where given the two points find the angles
public class BackWheelPair extends Wheel implements KeyListener
{
	Wheel left,right;
	int velo=0;
	double wheeldist;
	Point temp=new Point();
	Car c;
	public BackWheelPair(Point p,double wheeldistfromp,double angleJ)
	{
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
	public void UpdateAngle()
	{
		left.UpdateAngle();
		right.UpdateAngle();
		super.UpdateAngle();
	}
	public void setDegreesTurn(double d)
	{
		super.setDegreesTurn(d);
		left.setDegreesTurn(d);
		right.setDegreesTurn(d);
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
		if(id==e.VK_UP)
		{
			velo=1;
		}
		if(id==e.VK_DOWN)
		{
			velo=-1;
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		int id=e.getKeyCode();
		if(id==e.VK_UP)
		{
			velo=0;
		}
		if(id==e.VK_DOWN)
		{
			velo=0;
		}
	}
	public void Move(double time,Car c)
	{
		temp.setLocation(0,-time*velocity);
		rotate(temp,c.cos,c.sin);
		p.setLocation(p.getX()+temp.getX(),p.getY()+temp.getY());
		setLocation(p,c.cos,c.sin);
		left.setAngles(c.degreesturn,c.cos,c.sin);
		right.setAngles(c.degreesturn,c.cos,c.sin);
		
	}
	public void Update(double time)
	{
		velocity+=time*(velo==0?0:velo>0?AccelBrake.getAccel(getVelocity(),c):AccelBrake.getReverse(getVelocity()));
	}
/*	public void getNewAngle(Wheel back,Wheel front)
	{
		double distance=back.getDistance(front);
		if(velocity>=0)
		{
			Point temp=new Point();
			temp.setLocation(0,-distance);
			double top=temp.getX()*temp.getY();
			tempor.setColor(Color.blue);
			tempor.drawLine((int)p.getX(),(int)p.getY(),(int)(p.getX()+temp.getX()),(int)(p.getY()+temp.getY()));
			tempor.drawLine((int)p.getX(),(int)p.getY(),(int)front.p.getX(),(int)front.p.getY());
			tempor.setColor(Color.black);
			temp.setLocation(front.p.getX()-back.p.getX(),front.p.getY()-back.p.getY());
			top+=temp.getX()*temp.getY();
			double bottom=distance*distance;
			double AngleBetween=Math.acos(top/bottom)-Math.PI/2.;
			System.out.println("angle between "+AngleBetween+" degrees turn "+degreesturn);
			back.setDegreesTurn(AngleBetween);
		}
	}*/
}