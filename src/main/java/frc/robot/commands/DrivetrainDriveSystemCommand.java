package frc.robot.commands;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.RobotController;

import frc.robot.commands.DrivetrainDriveSystemCommand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.drive;

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
        //Values used for Tank Drive
        double throttleJoystick = Robot.m_oi.driverController.getRawAxis(1);
        double steerJoystick = Robot.m_oi.operatorController.getRawAxis(4);

        Robot.m_driveSubsystem.m_drive.arcadeDrive(throttleJoystick, steerJoystick);
        
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        
    }
}