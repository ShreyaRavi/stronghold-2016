package org.usfirst.frc.team86.robot;

import edu.wpi.first.wpilibj.DigitalOutput;

public class InvertibleDigitalOutput extends DigitalOutput {
	
	private boolean isInverted;

	public InvertibleDigitalOutput(int channel) {
		super(channel);
	}
	
	public InvertibleDigitalOutput(int channel, boolean inverted) {
		super(channel);
		this.isInverted = inverted;
	}
	
	public void set(boolean setVal) {
		if (isInverted) {
			super.set(!setVal);
		} else {
			super.set(setVal);
		}
	}
	
	public boolean isInverted() {
		return isInverted;
	}

}
