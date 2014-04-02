package Image.color;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 * This is my class for a color in the XYZ mode
 *  The numerical values below match those in the official sRGB specification (IEC 61966-2-1:1999)
 * @author gigemjt
 *
 */
public class MyColor extends Color
{
	
	double X,Y,Z;
	double L,a,b;
	public static final double Yn=100;
	private double Xn,Zn;
	public static final double SLOPE_VALUE=0.008856451679036;//the value that determines the types that the function follows
	public static final double MULT_VALUE=7.787037037037037;
	public static final Map<String,double[]> Illuminants=createIlluminants();
	public static final String DEFUALT_ILLUMINANT="Equal";
	String currentIlluminant=DEFUALT_ILLUMINANT;
	
	public MyColor(Color c)
	{
		super(c.getRGB());
		RGBtoXYZ(c.getRed(),c.getGreen(),c.getBlue());
		double[] XnZn=Illuminants.get(currentIlluminant);
		Xn=XnZn[0];
		Zn=XnZn[1];
		convertToLab();
	}
	public MyColor(int rbg)
	{
		this(new Color(rbg));
	}
	public void RGBtoXYZ(int R,int G,int B)
	{
		double a=.055;//determined by CIE
		double Rl=RBGtoXYZLinear(((double)R)/255.,a);
		double Gl=RBGtoXYZLinear(((double)G)/255.,a);
		double Bl=RBGtoXYZLinear(((double)B)/255.,a);
		X=0.4124*Rl+0.3576*Gl+0.1805*Bl;
		Y=0.2126*Rl+0.7152*Gl+0.0722*Bl;
		Z=0.0193*Rl+0.1192*Gl+0.9505*Bl;
	}
	public double RBGtoXYZLinear(double C,double a)//Values determined by CIE
	{
		if(C<=0.04045)
		{
			return C/12.92;
		}else
		{
			return Math.pow((C+a)/(1+a), 2.4);
		}
	}
	public Color XYZtoRBG()
	{
		double a=.055;//determined by CIE
		double Rl=3.2406*X-1.5372*Y-0.4986*Z;
		double Gl=-.09689*X+1.8758*Y+0.0415*Z;
		double Bl=0.0557*X-.2040*Y+1.0570*Z;
		int R=(int)Math.round(XYZtoRBGLinear(Rl,a)*255.);
		int G=(int)Math.round(XYZtoRBGLinear(Gl,a)*255.);
		int B=(int)Math.round(XYZtoRBGLinear(Bl,a)*255.);
		return new Color(R,G,B);
	}
	public double XYZtoRBGLinear(double C,double a)//Values determined by CIE
	{
		if(C<=0.0031308)
		{
			return C*12.92;
		}else
		{
			return (1+a)*Math.pow(C, 1/2.4)-a;
		}
	}
	/**
	 * These values are found by HunterLab
	 */
	public void convertToLab()
	{
		
		double Xrat=X/Xn;
		double Yrat=Z/Zn;
		double Zrat=Y/Yn;
		L=116.*Fn(Yrat)-16.;
		a=500.*(Fn(Xrat)-Fn(Yrat));
		b=200.*(Fn(Yrat)-Fn(Zrat));
	}
	public static Map<String,double[]> createIlluminants()
	{
		Map<String,double[]> luminants;
		if(Illuminants==null)
		{
			luminants=new HashMap<String,double[]>();
			final double[] A	=new double[]{109.83,35.55};
			final double[] C	=new double[]{98.04	,118.11};
			final double[] D65	=new double[]{95.02	,108.82};
			final double[] F2	=new double[]{98.09	,67.53};
			final double[] TL4	=new double[]{101.40,65.90};
			final double[] UL	=new double[]{107.99,33.91};
			final double[] D50	=new double[]{96.38	,82.45};
			final double[] D60	=new double[]{95.23	,100.86};
			final double[] D75	=new double[]{94.96	,122.53};
			final double[] Equal=new double[]{100.0	,100.00};
			
			luminants.put("A", A);
			luminants.put("C", C);
			luminants.put("D65", D65);
			luminants.put("F2", F2);
			luminants.put("TL4", TL4);
			luminants.put("UL", UL);
			luminants.put("D50", D50);
			luminants.put("D60", D60);
			luminants.put("D75", D75);
			luminants.put("Equal", D75);
			return luminants;
		}else
		{
			return Illuminants;
		}
	}
	public boolean getDifference(MyColor c2,double compare)
	{
		return getDifference(c2)<compare;
	}
	public double Fn(double t)
	{
		
		if(t>SLOPE_VALUE)
		{
			return Math.cbrt(t);
		}else
		{
			return MULT_VALUE*t+0.137931034482759;
		}
			
	}
	public double getDifferenceA(MyColor c2)
	{
		return this.a-c2.a;
	}
	public double getDifferenceB(MyColor c2)
	{
		return this.b-c2.b;
	}
	public double getDifferenceL(MyColor c2)
	{
		return this.L-c2.L;
	}
	public double getC1()
	{
		return Math.sqrt(a*a+b*b);
	}
	public double getC2(MyColor c2)
	{
		return Math.sqrt(c2.a*c2.a+c2.b*c2.b);
	}
	public double getDifferenceCab(MyColor c2)
	{
		return getC1()-getC2(c2);
	}
	/**
	 * All value  output in the squared value
	 * @param c2
	 * @return
	 */
	public double getDifferenceEabSquared(MyColor c2)
	{
		double DeltaL=L-c2.L;
		double DeltaA=a-c2.a;
		double DeltaB=b-c2.b;
		return DeltaL*DeltaL+DeltaB*DeltaB+DeltaA*DeltaA;
	}
	public double getDifferenceHabSquared(MyColor c2)
	{
		double l=getDifferenceL(c2);
		double Cab=getDifferenceCab(c2);
		return getDifferenceEabSquared(c2)-l*l-Cab*Cab;
	}
	public double getDifference(MyColor c2)
	{
		return Math.sqrt(getDifferenceXYZSquare(c2))+Math.sqrt(getDifferenceSquare(c2))+Math.sqrt(getDifferenceRBGSquare(c2));
	}
	public double getDifferenceSquare(MyColor c2)
	{
		double a=getDifferenceL(c2);
		double b=getDifferenceCab(c2)/(1+0.045*getC1());
		double under=1+.014*getC1();
		double c=getDifferenceHabSquared(c2)/(under*under);
		return a*a+b*b+c;
	}
	public double getDifferenceXYZSquare(MyColor c2)
	{
		return Math.pow(X-c2.X,2)+Math.pow(Z-c2.Z,2)+Math.pow(Y-c2.Y,2);
	}
	public double getDifferenceRBGSquare(MyColor c2)
	{
		return Math.pow(this.getBlue()-c2.getBlue(),2)+Math.pow(getRed()-c2.getRed(),2)+Math.pow(getGreen()-c2.getGreen(),2);
	}
}