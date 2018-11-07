/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import frc.robot.commands.ElevatorGoToPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class autonTestCommand extends CommandGroup {
  /**
   * Add your docs here.
   */
  public autonTestCommand() {
    addSequential(new ElevatorGoToPosition(ElevatorGoToPosition.ElevatorPosition.switchHeight));
    addSequential(new IntakeEject());
    
  }
}
