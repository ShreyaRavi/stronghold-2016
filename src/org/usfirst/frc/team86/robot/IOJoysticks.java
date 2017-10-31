package org.usfirst.frc.team86.robot;

public class IOJoysticks {
	
	public static SingleJoystick leftJoystick;
	public static SingleJoystick rightJoystick;
	public static SingleJoystick coDriverJoystick;
	public static Button holdAntlersButton;
	public static Button holdShootButton;
	public static Button holdLoadOutButton;
	public static Button toggleAngleHoldButton;
	public static Button toggleFrontButton;
	public static Button toggleSnorflerSolenoidButton;
	public static Button[] buttons;
	
	public static void init() {
		leftJoystick = new SingleJoystick(0);
		rightJoystick = new SingleJoystick(1);
		coDriverJoystick = new SingleJoystick(2);
		
		holdAntlersButton = new HoldAntlersButton(leftJoystick, 0);
		holdShootButton = new HoldShootButton(leftJoystick, 1);
		holdLoadOutButton = new HoldLoadOutButton(leftJoystick, 2);
		toggleAngleHoldButton = new ToggleAngleHoldButton(leftJoystick, 3);
		toggleFrontButton = new ToggleFrontButton(leftJoystick, 4);
		toggleSnorflerSolenoidButton = new ToggleSnorflerSolenoidButton(leftJoystick, 5);
		
		// initialize array of buttons
		buttons = new Button[] {
				holdAntlersButton,
				holdShootButton,
				holdLoadOutButton,
				toggleAngleHoldButton,
				toggleFrontButton,
				toggleSnorflerSolenoidButton
			};
		
	}
	
	public static void update() {
		for (Button button: buttons) {
			button.update();
		}
	}
	
	
	
}
