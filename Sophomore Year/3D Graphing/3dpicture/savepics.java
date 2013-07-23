import static java.lang.System.*;
import java.awt.*;
import static java.awt.Color.*;
import java.awt.event.*;
import java.awt.event.KeyEvent.*;
import java.awt.Event.*;
import java.awt.image.*;
import java.applet.*;
import java.io.*;
import java.net.*;
import java.text.*;
import javax.swing.*;
import java.lang.*;
import java.util.*;
import java.sql.*;
import sun.audio.*;

public class savepics extends JFrame implements KeyListener,EventListener,
						MouseListener,MouseMotionListener,MouseWheelListener
{
	Image dbg,dbg2;
	Graphics g,g2;
	boolean run;
	int sizew=1024;
	int sizeh=760;
	long delay=0,startdelay=0;
	ArrayList<Polygon3D>polygons=new ArrayList<Polygon3D>();
	PolygonRenderer polygonRenderer;
	ViewWindow viewWindow;
	public static void main(String[] args)
    {
    	savepics v=new savepics();
    }
    public savepics()
    {
		frameInit();
		setSize(sizew,sizeh);
      	setBackground(Color.white);
      	setDefaultCloseOperation(EXIT_ON_CLOSE);
      	setVisible(true);
      	addKeyListener(this);
      	addMouseListener(this);
      	addMouseMotionListener(this);
      	addMouseWheelListener(this);
      	dbg = createImage (this.getSize().width, this.getSize().height);
      	dbg2 = createImage (this.getSize().width, this.getSize().height);
		g = dbg.getGraphics ();
		g2 = dbg2.getGraphics ();
		createPolygonRenderer();
    	createPolygons();
		repaint();
    }
    public void init()
    {
    }
    public void createPolygons()
    {

        // create Textures
        Texture wall = loadTexture("wall1.png");
        Texture roof = loadTexture("roof1.png");

        TexturedPolygon3D poly;

        // walls
        poly = new TexturedPolygon3D(
            new Vector3D(-200, 250, -1000),
            new Vector3D(-200, 0, -1000),
            new Vector3D(200, 0, -1000),
            new Vector3D(200, 250, -1000));
        setTexture(poly, wall);
        polygons.add(poly);

        poly = new TexturedPolygon3D(
            new Vector3D(200, 250, -1400),
            new Vector3D(200, 0, -1400),
            new Vector3D(-200, 0, -1400),
            new Vector3D(-200, 250, -1400));
        setTexture(poly, wall);
        polygons.add(poly);

        poly = new TexturedPolygon3D(
            new Vector3D(-200, 250, -1400),
            new Vector3D(-200, 0, -1400),
            new Vector3D(-200, 0, -1000),
            new Vector3D(-200, 250, -1000));
        setTexture(poly, wall);
        polygons.add(poly);

        poly = new TexturedPolygon3D(
            new Vector3D(200, 250, -1000),
            new Vector3D(200, 0, -1000),
            new Vector3D(200, 0, -1400),
            new Vector3D(200, 250, -1400));
        setTexture(poly, wall);
        polygons.add(poly);

        // roof
        poly = new TexturedPolygon3D(
            new Vector3D(-200, 250, -1000),
            new Vector3D(200, 250, -1000),
            new Vector3D(75, 400, -1200),
            new Vector3D(-75, 400, -1200));
        setTexture(poly, roof);
        polygons.add(poly);

        poly = new TexturedPolygon3D(
            new Vector3D(-200, 250, -1400),
            new Vector3D(-200, 250, -1000),
            new Vector3D(-75, 400, -1200));
        setTexture(poly, roof);
        polygons.add(poly);

        poly = new TexturedPolygon3D(
            new Vector3D(200, 250, -1400),
            new Vector3D(-200, 250, -1400),
            new Vector3D(-75, 400, -1200),
            new Vector3D(75, 400, -1200));
        setTexture(poly, roof);
        polygons.add(poly);

        poly = new TexturedPolygon3D(
            new Vector3D(200, 250, -1000),
            new Vector3D(200, 250, -1400),
            new Vector3D(75, 400, -1200));
        setTexture(poly, roof);
        polygons.add(poly);
    }

    public void setTexture(TexturedPolygon3D poly,
        Texture texture)
    {
        Vector3D origin = poly.getVertex(0);

        Vector3D dv = new Vector3D(poly.getVertex(1));
        dv.subtract(origin);

        Vector3D du = new Vector3D();
        du.setToCrossProduct(poly.getNormal(), dv);

        Rectangle3D textureBounds = new Rectangle3D(origin, du, dv,
            texture.getWidth(), texture.getHeight());

        poly.setTexture(texture, textureBounds);
    }

    public Texture loadTexture(String imageName)
    {
        Texture t=Texture.createTexture(imageName);
        System.out.println(t+" object\n");//+t.getName()+" name");
        	return t;
    }
    public void createPolygonRenderer()
    {
        viewWindow = new ViewWindow(0, 0,
            this.getSize().width, this.getSize().height,
            (float)Math.toRadians(75));


        Transform3D camera = new Transform3D(0,100,0);
        polygonRenderer = new FastTexturedPolygonRenderer(
            camera, viewWindow);
    }
    public void paint(Graphics g3)
    {
    	run=true;
    	polygonRenderer.startFrame((Graphics2D)g);
    	for(int k=0;k<polygons.size();k++)
    	{
    		polygonRenderer.draw((Graphics2D)g,polygons.get(k));
    	}
    	polygonRenderer.endFrame((Graphics2D)g);
    	g2.drawImage(dbg,0,0,this);
    	g3.drawImage(dbg2,0,0,this);
    	action1();
    }
    public void update(Graphics g3)
    {
    	paint(g3);
    }
    public void action1()
    {
	    do
	    {
	    	boolean t=test();
	    	if(t)
			{
				action2();
			}
	    }while(run);
    }
    public void action2()
    {
    	repaint();
    	run=false;
    }
    public boolean test()
    {

		long endDelay=System.currentTimeMillis();
		if(startdelay+delay<=endDelay)
		{
			startdelay=System.currentTimeMillis();
			return true;
		}
		else
			return false;
    }
    public void keyPressed(KeyEvent e)
    {
    }
    public void keyReleased(KeyEvent e)
    {
    }
    public void keyTyped(KeyEvent e)
    {
    }
    public void mousePressed(MouseEvent e)
    {
    }
    public void mouseReleased(MouseEvent e)
    {
    }
    public void mouseEntered(MouseEvent e)
    {
    }
    public void mouseExited(MouseEvent e)
    {
    }
    public void mouseClicked(MouseEvent e)
    {
    }
    public void mouseDragged(MouseEvent e)
    {
    }
    public void mouseMoved(MouseEvent e)
    {
    }
    public void mouseWheelMoved(MouseWheelEvent e)
    {
    }
}