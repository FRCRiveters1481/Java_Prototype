/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.SensorCollection;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.DrivetrainDriveSystemCommand;
import edu.wpi.first.wpilibj.PIDController;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import java.util.HashMap;

/**
 * An example subsystem. You can replace me with your own Subsystem.
 */
public class drive extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.


	WPI_VictorSPX m_frontLeft = new WPI_VictorSPX(RobotMap.frontLeftMotor);
	WPI_VictorSPX m_midLeft = new WPI_VictorSPX(RobotMap.middleLeftMotor);
	WPI_VictorSPX m_rearLeft = new WPI_VictorSPX(RobotMap.backLeftMotor);

	SpeedControllerGroup m_left = new SpeedControllerGroup(m_frontLeft, m_midLeft, m_rearLeft);

	WPI_VictorSPX m_frontRight = new WPI_VictorSPX(RobotMap.frontRightMotor);
	WPI_VictorSPX m_midRight = new WPI_VictorSPX(RobotMap.middleRightMotor);
	WPI_VictorSPX m_rearRight = new WPI_VictorSPX(RobotMap.backRightMotor);

	SpeedControllerGroup m_right = new SpeedControllerGroup(m_frontRight, m_midRight, m_rearRight);

	public DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);


  /* Create a Hash of sensors that contain the sensor collection
     from the left and right sides of the robot.
     Use the enums driveSide.Left and driveSide.Right to access the
     encoders for the left and right sides of the robot from m_odometers */
  public enum driveSide {
		Left, Right
	};


	private static HashMap<driveSide, SensorCollection> m_odometers = new HashMap<>();
	static {
		m_odometers.put(driveSide.Left, new WPI_TalonSRX(RobotMap.leftDriveControllerSensor).getSensorCollection());
		m_odometers.put(driveSide.Right, new WPI_TalonSRX(RobotMap.rightDriveControllerSensor).getSensorCollection());
	}

	public void periodic() {
		// Override me!

	}

	/* Compute the absolute distance travelled per a given side */
	public double getCurrentDistance(driveSide side) {
		int ticksOdometer = m_odometers.get(side).getQuadraturePosition();
		return new RobotMap().convertDriveTicksToInches(ticksOdometer);
	}

	@Override
	public void initDefaultCommand() {

		setDefaultCommand(new DrivetrainDriveSystemCommand());

		// *** These 3 inversions are for 1481_Beta bot *** //
		m_rearLeft.setInverted(true);// motor #3
		m_frontLeft.setInverted(true);// motor #1
		m_midLeft.setInverted(true);// motor #5

	}

}
