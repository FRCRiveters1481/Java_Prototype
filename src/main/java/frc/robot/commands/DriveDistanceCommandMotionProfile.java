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
import MotionProfile.Profile;

/**
 * A command to drive to a specified distance in inches using the drive encoders
 * and a differential.
 */
public class DriveDistanceCommandMotionProfile extends Command {

	private PIDController m_pidLeft;
	private PIDController m_pidRight;
	private double m_setpoint = 0;
	private double m_outputLeft = 0;
	private double m_outputRight = 0;

	private Profile m_motionProfile;

	private drivePIDSource m_distanceSourceLeft = new drivePIDSource(drive.driveSide.Left);
	private drivePIDSource m_distanceSourceRight = new drivePIDSource(drive.driveSide.Right);

	private long m_startOfProfileTimestamp;

	/**
	 * Requires DriveTrain
	 * 
	 * @param setpoint
	 *            distance to move in inches
	 */
	public DriveDistanceCommandMotionProfile(double setpoint) {
		requires(Robot.m_driveSubsystem);

		// System.out.print("DriveDistanceCommand:DriveDistanceCommand()\n");

		m_setpoint = setpoint;

		m_motionProfile = Profile.getVelProfile(15.0, 5.0, setpoint, 0.0, 0.0);

		m_pidLeft = new PIDController(0.2, 0, 0, m_distanceSourceLeft, new pidOutputLeft());
		m_pidRight = new PIDController(0.2, 0, 0, m_distanceSourceRight, new pidOutputRight());

		m_pidLeft.setAbsoluteTolerance(0.5);
		m_pidRight.setAbsoluteTolerance(0.5);

		// m_pidLeft.setOutputRange(-0.6, 0.6);
		// m_pidRight.setOutputRange(-0.6, 0.6);

		SmartDashboard.putNumber("DriveDistanceSetpoint", setpoint);
			SmartDashboard.putNumber("DriveDistance EndTime", m_motionProfile.getFinalTime());
		LiveWindow.addSensor("DriveDistanceCommand", "LeftDrive", m_pidLeft);
		LiveWindow.addSensor("DriveDistanceCommand", "RightDrive", m_pidRight);
	}

	@Override
	protected void initialize() {
		// System.out.print("DriveDistanceCommand:initialize()\n");
		m_distanceSourceLeft.setInitialDistance(Robot.m_driveSubsystem.getCurrentDistance(drive.driveSide.Left));
		m_distanceSourceRight.setInitialDistance(Robot.m_driveSubsystem.getCurrentDistance(drive.driveSide.Right));

		m_startOfProfileTimestamp = System.currentTimeMillis();

		m_pidLeft.enable();
		m_pidRight.enable();

	}

	@Override
	protected void execute() {

		// System.out.print("DriveDistanceCommand:execute()\n");
		double currentTargetTime = getMotionProfileElapsedTime();
		double currentTargetDistance = m_motionProfile.getDistAtTime(currentTargetTime);

		m_pidLeft.setSetpoint(currentTargetDistance);
		m_pidRight.setSetpoint(currentTargetDistance);

		SmartDashboard.putNumber("DriveDistanceCurrentTargetDistance", currentTargetDistance);

		System.out.printf("DriveDistance: Dist %.2f, Time %.2f LDist %.2f RDist %.2f\n", currentTargetDistance, currentTargetTime, m_distanceSourceLeft.pidGet(),m_distanceSourceRight.pidGet() );

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
		// System.out.print("DriveDistanceCommand:isFinished()\n");
		return (getMotionProfileElapsedTime() > m_motionProfile.getFinalTime());
		// return (m_pidLeft.onTarget() && m_pidRight.onTarget());
	}

	/**
	 * Stop robot
	 */
	@Override
	protected void end() {
		System.out.print("DriveDistanceCommand:end()\n");
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
		System.out.print("DriveDistanceCommand:interrupted()\n");
		m_pidLeft.disable();
		m_pidRight.disable();
		m_pidLeft.reset();
		m_pidRight.reset();
		Robot.m_driveSubsystem.m_drive.arcadeDrive(0, 0);
	}

	private double getMotionProfileElapsedTime() {

		return ((double) (System.currentTimeMillis() - m_startOfProfileTimestamp)) / 1000.0;
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
