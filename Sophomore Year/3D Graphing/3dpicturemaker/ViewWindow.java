
package classes;
import java.awt.Rectangle;

/**
    The ViewWindow class represents the geometry of a view window
    for 3D viewing.
*/

public class ViewWindow {

    private Rectangle bounds;
    private double angle;
    private double distanceToCamera;
    private double panx=0;
    private double panz=0;
    private double pany=0;

    /**
        Creates a new ViewWindow with the specified bounds on the
        screen and horizontal view angle.
    */
    public void setTo(ViewWindow v)
    {
    	angle=v.getAngle();
    	setBounds(v.getLeftOffset(),v.getTopOffset(),v.getWidth(),v.getHeight());
    	panx=v.getx();
    	pany=v.gety();
    	panz=v.getz();
    }
    public ViewWindow(int left, int top, int width, int height,
        double angle)
    {
        bounds = new Rectangle();
        this.angle = angle;
        setBounds(left, top, width, height);
    }
    /**
        Sets the bounds for this ViewWindow on the screen.
    */
    public void setBounds(int left, int top, int width,
        int height)
    {
        bounds.x = left;
        bounds.y = top;
        bounds.width = width;
        bounds.height = height;
        distanceToCamera = (bounds.width/2) /
            (double)Math.tan(angle/2);
    }


    /**
        Sets the horizontal view angle for this ViewWindow.
    */
    public void setAngle(double angle) {
        this.angle = angle;
        distanceToCamera = (bounds.width/2) /
            (double)Math.tan(angle/2);
    }


    /**
        Gets the horizontal view angle of this view window.
    */
    public double getAngle() {
        return angle;
    }


    /**
        Gets the width of this view window.
    */
    public int getWidth() {
        return bounds.width;
    }


    /**
        Gets the height of this view window.
    */
    public int getHeight() {
        return bounds.height;
    }


    /**
        Gets the y offset of this view window on the screen.
    */
    public int getTopOffset() {
        return bounds.y;
    }


    /**
        Gets the x offset of this view window on the screen.
    */
    public int getLeftOffset() {
        return bounds.x;
    }


    /**
        Gets the distance from the camera to to this view window.
    */
    public double getDistance() {
        return distanceToCamera;
    }



    /**
        Converts an x coordinate on this view window to the
        corresponding x coordinate on the screen.
    */
    public double convertFromViewXToScreenX(double x) {
        return x + bounds.x + bounds.width/2;
    }


    /**
        Converts a y coordinate on this view window to the
        corresponding y coordinate on the screen.
    */
    public double convertFromViewYToScreenY(double y) {
        return -y + bounds.y + bounds.height/2;
    }


    /**
        Converts an x coordinate on the screen to the
        corresponding x coordinate on this view window.
    */
    public double convertFromScreenXToViewX(double x) {
        return x - bounds.x - bounds.width/2+panx;
    }


    /**
        Converts an y coordinate on the screen to the
        corresponding y coordinate on this view window.
    */
    public double convertFromScreenYToViewY(double y) {
        return -y + bounds.y + bounds.height/2-pany;
    }


    /**
        Projects the specified vector to the screen.
    */
    public void project(vector3d v)
    {
        // project to view window
        v.x2 = distanceToCamera * v.x / -(v.z+panz);
        v.y2 = distanceToCamera * v.y / -(v.z+panz);

        // convert to screen coordinates
        v.x2 = convertFromViewXToScreenX(v.x2);
        v.y2 = convertFromViewYToScreenY(v.y2);
    }
    public void setz(double z)
	{
		panz=z;
	}
	public double getz()
	{
		return panz;
	}
	public void addz(double z)
	{
		panz+=z;
	}
	public void subtractz(double z)
	{
		addz(-z);
	}
	public void multz(double z)
	{
		addz(panz*(z-1));
	}
	public void dividez(double z)
	{
		panz/=z;
	}
	public void setx(double z)
	{
		panx=z;
	}
	public double getx()
	{
		return panx;
	}
	public void addx(double z)
	{
		panx+=z;
	}
	public void subtractx(double z)
	{
		addx(-z);
	}
	public void multx(double z)
	{
		addx(panx*(z-1));
	}
	public void dividex(double z)
	{
		panx/=z;
	}
	public void sety(double z)
	{
		pany=z;
	}
	public double gety()
	{
		return pany;
	}
	public void addy(double z)
	{
		panz+=z;
	}
	public void subtracty(double z)
	{
		addz(-z);
	}
	public void multy(double z)
	{
		addz(panz*(z-1));
	}
	public void dividey(double z)
	{
		panz/=z;
	}
}

