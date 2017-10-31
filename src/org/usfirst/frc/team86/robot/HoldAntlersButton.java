package org.usfirst.frc.team86.robot;

import edu.wpi.first.wpilibj.GenericHID;

public class HoldAntlersButton extends Button {

	public HoldAntlersButton(GenericHID joystick, int buttonVal) {
		super(joystick, buttonVal);
	}

	public void update() {
		super.update();
		if (currentState) {
			IO.antlersSolenoid.setState(SolenoidState.ON);
			IO.shootSolenoid.setState(SolenoidState.OFF);
		} else {
			IO.antlersSolenoid.setState(SolenoidState.OFF);
		}
	}
	

}
