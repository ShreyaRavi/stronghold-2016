package org.usfirst.frc.team86.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI.Port;

public class Gyro3D extends AHRS {
	
	public double rotationRatekP;
	
	public Gyro3D(Port spi_port_id) {
		super(spi_port_id);
	}
	
	public double normalize(double angle) {
		return ((angle%360)+360)%360;
	}
	
	public double getYawVal() {
		switch(IO.drive.getFrontState()) {
		case FRONT_A:
			return normalize((double)super.getYaw());
		case FRONT_B:
			return normalize(((double)super.getYaw()) + 180.0);
		}
		return normalize((double)super.getYaw());
	}
	
	public double getRollVal() {
		return normalize((double)super.getRoll());
	}
	
	public double getPitchVal() {
		return normalize((double)super.getPitch());
	}
	
	public boolean isStraight(double deadband, double startAngle) {
		double currAngle = getYawVal();
		if (Math.abs(currAngle - startAngle) <= deadband) {
			return true;
		} else {
			return false;
		}		
	}
	
	public boolean isLevel(double pitchDeadband, double rollDeadband) {
		double currPitch = getPitchVal();
		double currRoll = getRollVal();
		boolean isPitchLevel = false;
		boolean isRollLevel = false;
		if (Math.abs(currPitch) <= pitchDeadband) {
			isPitchLevel = true;
		}
		if (Math.abs(currRoll) <= rollDeadband) {
			isRollLevel = true;
		}
		if (isPitchLevel && isRollLevel) {
			return true;
		} else {
			return false;
		}
	}
	
//	public double getAngleError(double deadband, double angle) {
//		double error = 0.0;
//		if (!isStraight(deadband, angle)) {
//			double currAngle = getYawVal();
//			error = currAngle - angle;
//		}
//		return error;
//	}
	
	

}
