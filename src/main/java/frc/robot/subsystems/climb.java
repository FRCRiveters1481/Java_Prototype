/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ClimbManualCommand;
import com.ctre.phoenix.motorcontrol.can.*;


/**
 * Add your docs here.
 */
public class climb extends Subsystem {
  private static WPI_TalonSRX m_climb_talon = new WPI_TalonSRX (RobotMap.climb_talon);

  @Override
  public void initDefaultCommand() {

    setDefaultCommand(new ClimbManualCommand());
  }

  public void setClimbVelocity(double velocity) {

    /* Limit the climb speed and torque to match the mechanical
    limits, including the ratchet. */
    velocity = Math.min(RobotMap.ClimbSpeedMax, velocity);
    velocity = Math.max(RobotMap.ClimbSpeedMin, velocity);
    
    m_climb_talon.set(velocity);
  }

  public double getClimbVelocity() {
    return m_climb_talon.get();
  }
}
