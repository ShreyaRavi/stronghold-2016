package org.usfirst.frc.team86.robot;

public class Util {
	
	// highLim and lowLim have to both be positive
	// (high lim is for 100% power)
	// (low lim is for 20% power)
	public static double limit(double input, double highLim, double lowLim) {
		boolean neg;
		if (input > 0) {
			neg = false;
		} else {
			neg = true;
		}
		if (Math.abs(input) >= highLim) {
			if (neg) {
				input = -highLim;
			} else {
				input = highLim;
			}
		}
		if (Math.abs(input) <= lowLim) {
			if (neg) {
				input = -lowLim;
			} else  {
				input = lowLim;
			}
		}
		return input;
	}
	
	

}
