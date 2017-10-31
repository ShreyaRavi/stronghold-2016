package org.usfirst.frc.team86.robot;

import edu.wpi.first.wpilibj.GenericHID;

public class ToggleFrontButton extends Button {
	
	public boolean prevState = false;
	public boolean currState;
	
	public ToggleFrontButton(GenericHID joystick, int buttonVal) {
		super(joystick, buttonVal);
	}
	
	public void update() {
		super.update();
		if ((previousState != currentState) && currentState) {
			IO.drive.toggleFrontState();
		}		
	}
	
}
