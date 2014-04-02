package car.functions;

import car.Car;
import static car.functions.PixelInch.*;
public class AccelBrake
{
	public static double getAccel(double speed,Car c)
	{
		return 150.*1000./(getPIRS())-c.getFrontalArea()*0.45*(speed*speed/getPIRS())/2.;
	}
	public static double getReverse(double speed)
	{
		return (speed>0?-5:-2);
	}
}
