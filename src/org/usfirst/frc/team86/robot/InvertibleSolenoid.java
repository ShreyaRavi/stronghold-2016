package org.usfirst.frc.team86.robot;

import edu.wpi.first.wpilibj.Solenoid;

public class InvertibleSolenoid extends Solenoid {
	
	protected SolenoidState state;
	private boolean isInverted;

	public InvertibleSolenoid(int channel) {
		super(channel);
		this.isInverted = false;
	}
	
	public InvertibleSolenoid(int channel, boolean inverted) {
		super(channel);
		this.isInverted = inverted;
	}
	
	public void update() {
		switch(state) {
		case ON:
			set(true);
			break;
		case OFF:
			set(false);
			break;
		}
	}
	
	public void set(boolean val) {
		if (isInverted) {
			super.set(!val);
		} else {
			super.set(val);
		}
	}
	
	public void toggleState() {
		if (state == SolenoidState.OFF) {
			state = SolenoidState.ON;
		} else {
			state = SolenoidState.OFF;
		}
	}
	
	public SolenoidState getState() {
		return state;
	}
	
	public void setState(SolenoidState solenoidState) {
		state = solenoidState;
	}
	
}
