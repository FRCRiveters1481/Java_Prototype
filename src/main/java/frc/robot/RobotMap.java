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

  /**
   *
   */

  // SRX
  public final static int frontLeftMotor = 1;
  public final static int middleLeftMotor = 5;
  public final static int backLeftMotor = 3;
  
  public final static int frontRightMotor = 2;
  public final static int middleRightMotor = 6;
  public final static int backRightMotor = 4;
  

  public final static int elevator_talon = 7;
  public final static int kTimeoutMs = 30;
  public final static double elevatorGearDiameter= 2.1;//this is not the real number this is a non magical number.  
  public final static int hold = -1;
  public final static int elevatorFloorHeight = 0;
  public final static int elevatorJogLowerLimit = 0;
  public final static int  elevatorSwitchHeight = 5168;
  public final static int  elevatorLowScaleHeight = 14116;
  public final static int elevatorHighScaleHeight = 21044;
  public final static int elevatorJogUpperLimit = 21500; 
  public final static int elevatorLimitSwitchInput = 2;
  public final static int elevatorJogUpButton = 5;
  public final static int elevatorJogDownButton = 6;
  public final static int elevatorRate = 200; //encoder counts per 20 mil secs
  public final static double jogUpdateDistanceOvertravelOffset = 4.0; /* inches */
  public final static double jogDownDistanceOvertravelOffset = 4.5; /* inches */
  public final static double elevatorTicksPerInch = 288.669f; /*  ticks */
  private final static int elevatorTicksAtBottomFromFloor = 2915; /* ticks */
  public static boolean elevatorIsUpFlag = false; // Flag to slow down driving when the elevator is up to avoid tippage
  public final static int elevatorIsUpCountThreshold = 100;
  public final static double throttleFactorForElevatorUp = 0.5; // Factor to slow the joystick response by when the elevator is up 

  public final static int driverController = 0; // driver joystick
  public final static int operatorController = 1; // operator joystick
  public final static int forwardReverseAxis = 1;
  public final static int leftRightAxis = 4;

  public final static int PID_PRIMARY = 0;

  public final static int intakeTalon1 = 11;
  public final static int intakeTalon2 = 10; 

  public final static int leftDriveControllerSensor = intakeTalon1;
  public final static int rightDriveControllerSensor = intakeTalon2;
  

  //public final static double intakeInSpeed = 0.5; // controls speed of motors for bringing in a cube
  //public final static double intakeOutSpeed = -0.5; // controls speed of motors for shooting out a cube
  public final static double intakeHoldSpeed = 0.2; // controls speed of motors to hold cube
  public final static double joystickIsActive = 0.1; 
  //public final static int intakeStop = 0; 

  public final static int intakeInAxisNumber = 3;
  public final static int intakeOutAxisNumber = 2;
  
  public final static int wristTalon = 8;
  public final static int wristUpAxisNumber = 2;
  public final static int wristDownAxisNumber = 3;

  //public static double wristDownSpeed = -0.3;
  //public static double wristUpSpeed = 0.75;
  //public static double wristHoldSpeed = 0.4;
  //public static int wristMotorStop = 0; 

  public static int wristRate = 2000; //encoder counts per 20 mil secs 
  public static int buttonWristFortyFiveButton = 2; // Takes wrist to 45 degree angle position
  public static int buttonWristUpButton = 3; // Takes wrist all the way up
  public static int buttonWristHorizontalButton = 1; // Takes wrist to horizontal (cube pickup)
  public static int wristJogLowerLimit = 100; // Don't go below this encoder count on the way down
  public static int wristJogUpperLimit = 890000; // Don't go above this encoder count on the way up
  //public static int wristLimitSwitchInput = 2; // What is this when it is on the Talon?
  public static int wristZeroPositionCounts = 0; // Encoder coutns for wrist level with the floor
  public static int wristFortyFivePositionCounts = 49000; // Encoder counts for wrist 45 degrees to the floor
  public static int wristNinetyPositionCounts = 90000; // Encoder counts for wrist 90 degrees to the floor


  public int convertRelativeInchesToElevatorTicks(double inches) {
    return (int)(inches * elevatorTicksPerInch);
  }

  public int convertAbsoluteInchesToElevatorTicks(double inches) {
    return (int)(inches * elevatorTicksPerInch) + elevatorTicksAtBottomFromFloor;

    /* y = m(x) + b

    where:
    y is the number of ticks to send to the talon set() function
    m is the number of ticks per inch of elevator travel
    b is the number of ticks that represent the height of the lowest point the elevator can travel
     (the "0" tick) and the floor.
     */
  }

  public static int climb_talon = 9; /* CAN ID of the climb_talon */
public static int ClimbEnableButton = 8;


public double convertDriveTicksPerTimeToInchesPerSecond(int ticksPerUnitTime) {

  return (double) ticksPerUnitTime;
}


public int convertInchesToDriveTicks(double inches) {

  return (int) inches;
}
}