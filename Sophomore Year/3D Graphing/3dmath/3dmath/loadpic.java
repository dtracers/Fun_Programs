/**
 * loadpic.java  8/24/2008
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

public class loadpic
{
	graph gr=new graph();
	String filename;
	public void draw(Graphics g)
	{
		gr.drawgraph((Graphics2D)g);
	}
	public graph getgraph()
	{
		return gr;
	}
    public loadpic()
    {
    }
    public loadpic(String f)
    {
    	filename=f;
    }
    public void setfilename(String f)
    {
    	filename=f;
    }
    public String getfilename()
    {
    	return filename;
    }
    public void load()
    {
    	/*
    	FileLoader file=new FileLoader();
		BufferedReader b=file.datFile(filename);
		try
		{
			String temp="";
			do
			{
				temp=b.readLine();
			}while(!temp.equals("points")&&!temp.equals("done"));
			do
			{
				try
				{
				temp=b.readLine();
				char t=temp.charAt(0);
				String t2=""+t;
				if(t2.equals("p"))
				{
					String y=temp.substring(1,temp.length());
					String y2[]=y.split(",");
					boolean z0=false;
					boolean o1=false;
					boolean t3=false;
					char tempor=y2[0].charAt(0);
					if(tempor=='-')
					{
						z0=true;
					}
					tempor=y2[1].charAt(0);
					if(tempor=='-')
					{
						o1=true;
					}
					tempor=y2[2].charAt(0);
					if(tempor=='-')
					{
						t3=true;
					}
					int zer;
					int one;
					int two;
					if(z0)
					zer=parse(y2[0].substring(1,y2[0].length()));
					else
					zer=parse(y2[0]);
					if(o1)
					one=parse(y2[1].substring(1,y2[1].length()));
					else
					one=parse(y2[1]);
					if(t3)
					two=parse(y2[2].substring(1,y2[2].length()));
					else
					two=parse(y2[2]);
					if(z0)
					{
						zer*=-1;
					}
					if(o1)
					{
						one*=-1;
					}
					if(t3)
					{
						two*=-1;
					}
					points.add(new Points(zer,one,two));
				}
				}catch(Exception e)
				{
				}
			}while(!temp.equals("faces")&&!temp.equals("done"));
			Polygon3d poly=null;
			if(!temp.equals("done"))
			do
			{
				temp=b.readLine();
				if(temp!=null)
				{
					try
					{
						char t=temp.charAt(0);
						String t2=""+t;
						if(t2.equals("f"))
						{
							String y=temp.substring(1,temp.length());
							String y2[]=y.split(",");
							if(poly!=null)
							{
								polys.add(poly);
							}
							poly=new Polygon3d();
							poly.set(3);
							for(int k=0;k<y2.length;k++)
							{
								poly.add(new Points(points.get(parse(y2[k]))));
							}
						}
						if(t2.equals("p"))
						{
							String y=temp.substring(1,temp.length());
							String y2[]=y.split(",");
							if(poly!=null)
							{
								polys.add(poly);
							}
							poly=new SolidPolygon3d();
							poly.set(3);
							for(int k=0;k<y2.length;k++)
							{
								poly.add(new Points(points.get(parse(y2[k]))));
							}
						}
						if(t2.equals("c"))
						{
							String y=temp.substring(1,temp.length());
							String y2[]=y.split(",");
							poly.setColor(new Color(parse(y2[0]),
							parse(y2[1]),
							parse(y2[2])));
						}
						if(t2.equals("t"))
						{
							String y=temp.substring(1,temp.length());
							poly.set(parse(y));
						}if(temp.equals("add all"))
						{
							for(int k=0;k<polys.size();k++)
							{
								if(polys.get(k) instanceof SolidPolygon3d)
								{
									SolidPolygon3d ary=new SolidPolygon3d();
									ary.setTo(polys.get(k));
									if(ary!=null)
									gr.add(ary);
								}else
								{
									Polygon3d ary=new Polygon3d();
										ary.setTo(polys.get(k));
									if(ary!=null)
									gr.add(ary);
								}
							}
						}else
						if(t2.equals("a"))
						{
							polys.add(poly);
							poly=null;
						}
					}catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}while(!temp.equals("lines")&&!temp.equals("done"));
			if(poly!=null)
			{
				polys.add(poly);
				poly=null;
			}
			Line3d l=null;
			if(!temp.equals("done"))
			do
			{
				temp=b.readLine();
				if(temp!=null)
				{
					try
					{
						char t=temp.charAt(0);
						String t2=""+t;
						if(t2.equals("l"))
						{
							String y=temp.substring(1,temp.length());
							String y2[]=y.split(",");
							if(l!=null)
							{
								gr.add(l);
							}
							l=new Line3d(new Points(points.get(parse(y2[0]))),new Points(points.get(parse(y2[1]))));
						}
						if(t2.equals("a"))
						{
							gr.add(l);
							l=null;
						}
						if(t2.equals("c"))
						{
							String y=temp.substring(1,temp.length());
							String y2[]=y.split(",");
							l.setColor(new Color(parse(y2[0]),
							parse(y2[1]),
							parse(y2[2])));
						}
					}catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}while(!temp.equals("done")&&!temp.equals("hedrals"));
			if(l!=null)
			{
				gr.add(l);
				l=null;
			}
			perfectPolyHedrals polyh=null;
			if(!temp.equals("done"))
			do
			{
				temp=b.readLine();
				if(temp!=null)
				{
					try
					{
						char t=temp.charAt(0);
						String t2=""+t;
						if(t2.equals("h"))
						{
							String y=temp.substring(1,temp.length());
							String y2[]=y.split(",");
							if(polyh!=null)
							{
								polyhs.add(polyh);
							}
							polyh=new perfectPolyHedrals(new Points(points.get(parse(y2[0]))),parse(y2[1]),parse(y2[2]),parse(y2[3]));
						}
						if(t2.equals("t"))
						{
							String y=temp.substring(1,temp.length());
							polyh.set(parse(y));
						}
						if(t2.equals("n"))
						{
							String y=temp.substring(1,temp.length());
							String y2[]=y.split(",");
							Polygon3d temps=polys.get(parse(y2[0]));
							if(temps instanceof SolidPolygon3d)
							{
								SolidPolygon3d temps2=new SolidPolygon3d();
								temps2.setTo((SolidPolygon3d)polys.get(parse(y2[0])));
								polyh.add(temps2);
							}else
							{
								temps=new Polygon3d();
								temps.setTo(polys.get(parse(y2[0])));
								polyh.add(temps);
							}
						}
						if(t2.equals("p"))
						{
							String y=temp.substring(1,temp.length());
							String y2[]=y.split(",");
							PolyHedrals temps=new PolyHedrals();
							temps.setTo(polyhs.get(parse(y2[0])));
							polyh.add(temps);
						}
						if(t2.equals("s"))
						{
							String y=temp.substring(1,temp.length());
							polyh.setStation(y.equals("true"));
						}
						if(temp.equals("add all"))
						{
							for(int k=0;k<polyhs.size();k++)
							{
								PolyHedrals ary=polyhs.get(k);
								if(ary!=null)
								{
									System.out.println("adding");
									System.out.println("\n"+ary);
									gr.add(ary);
								}
							}
						}else
						if(t2.equals("a"))
						{
							polyhs.add(polyh);
							polyh=null;
						}
						if(t2.equals("c"))
						{
							String y=temp.substring(1,temp.length());
							String y2[]=y.split(",");
							polyh.setColor(new Color(parse(y2[0]),
							parse(y2[1]),
							parse(y2[2])));
						}

					}catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}while(!temp.equals("done")&&!temp.equals("last"));
			if(!temp.equals("done"))
			do
			{
				temp=b.readLine();
				if(temp!=null)
				{
					try
					{
						char t=temp.charAt(0);
						String t2=""+t;
						if(t2.equals("h"))
						{
							String y=temp.substring(1,temp.length());
							String y2[]=y.split(",");
							PolyHedrals ary=new PolyHedrals();
							ary.setTo(polyhs.get(parse(y2[0])));
							if(ary!=null)
							{
							//	System.out.println("adding");
								//System.out.println("\n"+ary);
								gr.add(ary);
							}
						}
						if(t2.equals("f"))
						{
							String y=temp.substring(1,temp.length());
							String y2[]=y.split(",");
							Polygon3d ary=new Polygon3d();
							ary.setTo(polys.get(parse(y2[0])));
							if(ary!=null)
							gr.add(ary);
						}
						if(t2.equals("p"))
						{
							String y=temp.substring(1,temp.length());
							String y2[]=y.split(",");
							gr.add(new Points(points.get(parse(y2[0]))));
						}
						String u="add all";
						if(temp.length()>u.length())
						{
							String i=temp.substring(0,u.length());
							System.out.println(i);
							if(i.equals("add all"))
							{
								String y=temp.substring(u.length(),temp.length());
								String y2[]=y.split(",");
								int d=parse(y2[0]);
								if(d==2)
									for(int k=0;k<polyhs.size();k++)
									{
										PolyHedrals ary=new PolyHedrals();
										ary.setTo(polyhs.get(k));
										if(ary!=null)
										{
										//	System.out.println("adding");
											//System.out.println("\n"+ary);
											gr.add(ary);
										}
									}
								else if(d==1)
									for(int k=0;k<polys.size();k++)
									{
										if(polys.get(k) instanceof SolidPolygon3d)
										{
											SolidPolygon3d ary=(SolidPolygon3d)polys.get(k);
											if(ary!=null)
											gr.add(ary);
										}else
										{
										Polygon3d ary=polys.get(k);
										if(ary!=null)
										gr.add(ary);
										}
									}
								else if(d==0)
									for(int k=0;k<polys.size();k++)
									{
										Points ary=new Points(points.get(k));
										if(ary!=null)
										gr.add(ary);
									}
							}
						}
					}catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}while(!temp.equals("done"));
			b.close();
			if(polyh!=null)
			{
				gr.add(polyh);
				polyh=null;
			}
		}catch(IOException e)
		{
		}
		*/
    }
    public void setgraph(graph g)
    {
    	gr=g;
    }
	public int parse(String g)
	{
		return Integer.parseInt(g);
	}
	public graph getgraph(equationset c,double ct,double cm)
    {
    	gr.setformloc(new Points(-100,100,-100));
    	gr.init(c.getX(),"x",c.getY(),"x",c.getZ(),"x",ct,cm);
    	return gr;
    }
}