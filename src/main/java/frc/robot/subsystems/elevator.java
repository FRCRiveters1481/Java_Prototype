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
import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.SensorCollection;
import edu.wpi.first.wpilibj.DriverStation;
import java.lang.String;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class elevator extends Subsystem {
public static WPI_TalonSRX m_elevator_talon = new WPI_TalonSRX (RobotMap.elevator_talon);                                       //nothing here!!
public enum  ElevatorDirection {
  Up, Down, Hold
};

private static DigitalInput m_limitSwitchElevator = new DigitalInput(RobotMap.elevatorLimitSwitchInput);
int m_lastTargetPosition;
public elevator () {

  m_lastTargetPosition = getActualPosition();
}
public void periodic() {

  m_elevator_talon.config_kF(0,  SmartDashboard.getNumber("MotorKF", 0.0), 30); 
  m_elevator_talon.config_kP(0,  SmartDashboard.getNumber("MotorKp", 0.0), 30); 
  m_elevator_talon.config_kI(0,  SmartDashboard.getNumber("MotorKI", 0.0), 30); 
  m_elevator_talon.config_kD(0,  SmartDashboard.getNumber("MotorKD", 0.0), 30); 
  m_elevator_talon.configClosedloopRamp(SmartDashboard.getNumber("ElevatorRampRate",0.1),0);

  

  if (m_limitSwitchElevator.get() == false) {
    m_elevator_talon.getSensorCollection().setQuadraturePosition(0,0);
  }
  SmartDashboard.putBoolean("ElevatorLimitSwitch", m_limitSwitchElevator.get());
  SmartDashboard.putNumber("ElevatorEncoderCounts",  getActualPosition());
 
  SmartDashboard.putNumber("bullseyeElevatorPosition",  m_lastTargetPosition);
}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new ElevatorManualCommand());
  

		m_elevator_talon.configSelectedFeedbackSensor(	FeedbackDevice.QuadEncoder,				// Local Feedback Source

		RobotMap.PID_PRIMARY,					// PID Slot for Source [0, 1]

    RobotMap.kTimeoutMs);	
    
    m_elevator_talon.configSensorTerm(SensorTerm.Sum0, FeedbackDevice.RemoteSensor0, RobotMap.kTimeoutMs);				// Feedback Device of Remote Talon

    
    m_elevator_talon.configSensorTerm(SensorTerm.Sum1, FeedbackDevice.CTRE_MagEncoder_Relative, RobotMap.kTimeoutMs);	// Quadrature Encoder of current Talon		
    m_elevator_talon.configNominalOutputForward(0, 30); 
    m_elevator_talon.configNominalOutputReverse(0, 30); 
    m_elevator_talon.configPeakOutputForward(1, 30); 
    m_elevator_talon.configPeakOutputReverse(-1, 30); 
    m_elevator_talon.setSensorPhase(false);
    m_elevator_talon.config_kF(0, 0.0, 30); 
    m_elevator_talon.config_kP(0, 1.0, 30); 
    m_elevator_talon.config_kI(0, 0.0, 30); 
    m_elevator_talon.config_kD(0, 0.0, 30); 

    SmartDashboard.putNumber("MotorKF", 0.0); 
    SmartDashboard.putNumber("MotorKp", 1.0);
    SmartDashboard.putNumber("MotorKI", 0.0);
    SmartDashboard.putNumber("MotorKD", 0.0);

    SmartDashboard.putNumber("ElevatorEncoderCounts", getActualPosition());

    SmartDashboard.putBoolean("ElevatorLimitSwitch", m_limitSwitchElevator.get());
    SmartDashboard.putNumber("ElevatorEncoderCounts", getActualPosition());

    SmartDashboard.putNumber("ElevatorRampRate",0.1);
  
   
    
  }

  public void setTargetPosition(int TargetPosition) {

  if (TargetPosition < RobotMap.elevatorJogLowerLimit)  {
    TargetPosition = RobotMap.elevatorJogLowerLimit;
  }
  if (TargetPosition > RobotMap.elevatorJogUpperLimit){
    TargetPosition = RobotMap.elevatorJogUpperLimit;
  }
    m_elevator_talon.set(ControlMode.Position, TargetPosition);
    m_lastTargetPosition = TargetPosition;
  }


  

  public int getTargetPosition() {

  return m_lastTargetPosition;
   
    }

    public int getActualPosition() {

      return m_elevator_talon.getSensorCollection().getQuadraturePosition();
       
        }
}
