/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.*;
public class WristManualCommand extends Command {
  public WristManualCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_wrist);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double wristUpTrigger = Robot.m_oi.operatorController.getRawAxis(RobotMap.wristUpAxisNumber);
    double wristDownTrigger = Robot.m_oi.operatorController.getRawAxis(RobotMap.wristDownAxisNumber); 
       

    // if wristUpTrigger is pulled, move the wrist up
      if(wristUpTrigger > RobotMap.joystickIsActive){
        Robot.m_wrist.setSpeed(RobotMap.wristUpSpeed); 
      }
      // if wristUpTrigger is not pulled, check if the wristDownTrigger is pulled
     else { 
        if(wristDownTrigger > RobotMap.joystickIsActive){
          Robot.m_wrist.setSpeed(RobotMap.wristDownSpeed); 
        }
        else {
        // if neither trigger is pulled, set wrist speed to stop
          Robot.m_wrist.m_wristMotor.set(ControlMode.Position, RobotMap.floorHeight);
        }
     }
    }  

  

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}

