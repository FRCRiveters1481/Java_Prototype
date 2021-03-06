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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;
import java.lang.String;
/**
 * An example command.  You can replace me with your own command.
 */
public class ElevatorManualCommand extends Command {
  public ElevatorManualCommand() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_elevator);
  
  }
  public enum elevatorPosition {ClimbPosition,
    Hold, Floor , Switch, LowScale, HighScale;
  
  };

public ElevatorManualCommand(elevatorPosition commandedPosition){
 requires(Robot.m_elevator);
      
}



  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    
    int POVJoystick = Robot.m_oi.operatorController.getPOV();
 
    switch (POVJoystick) {
      case 270:
      Robot.m_elevator.setTargetPosition(RobotMap.elevatorClimbPosition);
      break;
      case 180:
      Robot.m_elevator.setTargetPosition(RobotMap.elevatorFloorHeight);
      break;
      case 135:
      Robot.m_elevator.setTargetPosition(RobotMap.elevatorSwitchHeight);
      break;
      case 0:
      Robot.m_elevator.setTargetPosition( RobotMap.elevatorHighScaleHeight);
      break;
      default:
   
      break;
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
