package org.usfirst.frc.team4915.robot.commands;

import org.usfirst.frc.team4915.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveCommand extends Command {

    private Drivetrain m_drivetrain;
    private Joystick m_joystick;

    public DriveCommand(Drivetrain drivetrain, Joystick joystick) {
        m_drivetrain = drivetrain;
        m_joystick = joystick;
        requires(m_drivetrain);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        double forwardAmount = m_joystick.getAxis(AxisType.kY) * -1;

        @SuppressWarnings("deprecation")
        double rotationAmount =
                m_joystick.getAxis(AxisType.kX) * SmartDashboard.getNumber("rotation multiply") + SmartDashboard.getNumber("rotation add");
        m_drivetrain.drive(forwardAmount, rotationAmount);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }
}
