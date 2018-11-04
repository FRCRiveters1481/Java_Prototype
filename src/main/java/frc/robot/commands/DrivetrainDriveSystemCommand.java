package frc.robot.commands;

import frc.robot.Robot;

import frc.robot.commands.DrivetrainDriveSystemCommand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;

/**
 *
 */
public class DrivetrainDriveSystemCommand extends Command {


    public DrivetrainDriveSystemCommand() {
        requires(Robot.m_driveSubsystem);
    
    
    }


    @Override
    protected void initialize() {
        super.initialize();
    
    }

    @Override
    protected void execute() {
        // Check the position of the elevator to see if we need to throttle the joystick inputs to avoid tippage
        int elevatorPositionEncoderCounts = Robot.m_elevator.getActualPosition();
        
        // Read the axes of the joysticks
        double throttleJoystick = Robot.m_oi.driverController.getRawAxis(RobotMap.forwardReverseAxis);
        double steerJoystick = Robot.m_oi.driverController.getRawAxis(RobotMap.leftRightAxis);
        
        if (elevatorPositionEncoderCounts > RobotMap.elevatorIsUpCountThreshold){
            throttleJoystick = throttleJoystick * RobotMap.throttleFactorForElevatorUp;
            steerJoystick = steerJoystick * RobotMap.steerFactorForElevatorUp;
        }

        Robot.m_driveSubsystem.m_drive.arcadeDrive(-steerJoystick, throttleJoystick);
        
    }

    @Override 
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        
    }
}