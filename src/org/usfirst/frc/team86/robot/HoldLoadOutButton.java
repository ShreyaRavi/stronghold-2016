package org.usfirst.frc.team86.robot;

import edu.wpi.first.wpilibj.GenericHID;

public class HoldLoadOutButton extends Button {
	
	public boolean isUpright;
	public boolean hasBall;
	
	public HoldLoadOutButton(GenericHID joystick, int buttonVal) {
		super(joystick, buttonVal);
	}

	public void update() {
		super.update();
		isUpright = (IO.snorflerSolenoid.getState() == SolenoidState.OFF) ? true : false;
		hasBall = IO.bannerSensor.get();
		IO.snorfle.setSnorfleState(SnorfleState.IDLE);
		if (currentState) {
			IO.snorfle.setSnorfleState(SnorfleState.LOAD_OUT);
		} else if (!isUpright && !hasBall) {
			IO.snorfle.setSnorfleState(SnorfleState.SNORFLE_IN);
		}
		if (!isUpright && hasBall) {
			IO.snorflerSolenoid.setState(SolenoidState.OFF);
		}
	}

}
