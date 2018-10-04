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
import frc.robot.commands.*;
import com.ctre.phoenix.motorcontrol.*;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class intake extends Subsystem {
private static WPI_TalonSRX intakeLeftMotor = new WPI_TalonSRX (RobotMap.intakeLeftMotorPort);
private static WPI_TalonSRX intakeRightMotor = new WPI_TalonSRX (RobotMap.intakeRightMotorPort);
private SpeedControllerGroup intakeMotors = new SpeedControllerGroup(intakeLeftMotor,intakeRightMotor);



public void periodic() {



}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new IntakeCommand());
    intakeRightMotor.setInverted(true);

  }
// set the speed of the intake motor 
  public void setSpeed(double speed){
      intakeMotors.set(speed);
      
  }
//set hold speed
  public void hold() {
      intakeMotors.set(RobotMap.intakeHoldSpeed);
      
  }
}
