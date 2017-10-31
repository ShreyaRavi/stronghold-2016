package org.usfirst.frc.team86.robot;

import edu.wpi.first.wpilibj.DigitalInput;

public class InvertibleDigitalInput extends DigitalInput {
	
	private boolean isInverted;

	public InvertibleDigitalInput(int channel) {
		super(channel);
	}
	
	public InvertibleDigitalInput(int channel, boolean inverted) {
		super(channel);
		this.isInverted = inverted;
	}
	
	public boolean get() {
		if (isInverted()) {
			return !super.get();
		} else {
			return super.get();
		}
	}
	
	public boolean isInverted() {
		return this.isInverted;
	}
	
	

}
