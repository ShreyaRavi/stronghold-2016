package org.usfirst.frc.team86.robot;

public class Teleop {
	
	// TODO: set up ports in IO class (real ports not the placeholder values I put in)
	// TODO: set up magnetic limit switches on solenoid for boot (InvertibleDigitalInput)
	// TODO: set up limit switches on the lifter (InvertibleDigitalIput)
	// TODO: set up autonomous code (based on position, goal chosen, and defenses)
	//			look up the defenses positons on the FRC game manual
	// TODO: set up all buttons (look at LIST OF BUTTONS)
	// TODO: set up solenoid for boot
	// TODO: set up invertible motor and then account for 5% (or .05) power lost when inverted
	// TODO: set up Lifter Class (set up states for this -- enum) (make use of limit switches)
	//			extend VictorSP class
	//			could do has-a relationship with corresponding limit switches on lifter or could
	//			continue with static IO refereces (doesn't particulary matter - just a design choice)
	// TODO: set up Boot Class (set up states for this -- enum) (make use of limit switches)
	//			extend solenoid class
	//			could do has-a relationship with corresponding limit switches on lifter or could
	//			continue with static IO refereces (doesn't particulary matter - just a design choice
	// TODO: set up LifterAndBoot Class (already set up) and have lifter and boot objects within class
	//			create a has-a relationship, which is what makes most sense here
	
	// MAKE SURE TO PUT THE FOLLOWING ON THE SMART DASHBOARD
	//		the banner sensor reading (put boolean)
	//		the angle being read by the navx (or at least the getYawVal() for the navx)
	
	
	// LIST OF BUTTONS
	//		snorfler button				Done
	//		antlers button				Done
	//		front toggle button			Done
	//		hold angle button			Done
	//		hold shoot button			Done
	//		bring down drawbridge		
	//		lift portcullis				
	
	// set up autonomous code:
	// set up crossing defense
	// (based on defense position, defense type, and goal chosen to target
	// once defense crossed (can tell b/c will be level again), rotate + translate + shoot
	
	
	// organize classes into packages and do all the imports
	// package by features (in a car: air conditioning, driving, etc)
	// 		(not by pneumatics, gears, etc)
	
	// boot and lifter
	
	// STEPS TO LIFT PORTICULLIS
	// boot begins up, tilt begins off and stays off
	// boot goes all the way down
	// drive toward gate
	// lift boot up
	// porticullis gets jammed between boot and lifter (bootLimitUp reads true)
	// don't change state of boot (leave it as OFF, which it already should be)
	// start the lifter as soon as bootLimitUp reads true
	// keep it going until lifterLimitTop reads true
	// set the state of the lifter to IDLE
	
	// STEPS TO BRINGS DOWN DRAWBRIDGE
	// set boot to up and move the lifter until the lifterLimitMiddle is true
	//		this is based on which lifterLimit switch returns true initially
	//		NOTE: a limit switch should always be hit when the lifter is IDLE
	//		if lifterLimitTop returns true, run motor down
	//		if lifterLimitBottom returns true, run motor up
	// drive to drawbridge (make sure you are on the tilted platform)
	//		drive motors should move accordingly to keep robot in place on platform
	// drive toward drawbridge
	// tilt lifter forward using tiltSolenoid
	// run lifter down until lifterLimitBottom returns true while driving drive motors backwards
	//		set this rate in Constants class
	// set bootSolenoid to on and run until bootLimitDown reads true

	// IDEA: always set the lifter thing to neutralize at center
	//		 unless we need a specific position for a specific autonomous function
	//		 this way, won't have to travel the whole length sometimes and no length others
	//		 instead, travels 1/2 way all time
	//		 reset will occur automatically, such as while shooting
	
	// MAKE SURE THAT MIN SPEEDS ARE NEVER 0 AND THAT IT IS POSSIBLE TO BE
	// WITHIN THE DEADBAND
	// (won't continuously wobble back and forth attempting to get in range of deadband)
	
	// have a button that resets the positions of robot in case something gets messed up
	// while crossing a defense
	// basically, robot won't continue to go on with sequence of events
	// make sure it is some sort of manual override (preferably through a button)
	//		maybe the button controls the boot and have all cross defense actions
	//		be dependent on boot position (boot can't be down for anything but the gate)
	//		^ maybe not a great idea actually, but just a thought for now
	
	// Angle Hold State SETUP CHANGES NEED TO BE MADE
	// make angleHoldState independent of operator control and computer control so that angleHold
	// can be implemented while shooting (which is both operator control (teleop) and comp control (auto))
	// and can be implemented while crossing defenses (which is both operator control (teleop) and comp control (auto))
	
	public void init() {
		IOJoysticks.init();
		IO.drive.setControlState(ControlState.OP_CONTROL);
	}
	
	public void update() {
		
		IOJoysticks.update(); // button update (buttons only control states)
		IO.update(); // solenoid update (solenoids read in state and output action)
		
	}
	
}

