package org.usfirst.frc.team86.robot;

import edu.wpi.first.wpilibj.GenericHID;

public class ToggleAngleHoldButton extends Button {
	
	public ToggleAngleHoldButton(GenericHID joystick, int buttonVal) {
		super(joystick, buttonVal);
	}	
	
	public void update() {
		super.update();
		if ((previousState != currentState) && currentState) {
			IO.drive.toggleAngleHoldState();
		}	
	}

}