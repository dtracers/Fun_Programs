package car;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;

public class Wheel
{
	boolean straightahead=true;
	double degreesturn=0;
	double velocity;
	Point p;
	Rectangle r=new Rectangle(20,40);
	double sin,cos;
	public Wheel()
	{
		
	}
	public Wheel(Point p)
	{
		System.out.println("Im a made wheel");
		this.p=p;
	}
	public void draw(Graphics g)
	{
		Polygon poly=new Polygon();
		
		Point corner=new Point();
		corner.setLocation(-r.getWidth()/2,-r.getHeight()/2);
		Point first=rotate(corner,cos,sin);
		poly.addPoint((int)(p.getX()+corner.getX()),(int)(p.getY()+corner.getY()));
		
		corner.setLocation(r.getWidth()/2,-r.getHeight()/2);
		rotate(corner,cos,sin);
		poly.addPoint((int)(p.getX()+corner.getX()),(int)(p.getY()+corner.getY()));
		
		corner.setLocation(r.getWidth()/2,r.getHeight()/2);
		rotate(corner,cos,sin);
		poly.addPoint((int)(p.getX()+corner.getX()),(int)(p.getY()+corner.getY()));
		
		corner.setLocation(-r.getWidth()/2,r.getHeight()/2);
		rotate(corner,cos,sin);
		poly.addPoint((int)(p.getX()+corner.getX()),(int)(p.getY()+corner.getY()));
		
		poly.addPoint((int)(p.getX()+first.getX()),(int)(p.getY()+first.getY()));
		
		g.drawPolygon(poly);
		first.setLocation(0,-20);
		rotate(first,cos,sin);
		g.drawLine((int)p.getX(),(int)p.getY(),(int)(p.getX()+first.getX()),(int)(p.getY()+first.getY()));
	}
	public void draw(Graphics g,Point p2)
	{
		Polygon poly=new Polygon();
		
		Point corner=new Point();
		corner.setLocation(-r.getWidth()/2,-r.getHeight()/2);
		Point first=rotate(corner,cos,sin);
		poly.addPoint((int)(p2.getX()+p.getX()+corner.getX()),(int)(p2.getY()+p.getY()+corner.getY()));
		
		corner.setLocation(r.getWidth()/2,-r.getHeight()/2);
		rotate(corner,cos,sin);
		poly.addPoint((int)(p2.getX()+p.getX()+corner.getX()),(int)(p2.getY()+p.getY()+corner.getY()));
		
		corner.setLocation(r.getWidth()/2,r.getHeight()/2);
		rotate(corner,cos,sin);
		poly.addPoint((int)(p2.getX()+p.getX()+corner.getX()),(int)(p2.getY()+p.getY()+corner.getY()));
		
		corner.setLocation(-r.getWidth()/2,r.getHeight()/2);
		rotate(corner,cos,sin);
		poly.addPoint((int)(p2.getX()+p.getX()+corner.getX()),(int)(p2.getY()+p.getY()+corner.getY()));
		
		poly.addPoint((int)(p2.getX()+p.getX()+first.getX()),(int)(p2.getY()+p.getY()+first.getY()));
		
		g.drawPolygon(poly);
	}
	public void draw(Graphics g,Point p2,double angleJ)
	{
		UpdateAngle(angleJ);
		Polygon poly=new Polygon();
		
		Point corner=new Point();
		corner.setLocation(-r.getWidth()/2,-r.getHeight()/2);
		Point first=rotate(corner,cos,sin);
		poly.addPoint((int)(p2.getX()+p.getX()+corner.getX()),(int)(p2.getY()+p.getY()+corner.getY()));
		
		corner.setLocation(r.getWidth()/2,-r.getHeight()/2);
		rotate(corner,cos,sin);
		poly.addPoint((int)(p2.getX()+p.getX()+corner.getX()),(int)(p2.getY()+p.getY()+corner.getY()));
		
		corner.setLocation(r.getWidth()/2,r.getHeight()/2);
		rotate(corner,cos,sin);
		poly.addPoint((int)(p2.getX()+p.getX()+corner.getX()),(int)(p2.getY()+p.getY()+corner.getY()));
		
		corner.setLocation(-r.getWidth()/2,r.getHeight()/2);
		rotate(corner,cos,sin);
		poly.addPoint((int)(p2.getX()+p.getX()+corner.getX()),(int)(p2.getY()+p.getY()+corner.getY()));
		
		poly.addPoint((int)(p2.getX()+p.getX()+first.getX()),(int)(p2.getY()+p.getY()+first.getY()));
		
		g.drawPolygon(poly);
		UpdateAngle();
	}
	public void UpdateAngle()
	{
		sin=Math.sin(degreesturn);
		cos=Math.cos(degreesturn);
	}
	public void UpdateAngle(double angleJ)
	{
		sin=Math.sin(degreesturn+angleJ);
		cos=Math.cos(degreesturn+angleJ);
	}
	public static double[] getAngle(double angleJ)//return cos sin
	{
		double b[]=new double[2];
		b[1]=Math.sin(angleJ);
		b[0]=Math.cos(angleJ);
		return b;
	}
	public double getDegreesTurn()
	{
		return degreesturn;
	}
	public void setDegreesTurn(double d)
	{
		degreesturn=d;
		UpdateAngle();
	}
	public void setAngles(double d,double c,double s)
	{
		cos=c;sin=s;degreesturn=d;
	}
	public void Move(double time,Car c)
	{
		
	}
	public void Update(double time)
	{
		/*
		Point temp=new Point();
		temp.setLocation(0,-time*velocity);
		rotate(temp,cos,sin);
		p.setLocation(p.getX()+temp.getX(), p.getY()+temp.getY());*/
	}
	public static Point rotate(Point p,double cosAngle,double sinAngle)//cross product
    {
    	double newX=p.getX()*cosAngle-p.getY()*sinAngle;
    	double newY=p.getX()*sinAngle+p.getY()*cosAngle;
    	p.setLocation(newX,newY);
    	return p;
    }
	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}
	public double getVelocity() {
		return velocity;
	}
	public void setAnglesToZero()
	{
		cos=0;sin=0;
	}
	public double getDistance(Wheel j)
	{
		double x=j.p.getX()-p.getX();
		double y=j.p.getY()-p.getY();
		return Math.sqrt(x*x+y*y);
	}
}
