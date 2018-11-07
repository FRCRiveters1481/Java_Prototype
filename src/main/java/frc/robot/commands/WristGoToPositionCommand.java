/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import com.ctre.phoenix.motorcontrol.can.*;
import frc.robot.Robot;
import frc.robot.RobotMap;


public class WristGoToPositionCommand extends Command {

  public enum WristPosition { Up, Horizontal, FortyFive};

  private WristPosition m_position;

  public WristGoToPositionCommand(WristPosition position) {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_wrist);

    m_position = position;

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

   /* if(Robot.m_wrist.getActualPosition() < getTargetPosition(m_position))
    {
    Robot.m_wrist.setTargetPosition(Robot.m_wrist.getActualPosition() - RobotMap.wristRate) ;
    }
    else if(Robot.m_wrist.getActualPosition() < getTargetPosition(m_position))
    {
      Robot.m_wrist.setTargetPosition(Robot.m_wrist.getActualPosition() + RobotMap.wristRate) ;
    }
    else{
    }
    */
    switch (m_position) {
      case Up:
      Robot.m_wrist.setTargetPosition(RobotMap.wristNinetyPositionCounts);
      break;
      case Horizontal:
      Robot.m_wrist.setTargetPosition(RobotMap.wristZeroPositionCounts);
      break;
      case FortyFive:
      Robot.m_wrist.setTargetPosition(RobotMap.wristFortyFivePositionCounts);
      break;

    }
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
     
    if( Math.abs(Robot.m_wrist.getActualPosition() - getTargetPosition(m_position)) < RobotMap.wristTenPositionCounts)
    {
    return true;
    }
    else {
      return false;
  }

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
  protected int getTargetPosition(WristPosition getTargetPosition){
    switch (m_position) {
      case Up:
      Robot.m_wrist.setTargetPosition(RobotMap.wristNinetyPositionCounts);
      break;
      case Horizontal:
      Robot.m_wrist.setTargetPosition(RobotMap.wristZeroPositionCounts);
      break;
      case FortyFive:
      Robot.m_wrist.setTargetPosition(RobotMap.wristFortyFivePositionCounts);

    }
  return (0); 
}
}


