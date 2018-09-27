/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.commands.DrivetrainDriveSystemCommand;


/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class drive extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

WPI_VictorSPX m_frontLeft = new WPI_VictorSPX(RobotMap.frontLeftMotor);
WPI_VictorSPX m_midLeft = new WPI_VictorSPX(RobotMap.middleLeftMotor);
WPI_VictorSPX m_rearLeft = new WPI_VictorSPX(RobotMap.backLeftMotor);

//m_frontLeft.setInverted(true);
//m_rearLeft.setInverted(true);
SpeedControllerGroup m_left = new SpeedControllerGroup(m_frontLeft, m_midLeft, m_rearLeft);

WPI_VictorSPX m_frontRight = new WPI_VictorSPX(RobotMap.frontRightMotor);
WPI_VictorSPX m_midRight = new WPI_VictorSPX(RobotMap.middleRightMotor);
WPI_VictorSPX m_rearRight = new WPI_VictorSPX(RobotMap.backRightMotor);

//m_frontRight.getInverted(); 
//m_rearRight.setInverted(true);

SpeedControllerGroup m_right = new SpeedControllerGroup(m_frontRight, m_midRight, m_rearRight);

public DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);


public void periodic() {
  // Override me!

}

@Override
public void initDefaultCommand() {
    //setDefaultCommand(new DriveCommand());
    setDefaultCommand(new DrivetrainDriveSystemCommand());
   

    m_rearLeft.setInverted(true);//motor #3
    m_rearRight.setInverted(true);//motor #4
}
}