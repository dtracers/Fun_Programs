/**
 * Points.java  7/27/2008
 *
 * @author - Jane Doe
 * @author - Period n
 * @author - Id nnnnnnn
 *
 * @author - I received help from ...
 *
 */
import java.awt.*;
import java.applet.*;
import java.io.*;
import java.net.*;
import java.text.*;
import javax.swing.*;
import java.lang.*;
import java.util.*;
import java.sql.*;
import sun.audio.*;

public class Points extends singular
{
	double x=0;
	double y=0;
	double z=0;
	double graphx=0;
	private double fx=0;
	private double fy=0;
	private double fz=0;
	private static double d4=4.*(200.-settings)/100.,d3=3.*(200.-settings)/100.;
//	double zpic=0;
	double x2d=panx-screenx;
	double y2d=pany-screeny;
	Color color=null;
	private boolean different=false;
    public String toString()
    {
    	return "("+x+","+y+","+z+") Point";
    }
    public String toString(int t)
    {
    	if(t==3)
    	{
    		return "("+x+","+y+","+z+") Point";
    	}else if(t==2)
    	{
    		return "("+x2d+","+y2d+") Point";
    	}else if(t==1)
    	{
    		return "("+x+","+y+","+z+") Point\n"+"("+x2d+","+y2d+") Point";
    	}
    	return toString();
    }
    public Points()
    {
    	this(0,0,0);
    	fx=x;fy=y;fz=z;
    }
    public Points(Points v)
    {
    	this(v.x,v.y,v.z,v.graphx);
    	fx=x;fy=y;fz=z;
    }
    public double getfinalx()
    {
    	return fx;
    }
    public double getfinaly()
    {
    	return fy;
    }
    public double getfinalz()
    {
    	return fz;
    }
    public void rotateX(transform xform)
    {
    	rotateX(xform.getCosAngleX(),xform.getSinAngleX());
    	recalculate();
    }
    public void rotateY(transform xform)
    {
    	rotateY(xform.getCosAngleY(),xform.getSinAngleY());
    	recalculate();
    }
    public void rotateZ(transform xform)
    {
    	rotateZ(xform.getCosAngleZ(),xform.getSinAngleZ());
    	recalculate();
    }
    public void rotateX(transform xform,double times)
    {
    	double a1=xform.getCosAngleX(),a2=xform.getSinAngleX();
    	for(int k=0;k<(int)times;k++)
    	{
    		rotateX(a1,a2);
    	}
    //	recalculate();
    }
    public void rotateY(transform xform,double times)
    {
    	double a1=xform.getCosAngleY(),a2=xform.getSinAngleY();
    	for(int k=0;k<(int)times;k++)
    	{
    		rotateY(a1,a2);
    	}
    //	recalculate();
    }
    public void rotateZ(transform xform,double times)
    {
    	double a1=xform.getCosAngleZ(),a2=xform.getSinAngleZ();
    	for(int k=0;k<(int)times;k++)
    	{
    		rotateZ(a1,a2);
    	}
    //	recalculate();
    }
    public void moveUp(transform xform)
    {
    	y=y+1.*panz/1000.;
    	recalculate();
    }
    public void moveDown(transform xform)
    {
    	y=y-1.*panz/1000.;
    	recalculate();
    }
    public void moveLeft(transform xform)
    {
    	x=x-1.*panz/1000.;
    	recalculate();
    }
    public void moveRight(transform xform)
    {
    	x=x-1.*panz/1000.;
    	recalculate();
    }
    public void moveUp(double panz)
    {
    	y=y+1.*panz/1000.;
    	recalculate();
    }
    public void moveDown(double panz)
    {
    	y=y-1.*panz/1000.;
    	recalculate();
    }
    public void moveLeft(double panz)
    {
    	x=x-1.*panz/1000.;
    	recalculate();
    }
    public void moveRight(double panz)
    {
    	x=x-1.*panz/1000.;
    	recalculate();
    }
    public Points(double x,double y,double z)
    {
    	setTo(x,y,z);
    	fx=x;fy=y;fz=z;
    }
    public Points(double x,double y,double z,double gx)
    {
    	setTo(x,y,z);
    	fx=x;fy=y;fz=z;graphx=gx;
    }
    public void setTo(double x,double y,double z)
    {
    	this.x=x;this.y=y;this.z=z;//zpic=z+finald;
    	fx=this.x;fy=this.y;fz=this.z;
    	recalculate();
    }
    public double getx()
    {
    	return x;
    }
    public double gety()
    {
    	return y;
    }
    public double getDotProduct(Points p)
    {
    	return x*p.x+y*p.y+z*p.z;
    }
    public void setToCrossProduct(Points u,Points v)
    {
    	double x=u.y*v.z-u.z*v.y;
    	double y=u.z*v.x-u.x*v.z;
    	double z=u.x*v.y-u.y*v.x;
    	this.x=x;this.y=y;this.z=z;
    }
    public void goback()
    {
    	//System.out.println("before");
    //	System.out.println(this);
    	x=fx;y=fy;z=fz;
    	recalculate();
    //	System.out.println("after");
    //	System.out.println(this);
    }
    public double getz()
    {
    	return z;
    }
    public void zoomOut()
    {
//    	zpic++;
    	recalculate();
    }
    public void zoomIn()
    {
//    	zpic--;
    	recalculate();
    }
    public int getfakex()
    {
    	return (int)Math.round(x2d);
    }
    public int getfakey()
    {
    	return(int)Math.round(y2d);
    }
    public void recalculate()
    {
    	x2d=win2screen.convertx(x,z);
    	y2d=win2screen.converty(y,z);
    	if(x2d>screenx&&y2d>screeny&&x2d<screenx+sizew&&y2d<screeny+sizeh)
    	{
    		onscreen=true;
    	}else
    	{
    		onscreen=false;
    	}
    }
    public void recalculate(ArrayList<Trans> trans)
    {
    //	System.out.println(trans);
    	double tempz=variables.panz;
    	double tempx=variables.panx0;
    	double tempy=variables.pany0;
    //	System.out.println(this);
    //	System.out.println("before");
    	for(int k=0;k<trans.size();k++)
    	{
    		Trans t=trans.get(k);
    		switch(t.getwhich())
    		{
    			case 0:
    			{
    				variables.panz=t.getcount();
    			//	recalculate();
    			}break;
    			case -1:
    			{
    				variables.pany0=t.getcount();
    			//	recalculate();
    			}break;
    			case 1:
    			{
    				variables.pany0=t.getcount();
    			//	recalculate();
    			}break;
    			case -2:
    			{
    				variables.panx0=t.getcount();
    			//	recalculate();
    			}break;
    			case 2:
    			{
    				variables.panx0=t.getcount();
    			//	recalculate();
    			}break;
    			case -3:
    			{
    				rotateY(t.to,t.getcount());
    		//		System.out.println("-ry");
    			}break;
    			case 3:
    			{
    				rotateY(t.to,t.getcount());
    		//		System.out.println("ry");
    			}break;
    			case -4:
    			{
    				rotateX(t.to,t.getcount());
    		//		System.out.println("-rx");
    			}break;
    			case 4:
    			{
    				rotateX(t.to,t.getcount());
    		//		System.out.println("rx");
    			}break;
    			case -5:
    			{
    				rotateZ(t.to,t.getcount());
    		//		System.out.println("-rz");
    			}break;
    			case 5:
    			{
    				rotateZ(t.to,t.getcount());
    		//		System.out.println("rz");
    			}break;
    		}
    	}
    	recalculate();
    //	System.out.println(this);
    //	System.out.println("after");
    	if(tempz!=variables.panz)
    	{
    	//	System.out.println("not the same");
    	}
    }
    public int recalculate(Points p,ArrayList<Extendable> list,int k,
    	Tree x,String xs,Tree y,String ys,Tree z,String zs,ArrayList<Trans> trans)
    {
    	recalculate();
    	p.recalculate();
    	double d=visualDistance(this,p);
    	boolean fish=false;
    	if(!onscreen&&k!=0&&k!=list.size()-1)
    	{
    	//	System.out.println("changing");
    		list.remove(k);
    		fish=true;
    	}
    	else if(onscreen)
    	{
	    	if(d<d3&&list.size()>4000*settings/100.)
	    	{
	    		if(k==1)
	    		{
	    			list.remove(k);
	    		}else if(k>1)
	    		{
	    			list.remove(k-1);
	    		}
	    		fish=true;
	    	}
	    	else if(d>d4&&list.size()<10000*settings/100.)
	    	{
	    		double gx=(this.graphx+p.graphx)/2.;
	    	//	set(x,xs,y,ys,z,zs,gx);
	    		x.set(xs,gx);
	    		y.set(ys,gx);
	    		z.set(zs,gx);
	    		double x2=x.getnum(),y2=y.getnum(),z2=z.getnum();
	    		boolean cont=false;
	    		if(isfake(x2)||isfake(y2)||isfake(z2))
	    		{
	    			cont=true;
	    		}
	    		if(!cont)
	    		{
		    		Points temp=new Points(x2,y2,z2,gx);
		    		temp.recalculate(trans);
		    		list.add(k,temp);//adds afterwords
		    		d=visualDistance(temp,p);
		    		if(d>4&&list.size()<8000*settings/100.&&temp.onscreen)
		    		{
		    			fish=true;
		    		}
	    		}
	    	}
    	}
    	else if(!onscreen&&k-2>=0&&k==list.size()-1&&p.onscreen)
    	{
    		if(d>d4&&list.size()<10000*settings/100.)
    		{
				double gx=p.graphx-((Points)list.get(k-2)).graphx;
				gx+=p.graphx;
			//	set(x,xs,y,ys,z,zs,gx);
	    		x.set(xs,gx);
	    		y.set(ys,gx);
	    		z.set(zs,gx);
	    		double x2=x.getnum(),y2=y.getnum(),z2=z.getnum();
	    		boolean cont=false;
	    		if(isfake(x2)||isfake(y2)||isfake(z2))
	    		{
	    			cont=true;
	    		}
	    		if(!cont)
	    		{
		    		Points temp=new Points(x2,y2,z2,gx);
		    		temp.recalculate(trans);
		    		list.add(k,temp);
	    		}
    		}
    	}
    	if(fish)
    	{
    		k-=1;
    	}
    	return k;
    }
    public void transfer(Vector3d v)
    {
    	setTo((double)v.x,(double)v.y,(double)v.z);
    }
    public static Points change(Vector3d v)
    {
    	return new Points((double)v.x,(double)v.y,(double)v.z);
    }
    public static Points addpoints(Points p1,Points p2)
    {
    	Points temp=new Points();
    	temp.setTo(p1);
    	temp.add(p2);
    	return temp;
    }
    public void setTo(Points v)
    {
    	setTo(v.x,v.y,v.z);
    }
    public boolean equals(Object obj)
    {
    	Points v=(Points)obj;
    	return equals(v.x,v.y,v.z);
    }
    public boolean equals(double vx,double vy, double vz)
    {
    	return(vx==x&&vy==y&&vz==z);
    }
    public static double visualDistance(Points p,Points p2)
    {
    	return Math.sqrt(Math.pow(p.x2d-p2.x2d,2)+Math.pow(p.y2d-p2.y2d,2));
    }
	public void add(double x2,double y2,double z2)
    {
    	setTo(x+x2,y+y2,z+z2);
    }
    public void add(Points v)
    {
    	add(v.x,v.y,v.z);
    }
    public void subtract(double x,double y,double z)
    {
    	add(-x,-y,-z);
    }
    public void subtract(Points v)
    {
    	add(-v.x,-v.y,-v.z);
    }
    public void multiply(double x2)
    {
    	setTo(x*x2,y*x2,z*x2);
    }
    public void divide(double x2)
    {
    	setTo(x/x2,y/x2,z/x2);
    }
    public double getdistance0()
	{
		return Math.sqrt(x*x+y*y+z*z);
	}
    public void draw(Graphics2D g)
    {
    	if(onscreen)
    	{
    		if(different)
	    		g.setColor(color);
	    	else
	    		g.setColor(Color.black);
	    	g.fillRect((int)Math.round(x2d)-2,(int)Math.round(y2d)-2,4,4);
    	}
    }
    public void setColor(Color t)
    {
    	if(!t.equals(Color.black))
    	{
    		different=true;
    		color=t;
    	}else
    	{
    		different=false;
    		color=null;
    	}
    }
    public void setgraphx(double x)
    {
    	graphx=x;
    }
    public void rotateX(double cosAngle,double sinAngle)//cross product
    {
    	double newY=y*cosAngle-z*sinAngle;
    	double newZ=y*sinAngle+z*cosAngle;
    	y=newY;
    	z=newZ;
    }
    public void rotateY(double cosAngle,double sinAngle)//cross product
    {
    	double newZ=z*cosAngle-x*sinAngle;
    	double newX=z*sinAngle+x*cosAngle;
    	x=newX;
    	z=newZ;
    }
    public void rotateZ(double cosAngle,double sinAngle)//cross product
    {
    	double newX=x*cosAngle-y*sinAngle;
    	double newY=x*sinAngle+y*cosAngle;
    	x=newX;
    	y=newY;
    }
    //rotated then tranlated
    public void add(transform xform)
    {
    	addRotation(xform);
    	add(xform.getLocation());
    }
    public void subtract(transform xform)
    {
    	subtract(xform.getLocation());
    	subtractRotation(xform);
    }
    public void addRotation(transform xform)
    {
   		rotateX((double)xform.getCosAngleX(),(double)xform.getSinAngleX());
   		rotateY((double)xform.getCosAngleY(),(double)xform.getSinAngleY());
   		rotateZ((double)xform.getCosAngleZ(),(double)xform.getSinAngleZ());
    }
    public void subtractRotation(transform xform)
    {
    	//note that sin(-x) == -sin(x)  and  cos(-x) == cos(x)
    	rotateZ((double)xform.getCosAngleZ(),(double)-xform.getSinAngleZ());
   		rotateY((double)xform.getCosAngleY(),(double)-xform.getSinAngleY());
   		rotateX((double)xform.getCosAngleX(),(double)-xform.getSinAngleX());
    //	rotateX();
    }
    private boolean isfake(double t)
    {
    	String g=""+t;
    	if(g.equals("NaN")||g.equalsIgnoreCase("Inf")||g.equalsIgnoreCase("-Inf")||g.equalsIgnoreCase("?"))
    	{
    		return true;
    	}
    	return false;
    }
    private void set(Tree x,String xs,Tree y,String ys,Tree z,String zs,double gx)
    {
		x.set(xs,gx);
		y.set(ys,gx);
		z.set(zs,gx);
	}
	protected boolean isdifferent()
	{
		return different;
	}
	public int compareTo(Object j)
    {
    	Points j2=(Points)j;
    	return (int)Math.signum(j2.z-this.z);
    }
}