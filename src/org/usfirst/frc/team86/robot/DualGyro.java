package org.usfirst.frc.team86.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DualGyro {
	
	public AnalogGyro gyro1;
	public boolean gyro1Inverted;
	public AnalogGyro gyro2;
	public boolean gyro2Inverted;
	
	public DualGyro() {
		gyro1 = new AnalogGyro(0);
		gyro2 = new AnalogGyro(1);
		gyro1Inverted = false;
		gyro2Inverted = false;
	}
	
	public void init() {
		gyro1.initGyro();
		gyro2.initGyro();
	}
	
	public void reset() {
		gyro1.reset();
		gyro2.reset();
	}
	
	public static double normalizeAngle(double angle) {
		return ((angle%360)+360)%360;
	}
	
	public double getAngle() {
		double gyro1Angle = gyro1.getAngle();
		double gyro2Angle = gyro2.getAngle();
		if (gyro1Inverted) {
			gyro1Angle *= -1;
		}
		if (gyro2Inverted) {
			gyro2Angle *= -1;
		}
		double resultAngle = (gyro1Angle + gyro2Angle)/(2.0);
		// add or subtract from resultAngle if we don't start by positioning robot at
		// what we want it to read as 0
		
		resultAngle = normalizeAngle(resultAngle);
		SmartDashboard.putNumber("Current Angle", resultAngle);
		return resultAngle;
	}
	
	public void setInvertedGyro(int gyroNum, boolean isInverted) {
		// gyroNum refers to either gyro1 or gyro2, whichever one you want to invert
		switch(gyroNum) {
		case 1:
			gyro1Inverted = isInverted;
			break;
		case 2:
			gyro2Inverted = isInverted;
		}
	}
	

}
