package org.usfirst.frc.team86.robot;

import edu.wpi.first.wpilibj.Solenoid;

public class InvertibleShootSolenoid extends InvertibleSolenoid {
	
	public long prevTime;
	public long currTime;
	public double cummulativeTime = 0;
	public double waitTime = 10.0; // tune (in seconds) how long delay is
	public boolean once = true;

	public InvertibleShootSolenoid(int channel) {
		super(channel);
	}
	
	public InvertibleShootSolenoid(int channel, boolean inverted) {
		super(channel,inverted);
	}
	
	public void update() {
		switch (state) {
		case ON:
			if (once) {
				prevTime = System.currentTimeMillis();
				once = false;
			} else {
				currTime = System.currentTimeMillis();
				cummulativeTime += ((currTime - prevTime)/1000.0);
			}
			if (cummulativeTime < waitTime)  {
				super.set(true); // could also do: IO.shootSolenoid.set(true) OR this.set(true)
			} else {
				cummulativeTime = 0.0;
				once = true;
				setState(SolenoidState.OFF);
			}
			break;
		case OFF:
			super.set(false);
			break;
		}
	}
	
}

