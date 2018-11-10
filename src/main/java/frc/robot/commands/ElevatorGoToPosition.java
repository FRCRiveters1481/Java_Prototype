/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.elevator;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorGoToPosition extends Command {
	public ElevatorGoToPosition() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	public enum ElevatorPosition {
		switchHeight
	};

	private ElevatorPosition m_position;

	public ElevatorGoToPosition(ElevatorPosition position) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.m_elevator);

		m_position = position;

	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {

    /* Figure out how far we have to travel in inches. */
		double inchesToTravel = new RobotMap()
        .convertRelativeElevatorTicksToInches(Math.abs(Robot.m_elevator.getActualPosition() - getTargetHeight(m_position)));
        
        /* Then, calculate the time it'll probably take to get there, add 0.5 seconds
        and set the command's timeout to that value, but only if it's larger than 2.0 seconds; always
        wait at least 2.0 seconds for the elevator to drive anywhere. */
		setTimeout(Math.max((inchesToTravel / RobotMap.elevatorSpeed) + 0.5, 2.0));
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {

		switch (m_position) {
		case switchHeight:
			Robot.m_elevator.setTargetPosition(RobotMap.elevatorSwitchHeight);
			break;

		}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {

		double ElevatorDifference = Math.abs(Robot.m_elevator.getActualPosition() - getTargetHeight(m_position));
	
		if ((ElevatorDifference < RobotMap.elevatorTicksPerHalfInch) || isTimedOut()) {
			return true;
		} else {
			return false;
		}

	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}

	protected int getTargetHeight(ElevatorPosition getTargetHeight) {
		switch (getTargetHeight) {
		case switchHeight:
			return (RobotMap.elevatorSwitchHeight);
		}
		return (0);
	}
}