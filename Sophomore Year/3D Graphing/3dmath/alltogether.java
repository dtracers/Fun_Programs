
package edu.cmu.cs.stage3.math;
import javax.vecmath.Tuple3d;
import javax.vecmath.Vector3d;
import javax.vecmath.*;
import javax.vecmath.*;
// Referenced classes of package edu.cmu.cs.stage3.math:
//            Measurement

class Angle extends Measurement
{

    public Angle(double value, double factor)
    {
        super(value, factor);
    }

    public static final double REVOLUTIONS = 1D;
    public static final double RADIANS = 0.15915494309189535D;
    public static final double DEGREES = 0.0027777777777777779D;
}

//package edu.cmu.cs.stage3.math;



// Referenced classes of package edu.cmu.cs.stage3.math:
//            MathUtilities, Quaternion, EulerAngles, Matrix33

class AxisAngle
    implements Cloneable
{

    public AxisAngle()
    {
        m_axis = MathUtilities.createZAxis();
        m_angle = 0.0D;
    }

    public AxisAngle(double x, double y, double z, double angle)
    {
        this(new Vector3d(x, y, z), angle);
    }

    public AxisAngle(double axis[], double angle)
    {
        this(new Vector3d(axis), angle);
    }

    public AxisAngle(double array[])
    {
        this(array[0], array[1], array[2], array[3]);
    }

    public AxisAngle(Vector3d axis, double angle)
    {
        m_axis = MathUtilities.createZAxis();
        m_angle = 0.0D;
        m_axis = axis;
        m_angle = angle;
    }

    public AxisAngle(Matrix33 m)
    {
        m_axis = MathUtilities.createZAxis();
        m_angle = 0.0D;
        setMatrix33(m);
    }

    public AxisAngle(Quaternion q)
    {
        m_axis = MathUtilities.createZAxis();
        m_angle = 0.0D;
        setQuaternion(q);
    }

    public AxisAngle(EulerAngles ea)
    {
        m_axis = MathUtilities.createZAxis();
        m_angle = 0.0D;
        setEulerAngles(ea);
    }

    public double getAngle()
    {
        return m_angle;
    }

    public void setAngle(double angle)
    {
        m_angle = angle;
    }

    public Vector3d getAxis()
    {
        if(m_axis != null)
            return (Vector3d)m_axis.clone();
        else
            return null;
    }

    public void setAxis(Vector3d axis)
    {
        m_axis = axis;
    }

    public synchronized Object clone()
    {
        try
        {
            AxisAngle axisAngle = (AxisAngle)super.clone();
            axisAngle.setAxis((Vector3d)m_axis.clone());
            return axisAngle;
        }
        catch(CloneNotSupportedException e)
        {
            throw new InternalError();
        }
    }

    public boolean equals(Object o)
    {
        if(o == this)
            return true;
        if(o != null && (o instanceof AxisAngle))
        {
            AxisAngle aa = (AxisAngle)o;
            return m_axis.equals(aa.m_axis) && m_angle == aa.m_angle;
        } else
        {
            return false;
        }
    }

    public double[] getArray()
    {
        double a[] = {
            ((Tuple3d) (m_axis)).x, ((Tuple3d) (m_axis)).y, ((Tuple3d) (m_axis)).z, m_angle
        };
        return a;
    }

    public void setArray(double a[])
    {
        m_axis.x = a[0];
        m_axis.y = a[1];
        m_axis.z = a[2];
        m_angle = a[3];
    }

    public Quaternion getQuaternion()
    {
        return new Quaternion(this);
    }

    public void setQuaternion(Quaternion q)
    {
        m_angle = 2D * Math.acos(q.w);
        m_axis.x = 2D * Math.asin(q.x);
        m_axis.y = 2D * Math.asin(q.y);
        m_axis.z = 2D * Math.asin(q.z);
    }

    public EulerAngles getEulerAngles()
    {
        return new EulerAngles(this);
    }

    public void setEulerAngles(EulerAngles ea)
    {
        setQuaternion(ea.getQuaternion());
    }

    public Matrix33 getMatrix33()
    {
        return new Matrix33(this);
    }

    public void setMatrix33(Matrix33 m)
    {
        setQuaternion(m.getQuaternion());
    }

    public String toString()
    {
        return "edu.cmu.cs.stage3.math.AxisAngle[axis.x=" + ((Tuple3d) (m_axis)).x + ",axis.y=" + ((Tuple3d) (m_axis)).y + ",axis.z=" + ((Tuple3d) (m_axis)).z + ",angle=" + m_angle + "]";
    }

    public static AxisAngle valueOf(String s)
    {
        String markers[] = {
            "edu.cmu.cs.stage3.math.AxisAngle[axis.x=", ",axis.y=", ",axis.z=", ",angle=", "]"
        };
        double values[] = new double[markers.length - 1];
        for(int i = 0; i < values.length; i++)
        {
            int begin = s.indexOf(markers[i]) + markers[i].length();
            int end = s.indexOf(markers[i + 1]);
            values[i] = Double.valueOf(s.substring(begin, end)).doubleValue();
        }

        return new AxisAngle(values);
    }

    protected Vector3d m_axis;
    protected double m_angle;
}

//package edu.cmu.cs.stage3.math;



// Referenced classes of package edu.cmu.cs.stage3.math:
//            Cubic

abstract class BasisMatrixCubic
    implements Cubic
{

    protected BasisMatrixCubic(Matrix4d h, Vector4d g)
    {
        m_h = h;
        m_g = g;
    }

    public double evaluate(double t)
    {
        double ttt = t * t * t;
        double tt = t * t;
        return (ttt * m_h.m00 + tt * m_h.m10 + t * m_h.m20 + m_h.m30) * ((Tuple4d) (m_g)).x + (ttt * m_h.m01 + tt * m_h.m11 + t * m_h.m21 + m_h.m31) * ((Tuple4d) (m_g)).y + (ttt * m_h.m02 + tt * m_h.m12 + t * m_h.m22 + m_h.m32) * ((Tuple4d) (m_g)).z + (ttt * m_h.m03 + tt * m_h.m13 + t * m_h.m23 + m_h.m33) * ((Tuple4d) (m_g)).w;
    }

    public double evaluateDerivative(double t)
    {
        double tt3 = t * t * 3D;
        double t2 = t * 2D;
        return (tt3 * m_h.m00 + t2 * m_h.m10 + m_h.m20) * ((Tuple4d) (m_g)).x + (tt3 * m_h.m01 + t2 * m_h.m11 + m_h.m21) * ((Tuple4d) (m_g)).y + (tt3 * m_h.m02 + t2 * m_h.m12 + m_h.m22) * ((Tuple4d) (m_g)).z + (tt3 * m_h.m03 + t2 * m_h.m13 + m_h.m23) * ((Tuple4d) (m_g)).w;
    }

    private Matrix4d m_h;
    private Vector4d m_g;
}

//package edu.cmu.cs.stage3.math;



// Referenced classes of package edu.cmu.cs.stage3.math:
//            Quadratic

abstract class BasisMatrixQuadratic
    implements Quadratic
{

    protected BasisMatrixQuadratic(Matrix3d h, Vector3d g)
    {
        m_h = h;
        m_g = g;
    }

    public double evaluate(double t)
    {
        double tt = t * t;
        return (tt * m_h.m00 + t * m_h.m10 + m_h.m20) * ((Tuple3d) (m_g)).x + (tt * m_h.m01 + t * m_h.m11 + m_h.m21) * ((Tuple3d) (m_g)).y + (tt * m_h.m02 + t * m_h.m12 + m_h.m22) * ((Tuple3d) (m_g)).z;
    }

    public double evaluateDerivative(double t)
    {
        double t2 = t * 2D;
        return (t2 * m_h.m00 + m_h.m10) * ((Tuple3d) (m_g)).x + (t2 * m_h.m01 + m_h.m11) * ((Tuple3d) (m_g)).y + (t2 * m_h.m02 + m_h.m12) * ((Tuple3d) (m_g)).z;
    }

    private Matrix3d m_h;
    protected Vector3d m_g;
}

package edu.cmu.cs.stage3.math;

import javax.vecmath.Matrix4d;
import javax.vecmath.Vector4d;

// Referenced classes of package edu.cmu.cs.stage3.math:
//            BasisMatrixCubic

class BezierCubic extends BasisMatrixCubic
{

    public BezierCubic(Vector4d g)
    {
        super(s_h, g);
    }

    public BezierCubic(double g0, double g1, double g2, double g3)
    {
        this(new Vector4d(g0, g1, g2, g3));
    }

    private static final Matrix4d s_h = new Matrix4d(-1D, 3D, -3D, 1.0D, 3D, -6D, 3D, 0.0D, -3D, 3D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D);

}

package edu.cmu.cs.stage3.math;

import javax.vecmath.Matrix3d;
import javax.vecmath.Vector3d;

// Referenced classes of package edu.cmu.cs.stage3.math:
//            BasisMatrixQuadratic

class BezierQuadratic extends BasisMatrixQuadratic
{

    public BezierQuadratic(Vector3d g)
    {
        super(s_h, g);
    }

    public BezierQuadratic(double g0, double g1, double g2)
    {
        this(new Vector3d(g0, g1, g2));
    }

    private static final Matrix3d s_h = new Matrix3d(1.0D, -2D, 1.0D, -2D, 2D, 0.0D, 1.0D, 0.0D, 0.0D);

}

package edu.cmu.cs.stage3.math;

import javax.vecmath.*;

// Referenced classes of package edu.cmu.cs.stage3.math:
//            MathUtilities

class Box
    implements Cloneable
{

    public Box()
    {
        m_minimum = null;
        m_maximum = null;
    }

    public Box(Vector3d minimum, Vector3d maximum)
    {
        m_minimum = null;
        m_maximum = null;
        setMinimum(minimum);
        setMaximum(maximum);
    }

    public Box(double minimumX, double minimumY, double minimumZ, double maximumX, double maximumY, double maximumZ)
    {
        m_minimum = null;
        m_maximum = null;
        setMinimum(new Vector3d(minimumX, minimumY, minimumZ));
        setMaximum(new Vector3d(maximumX, maximumY, maximumZ));
    }

    public synchronized Object clone()
    {
        try
        {
            Box box = (Box)super.clone();
            box.setMinimum(m_minimum);
            box.setMaximum(m_maximum);
            return box;
        }
        catch(CloneNotSupportedException e)
        {
            throw new InternalError();
        }
    }

    public boolean equals(Object o)
    {
        if(o == this)
            return true;
        if(o != null && (o instanceof Box))
        {
            Box box = (Box)o;
            return m_minimum.equals(box.m_minimum) && m_maximum.equals(box.m_maximum);
        } else
        {
            return false;
        }
    }

    public Vector3d[] getCorners()
    {
        Vector3d corners[] = new Vector3d[8];
        corners[0] = new Vector3d(((Tuple3d) (m_minimum)).x, ((Tuple3d) (m_minimum)).y, ((Tuple3d) (m_minimum)).z);
        corners[1] = new Vector3d(((Tuple3d) (m_minimum)).x, ((Tuple3d) (m_minimum)).y, ((Tuple3d) (m_maximum)).z);
        corners[2] = new Vector3d(((Tuple3d) (m_minimum)).x, ((Tuple3d) (m_maximum)).y, ((Tuple3d) (m_minimum)).z);
        corners[3] = new Vector3d(((Tuple3d) (m_minimum)).x, ((Tuple3d) (m_maximum)).y, ((Tuple3d) (m_maximum)).z);
        corners[4] = new Vector3d(((Tuple3d) (m_maximum)).x, ((Tuple3d) (m_minimum)).y, ((Tuple3d) (m_minimum)).z);
        corners[5] = new Vector3d(((Tuple3d) (m_maximum)).x, ((Tuple3d) (m_minimum)).y, ((Tuple3d) (m_maximum)).z);
        corners[6] = new Vector3d(((Tuple3d) (m_maximum)).x, ((Tuple3d) (m_maximum)).y, ((Tuple3d) (m_minimum)).z);
        corners[7] = new Vector3d(((Tuple3d) (m_maximum)).x, ((Tuple3d) (m_maximum)).y, ((Tuple3d) (m_maximum)).z);
        return corners;
    }

    public Vector3d getMinimum()
    {
        if(m_minimum != null)
            return new Vector3d(m_minimum);
        else
            return null;
    }

    public void setMinimum(Vector3d minimum)
    {
        if(minimum != null)
            m_minimum = new Vector3d(minimum);
        else
            m_minimum = null;
    }

    public Vector3d getMaximum()
    {
        if(m_maximum != null)
            return new Vector3d(m_maximum);
        else
            return null;
    }

    public void setMaximum(Vector3d maximum)
    {
        if(maximum != null)
            m_maximum = new Vector3d(maximum);
        else
            m_maximum = null;
    }

    public Vector3d getCenter()
    {
        if(m_minimum != null && m_maximum != null)
            return new Vector3d((((Tuple3d) (m_minimum)).x + ((Tuple3d) (m_maximum)).x) / 2D, (((Tuple3d) (m_minimum)).y + ((Tuple3d) (m_maximum)).y) / 2D, (((Tuple3d) (m_minimum)).z + ((Tuple3d) (m_maximum)).z) / 2D);
        else
            return null;
    }

    public Vector3d getCenterOfFrontFace()
    {
        if(m_minimum != null && m_maximum != null)
            return new Vector3d((((Tuple3d) (m_minimum)).x + ((Tuple3d) (m_maximum)).x) / 2D, (((Tuple3d) (m_minimum)).y + ((Tuple3d) (m_maximum)).y) / 2D, ((Tuple3d) (m_maximum)).z);
        else
            return null;
    }

    public Vector3d getCenterOfBackFace()
    {
        if(m_minimum != null && m_maximum != null)
            return new Vector3d((((Tuple3d) (m_minimum)).x + ((Tuple3d) (m_maximum)).x) / 2D, (((Tuple3d) (m_minimum)).y + ((Tuple3d) (m_maximum)).y) / 2D, ((Tuple3d) (m_minimum)).z);
        else
            return null;
    }

    public Vector3d getCenterOfLeftFace()
    {
        if(m_minimum != null && m_maximum != null)
            return new Vector3d(((Tuple3d) (m_minimum)).x, (((Tuple3d) (m_minimum)).y + ((Tuple3d) (m_maximum)).y) / 2D, (((Tuple3d) (m_minimum)).z + ((Tuple3d) (m_maximum)).z) / 2D);
        else
            return null;
    }

    public Vector3d getCenterOfRightFace()
    {
        if(m_minimum != null && m_maximum != null)
            return new Vector3d(((Tuple3d) (m_maximum)).x, (((Tuple3d) (m_minimum)).y + ((Tuple3d) (m_maximum)).y) / 2D, (((Tuple3d) (m_minimum)).z + ((Tuple3d) (m_maximum)).z) / 2D);
        else
            return null;
    }

    public Vector3d getCenterOfTopFace()
    {
        if(m_minimum != null && m_maximum != null)
            return new Vector3d((((Tuple3d) (m_minimum)).x + ((Tuple3d) (m_maximum)).x) / 2D, ((Tuple3d) (m_maximum)).y, (((Tuple3d) (m_minimum)).z + ((Tuple3d) (m_maximum)).z) / 2D);
        else
            return null;
    }

    public Vector3d getCenterOfBottomFace()
    {
        if(m_minimum != null && m_maximum != null)
            return new Vector3d((((Tuple3d) (m_minimum)).x + ((Tuple3d) (m_maximum)).x) / 2D, ((Tuple3d) (m_minimum)).y, (((Tuple3d) (m_minimum)).z + ((Tuple3d) (m_maximum)).z) / 2D);
        else
            return null;
    }

    public double getWidth()
    {
        if(m_minimum != null && m_maximum != null)
            return ((Tuple3d) (m_maximum)).x - ((Tuple3d) (m_minimum)).x;
        else
            return 0.0D;
    }

    public double getHeight()
    {
        if(m_minimum != null && m_maximum != null)
            return ((Tuple3d) (m_maximum)).y - ((Tuple3d) (m_minimum)).y;
        else
            return 0.0D;
    }

    public double getDepth()
    {
        if(m_minimum != null && m_maximum != null)
            return ((Tuple3d) (m_maximum)).z - ((Tuple3d) (m_minimum)).z;
        else
            return 0.0D;
    }

    public void union(Box b)
    {
        if(b != null)
        {
            if(b.m_minimum != null)
                if(m_minimum != null)
                {
                    m_minimum.x = Math.min(((Tuple3d) (m_minimum)).x, ((Tuple3d) (b.m_minimum)).x);
                    m_minimum.y = Math.min(((Tuple3d) (m_minimum)).y, ((Tuple3d) (b.m_minimum)).y);
                    m_minimum.z = Math.min(((Tuple3d) (m_minimum)).z, ((Tuple3d) (b.m_minimum)).z);
                } else
                {
                    m_minimum = new Vector3d(b.m_minimum);
                }
            if(b.m_maximum != null)
                if(m_maximum != null)
                {
                    m_maximum.x = Math.max(((Tuple3d) (m_maximum)).x, ((Tuple3d) (b.m_maximum)).x);
                    m_maximum.y = Math.max(((Tuple3d) (m_maximum)).y, ((Tuple3d) (b.m_maximum)).y);
                    m_maximum.z = Math.max(((Tuple3d) (m_maximum)).z, ((Tuple3d) (b.m_maximum)).z);
                } else
                {
                    m_maximum = new Vector3d(b.m_maximum);
                }
        }
    }

    public void transform(Matrix4d m)
    {
        if(m_minimum != null && m_maximum != null)
        {
            m_minimum = MathUtilities.createVector3d(MathUtilities.multiply(m_minimum, 1.0D, m));
            m_maximum = MathUtilities.createVector3d(MathUtilities.multiply(m_maximum, 1.0D, m));
        }
    }

    public void scale(Matrix3d s)
    {
        if(s != null)
        {
            if(m_minimum != null)
                s.transform(m_minimum);
            if(m_maximum != null)
                s.transform(m_maximum);
        }
    }

    public String toString()
    {
        return "edu.cmu.cs.stage3.math.Box[minimum=" + m_minimum + ",maximum=" + m_maximum + "]";
    }

    protected Vector3d m_minimum;
    protected Vector3d m_maximum;
}

package edu.cmu.cs.stage3.math;

import javax.vecmath.Matrix4d;
import javax.vecmath.Vector4d;

// Referenced classes of package edu.cmu.cs.stage3.math:
//            BasisMatrixCubic

class CatmullRomCubic extends BasisMatrixCubic
{

    public CatmullRomCubic(Vector4d g)
    {
        super(s_h, g);
    }

    public CatmullRomCubic(double g0, double g1, double g2, double g3)
    {
        this(new Vector4d(g0, g1, g2, g3));
    }

    private static final Matrix4d s_h = new Matrix4d(-0.5D, 1.5D, -1.5D, 0.5D, 1.0D, -2.5D, 2D, -0.5D, -0.5D, 0.0D, 0.5D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D);

}

package edu.cmu.cs.stage3.math;

import java.awt.Color;
import javax.vecmath.Matrix4d;

// Referenced classes of package edu.cmu.cs.stage3.math:
//            Matrix44, Interpolable

class Interpolator
{

    public Interpolator()
    {
    }

    public static int interpolate(int a, int b, double portion)
    {
        return a + (int)((double)(b - a) * portion);
    }

    public static double interpolate(double a, double b, double portion)
    {
        return a + (b - a) * portion;
    }

    public static Number interpolate(Number a, Number b, double portion)
    {
        return new Double(interpolate(a.doubleValue(), b.doubleValue(), portion));
    }

    public static double[] interpolate(double a[], double b[], double portion)
    {
        if(a.length != b.length)
            return null;
        double v[] = new double[a.length];
        for(int i = 0; i < a.length; i++)
            v[i] = interpolate(a[i], b[i], portion);

        return v;
    }

    public static double[][] interpolate(double a[][], double b[][], double portion)
    {
        if(a.length != b.length)
            return null;
        double m[][] = new double[a.length][];
        for(int i = 0; i < a.length; i++)
        {
            if(a[i].length != b[i].length)
                return null;
            m[i] = new double[a[i].length];
            for(int j = 0; j < a[i].length; j++)
                m[i][j] = interpolate(a[i][j], b[i][j], portion);

        }

        return m;
    }

    public static Color interpolate(Color a, Color b, double portion)
    {
        return new Color(Math.max(0, Math.min(interpolate(a.getRed(), b.getRed(), portion), 255)), Math.max(0, Math.min(interpolate(a.getGreen(), b.getGreen(), portion), 255)), Math.max(0, Math.min(interpolate(a.getBlue(), b.getBlue(), portion), 255)));
    }

    public static Object interpolate(Object a, Object b, double portion)
    {
        if(a instanceof Matrix4d)
        {
            Interpolable ai;
            if(a instanceof Matrix44)
                ai = (Matrix44)a;
            else
                ai = new Matrix44((Matrix4d)a);
            Interpolable bi;
            if(b instanceof Matrix44)
                bi = (Matrix44)b;
            else
                bi = new Matrix44((Matrix4d)b);
            return ai.interpolate(bi, portion);
        }
        if(a instanceof Interpolable)
            return ((Interpolable)a).interpolate((Interpolable)b, portion);
        if(a instanceof Number)
            return interpolate((Number)a, (Number)b, portion);
        if(a instanceof Color)
            return interpolate((Color)a, (Color)b, portion);
        if(a instanceof double[])
            return interpolate((double[])a, (double[])b, portion);
        if(a instanceof double[][])
            return interpolate((double[][])a, (double[][])b, portion);
        else
            return b;
    }
}

package edu.cmu.cs.stage3.math;

import java.io.Serializable;
import javax.vecmath.Point2d;

class Circle
    implements Serializable
{

    public Circle()
    {
        center = new Point2d();
    }

    public Point2d center;
    public double radius;
}

package edu.cmu.cs.stage3.math;


// Referenced classes of package edu.cmu.cs.stage3.math:
//            Polynomial

interface Cubic
    extends Polynomial
{
}

package edu.cmu.cs.stage3.math;


// Referenced classes of package edu.cmu.cs.stage3.math:
//            Measurement

class Distance extends Measurement
{

    public Distance(double value, double factor)
    {
        super(value, factor);
    }

    public static final double METERS = 1D;
    public static final double INCHES = 0.025399999999999999D;
    public static final double FEET = 0.30479999999999996D;
    public static final double MILES = 1609.3439999999998D;
}

package edu.cmu.cs.stage3.math;

import javax.vecmath.Tuple3d;
import javax.vecmath.Vector3d;

// Referenced classes of package edu.cmu.cs.stage3.math:
//            Interpolable, Matrix33, MathUtilities, Shear,
//            AxisAngle, Quaternion

class EulerAngles
    implements Interpolable, Cloneable
{

    public EulerAngles()
    {
        pitch = 0.0D;
        yaw = 0.0D;
        roll = 0.0D;
    }

    public EulerAngles(double pitch, double yaw, double roll)
    {
        this.pitch = 0.0D;
        this.yaw = 0.0D;
        this.roll = 0.0D;
        this.pitch = pitch;
        this.yaw = yaw;
        this.roll = roll;
    }

    public EulerAngles(double a[])
    {
        this(a[0], a[1], a[2]);
    }

    public EulerAngles(Matrix33 m)
    {
        pitch = 0.0D;
        yaw = 0.0D;
        roll = 0.0D;
        setMatrix33(m);
    }

    public EulerAngles(AxisAngle aa)
    {
        pitch = 0.0D;
        yaw = 0.0D;
        roll = 0.0D;
        setAxisAngle(aa);
    }

    public EulerAngles(Quaternion q)
    {
        pitch = 0.0D;
        yaw = 0.0D;
        roll = 0.0D;
        setQuaternion(q);
    }

    public synchronized Object clone()
    {
        try
        {
            return super.clone();
        }
        catch(CloneNotSupportedException e)
        {
            throw new InternalError();
        }
    }

    public boolean equals(Object o)
    {
        if(o == this)
            return true;
        if(o != null && (o instanceof EulerAngles))
        {
            EulerAngles ea = (EulerAngles)o;
            return yaw == ea.yaw && pitch == ea.pitch && roll == ea.roll;
        } else
        {
            return false;
        }
    }

    public Matrix33 getMatrix33()
    {
        return new Matrix33(this);
    }

    public void setMatrix33(Matrix33 m)
    {
        Vector3d row0 = MathUtilities.getRow(m, 0);
        Vector3d row1 = MathUtilities.getRow(m, 1);
        Vector3d row2 = MathUtilities.getRow(m, 2);
        Vector3d scale = new Vector3d();
        Shear shear = new Shear();
        scale.x = row0.length();
        row0.normalize();
        shear.xy = MathUtilities.dotProduct(row0, row1);
        row1 = MathUtilities.combine(row1, row0, 1.0D, -shear.xy);
        scale.y = row1.length();
        row1.normalize();
        shear.xy /= ((Tuple3d) (scale)).y;
        shear.xz = MathUtilities.dotProduct(row0, row2);
        row2 = MathUtilities.combine(row2, row0, 1.0D, -shear.xz);
        shear.yz = MathUtilities.dotProduct(row1, row2);
        row2 = MathUtilities.combine(row2, row1, 1.0D, -shear.yz);
        scale.z = row2.length();
        row2.normalize();
        shear.xz /= ((Tuple3d) (scale)).z;
        shear.yz /= ((Tuple3d) (scale)).z;
        double determinate = MathUtilities.dotProduct(row0, MathUtilities.crossProduct(row1, row2));
        if(determinate < 0.0D)
        {
            row0.negate();
            row1.negate();
            row2.negate();
            scale.scale(-1D);
        }
        yaw = Math.asin(-((Tuple3d) (row0)).z);
        if(Math.cos(yaw) != 0.0D)
        {
            pitch = Math.atan2(((Tuple3d) (row1)).z, ((Tuple3d) (row2)).z);
            roll = Math.atan2(((Tuple3d) (row0)).y, ((Tuple3d) (row0)).x);
        } else
        {
            pitch = Math.atan2(((Tuple3d) (row1)).x, ((Tuple3d) (row1)).y);
            roll = 0.0D;
        }
    }

    public AxisAngle getAxisAngle()
    {
        return new AxisAngle(this);
    }

    public void setAxisAngle(AxisAngle aa)
    {
        setMatrix33(aa.getMatrix33());
    }

    public Quaternion getQuaternion()
    {
        return new Quaternion(this);
    }

    public void setQuaternion(Quaternion q)
    {
        setMatrix33(q.getMatrix33());
    }

    public static EulerAngles interpolate(EulerAngles a, EulerAngles b, double portion)
    {
        Quaternion q = Quaternion.interpolate(a.getQuaternion(), b.getQuaternion(), portion);
        return new EulerAngles(q);
    }

    public Interpolable interpolate(Interpolable b, double portion)
    {
        return interpolate(this, (EulerAngles)b, portion);
    }

    public String toString()
    {
        return "edu.cmu.cs.stage3.math.EulerAngles[pitch=" + pitch + ",yaw=" + yaw + ",roll=" + roll + "]";
    }

    public static EulerAngles revolutionsToRadians(EulerAngles ea)
    {
        return new EulerAngles(ea.pitch / 0.15915494309189535D, ea.yaw / 0.15915494309189535D, ea.roll / 0.15915494309189535D);
    }

    public static EulerAngles radiansToRevolutions(EulerAngles ea)
    {
        return new EulerAngles(ea.pitch * 0.15915494309189535D, ea.yaw * 0.15915494309189535D, ea.roll * 0.15915494309189535D);
    }

    public static EulerAngles valueOf(String s)
    {
        String markers[] = {
            "edu.cmu.cs.stage3.math.EulerAngles[pitch=", ",yaw=", ",roll=", "]"
        };
        double values[] = new double[markers.length - 1];
        for(int i = 0; i < values.length; i++)
        {
            int begin = s.indexOf(markers[i]) + markers[i].length();
            int end = s.indexOf(markers[i + 1]);
            values[i] = Double.valueOf(s.substring(begin, end)).doubleValue();
        }

        return new EulerAngles(values);
    }

    public double pitch;
    public double yaw;
    public double roll;
}

package edu.cmu.cs.stage3.math;


class GoldenRatio
{

    public GoldenRatio()
    {
    }

    public static int getShorterSideLength(int longerSideLength)
    {
        return (int)((double)longerSideLength / PHI);
    }

    public static int getLongerSideLength(int shorterSideLength)
    {
        return (int)((double)shorterSideLength * PHI);
    }

    public static double PHI = 1.6180339886999999D;

}

package edu.cmu.cs.stage3.math;

import javax.vecmath.Matrix4d;
import javax.vecmath.Vector4d;

// Referenced classes of package edu.cmu.cs.stage3.math:
//            BasisMatrixCubic

class HermiteCubic extends BasisMatrixCubic
{

    public HermiteCubic(Vector4d g)
    {
        super(s_h, g);
    }

    public HermiteCubic(double g0, double g1, double g2, double g3)
    {
        this(new Vector4d(g0, g1, g2, g3));
    }

    private static final Matrix4d s_h = new Matrix4d(2D, -2D, 1.0D, 1.0D, -3D, 3D, -2D, -1D, 0.0D, 0.0D, 1.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D);

}

package edu.cmu.cs.stage3.math;


interface Interpolable
{

    public abstract Interpolable interpolate(Interpolable interpolable, double d);
}

package edu.cmu.cs.stage3.math;

import java.awt.Color;
import javax.vecmath.Matrix4d;

// Referenced classes of package edu.cmu.cs.stage3.math:
//            Matrix44, Interpolable

class Interpolator
{

    public Interpolator()
    {
    }

    public static int interpolate(int a, int b, double portion)
    {
        return a + (int)((double)(b - a) * portion);
    }

    public static double interpolate(double a, double b, double portion)
    {
        return a + (b - a) * portion;
    }

    public static Number interpolate(Number a, Number b, double portion)
    {
        return new Double(interpolate(a.doubleValue(), b.doubleValue(), portion));
    }

    public static double[] interpolate(double a[], double b[], double portion)
    {
        if(a.length != b.length)
            return null;
        double v[] = new double[a.length];
        for(int i = 0; i < a.length; i++)
            v[i] = interpolate(a[i], b[i], portion);

        return v;
    }

    public static double[][] interpolate(double a[][], double b[][], double portion)
    {
        if(a.length != b.length)
            return null;
        double m[][] = new double[a.length][];
        for(int i = 0; i < a.length; i++)
        {
            if(a[i].length != b[i].length)
                return null;
            m[i] = new double[a[i].length];
            for(int j = 0; j < a[i].length; j++)
                m[i][j] = interpolate(a[i][j], b[i][j], portion);

        }

        return m;
    }

    public static Color interpolate(Color a, Color b, double portion)
    {
        return new Color(Math.max(0, Math.min(interpolate(a.getRed(), b.getRed(), portion), 255)), Math.max(0, Math.min(interpolate(a.getGreen(), b.getGreen(), portion), 255)), Math.max(0, Math.min(interpolate(a.getBlue(), b.getBlue(), portion), 255)));
    }

    public static Object interpolate(Object a, Object b, double portion)
    {
        if(a instanceof Matrix4d)
        {
            Interpolable ai;
            if(a instanceof Matrix44)
                ai = (Matrix44)a;
            else
                ai = new Matrix44((Matrix4d)a);
            Interpolable bi;
            if(b instanceof Matrix44)
                bi = (Matrix44)b;
            else
                bi = new Matrix44((Matrix4d)b);
            return ai.interpolate(bi, portion);
        }
        if(a instanceof Interpolable)
            return ((Interpolable)a).interpolate((Interpolable)b, portion);
        if(a instanceof Number)
            return interpolate((Number)a, (Number)b, portion);
        if(a instanceof Color)
            return interpolate((Color)a, (Color)b, portion);
        if(a instanceof double[])
            return interpolate((double[])a, (double[])b, portion);
        if(a instanceof double[][])
            return interpolate((double[][])a, (double[][])b, portion);
        else
            return b;
    }
}

package edu.cmu.cs.stage3.math;

import javax.vecmath.*;

class MathUtilities
{

    public MathUtilities()
    {
    }

    public static Matrix4d getIdentityMatrix4d()
    {
        IDENTITY_MATRIX_4D.setIdentity();
        return IDENTITY_MATRIX_4D;
    }

    public static Matrix3d getIdentityMatrix3d()
    {
        IDENTITY_MATRIX_3D.setIdentity();
        return IDENTITY_MATRIX_3D;
    }

    public static Vector3d getXAxis()
    {
        X_AXIS.x = 1.0D;
        X_AXIS.y = X_AXIS.z = 0.0D;
        return X_AXIS;
    }

    public static Vector3d getYAxis()
    {
        Y_AXIS.y = 1.0D;
        Y_AXIS.x = Y_AXIS.z = 0.0D;
        return Y_AXIS;
    }

    public static Vector3d getZAxis()
    {
        Z_AXIS.z = 1.0D;
        Z_AXIS.x = Z_AXIS.y = 0.0D;
        return Z_AXIS;
    }

    public static Vector3d getNegativeXAxis()
    {
        NEGATIVE_X_AXIS.x = -1D;
        NEGATIVE_X_AXIS.y = NEGATIVE_X_AXIS.z = 0.0D;
        return NEGATIVE_X_AXIS;
    }

    public static Vector3d getNegativeYAxis()
    {
        NEGATIVE_Y_AXIS.y = -1D;
        NEGATIVE_Y_AXIS.x = NEGATIVE_Y_AXIS.z = 0.0D;
        return NEGATIVE_Y_AXIS;
    }

    public static Vector3d getNegativeZAxis()
    {
        NEGATIVE_Z_AXIS.z = 1.0D;
        NEGATIVE_Z_AXIS.x = Z_AXIS.y = 0.0D;
        return NEGATIVE_Z_AXIS;
    }

    public static Matrix4d createIdentityMatrix4d()
    {
        Matrix4d m = new Matrix4d();
        m.setIdentity();
        return m;
    }

    public static Matrix3d createIdentityMatrix3d()
    {
        Matrix3d m = new Matrix3d();
        m.setIdentity();
        return m;
    }

    public static Vector3d createXAxis()
    {
        return new Vector3d(1.0D, 0.0D, 0.0D);
    }

    public static Vector3d createYAxis()
    {
        return new Vector3d(0.0D, 1.0D, 0.0D);
    }

    public static Vector3d createZAxis()
    {
        return new Vector3d(0.0D, 0.0D, 1.0D);
    }

    public static Vector3d createNegativeXAxis()
    {
        return new Vector3d(-1D, 0.0D, 0.0D);
    }

    public static Vector3d createNegativeYAxis()
    {
        return new Vector3d(0.0D, -1D, 0.0D);
    }

    public static Vector3d createNegativeZAxis()
    {
        return new Vector3d(0.0D, 0.0D, -1D);
    }

    public static Vector3d createVector3d(Tuple4d t)
    {
        return new Vector3d(t.x / t.w, t.y / t.w, t.z / t.w);
    }

    public static Vector4d createVector4d(Tuple3d t, double tW)
    {
        return new Vector4d(t.x, t.y, t.z, tW);
    }

    public static double getItem(Vector3d vector, int i)
    {
        switch(i)
        {
        case 0: // '\0'
            return ((Tuple3d) (vector)).x;

        case 1: // '\001'
            return ((Tuple3d) (vector)).y;

        case 2: // '\002'
            return ((Tuple3d) (vector)).z;
        }
        throw new IndexOutOfBoundsException();
    }

    public static void setItem(Vector3d vector, int i, double value)
    {
        switch(i)
        {
        case 0: // '\0'
            vector.x = value;
            return;

        case 1: // '\001'
            vector.y = value;
            return;

        case 2: // '\002'
            vector.z = value;
            return;
        }
        throw new IndexOutOfBoundsException();
    }

    public static Vector3d add(Vector3d a, Vector3d b)
    {
        return new Vector3d(((Tuple3d) (a)).x + ((Tuple3d) (b)).x, ((Tuple3d) (a)).y + ((Tuple3d) (b)).y, ((Tuple3d) (a)).z + ((Tuple3d) (b)).z);
    }

    public static Vector3d subtract(Vector3d a, Vector3d b)
    {
        return new Vector3d(((Tuple3d) (a)).x - ((Tuple3d) (b)).x, ((Tuple3d) (a)).y - ((Tuple3d) (b)).y, ((Tuple3d) (a)).z - ((Tuple3d) (b)).z);
    }

    public static Vector3d negate(Vector3d v)
    {
        return new Vector3d(-((Tuple3d) (v)).x, -((Tuple3d) (v)).y, -((Tuple3d) (v)).z);
    }

    public static Vector3d multiply(Vector3d v, double scalar)
    {
        return new Vector3d(((Tuple3d) (v)).x * scalar, ((Tuple3d) (v)).y * scalar, ((Tuple3d) (v)).z * scalar);
    }

    public static Vector3d multiply(Vector3d v, Vector3d scalar)
    {
        return new Vector3d(((Tuple3d) (v)).x * ((Tuple3d) (scalar)).x, ((Tuple3d) (v)).y * ((Tuple3d) (scalar)).y, ((Tuple3d) (v)).z * ((Tuple3d) (scalar)).z);
    }

    public static Vector3d divide(Vector3d v, double divisor)
    {
        return multiply(v, 1.0D / divisor);
    }

    public static Vector3d divide(Vector3d numerator, Vector3d divisor)
    {
        return new Vector3d(((Tuple3d) (numerator)).x / ((Tuple3d) (divisor)).x, ((Tuple3d) (numerator)).y / ((Tuple3d) (divisor)).y, ((Tuple3d) (numerator)).z / ((Tuple3d) (divisor)).z);
    }

    public static Vector3d invert(Vector3d v)
    {
        return new Vector3d(1.0D / ((Tuple3d) (v)).x, 1.0D / ((Tuple3d) (v)).y, 1.0D / ((Tuple3d) (v)).z);
    }

    public static Vector3d normalizeV(Vector3d v)
    {
        Vector3d nv = new Vector3d(v);
        nv.normalize();
        return nv;
    }

    public static double getLengthSquared(double x, double y, double z)
    {
        return x * x + y * y + z * z;
    }

    public static double getLength(double x, double y, double z)
    {
        double lengthSquared = getLengthSquared(x, y, z);
        if(lengthSquared == 1.0D)
            return 1.0D;
        else
            return Math.sqrt(lengthSquared);
    }

    public static double getLengthSquared(Vector3d v)
    {
        return getLengthSquared(((Tuple3d) (v)).x, ((Tuple3d) (v)).y, ((Tuple3d) (v)).z);
    }

    public static double getLength(Vector3d v)
    {
        return getLength(((Tuple3d) (v)).x, ((Tuple3d) (v)).y, ((Tuple3d) (v)).z);
    }

    public static double dotProduct(Vector3d a, Vector3d b)
    {
        return ((Tuple3d) (a)).x * ((Tuple3d) (b)).x + ((Tuple3d) (a)).y * ((Tuple3d) (b)).y + ((Tuple3d) (a)).z * ((Tuple3d) (b)).z;
    }

    public static Vector3d crossProduct(Vector3d a, Vector3d b)
    {
        return new Vector3d(((Tuple3d) (a)).y * ((Tuple3d) (b)).z - ((Tuple3d) (a)).z * ((Tuple3d) (b)).y, ((Tuple3d) (a)).z * ((Tuple3d) (b)).x - ((Tuple3d) (a)).x * ((Tuple3d) (b)).z, ((Tuple3d) (a)).x * ((Tuple3d) (b)).y - ((Tuple3d) (a)).y * ((Tuple3d) (b)).x);
    }

    public static Vector3d interpolate(Vector3d a, Vector3d b, double portion)
    {
        return new Vector3d(((Tuple3d) (a)).x + (((Tuple3d) (b)).x - ((Tuple3d) (a)).x) * portion, ((Tuple3d) (a)).y + (((Tuple3d) (b)).y - ((Tuple3d) (a)).y) * portion, ((Tuple3d) (a)).z + (((Tuple3d) (b)).z - ((Tuple3d) (a)).z) * portion);
    }

    public static Vector3d projectOnto(Vector3d a, Vector3d b)
    {
        return multiply(b, dotProduct(b, a) / dotProduct(b, b));
    }

    public static Vector3d multiply(Matrix3d a, Vector3d b)
    {
        double x = a.m00 * ((Tuple3d) (b)).x + a.m01 * ((Tuple3d) (b)).y + a.m02 * ((Tuple3d) (b)).z;
        double y = a.m10 * ((Tuple3d) (b)).x + a.m11 * ((Tuple3d) (b)).y + a.m12 * ((Tuple3d) (b)).z;
        double z = a.m20 * ((Tuple3d) (b)).x + a.m21 * ((Tuple3d) (b)).y + a.m22 * ((Tuple3d) (b)).z;
        return new Vector3d(x, y, z);
    }

    public static Vector3d multiply(Vector3d a, Matrix4d b)
    {
        Vector3d ab = new Vector3d();
        ab.x = ((Tuple3d) (a)).x * b.m00 + ((Tuple3d) (a)).y * b.m10 + ((Tuple3d) (a)).z * b.m20;
        ab.y = ((Tuple3d) (a)).x * b.m01 + ((Tuple3d) (a)).y * b.m11 + ((Tuple3d) (a)).z * b.m21;
        ab.z = ((Tuple3d) (a)).x * b.m02 + ((Tuple3d) (a)).y * b.m12 + ((Tuple3d) (a)).z * b.m22;
        return ab;
    }

    public static Vector3d combine(Vector3d a, Vector3d b, double asc1, double bsc1)
    {
        Vector3d ab = new Vector3d();
        ab.x = asc1 * ((Tuple3d) (a)).x + bsc1 * ((Tuple3d) (b)).x;
        ab.y = asc1 * ((Tuple3d) (a)).y + bsc1 * ((Tuple3d) (b)).y;
        ab.z = asc1 * ((Tuple3d) (a)).z + bsc1 * ((Tuple3d) (b)).z;
        return ab;
    }

    public static double getItem(Vector4d vector, int i)
    {
        switch(i)
        {
        case 0: // '\0'
            return ((Tuple4d) (vector)).x;

        case 1: // '\001'
            return ((Tuple4d) (vector)).y;

        case 2: // '\002'
            return ((Tuple4d) (vector)).z;

        case 3: // '\003'
            return ((Tuple4d) (vector)).w;
        }
        throw new IndexOutOfBoundsException();
    }

    public static void setItem(Vector4d vector, int i, double value)
    {
        switch(i)
        {
        case 0: // '\0'
            vector.x = value;
            return;

        case 1: // '\001'
            vector.y = value;
            return;

        case 2: // '\002'
            vector.z = value;
            return;

        case 3: // '\003'
            vector.w = value;
            return;
        }
        throw new IndexOutOfBoundsException();
    }

    public static Vector4d negate(Vector4d v)
    {
        return new Vector4d(-((Tuple4d) (v)).x, -((Tuple4d) (v)).y, -((Tuple4d) (v)).z, -((Tuple4d) (v)).w);
    }

    public static double dotProduct(Vector4d a, Vector4d b)
    {
        return ((Tuple4d) (a)).x * ((Tuple4d) (b)).x + ((Tuple4d) (a)).y * ((Tuple4d) (b)).y + ((Tuple4d) (a)).z * ((Tuple4d) (b)).z + ((Tuple4d) (a)).w * ((Tuple4d) (b)).w;
    }

    public static Vector4d multiply(double aX, double aY, double aZ, double aW,
            Matrix4d b)
    {
        Vector4d ab = new Vector4d();
        ab.x = aX * b.m00 + aY * b.m10 + aZ * b.m20 + aW * b.m30;
        ab.y = aX * b.m01 + aY * b.m11 + aZ * b.m21 + aW * b.m31;
        ab.z = aX * b.m02 + aY * b.m12 + aZ * b.m22 + aW * b.m32;
        ab.w = aX * b.m03 + aY * b.m13 + aZ * b.m23 + aW * b.m33;
        return ab;
    }

    public static Vector4d multiply(Vector4d a, Matrix4d b)
    {
        return multiply(((Tuple4d) (a)).x, ((Tuple4d) (a)).y, ((Tuple4d) (a)).z, ((Tuple4d) (a)).w, b);
    }

    public static Vector4d multiply(Vector3d a, double aW, Matrix4d b)
    {
        return multiply(((Tuple3d) (a)).x, ((Tuple3d) (a)).y, ((Tuple3d) (a)).z, aW, b);
    }

    public static Vector4d multiply(Matrix4d a, double bX, double bY, double bZ, double bW)
    {
        Vector4d ab = new Vector4d();
        ab.x = bX * a.m00 + bY * a.m01 + bZ * a.m02 + bW * a.m03;
        ab.y = bX * a.m10 + bY * a.m11 + bZ * a.m12 + bW * a.m13;
        ab.z = bX * a.m20 + bY * a.m21 + bZ * a.m22 + bW * a.m23;
        ab.w = bX * a.m30 + bY * a.m31 + bZ * a.m32 + bW * a.m33;
        return ab;
    }

    public static Vector4d multiply(Matrix4d a, Vector4d b)
    {
        return multiply(a, ((Tuple4d) (b)).x, ((Tuple4d) (b)).y, ((Tuple4d) (b)).z, ((Tuple4d) (b)).w);
    }

    public static Vector4d multiply(Matrix4d a, Vector3d b, double bW)
    {
        return multiply(a, ((Tuple3d) (b)).x, ((Tuple3d) (b)).y, ((Tuple3d) (b)).z, bW);
    }

    public static Vector3d getRow(Matrix3d m, int i)
    {
        switch(i)
        {
        case 0: // '\0'
            return new Vector3d(m.m00, m.m01, m.m02);

        case 1: // '\001'
            return new Vector3d(m.m10, m.m11, m.m12);

        case 2: // '\002'
            return new Vector3d(m.m20, m.m21, m.m22);
        }
        throw new IndexOutOfBoundsException();
    }

    public static void setRow(Matrix3d m, int i, Vector3d v)
    {
        switch(i)
        {
        case 0: // '\0'
            m.m00 = ((Tuple3d) (v)).x;
            m.m01 = ((Tuple3d) (v)).y;
            m.m02 = ((Tuple3d) (v)).z;
            break;

        case 1: // '\001'
            m.m10 = ((Tuple3d) (v)).x;
            m.m11 = ((Tuple3d) (v)).y;
            m.m12 = ((Tuple3d) (v)).z;
            break;

        case 2: // '\002'
            m.m20 = ((Tuple3d) (v)).x;
            m.m21 = ((Tuple3d) (v)).y;
            m.m22 = ((Tuple3d) (v)).z;
            break;

        default:
            throw new IndexOutOfBoundsException();
        }
    }

    public static Vector3d getColumn(Matrix3d m, int i)
    {
        switch(i)
        {
        case 0: // '\0'
            return new Vector3d(m.m00, m.m10, m.m20);

        case 1: // '\001'
            return new Vector3d(m.m01, m.m11, m.m21);

        case 2: // '\002'
            return new Vector3d(m.m02, m.m12, m.m22);
        }
        throw new IndexOutOfBoundsException();
    }

    public static void setColumn(Matrix3d m, int i, Vector3d v)
    {
        switch(i)
        {
        case 0: // '\0'
            m.m00 = ((Tuple3d) (v)).x;
            m.m10 = ((Tuple3d) (v)).y;
            m.m20 = ((Tuple3d) (v)).z;
            break;

        case 1: // '\001'
            m.m01 = ((Tuple3d) (v)).x;
            m.m11 = ((Tuple3d) (v)).y;
            m.m21 = ((Tuple3d) (v)).z;
            break;

        case 2: // '\002'
            m.m02 = ((Tuple3d) (v)).x;
            m.m12 = ((Tuple3d) (v)).y;
            m.m22 = ((Tuple3d) (v)).z;
            break;

        default:
            throw new IndexOutOfBoundsException();
        }
    }

    public static Matrix3d multiply(Matrix3d a, Matrix3d b)
    {
        Matrix3d m = new Matrix3d();
        m.m00 = a.m00 * b.m00 + a.m01 * b.m10 + a.m02 * b.m20;
        m.m01 = a.m00 * b.m01 + a.m01 * b.m11 + a.m02 * b.m21;
        m.m02 = a.m00 * b.m02 + a.m01 * b.m12 + a.m02 * b.m22;
        m.m10 = a.m10 * b.m00 + a.m11 * b.m10 + a.m12 * b.m20;
        m.m11 = a.m10 * b.m01 + a.m11 * b.m11 + a.m12 * b.m21;
        m.m12 = a.m10 * b.m02 + a.m11 * b.m12 + a.m12 * b.m22;
        m.m20 = a.m20 * b.m00 + a.m21 * b.m10 + a.m22 * b.m20;
        m.m21 = a.m20 * b.m01 + a.m21 * b.m11 + a.m22 * b.m21;
        m.m22 = a.m20 * b.m02 + a.m21 * b.m12 + a.m22 * b.m22;
        return m;
    }

    public static Matrix4d multiply(Matrix4d a, Matrix4d b)
    {
        Matrix4d m = new Matrix4d();
        m.m00 = a.m00 * b.m00 + a.m01 * b.m10 + a.m02 * b.m20 + a.m03 * b.m30;
        m.m01 = a.m00 * b.m01 + a.m01 * b.m11 + a.m02 * b.m21 + a.m03 * b.m31;
        m.m02 = a.m00 * b.m02 + a.m01 * b.m12 + a.m02 * b.m22 + a.m03 * b.m32;
        m.m03 = a.m00 * b.m03 + a.m01 * b.m13 + a.m02 * b.m23 + a.m03 * b.m33;
        m.m10 = a.m10 * b.m00 + a.m11 * b.m10 + a.m12 * b.m20 + a.m13 * b.m30;
        m.m11 = a.m10 * b.m01 + a.m11 * b.m11 + a.m12 * b.m21 + a.m13 * b.m31;
        m.m12 = a.m10 * b.m02 + a.m11 * b.m12 + a.m12 * b.m22 + a.m13 * b.m32;
        m.m13 = a.m10 * b.m03 + a.m11 * b.m13 + a.m12 * b.m23 + a.m13 * b.m33;
        m.m20 = a.m20 * b.m00 + a.m21 * b.m10 + a.m22 * b.m20 + a.m23 * b.m30;
        m.m21 = a.m20 * b.m01 + a.m21 * b.m11 + a.m22 * b.m21 + a.m23 * b.m31;
        m.m22 = a.m20 * b.m02 + a.m21 * b.m12 + a.m22 * b.m22 + a.m23 * b.m32;
        m.m23 = a.m20 * b.m03 + a.m21 * b.m13 + a.m22 * b.m23 + a.m23 * b.m33;
        m.m30 = a.m30 * b.m00 + a.m31 * b.m10 + a.m32 * b.m20 + a.m33 * b.m30;
        m.m31 = a.m30 * b.m01 + a.m31 * b.m11 + a.m32 * b.m21 + a.m33 * b.m31;
        m.m32 = a.m30 * b.m02 + a.m31 * b.m12 + a.m32 * b.m22 + a.m33 * b.m32;
        m.m33 = a.m30 * b.m03 + a.m31 * b.m13 + a.m32 * b.m23 + a.m33 * b.m33;
        return m;
    }

    public static boolean contains(Tuple3d t, double d)
    {
        if(Double.isNaN(d))
            return Double.isNaN(t.x) || Double.isNaN(t.y) || Double.isNaN(t.z);
        return t.x == d || t.y == d || t.z == d;
    }

    private static final Matrix4d IDENTITY_MATRIX_4D = new Matrix4d();
    private static final Matrix3d IDENTITY_MATRIX_3D = new Matrix3d();
    private static final Vector3d X_AXIS = new Vector3d(1.0D, 0.0D, 0.0D);
    private static final Vector3d Y_AXIS = new Vector3d(0.0D, 1.0D, 0.0D);
    private static final Vector3d Z_AXIS = new Vector3d(0.0D, 0.0D, 1.0D);
    private static final Vector3d NEGATIVE_X_AXIS = new Vector3d(-1D, 0.0D, 0.0D);
    private static final Vector3d NEGATIVE_Y_AXIS = new Vector3d(0.0D, -1D, 0.0D);
    private static final Vector3d NEGATIVE_Z_AXIS = new Vector3d(0.0D, 0.0D, -1D);

}

package edu.cmu.cs.stage3.math;

import javax.vecmath.*;

// Referenced classes of package edu.cmu.cs.stage3.math:
//            Interpolable, Vector3, Quaternion, AxisAngle,
//            EulerAngles, Shear

class Matrix33 extends Matrix3d
    implements Interpolable
{

    public Matrix33()
    {
        this(1.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 1.0D);
    }

    public Matrix33(double rc00, double rc01, double rc02, double rc10, double rc11, double rc12, double rc20,
            double rc21, double rc22)
    {
        super.m00 = rc00;
        super.m01 = rc01;
        super.m02 = rc02;
        super.m10 = rc10;
        super.m11 = rc11;
        super.m12 = rc12;
        super.m20 = rc20;
        super.m21 = rc21;
        super.m22 = rc22;
    }

    public Matrix33(double row0[], double row1[], double row2[])
    {
        this(row0[0], row0[1], row0[2], row1[0], row1[1], row1[2], row2[0], row2[1], row2[2]);
    }

    public Matrix33(Vector3d row0, Vector3d row1, Vector3d row2)
    {
        this(((Tuple3d) (row0)).x, ((Tuple3d) (row0)).y, ((Tuple3d) (row0)).z, ((Tuple3d) (row1)).x, ((Tuple3d) (row1)).y, ((Tuple3d) (row1)).z, ((Tuple3d) (row2)).x, ((Tuple3d) (row2)).y, ((Tuple3d) (row2)).z);
    }

    public Matrix33(double a[])
    {
        this(a[0], a[1], a[2], a[3], a[4], a[5], a[6], a[7], a[8]);
    }

    public Matrix33(double m[][])
    {
        this(m[0], m[1], m[2]);
    }

    public Matrix33(Matrix3d m)
    {
        super(m);
    }

    public Matrix33(AxisAngle aa)
    {
        setAxisAngle(aa);
    }

    public Matrix33(Quaternion q)
    {
        setQuaternion(q);
    }

    public Matrix33(EulerAngles ea)
    {
        setEulerAngles(ea);
    }

    public boolean equals(Object o)
    {
        if(o == this)
            return true;
        if(o != null && (o instanceof Matrix33))
        {
            Matrix33 m = (Matrix33)o;
            return super.m00 == ((Matrix3d) (m)).m00 && super.m01 == ((Matrix3d) (m)).m01 && super.m02 == ((Matrix3d) (m)).m02 && super.m10 == ((Matrix3d) (m)).m10 && super.m11 == ((Matrix3d) (m)).m11 && super.m12 == ((Matrix3d) (m)).m12 && super.m20 == ((Matrix3d) (m)).m20 && super.m21 == ((Matrix3d) (m)).m21 && super.m22 == ((Matrix3d) (m)).m22;
        } else
        {
            return false;
        }
    }

    public double getItem(int i, int j)
    {
        switch(i)
        {
        default:
            break;

        case 0: // '\0'
            switch(j)
            {
            case 0: // '\0'
                return super.m00;

            case 1: // '\001'
                return super.m01;

            case 2: // '\002'
                return super.m02;
            }
            break;

        case 1: // '\001'
            switch(j)
            {
            case 0: // '\0'
                return super.m10;

            case 1: // '\001'
                return super.m11;

            case 2: // '\002'
                return super.m12;
            }
            break;

        case 2: // '\002'
            switch(j)
            {
            case 0: // '\0'
                return super.m20;

            case 1: // '\001'
                return super.m21;

            case 2: // '\002'
                return super.m22;
            }
            break;
        }
        throw new IllegalArgumentException();
    }

    public void setItem(int i, int j, double v)
    {
        switch(i)
        {
        default:
            break;

        case 0: // '\0'
            switch(j)
            {
            case 0: // '\0'
                super.m00 = v;
                return;

            case 1: // '\001'
                super.m01 = v;
                return;

            case 2: // '\002'
                super.m02 = v;
                return;
            }
            break;

        case 1: // '\001'
            switch(j)
            {
            case 0: // '\0'
                super.m10 = v;
                return;

            case 1: // '\001'
                super.m11 = v;
                return;

            case 2: // '\002'
                super.m12 = v;
                return;
            }
            break;

        case 2: // '\002'
            switch(j)
            {
            case 0: // '\0'
                super.m20 = v;
                return;

            case 1: // '\001'
                super.m21 = v;
                return;

            case 2: // '\002'
                super.m22 = v;
                return;
            }
            break;
        }
        throw new IllegalArgumentException();
    }

    public Vector3 getRow(int i)
    {
        switch(i)
        {
        case 0: // '\0'
            return new Vector3(super.m00, super.m01, super.m02);

        case 1: // '\001'
            return new Vector3(super.m10, super.m11, super.m12);

        case 2: // '\002'
            return new Vector3(super.m20, super.m21, super.m22);
        }
        return null;
    }

    public void setRow(int i, Vector3 v)
    {
        switch(i)
        {
        case 0: // '\0'
            super.m00 = ((Tuple3d) (v)).x;
            super.m01 = ((Tuple3d) (v)).y;
            super.m02 = ((Tuple3d) (v)).z;
            break;

        case 1: // '\001'
            super.m10 = ((Tuple3d) (v)).x;
            super.m11 = ((Tuple3d) (v)).y;
            super.m12 = ((Tuple3d) (v)).z;
            break;

        case 2: // '\002'
            super.m20 = ((Tuple3d) (v)).x;
            super.m21 = ((Tuple3d) (v)).y;
            super.m22 = ((Tuple3d) (v)).z;
            break;

        default:
            throw new IndexOutOfBoundsException();
        }
    }

    public Vector3 getColumn(int i)
    {
        switch(i)
        {
        case 0: // '\0'
            return new Vector3(super.m00, super.m10, super.m20);

        case 1: // '\001'
            return new Vector3(super.m01, super.m11, super.m21);

        case 2: // '\002'
            return new Vector3(super.m02, super.m12, super.m22);
        }
        throw new IndexOutOfBoundsException();
    }

    public void setColumn(int i, Vector3 v)
    {
        switch(i)
        {
        case 0: // '\0'
            super.m00 = ((Tuple3d) (v)).x;
            super.m10 = ((Tuple3d) (v)).y;
            super.m20 = ((Tuple3d) (v)).z;
            break;

        case 1: // '\001'
            super.m01 = ((Tuple3d) (v)).x;
            super.m11 = ((Tuple3d) (v)).y;
            super.m21 = ((Tuple3d) (v)).z;
            break;

        case 2: // '\002'
            super.m02 = ((Tuple3d) (v)).x;
            super.m12 = ((Tuple3d) (v)).y;
            super.m22 = ((Tuple3d) (v)).z;
            break;

        default:
            throw new IndexOutOfBoundsException();
        }
    }

    public double[] getArray(boolean rowMajor)
    {
        if(rowMajor)
        {
            double a[] = {
                super.m00, super.m01, super.m02, super.m10, super.m11, super.m12, super.m20, super.m21, super.m22
            };
            return a;
        } else
        {
            double a[] = {
                super.m00, super.m10, super.m20, super.m01, super.m11, super.m21, super.m02, super.m12, super.m22
            };
            return a;
        }
    }

    public void setArray(double a[], boolean rowMajor)
    {
        if(rowMajor)
        {
            super.m00 = a[0];
            super.m01 = a[1];
            super.m02 = a[2];
            super.m10 = a[3];
            super.m11 = a[4];
            super.m12 = a[5];
            super.m20 = a[6];
            super.m21 = a[7];
            super.m22 = a[8];
        } else
        {
            super.m00 = a[0];
            super.m01 = a[3];
            super.m02 = a[6];
            super.m10 = a[1];
            super.m11 = a[4];
            super.m12 = a[7];
            super.m20 = a[2];
            super.m21 = a[5];
            super.m22 = a[8];
        }
    }

    public double[][] getMatrix()
    {
        double m[][] = {
            {
                super.m00, super.m01, super.m02
            }, {
                super.m10, super.m11, super.m12
            }, {
                super.m20, super.m21, super.m22
            }
        };
        return m;
    }

    public void setMatrix(double m[][])
    {
        super.m00 = m[0][0];
        super.m01 = m[0][1];
        super.m02 = m[0][2];
        super.m10 = m[1][0];
        super.m11 = m[1][1];
        super.m12 = m[1][2];
        super.m20 = m[2][0];
        super.m21 = m[2][1];
        super.m22 = m[2][2];
    }

    public Quaternion getQuaternion()
    {
        return new Quaternion(this);
    }

    public void setQuaternion(Quaternion q)
    {
        double xx = q.x * q.x;
        double xy = q.x * q.y;
        double xz = q.x * q.z;
        double yy = q.y * q.y;
        double yz = q.y * q.z;
        double zz = q.z * q.z;
        double wx = q.w * q.x;
        double wy = q.w * q.y;
        double wz = q.w * q.z;
        super.m00 = 1.0D - 2D * (yy + zz);
        super.m01 = 2D * (xy - wz);
        super.m02 = 2D * (xz + wy);
        super.m10 = 2D * (xy + wz);
        super.m11 = 1.0D - 2D * (xx + zz);
        super.m12 = 2D * (yz - wx);
        super.m20 = 2D * (xz - wy);
        super.m21 = 2D * (yz + wx);
        super.m22 = 1.0D - 2D * (xx + yy);
    }

    public AxisAngle getAxisAngle()
    {
        return new AxisAngle(this);
    }

    public void setAxisAngle(AxisAngle aa)
    {
        double theta = aa.getAngle();
        Vector3d axis = aa.getAxis();
        double cosTheta = Math.cos(theta);
        double sinTheta = Math.sin(theta);
        super.m00 = ((Tuple3d) (axis)).x * ((Tuple3d) (axis)).x + cosTheta * (1.0D - ((Tuple3d) (axis)).x * ((Tuple3d) (axis)).x);
        super.m01 = ((Tuple3d) (axis)).x * ((Tuple3d) (axis)).y * (1.0D - cosTheta) + ((Tuple3d) (axis)).z * sinTheta;
        super.m02 = ((Tuple3d) (axis)).z * ((Tuple3d) (axis)).x * (1.0D - cosTheta) - ((Tuple3d) (axis)).y * sinTheta;
        super.m10 = ((Tuple3d) (axis)).x * ((Tuple3d) (axis)).y * (1.0D - cosTheta) - ((Tuple3d) (axis)).z * sinTheta;
        super.m11 = ((Tuple3d) (axis)).y * ((Tuple3d) (axis)).y + cosTheta * (1.0D - ((Tuple3d) (axis)).y * ((Tuple3d) (axis)).y);
        super.m12 = ((Tuple3d) (axis)).y * ((Tuple3d) (axis)).z * (1.0D - cosTheta) + ((Tuple3d) (axis)).x * sinTheta;
        super.m20 = ((Tuple3d) (axis)).z * ((Tuple3d) (axis)).x * (1.0D - cosTheta) + ((Tuple3d) (axis)).y * sinTheta;
        super.m21 = ((Tuple3d) (axis)).y * ((Tuple3d) (axis)).z * (1.0D - cosTheta) - ((Tuple3d) (axis)).x * sinTheta;
        super.m22 = ((Tuple3d) (axis)).z * ((Tuple3d) (axis)).z + cosTheta * (1.0D - ((Tuple3d) (axis)).z * ((Tuple3d) (axis)).z);
    }

    public EulerAngles getEulerAngles()
    {
        return new EulerAngles(this);
    }

    public void setEulerAngles(EulerAngles ea)
    {
        double c1 = Math.cos(ea.pitch);
        double s1 = Math.sin(ea.pitch);
        double c2 = Math.cos(ea.yaw);
        double s2 = Math.sin(ea.yaw);
        double c3 = Math.cos(ea.roll);
        double s3 = Math.sin(ea.roll);
        super.m00 = c2 * c3;
        super.m01 = s2 * s1 * c3 - c1 * s3;
        super.m02 = s2 * c1 * c3 + s1 * s3;
        super.m10 = c2 * s3;
        super.m11 = s2 * s1 * s3 + c1 * c3;
        super.m12 = c2 * s1;
        super.m20 = -s2;
        super.m21 = c2 * s1;
        super.m22 = c2 * c1;
    }

    public Vector3d[] getRows()
    {
        return (new Vector3d[] {
            getRow(0), getRow(1), getRow(2)
        });
    }

    public void setRows(Vector3d row0, Vector3d row1, Vector3d row2)
    {
        super.m00 = ((Tuple3d) (row0)).x;
        super.m01 = ((Tuple3d) (row0)).y;
        super.m02 = ((Tuple3d) (row0)).z;
        super.m10 = ((Tuple3d) (row1)).x;
        super.m11 = ((Tuple3d) (row1)).y;
        super.m12 = ((Tuple3d) (row1)).z;
        super.m20 = ((Tuple3d) (row2)).x;
        super.m21 = ((Tuple3d) (row2)).y;
        super.m22 = ((Tuple3d) (row2)).z;
    }

    public void setForwardUpGuide(Vector3d forward, Vector3d upGuide)
    {
        Vector3 row2 = Vector3.normalizeV(forward);
        Vector3 normalizedUpGuide;
        if(upGuide != null)
            normalizedUpGuide = Vector3.normalizeV(upGuide);
        else
            normalizedUpGuide = new Vector3(0.0D, 1.0D, 0.0D);
        Vector3 row0 = Vector3.crossProduct(normalizedUpGuide, row2);
        Vector3 row1 = Vector3.crossProduct(row2, row0);
        setRows(row0, row1, row2);
    }

    public void rotateX(double angle)
    {
        double cosAngle = Math.cos(angle);
        double sinAngle = Math.sin(angle);
        for(int i = 0; i < 3; i++)
        {
            double tmp = getItem(i, 1);
            setItem(i, 1, tmp * cosAngle - getItem(i, 2) * sinAngle);
            setItem(i, 2, tmp * sinAngle + getItem(i, 2) * cosAngle);
        }

    }

    public void rotateY(double angle)
    {
        double cosAngle = Math.cos(angle);
        double sinAngle = Math.sin(angle);
        for(int i = 0; i < 3; i++)
        {
            double tmp = getItem(i, 0);
            setItem(i, 0, tmp * cosAngle + getItem(i, 2) * sinAngle);
            setItem(i, 2, -tmp * sinAngle + getItem(i, 2) * cosAngle);
        }

    }

    public void rotateZ(double angle)
    {
        double cosAngle = Math.cos(angle);
        double sinAngle = Math.sin(angle);
        for(int i = 0; i < 3; i++)
        {
            double tmp = getItem(i, 0);
            setItem(i, 0, tmp * cosAngle - getItem(i, 1) * sinAngle);
            setItem(i, 1, tmp * sinAngle + getItem(i, 1) * cosAngle);
        }

    }

    public Vector3 getScaledSpace()
    {
        Vector3 row0 = getRow(0);
        Vector3 row1 = getRow(1);
        Vector3 row2 = getRow(2);
        Vector3 scale = new Vector3();
        Shear shear = new Shear();
        scale.x = row0.getLength();
        row0.normalize();
        shear.xy = Vector3.dotProduct(row0, row1);
        row1 = Vector3.combine(row1, row0, 1.0D, -shear.xy);
        scale.y = row1.getLength();
        row1.normalize();
        shear.xy /= ((Tuple3d) (scale)).y;
        shear.xz = Vector3.dotProduct(row0, row2);
        row2 = Vector3.combine(row2, row0, 1.0D, -shear.xz);
        shear.yz = Vector3.dotProduct(row1, row2);
        row2 = Vector3.combine(row2, row1, 1.0D, -shear.yz);
        scale.z = row2.getLength();
        row2.normalize();
        shear.xz /= ((Tuple3d) (scale)).z;
        shear.yz /= ((Tuple3d) (scale)).z;
        double determinate = Vector3.dotProduct(row0, Vector3.crossProduct(row1, row2));
        if(determinate < 0.0D)
        {
            row0.negate();
            row1.negate();
            row2.negate();
            scale.multiply(-1D);
        }
        return scale;
    }

    public static Matrix33 multiply(Matrix33 a, Matrix33 b)
    {
        Matrix33 m = new Matrix33();
        m.m00 = ((Matrix3d) (a)).m00 * ((Matrix3d) (b)).m00 + ((Matrix3d) (a)).m01 * ((Matrix3d) (b)).m10 + ((Matrix3d) (a)).m02 * ((Matrix3d) (b)).m20;
        m.m01 = ((Matrix3d) (a)).m00 * ((Matrix3d) (b)).m01 + ((Matrix3d) (a)).m01 * ((Matrix3d) (b)).m11 + ((Matrix3d) (a)).m02 * ((Matrix3d) (b)).m21;
        m.m02 = ((Matrix3d) (a)).m00 * ((Matrix3d) (b)).m02 + ((Matrix3d) (a)).m01 * ((Matrix3d) (b)).m12 + ((Matrix3d) (a)).m02 * ((Matrix3d) (b)).m22;
        m.m10 = ((Matrix3d) (a)).m10 * ((Matrix3d) (b)).m00 + ((Matrix3d) (a)).m11 * ((Matrix3d) (b)).m10 + ((Matrix3d) (a)).m12 * ((Matrix3d) (b)).m20;
        m.m11 = ((Matrix3d) (a)).m10 * ((Matrix3d) (b)).m01 + ((Matrix3d) (a)).m11 * ((Matrix3d) (b)).m11 + ((Matrix3d) (a)).m12 * ((Matrix3d) (b)).m21;
        m.m12 = ((Matrix3d) (a)).m10 * ((Matrix3d) (b)).m02 + ((Matrix3d) (a)).m11 * ((Matrix3d) (b)).m12 + ((Matrix3d) (a)).m12 * ((Matrix3d) (b)).m22;
        m.m20 = ((Matrix3d) (a)).m20 * ((Matrix3d) (b)).m00 + ((Matrix3d) (a)).m21 * ((Matrix3d) (b)).m10 + ((Matrix3d) (a)).m22 * ((Matrix3d) (b)).m20;
        m.m21 = ((Matrix3d) (a)).m20 * ((Matrix3d) (b)).m01 + ((Matrix3d) (a)).m21 * ((Matrix3d) (b)).m11 + ((Matrix3d) (a)).m22 * ((Matrix3d) (b)).m21;
        m.m22 = ((Matrix3d) (a)).m20 * ((Matrix3d) (b)).m02 + ((Matrix3d) (a)).m21 * ((Matrix3d) (b)).m12 + ((Matrix3d) (a)).m22 * ((Matrix3d) (b)).m22;
        return m;
    }

    public static Matrix33 interpolate(Matrix33 a, Matrix33 b, double portion)
    {
        Quaternion q = Quaternion.interpolate(a.getQuaternion(), b.getQuaternion(), portion);
        return new Matrix33(q);
    }

    public Interpolable interpolate(Interpolable b, double portion)
    {
        return interpolate(this, (Matrix33)b, portion);
    }

    public String toString()
    {
        return "edu.cmu.cs.stage3.math.Matrix33[rc00=" + super.m00 + ",rc01=" + super.m01 + ",rc02=" + super.m02 + ",rc10=" + super.m10 + ",rc11=" + super.m11 + ",rc12=" + super.m12 + ",rc20=" + super.m20 + ",rc21=" + super.m21 + ",rc22=" + super.m22 + "]";
    }

    public static Matrix33 valueOf(String s)
    {
        String markers[] = {
            "edu.cmu.cs.stage3.math.Matrix33[rc00=", ",rc01=", ",rc02=", ",rc10=", ",rc11=", ",rc12=", ",rc20=", ",rc21=", ",rc22=", "]"
        };
        double values[] = new double[markers.length - 1];
        for(int i = 0; i < values.length; i++)
        {
            int begin = s.indexOf(markers[i]) + markers[i].length();
            int end = s.indexOf(markers[i + 1]);
            values[i] = Double.valueOf(s.substring(begin, end)).doubleValue();
        }

        return new Matrix33(values);
    }

    public static final Matrix33 IDENTITY = new Matrix33();

}

package edu.cmu.cs.stage3.math;

import javax.vecmath.*;

// Referenced classes of package edu.cmu.cs.stage3.math:
//            Interpolable, AxisAngle, Quaternion, Vector4,
//            Vector3, Matrix33, SingularityException

class Matrix44 extends Matrix4d
    implements Interpolable
{

    public Matrix44()
    {
        this(1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D);
    }

    public Matrix44(double rc00, double rc01, double rc02, double rc03, double rc10, double rc11, double rc12,
            double rc13, double rc20, double rc21, double rc22, double rc23, double rc30, double rc31,
            double rc32, double rc33)
    {
        super.m00 = rc00;
        super.m01 = rc01;
        super.m02 = rc02;
        super.m03 = rc03;
        super.m10 = rc10;
        super.m11 = rc11;
        super.m12 = rc12;
        super.m13 = rc13;
        super.m20 = rc20;
        super.m21 = rc21;
        super.m22 = rc22;
        super.m23 = rc23;
        super.m30 = rc30;
        super.m31 = rc31;
        super.m32 = rc32;
        super.m33 = rc33;
    }

    public Matrix44(double row0[], double row1[], double row2[], double row3[])
    {
        this(row0[0], row0[1], row0[2], row0[3], row1[0], row1[1], row1[2], row1[3], row2[0], row2[1], row2[2], row2[3], row3[0], row3[1], row3[2], row3[3]);
    }

    public Matrix44(double a[])
    {
        this(a[0], a[1], a[2], a[3], a[4], a[5], a[6], a[7], a[8], a[9], a[10], a[11], a[12], a[13], a[14], a[15]);
    }

    public Matrix44(double m[][])
    {
        this(m[0], m[1], m[2], m[3]);
    }

    public Matrix44(Matrix4d m)
    {
        if(m != null)
            set(m);
        else
            throw new NullPointerException();
    }

    public Matrix44(Matrix3d axes, Vector3d t)
    {
        setAxes(axes);
        setPosition(t);
        super.m33 = 1.0D;
    }

    public Matrix44(AxisAngle aa, Vector3 t)
    {
        this(((Matrix3d) (aa.getMatrix33())), ((Vector3d) (t)));
    }

    public Matrix44(Quaternion q, Vector3 t)
    {
        this(((Matrix3d) (q.getMatrix33())), ((Vector3d) (t)));
    }

    public boolean equals(Object o)
    {
        if(o == this)
            return true;
        if(o != null && (o instanceof Matrix44))
        {
            Matrix44 m = (Matrix44)o;
            return super.m00 == ((Matrix4d) (m)).m00 && super.m01 == ((Matrix4d) (m)).m01 && super.m02 == ((Matrix4d) (m)).m02 && super.m03 == ((Matrix4d) (m)).m03 && super.m10 == ((Matrix4d) (m)).m10 && super.m11 == ((Matrix4d) (m)).m11 && super.m12 == ((Matrix4d) (m)).m12 && super.m13 == ((Matrix4d) (m)).m13 && super.m20 == ((Matrix4d) (m)).m20 && super.m21 == ((Matrix4d) (m)).m21 && super.m22 == ((Matrix4d) (m)).m22 && super.m23 == ((Matrix4d) (m)).m23 && super.m30 == ((Matrix4d) (m)).m30 && super.m31 == ((Matrix4d) (m)).m31 && super.m32 == ((Matrix4d) (m)).m32 && super.m33 == ((Matrix4d) (m)).m33;
        } else
        {
            return false;
        }
    }

    public double getItem(int i, int j)
    {
        switch(i)
        {
        default:
            break;

        case 0: // '\0'
            switch(j)
            {
            case 0: // '\0'
                return super.m00;

            case 1: // '\001'
                return super.m01;

            case 2: // '\002'
                return super.m02;

            case 3: // '\003'
                return super.m03;
            }
            break;

        case 1: // '\001'
            switch(j)
            {
            case 0: // '\0'
                return super.m10;

            case 1: // '\001'
                return super.m11;

            case 2: // '\002'
                return super.m12;

            case 3: // '\003'
                return super.m13;
            }
            break;

        case 2: // '\002'
            switch(j)
            {
            case 0: // '\0'
                return super.m20;

            case 1: // '\001'
                return super.m21;

            case 2: // '\002'
                return super.m22;

            case 3: // '\003'
                return super.m23;
            }
            break;

        case 3: // '\003'
            switch(j)
            {
            case 0: // '\0'
                return super.m30;

            case 1: // '\001'
                return super.m31;

            case 2: // '\002'
                return super.m32;

            case 3: // '\003'
                return super.m33;
            }
            break;
        }
        throw new IllegalArgumentException();
    }

    public void setItem(int i, int j, double v)
    {
        switch(i)
        {
        default:
            break;

        case 0: // '\0'
            switch(j)
            {
            case 0: // '\0'
                super.m00 = v;
                return;

            case 1: // '\001'
                super.m01 = v;
                return;

            case 2: // '\002'
                super.m02 = v;
                return;

            case 3: // '\003'
                super.m03 = v;
                return;
            }
            break;

        case 1: // '\001'
            switch(j)
            {
            case 0: // '\0'
                super.m10 = v;
                return;

            case 1: // '\001'
                super.m11 = v;
                return;

            case 2: // '\002'
                super.m12 = v;
                return;

            case 3: // '\003'
                super.m13 = v;
                return;
            }
            break;

        case 2: // '\002'
            switch(j)
            {
            case 0: // '\0'
                super.m20 = v;
                return;

            case 1: // '\001'
                super.m21 = v;
                return;

            case 2: // '\002'
                super.m22 = v;
                return;

            case 3: // '\003'
                super.m23 = v;
                return;
            }
            break;

        case 3: // '\003'
            switch(j)
            {
            case 0: // '\0'
                super.m30 = v;
                return;

            case 1: // '\001'
                super.m31 = v;
                return;

            case 2: // '\002'
                super.m32 = v;
                return;

            case 3: // '\003'
                super.m33 = v;
                return;
            }
            break;
        }
        throw new IllegalArgumentException();
    }

    public Vector4 getRow(int i)
    {
        switch(i)
        {
        case 0: // '\0'
            return new Vector4(super.m00, super.m01, super.m02, super.m03);

        case 1: // '\001'
            return new Vector4(super.m10, super.m11, super.m12, super.m13);

        case 2: // '\002'
            return new Vector4(super.m20, super.m21, super.m22, super.m23);

        case 3: // '\003'
            return new Vector4(super.m30, super.m31, super.m32, super.m33);
        }
        return null;
    }

    public void setRow(int i, Vector4 v)
    {
        switch(i)
        {
        case 0: // '\0'
            super.m00 = ((Tuple4d) (v)).x;
            super.m01 = ((Tuple4d) (v)).y;
            super.m02 = ((Tuple4d) (v)).z;
            super.m03 = ((Tuple4d) (v)).w;
            break;

        case 1: // '\001'
            super.m10 = ((Tuple4d) (v)).x;
            super.m11 = ((Tuple4d) (v)).y;
            super.m12 = ((Tuple4d) (v)).z;
            super.m13 = ((Tuple4d) (v)).w;
            break;

        case 2: // '\002'
            super.m20 = ((Tuple4d) (v)).x;
            super.m21 = ((Tuple4d) (v)).y;
            super.m22 = ((Tuple4d) (v)).z;
            super.m23 = ((Tuple4d) (v)).w;
            break;

        case 3: // '\003'
            super.m30 = ((Tuple4d) (v)).x;
            super.m31 = ((Tuple4d) (v)).y;
            super.m32 = ((Tuple4d) (v)).z;
            super.m33 = ((Tuple4d) (v)).w;
            break;

        default:
            throw new IndexOutOfBoundsException();
        }
    }

    public Vector4 getColumn(int i)
    {
        switch(i)
        {
        case 0: // '\0'
            return new Vector4(super.m00, super.m10, super.m20, super.m30);

        case 1: // '\001'
            return new Vector4(super.m01, super.m11, super.m21, super.m31);

        case 2: // '\002'
            return new Vector4(super.m02, super.m12, super.m22, super.m32);

        case 3: // '\003'
            return new Vector4(super.m03, super.m13, super.m23, super.m33);
        }
        throw new IndexOutOfBoundsException();
    }

    public void setColumn(int i, Vector4 v)
    {
        switch(i)
        {
        case 0: // '\0'
            super.m00 = ((Tuple4d) (v)).x;
            super.m10 = ((Tuple4d) (v)).y;
            super.m20 = ((Tuple4d) (v)).z;
            super.m30 = ((Tuple4d) (v)).w;
            break;

        case 1: // '\001'
            super.m01 = ((Tuple4d) (v)).x;
            super.m11 = ((Tuple4d) (v)).y;
            super.m21 = ((Tuple4d) (v)).z;
            super.m31 = ((Tuple4d) (v)).w;
            break;

        case 2: // '\002'
            super.m02 = ((Tuple4d) (v)).x;
            super.m12 = ((Tuple4d) (v)).y;
            super.m22 = ((Tuple4d) (v)).z;
            super.m32 = ((Tuple4d) (v)).w;
            break;

        case 3: // '\003'
            super.m03 = ((Tuple4d) (v)).x;
            super.m13 = ((Tuple4d) (v)).y;
            super.m23 = ((Tuple4d) (v)).z;
            super.m33 = ((Tuple4d) (v)).w;
            break;

        default:
            throw new IndexOutOfBoundsException();
        }
    }

    public double[] getArray(boolean rowMajor)
    {
        if(rowMajor)
        {
            double a[] = {
                super.m00, super.m01, super.m02, super.m03, super.m10, super.m11, super.m12, super.m13, super.m20, super.m21,
                super.m22, super.m23, super.m30, super.m31, super.m32, super.m33
            };
            return a;
        } else
        {
            double a[] = {
                super.m00, super.m10, super.m20, super.m30, super.m01, super.m11, super.m21, super.m31, super.m02, super.m12,
                super.m22, super.m32, super.m03, super.m13, super.m23, super.m33
            };
            return a;
        }
    }

    public void setArray(double a[], boolean rowMajor)
    {
        if(rowMajor)
        {
            super.m00 = a[0];
            super.m01 = a[1];
            super.m02 = a[2];
            super.m03 = a[3];
            super.m10 = a[4];
            super.m11 = a[5];
            super.m12 = a[6];
            super.m13 = a[7];
            super.m20 = a[8];
            super.m21 = a[9];
            super.m22 = a[10];
            super.m23 = a[11];
            super.m30 = a[12];
            super.m31 = a[13];
            super.m32 = a[14];
            super.m33 = a[15];
        } else
        {
            super.m00 = a[0];
            super.m01 = a[4];
            super.m02 = a[8];
            super.m03 = a[12];
            super.m10 = a[1];
            super.m11 = a[5];
            super.m12 = a[9];
            super.m13 = a[13];
            super.m20 = a[2];
            super.m21 = a[6];
            super.m22 = a[10];
            super.m23 = a[14];
            super.m30 = a[3];
            super.m31 = a[7];
            super.m32 = a[11];
            super.m33 = a[15];
        }
    }

    public double[][] getMatrix()
    {
        double m[][] = {
            {
                super.m00, super.m01, super.m02, super.m03
            }, {
                super.m10, super.m11, super.m12, super.m13
            }, {
                super.m20, super.m21, super.m22, super.m23
            }, {
                super.m30, super.m31, super.m32, super.m33
            }
        };
        return m;
    }

    public void setMatrix(double m[][])
    {
        super.m00 = m[0][0];
        super.m01 = m[0][1];
        super.m02 = m[0][2];
        super.m03 = m[0][3];
        super.m10 = m[1][0];
        super.m11 = m[1][1];
        super.m12 = m[1][2];
        super.m13 = m[1][3];
        super.m20 = m[2][0];
        super.m21 = m[2][1];
        super.m22 = m[2][2];
        super.m23 = m[2][3];
        super.m30 = m[3][0];
        super.m31 = m[3][1];
        super.m32 = m[3][2];
        super.m33 = m[3][3];
    }

    public void set(Matrix44 other)
    {
        super.m00 = ((Matrix4d) (other)).m00;
        super.m01 = ((Matrix4d) (other)).m01;
        super.m02 = ((Matrix4d) (other)).m02;
        super.m03 = ((Matrix4d) (other)).m03;
        super.m10 = ((Matrix4d) (other)).m10;
        super.m11 = ((Matrix4d) (other)).m11;
        super.m12 = ((Matrix4d) (other)).m12;
        super.m13 = ((Matrix4d) (other)).m13;
        super.m20 = ((Matrix4d) (other)).m20;
        super.m21 = ((Matrix4d) (other)).m21;
        super.m22 = ((Matrix4d) (other)).m22;
        super.m23 = ((Matrix4d) (other)).m23;
        super.m30 = ((Matrix4d) (other)).m30;
        super.m31 = ((Matrix4d) (other)).m31;
        super.m32 = ((Matrix4d) (other)).m32;
        super.m33 = ((Matrix4d) (other)).m33;
    }

    public Vector3 getPosition()
    {
        return new Vector3(super.m30, super.m31, super.m32);
    }

    public void setPosition(Vector3d position)
    {
        super.m30 = ((Tuple3d) (position)).x;
        super.m31 = ((Tuple3d) (position)).y;
        super.m32 = ((Tuple3d) (position)).z;
    }

    public Matrix33 getAxes()
    {
        return new Matrix33(super.m00, super.m01, super.m02, super.m10, super.m11, super.m12, super.m20, super.m21, super.m22);
    }

    public void setAxes(Matrix3d axes)
    {
        super.m00 = axes.m00;
        super.m01 = axes.m01;
        super.m02 = axes.m02;
        super.m10 = axes.m10;
        super.m11 = axes.m11;
        super.m12 = axes.m12;
        super.m20 = axes.m20;
        super.m21 = axes.m21;
        super.m22 = axes.m22;
    }

    public void translate(Vector3d vector)
    {
        if(((Tuple3d) (vector)).x != 0.0D)
        {
            super.m00 += super.m03 * ((Tuple3d) (vector)).x;
            super.m10 += super.m13 * ((Tuple3d) (vector)).x;
            super.m20 += super.m23 * ((Tuple3d) (vector)).x;
            super.m30 += super.m33 * ((Tuple3d) (vector)).x;
        }
        if(((Tuple3d) (vector)).y != 0.0D)
        {
            super.m01 += super.m03 * ((Tuple3d) (vector)).y;
            super.m11 += super.m13 * ((Tuple3d) (vector)).y;
            super.m21 += super.m23 * ((Tuple3d) (vector)).y;
            super.m31 += super.m33 * ((Tuple3d) (vector)).y;
        }
        if(((Tuple3d) (vector)).z != 0.0D)
        {
            super.m02 += super.m03 * ((Tuple3d) (vector)).z;
            super.m12 += super.m13 * ((Tuple3d) (vector)).z;
            super.m22 += super.m23 * ((Tuple3d) (vector)).z;
            super.m32 += super.m33 * ((Tuple3d) (vector)).z;
        }
    }

    public void rotateX(double angle)
    {
        double cosAngle = Math.cos(angle);
        double sinAngle = Math.sin(angle);
        for(int i = 0; i < 4; i++)
        {
            double tmp = getItem(i, 1);
            setItem(i, 1, tmp * cosAngle - getItem(i, 2) * sinAngle);
            setItem(i, 2, tmp * sinAngle + getItem(i, 2) * cosAngle);
        }

    }

    public void rotateY(double angle)
    {
        double cosAngle = Math.cos(angle);
        double sinAngle = Math.sin(angle);
        for(int i = 0; i < 4; i++)
        {
            double tmp = getItem(i, 0);
            setItem(i, 0, tmp * cosAngle + getItem(i, 2) * sinAngle);
            setItem(i, 2, -tmp * sinAngle + getItem(i, 2) * cosAngle);
        }

    }

    public void rotateZ(double angle)
    {
        double cosAngle = Math.cos(angle);
        double sinAngle = Math.sin(angle);
        for(int i = 0; i < 4; i++)
        {
            double tmp = getItem(i, 0);
            setItem(i, 0, tmp * cosAngle - getItem(i, 1) * sinAngle);
            setItem(i, 1, tmp * sinAngle + getItem(i, 1) * cosAngle);
        }

    }

    public void scale(Vector3d vector)
    {
        if(((Tuple3d) (vector)).x != 1.0D)
        {
            super.m00 *= ((Tuple3d) (vector)).x;
            super.m10 *= ((Tuple3d) (vector)).x;
            super.m20 *= ((Tuple3d) (vector)).x;
            super.m30 *= ((Tuple3d) (vector)).x;
        }
        if(((Tuple3d) (vector)).y != 1.0D)
        {
            super.m01 *= ((Tuple3d) (vector)).y;
            super.m11 *= ((Tuple3d) (vector)).y;
            super.m21 *= ((Tuple3d) (vector)).y;
            super.m31 *= ((Tuple3d) (vector)).y;
        }
        if(((Tuple3d) (vector)).z != 1.0D)
        {
            super.m02 *= ((Tuple3d) (vector)).z;
            super.m12 *= ((Tuple3d) (vector)).z;
            super.m22 *= ((Tuple3d) (vector)).z;
            super.m32 *= ((Tuple3d) (vector)).z;
        }
    }

    public void transform(Matrix4d m)
    {
        set(multiply(this, m));
    }

    public void rotate(Vector3d axis, double angle)
    {
        if(axis.equals(Vector3.X_AXIS))
            rotateX(angle);
        else
        if(axis.equals(Vector3.Y_AXIS))
            rotateY(angle);
        else
        if(axis.equals(Vector3.Z_AXIS))
            rotateZ(angle);
        else
        if(axis.equals(Vector3.X_AXIS_NEGATIVE))
            rotateX(-angle);
        else
        if(axis.equals(Vector3.Y_AXIS_NEGATIVE))
            rotateY(-angle);
        else
        if(axis.equals(Vector3.Z_AXIS_NEGATIVE))
        {
            rotateZ(-angle);
        } else
        {
            Matrix44 m = new Matrix44();
            double cosAngle = Math.cos(angle);
            double sinAngle = Math.sin(angle);
            m.m00 = ((Tuple3d) (axis)).x * ((Tuple3d) (axis)).x + cosAngle * (1.0D - ((Tuple3d) (axis)).x * ((Tuple3d) (axis)).x);
            m.m01 = ((Tuple3d) (axis)).x * ((Tuple3d) (axis)).y * (1.0D - cosAngle) + ((Tuple3d) (axis)).z * sinAngle;
            m.m02 = ((Tuple3d) (axis)).z * ((Tuple3d) (axis)).x * (1.0D - cosAngle) - ((Tuple3d) (axis)).y * sinAngle;
            m.m10 = ((Tuple3d) (axis)).x * ((Tuple3d) (axis)).y * (1.0D - cosAngle) - ((Tuple3d) (axis)).z * sinAngle;
            m.m11 = ((Tuple3d) (axis)).y * ((Tuple3d) (axis)).y + cosAngle * (1.0D - ((Tuple3d) (axis)).y * ((Tuple3d) (axis)).y);
            m.m12 = ((Tuple3d) (axis)).y * ((Tuple3d) (axis)).z * (1.0D - cosAngle) + ((Tuple3d) (axis)).x * sinAngle;
            m.m20 = ((Tuple3d) (axis)).z * ((Tuple3d) (axis)).x * (1.0D - cosAngle) + ((Tuple3d) (axis)).y * sinAngle;
            m.m21 = ((Tuple3d) (axis)).y * ((Tuple3d) (axis)).z * (1.0D - cosAngle) - ((Tuple3d) (axis)).x * sinAngle;
            m.m22 = ((Tuple3d) (axis)).z * ((Tuple3d) (axis)).z + cosAngle * (1.0D - ((Tuple3d) (axis)).z * ((Tuple3d) (axis)).z);
            transform(m);
        }
    }

    public static Matrix44 multiply(Matrix4d a, Matrix4d b)
    {
        Matrix44 m = new Matrix44();
        m.m00 = a.m00 * b.m00 + a.m01 * b.m10 + a.m02 * b.m20 + a.m03 * b.m30;
        m.m01 = a.m00 * b.m01 + a.m01 * b.m11 + a.m02 * b.m21 + a.m03 * b.m31;
        m.m02 = a.m00 * b.m02 + a.m01 * b.m12 + a.m02 * b.m22 + a.m03 * b.m32;
        m.m03 = a.m00 * b.m03 + a.m01 * b.m13 + a.m02 * b.m23 + a.m03 * b.m33;
        m.m10 = a.m10 * b.m00 + a.m11 * b.m10 + a.m12 * b.m20 + a.m13 * b.m30;
        m.m11 = a.m10 * b.m01 + a.m11 * b.m11 + a.m12 * b.m21 + a.m13 * b.m31;
        m.m12 = a.m10 * b.m02 + a.m11 * b.m12 + a.m12 * b.m22 + a.m13 * b.m32;
        m.m13 = a.m10 * b.m03 + a.m11 * b.m13 + a.m12 * b.m23 + a.m13 * b.m33;
        m.m20 = a.m20 * b.m00 + a.m21 * b.m10 + a.m22 * b.m20 + a.m23 * b.m30;
        m.m21 = a.m20 * b.m01 + a.m21 * b.m11 + a.m22 * b.m21 + a.m23 * b.m31;
        m.m22 = a.m20 * b.m02 + a.m21 * b.m12 + a.m22 * b.m22 + a.m23 * b.m32;
        m.m23 = a.m20 * b.m03 + a.m21 * b.m13 + a.m22 * b.m23 + a.m23 * b.m33;
        m.m30 = a.m30 * b.m00 + a.m31 * b.m10 + a.m32 * b.m20 + a.m33 * b.m30;
        m.m31 = a.m30 * b.m01 + a.m31 * b.m11 + a.m32 * b.m21 + a.m33 * b.m31;
        m.m32 = a.m30 * b.m02 + a.m31 * b.m12 + a.m32 * b.m22 + a.m33 * b.m32;
        m.m33 = a.m30 * b.m03 + a.m31 * b.m13 + a.m32 * b.m23 + a.m33 * b.m33;
        return m;
    }

    public static Matrix44 transpose(Matrix44 m)
    {
        Matrix44 mTranspose = new Matrix44();
        mTranspose.m00 = ((Matrix4d) (m)).m00;
        mTranspose.m01 = ((Matrix4d) (m)).m10;
        mTranspose.m02 = ((Matrix4d) (m)).m20;
        mTranspose.m03 = ((Matrix4d) (m)).m30;
        mTranspose.m10 = ((Matrix4d) (m)).m01;
        mTranspose.m11 = ((Matrix4d) (m)).m11;
        mTranspose.m12 = ((Matrix4d) (m)).m21;
        mTranspose.m13 = ((Matrix4d) (m)).m31;
        mTranspose.m20 = ((Matrix4d) (m)).m02;
        mTranspose.m21 = ((Matrix4d) (m)).m12;
        mTranspose.m22 = ((Matrix4d) (m)).m22;
        mTranspose.m23 = ((Matrix4d) (m)).m32;
        mTranspose.m30 = ((Matrix4d) (m)).m03;
        mTranspose.m31 = ((Matrix4d) (m)).m13;
        mTranspose.m32 = ((Matrix4d) (m)).m23;
        mTranspose.m33 = ((Matrix4d) (m)).m33;
        return mTranspose;
    }

    public void divide(double denom)
    {
        super.m00 /= denom;
        super.m01 /= denom;
        super.m02 /= denom;
        super.m03 /= denom;
        super.m10 /= denom;
        super.m11 /= denom;
        super.m12 /= denom;
        super.m13 /= denom;
        super.m20 /= denom;
        super.m21 /= denom;
        super.m22 /= denom;
        super.m23 /= denom;
        super.m30 /= denom;
        super.m31 /= denom;
        super.m32 /= denom;
        super.m33 /= denom;
    }

    private static Vector3 ROW(Vector4 a, Vector4 b, Vector4 c, int index)
    {
        return new Vector3(a.getItem(index), b.getItem(index), c.getItem(index));
    }

    private static double DET(Vector4 a, Vector4 b, Vector4 c, int i, int j, int k)
    {
        return Vector3.dotProduct(ROW(a, b, c, i), Vector3.crossProduct(ROW(a, b, c, j), ROW(a, b, c, k)));
    }

    private static Vector4 cross(Vector4 a, Vector4 b, Vector4 c)
    {
        Vector4 result = new Vector4();
        result.x = DET(a, b, c, 1, 2, 3);
        result.y = -DET(a, b, c, 0, 2, 3);
        result.z = DET(a, b, c, 0, 1, 3);
        result.w = -DET(a, b, c, 0, 1, 2);
        return result;
    }

    public static Matrix44 adjoint(Matrix44 m)
    {
        Matrix44 mAdjoint = new Matrix44();
        mAdjoint.setRow(0, cross(m.getRow(1), m.getRow(2), m.getRow(3)));
        mAdjoint.setRow(1, Vector4.negate(cross(m.getRow(0), m.getRow(2), m.getRow(3))));
        mAdjoint.setRow(2, cross(m.getRow(0), m.getRow(1), m.getRow(3)));
        mAdjoint.setRow(3, Vector4.negate(cross(m.getRow(0), m.getRow(1), m.getRow(2))));
        return mAdjoint;
    }

    public static Matrix44 invert(Matrix44 m)
    {
        Matrix44 mInverse = new Matrix44();
        if(Math.abs(((Matrix4d) (m)).m03) > 0.001D || Math.abs(((Matrix4d) (m)).m13) > 0.001D || Math.abs(((Matrix4d) (m)).m23) > 0.001D || Math.abs(((Matrix4d) (m)).m33 - 1.0D) > 0.001D)
        {
            Matrix44 adj = adjoint(m);
            double mDet = Vector4.dotProduct(adj.getRow(0), m.getRow(0));
            if(mDet == 0.0D)
                throw new SingularityException();
            mInverse = transpose(adj);
            mInverse.divide(mDet);
        } else
        {
            double fDetInv = 1.0D / ((((Matrix4d) (m)).m00 * (((Matrix4d) (m)).m11 * ((Matrix4d) (m)).m22 - ((Matrix4d) (m)).m12 * ((Matrix4d) (m)).m21) - ((Matrix4d) (m)).m01 * (((Matrix4d) (m)).m10 * ((Matrix4d) (m)).m22 - ((Matrix4d) (m)).m12 * ((Matrix4d) (m)).m20)) + ((Matrix4d) (m)).m02 * (((Matrix4d) (m)).m10 * ((Matrix4d) (m)).m21 - ((Matrix4d) (m)).m11 * ((Matrix4d) (m)).m20));
            mInverse.m00 = fDetInv * (((Matrix4d) (m)).m11 * ((Matrix4d) (m)).m22 - ((Matrix4d) (m)).m12 * ((Matrix4d) (m)).m21);
            mInverse.m01 = -fDetInv * (((Matrix4d) (m)).m01 * ((Matrix4d) (m)).m22 - ((Matrix4d) (m)).m02 * ((Matrix4d) (m)).m21);
            mInverse.m02 = fDetInv * (((Matrix4d) (m)).m01 * ((Matrix4d) (m)).m12 - ((Matrix4d) (m)).m02 * ((Matrix4d) (m)).m11);
            mInverse.m03 = 0.0D;
            mInverse.m10 = -fDetInv * (((Matrix4d) (m)).m10 * ((Matrix4d) (m)).m22 - ((Matrix4d) (m)).m12 * ((Matrix4d) (m)).m20);
            mInverse.m11 = fDetInv * (((Matrix4d) (m)).m00 * ((Matrix4d) (m)).m22 - ((Matrix4d) (m)).m02 * ((Matrix4d) (m)).m20);
            mInverse.m12 = -fDetInv * (((Matrix4d) (m)).m00 * ((Matrix4d) (m)).m12 - ((Matrix4d) (m)).m02 * ((Matrix4d) (m)).m10);
            mInverse.m13 = 0.0D;
            mInverse.m20 = fDetInv * (((Matrix4d) (m)).m10 * ((Matrix4d) (m)).m21 - ((Matrix4d) (m)).m11 * ((Matrix4d) (m)).m20);
            mInverse.m21 = -fDetInv * (((Matrix4d) (m)).m00 * ((Matrix4d) (m)).m21 - ((Matrix4d) (m)).m01 * ((Matrix4d) (m)).m20);
            mInverse.m22 = fDetInv * (((Matrix4d) (m)).m00 * ((Matrix4d) (m)).m11 - ((Matrix4d) (m)).m01 * ((Matrix4d) (m)).m10);
            mInverse.m23 = 0.0D;
            mInverse.m30 = -(((Matrix4d) (m)).m30 * ((Matrix4d) (mInverse)).m00 + ((Matrix4d) (m)).m31 * ((Matrix4d) (mInverse)).m10 + ((Matrix4d) (m)).m32 * ((Matrix4d) (mInverse)).m20);
            mInverse.m31 = -(((Matrix4d) (m)).m30 * ((Matrix4d) (mInverse)).m01 + ((Matrix4d) (m)).m31 * ((Matrix4d) (mInverse)).m11 + ((Matrix4d) (m)).m32 * ((Matrix4d) (mInverse)).m21);
            mInverse.m32 = -(((Matrix4d) (m)).m30 * ((Matrix4d) (mInverse)).m02 + ((Matrix4d) (m)).m31 * ((Matrix4d) (mInverse)).m12 + ((Matrix4d) (m)).m32 * ((Matrix4d) (mInverse)).m22);
            mInverse.m33 = 1.0D;
        }
        return mInverse;
    }

    public static Matrix44 interpolate(Matrix44 a, Matrix44 b, double portion)
    {
        Vector3 t = Vector3.interpolate(a.getPosition(), b.getPosition(), portion);
        Matrix33 m = Matrix33.interpolate(a.getAxes(), b.getAxes(), portion);
        return new Matrix44(m, t);
    }

    public Interpolable interpolate(Interpolable b, double portion)
    {
        return interpolate(this, (Matrix44)b, portion);
    }

    public String toString()
    {
        return "edu.cmu.cs.stage3.math.Matrix44[rc00=" + super.m00 + ",rc01=" + super.m01 + ",rc02=" + super.m02 + ",rc03=" + super.m03 + ",rc10=" + super.m10 + ",rc11=" + super.m11 + ",rc12=" + super.m12 + ",rc13=" + super.m13 + ",rc20=" + super.m20 + ",rc21=" + super.m21 + ",rc22=" + super.m22 + ",rc23=" + super.m23 + ",rc30=" + super.m30 + ",rc31=" + super.m31 + ",rc32=" + super.m32 + ",rc33=" + super.m33 + "]";
    }

    public static Matrix44 valueOf(String s)
    {
        String markers[] = {
            "edu.cmu.cs.stage3.math.Matrix44[rc00=", ",rc01=", ",rc02=", ",rc03=", ",rc10=", ",rc11=", ",rc12=", ",rc13=", ",rc20=", ",rc21=",
            ",rc22=", ",rc23=", ",rc30=", ",rc31=", ",rc32=", ",rc33=", "]"
        };
        double values[] = new double[markers.length - 1];
        for(int i = 0; i < values.length; i++)
        {
            int begin = s.indexOf(markers[i]) + markers[i].length();
            int end = s.indexOf(markers[i + 1]);
            String v = s.substring(begin, end);
            Double d = Double.valueOf(v);
            values[i] = d.doubleValue();
        }

        return new Matrix44(values);
    }

    public static final Matrix44 IDENTITY = new Matrix44();

}

package edu.cmu.cs.stage3.math;


class Measurement extends Number
{

    public Measurement(double value, double factor)
    {
        m_value = value;
        m_factor = factor;
    }

    public byte byteValue()
    {
        return (byte)(int)doubleValue();
    }

    public double doubleValue()
    {
        return m_value * m_factor;
    }

    public float floatValue()
    {
        return (float)doubleValue();
    }

    public int intValue()
    {
        return (int)doubleValue();
    }

    public long longValue()
    {
        return (long)doubleValue();
    }

    public short shortValue()
    {
        return (short)(int)doubleValue();
    }

    private double m_value;
    private double m_factor;
}

package edu.cmu.cs.stage3.math;

import javax.vecmath.Tuple3d;
import javax.vecmath.Vector3d;

// Referenced classes of package edu.cmu.cs.stage3.math:
//            MathUtilities, Ray

class Plane
    implements Cloneable
{

    public Plane()
    {
        this(0.0D, 0.0D, 0.0D, 0.0D);
    }

    public Plane(double a, double b, double c, double d)
    {
        m_a = a;
        m_b = b;
        m_c = c;
        m_d = d;
    }

    public Plane(double array[])
    {
        this(array[0], array[1], array[2], array[3]);
    }

    public Plane(Vector3d position, Vector3d normal)
    {
        this(((Tuple3d) (normal)).x, ((Tuple3d) (normal)).y, ((Tuple3d) (normal)).z, -(((Tuple3d) (normal)).x * ((Tuple3d) (position)).x + ((Tuple3d) (normal)).y * ((Tuple3d) (position)).y + ((Tuple3d) (normal)).z * ((Tuple3d) (position)).z));
    }

    public Plane(Vector3d v0, Vector3d v1, Vector3d v2)
    {
        this(v0, MathUtilities.normalizeV(MathUtilities.crossProduct(MathUtilities.normalizeV(MathUtilities.subtract(v2, v1)), MathUtilities.normalizeV(MathUtilities.subtract(v0, v1)))));
    }

    public synchronized Object clone()
    {
        try
        {
            return (Plane)super.clone();
        }
        catch(CloneNotSupportedException e)
        {
            throw new InternalError();
        }
    }

    public boolean equals(Object o)
    {
        if(o == this)
            return true;
        if(o != null && (o instanceof Plane))
        {
            Plane plane = (Plane)o;
            return m_a == plane.m_a && m_b == plane.m_b && m_c == plane.m_c && m_d == plane.m_d;
        } else
        {
            return false;
        }
    }

    public double intersect(Ray ray)
    {
        Vector3d p = ray.getOrigin();
        Vector3d d = ray.getDirection();
        double denom = m_a * ((Tuple3d) (d)).x + m_b * ((Tuple3d) (d)).y + m_c * ((Tuple3d) (d)).z;
        if(denom == 0.0D)
        {
            return (0.0D / 0.0D);
        } else
        {
            double numer = m_a * ((Tuple3d) (p)).x + m_b * ((Tuple3d) (p)).y + m_c * ((Tuple3d) (p)).z + m_d;
            return -numer / denom;
        }
    }

    public String toString()
    {
        return "edu.cmu.cs.stage3.math.Plane[a=" + m_a + ",b=" + m_b + ",c=" + m_c + ",d=" + m_d + "]";
    }

    public static Plane valueOf(String s)
    {
        String markers[] = {
            "edu.cmu.cs.stage3.math.Plane[a=", ",b=", ",c=", ",d=", "]"
        };
        double values[] = new double[markers.length - 1];
        for(int i = 0; i < values.length; i++)
        {
            int begin = s.indexOf(markers[i]) + markers[i].length();
            int end = s.indexOf(markers[i + 1]);
            values[i] = Double.valueOf(s.substring(begin, end)).doubleValue();
        }

        return new Plane(values);
    }

    private double m_a;
    private double m_b;
    private double m_c;
    private double m_d;
}

package edu.cmu.cs.stage3.math;


interface Polynomial
{

    public abstract double evaluate(double d);

    public abstract double evaluateDerivative(double d);
}

class HermiteCubic extends BasisMatrixCubic
{

    public HermiteCubic(Vector4d g)
    {
        super(s_h, g);
    }

    public HermiteCubic(double g0, double g1, double g2, double g3)
    {
        this(new Vector4d(g0, g1, g2, g3));
    }

    private static final Matrix4d s_h = new Matrix4d(2D, -2D, 1.0D, 1.0D, -3D, 3D, -2D, -1D, 0.0D, 0.0D, 1.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D);

}

package edu.cmu.cs.stage3.math;

import javax.vecmath.Matrix3d;
import javax.vecmath.Tuple3d;

// Referenced classes of package edu.cmu.cs.stage3.math:
//            Interpolable, AxisAngle, Vector3, EulerAngles,
//            Matrix33

class Quaternion
    implements Cloneable, Interpolable
{

    public Quaternion()
    {
        x = 0.0D;
        y = 0.0D;
        z = 0.0D;
        w = 1.0D;
    }

    public Quaternion(double x, double y, double z, double w)
    {
        this.x = 0.0D;
        this.y = 0.0D;
        this.z = 0.0D;
        this.w = 1.0D;
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Quaternion(double v[])
    {
        this(v[0], v[1], v[2], v[3]);
    }

    public Quaternion(Matrix33 m)
    {
        x = 0.0D;
        y = 0.0D;
        z = 0.0D;
        w = 1.0D;
        setMatrix33(m);
    }

    public Quaternion(AxisAngle aa)
    {
        x = 0.0D;
        y = 0.0D;
        z = 0.0D;
        w = 1.0D;
        setAxisAngle(aa);
    }

    public Quaternion(EulerAngles ea)
    {
        x = 0.0D;
        y = 0.0D;
        z = 0.0D;
        w = 1.0D;
        setEulerAngles(ea);
    }

    public synchronized Object clone()
    {
        try
        {
            return super.clone();
        }
        catch(CloneNotSupportedException e)
        {
            throw new InternalError();
        }
    }

    public boolean equals(Object o)
    {
        if(o == this)
            return true;
        if(o != null && (o instanceof Quaternion))
        {
            Quaternion q = (Quaternion)o;
            return x == q.x && y == q.y && z == q.z && w == q.w;
        } else
        {
            return false;
        }
    }

    public double[] getArray()
    {
        double a[] = {
            x, y, z, w
        };
        return a;
    }

    public void setArray(double a[])
    {
        x = a[0];
        y = a[1];
        z = a[2];
        w = a[3];
    }

    public boolean equals(Quaternion q)
    {
        return x == q.x && y == q.y && z == q.z && w == q.w;
    }

    public AxisAngle getAxisAngle()
    {
        return new AxisAngle(this);
    }

    public void setAxisAngle(AxisAngle aa)
    {
        double halfAngle = aa.getAngle() * 0.5D;
        double cosHalfAngle = Math.cos(halfAngle);
        double sinHalfAngle = Math.sin(halfAngle);
        javax.vecmath.Vector3d normalizedAxis = Vector3.normalizeV(aa.getAxis());
        w = cosHalfAngle;
        x = sinHalfAngle * ((Tuple3d) (aa.m_axis)).x;
        y = sinHalfAngle * ((Tuple3d) (aa.m_axis)).y;
        z = sinHalfAngle * ((Tuple3d) (aa.m_axis)).z;
    }

    public EulerAngles getEulerAngles()
    {
        return new EulerAngles(this);
    }

    public void setEulerAngles(EulerAngles ea)
    {
        Matrix33 m = new Matrix33();
        m.rotateX(ea.pitch);
        m.rotateY(ea.yaw);
        m.rotateZ(ea.roll);
        setMatrix33(m);
    }

    public Matrix33 getMatrix33()
    {
        Matrix33 m = new Matrix33();
        m.setQuaternion(this);
        return m;
    }

    public void setMatrix33(Matrix33 m)
    {
        double tr = ((Matrix3d) (m)).m00 + ((Matrix3d) (m)).m11 + ((Matrix3d) (m)).m22;
        if(tr > 0.0D)
        {
            double s = Math.sqrt(tr + 1.0D);
            w = s * 0.5D;
            s = 0.5D / s;
            x = (((Matrix3d) (m)).m21 - ((Matrix3d) (m)).m12) * s;
            y = (((Matrix3d) (m)).m02 - ((Matrix3d) (m)).m20) * s;
            z = (((Matrix3d) (m)).m10 - ((Matrix3d) (m)).m01) * s;
        } else
        {
            int nxt[] = {
                1, 2, 0
            };
            double a[][] = m.getMatrix();
            int i = 0;
            if(a[1][1] > a[0][0])
                i = 1;
            if(a[2][2] > a[i][i])
                i = 2;
            int j = nxt[i];
            int k = nxt[j];
            double s = Math.sqrt((a[i][i] - (a[j][j] + a[k][k])) + 1.0D);
            double q[] = new double[4];
            q[i] = s * 0.5D;
            if(s != 0.0D)
                s = 0.5D / s;
            q[3] = (a[k][j] - a[j][k]) * s;
            q[j] = (a[j][i] + a[i][j]) * s;
            q[k] = (a[k][i] + a[i][k]) * s;
            setArray(q);
        }
    }

    public void normalize()
    {
        double lengthSquared = x * x + y * y + z * z + w * w;
        if(lengthSquared != 1.0D)
        {
            double length = Math.sqrt(lengthSquared);
            x /= length;
            y /= length;
            z /= length;
            w /= length;
        }
    }

    public static Quaternion multiply(Quaternion a, Quaternion b)
    {
        double A = (a.w + a.x) * (b.w + b.x);
        double B = (a.z - a.y) * (b.y - b.z);
        double C = (a.x - a.w) * (b.y - b.z);
        double D = (a.y + a.z) * (b.x - b.w);
        double E = (a.x + a.z) * (b.x + b.y);
        double F = (a.x - a.z) * (b.x - b.y);
        double G = (a.w + a.y) * (b.w - b.z);
        double H = (a.w - a.y) * (b.w + b.z);
        Quaternion q = new Quaternion();
        q.w = B + ((-E - F) + G + H) / 2D;
        q.x = A - (E + F + G + H) / 2D;
        q.y = -C + (((E - F) + G) - H) / 2D;
        q.z = -D + ((E - F - G) + H) / 2D;
        return q;
    }

    public static Quaternion interpolate(Quaternion a, Quaternion b, double portion)
    {
        if(portion <= 0.0D)
            return (Quaternion)a.clone();
        if(portion >= 1.0D)
            return (Quaternion)b.clone();
        double cosom = a.x * b.x + a.y * b.y + a.z * b.z + a.w * b.w;
        Quaternion b1;
        if(cosom < 0.0D)
        {
            cosom = -cosom;
            b1 = new Quaternion(-b.x, -b.y, -b.z, -b.w);
        } else
        {
            b1 = b;
        }
        double scale0;
        double scale1;
        if(1.0D - cosom > Double.MIN_VALUE)
        {
            double omega = Math.acos(cosom);
            double sinom = Math.sin(omega);
            scale0 = Math.sin((1.0D - portion) * omega) / sinom;
            scale1 = Math.sin(portion * omega) / sinom;
        } else
        {
            scale0 = 1.0D - portion;
            scale1 = portion;
        }
        Quaternion q = new Quaternion();
        q.x = scale0 * a.x + scale1 * b1.x;
        q.y = scale0 * a.y + scale1 * b1.y;
        q.z = scale0 * a.z + scale1 * b1.z;
        q.w = scale0 * a.w + scale1 * b1.w;
        return q;
    }

    public Interpolable interpolate(Interpolable b, double portion)
    {
        return interpolate(this, (Quaternion)b, portion);
    }

    public String toString()
    {
        return "edu.cmu.cs.stage3.math.Quaternion[x=" + x + ",y=" + y + ",z=" + z + ",w=" + w + "]";
    }

    public static Quaternion valueOf(String s)
    {
        String markers[] = {
            "edu.cmu.cs.stage3.math.Quaternion[x=", ",y=", ",z=", ",w=", "]"
        };
        double values[] = new double[markers.length - 1];
        for(int i = 0; i < values.length; i++)
        {
            int begin = s.indexOf(markers[i]) + markers[i].length();
            int end = s.indexOf(markers[i + 1]);
            values[i] = Double.valueOf(s.substring(begin, end)).doubleValue();
        }

        return new Quaternion(values);
    }

    public double x;
    public double y;
    public double z;
    public double w;
}

package edu.cmu.cs.stage3.math;

import javax.vecmath.*;

// Referenced classes of package edu.cmu.cs.stage3.math:
//            MathUtilities

class Ray
    implements Cloneable
{

    public Ray()
    {
        this(new Vector3d(0.0D, 0.0D, 0.0D), new Vector3d(0.0D, 0.0D, 1.0D));
    }

    public Ray(Vector3d origin, Vector3d direction)
    {
        m_origin = null;
        m_direction = null;
        setOrigin(origin);
        setDirection(direction);
    }

    public synchronized Object clone()
    {
        try
        {
            Ray ray = (Ray)super.clone();
            ray.setOrigin(new Vector3d(m_origin));
            ray.setDirection(new Vector3d(m_direction));
            return ray;
        }
        catch(CloneNotSupportedException e)
        {
            throw new InternalError();
        }
    }

    public boolean equals(Object o)
    {
        if(o == this)
            return true;
        if(o != null && (o instanceof Ray))
        {
            Ray ray = (Ray)o;
            return m_origin.equals(ray.m_origin) && m_direction.equals(ray.m_direction);
        } else
        {
            return false;
        }
    }

    public Vector3d getOrigin()
    {
        if(m_origin != null)
            return new Vector3d(m_origin);
        else
            return null;
    }

    public void setOrigin(Vector3d origin)
    {
        if(origin != null)
            m_origin = new Vector3d(origin);
        else
            m_origin = null;
    }

    public Vector3d getDirection()
    {
        if(m_direction != null)
            return new Vector3d(m_direction);
        else
            return null;
    }

    public void setDirection(Vector3d direction)
    {
        if(direction != null)
            m_direction = new Vector3d(direction);
        else
            m_direction = null;
    }

    public Vector3d getPoint(double t)
    {
        Vector3d p = new Vector3d(m_direction);
        p.scale(t);
        p.add(m_origin);
        return p;
    }

    public void transform(Matrix4d m)
    {
        javax.vecmath.Vector4d transformedOrigin = MathUtilities.multiply(m_origin, 1.0D, m);
        m_origin = MathUtilities.createVector3d(transformedOrigin);
        javax.vecmath.Vector4d transformedDirection = MathUtilities.multiply(m_direction, 0.0D, m);
        transformedDirection.w = 1.0D;
        m_direction = MathUtilities.createVector3d(transformedDirection);
    }

    public String toString()
    {
        return "edu.cmu.cs.stage3.math.Ray[origin=" + m_origin + ",direction=" + m_direction + "]";
    }

    protected Vector3d m_origin;
    protected Vector3d m_direction;
}

package edu.cmu.cs.stage3.math;


class Shear
{

    public Shear()
    {
        xy = 1.0D;
        xz = 1.0D;
        yz = 1.0D;
    }

    public Shear(double xy, double xz, double yz)
    {
        this.xy = 1.0D;
        this.xz = 1.0D;
        this.yz = 1.0D;
        this.xy = xy;
        this.xz = xz;
        this.yz = yz;
    }

    public Shear(double array[])
    {
        this(array[0], array[1], array[2]);
    }

    public String toString()
    {
        return "edu.cmu.cs.stage3.math.Shear[xy=" + xy + ",xz=" + xz + ",yz=" + yz + "]";
    }

    public static Shear valueOf(String s)
    {
        String markers[] = {
            "edu.cmu.cs.stage3.math.Shear[xy=", ",xz=", ",yz=", "]"
        };
        double values[] = new double[markers.length - 1];
        for(int i = 0; i < values.length; i++)
        {
            int begin = s.indexOf(markers[i]) + markers[i].length();
            int end = s.indexOf(markers[i + 1]);
            values[i] = Double.valueOf(s.substring(begin, end)).doubleValue();
        }

        return new Shear(values);
    }

    double xy;
    double xz;
    double yz;
}

package edu.cmu.cs.stage3.math;


class SingularityException extends RuntimeException
{

    public SingularityException()
    {
    }
}

package edu.cmu.cs.stage3.math;

import javax.vecmath.*;

// Referenced classes of package edu.cmu.cs.stage3.math:
//            MathUtilities, Vector3

class Sphere
    implements Cloneable
{

    public Sphere()
    {
        this(null, (0.0D / 0.0D));
    }

    public Sphere(Vector3d center, double radius)
    {
        setCenter(center);
        setRadius(radius);
    }

    public Sphere(double x, double y, double z, double radius)
    {
        setCenter(new Vector3d(x, y, z));
        setRadius(radius);
    }

    public synchronized Object clone()
    {
        try
        {
            Sphere sphere = (Sphere)super.clone();
            sphere.setCenter(m_center);
            return sphere;
        }
        catch(CloneNotSupportedException e)
        {
            throw new InternalError();
        }
    }

    public boolean equals(Object o)
    {
        if(o == this)
            return true;
        if(o != null && (o instanceof Sphere))
        {
            Sphere s = (Sphere)o;
            return m_center.equals(s.m_center) && m_radius == s.m_radius;
        } else
        {
            return false;
        }
    }

    public double getRadius()
    {
        return m_radius;
    }

    public void setRadius(double radius)
    {
        m_radius = radius;
    }

    public Vector3d getCenter()
    {
        if(m_center != null)
            return new Vector3d(m_center);
        else
            return null;
    }

    public void setCenter(Vector3d center)
    {
        if(center != null)
            m_center = new Vector3d(center);
        else
            m_center = null;
    }

    public void union(Sphere s)
    {
        if(s != null && s.m_center != null)
            if(m_center != null)
            {
                Vector3d diagonal = new Vector3d(m_center);
                diagonal.sub(s.m_center);
                diagonal.normalize();
                Vector3d points[] = new Vector3d[4];
                points[0] = MathUtilities.add(m_center, MathUtilities.multiply(diagonal, m_radius));
                points[1] = MathUtilities.subtract(m_center, MathUtilities.multiply(diagonal, m_radius));
                points[2] = MathUtilities.add(s.m_center, MathUtilities.multiply(diagonal, s.m_radius));
                points[3] = MathUtilities.subtract(s.m_center, MathUtilities.multiply(diagonal, s.m_radius));
                double maxDistanceSquared = 0.0D;
                int maxDistanceI = 0;
                int maxDistanceJ = 1;
                for(int i = 0; i < 4; i++)
                {
                    for(int j = i + 1; j < 4; j++)
                    {
                        double d2 = MathUtilities.subtract(points[i], points[j]).lengthSquared();
                        if(d2 > maxDistanceSquared)
                        {
                            maxDistanceSquared = d2;
                            maxDistanceI = i;
                            maxDistanceJ = j;
                        }
                    }

                }

                m_center = MathUtilities.divide(MathUtilities.add(points[maxDistanceI], points[maxDistanceJ]), 2D);
                m_radius = Math.sqrt(maxDistanceSquared) / 2D;
            } else
            {
                m_center = s.getCenter();
                m_radius = s.getRadius();
            }
    }

    public void transform(Matrix4d m)
    {
        if(m_center != null && !Double.isNaN(m_radius))
            m_center.add(new Vector3(m.m30, m.m31, m.m32));
    }

    public void scale(Matrix3d s)
    {
        if(s != null)
        {
            if(m_center != null)
                m_center = MathUtilities.multiply(s, m_center);
            m_radius *= s.getScale();
        }
    }

    public String toString()
    {
        String s = "edu.cmu.cs.stage3.math.Sphere[radius=" + m_radius + ",center=";
        if(m_center != null)
            s = s + m_center + "]";
        else
            s = s + "null]";
        return s;
    }

    protected double m_radius;
    protected Vector3d m_center;
}

package edu.cmu.cs.stage3.math;

import javax.vecmath.*;

// Referenced classes of package edu.cmu.cs.stage3.math:
//            Interpolable

class Vector3 extends Vector3d
    implements Interpolable
{

    public Vector3()
    {
    }

    public Vector3(double x, double y, double z)
    {
        super.x = x;
        super.y = y;
        super.z = z;
    }

    public Vector3(double v[])
    {
        this(v[0], v[1], v[2]);
    }

    public Vector3(Tuple3d t)
    {
        this(t.x, t.y, t.z);
    }

    public Vector3(Tuple4d t)
    {
        this(t.x / t.w, t.y / t.w, t.z / t.w);
    }

    public double getItem(int i)
    {
        switch(i)
        {
        case 0: // '\0'
            return super.x;

        case 1: // '\001'
            return super.y;

        case 2: // '\002'
            return super.z;
        }
        throw new IllegalArgumentException();
    }

    public void setItem(int i, double v)
    {
        switch(i)
        {
        case 0: // '\0'
            super.x = v;
            return;

        case 1: // '\001'
            super.y = v;
            return;

        case 2: // '\002'
            super.z = v;
            return;
        }
        throw new IllegalArgumentException();
    }

    public double[] getArray()
    {
        double a[] = {
            super.x, super.y, super.z
        };
        return a;
    }

    public void setArray(double a[])
    {
        super.x = a[0];
        super.y = a[1];
        super.z = a[2];
    }

    public void add(Vector3 v)
    {
        super.x += ((Tuple3d) (v)).x;
        super.y += ((Tuple3d) (v)).y;
        super.z += ((Tuple3d) (v)).z;
    }

    public static Vector3 add(Vector3d a, Vector3d b)
    {
        return new Vector3(((Tuple3d) (a)).x + ((Tuple3d) (b)).x, ((Tuple3d) (a)).y + ((Tuple3d) (b)).y, ((Tuple3d) (a)).z + ((Tuple3d) (b)).z);
    }

    public void subtract(Vector3 v)
    {
        super.x -= ((Tuple3d) (v)).x;
        super.y -= ((Tuple3d) (v)).y;
        super.z -= ((Tuple3d) (v)).z;
    }

    public static Vector3 subtract(Vector3d a, Vector3d b)
    {
        return new Vector3(((Tuple3d) (a)).x - ((Tuple3d) (b)).x, ((Tuple3d) (a)).y - ((Tuple3d) (b)).y, ((Tuple3d) (a)).z - ((Tuple3d) (b)).z);
    }

    public static Vector3 negate(Vector3d v)
    {
        return new Vector3(-((Tuple3d) (v)).x, -((Tuple3d) (v)).y, -((Tuple3d) (v)).z);
    }

    public void multiply(double scalar)
    {
        super.x *= scalar;
        super.y *= scalar;
        super.z *= scalar;
    }

    public void multiply(Vector3d scalar)
    {
        super.x *= ((Tuple3d) (scalar)).x;
        super.y *= ((Tuple3d) (scalar)).y;
        super.z *= ((Tuple3d) (scalar)).z;
    }

    public static Vector3 multiply(Vector3d v, double scalar)
    {
        return new Vector3(((Tuple3d) (v)).x * scalar, ((Tuple3d) (v)).y * scalar, ((Tuple3d) (v)).z * scalar);
    }

    public static Vector3 multiply(Vector3d v, Vector3 scalar)
    {
        return new Vector3(((Tuple3d) (v)).x * ((Tuple3d) (scalar)).x, ((Tuple3d) (v)).y * ((Tuple3d) (scalar)).y, ((Tuple3d) (v)).z * ((Tuple3d) (scalar)).z);
    }

    public void divide(double divisor)
    {
        multiply(1.0D / divisor);
    }

    public void divide(Vector3d divisor)
    {
        super.x /= ((Tuple3d) (divisor)).x;
        super.y /= ((Tuple3d) (divisor)).y;
        super.z /= ((Tuple3d) (divisor)).z;
    }

    public static Vector3 divide(Vector3d v, double divisor)
    {
        return multiply(v, 1.0D / divisor);
    }

    public static Vector3 divide(Vector3d numerator, Vector3d divisor)
    {
        return new Vector3(((Tuple3d) (numerator)).x / ((Tuple3d) (divisor)).x, ((Tuple3d) (numerator)).y / ((Tuple3d) (divisor)).y, ((Tuple3d) (numerator)).z / ((Tuple3d) (divisor)).z);
    }

    public void invert()
    {
        super.x = 1.0D / super.x;
        super.y = 1.0D / super.y;
        super.z = 1.0D / super.z;
    }

    public static Vector3 invert(Vector3d v)
    {
        return new Vector3(1.0D / ((Tuple3d) (v)).x, 1.0D / ((Tuple3d) (v)).y, 1.0D / ((Tuple3d) (v)).z);
    }

    public static Vector3 normalizeV(Vector3d v)
    {
        Vector3 nv = new Vector3(((Tuple3d) (v)).x, ((Tuple3d) (v)).y, ((Tuple3d) (v)).z);
        nv.normalize();
        return nv;
    }

    public static double getLengthSquared(double x, double y, double z)
    {
        return x * x + y * y + z * z;
    }

    public static double getLength(double x, double y, double z)
    {
        double lengthSquared = getLengthSquared(x, y, z);
        if(lengthSquared == 1.0D)
            return 1.0D;
        else
            return Math.sqrt(lengthSquared);
    }

    public double getLengthSquared()
    {
        return getLengthSquared(super.x, super.y, super.z);
    }

    public double getLength()
    {
        return getLength(super.x, super.y, super.z);
    }

    public static double dotProduct(Vector3d a, Vector3d b)
    {
        return ((Tuple3d) (a)).x * ((Tuple3d) (b)).x + ((Tuple3d) (a)).y * ((Tuple3d) (b)).y + ((Tuple3d) (a)).z * ((Tuple3d) (b)).z;
    }

    public static Vector3 crossProduct(Vector3d a, Vector3d b)
    {
        return new Vector3(((Tuple3d) (a)).y * ((Tuple3d) (b)).z - ((Tuple3d) (a)).z * ((Tuple3d) (b)).y, ((Tuple3d) (a)).z * ((Tuple3d) (b)).x - ((Tuple3d) (a)).x * ((Tuple3d) (b)).z, ((Tuple3d) (a)).x * ((Tuple3d) (b)).y - ((Tuple3d) (a)).y * ((Tuple3d) (b)).x);
    }

    public static Vector3 interpolate(Vector3d a, Vector3d b, double portion)
    {
        return new Vector3(((Tuple3d) (a)).x + (((Tuple3d) (b)).x - ((Tuple3d) (a)).x) * portion, ((Tuple3d) (a)).y + (((Tuple3d) (b)).y - ((Tuple3d) (a)).y) * portion, ((Tuple3d) (a)).z + (((Tuple3d) (b)).z - ((Tuple3d) (a)).z) * portion);
    }

    public Interpolable interpolate(Interpolable b, double portion)
    {
        return interpolate(((Vector3d) (this)), ((Vector3d) ((Vector3)b)), portion);
    }

    public Vector3 projectOnto(Vector3d b)
    {
        return multiply(b, dotProduct(b, this) / dotProduct(b, b));
    }

    public static Vector3 projectOnto(Vector3d a, Vector3d b)
    {
        return multiply(b, dotProduct(b, a) / dotProduct(b, b));
    }

    public static Vector3 multiply(Matrix3d a, Vector3d b)
    {
        double x = a.m00 * ((Tuple3d) (b)).x + a.m01 * ((Tuple3d) (b)).y + a.m02 * ((Tuple3d) (b)).z;
        double y = a.m10 * ((Tuple3d) (b)).x + a.m11 * ((Tuple3d) (b)).y + a.m12 * ((Tuple3d) (b)).z;
        double z = a.m20 * ((Tuple3d) (b)).x + a.m21 * ((Tuple3d) (b)).y + a.m22 * ((Tuple3d) (b)).z;
        return new Vector3(x, y, z);
    }

    public static Vector3 multiply(Vector3d a, Matrix4d b)
    {
        Vector3 ab = new Vector3();
        ab.x = ((Tuple3d) (a)).x * b.m00 + ((Tuple3d) (a)).y * b.m10 + ((Tuple3d) (a)).z * b.m20;
        ab.y = ((Tuple3d) (a)).x * b.m01 + ((Tuple3d) (a)).y * b.m11 + ((Tuple3d) (a)).z * b.m21;
        ab.z = ((Tuple3d) (a)).x * b.m02 + ((Tuple3d) (a)).y * b.m12 + ((Tuple3d) (a)).z * b.m22;
        return ab;
    }

    public static Vector3 combine(Vector3d a, Vector3d b, double asc1, double bsc1)
    {
        Vector3 ab = new Vector3();
        ab.x = asc1 * ((Tuple3d) (a)).x + bsc1 * ((Tuple3d) (b)).x;
        ab.y = asc1 * ((Tuple3d) (a)).y + bsc1 * ((Tuple3d) (b)).y;
        ab.z = asc1 * ((Tuple3d) (a)).z + bsc1 * ((Tuple3d) (b)).z;
        return ab;
    }

    public String toString()
    {
        return "edu.cmu.cs.stage3.math.Vector3[x=" + super.x + ",y=" + super.y + ",z=" + super.z + "]";
    }

    public static Vector3 valueOf(String s)
    {
        String markers[] = {
            "edu.cmu.cs.stage3.math.Vector3[x=", ",y=", ",z=", "]"
        };
        double values[] = new double[markers.length - 1];
        for(int i = 0; i < values.length; i++)
        {
            int begin = s.indexOf(markers[i]) + markers[i].length();
            int end = s.indexOf(markers[i + 1]);
            values[i] = Double.valueOf(s.substring(begin, end)).doubleValue();
        }

        return new Vector3(values);
    }

    public static final Vector3 ZERO = new Vector3(0.0D, 0.0D, 0.0D);
    public static final Vector3 X_AXIS = new Vector3(1.0D, 0.0D, 0.0D);
    public static final Vector3 X_AXIS_NEGATIVE = new Vector3(-1D, 0.0D, 0.0D);
    public static final Vector3 Y_AXIS = new Vector3(0.0D, 1.0D, 0.0D);
    public static final Vector3 Y_AXIS_NEGATIVE = new Vector3(0.0D, -1D, 0.0D);
    public static final Vector3 Z_AXIS = new Vector3(0.0D, 0.0D, 1.0D);
    public static final Vector3 Z_AXIS_NEGATIVE = new Vector3(0.0D, 0.0D, -1D);

}

package edu.cmu.cs.stage3.math;

import javax.vecmath.*;

class Vector4 extends Vector4d
{

    public Vector4()
    {
    }

    public Vector4(double x, double y, double z, double w)
    {
        super.x = x;
        super.y = y;
        super.z = z;
        super.w = w;
    }

    public Vector4(double v[])
    {
        this(v[0], v[1], v[2], v[3]);
    }

    public Vector4(Tuple3d v, double w)
    {
        this(v.x, v.y, v.z, w);
    }

    public boolean equals(Object o)
    {
        if(o == this)
            return true;
        if(o != null && (o instanceof Vector4))
        {
            Vector4 v = (Vector4)o;
            return super.x == ((Tuple4d) (v)).x && super.y == ((Tuple4d) (v)).y && super.z == ((Tuple4d) (v)).z && super.w == ((Tuple4d) (v)).w;
        } else
        {
            return false;
        }
    }

    public double[] getArray()
    {
        double a[] = {
            super.x, super.y, super.z, super.w
        };
        return a;
    }

    public void setArray(double a[])
    {
        super.x = a[0];
        super.y = a[1];
        super.z = a[2];
        super.w = a[3];
    }

    public void set(Vector4 other)
    {
        super.x = ((Tuple4d) (other)).x;
        super.y = ((Tuple4d) (other)).y;
        super.z = ((Tuple4d) (other)).z;
        super.w = ((Tuple4d) (other)).w;
    }

    public boolean equals(Vector4 v)
    {
        return super.x == ((Tuple4d) (v)).x && super.y == ((Tuple4d) (v)).y && super.z == ((Tuple4d) (v)).z && super.w == ((Tuple4d) (v)).w;
    }

    public double getItem(int i)
    {
        switch(i)
        {
        case 0: // '\0'
            return super.x;

        case 1: // '\001'
            return super.y;

        case 2: // '\002'
            return super.z;

        case 3: // '\003'
            return super.w;
        }
        throw new IllegalArgumentException();
    }

    public void setItem(int i, double v)
    {
        switch(i)
        {
        case 0: // '\0'
            super.x = v;
            return;

        case 1: // '\001'
            super.y = v;
            return;

        case 2: // '\002'
            super.z = v;
            return;

        case 3: // '\003'
            super.w = v;
            return;
        }
        throw new IllegalArgumentException();
    }

    public static Vector4 negate(Vector4 v)
    {
        return new Vector4(-((Tuple4d) (v)).x, -((Tuple4d) (v)).y, -((Tuple4d) (v)).z, -((Tuple4d) (v)).w);
    }

    public static double dotProduct(Vector4 a, Vector4 b)
    {
        return ((Tuple4d) (a)).x * ((Tuple4d) (b)).x + ((Tuple4d) (a)).y * ((Tuple4d) (b)).y + ((Tuple4d) (a)).z * ((Tuple4d) (b)).z + ((Tuple4d) (a)).w * ((Tuple4d) (b)).w;
    }

    public static Vector4 multiply(Vector4d a, Matrix4d b)
    {
        Vector4 ab = new Vector4();
        ab.x = ((Tuple4d) (a)).x * b.m00 + ((Tuple4d) (a)).y * b.m10 + ((Tuple4d) (a)).z * b.m20 + ((Tuple4d) (a)).w * b.m30;
        ab.y = ((Tuple4d) (a)).x * b.m01 + ((Tuple4d) (a)).y * b.m11 + ((Tuple4d) (a)).z * b.m21 + ((Tuple4d) (a)).w * b.m31;
        ab.z = ((Tuple4d) (a)).x * b.m02 + ((Tuple4d) (a)).y * b.m12 + ((Tuple4d) (a)).z * b.m22 + ((Tuple4d) (a)).w * b.m32;
        ab.w = ((Tuple4d) (a)).x * b.m03 + ((Tuple4d) (a)).y * b.m13 + ((Tuple4d) (a)).z * b.m23 + ((Tuple4d) (a)).w * b.m33;
        return ab;
    }

    public static Vector4 multiply(Matrix4d a, Vector4d b)
    {
        Vector4 ab = new Vector4();
        ab.x = ((Tuple4d) (b)).x * a.m00 + ((Tuple4d) (b)).y * a.m01 + ((Tuple4d) (b)).z * a.m02 + ((Tuple4d) (b)).w * a.m03;
        ab.y = ((Tuple4d) (b)).x * a.m10 + ((Tuple4d) (b)).y * a.m11 + ((Tuple4d) (b)).z * a.m12 + ((Tuple4d) (b)).w * a.m13;
        ab.z = ((Tuple4d) (b)).x * a.m20 + ((Tuple4d) (b)).y * a.m21 + ((Tuple4d) (b)).z * a.m22 + ((Tuple4d) (b)).w * a.m23;
        ab.w = ((Tuple4d) (b)).x * a.m30 + ((Tuple4d) (b)).y * a.m31 + ((Tuple4d) (b)).z * a.m32 + ((Tuple4d) (b)).w * a.m33;
        return ab;
    }

    public void transform(Matrix4d m)
    {
        set(multiply(this, m));
    }

    public String toString()
    {
        return "edu.cmu.cs.stage3.math.Vector4[x=" + super.x + ",y=" + super.y + ",z=" + super.z + ",w=" + super.w + "]";
    }

    public static Vector4 valueOf(String s)
    {
        String markers[] = {
            "edu.cmu.cs.stage3.math.Vector4[x=", ",y=", ",z=", ",w=", "]"
        };
        double values[] = new double[markers.length - 1];
        for(int i = 0; i < values.length; i++)
        {
            int begin = s.indexOf(markers[i]) + markers[i].length();
            int end = s.indexOf(markers[i + 1]);
            values[i] = Double.valueOf(s.substring(begin, end)).doubleValue();
        }

        return new Vector4(values);
    }
}

