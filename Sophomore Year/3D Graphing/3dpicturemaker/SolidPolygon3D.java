package classes;
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

import java.awt.Color;

/**
    The SolidPolygon3D class is a Polygon with a color.
*/
public class SolidPolygon3D extends Polygon3D {

    private Color color = Color.GREEN;

    public SolidPolygon3D() {
        super();
    }


    public SolidPolygon3D(vector3d v0, vector3d v1, vector3d v2) {
        super(v0,v1,v2);
    }
    public SolidPolygon3D(vector3d v0, vector3d v1, vector3d v2,
        vector3d v3)
    {
        super(v0,v1,v2,v3);
    }
    public SolidPolygon3D(ArrayList vertices) {
        super(vertices);
    }
    public void setTo(Polygon3D polygon) {
        super.setTo(polygon);
        if (polygon instanceof SolidPolygon3D) {
            color = ((SolidPolygon3D)polygon).color;
        }
    }
    /**
        Gets the color of this solid-colored polygon used for
        rendering this polygon.
    */
    public Color getColor() {
        return color;
    }
    /**
        Sets the color of this solid-colored polygon used for
        rendering this polygon.
    */
    public void setColor(Color color) {
        this.color = color;
    }
}