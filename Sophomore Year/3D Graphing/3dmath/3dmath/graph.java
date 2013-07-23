/**
 * graph.java  7/26/2008
 *
 * @author - Jane Doe
 * @author - Period n
 * @author - Id nnnnnnn
 *
 * @author - I received help from ...
 *
 */
import static java.lang.Math.abs;
import static java.lang.Math.pow;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class graph extends staticgraph implements objectproperties
{
	//TODO:finish up transformations and then fix graph to work for only one equation
	static boolean first=true;
	int position=0;
	boolean second=true;
	boolean tempsecond=false;
	Tree x;
	Tree y;
	Tree z;
	Line2D line;
	Points origin=new Points(0,0,0);
	transform form=new transform(0,0,-panz);
	transform form2=new transform(0,0,-panz);
	ArrayList<Trans> transforms=new ArrayList<Trans>();
	int current=-6;
	Color clor=null;
	String xs,ys,zs;
	static ArrayList<Line3d> axis=new ArrayList<Line3d>();
    public graph(Tree x2,String rex,Tree y2,String rey,Tree z2,String rez,double t,double tm)
    {
    	this();
    	xs=rex;ys=rey;zs=rez;
    	x=x2;y=y2;z=z2;
    	x.set("rex",t);
		y.set("rey",t);
		z.set("rez",t);
		objects.add(new Points(x.getnum(),y.getnum(),z.getnum(),t));
    	x.set("rex",tm);
		y.set("rey",tm);
		z.set("rez",tm);
		objects.add(new Points(x.getnum(),y.getnum(),z.getnum(),tm));
		transforms.add(new Trans(0,panz));
		current=0;
    }
    public void init(Tree x2,String rex,Tree y2,String rey,Tree z2,String rez,double t2,double tm)
    {
    	System.out.println("INITINIT/n/n/n/nINITINIT");
    	x=x2;y=y2;z=z2;
    	xs=rex;ys=rey;zs=rez;
    	double x3=0,y3=0,z3=0;
	  	for(double t=t2;t<tm;t+=.05)
    	{
    		x2.set(rex,t);
    		y2.set(rey,t);
    		z2.set(rez,t);
    		//z.set("x",t);
    		x3=x.getnum();
    		y3=y.getnum();
    		z3=z.getnum();
    //		z3=0;
    //		z=Math.pow(x,2)*y;
    		objects.add(new Points(x3,y3,z3,t));
    	}
		transforms.add(new Trans(0,panz));
		current=0;
	//	System.out.println(objects);
    }
    public graph()
    {
    	if(!first)
    	{
    		second=false;
    	}
    	form.rotateAngleY(.01);
    	form.rotateAngleX(.01);
    	form.rotateAngleZ(.01);
    	form2.rotateAngleY(-.01);
    	form2.rotateAngleX(-.01);
    	form2.rotateAngleZ(-.01);
    	if(first)
    	{
    		System.out.println("\n\nfirst\n\n");
    		first=false;
    		makeaxis(-10,10,10);
    	}
    }
    public void makeaxis(int left,int right,double step)
    {
    	double step2=step/10.;
    	axis.add(new Line3d(new Points(-100,0,0),new Points(100,0,0)));
    	axis.add(new Line3d(new Points(0,-100,0),new Points(0,100,0)));
    	axis.add(new Line3d(new Points(0,0,-20),new Points(0,0,500)));
    	for(double k=left;k<=right;k+=step2)
    	{
    		Points p,p2;
    		Line3d l;
    		p=new Points(k*10,-step,0);
    		p2=new Points(k*10,step,0);
    		l=new Line3d(p,p2);
    		l.setColor(Color.red);
    		axis.add(l);
    		p=new Points(k*10,0,-step);
    		p2=new Points(k*10,0,step);
    		l=new Line3d(p,p2);
    		l.setColor(Color.red);
    		axis.add(l);
    		p=new Points(0,k*10,-step);
    		p2=new Points(0,k*10,step);
    		l=new Line3d(p,p2);
    		l.setColor(Color.green);
    		axis.add(l);
    		p=new Points(-step,k*10,0);
    		p2=new Points(step,k*10,0);
    		l=new Line3d(p,p2);
    		l.setColor(Color.green);
    		axis.add(l);
    		p=new Points(0,-step,k*10);
    		p2=new Points(0,step,k*10);
    		l=new Line3d(p,p2);
    		l.setColor(new Color(255,0,255));
    		axis.add(l);
    		p=new Points(-step,0,k*10);
    		p2=new Points(step,0,k*10);
    		l=new Line3d(p,p2);
    		l.setColor(new Color(255,0,255));
    		axis.add(l);
    	}
    }
    public graph(double x1,double y1,double z1)
    {
    	super(x1,y1,z1);
    }
    public Points findPoint(int x,int y)
    {
    	for(int k=0;k<objects.size();k++)
		{
			Extendable c=objects.get(k);
			if(c instanceof Points)
			{
				Points p=(Points)c;
				if(x>=Math.round(p.x2d)-2&&x<=Math.round(p.x2d)+2&&y>=Math.round(p.y2d)-2&&y<=Math.round(p.y2d)+2)
				{
					return p;
				}
			}
		}
		return null;
    }
    public ArrayList<Points> findPoints(Rectangle r)
    {
    	ArrayList<Points> ps=new ArrayList<Points>();
    	for(int k=0;k<objects.size();k++)
		{
			Extendable c=objects.get(k);
			if(c instanceof Points)
			{
				Points p=(Points)c;
				Rectangle temp=new Rectangle((int)(p.x2d-2),(int)(p.y2d-2),(int)4,(int)4);
				if(r.contains(temp))
				{
					ps.add(p);
				}
			}
		}
		return ps;
    }
    public ArrayList<Trans> getTransforms()
    {
    	return transforms;
    }
    public void setformloc(Points f)
    {
    	form=new transform(f.getx(),f.gety(),f.getz());
    	form2=new transform(f.getx(),f.gety(),f.getz());
    	form.rotateAngleY(.01);
    	form.rotateAngleX(.01);
    	form.rotateAngleZ(.01);
    	form2.rotateAngleY(-.01);
    	form2.rotateAngleX(-.01);
    	form2.rotateAngleZ(-.01);
    }
    public void setformloc(int x,int y,int z)
    {
    	form=new transform(x,y,z);
    	form2=new transform(x,y,z);
    	form.rotateAngleY(.01);
    	form.rotateAngleX(.01);
    	form.rotateAngleZ(.01);
    	form2.rotateAngleY(-.01);
    	form2.rotateAngleX(-.01);
    	form2.rotateAngleZ(-.01);
    }
    public void rotate(double x,double y,double z)
    {
    }
	public void zoomout(double x,double y,double z)
	{
	}
	public void panupdown(boolean t)//t if true down
	{
		if(t)
			variables.pany++;
		else
			variables.pany--;
	}
	public void panleftright(boolean t)//t if true right
	{
		if(t)
			panx++;
		else
			panx--;
	}
	public void Moveupdown(boolean t)//t if true down
	{
		double temp=variables.finald-variables.panz;
		if(panz<=-9||panz>0)
		{
			if(temp>=0)
			{
				temp=Math.log(Math.abs(temp)+1)*5+1;
			}
			else
			{
				temp=Math.abs(temp);
				if(temp<1)
				{
					temp=1.;
				}
				temp=1./(Math.log(temp/2.+1));
			}
		}else
		{
			double d=finald-panz;
			d=pow(panz/10.,(int)abs(d))+abs(panz)/100;
			temp=d;
		}
	//	System.out.println(temp);
		if(t)
		{
			variables.pany0+=temp;
//			for(int k=0;k<objects.size();k++)
//			{
//				objects.get(k).moveUp(form);
//			}
		}else
		{
			variables.pany0-=temp;
		}
		int t23=1;
		if(current!=abs(t23))
		{
			if(t)
				current=-t23;
			else
				current=t23;
			transforms.add(new Trans(current,variables.pany0));
		}else if(current!=t23)
		{
			current=-t23;
			transforms.add(new Trans(current,variables.pany0));
		}else if(current!=-t23)
		{
			current=t23;
			transforms.add(new Trans(current,variables.pany0));
		}else
		{
			transforms.get(transforms.size()-1).setTo(variables.pany0);
		}
		for(int k=0;k<objects.size();k++)
		{
			objects.get(k).recalculate();
		}
	}
	public void Moveleftright(boolean t)//t if true down
	{
	//	System.out.print(temp+" first ");
		double temp=variables.finald-variables.panz;
		if(panz<=-9||panz>0)
		{
			if(temp>=0)
			{
				temp=Math.log(Math.abs(temp)+1)*5+1;
			}
			else
			{
				temp=Math.abs(temp);
				if(temp<1)
				{
					temp=1.;
				}
				temp=1./(Math.log(temp/2.+1));
			}
		}else
		{
			double d=finald-panz;
			d=pow(panz/10.,(int)abs(d))+abs(panz)/100;
			temp=d;
		}
	//	System.out.println(temp);
		if(t)
		{
			variables.panx0+=temp;
//			for(int k=0;k<objects.size();k++)
//			{
//				objects.get(k).moveLeft(form);
//			}
		}else
		{
			variables.panx0-=temp;
//			for(int k=0;k<objects.size();k++)
//			{
//				objects.get(k).moveRight(form);
//			}
		}
		int t23=2;
		if(current!=abs(t23))
		{
			if(t)
				current=-t23;
			else
				current=t23;
			transforms.add(new Trans(current,variables.panx0));
		}else if(current!=t23)
		{
			current=-t23;
			transforms.add(new Trans(current,variables.panx0));
		}else if(current!=-t23)
		{
			current=t23;
			transforms.add(new Trans(current,variables.panx0));
		}else
		{
			transforms.get(transforms.size()-1).setTo(variables.panx0);
		}
		for(int k=0;k<objects.size();k++)
		{
			objects.get(k).recalculate();
		}
	}
	public void zoomIn()
	{
		super.zoomIn();
		int t=0;
		if(current!=t)
		{
			current=t;
			transforms.add(new Trans(current,panz));
		}else
		{
			transforms.get(transforms.size()-1).setTo(panz);
		}
	}
	public void zoomOut()
	{
		super.zoomOut();
		int t=0;
		if(current!=t)
		{
			current=t;
			transforms.add(new Trans(current,panz));
		}else
		{
			transforms.get(transforms.size()-1).setTo(panz);
		}
	}
	public void goback()
	{
		current=-6;
		transforms=new ArrayList<Trans>();
		transforms.add(new Trans(0,panz));
		panx=fpanx;pany=fpany;panz=fpanz;
		panx0=0;pany0=0;
		for(int k=0;k<objects.size();k++)
		{
			objects.get(k).goback();
		}
	}
	public void rotatey()
	{
		int t=3;
		if(current!=t)
		{
			current=t;
			Trans temp=new Trans(current,1);
			temp.to=form;
			transforms.add(temp);
		}else
		{
			transforms.get(transforms.size()-1).add();
		}
		for(int k=0;k<objects.size();k++)
		{
			objects.get(k).rotateY(form);
		}
		if(second||tempsecond)
		origin.rotateY(form);
	}
	public void rotatex()
	{
		int t=4;
		if(current!=t)
		{
			current=t;
			Trans temp=new Trans(current,1);
			temp.to=form;
			transforms.add(temp);
		}else
		{
			transforms.get(transforms.size()-1).add();
		}
		for(int k=0;k<objects.size();k++)
		{
			objects.get(k).rotateX(form);
		}
		if(second||tempsecond)
		origin.rotateX(form);
	}
	public void rotatez()
	{
		int t=5;
		if(current!=t)
		{
			current=t;
			Trans temp=new Trans(current,1);
			temp.to=form;
			transforms.add(temp);
		}else
		{
			transforms.get(transforms.size()-1).add();
		}
		for(int k=0;k<objects.size();k++)
		{
			objects.get(k).rotateZ(form);
		}
		if(second||tempsecond)
		origin.rotateZ(form);
	}
	public void backrotatey()
	{
		int t=-3;
		if(current!=t)
		{
			current=t;
			Trans temp=new Trans(current,1);
			temp.to=form2;
			transforms.add(temp);
		}else
		{
			transforms.get(transforms.size()-1).add();
		}
		for(int k=0;k<objects.size();k++)
		{
			objects.get(k).rotateY(form2);
		}
		if(second||tempsecond)
		origin.rotateY(form2);
	}
	public void backrotatex()
	{
		int t=-4;
		if(current!=t)
		{
			current=t;
			Trans temp=new Trans(current,1);
			temp.to=form2;
			transforms.add(temp);
		}else
		{
			transforms.get(transforms.size()-1).add();
		}
		for(int k=0;k<objects.size();k++)
		{
			objects.get(k).rotateX(form2);
		}
		if(second||tempsecond)
		origin.rotateX(form2);
	}
	public void backrotatez()
	{
		int t=-5;
		if(current!=t)
		{
			current=t;
			Trans temp=new Trans(current,1);
			temp.to=form2;
			transforms.add(temp);
		}else
		{
			transforms.get(transforms.size()-1).add();
		}
		for(int k=0;k<objects.size();k++)
		{
			objects.get(k).rotateZ(form2);
		}
		if(second||tempsecond)
		origin.rotateZ(form2);
	}
	public void Moveupdown(boolean t,int w)//t if true down
	{
		double temp=variables.finald-variables.panz;
		if(panz<=-9||panz>0)
		{
			if(temp>=0)
			{
				temp=Math.log(Math.abs(temp)+1)*5+1;
			}
			else
			{
				temp=Math.abs(temp);
				if(temp<1)
				{
					temp=1.;
				}
				temp=1./(Math.log(temp/2.+1));
			}
		}else
		{
			double d=finald-panz;
			d=pow(panz/10.,(int)abs(d))+abs(panz)/100;
			temp=d;
		}
	//	System.out.println(temp);
		if(t)
		{
			variables.pany0+=temp;
//			for(int k=0;k<objects.size();k++)
//			{
//				objects.get(k).moveUp(form);
//			}
		}else
		{
			variables.pany0-=temp;
		}
		int t23=1;
		if(current!=abs(t23))
		{
			if(t)
				current=-t23;
			else
				current=t23;
			transforms.add(new Trans(current,variables.pany0));
		}else if(current!=t23)
		{
			current=-t23;
			transforms.add(new Trans(current,variables.pany0));
		}else if(current!=-t23)
		{
			current=t23;
			transforms.add(new Trans(current,variables.pany0));
		}else
		{
			transforms.get(transforms.size()-1).setTo(variables.pany0);
		}
	}
	public void Moveleftright(boolean t,int w)//t if true down
	{
	//	System.out.print(temp+" first ");
		double temp=variables.finald-variables.panz;
		if(panz<=-9||panz>0)
		{
			if(temp>=0)
			{
				temp=Math.log(Math.abs(temp)+1)*5+1;
			}
			else
			{
				temp=Math.abs(temp);
				if(temp<1)
				{
					temp=1.;
				}
				temp=1./(Math.log(temp/2.+1));
			}
		}else
		{
			double d=finald-panz;
			d=pow(panz/10.,(int)abs(d))+abs(panz)/100;
			temp=d;
		}
	//	System.out.println(temp);
		if(t)
		{
			variables.panx0+=temp;
//			for(int k=0;k<objects.size();k++)
//			{
//				objects.get(k).moveLeft(form);
//			}
		}else
		{
			variables.panx0-=temp;
//			for(int k=0;k<objects.size();k++)
//			{
//				objects.get(k).moveRight(form);
//			}
		}
		int t23=2;
		if(current!=abs(t23))
		{
			if(t)
				current=-t23;
			else
				current=t23;
			transforms.add(new Trans(current,variables.panx0));
		}else if(current!=t23)
		{
			current=-t23;
			transforms.add(new Trans(current,variables.panx0));
		}else if(current!=-t23)
		{
			current=t23;
			transforms.add(new Trans(current,variables.panx0));
		}else
		{
			transforms.get(transforms.size()-1).setTo(variables.panx0);
		}
	}
	public void zoomIn(int w)
	{
		if(panz<=-9||panz>0)
		{
			double d=finald-panz;
		//	System.out.print(d+" ");
			d=Math.pow(.999999,(d*d)/5)-.1;
	//		System.out.print(d+" ");
			panz+=d;
	//		System.out.println(panz);
		}else
		{
			double d=finald-panz;
			d=pow(panz/10.,(int)abs(d))+abs(panz)/100.;
	//		System.out.print(d+" ");
			panz+=abs(d);
	//		System.out.println(panz);
		}
		int t=0;
		if(current!=t)
		{
			current=t;
			transforms.add(new Trans(current,panz));
		}else
		{
			transforms.get(transforms.size()-1).setTo(panz);
		}
	}
	public void zoomOut(int w)
	{
		double d=finald-panz;
//		System.out.print(d+" ");
		d=Math.pow(.999999,(d*d)/5)-.1;
//		System.out.println(d);
		panz-=d;
		int t=0;
		if(current!=t)
		{
			current=t;
			transforms.add(new Trans(current,panz));
		}else
		{
			transforms.get(transforms.size()-1).setTo(panz);
		}
	}
	public void goback(int w)
	{
		transforms=new ArrayList<Trans>();
		current=-6;
		current=0;
		panx=fpanx;pany=fpany;panz=fpanz;
		transforms.add(new Trans(current,panz));
		panx0=0;pany0=0;
		for(int k=0;k<objects.size();k++)
		{
			objects.get(k).goback();
		}
		for(int k=1;k<objects.size();k++)
		{
			if(objects.get(k) instanceof Points)
				((Points)objects.get(k)).recalculate((Points)objects.get(k-1),objects,k,x,xs,y,ys,z,zs,transforms);
		}
		if(second||tempsecond)
		{
			for(int k=0;k<axis.size();k++)
			{
				axis.get(k).goback();
			}
		}
		if(second||tempsecond)
		{
			origin.goback();
		}
		recalculate();
	}
	public void rotatey(int w)
	{
		int t=3;
		if(current!=t)
		{
			current=t;
			Trans temp=new Trans(current,1);
			temp.to=form;
			transforms.add(temp);
		}else
		{
			transforms.get(transforms.size()-1).add();
		}
		for(int k=0;k<objects.size();k++)
		{
			objects.get(k).rotateY(form);
		}
		if(second||tempsecond)
		for(int k=0;k<axis.size();k++)
		{
			axis.get(k).rotateY(form);
		}
		if(second||tempsecond)
		origin.rotateY(form);
	}
	public void rotatex(int w)
	{
		int t=4;
		if(current!=t)
		{
			current=t;
			Trans temp=new Trans(current,1);
			temp.to=form;
			transforms.add(temp);
		}else
		{
			transforms.get(transforms.size()-1).add();
		}
		for(int k=0;k<objects.size();k++)
		{
			objects.get(k).rotateX(form);
		}
		if(second||tempsecond)
		for(int k=0;k<axis.size();k++)
		{
			axis.get(k).rotateX(form);
		}
		if(second||tempsecond)
		origin.rotateX(form);
	}
	public void rotatez(int w)
	{
		int t=5;
		if(current!=t)
		{
			current=t;
			Trans temp=new Trans(current,1);
			temp.to=form;
			transforms.add(temp);
		}else
		{
			transforms.get(transforms.size()-1).add();
		}
		for(int k=0;k<objects.size();k++)
		{
			objects.get(k).rotateZ(form);
		}
		if(second||tempsecond)
		for(int k=0;k<axis.size();k++)
		{
			axis.get(k).rotateZ(form);
		}
		if(second||tempsecond)
		origin.rotateZ(form);
	}
	public void backrotatey(int w)
	{
		int t=-3;
		if(current!=t)
		{
			current=t;
			Trans temp=new Trans(current,1);
			temp.to=form2;
			transforms.add(temp);
		}else
		{
			transforms.get(transforms.size()-1).add();
		}
		for(int k=0;k<objects.size();k++)
		{
			objects.get(k).rotateY(form2);
		}
		if(second||tempsecond)
		for(int k=0;k<axis.size();k++)
		{
			axis.get(k).rotateY(form2);
		}
		if(second||tempsecond)
		origin.rotateY(form2);
	}
	public void backrotatex(int w)
	{
		int t=-4;
		if(current!=t)
		{
			current=t;
			Trans temp=new Trans(current,1);
			temp.to=form2;
			transforms.add(temp);
		}else
		{
			transforms.get(transforms.size()-1).add();
		}
		for(int k=0;k<objects.size();k++)
		{
			objects.get(k).rotateX(form2);
		}
		if(second||tempsecond)
		for(int k=0;k<axis.size();k++)
		{
			axis.get(k).rotateX(form2);
		}
		if(second||tempsecond)
		origin.rotateX(form2);
	}
	public void backrotatez(int w)
	{
		int t=-5;
		if(current!=t)
		{
			current=t;
			Trans temp=new Trans(current,1);
			temp.to=form2;
			transforms.add(temp);
		}else
		{
			transforms.get(transforms.size()-1).add();
		}
		for(int k=0;k<objects.size();k++)
		{
			objects.get(k).rotateZ(form2);
		}
		if(second||tempsecond)
		for(int k=0;k<axis.size();k++)
		{
			axis.get(k).rotateZ(form2);
		}
		if(second||tempsecond)
		origin.rotateZ(form2);
	}
	public void drawgraph(Graphics2D g)
	{
		if(!(oldxcamera==xcamera&&oldpanx==panx&&
		oldycamera==ycamera&&oldpany==pany&&
		oldzcamera==zcamera&&oldpany==pany&&oldd==distance))
		{
			oldxcamera=xcamera;oldpanx=panx;
			oldycamera=ycamera;oldpany=pany;
			oldzcamera=zcamera;oldpany=pany;oldd=distance;
		/*	if(changed)
			for(int k=0;k<objects.size();k++)
			{
			//	System.out.println("recalculateing");
				objects.get(k).recalculate();
			}
			if(second)
			{
				for(int k=0;k<axis.size();k++)
				{
					axis.get(k).recalculate();
				}
			}*/
		}
		for(int k=0;k<objects.size();k++)
		{
		//	Color t=new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
		//	g.setColor(t);
			try
			{
				Extendable s=objects.get(k);
				if(s instanceof Points)
				{
					if(((Points)s).z+panz<=0)
					{
				//		s.setColor(clor);
						s.draw(g);
					}
				}
			}catch(Exception e)
			{

			}
		}
	}
	public static void drawAxis(Graphics g)
	{
		try
		{
		//	System.out.println("the size of the axis "+axis.size());
			for(int k=0;k<axis.size();k++)
			{
				axis.get(k).draw((Graphics2D)g);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void recalculate()
	{

		for(int k=1;k<objects.size();k++)
		{
			Extendable s=objects.get(k);
			if(s instanceof Points)
				((Points)s).recalculate((Points)objects.get(k-1),objects,k,x,xs,y,ys,z,zs,transforms);
		}
		if(second||tempsecond)
		{
			for(int k=0;k<axis.size();k++)
		{
			axis.get(k).recalculate();
		}
		}
		origin.recalculate();
	}
	public void setColor(Color r)
	{
		clor=r;
		for(int k=0;k<objects.size();k++)
		{
			objects.get(k).setColor(r);
		}
	}
	public String printTree(threedpicmaker t2)
	{
		String x2="the equation for the x cordinate is:\n"+x.toString2();
		JOptionPane.showMessageDialog(t2,x2);
		String y2="the equation for the y cordinate is:\n"+y.toString2();
		JOptionPane.showMessageDialog(t2,y2);
		String z2="the equation for the z cordinate is:\n"+z.toString2();
		JOptionPane.showMessageDialog(t2,z2);
		return x2+"\n"+y2+"\n"+z2+"\n";
	}
}

class Trans
{
	private int which;// 0=zoom, 1+-=+-moveup, 2+-=+-moveleft, 3+\-=+\-rotatey,
	//4+\-=+\-rotatex, 5+\-=+\-rotatez
	private double count;
	transform to;
	public Trans(int w,double t)
	{
		which=w;count=t;
	}
	public Trans()
	{

	}
	public void add()
	{
		count++;
	}
	public void subtract()
	{
		count--;
	}
	public void setTo(double t)
	{
		count=t;
	}
	public double getcount()
	{
		return count;
	}
	public int getwhich()
	{
		return which;
	}
	public void setwhich(int t)
	{
		which=t;
	}
	public String toString()
	{
		return "which: "+which+" count: "+count;
	}
}