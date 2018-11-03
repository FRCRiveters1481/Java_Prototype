/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;
import frc.robot.commands.ElevatorJogDownCommand;
import frc.robot.commands.ElevatorJogUpCommand;
import frc.robot.commands.WristFortyFiveCommand;
import frc.robot.commands.WristGoToPositionCommand;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  public Joystick driverController = new Joystick(RobotMap.driverController);
  public Joystick operatorController = new Joystick(RobotMap.operatorController);
  
  private Button ButtonElevatorJogUp = new JoystickButton(operatorController, RobotMap.elevatorJogUpButton);

  private Button ButtonElevatorJogDown = new JoystickButton(operatorController, RobotMap.elevatorJogDownButton);
  
  private Button ButtonWristFortyFive = new JoystickButton(operatorController, RobotMap.buttonWristFortyFiveButton);
  private Button ButtonWristUp = new JoystickButton(operatorController, RobotMap.buttonWristUpButton);
  private Button ButtonWristHorizontal = new JoystickButton(operatorController, RobotMap.buttonWristHorizontalButton);
  


  private Button ButtonClimbEnable = new JoystickButton(operatorController,RobotMap.ClimbEnableButton);

  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
 

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
  public OI() {
  //  buttonIn.whileHeld(new IntakeCommand(IntakeCommand.intakeDirection.In));
  ButtonElevatorJogUp.whileHeld(new ElevatorJogUpCommand());
  ButtonElevatorJogDown.whileHeld (new ElevatorJogDownCommand());

  ButtonWristFortyFive.whileHeld(new WristFortyFiveCommand());

  ButtonWristUp.whileHeld(new WristGoToPositionCommand(WristGoToPositionCommand.WristPosition.Up));
  ButtonWristHorizontal.whileHeld(new WristGoToPositionCommand(WristGoToPositionCommand.WristPosition.Horizontal));

  }


  public double getClimbVelocity() {
    double retValue;

    /* If the climb's joystick is pressed down (like a button) the climb
    is enabled, and moving the joystick up and down changes the returned value.
    If the button isn't pressed the climb speed is 0.0, for safety. */
    if (ButtonClimbEnable.get()) {
      retValue = operatorController.getRawAxis(1);
    } else {
      retValue = 0.0;
    }
  return retValue;
  }
}

