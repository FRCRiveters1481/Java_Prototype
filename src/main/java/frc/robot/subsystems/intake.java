/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.*;
import frc.robot.commands.ElevatorManualCommand;
import com.ctre.phoenix.motorcontrol.*;
/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class intake extends Subsystem {
private static WPI_TalonSRX m_intake_talon_1 = new WPI_TalonSRX (RobotMap.intake_talon_1);
private static WPI_TalonSRX m_intake_talon_2 = new WPI_TalonSRX (RobotMap.intake_talon_2);



public void periodic() {



}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new IntakeCommand());

  }
// set the speed of the intake motor 
  public void setSpeed(double speed){
      intake_talon_1.setSpeed(IntakeSetSpeed);
      intake_talon_2.setSpeed(IntakeSetSpeed);
  }
}
