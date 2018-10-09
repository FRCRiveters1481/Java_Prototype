/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.intake;
import frc.robot.RobotMap;

/**
 * An example command.  You can replace me with your own command.
 */

public class IntakeCommand extends Command {
    
    public enum intakeDirection{
        In, Out,  Hold
    };
    private intakeDirection m_intakeCommand;

  public IntakeCommand(intakeDirection command) {
     m_intakeCommand = command;

    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_intake);
  }
  public IntakeCommand() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_intake);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() { 
    if (m_intakeCommand == intakeDirection.In) {  
      Robot.m_intake.setSpeed(RobotMap.intakeSpeed);

      if(Robot.m_oi.driverController.getRawAxis(RobotMap.intakeInAxisNumber) > RobotMap.buttonPressed){
         Robot.m_intake.setSpeed(RobotMap.intakeSpeed);
      }
      else {
        Robot.m_intake.setSpeed(RobotMap.intakeHoldSpeed);
      }
    }   
    if (m_intakeCommand == intakeDirection.Out) {
        Robot.m_intake.setSpeed(RobotMap.intakeOutSpeed);
      
    }  
    if (m_intakeCommand == intakeDirection.Hold) {
            Robot.m_intake.setSpeed(RobotMap.intakeHoldSpeed);
          
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
