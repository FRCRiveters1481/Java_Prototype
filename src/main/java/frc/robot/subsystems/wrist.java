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
import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.SensorCollection;
import edu.wpi.first.wpilibj.DriverStation;
import java.lang.String;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;
/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class wrist extends Subsystem {
public static WPI_TalonSRX m_wristTalon = new WPI_TalonSRX (RobotMap.wristTalon);

int m_lastTargetPosition;

public void periodic() {



}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new WristManualCommand());

    m_wristTalon.configSelectedFeedbackSensor(	FeedbackDevice.QuadEncoder,				// Local Feedback Source

		RobotMap.PID_PRIMARY,					// PID Slot for Source [0, 1]

    RobotMap.kTimeoutMs);	
    
    m_wristTalon.configSensorTerm(SensorTerm.Sum0, FeedbackDevice.RemoteSensor0, RobotMap.kTimeoutMs);				// Feedback Device of Remote Talon


  }
// set the speed of the wrist motor 
  public void setSpeed(double speed){
    m_wristTalon.set(speed);
  }

  public void setTargetPosition(int TargetPosition) {

    if (TargetPosition < RobotMap.jogLowerLimit)  {
      TargetPosition = RobotMap.jogLowerLimit;
    }
    if (TargetPosition > RobotMap.jogUpperLimit){
      TargetPosition = RobotMap.jogUpperLimit;
    }
    m_wristTalon.set(ControlMode.Position, TargetPosition);
      m_lastTargetPosition = TargetPosition;
    }

  public int getTargetPosition() {

    return m_lastTargetPosition;
     
      }
  
      public int getActualPosition() {
  
        return m_wristTalon.getSensorCollection().getQuadraturePosition();
         
          }
}