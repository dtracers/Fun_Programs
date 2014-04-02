package car.functions;

public class PixelInch
{
	public static final double PixelToMeterRatio=1000;
	private static double PIR=PixelToMeterRatio;
	private static double PIRS=PixelToMeterRatio;
	public static void setPIR(double pIR) {
		PIR = pIR;
		PIRS=PIR*PIR;
	}
	public static double getPIR() {
		return PIR;
	}
	public static double getPIRS() {
		return PIRS;
	}

}
