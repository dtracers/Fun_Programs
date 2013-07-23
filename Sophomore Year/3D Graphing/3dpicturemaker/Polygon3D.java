

/**
    The Polygon3D class represents a polygon as a series of
    vertices.
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
public class Polygon3D{

    // temporary vectors used for calculation
    private vector3d temp1 = new vector3d();
    private vector3d temp2 = new vector3d();

    private ArrayList<vector3d> v;
    private int numVertices;
    private vector3d normal;

    /**
        Creates an empty polygon that can be used as a "scratch"
        polygon for transforms, projections, etc.
    */
    public Polygon3D() {
        numVertices = 0;
        v = new ArrayList<vector3d>();
        normal = new vector3d();
    }


    /**
       Creates a new Polygon3D with the specified vertices.
    */
    public Polygon3D(vector3d v0, vector3d v1, vector3d v2) {
    	ArrayList temp= new ArrayList();
    	temp.add(v0);
    	temp.add(v1);
    	temp.add(v2);
        Polygon3D(temp);
    }


    /**
        Creates a new Polygon3D with the specified vertices. All
        the vertices are assumed to be in the same plane.
    */
    public Polygon3D(vector3d v0, vector3d v1, vector3d v2,
        vector3d v3)
    {
    	ArrayList temp= new ArrayList();
    	temp.add(v0);
    	temp.add(v1);
    	temp.add(v2);
    	temp.add(v3);
        Polygon3D(temp);
    }


    /**
        Creates a new Polygon3D with the specified vertices. All
        the vertices are assumed to be in the same plane.
    */
    public void addvertex(vector3d v)
    {
    	this.v.add(v);
    	numVertices++;
    }
    public Polygon3D(ArrayList<vector3d> vertices)
    {
        this.v = clone(vertices);
        numVertices = vertices.size();
        calcNormal();
    }
    private void Polygon3D(ArrayList<vector3d> vertices)
    {
        this.v = clone(vertices);
        numVertices = vertices.size();
        calcNormal();
    }
	public ArrayList<vector3d> clone(ArrayList<vector3d> cloneable)
	{
		ArrayList<vector3d> temp=new ArrayList<vector3d>();
		for(int k=0;k<cloneable.size();k++)
		{
			temp.add(new vector3d(cloneable.get(k)));
		}
		return temp;
	}

    /**
        Sets this polygon to the same vertices as the specfied
        polygon.
    */
    public void setTo(Polygon3D polygon) {
        numVertices = polygon.numVertices;
        normal.setTo(polygon.normal);
        ensureCapacity(numVertices);
        for (int i=0; i<numVertices; i++)
        {
            v.get(i).setTo(polygon.v.get(i));
        }
    }


    /**
        Ensures this polgon has enough capacity to hold the
        specified number of vertices.
    */
    protected void ensureCapacity(int length) {
        if (v.size() < length)
        {
            for (int i=v.size(); i<length-v.size(); i++)
            {
                v.add(new vector3d());
            }
        }
    }


    /**
        Gets the number of vertices this polygon has.
    */
    public int getNumVertices() {
        return numVertices;
    }


    /**
        Gets the vertex at the specified index.
    */
    public vector3d getVertex(int index) {
        return v.get(index);
    }


    /**
        Projects this polygon onto the view window.
    */
    public void project(ViewWindow view) {
        for (int i=0; i<numVertices; i++) {
            view.project(v.get(i));
        }
    }


    // methods from the Transformable interface.

    public void add(vector3d u) {
       for (int i=0; i<numVertices; i++) {
           v.get(i).add(u);
       }
    }

    public void subtract(vector3d u) {
       for (int i=0; i<numVertices; i++) {
           v.get(i).subtract(u);
       }
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
        for (int i=0; i<numVertices; i++) {
           v.get(i).addRotation(xform);
        }
        normal.addRotation(xform);
    }

    public void subtractRotation(Transform3D xform) {
        for (int i=0; i<numVertices; i++) {
           v.get(i).subtractRotation(xform);
        }
        normal.subtractRotation(xform);
    }


    /**
        Calculates the unit-vector normal of this polygon.
        This method uses the first, second, and third vertices
        to calcuate the normal, so if these vertices are
        collinear, this method will not work. In this case,
        you can get the normal from the bounding rectangle.
        Use setNormal() to explicitly set the normal.
        This method uses static objects in the Polygon3D class
        for calculations, so this method is not thread-safe across
        all instances of Polygon3D.
    */
    public vector3d calcNormal() {
        if (normal == null) {
            normal = new vector3d();
        }
        temp1.setTo(v.get(2));
        temp1.subtract(v.get(1));
        temp2.setTo(v.get(0));
        temp2.subtract(v.get(1));
        normal.setToCrossProduct(temp1, temp2);
        normal.normalize();
        return normal;
    }


    /**
        Gets the normal of this polygon. Use calcNormal() if
        any vertices have changed.
    */
    public vector3d getNormal() {
        return normal;
    }


    /**
        Sets the normal of this polygon.
    */
    public void setNormal(vector3d n) {
        if (normal == null) {
            normal = new vector3d(n);
        }
        else {
            normal.setTo(n);
        }
    }


    /**
        Tests if this polygon is facing the specified location.
        This method uses static objects in the Polygon3D class
        for calculations, so this method is not thread-safe across
        all instances of Polygon3D.
    */
    public boolean isFacing(vector3d u) {
        temp1.setTo(u);
        temp1.subtract(v.get(0));
        return (normal.getDotProduct(temp1) >= 0);
    }


    /**
        Clips this polygon so that all vertices are in front of
        the clip plane, clipZ (in other words, all vertices
        have z <= clipZ).
        The value of clipZ should not be 0, as this causes
        divide-by-zero problems.
        Returns true if the polygon is at least partially in
        front of the clip plane.
    */
    public boolean clip(double clipZ) {
        ensureCapacity(numVertices * 3);

        boolean isCompletelyHidden = true;

        // insert vertices so all edges are either completly
        // in front or behind the clip plane
        for (int i=0; i<numVertices; i++) {
            int next = (i + 1) % numVertices;
            vector3d v1 = v.get(i);
            vector3d v2 = v.get(next);
            if (v1.z < clipZ) {
                isCompletelyHidden = false;
            }
            // ensure v1.z < v2.z
            if (v1.z > v2.z) {
                vector3d temp = v1;
                v1 = v2;
                v2 = temp;
            }
            if (v1.z < clipZ && v2.z > clipZ) {
                double scale = (clipZ-v1.z) / (v2.z - v1.z);
                insertVertex(next,
                    v1.x + scale * (v2.x - v1.x) ,
                    v1.y + scale * (v2.y - v1.y),
                    clipZ);
                // skip the vertex we just created
                i++;
            }
        }

        if (isCompletelyHidden) {
            return false;
        }

        // delete all vertices that have z > clipZ
        for (int i=numVertices-1; i>=0; i--) {
            if (v.get(i).z > clipZ) {
                deleteVertex(i);
            }
        }

        return (numVertices >= 3);
    }


    /**
        Inserts a new vertex at the specified index.
    */
    protected void insertVertex(int index, double x, double y,
        double z)
    {
    	v.add(index,new vector3d(x,y,z));
        numVertices++;
    }


    /**
        Delete the vertex at the specified index.
    */
    protected void deleteVertex(int index) {
        vector3d deleted = v.get(index);
        for (int i=index; i<v.size()-1; i++) {
            v.get(i).setTo(v.get(i+1));
        }
        v.get(v.size()-1).setTo(deleted);
        numVertices--;
    }


    /**
        Inserts a vertex into this polygon at the specified index.
        The exact vertex in inserted (not a copy).
    */
    public void insertVertex(int index, vector3d vertex)
    {
       	v.add(index,vertex);
        numVertices++;
    }


    /**
        Calculates and returns the smallest bounding rectangle for
        this polygon.
    */
    public Rectangle3D calcBoundingRectangle() {

        // the smallest bounding rectangle for a polygon shares
        // at least one edge with the polygon. so, this method
        // finds the bounding rectangle for every edge in the
        // polygon, and returns the smallest one.
        Rectangle3D boundingRect = new Rectangle3D();
        double minimumArea = Float.MAX_VALUE;
        vector3d u = new vector3d();
        vector3d v = new vector3d();
        vector3d d = new vector3d();
        for (int i=0; i<getNumVertices(); i++) {
            u.setTo(getVertex((i + 1) % getNumVertices()));
            u.subtract(getVertex(i));
            u.normalize();
            v.setToCrossProduct(getNormal(), u);
            v.normalize();

            double uMin = 0;
            double uMax = 0;
            double vMin = 0;
            double vMax = 0;
            for (int j=0; j<getNumVertices(); j++) {
                if (j != i) {
                    d.setTo(getVertex(j));
                    d.subtract(getVertex(i));
                    double uLength = d.getDotProduct(u);
                    double vLength = d.getDotProduct(v);
                    uMin = Math.min(uLength, uMin);
                    uMax = Math.max(uLength, uMax);
                    vMin = Math.min(vLength, vMin);
                    vMax = Math.max(vLength, vMax);
                }
            }
            // if this calculated area is the smallest, set
            // the bounding rectangle
            double area = (uMax - uMin) * (vMax - vMin);
            if (area < minimumArea) {
                minimumArea = area;
                vector3d origin = boundingRect.getOrigin();
                origin.setTo(getVertex(i));
                d.setTo(u);
                d.multiply(uMin);
                origin.add(d);
                d.setTo(v);
                d.multiply(vMin);
                origin.add(d);
                boundingRect.getDirectionU().setTo(u);
                boundingRect.getDirectionV().setTo(v);
                boundingRect.setWidth(uMax - uMin);
                boundingRect.setHeight(vMax - vMin);
            }
        }
        return boundingRect;
    }
}
