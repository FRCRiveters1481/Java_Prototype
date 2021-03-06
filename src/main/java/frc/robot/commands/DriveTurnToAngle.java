/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.drive;

public class DriveTurnToAngle extends Command {

  private double m_angle;

  public DriveTurnToAngle(double angle) {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_driveSubsystem);

    m_angle = angle;

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.print(String.format("DriveTurnToAngle doesn't do anything yet, but you did request %.2f degrees of turn\n",m_angle));
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
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
