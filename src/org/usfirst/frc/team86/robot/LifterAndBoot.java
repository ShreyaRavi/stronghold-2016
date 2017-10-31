package org.usfirst.frc.team86.robot;

public class LifterAndBoot {
	
	public LifterBootMachineState lifterBootMachineState;
	public LiftGateState liftGateState;
	public PullBridgeState pullBridgeState;
	
	public LifterAndBoot() {
		
	}
	
	public void update() {
		switch(lifterBootMachineState) {
		case LIFT_GATE:
			liftGateUpdate();
			break;
		case PULL_BRIDGE:
			pullBridgeUpdate();
			break;		
		}
	}
	
	// make gate defense class that extends the abstract class defense
	// in crossDefense() set LiftGateState to INIT
	// drive forward for specified time while LiftGateState is INIT
	// once drive forward time has passed && bootLimitDown.get() == true && lifter
	//		set LiftGateState to BOOT_UP
	// set variable in gate defense class for time to drive after lifting gate
	// 		also make a variable for the seesaw and drawbridge
	// drive forward for that specified time
	// once specified time is up, set LiftGateState to LIFTER_MIDDLE
	
	
	public void liftGateUpdate() {
		switch(liftGateState) {
		case INIT:
			if (!IO.lifterLimitBottom.get()) {
				IO.lifterMotor.set(Constants.LIFTER_SPEED_DOWN);
			} else {
				IO.lifterMotor.set(Constants.LIFTER_BOTTOM_IDLE_SPEED);
			}
			IO.bootSolenoid.setState(SolenoidState.ON);
			IO.tiltSolenoid.setState(SolenoidState.OFF);
			break;
		case BOOT_UP:
			// if you are in BOOT_UP state, the following can be assumed:
			// boot is at the bootLimitDown
			// lifter is at lifterLimitBottom
			IO.lifterMotor.set(Constants.LIFTER_BOTTOM_IDLE_SPEED);
			if (!IO.bootLimitUp.get()) {
				IO.bootSolenoid.setState(SolenoidState.OFF);
			} else {
				setLiftGateState(LiftGateState.LIFTER_UP);
			}
			break;
		case LIFTER_UP:
			if (!IO.lifterLimitTop.get()) {
				IO.lifterMotor.set(Constants.LIFTER_SPEED_UP);
			} else {
				IO.lifterMotor.set(Constants.LIFTER_TOP_IDLE_SPEED);
			}
			break;
		case RESET:
			if (!IO.lifterLimitMiddle.get()) {
				IO.lifterMotor.set(Constants.LIFTER_SPEED_DOWN);
			} else {
				IO.lifterMotor.set(Constants.LIFTER_MIDDLE_IDLE_SPEED);
			}
		}
		
		
	}
	
	// make gate defense class that extends abstract defense class
	// in crossDefense() set PullBridgeState to INIT
	// drive forward for specified time while PullBridgeState is INIT
	// once drive forward time has passed && bootLimitTop.get() == true && IO.lifterLimitMiddle
	
	public void pullBridgeUpdate() {
		switch(pullBridgeState) {
		case INIT:
			if (!IO.bootLimitUp.get()) {
				IO.bootSolenoid.setState(SolenoidState.OFF);
			}
			if (!IO.lifterLimitMiddle.get()) {
				if (IO.lifterLimitTop.get()) {
					IO.lifterMotor.set(Constants.LIFTER_SPEED_DOWN);
				} else if (IO.lifterLimitBottom.get()) {
					IO.lifterMotor.set(Constants.LIFTER_SPEED_UP);
				}
			}
			break;
		case BOOT_DOWN:
			if (!IO.bootLimitDown.get()) {
				IO.bootSolenoid.setState(SolenoidState.ON);
			}
			break;
		case LIFTER_TILT:
			if (IO.tiltSolenoid.getState() == SolenoidState.OFF) {
				IO.tiltSolenoid.setState(SolenoidState.ON);
			}
			break;
		case LIFTER_DOWN:
			if (!IO.lifterLimitBottom.get()) {
				IO.lifterMotor.set(Constants.LIFTER_SPEED_DOWN);
			}
			break;
		}
		
	}
	
	public void setLiftGateState(LiftGateState state) {
		liftGateState = state;
	}
	
	public void setPullBridgeState(PullBridgeState state) {
		pullBridgeState = state;
	}
	
	public void resetLiftGate() {
		liftGateState = LiftGateState.RESET;
	}
	
	public void resetPullBridge() {
		pullBridgeState = PullBridgeState.RESET;
	}
	
}

enum LifterBootMachineState {
	LIFT_GATE,
	PULL_BRIDGE,
}

enum LiftGateState {
	INIT,
	BOOT_UP,
	LIFTER_UP,
	RESET;
}

enum PullBridgeState {
	INIT,
	BOOT_DOWN,
	LIFTER_TILT,
	LIFTER_DOWN,
	RESET;
}


