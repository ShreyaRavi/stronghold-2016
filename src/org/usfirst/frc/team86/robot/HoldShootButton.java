package org.usfirst.frc.team86.robot;

import edu.wpi.first.wpilibj.GenericHID;

public class HoldShootButton extends Button {
	
	private FrontState prevState;
	private boolean onEntry = true;
	
	public HoldShootButton(GenericHID joystick, int buttonVal) {
		super(joystick, buttonVal);
	}

	public void update() {
		if (currentState) {
			if (onEntry) {
				prevState = IO.drive.getFrontState();
				onEntry = false;
			}
			IO.snorfle.setSnorfleState(SnorfleState.LOAD_OUT_SHOOT);
			IO.drive.turnCompControlOn();		
		} else {
			IO.shootSolenoid.setState(SolenoidState.OFF);
			IO.drive.turnCompControlOff();
			onEntry = true;
			if (prevState != null) { // maybe not needed bc there should always be a frontState chosen
				IO.drive.setFrontState(prevState);
			}
		}
	}

}
