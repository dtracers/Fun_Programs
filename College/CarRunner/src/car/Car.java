package car;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

import static car.functions.PixelInch.*;

public class Car
{
	Point p;
	double carlength;
	double carlength2;
	double velocity;
	double degreesturn=0;
	double degreesturn2=0;
	public FrontWheelPair Front;
	public BackWheelPair Back;
	double cos,sin;
	/**
	 * 
	 * @param w1 front left wheel
	 * @param w2 front right wheel
	 * @param w3 back left wheel
	 * @param w4 back right wheel
	 * @param angleJ
	 */
	public Car(Point w1,Point w2,Point w3,Point w4,double angleJ)
	{
		Point p=new Point();
		p.setLocation((w3.getX()+w4.getX())/2.,(w3.getY()+w4.getY())/2.);
		Point p2=new Point();
		p2.setLocation((w1.getX()+w2.getX())/2.,(w1.getY()+w2.getY())/2.);
		double frontaxeldist=carlength=getDistance(p,p2);
		carlength2=frontaxeldist;
		System.out.println("the distance is "+carlength2);
		//carlength*=getPIR();
		Point temp=new Point();
		temp.setLocation(0,-frontaxeldist);
		double[] b=Wheel.getAngle(angleJ);
		Wheel.rotate(temp,b[0],b[1]);
		
		Back=new BackWheelPair(p,getDistance(w3,w4)/2.,angleJ);
		Back.c=this;
		Back.UpdateAngle();
		
		p2.setLocation(p.getX()+temp.getX(),p.getY()+temp.getY());
		Front=new FrontWheelPair(p2,getDistance(w1,w2)/2.,angleJ);
		Front.UpdateAngle();
	}
	public void UpdateAngle()
	{
		sin=Math.sin(degreesturn);
		cos=Math.cos(degreesturn);
	}
	public void UpdateAngle(double angleJ)
	{
		degreesturn=angleJ;
		degreesturn2=degreesturn;//+angleJ;
		sin=Math.sin(degreesturn2);
		cos=Math.cos(degreesturn2);
	}
	public void Update(double time)
	{
		time/=1000.;
		Back.Update(time);
		Front.Update(time);
		System.out.println("time "+time);
		setVelocity(Back.getVelocity());
		Front.setVelocity(velocity);
		double d=velocity/carlength*Math.tan(Front.degreesturn);
		System.out.println("degrees of turnage "+Math.toDegrees(d)/velocity*time);
		d+=degreesturn;
		if(d>Math.PI*2)
			d-=Math.PI*2;
		UpdateAngle(d);
		Back.Move(time,this);
		p=Back.p;
		Front.Move(time, this);
	//	System.out.println("updating Velocity is:"+velocity+" angle is: "+Math.toDegrees(degreesturn));
		System.out.println("Pixels traveled in a sec is "+(velocity*time)+"velocity is "+velocity);
	}
	public void setVelocity(double velocity)
	{
		this.velocity=velocity;
	}
	public void draw(Graphics g)
	{
		Front.draw(g);
		Back.draw(g);
		Polygon poly=new Polygon();
		poly.addPoint((int)Front.left.p.getX(), (int)Front.left.p.getY());
		poly.addPoint((int)Front.right.p.getX(), (int)Front.right.p.getY());
		poly.addPoint((int)Back.left.p.getX(), (int)Back.left.p.getY());
		poly.addPoint((int)Back.right.p.getX(), (int)Back.right.p.getY());
		g.drawPolygon(poly);
	}
	public static double getDistance(Point j,Point p)
	{
		double x=j.getX()-p.getX();
		double y=j.getY()-p.getY();
		return Math.sqrt(x*x+y*y);
	}
	public double getFrontalArea() {
		// TODO Auto-generated method stub
		return 0;
	}
}
