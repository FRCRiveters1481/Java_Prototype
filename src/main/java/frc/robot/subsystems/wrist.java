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

private static DigitalInput m_limitSwitchWrist = new DigitalInput(RobotMap.elevatorLimitSwitchInput);
int m_lastTargetPosition;

public wrist(){
  m_lastTargetPosition = getActualPosition();
}

public void periodic() {
  m_wristTalon.config_kF(0,  SmartDashboard.getNumber("MotorKF", 0.0), 30); 
  m_wristTalon.config_kP(0,  SmartDashboard.getNumber("MotorKp", 0.0), 30); 
  m_wristTalon.config_kI(0,  SmartDashboard.getNumber("MotorKI", 0.0), 30); 
  m_wristTalon.config_kD(0,  SmartDashboard.getNumber("MotorKD", 0.0), 30); 
  m_wristTalon.configClosedloopRamp(SmartDashboard.getNumber("WristRampRate",0.1),0);

  

  if (m_limitSwitchWrist.get() == false) {
    m_wristTalon.getSensorCollection().setQuadraturePosition(0,0);
  }
  SmartDashboard.putBoolean("WristLimitSwitch", m_limitSwitchWrist.get());
  SmartDashboard.putNumber("WristEncoderCounts",  getActualPosition());
 
  SmartDashboard.putNumber("bullseyeWristPosition",  m_lastTargetPosition);


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

    m_wristTalon.configSensorTerm(SensorTerm.Sum1, FeedbackDevice.CTRE_MagEncoder_Relative, RobotMap.kTimeoutMs);	// Quadrature Encoder of current Talon		
    m_wristTalon.configNominalOutputForward(0, 30); 
    m_wristTalon.configNominalOutputReverse(0, 30); 
    m_wristTalon.configPeakOutputForward(1, 30); 
    m_wristTalon.configPeakOutputReverse(-1, 30); 
    m_wristTalon.setSensorPhase(false);
    m_wristTalon.config_kF(0, 0.0, 30); 
    m_wristTalon.config_kP(0, 1.0, 30); 
    m_wristTalon.config_kI(0, 0.0, 30); 
    m_wristTalon.config_kD(0, 0.0, 30); 

    SmartDashboard.putNumber("MotorKF", 0.0); 
    SmartDashboard.putNumber("MotorKp", 1.0);
    SmartDashboard.putNumber("MotorKI", 0.0);
    SmartDashboard.putNumber("MotorKD", 0.0);

    SmartDashboard.putNumber("WristEncoderCounts", getActualPosition());

    SmartDashboard.putBoolean("WristLimitSwitch", m_limitSwitchWrist.get());
    SmartDashboard.putNumber("WristEncoderCounts", getActualPosition());

    SmartDashboard.putNumber("WristRampRate",0.1);
  
  }
// set the speed of the wrist motor 
  public void setSpeed(double speed){
    m_wristTalon.set(speed);
  }

  public void setTargetPosition(int TargetPosition) {

    if (TargetPosition < RobotMap.wristJogLowerLimit)  {
      TargetPosition = RobotMap.wristJogLowerLimit;
    }
    if (TargetPosition > RobotMap.wristJogUpperLimit){
      TargetPosition = RobotMap.wristJogUpperLimit;
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