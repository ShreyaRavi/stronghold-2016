package org.usfirst.frc.team86.robot;

import edu.wpi.first.wpilibj.Joystick;

public class SingleJoystick extends Joystick {

	public SingleJoystick(int port) {
		super(port);
		
	}
	
	public double getYVal() {
		return getFilteredRate(super.getY());
	}
	
	public double getXVal() {
		return getFilteredRate(super.getX());
	}
	
	public double getFilteredRate(double input) {
		double absValInput = Math.abs(input);
		if (absValInput < Constants.JOYSTICK_DEADBAND) {
			return 0.0;
		} else if (absValInput < Constants.JOYSTICK_LIM_1) {
			return input * Constants.JOYSTICK_LIM_1_kP;
		} else if (absValInput < Constants.JOYSTICK_LIM_2) {
			return input * Constants.JOYSTICK_LIM_2_kP;
		}	else {
			return input;
		}
	}

}
