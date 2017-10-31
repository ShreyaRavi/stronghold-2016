package org.usfirst.frc.team86.robot;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class NetworkTableUtil {
	
	public static double[] defaultVal = new double[1];

	public static double getCenterX(NetworkTable table) {
		double[] centerXVals = table.getNumberArray("centerX", defaultVal);
		int position = maxAreaPosition(table);
		double centerX = centerXVals[position];
		return centerX;
	}
	
	public static int maxAreaPosition(NetworkTable table) {
		double[] areaVals = table.getNumberArray("area", defaultVal);
		double min = areaVals[0];
		int pos = -1;
		for (int i = 0; i < areaVals.length; i++) {
			min = Math.min(min, areaVals[i]);
			if (min == areaVals[i]) {
				pos = i;
			}
		}
		return pos;
	}
	
	
}
