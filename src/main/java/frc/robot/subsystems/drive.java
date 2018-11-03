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
/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class drive extends PIDSubsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private enum driveSide {Left, Right};

WPI_VictorSPX m_frontLeft = new WPI_VictorSPX(RobotMap.frontLeftMotor);
WPI_VictorSPX m_midLeft = new WPI_VictorSPX(RobotMap.middleLeftMotor);
WPI_VictorSPX m_rearLeft = new WPI_VictorSPX(RobotMap.backLeftMotor);

SpeedControllerGroup m_left = new SpeedControllerGroup(m_frontLeft, m_midLeft, m_rearLeft);

WPI_VictorSPX m_frontRight = new WPI_VictorSPX(RobotMap.frontRightMotor);
WPI_VictorSPX m_midRight = new WPI_VictorSPX(RobotMap.middleRightMotor);
WPI_VictorSPX m_rearRight = new WPI_VictorSPX(RobotMap.backRightMotor);

SpeedControllerGroup m_right = new SpeedControllerGroup(m_frontRight, m_midRight, m_rearRight);

public DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);

private SensorCollection m_leftSensor = new WPI_TalonSRX(RobotMap.leftDriveControllerSensor).getSensorCollection();
private SensorCollection m_rightSensor = new WPI_TalonSRX(RobotMap.rightDriveControllerSensor).getSensorCollection();


public drive() {
   // Intert a subsystem name and PID values here
   super("drive", 1.0 /* P */, 0.0 /* I */ , 0.0 /* D */);
   // Use these to get going:
   // setSetpoint() - Sets where the PID controller should move the system
   // to
   // enable() - Enables the PID controller.
}

public void periodic() {
  // Override me!

}

/* Drive straight for distance, in inches */
public void driveStraightDistance(double distance) {

  if (distance == 0.0) {
    /* Disable the PID controller since we no longer need to drive anywhere. */
    disable();

    return;
  }

  driveResetDistanceTravelled();

  setSetpoint(new RobotMap().convertInchesToDriveTicks(distance));


}

private void driveResetDistanceTravelled() {
   m_leftSensor.setQuadraturePosition(0,0);
   m_rightSensor.setQuadraturePosition(0,0);
}

/* Compute the velocity of the drive motors by side in inches per second. */
private double driveGetCurrentSpeed(driveSide side) {

  int ticksPerUnitTime = 0;

  switch (side) {
    case Left:
    ticksPerUnitTime = m_leftSensor.getQuadratureVelocity();
    break;
    case Right:
    ticksPerUnitTime = m_rightSensor.getQuadratureVelocity();
    break;
    default:
    break;
  }

  return new RobotMap().convertDriveTicksPerTimeToInchesPerSecond(ticksPerUnitTime);
}


@Override
public void initDefaultCommand() {
    //setDefaultCommand(new DriveCommand());
    setDefaultCommand(new DrivetrainDriveSystemCommand());  

    // *** These 3 inversions are for 1481_Beta bot *** //
    m_rearLeft.setInverted(true);//motor #3
    m_frontLeft.setInverted(true);//motor #1
    m_midLeft.setInverted(true);//motor #5

    
}



@Override
protected double returnPIDInput() {
  // Return your input value for the PID loop

  int averageDistanceTravelled = (m_leftSensor.getQuadraturePosition() + m_rightSensor.getQuadraturePosition() + 1) / 2;
  return averageDistanceTravelled;
}

@Override
protected void usePIDOutput(double output) {
  // Use output to drive your system, like a motor
  // e.g. yourMotor.set(output);

  m_drive.arcadeDrive(0.0, output);
}
}