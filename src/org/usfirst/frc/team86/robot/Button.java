package org.usfirst.frc.team86.robot;

import edu.wpi.first.wpilibj.GenericHID;

public class Button {

	private int buttonVal;
	private GenericHID joystick;
	protected boolean currentState;
	protected boolean previousState;
	
	public Button(GenericHID joystick, int buttonVal) {
		this.buttonVal = buttonVal;
		this.joystick = joystick;
		this.currentState = isPressed();
	}
	
	public void update() {
		previousState = currentState;
		currentState = isPressed();
	}
	
	public boolean isPressed() {
		return this.joystick.getRawButton(this.buttonVal);
	}
	
	
	
}
