package org.usfirst.frc.team86.robot;

import edu.wpi.first.wpilibj.GenericHID;

public class ToggleSnorflerSolenoidButton extends Button {
		
	public ToggleSnorflerSolenoidButton(GenericHID joystick, int buttonVal) {
		super(joystick, buttonVal);
	}
	
	public void update() {
		super.update();
		if ((previousState != currentState) && currentState) {
			SolenoidState currState = IO.snorflerSolenoid.getState();
			if (currState == SolenoidState.ON) {
				IO.antlersSolenoid.setState(SolenoidState.OFF);
			}
		}
		
	}	
	
}
