/**
 * Arc3d.java  11/19/2008
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

public class Arc3d extends Polys
{
	Points[] main=new Points[5];
	Arc2d[] oldmain=new Arc2d[5];
	double degrees,degrees2;
	int which;
	double xrad,yrad,zrad;
	boolean xswitch=false,yswitch=false;
	int farleft,farright,farup,fardown;
	int counter=0;
	double xstart,ystart;
	Random h=new Random();
	Points center;
	double xrad2d,yrad2d;
    public Arc3d()
    {
    }
    public Arc3d(Points start,double angle,double angle2,double xrad2,double yrad2,double zrad2)
    {
    	start.recalculate();
    	xstart=start.x2d;
    	ystart=start.y2d;
    	main[0]=start;
    	degrees=angle;
    	degrees2=angle2;
    	xrad=xrad2;yrad=yrad2;zrad=zrad2;
    	makepoints();
    }
    public void setColor(Color t)
    {
    	super.setColor(t);
    	for(int k=0;k<main.length;k++)
    	{
    		main[k].setColor(t);
    	}
    }
    public void setwhich(int t)
    {
    	which=t;
    }
    public void goback()
    {
    	for(int k=0;k<main.length;k++)
    	{
    		main[k].goback();
    	}
    	recalculate();
    }
    public void draw(Graphics2D g)
    {
    	g.setColor(super.getColor());
    	switch(which)
    	{
    		case 0: 
    		case 1: 
    			for(int k=0;k<=main.length;k++)
    			{
    				if(main[k%main.length]!=null&&main[(k+1)%main.length]!=null)
    				new Line3d(main[k%main.length],main[(k+1)%main.length]).draw(g);
    			}   			
    		case 2: fill(g,which);break;
    		case 3:
    		case 4: 
    			for(int k=0;k<=main.length;k++)
    			{
    				if(main[k%main.length]!=null&&main[(k+1)%main.length]!=null)
    				new Line3d(main[k%main.length],main[(k+1)%main.length]).draw(g);
    			}   		 
    		case 5: draw1(g,which);break;
    	}
    }
    public void fill(Graphics g,int t)
    {
    	if(t==1)
    		for(int k=0;k<main.length;k++)
    			if(main[k]!=null)  	
    			main[k].draw((Graphics2D)g);
    		g.setColor(Color.red);
    	for(int k=0;k<oldmain.length;k++)
    	{
    		if(oldmain[k]!=null)
    		{
    			oldmain[k].fill(g);
    		}
    	}
    }
    public void draw1(Graphics g,int t)
    {
    	if(t==3)
    		for(int k=0;k<main.length;k++)
    			if(main[k]!=null)
    			main[k].draw((Graphics2D)g);
    		g.setColor(Color.red);	
    	for(int k=0;k<oldmain.length;k++)
    	{
    		if(oldmain[k]!=null)
    		{
    			g.setColor(new Color(h.nextInt(255),h.nextInt(255),h.nextInt(255)));
    			oldmain[k].draw(g);
    		}
    	}
    }
    public void rotateX(transform xform)
    {
    	for(int k=0;k<main.length;k++)
    	{
    		if(main[k]!=null)
    			main[k].rotateX(xform);
    	}
    	recalculate();
    }
    public void rotateY(transform xform)
    {
    	for(int k=0;k<main.length;k++)
    	{
    		if(main[k]!=null)
    		main[k].rotateY(xform);
    	}
    	recalculate();
    }
    public void rotateZ(transform xform)
    {
    	for(int k=0;k<main.length;k++)
    	{
    		if(main[k]!=null)
    		main[k].rotateZ(xform);
    	}
    	recalculate();
    }
    public void recalculate()
    {
    	for(int k=0;k<main.length;k++)
    	{
    		if(main[k]!=null)
    		main[k].recalculate();
    	}
    	makearc();
    }
    public void zoomOut()
    {
    	for(int k=0;k<main.length;k++)
    	{
    		if(main[k]!=null)
    		main[k].zoomOut();
    	}
    }
    public void zoomIn()
    {
    	for(int k=0;k<main.length;k++)
    	{
    		if(main[k]!=null)
    		main[k].zoomIn();
    	}
    }
    public void makepoints()
    {
//    	Points temp=new Points[5];
    	Double[] xt=new Double[4],yt=new Double[4],zt=new Double[4];
    	if(degrees>=90)
    	{
    		yt[0]=main[0].gety()+yrad;
    		xt[0]=main[0].getx()-xrad;
    		
    	}if(degrees>=180)
    	{
    		yt[1]=main[0].gety()+0;
    		xt[1]=main[0].getx()-2*xrad;
   		}if(degrees>=270)
    	{
    		yt[2]=main[0].gety()-yrad;
    		xt[2]=main[0].getx()-xrad;
    	}
    	if(degrees==360)
    	{
    		yt[3]=main[0].gety();
    		xt[3]=main[0].getx();
    	}
    	if(degrees%90!=0)
    	{
    		int t=0;
    		while(t<xt.length-1&&xt[t]==null&&yt[t]==null)
    			t++;
    		xt[t]=main[0].getx()-Math.cos(Math.toRadians(degrees))*xrad;
    		yt[t]=main[0].gety()+Math.sin(Math.toRadians(degrees))*yrad;
    	}
    	if(degrees2>=90)
    	{
    		zt[0]=main[0].getz()+zrad;
    		
    	}if(degrees2>=180)
    	{
    		zt[1]=main[0].getz()+0;
    	}if(degrees2>=270)
    	{
    		zt[2]=main[0].getz()-zrad;
    	}
    	if(degrees==360)
    	{
    		zt[3]=main[0].getz();
    	}
    	if(degrees2%90!=0)
    	{
    		int t=0;
    		while(t<zt.length-1&&zt[t]==null)
    			t++;
    		zt[t]=main[0].getz()+Math.sin(Math.toRadians(degrees2))*zrad;
    	}
    	int t=0;
    	while(t<xt.length&&xt[t]!=null&&yt[t]!=null)
    	{
    		if(zt[t]==null)
    		{
    			zt[t]=main[0].getz();
    		}
    		t++;
    	}
    	t=0;
    	while(t<zt.length&&zt[t]!=null)
    	{
    		if(xt[t]==null)
    		{
    			xt[t]=main[0].getx();
    		}
    		if(yt[t]==null)
    		{
    			yt[t]=main[0].gety();
    		}
    		t++;
    	}
    //	System.out.println("xarray");
    //	myString.toString(xt);
    //	System.out.println("yarray");
    //	myString.toString(yt);
    //	System.out.println("xarray");
    //	myString.toString(zt);
    	t=0;
    	while(t<xt.length&&xt[t]!=null&&yt[t]!=null&&zt[t]!=null)
    	{
		//	if(degrees>=)
    		main[t+1]=new Points(xt[t],yt[t],zt[t]);
    		t++;
    	}
    	recalculate();
    	System.out.println(this);
    	center=new Points(main[0].getx()-xrad,main[1].getx()-yrad,midz(zt));
    	for(int k=0;k<oldmain.length;k++)
    	{
    		Color temp=null;
    		if(oldmain[k]!=null)
    		{
    			switch(k)
    			{
    				case 0: temp=Color.black;break;
    				case 1: temp=Color.red;break;
    				case 2: temp=Color.green;break;
    				case 3: temp=Color.blue;break;
    			}
    			oldmain[k].h=temp;
    			oldmain[k].counter=k;
    		}
    	}
    }
    public String toString()
    {
    	String u="Arc3d degrees: "+degrees+" degrees2: "+degrees2+"\n";
    	for(int k=0;k<main.length;k++)
    	{
    		if(main[k]!=null&&k<main.length-1&&main[k+1]!=null)
    			u+=""+main[k].toString()+" , ";
    		else if(main[k]!=null)
    			u+=""+main[k].toString()+"";
    	}
    	u+="\nxradius: "+xrad+" yradius: "+yrad+" zradius: "+zrad+"\n";
//    	u+="\nxarc: "+x+" yarc: "+y+"\n";
    	return u;
    }
    public void switchemx(int i,int i2)
    {
    	if(oldmain[i2]!=null&&oldmain[i]!=null)
		oldmain[i2].switchx(oldmain[i],(int)Math.round(xrad),(int)Math.round(xrad));
    }
    public double midz(Double []z)
    {
    	double under=Integer.MAX_VALUE;
    	double upper=Integer.MIN_VALUE;
    	for(int k=0;k<z.length;k++)
    	{
    		if(z[k]!=null)
    		{
    			if(z[k]<under)
    			{
    				under=z[k];
    			}
    			if(z[k]>upper)
    			{
    				upper=z[k];
    			}
    		}
    	}
    	return (under+upper)/2;
    }
    public void makearc()
    {
    	checkswitch();
    	if(!xswitch||true)
    	{
	    	int t=0;
	    	while(t<oldmain.length&&t<main.length-1&&main[t]!=null)
	    	{
	    		if(oldmain[t]==null)
	    		{
	    			oldmain[t]=new Arc2d(main[t],main[(t+1)%main.length],getdegrees1(t),getdegrees2(t),(int)Math.round(xrad2d),(int)Math.round(yrad2d));
	    		}
	    		else
	    		{
	    			oldmain[t].makearc(main[t],main[(t+1)%main.length],getdegrees1(t),getdegrees2(t),(int)Math.round(xrad2d),(int)Math.round(yrad2d));
	    		}
	    		t++;
	    		
	    	}
//    		if(oldmain[0]!=null&&xswitch&&oldmain[2]!=null)
//    		{
//				switchemx(0,2);
//    		}if(oldmain[1]!=null&&xswitch&&oldmain[2]!=null)
//    		{
//				switchemx(1,3);
//    		}
    	}else if(xswitch)
    	{
	    	int t=0;
	    	while(t<oldmain.length&&t<main.length-1&&main[t]!=null)
	    	{
	    		if(main[t]!=null)
	    		{
		    		if(oldmain[t]==null)
		    		{
		    			oldmain[t]=new Arc2d(main[(main.length-1)-t],main[(main.length-1)-(t+1)%main.length],getdegrees1(t),getdegrees2(t),(int)Math.round(xrad2d),(int)Math.round(yrad2d));
		    		}
		    		else
		    		{
		    			oldmain[t].makearc(main[(main.length-1)-t],main[(main.length-1)-(t+1)%main.length],getdegrees1(t),getdegrees2(t),(int)Math.round(xrad2d),(int)Math.round(yrad2d));
		    		}
	    		}
	    		t++;
	    	}
    	}
    	xswitch=false;
    }
    public void checkswitch()
    {
    	xrad2d=Math.abs(farright()-farleft());
    	yrad2d=Math.abs(fardown()-farup());
    	System.out.println(xrad2d+" currxrad"+ yrad2d+" curryrad");
//    	int temp=farleft(),temp2=farright();
//    	if(farleft!=temp&&farright!=temp2)
//    	{
//    		counter++;
//    		farleft=temp;
//    		farright=temp2;
//    		if(counter%2==0)
//    		{
//    			xswitch=true;
//    			System.out.println(true);
//    		}
//    		if(counter==2)
//    			counter=0;    	
//    	}
    }
    public int getdegrees1(int t)
    {
    	t+=1;
    	if(xswitch)
    		t=(int)Math.abs(main.length-t);
    	
    	if(t==1&&degrees>=90)
    	{
    		return 90;
    	}else if(t==2&&degrees>=180)
    	{
    		return 90;
    	}else if(t==3&&degrees>=270)
    	{
    		return 90;
    	}else if(t==4&&degrees>=360)
    	{
    		return 90;
    	}else if(degrees%90!=0)
    	{
    		return (int)degrees-((int)degrees/90)*90;
    	}
    	return 0;
    }
    public int getdegrees2(int t)
    {	
    	double angle=0;
    	t+=1;
//    	if(xswitch)
//    		t=(int)Math.abs(main.length-t);   	
    	if(t==1&&degrees>=90)
    	{
    		angle=0;
    	}
    	else if(t==2&&degrees>=180)
    	{
    		angle=90;
    	}else if(t==3&&degrees>=270)
    	{
    		angle=180;
    	}else if(t==4&&degrees>=271)
    	{
    		angle=270;
    	}
//else
//    		angle=getchanged();
	try
	{
		double pop=Math.abs(getchanged());
		if(!(""+pop).equalsIgnoreCase("nan"))
			angle+=pop;
	}catch(Exception e)
	{
		e.printStackTrace();
	}
    	return (int)Math.round(angle);
    }
    public double getchanged()
    {    	
    	return (Math.toDegrees(Math.atan(Math.abs(main[0].y2d-ystart)/Math.abs(main[0].x2d-xstart))));
    }
    public int farleft()
    {
    	int u=Integer.MAX_VALUE;
    	int index=0;
    	for(int k=0;k<main.length;k++)
    	if(main[k].x2d<u)
    	{
    		u=(int)Math.round(main[k].x2d);
    		index=k;
    	}
    	return index;
    }
    public int farright()
    {
    	int u=Integer.MIN_VALUE;
    	int index=0;
    	for(int k=0;k<main.length;k++)
    	if(main[k].x2d>u)
    	{
    		u=(int)Math.round(main[k].x2d);
    		index=k;
    	}
    	return index;
    }
    public int farup()
    {
    	int u=Integer.MAX_VALUE;
    	int index=0;
    	for(int k=0;k<main.length;k++)
    	if(main[k].y2d<u)
    	{
    		u=(int)Math.round(main[k].y2d);
    		index=k;
    	}
    	return index;
    }
    public int fardown()
    {
    	int u=Integer.MIN_VALUE;
    	int index=0;
    	for(int k=0;k<main.length;k++)
    	if(main[k].y2d>u)
    	{
    		u=(int)Math.round(main[k].y2d);
    		index=k;
    	}
    	return index;
    }
}
class Arc2d
{
	Color h;
	int x,y,degrees1=360,length,height,startangle=180;
	int counter=-1;
	public Arc2d()
	{
		
	}
	public Arc2d(Arc2d temp)
	{
		x=temp.x;
		y=temp.y;
		length=temp.length;
		height=temp.height;
		degrees1=temp.degrees1;
		startangle=temp.startangle;
	}
	public void switchxy(Arc2d temp,int xrad,int yrad)
	{
	int tempx,tempy,tempx2,tempy2;
		if(x<temp.x)
		{
			tempx=x+xrad;
			tempx2=temp.x-xrad;
		}else
		{
			tempx2=temp.x+xrad;
			tempx=x-xrad;
		}
		if(y<temp.y)
		{
			tempy2=temp.y-yrad;
			tempy=y+yrad;
		}else
		{
			tempy2=temp.y+yrad;
			tempy=y-yrad;
		}	
		x=tempx2;
		temp.x=tempx;
		y=tempy2;
		temp.y=tempy;
	}
	public void switchx(Arc2d temp,int xrad,int yrad)
	{	
		int tempx,tempx2;
		if(x<temp.x)
		{
			tempx=x+xrad;
			tempx2=temp.x-xrad;
		}else
		{
			tempx2=temp.x+xrad;
			tempx=x-xrad;
		}
		x=tempx2;
		temp.x=tempx;
	}
	public Arc2d(Points s,Points e,int degrees,int degrees2,int xrad,int yrad)
	{
		makearc(s,e,degrees,degrees2,xrad,yrad);
	}
	public void draw(Graphics g)
	{
		g.setColor(h);
		g.drawString(""+this,x,y);
		g.drawArc(x,y,length,height,startangle,degrees1);
	}
	public void fill(Graphics g)
	{
		g.setColor(h);
		g.drawString(""+this,x,y);
		g.fillArc(x,y,length,height,startangle,degrees1);
	}
	public void makearc(Points s,Points e,int degrees,int degrees2,int xrad,int yrad)
    {
    	x=farleft(s,e);
    	y=farup(s,e);
    	length=2*xrad;
    	height=2*yrad;
    //	length=(int)Math.abs(x-farright(s,e))*2;
    //	height=(int)Math.abs(y-fardown(s,e))*2;
    	if(startangle==0||startangle==270)
    		x-=length/2;
    	if(startangle==180||startangle==270)
    		y-=height/2;
    	degrees1=degrees;
    	startangle=degrees2;
    }
    public int farleft(Points s,Points e)
    {
    	int u=Integer.MAX_VALUE;
    	if(s.x2d<u)
    		u=(int)Math.round(s.x2d);
    	if(e.x2d<u)
    		u=(int)Math.round(e.x2d);
    	return u;
    }
    public int farright(Points s,Points e)
    {
    	int u=Integer.MIN_VALUE;
    	if(s.x2d>u)
    		u=(int)Math.round(s.x2d);
    	if(e.x2d>u)
    		u=(int)Math.round(e.x2d);
    	return u;
    }
    public int farup(Points s,Points e)
    {
    	int u=Integer.MAX_VALUE;
    	if(s.y2d<u)
    		u=(int)Math.round(s.y2d);
    	if(e.y2d<u)
    		u=(int)Math.round(e.y2d);
    	return u;
    }
    public int fardown(Points s,Points e)
    {
    	int u=Integer.MIN_VALUE;
    	if(s.y2d>u)
    		u=(int)Math.round(s.y2d);
    	if(e.y2d>u)
    		u=(int)Math.round(e.y2d);
    	return u;
    }
    public String toString()
    {
    	return "c:"+counter+" sa: "+startangle+" d: "+degrees1;
    }
}