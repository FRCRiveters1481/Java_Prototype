/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

  //SRX
  public static int frontLeftMotor = 1;
  public static int middleLeftMotor = 5;
  public static int backLeftMotor = 3;
  
  public static int frontRightMotor = 2;
  public static int middleRightMotor = 6;
  public static int backRightMotor = 4;
  

  public static int elevator_talon = 7;
  public final static int kTimeoutMs = 30;
  
  public static int driverController = 0; // driver joystick
  public static int operatorController = 1; // operator joystick
  public final static int PID_PRIMARY = 0;

  public static int intake_talon_1 = 9;
  public static int intake_talon_2 = 10; 

  public static int IntakeSpeed = 500; // controls speed of motor for intake
  public static int JoystickActive = 0.5; 
  public static int IntakeStop = 0; 
}