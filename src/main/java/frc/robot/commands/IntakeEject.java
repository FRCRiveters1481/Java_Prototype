/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

public class IntakeEject extends Command {
  private long m_cubeTimeStamp;
  public IntakeEject() {
    requires(Robot.m_intake);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_intake.setSpeed(.65);
    m_cubeTimeStamp =7000 + System.currentTimeMillis();
    System.out.print("init\n");
  
//TODO: start timer and start motor
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.m_intake.setSpeed(.65);

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    
    if (m_cubeTimeStamp <= System.currentTimeMillis())
    {
      Robot.m_intake.setSpeed(0);
      return true;
    }
    else 
    {
      return false;
    }
        

    //TODO: check timer for 7 seconds elapse
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
//TODO: motors are shut off 
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
