

/**
    The Transform3D class represents a rotation and translation.
*/
package classes;
public class Transform3D {

    protected vector3d location;
    private double cosAngleX;
    private double sinAngleX;
    private double cosAngleY;
    private double sinAngleY;
    private double cosAngleZ;
    private double sinAngleZ;

    /**
        Creates a new Transform3D with no translation or rotation.
    */
    public Transform3D() {
        this(0,0,0);
    }


    /**
        Creates a new Transform3D with the specified translation
        and no rotation.
    */
    public Transform3D(double x, double y, double z) {
        location = new vector3d(x, y, z);
        setAngle(0,0,0);
    }


    /**
        Creates a new Transform3D
    */
    public Transform3D(Transform3D v) {
        location = new vector3d();
        setTo(v);
    }


    public Object clone() {
        return new Transform3D(this);
    }


    /**
        Sets this Transform3D to the specified Transform3D.
    */
    public void setTo(Transform3D v) {
        location.setTo(v.location);
        this.cosAngleX = v.cosAngleX;
        this.sinAngleX = v.sinAngleX;
        this.cosAngleY = v.cosAngleY;
        this.sinAngleY = v.sinAngleY;
        this.cosAngleZ = v.cosAngleZ;
        this.sinAngleZ = v.sinAngleZ;
    }


    /**
        Gets the location (translation) of this transform.
    */
    public vector3d getLocation() {
        return location;
    }

    public double getCosAngleX() {
        return cosAngleX;
    }

    public double getSinAngleX() {
        return sinAngleX;
    }

    public double getCosAngleY() {
        return cosAngleY;
    }

    public double getSinAngleY() {
        return sinAngleY;
    }

    public double getCosAngleZ() {
        return cosAngleZ;
    }

    public double getSinAngleZ() {
        return sinAngleZ;
    }

    public double getAngleX() {
        return (double)Math.atan2(sinAngleX, cosAngleX);
    }

    public double getAngleY() {
        return (double)Math.atan2(sinAngleY, cosAngleY);
    }

    public double getAngleZ() {
        return (double)Math.atan2(sinAngleZ, cosAngleZ);
    }

    public void setAngleX(double angleX) {
        cosAngleX = (double)Math.cos(angleX);
        sinAngleX = (double)Math.sin(angleX);
    }

    public void setAngleY(double angleY) {
        cosAngleY = (double)Math.cos(angleY);
        sinAngleY = (double)Math.sin(angleY);
    }

    public void setAngleZ(double angleZ) {
        cosAngleZ = (double)Math.cos(angleZ);
        sinAngleZ = (double)Math.sin(angleZ);
    }

    public void setAngle(double angleX, double angleY, double angleZ)
    {
        setAngleX(angleX);
        setAngleY(angleY);
        setAngleZ(angleZ);
    }

    public void rotateAngleX(double angle) {
        if (angle != 0) {
            setAngleX(getAngleX() + angle);
        }
    }

    public void rotateAngleY(double angle) {
        if (angle != 0) {
            setAngleY(getAngleY() + angle);
        }
    }

    public void rotateAngleZ(double angle) {
        if (angle != 0) {
            setAngleZ(getAngleZ() + angle);
        }
    }

    public void rotateAngle(double angleX, double angleY,
        double angleZ)
    {
        rotateAngleX(angleX);
        rotateAngleY(angleY);
        rotateAngleZ(angleZ);
    }

}

