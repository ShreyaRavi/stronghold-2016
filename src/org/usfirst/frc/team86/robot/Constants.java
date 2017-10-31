package org.usfirst.frc.team86.robot;

public class Constants {
	
	
	// deadbands: min error for it not to matter that there's an error
	// kp: tune (all)
	// translate kp: inverse of the pixel propband
	//				 (if 100 pixels = 100% power, >100 pixels = 100% power)
	// setpointShooterX: tune (1/2 dimensions of image on driver station GRIP)
	
	// tune these values accordingly (like the kPs, deadbands, and min speeds)
	// max speeds should be fine at 100%
	// setpoint shooterX should be fine if the x dimension is adjusted (easy fix)
	public static final double ROTATION_kP = 0.1;
	public static final double ROTATE_DEADBAND = 5;
	public static final double MIN_ROTATE_SPEED = 0.2;
	public static final double MAX_ROTATE_SPEED = 1.0;
	
	public static final double SETPOINT_SHOOTER_X = 180; // 0.5 of x dimension of GRIP image
	
	public static final double TRANSLATE_kP = 0.1;
	public static final double TRANSLATE_DEADBAND = 5;
	public static final double MIN_TRANSLATE_SPEED = 0.2;
	public static final double MAX_TRANSLATE_SPEED = 1.0;	

	public static final double JOYSTICK_DEADBAND = 0.2;
	public static final double JOYSTICK_LIM_1 = 0.5; 
	public static final double JOYSTICK_LIM_1_kP = 0.3; 
	public static final double JOYSTICK_LIM_2 = 1.0;
	public static final double JOYSTICK_LIM_2_kP = 1.0;
		
	public static final double SNORFLE_IN_SPEED = 0.7;
	public static final double SNORFLE_IDLE_SPEED = 0.0;
	public static final double SNORFLE_OUT_SPEED = -0.5; // must be negative
	
	public static final double LIFTER_SPEED_UP = 0.8;
	public static final double LIFTER_BOTTOM_IDLE_SPEED = 0.0;
	public static final double LIFTER_MIDDLE_IDLE_SPEED = 0.0;
	public static final double LIFTER_TOP_IDLE_SPEED = 0.0;
	public static final double LIFTER_SPEED_DOWN = -0.7; // must be negative 
	
	public static final double DRIVE_TO_DEFENSE_SPEED = 1.0;
	
}
