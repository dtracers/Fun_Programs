/**
 * variables.java  7/26/2008
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

abstract class variables
{
	public static final double scaler=1;
	public static final double screenx=5,screeny=24;
	static double sizew=1024;
	static double sizeh=760;
	static double xcamera=0;
	static double ycamera=0;
	static double zcamera=0;
	static double winsize=sizew;
	static double angle=75;
	static double distance=100;
	static double oldd=distance;
	public static final double finald=(winsize/2)/Math.tan(angle/2);
	static double oldxcamera=0;
	static double oldycamera=0;
	static double oldzcamera=0;
	static double panx=sizew/2;
	static double pany=sizeh/2;
	static double panz=finald;
	static double fpanx=panx;
	static double fpany=pany;
	static double fpanz=panz;
	static double oldpanx=panx;
	static double oldpany=pany;
	static double oldpanz=panz;
	static double panx0=0;
	static double pany0=0;
	static double oldpanx0=0;
	static double oldpany0=0;
	static double settings=100;
}