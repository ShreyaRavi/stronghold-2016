package org.usfirst.frc.team86.robot;

public abstract class Defense {
	
	private int defenseVal;
	private double driveToTime;
	private double turnGoalAngle;
	
	public Defense(int defenseVal, double driveToTime) {
		this.defenseVal = defenseVal;
		this.driveToTime = driveToTime;
	}
	
	public void driveToDefense() {
		IO.drive.setAngleHoldState(AngleHoldState.ON);
	}
	
	public abstract void crossDefense();
	// set all init states for the defense to be crossed
	// drive to defense for the specified time (driveToTime)
	// make sure all init states are fully in the state (boot fully up/down,lifter position,etc)
	// cross the defense
	// set all back to initial states to (middle position of lifter, boot whatever (doesn't matter)
	
	public abstract void turnToGoal();
	
	public void shoot() {
		IO.drive.setControlState(ControlState.COMP_CONTROL);
	}
}