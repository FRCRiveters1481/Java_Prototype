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
import frc.robot.subsystems.elevator;
import com.ctre.phoenix.motorcontrol.*;

/**
 * An example command.  You can replace me with your own command.
 */
public class ElevatorManualCommand extends Command {
  public ElevatorManualCommand() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_elevator);
  
  }
  public enum elevatorPosition {
    Floor , Switch, lowScale, highScale
  
  };

public ElevatorManualCommand(elevatorPosition commandedPosition){
 
  
switch (commandedPosition) {
  case Floor:
   elevator.m_elevator_talon.set(ControlMode.Position, RobotMap.floorHeight);
    break;
  case Switch:

elevator.m_elevator_talon.set(ControlMode.Position, RobotMap.switchHeight);
    break;
  case lowScale:
elevator.m_elevator_talon.set(ControlMode.Position, RobotMap.lowScaleHeight);
    break;
  case highScale:
elevator.m_elevator_talon.set(ControlMode.Position, RobotMap.highScaleHeight);
    break;

  default:
    break;
    
}

}

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

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
