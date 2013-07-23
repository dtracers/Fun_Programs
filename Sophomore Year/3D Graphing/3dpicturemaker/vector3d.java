/**
 * vector3d.java  8/10/2008
 *
 * @author - Jane Doe
 * @author - Period n
 * @author - Id nnnnnnn
 *
 * @author - I received help from ...
 *
 */package classes;
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

public class vector3d
{
    public double x2;
    public double y2;
	public double x;
    public double y;
    public double z;

    /**
        Creates a new vector3d at (0,0,0).
    */
    public vector3d() {
        this(0,0,0);
    }


    /**
        Creates a new vector3d with the same values as the
        specified vector3d.
    */
    public vector3d(vector3d v) {
        this(v.x, v.y, v.z);
    }


    /**
        Creates a new vector3d with the specified (x, y, z) values.
    */
    public vector3d(double x, double y, double z) {
        setTo(x, y, z);
    }


    /**
        Checks if this vector3d is equal to the specified Object.
        They are equal only if the specified Object is a vector3d
        and the two vector3d's x, y, and z coordinates are equal.
    */
    public boolean equals(Object obj) {
        vector3d v = (vector3d)obj;
        return (v.x == x && v.y == y && v.z == z);
    }


    /**
        Checks if this vector3d is equal to the specified
        x, y, and z coordinates.
    */
    public boolean equals(double x, double y, double z) {
        return (this.x == x && this.y == y && this.z == z);
    }


    /**
        Sets the vector to the same values as the specified
        vector3d.
    */
    public void setTo(vector3d v) {
        setTo(v.x, v.y, v.z);
    }


    /**
        Sets this vector to the specified (x, y, z) values.
    */
    public void setTo(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }


    /**
        Adds the specified (x, y, z) values to this vector.
    */
    public void add(double x, double y, double z) {
        this.x+=x;
        this.y+=y;
        this.z+=z;
    }


    /**
        Subtracts the specified (x, y, z) values to this vector.
    */
    public void subtract(double x, double y, double z) {
        add(-x, -y, -z);
    }


    /**
        Adds the specified vector to this vector.
    */
    public void add(vector3d v) {
        add(v.x, v.y, v.z);
    }


    /**
        Subtracts the specified vector from this vector.
    */
    public void subtract(vector3d v) {
        add(-v.x, -v.y, -v.z);
    }


    /**
        Multiplies this vector by the specified value. The new
        length of this vector will be length()*s.
    */
    public void multiply(double s) {
       x*=s;
       y*=s;
       z*=s;
    }


    /**
        Divides this vector by the specified value. The new
        length of this vector will be length()/s.
    */
    public void divide(double s) {
       x/=s;
       y/=s;
       z/=s;
    }


    /**
        Returns the length of this vector as a double.
    */
    public double length() {
        return (double)Math.sqrt(x*x + y*y + z*z);
    }


    /**
        Converts this vector3d to a unit vector, or in other
        words, a vector of length 1. Same as calling
        v.divide(v.length()).
    */
    public void normalize() {
        divide(length());
    }


    /**
        Converts this vector3d to a String representation.
    */
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }


    /**
        Rotate this vector around the x axis the specified amount.
        The specified angle is in radians. Use Math.toRadians() to
        convert from degrees to radians.
    */
    public void rotateX(double angle) {
        rotateX((double)Math.cos(angle), (double)Math.sin(angle));
    }


    /**
        Rotate this vector around the y axis the specified amount.
        The specified angle is in radians. Use Math.toRadians() to
        convert from degrees to radians.
    */
    public void rotateY(double angle) {
        rotateY((double)Math.cos(angle), (double)Math.sin(angle));
    }


    /**
        Rotate this vector around the z axis the specified amount.
        The specified angle is in radians. Use Math.toRadians() to
        convert from degrees to radians.
    */
    public void rotateZ(double angle) {
        rotateZ((double)Math.cos(angle), (double)Math.sin(angle));
    }


    /**
        Rotate this vector around the x axis the specified amount,
        using pre-computed cosine and sine values of the angle to
        rotate.
    */
    public void rotateX(double cosAngle, double sinAngle) {
        double newY = y*cosAngle - z*sinAngle;
        double newZ = y*sinAngle + z*cosAngle;
        y = newY;
        z = newZ;
    }


    /**
        Rotate this vector around the y axis the specified amount,
        using pre-computed cosine and sine values of the angle to
        rotate.
    */
    public void rotateY(double cosAngle, double sinAngle) {
        double newX = z*sinAngle + x*cosAngle;
        double newZ = z*cosAngle - x*sinAngle;
        x = newX;
        z = newZ;
    }


    /**
        Rotate this vector around the y axis the specified amount,
        using pre-computed cosine and sine values of the angle to
        rotate.
    */
    public void rotateZ(double cosAngle, double sinAngle) {
        double newX = x*cosAngle - y*sinAngle;
        double newY = x*sinAngle + y*cosAngle;
        x = newX;
        y = newY;
    }


    /**
        Adds the specified transform to this vector. This vector
        is first rotated, then translated.
    */
    public void add(Transform3D xform) {

        // rotate
        addRotation(xform);

        // translate
        add(xform.getLocation());
    }


    /**
        Subtracts the specified transform to this vector. This
        vector translated, then rotated.
    */
    public void subtract(Transform3D xform) {

        // translate
        subtract(xform.getLocation());

        // rotate
        subtractRotation(xform);
    }


    /**
        Rotates this vector with the angle of the specified
        transform.
    */
    public void addRotation(Transform3D xform) {
        rotateX(xform.getCosAngleX(), xform.getSinAngleX());
        rotateZ(xform.getCosAngleZ(), xform.getSinAngleZ());
        rotateY(xform.getCosAngleY(), xform.getSinAngleY());
    }


    /**
        Rotates this vector with the opposite angle of the
        specified transform.
    */
    public void subtractRotation(Transform3D xform) {
        // note that sin(-x) == -sin(x) and cos(-x) == cos(x)
        rotateY(xform.getCosAngleY(), -xform.getSinAngleY());
        rotateZ(xform.getCosAngleZ(), -xform.getSinAngleZ());
        rotateX(xform.getCosAngleX(), -xform.getSinAngleX());
    }


    /**
        Returns the dot product of this vector and the specified
        vector.
    */
    public double getDotProduct(vector3d v) {
        return x*v.x + y*v.y + z*v.z;
    }


    /**
        Sets this vector to the cross product of the two
        specified vectors. Either of the specified vectors can
        be this vector.
    */
    public void setToCrossProduct(vector3d u, vector3d v) {
        // assign to local vars first in case u or v is 'this'
        double x = u.y * v.z - u.z * v.y;
        double y = u.z * v.x - u.x * v.z;
        double z = u.x * v.y - u.y * v.x;
        this.x = x;
        this.y = y;
        this.z = z;
    }


    /**
        Gets the distance squared between this vector and the
        specified vector.
    */
    public double getDistanceSq(vector3d v) {
        double dx = v.x - x;
        double dy = v.y - y;
        double dz = v.z - z;
        return dx*dx + dy*dy + dz*dz;
    }


    /**
        Gets the distance between this vector and the
        specified vector.
    */
    public double getDistance(vector3d v) {
        return (double)Math.sqrt(getDistanceSq(v));
    }


    /**
        Sets the length of this vector3d
    */
    public void setLength(double newLength) {
        normalize();
        multiply(newLength);
    }
}