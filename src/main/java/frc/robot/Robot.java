/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.subsystems.*;
import frc.robot.commands.*;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
//import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import frc.robot.commands.autonTestCommand;
import edu.wpi.first.wpilibj.DriverStation;
import openrio.powerup.MatchData;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static ExampleSubsystem m_subsystem = new ExampleSubsystem();
	public static OI m_oi;
	public static drive m_driveSubsystem = new drive();
	public static elevator m_elevator = new elevator();
	public static intake m_intake = new intake();
	public static wrist m_wrist = new wrist();
	public static climb m_climb = new climb();

	Command m_autonomousCommand;
	SendableChooser<String> m_chooser = new SendableChooser<>();

	SendableChooser<Command> m_testChooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_oi = new OI();
		m_chooser.addDefault("Default Auto doNothing", "Default Auto doNothing") ;
		m_chooser.addObject("Auto mode Drive Forward", "Auto mode Drive Forward") ;
		
		m_chooser.addObject("Auto mode Only Closest Switch Left side", "Auto mode Only Closest Switch Left side");
		m_chooser.addObject("Auto mode Only Closest Switch Right side", "Auto mode Only Closest Switch Right side") ;
		SmartDashboard.putData("Autonomous Selector", m_chooser);
		

		// Camera initialization
		UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture();
		camera1.setResolution(320, 240);
		camera1.setFPS(15);

		m_testChooser.addDefault("doNothing", new doNothing());
		m_testChooser.addObject("DriveDistanceCommand 100 inches", new DriveDistanceCommand(100.0));
		m_testChooser.addObject("DriveDistanceCommand 100 inches", new DriveDistanceCommand(-100.0));
		m_testChooser.addObject("DriveTurnToAngle 90 degrees", new DriveTurnToAngle(90.0));
		m_testChooser.addObject("DriveTurnToAngle -90 degrees", new DriveTurnToAngle(-90.0));
	}

	/**
	 * This function is called every robot packet, no matter the mode. Use this for
	 * items like diagnostics that you want ran during disabled, autonomous,
	 * teleoperated and test.
	 *
	 * <p>
	 * This runs after the mode specific periodic functions, but before LiveWindow
	 * and SmartDashboard integrated updating.
	 */
	@Override
	public void robotPeriodic() {
		// Example modification to test vscode git
	}

	/**
	 * This function is called once each time the robot enters Disabled mode. You
	 * can use it to reset any subsystem information you want to clear when the
	 * robot is disabled.
	 */
	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
	 * remove all of the chooser code and uncomment the getString code to get the
	 * auto name from the text box below the Gyro
	 *
	 * <p>
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons to
	 * the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		m_autonomousCommand = null;

		MatchData.OwnedSide switchSide = MatchData.getOwnedSide(MatchData.GameFeature.SWITCH_NEAR);
		if (switchSide == MatchData.OwnedSide.LEFT) {
			// Do something with the left
		} else if (switchSide == MatchData.OwnedSide.RIGHT) {
			// Do something with the right
		} else {
			// Unknown
		}
		
		String autoSelected = m_chooser.getSelected();

		switch (autoSelected) {
		case "Default Auto doNothing":
			m_autonomousCommand = new autonDoNothing();
			break;
		case "Auto mode Drive Forward":
			m_autonomousCommand = new autonDriveForward();
			break;
		case "Auto mode Only Closest Switch Left side":
			if (switchSide == MatchData.OwnedSide.LEFT) {
				m_autonomousCommand = new autonCloseSwitch();
			}
			else  {
				m_autonomousCommand = new autonDriveForward();
			}
			break;
		case "Auto mode Only Closest Switch Right side":
			if (switchSide == MatchData.OwnedSide.RIGHT) {
				m_autonomousCommand = new autonCloseSwitch();
			}
			else {
				m_autonomousCommand = new autonDriveForward();
			}
		default:

			break;
		}

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();

	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();

	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();

		Command testCommand = m_testChooser.getSelected();

		// schedule the autonomous command (example)
		if (testCommand != null) {
			testCommand.start();
		}
	}
}
