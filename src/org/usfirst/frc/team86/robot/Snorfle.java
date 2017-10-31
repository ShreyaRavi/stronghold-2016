package org.usfirst.frc.team86.robot;

public class Snorfle {
	
	public SnorfleState snorfleState;
	
	public Snorfle() {
		snorfleState = SnorfleState.IDLE;
	}
	
	public void update() {
		switch (snorfleState) {
		case IDLE:
			IO.snorfleMotor.set(Constants.SNORFLE_IDLE_SPEED);
			break;
		case SNORFLE_IN:
			IO.snorfleMotor.set(Constants.SNORFLE_IN_SPEED);
			break;
		case LOAD_OUT:
			IO.snorfleMotor.set(Constants.SNORFLE_OUT_SPEED);
//		case LOAD_OUT_SHOOT:
//			if (once) {
//				prevTime = System.currentTimeMillis();
//				once = false;
//			} else {
//				currTime = System.currentTimeMillis();
//				cummulativeTime += ((currTime - prevTime)/1000.0);
//			}
//			if (cummulativeTime < waitTime)  {
//				IO.snorfler.set(loadOutSpeed);
//			} else {
//				cummulativeTime = 0.0;
//				once = true;
//				setSnorfleState(SnorfleState.IDLE);
//			}
//			break;
		}
		
	}
	
	public void setSnorfleState(SnorfleState state) {
		snorfleState = state;
	}
	
	public SnorfleState getSnorfleState() {
		return snorfleState;
	}
	
}

enum SnorfleState {
	IDLE,
	SNORFLE_IN,
	LOAD_OUT_SHOOT,
	LOAD_OUT;
}
