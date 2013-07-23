/**
 * Polygon3d.java  7/27/2008
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

public class Polygon3d extends Polys
{
	Vector3d normal=new Vector3d();
	double left,right;
	double top,bottom;
	double finaltop,finalbottom,finalleft,finalright;
	boolean isfacing;
	boolean oldisfacing;
	boolean upsidedown;
	int indexl,indexr,indext,indexb;
	int oldindexl,oldindexr,oldindext,oldindexb;
	int finalindexl,finalindexr,finalindext,finalindexb;
    public Polygon3d()
    {
    	setColor(new Color(255/2,255,255/2));
    	which=1;
    }
    ArrayList<Points> p=new ArrayList<Points>();
    ArrayList<Points> oldp=new ArrayList<Points>();
    public void setwhich(int y)
    {
    	super.set(y);
    }
    public void setColor(Color t)
    {
    	super.setColor(t);
    	for(int k=0;k<p.size();k++)
    	{
    		p.get(k).setColor(t);
    	}
    }
    public Vector3d calcNormal()
    {
    	Vector3d temp1=new Vector3d();
    	Vector3d temp2=new Vector3d();
    	temp1.setTo(Vector3d.change(p.get(2)));
    	temp1.subtract(Vector3d.change(p.get(1)));
    	temp2.setTo(Vector3d.change(p.get(0)));
    	temp2.subtract(Vector3d.change(p.get(1)));
    	normal.setToCrossProduct(temp1,temp2);
    	normal.normalize();
    	return normal;
    }
    public Vector3d getNormal()
    {
    	return normal;
    }
    public void setNormal(Vector3d v)
    {
    	normal.setTo(v);
    }
    public void setNormal(Points v)
    {
    	setNormal(Vector3d.change(v));
    }
    public boolean isFacing(Vector3d v)
    {
    	Vector3d temp1=new Vector3d();
    	temp1.setTo(v);
    	temp1.subtract(Vector3d.change(p.get(0)));
    	return normal.getDotProduct(temp1)>=0;
    }
    public boolean isFacing(Points v)
    {
    	Vector3d temp1=new Vector3d();
    	temp1.setTo(Vector3d.change(v));
    	temp1.subtract(Vector3d.change(p.get(0)));
    	return normal.getDotProduct(temp1)>=0;
    }
    public Points GetNormal()
    {
    	return Points.change(getNormal());
    }
    public Points CalcNormal()
    {
    	return Points.change(calcNormal());
    }
    public Polygon3d(Points p0,Points p1,Points p2,Points p3)
    {
    	p.add(p0);
    	p.add(p1);
    	p.add(p2);
    	p.add(p3);
    	Polygon3d(p);
    	setColor(new Color(255/2,255,255/2));
    	which=1;
    }
    public void rotateX(transform xform)
    {
    	for(int k=0;k<p.size();k++)
    	{
    		p.get(k).rotateX(xform);
    	}
    	recalculate();
    }
    public void rotateY(transform xform)
    {
    	for(int k=0;k<p.size();k++)
    	{
    		p.get(k).rotateY(xform);
    	}
    	recalculate();
    }
    public void rotateZ(transform xform)
    {
    	for(int k=0;k<p.size();k++)
    	{
    		p.get(k).rotateZ(xform);
    	}
    	recalculate();
    }
    public void moveUp(transform xform)
    {
    	for(int k=0;k<p.size();k++)
    	{
    		p.get(k).moveUp(xform);
    	}
    	recalculate();
    }
    public void moveDown(transform xform)
    {
    	for(int k=0;k<p.size();k++)
    	{
    		p.get(k).moveDown(xform);
    	}
    	recalculate();
    }
    public void moveLeft(transform xform)
    {
    	for(int k=0;k<p.size();k++)
    	{
    		p.get(k).moveLeft(xform);
    	}
    	recalculate();
    }
    public void moveRight(transform xform)
    {
    	for(int k=0;k<p.size();k++)
    	{
    		p.get(k).moveRight(xform);
    	}
    	recalculate();
    }
    public void goback()
    {
    	p=(ArrayList<Points>)oldp.clone();
    ///	System.out.println("before");
    //	System.out.println(this);
    	for(int k=0;k<p.size();k++)
    	{
    		p.get(k).goback();
    	}
    //	System.out.println("after");
    //	System.out.println(this);
    	recalculate();
    }
    public boolean clipz(double clipz)
    {
    	boolean isCompletelyHidden=true;
    	for(int k=0;k<p.size();k++)
    	{
    		int next=(k+1)%p.size();
    		Points v1=p.get(k);
    		Points v2=p.get(next);
    		if(v1.z<clipz)
    		{
    			isCompletelyHidden=false;
    		}
    		if(v1.z>v2.z)
    		{
    			Points temp=v1;
    			v1=v2;
    			v2=temp;
    		}
    		if(v1.z<clipz&&v2.z>clipz)
    		{
    			double scale=(clipz-v1.z/(v2.z-v1.z));
    			p.add(next,new Points(v1.x+scale*(v2.x-v1.x),v1.y+scale*(v2.y-v1.y),clipz));
    		}
    	}
    	if(isCompletelyHidden)
    	{
    		return false;
    	}
    	for(int k=p.size()-1;k>=0;k--){
    		if(p.get(k).z>clipz)
    			p.remove(k);
    	}
    	return (p.size()>=3);
    }
    public boolean clipy(double clipy)
    {
    	boolean isCompletelyHidden=true;
    	for(int k=0;k<p.size();k++)
    	{
    		int next=(k+1)%p.size();
    		Points v1=p.get(k);
    		Points v2=p.get(next);
    		if(v1.y<clipy)
    		{
    			isCompletelyHidden=false;
    		}
    		if(v1.y>v2.y)
    		{
    			Points temp=v1;
    			v1=v2;
    			v2=temp;
    		}
    		if(v1.y<clipy&&v2.y>clipy)
    		{
    			double scale=(clipy-v1.y/(v2.z-v1.y));
    			p.add(next,new Points(v1.x+scale*(v2.x-v1.x),clipy,v1.z+scale*(v2.z-v1.z)));
    		}
    	}
    	if(isCompletelyHidden)
    	{
    		return false;
    	}
    	for(int k=p.size()-1;k>=0;k--){
    		if(p.get(k).y>clipy)
    			p.remove(k);
    	}
    	return (p.size()>=3);
    }
    public boolean clipx(double clipx)
    {
    	boolean isCompletelyHidden=true;
    	for(int k=0;k<p.size();k++)
    	{
    		int next=(k+1)%p.size();
    		Points v1=p.get(k);
    		Points v2=p.get(next);
    		if(v1.x<clipx)
    		{
    			isCompletelyHidden=false;
    		}
    		if(v1.x>v2.x)
    		{
    			Points temp=v1;
    			v1=v2;
    			v2=temp;
    		}
    		if(v1.x<clipx&&v2.x>clipx)
    		{
    			double scale=(clipx-v1.x/(v2.x-v1.x));
    			p.add(next,new Points(clipx,v1.y+scale*(v2.y-v1.y),v1.z+scale*(v2.z-v1.z)));
    		}
    	}
    	if(isCompletelyHidden)
    	{
    		return false;
    	}
    	for(int k=p.size()-1;k>=0;k--){
    		if(p.get(k).x>clipx)
    			p.remove(k);
    	}
    	return (p.size()>=3);
    }    
    public void add(Points p)
    {
    	this.p.add(p);
    	oldp.add(p);
    }
    public void addPoint(Points ps)
    {
    	for(int k=0;k<p.size();k++)
    	{
    		p.get(k).add(ps);
    	}
    }
    public Polygon3d(Points p0,Points p1,Points p2)
    {
    	p.add(p0);
    	p.add(p1);
    	p.add(p2);
    	Polygon3d(p);
    	setColor(new Color(255/2,255,255/2));
    	which=1;
    }
    public void showpoints(boolean t)
    {
    	set(t);
    }
    public Polygon3d(ArrayList<Points> p)
    {
    	Polygon3d(p);
    	setColor(new Color(255/2,255,255/2));
    	which=1;
    }
    private void Polygon3d(ArrayList<Points> p2)
    {
    	this.p=new ArrayList<Points>();
    	for(int k=0;k<p2.size();k++)
    	{
    		this.p.add(new Points(p2.get(k)));
    	}
    	p2=new ArrayList<Points>();
    	for(int k=0;k<p.size();k++)
    	{
    		oldp.add(new Points(p.get(k)));
    	}
    	for(int k=0;k<p.size();k++)
    	{
    		p.get(k).setColor(new Color(255/2,255,255/2));
    	}
    	recalculate();
    	which=1;
    }
    public void setTo(Polygon3d poly)
    {
    	Polygon3d(poly.p);
    	setColor(poly.t);
    	which=poly.which;
    }
    protected void ensureCapacity(int length)
    {
    	if(p.size()<length)
    	{
    		for(int k=0;k<length-p.size();k++)
    		{
    			p.add(new Points());
    		}
    	}
    }
    public void recalculate()
    {
    	clear();
    	for(int k=0;k<p.size();k++)
    	{
    		p.get(k).recalculate();
    		setboundry(p.get(k).x,p.get(k).y,k);
    		setboundry2(p.get(k).getfinalx(),p.get(k).getfinaly(),k);
    	}
    	calcNormal();
    	isfacing=isFacing(new Points(0,0,-panz));
    }
    public void setboundry2(double x,double y,int index)
    {
    	if(x<finalleft)
    	{
    		finalleft=x;
    		finalindexl=index;
    	}
    	if(x-1>finalright)
    	{
    		finalright=x-1;
    		finalindexr=index;
    	}
    	if(y<finaltop)
    	{
    		finaltop=y;
    		finalindext=index;
    	}
    	if(y-1>finalbottom)
    	{
    		finalbottom=y-1;
    		finalindexb=index;
    	}
    }
    public boolean isFacing()
    {
    	return isfacing;
    }
    public void clear()
    {
    	left=Integer.MAX_VALUE;
    	right=Integer.MIN_VALUE;
    	top=Integer.MAX_VALUE;
    	bottom=Integer.MIN_VALUE;
    }
    public boolean upsidedown()
    {
    	if(((indext!=finalindext&&indext!=oldindext)||(indexb!=finalindexb&&indexb!=oldindexb))||(isfacing!=oldisfacing&&upsidedown))
    	{
    		upsidedown=!upsidedown;
    	}
    	if(indexl!=oldindexl)
    	{
    		oldindexl=indexl;
    	}
    	if(indexr!=oldindexr)
    	{
    		oldindexr=indexr;
    	}
    	if(indext!=oldindext)
    	{
    		oldindext=indext;
    	}
    	if(indexb!=oldindexb)
    	{
    		oldindexb=indexb;
    	}
    	if(isfacing!=oldisfacing)
    	{
    		oldisfacing=isfacing;
    	}
    	return upsidedown;
    }
    public void setboundry(double x,double y,int index)
    {
    	if(x<left)
    	{
    		left=x;
    		indexl=index;
    	}
    	if(x-1>right)
    	{
    		right=x-1;
    		indexr=index;
    	}
    	if(y<top)
    	{
    		top=y;
    		indext=index;
    	}
    	if(y-1>bottom)
    	{
    		bottom=y-1;
    		indexb=index;
    	}
    }
    public int getNumVerts()
    {
    	return p.size();
    }
    public Points getVertex(int index)
    {
		return p.get(index);
    }
    public void draw1(Graphics2D g)
    {
		for(int k=0;k<p.size()-1;k++)
    	{
    		g.setColor(t);
    		g.drawLine(p.get(k).getfakex(),p.get(k).getfakey(),p.get(k+1).getfakex(),p.get(k+1).getfakey());
    		if(points)
    		{
    			p.get(k).draw(g);
    		}
    	}
    	g.setColor(t);
    	g.drawLine(p.get(p.size()-1).getfakex(),p.get(p.size()-1).getfakey(),p.get(0).getfakex(),p.get(0).getfakey());
    	if(points)
    	{
    		p.get(p.size()-1).draw(g);
    		p.get(0).draw(g);
    	}
    }
    public void zoomOut()
    {
    	for(int k=0;k<p.size();k++)
    	{
    		p.get(k).zoomOut();
    	}
    }
    public void zoomIn()
    {
    	for(int k=0;k<p.size();k++)
    	{
    		p.get(k).zoomIn();
    	}
    }
    public void drawVerts(Graphics2D g)
    {
    	for(int k=0;k<p.size();k++)
    	{
    		p.get(k).draw(g);
    	}
    }
    public String toString()
    {
    	String temp="";
		if(p.size()>0)
		{
			temp="Polygon3d\n";
		}
    	temp+=showinside();
    	return temp;
    }
    public String showinside()
    {
    	String temp="";
    	for(int k=0;k<p.size();k++)
    	{
    		if(k==0)
    		{
    			temp="{\n";
    		}
    		if(k<p.size()-1)
    		{
    			temp+="\t"+p.get(k)+",\n";
    		}else
    			temp+="\t"+p.get(k)+"\n}\t";
    	}
    	return temp;
    }
}