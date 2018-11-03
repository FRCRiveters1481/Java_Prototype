/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * An example command.  You can replace me with your own command.
 */

public class IntakeCommand extends Command {
    
  private long m_lastEjectAttemptTimestamp;


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
    double intakeInTrigger = Robot.m_oi.driverController.getRawAxis(RobotMap.intakeInAxisNumber);
    double intakeOutTrigger = Robot.m_oi.driverController.getRawAxis(RobotMap.intakeOutAxisNumber); 
       

      if(intakeInTrigger > RobotMap.joystickIsActive){
        Robot.m_intake.setSpeed(intakeInTrigger); 
      }
     
      if (intakeOutTrigger > RobotMap.joystickIsActive){
        Robot.m_intake.setSpeed(-intakeOutTrigger);
        /* Get a timestamp of the current time of operation, in milliseconds to use later
        to determine how long ago we pressed (then released) the Intake eject button */
        m_lastEjectAttemptTimestamp = System.currentTimeMillis();
      }
      if ((intakeOutTrigger > RobotMap.joystickIsActive) | (intakeInTrigger > RobotMap.joystickIsActive)){ 
      }
      else {
        /* The operator has released both buttons.
           Delay a little while before enabling the intake holding (intakeHoldSpeed) 
           to allow the robot to back away from a placed cube to keep the cube from
           being sucked up again into the intake.
           */
        if ( (System.currentTimeMillis() - m_lastEjectAttemptTimestamp) > RobotMap.intakeHoldDelay) {
          /* Enough time has elapsed since we ejected the cube
          Enable the intake holding speed to trap future intake'd cubes */
          Robot.m_intake.setSpeed(RobotMap.intakeHoldSpeed);
        } else {

          /* Not enough time has elapsed since the cube was ejected.
             Keep the intake speed at a neutral value to allow the robot
             to back away from the emplaced cube, and not inadvertantly
             pull in a cube. */
          Robot.m_intake.setSpeed(RobotMap.intakePostEjectSpeed);
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
