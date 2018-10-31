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
import frc.robot.commands.WristManualCommand;
/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class wrist extends Subsystem {
public static WPI_TalonSRX m_wristMotor = new WPI_TalonSRX (RobotMap.wristMotor);



public void periodic() {



}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new WristManualCommand());

  }
// set the speed of the wrist motor 
  public void setSpeed(double speed){
      m_wristMotor.set(speed);
  }
}