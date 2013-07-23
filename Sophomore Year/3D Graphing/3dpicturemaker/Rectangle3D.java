

/**
    A Rectangle3D is a rectangle in 3D space, defined as an origin
    and vectors pointing in the directions of the base (width) and
    side (height).
*/
package classes;
public class Rectangle3D{

    private vector3d origin;
    private vector3d directionU;
    private vector3d directionV;
    private vector3d normal;
    private double width;
    private double height;

    /**
        Creates a rectangle at the origin with a width and height
        of zero.
    */
    public Rectangle3D() {
        origin = new vector3d();
        directionU = new vector3d(1,0,0);
        directionV = new vector3d(0,1,0);
        width = 0;
        height = 0;
    }


    /**
        Creates a new Rectangle3D with the specified origin,
        direction of the base (directionU) and direction of
        the side (directionV).
    */
    public Rectangle3D(vector3d origin, vector3d directionU,
        vector3d directionV, double width, double height)
    {
        this.origin = new vector3d(origin);
        this.directionU = new vector3d(directionU);
        this.directionU.normalize();
        this.directionV = new vector3d(directionV);
        this.directionV.normalize();
        this.width = width;
        this.height = height;
    }


    /**
        Sets the values of this Rectangle3D to the specified
        Rectangle3D.
    */
    public void setTo(Rectangle3D rect) {
        origin.setTo(rect.origin);
        directionU.setTo(rect.directionU);
        directionV.setTo(rect.directionV);
        width = rect.width;
        height = rect.height;
    }


    /**
        Gets the origin of this Rectangle3D.
    */
    public vector3d getOrigin() {
        return origin;
    }


    /**
        Gets the direction of the base of this Rectangle3D.
    */
    public vector3d getDirectionU() {
        return directionU;
    }


    /**
        Gets the direction of the side of this Rectangle3D.
    */
    public vector3d getDirectionV() {
        return directionV;
    }


    /**
        Gets the width of this Rectangle3D.
    */
    public double getWidth() {
        return width;
    }


    /**
        Sets the width of this Rectangle3D.
    */
    public void setWidth(double width) {
        this.width = width;
    }


    /**
        Gets the height of this Rectangle3D.
    */
    public double getHeight() {
        return height;
    }


    /**
        Sets the height of this Rectangle3D.
    */
    public void setHeight(double height) {
        this.height = height;
    }


    /**
        Calculates the normal vector of this Rectange3D.
    */
    protected vector3d calcNormal() {
        if (normal == null) {
            normal = new vector3d();
        }
        normal.setToCrossProduct(directionU, directionV);
        normal.normalize();
        return normal;
    }


    /**
        Gets the normal of this Rectangle3D.
    */
    public vector3d getNormal() {
        if (normal == null) {
            calcNormal();
        }
        return normal;
    }


    /**
        Sets the normal of this Rectangle3D.
    */
    public void setNormal(vector3d n) {
        if (normal == null) {
            normal = new vector3d(n);
        }
        else {
            normal.setTo(n);
        }
    }


    public void add(vector3d u) {
        origin.add(u);
        // don't translate direction vectors or size
    }

    public void subtract(vector3d u) {
        origin.subtract(u);
        // don't translate direction vectors or size
    }

    public void add(Transform3D xform) {
        addRotation(xform);
        add(xform.getLocation());
    }

    public void subtract(Transform3D xform) {
        subtract(xform.getLocation());
        subtractRotation(xform);
    }

    public void addRotation(Transform3D xform) {
        origin.addRotation(xform);
        directionU.addRotation(xform);
        directionV.addRotation(xform);
    }

    public void subtractRotation(Transform3D xform) {
        origin.subtractRotation(xform);
        directionU.subtractRotation(xform);
        directionV.subtractRotation(xform);
    }

}
