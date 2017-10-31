package org.usfirst.frc.team86.robot;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class IO {
	
	public static Drive drive;
	public static VictorSP leftDriveMotor;
	public static VictorSP rightDriveMotor;
	public static VictorSP snorfleMotor;
	public static Snorfle snorfle;
	public static VictorSP lifterMotor;
	public static LifterAndBoot lifter;
	public static InvertibleShootSolenoid shootSolenoid;
	public static InvertibleSolenoid tiltSolenoid;
	public static InvertibleSolenoid bootSolenoid;
	public static InvertibleSolenoid snorflerSolenoid;
	public static InvertibleSolenoid antlersSolenoid;
	public static InvertibleSolenoid[] solenoids;
	public static NetworkTable table;
	public static Gyro3D gyro;
	public static InvertibleDigitalInput bannerSensor;
	public static InvertibleDigitalInput lifterLimitBottom;
	public static InvertibleDigitalInput lifterLimitMiddle;
	public static InvertibleDigitalInput lifterLimitTop;
	public static InvertibleDigitalInput bootLimitUp;
	public static InvertibleDigitalInput bootLimitDown;
	public static InvertibleDigitalOutput frontALight;
	public static InvertibleDigitalOutput frontBLight;
	
	
	public static void init() {
		
		leftDriveMotor = new VictorSP(0);
		rightDriveMotor = new VictorSP(1);
		leftDriveMotor.setInverted(false);
		rightDriveMotor.setInverted(false);
				
		drive = new Drive(leftDriveMotor, rightDriveMotor);
		drive.init();
				
		snorfleMotor = new VictorSP(2);
		snorfle = new Snorfle();
		
		lifterMotor = new VictorSP(3);
		lifter = new LifterAndBoot();
		lifterLimitBottom = new InvertibleDigitalInput(4, false);
		lifterLimitMiddle = new InvertibleDigitalInput(5, false);
		lifterLimitTop = new InvertibleDigitalInput(6, false);
		
		shootSolenoid = new InvertibleShootSolenoid(0, false);
		snorflerSolenoid = new InvertibleSolenoid(1, false);
		antlersSolenoid = new InvertibleSolenoid(2, false);
		bootSolenoid = new InvertibleSolenoid(3, false);
		tiltSolenoid = new InvertibleSolenoid(4, false);
		
		solenoids = new InvertibleSolenoid[] {
				shootSolenoid,
				snorflerSolenoid,
				antlersSolenoid,
				bootSolenoid
			};
		
		table = NetworkTable.getTable("GRIP/myContoursReport");
		
		gyro = new Gyro3D(SPI.Port.kMXP);
		
		bannerSensor = new InvertibleDigitalInput(4, false);
		
		frontALight = new InvertibleDigitalOutput(5, false);
		frontBLight = new InvertibleDigitalOutput(6, false);
		
		
	}
	
	public static void update() {
		for (InvertibleSolenoid solenoid: solenoids) {
			solenoid.update();
		}
		snorfle.update();
		drive.update();
	}


}
