package org.usfirst.frc.team4915.robot;

import org.usfirst.frc.team4915.robot.commands.ReplayCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OI {

    public static Joystick joystick = new Joystick(0);
    JoystickButton startRecording = new JoystickButton(joystick, 6);
    JoystickButton stopRecording = new JoystickButton(joystick, 7);
    JoystickButton replay = new JoystickButton(joystick, 8);

    public OI() {
        SmartDashboard.putNumber("rotation multiply", -1);
        SmartDashboard.putNumber("rotation add", 0);
        startRecording.whenPressed(new Command() {

            @Override
            public void execute() {
                System.out.println("Recording");
                Robot.m_drivetrain.startRecording();
            }

            @Override
            public boolean isFinished() {
                return true;
            }
        });
        stopRecording.whenPressed(new Command() {

            @Override
            public void execute() {
                System.out.println("Done with " + Robot.m_drivetrain.getForwardHistory().size() + " entries");
                Robot.m_drivetrain.stopRecording();
            }

            @Override
            public boolean isFinished() {
                return true;
            }
        });

        replay.whenPressed(new ReplayCommand(Robot.m_drivetrain));
    }

}
