/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.drive;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import java.util.HashMap;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;


/**
 * A command to drive to a specified distance in inches using the drive encoders
 * and a differential.
 */
public class DriveDistanceCommand extends Command {

	private PIDController m_pidLeft;
	private PIDController m_pidRight;
	private double m_setpoint = 0;
	private double m_outputLeft = 0;
	private double m_outputRight = 0;


	private drivePIDSource m_distanceSourceLeft = new drivePIDSource(drive.driveSide.Left);
	private drivePIDSource m_distanceSourceRight = new drivePIDSource(drive.driveSide.Right);



	/**
	 * Requires DriveTrain
	 * 
	 * @param setpoint
	 *            distance to move in inches
	 */
	public DriveDistanceCommand(double setpoint) {
		requires(Robot.m_driveSubsystem);

		// System.out.print("DriveDistanceCommand:DriveDistanceCommand()\n");

		m_setpoint = setpoint;



		m_pidLeft = new PIDController(0.07, 0, 0, m_distanceSourceLeft, new pidOutputLeft());
		m_pidRight = new PIDController(0.07, 0, 0, m_distanceSourceRight, new pidOutputRight());

		m_pidLeft.setAbsoluteTolerance(1.0);
		m_pidRight.setAbsoluteTolerance(1.0);

		m_pidLeft.setOutputRange(-0.45, 0.45);
		m_pidRight.setOutputRange(-0.45,0.45);

		SmartDashboard.putNumber("DriveDistanceSetpoint", setpoint);
		LiveWindow.addSensor("DriveDistanceCommand", "LeftDrive", m_pidLeft);
		LiveWindow.addSensor("DriveDistanceCommand", "RightDrive", m_pidRight);
	}

	@Override
	protected void initialize() {

		m_distanceSourceLeft.setInitialDistance(Robot.m_driveSubsystem.getCurrentDistance(drive.driveSide.Left));
		m_distanceSourceRight.setInitialDistance(Robot.m_driveSubsystem.getCurrentDistance(drive.driveSide.Right));

		
		m_pidLeft.setSetpoint(m_setpoint);
		m_pidRight.setSetpoint(m_setpoint);

		//setTimeout(Math.min(4.0, m_setpoint * 0.150));

		m_pidLeft.enable();
		m_pidRight.enable();

	}

	@Override
	protected void execute() {

		Robot.m_driveSubsystem.m_drive.tankDrive(m_outputRight, m_outputLeft);

		SmartDashboard.putNumber("DriveDistanceLeftOutput", m_outputLeft);
		SmartDashboard.putNumber("DriveDistanceRightOutput", m_outputRight);
		SmartDashboard.putNumber("DriveDistanceRightPIDError", m_pidRight.getError());
		SmartDashboard.putNumber("DriveDistanceLeftPIDError", m_pidLeft.getError());

	}

	/**
	 *
	 * @return when pid is on target
	 */
	@Override
	protected boolean isFinished() {
		return (m_pidLeft.onTarget() && m_pidRight.onTarget()) || isTimedOut();
	}

	/**
	 * Stop robot
	 */
	@Override
	protected void end() {
	
		m_pidLeft.disable();
		m_pidRight.disable();
		m_pidLeft.reset();
		m_pidRight.reset();
		Robot.m_driveSubsystem.m_drive.arcadeDrive(0, 0);
	}

	/**
	 * Stop robot
	 */
	@Override
	protected void interrupted() {

		m_pidLeft.disable();
		m_pidRight.disable();
		m_pidLeft.reset();
		m_pidRight.reset();
		Robot.m_driveSubsystem.m_drive.arcadeDrive(0, 0);
	}

	private class pidOutputLeft implements PIDOutput {
		@Override
		public void pidWrite(double output) {
			m_outputLeft = output;
		}
	}

	private class pidOutputRight implements PIDOutput {
		@Override
		public void pidWrite(double output) {
			m_outputRight = -output;
		}
	}

	private class drivePIDSource implements PIDSource {
		private drive.driveSide m_side;
		private double m_initialDistance = Double.NaN;

		public void setInitialDistance(double initialDistance) {
			m_initialDistance = Robot.m_driveSubsystem.getCurrentDistance(m_side);
		}

		public drivePIDSource(drive.driveSide side) {
			m_side = side;
		}

		@Override
		public double pidGet() {
			return Robot.m_driveSubsystem.getCurrentDistance(m_side) - m_initialDistance;
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			return PIDSourceType.kDisplacement;
		}

		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
			/* don't care about this */
		}
	}
}
