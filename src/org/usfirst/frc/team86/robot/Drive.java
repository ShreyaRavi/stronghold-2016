package org.usfirst.frc.team86.robot;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Drive extends RobotDrive {

	private FrontState frontState;
	private AngleHoldState angleHoldState;
	private ControlState controlState;
	private CompControlState compControlState;
	private AngleSelectState angleSelectState;
	private double rotationRate;
	private boolean onEntry = true;
	private double startAngle, errorAngle, currAngle, targetAngle;
	private boolean compControl, angleInRange;
	private boolean button1, button2, button3;
	private double currShooterX;
	private double translateError;
	private double translateRate;
	
	public Drive(VictorSP leftMotor, VictorSP rightMotor) {
		super(leftMotor, rightMotor);
	}
	
	public void init() {
		// init all variables here
	}
	
	public void update() {
		// take away game state (change in drive, autonomous, and teleop
		// instead, make autonomous one of the compControl states
		// this way shooting can be done in autonomous w/o repeating code by just switching
		// state from AUTO to COMP_CONTROL (which initiates shooting sequence)
		
		switch(angleHoldState) {
		case ON:
			if (onEntry) {
				onEntry = false;
				startAngle = IO.gyro.getYawVal();
			} else {
				if (!IO.gyro.isStraight(Constants.ROTATE_DEADBAND, startAngle)) {
					errorAngle = IO.gyro.getYawVal() - startAngle;
				} else {
					errorAngle = 0.0;
				}
				rotationRate = errorAngle * Constants.ROTATION_kP;
				rotationRate = Util.limit(rotationRate, Constants.MAX_ROTATE_SPEED, Constants.MIN_ROTATE_SPEED);
			}
			break;
		case OFF:
			onEntry = true;
			break;
		}
		
		// this is all fine for operator control
		// for computer control, the front chosen shouldn't matter
		switch(controlState) {
		case AUTONOMOUS_CONTROL:
			break;
		case OP_CONTROL:
			switch(frontState) {
			case FRONT_A:
				IO.leftDriveMotor.setInverted(false);
				IO.rightDriveMotor.setInverted(false);
				IO.frontALight.set(true);
				IO.frontBLight.set(false);
				break;
			case FRONT_B:
				IO.leftDriveMotor.setInverted(true);
				IO.rightDriveMotor.setInverted(true);
				IO.frontALight.set(false);
				IO.frontBLight.set(true);
				break;
			}
			switch(angleHoldState) {
			case ON:
				super.arcadeDrive(IOJoysticks.leftJoystick.getYVal(), this.rotationRate);
				break;
			case OFF:
				super.tankDrive(IOJoysticks.leftJoystick.getYVal(), IOJoysticks.rightJoystick.getYVal());
				break;
			}
			switch(angleSelectState) {
			case AUTO_SELECT:
				if (compControl && angleInRange) {
					controlState = ControlState.COMP_CONTROL;
				} else {
					controlState = ControlState.OP_CONTROL; // works for teleop (this is within teleop case)
				}
				break;
			case BUTTON_SELECT:
				if (compControl) {
					controlState = ControlState.COMP_CONTROL;
				}
			}
			break;
		case COMP_CONTROL:
			IO.frontALight.set(false);
			IO.frontBLight.set(false);
			currAngle = IO.gyro.getYawVal();
			targetAngle = chooseAngle(currAngle);
			errorAngle = currAngle - targetAngle;
			switch(compControlState) {
			case COMP_ROTATE:
				double rotationRate;
				if (IO.gyro.isStraight(Constants.ROTATE_DEADBAND, targetAngle)) {
					errorAngle = 0.0;
					compControlState = CompControlState.COMP_TRANSLATE;
				}
				rotationRate = errorAngle * Constants.ROTATION_kP;
				rotationRate = Util.limit(rotationRate, Constants.MAX_ROTATE_SPEED, Constants.MIN_ROTATE_SPEED);
				super.arcadeDrive(0, rotationRate);
				break;
			case COMP_TRANSLATE:
				setAngleHoldState(AngleHoldState.ON); // modifies this.rotationRate
				IO.table = NetworkTable.getTable("GRIP/myContoursReport");
				currShooterX = NetworkTableUtil.getCenterX(IO.table);
				translateError = currShooterX - Constants.SETPOINT_SHOOTER_X;
				if (onTarget(translateError, Constants.TRANSLATE_DEADBAND)) {
					translateError = 0.0;
					IO.shootSolenoid.setState(SolenoidState.ON);
				}
				translateRate = translateError * Constants.TRANSLATE_kP;
				translateRate = Util.limit(translateRate, Constants.MAX_TRANSLATE_SPEED, Constants.MIN_TRANSLATE_SPEED);
				super.arcadeDrive(translateRate, this.rotationRate);
				break;
			}
			break;
		}
					
	}	
	public boolean onTarget(double error, double deadband) {
		if (Math.abs(error) < deadband) {
			return true;
		} else {
			return false;
		}
	}
	
	public double chooseAngle(double currAngle) {
		double targetAngle = 0.0;
		switch (angleSelectState) {
		case AUTO_SELECT:
			targetAngle = closestAngle(currAngle);
			if (targetAngle == -1) {
				angleInRange = false;
			} else {
				angleInRange = true;
			}
			break;
		case BUTTON_SELECT:
			if (button1) {
				targetAngle = 60.0;
			} else if (button2) {
				targetAngle = 90.0;
			} else if (button3) {
				targetAngle = 120.0;
			}
			break;
		}
		return targetAngle;
	}
	
	public double closestAngle(double currAngle) {
		double targetLeft = 60.0;
		double targetMiddle = 90.0;
		double targetRight = 120.0;
		if ((currAngle > targetLeft - 15.0) && (currAngle < targetLeft + 15.0)) {
			return targetLeft;
		} else if ((currAngle > targetMiddle - 15.0) && (currAngle < targetMiddle + 15.0)) {
			return targetMiddle;
		} else if ((currAngle > targetRight - 15.0) && (currAngle < targetRight + 15.0)) {
			return targetRight;
		}
		return -1;
	}
	
	public void turnCompControlOn() {
		compControl = true;
	}
	
	public void turnCompControlOff() {	
		compControl = false;
	}
	
	public AngleHoldState getAngleHoldState() {
		return angleHoldState;
	}
	
	public void setAngleHoldState(AngleHoldState state) {
		angleHoldState = state;
	}
	
	public void toggleAngleHoldState() {
		if (getAngleHoldState() == AngleHoldState.OFF) {
			setAngleHoldState(AngleHoldState.ON);
		} else {
			setAngleHoldState(AngleHoldState.OFF);
		}
	}
	
	public FrontState getFrontState() {
		return frontState;
	}
	
	public void setFrontState(FrontState state) {
		frontState = state;
	}
	
	public void toggleFrontState() {
		if (getFrontState() == FrontState.FRONT_A) {
			setFrontState(FrontState.FRONT_B);
		} else {
			setFrontState(FrontState.FRONT_A);
		}
	}
	
	public ControlState getControlState() {
		return controlState;
	}
	
	public void setControlState(ControlState state) {
		controlState = state;
	}
	
	public CompControlState getCompControlState() {
		return compControlState;
	}
	
	public void setCompControlState(CompControlState state) {
		compControlState = state;
	}
	
	public AngleSelectState getAngleSelectState() {
		return angleSelectState;
	}
	
	public void setAngleSelectState(AngleSelectState state) {
		angleSelectState = state;
	}
	
	
}

enum AngleHoldState {
	ON,
	OFF;
}

enum FrontState {
	FRONT_A,
	FRONT_B;
}

enum ControlState {
	AUTONOMOUS_CONTROL,
	OP_CONTROL,
	COMP_CONTROL
}

enum CompControlState {
	COMP_ROTATE,
	COMP_TRANSLATE;
}

enum AngleSelectState {
	AUTO_SELECT,
	BUTTON_SELECT;
}
