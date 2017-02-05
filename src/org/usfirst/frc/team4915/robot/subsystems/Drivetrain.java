package org.usfirst.frc.team4915.robot.subsystems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.usfirst.frc.team4915.robot.OI;
import org.usfirst.frc.team4915.robot.commands.DriveCommand;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivetrain extends Subsystem {

    private CANTalon m_leftFollowerMotor;
    private CANTalon m_leftMasterMotor;
    private CANTalon m_rightFollowerMotor;
    private CANTalon m_rightMasterMotor;

    private RobotDrive m_robotDrive;

    private boolean recording = false;
    private List<Double> m_forwardHistory = new ArrayList<Double>()/*
                                                                    * { {
                                                                    * add(0.5);
                                                                    * add(0.5);
                                                                    * add(0.5);
                                                                    * add(0.5);
                                                                    * add(0.5);
                                                                    * add(0.5);
                                                                    * add(0.5);
                                                                    * add(0.5);
                                                                    * add(0.5);
                                                                    * add(0.5);
                                                                    * add(0.5);
                                                                    * add(0.5);
                                                                    * add(0.5);
                                                                    * add(0.5);
                                                                    * add(0.5);
                                                                    * add(0.5);
                                                                    * add(0.5);
                                                                    * add(0.5);
                                                                    * add(0.5);
                                                                    * add(0.5);
                                                                    * add(0.5);
                                                                    * add(-0.5);
                                                                    * add(-0.5);
                                                                    * add(-0.5);
                                                                    * add(-0.5);
                                                                    * add(-0.5);
                                                                    * add(-0.5);
                                                                    * add(-0.5);
                                                                    * add(-0.5);
                                                                    * add(-0.5);
                                                                    * add(-0.5);
                                                                    * add(-0.5);
                                                                    * add(-0.5);
                                                                    * add(-0.5);
                                                                    * add(-0.5);
                                                                    * add(-0.5);
                                                                    * add(-0.5);
                                                                    * add(-0.5);
                                                                    * add(-0.5);
                                                                    * add(-0.5);
                                                                    * add(-0.5);
                                                                    * add(-0.5);
                                                                    * add(-0.5);
                                                                    * add(-0.5);
                                                                    * add(-0.5);
                                                                    * add(-0.5);
                                                                    * add(-0.5);
                                                                    * add(-0.5);
                                                                    * add(-0.5);
                                                                    * add(-0.5);
                                                                    * add(-0.5);
                                                                    * add(-0.5);
                                                                    * } }
                                                                    */;
    private List<Double> m_rotationHistory = new ArrayList<Double>()/*
                                                                     * { {
                                                                     * add(0.0);
                                                                     * add(0.0);
                                                                     * add(0.0);
                                                                     * add(0.0);
                                                                     * add(0.0);
                                                                     * add(0.0);
                                                                     * add(0.0);
                                                                     * add(0.0);
                                                                     * add(0.0);
                                                                     * add(0.0);
                                                                     * add(0.0);
                                                                     * add(0.0);
                                                                     * add(0.0);
                                                                     * add(0.0);
                                                                     * add(0.0);
                                                                     * add(0.0);
                                                                     * add(0.0);
                                                                     * add(0.0);
                                                                     * add(0.0);
                                                                     * add(0.0);
                                                                     * add(0.0);
                                                                     * add(-0.0)
                                                                     * ;
                                                                     * add(-0.0)
                                                                     * ;
                                                                     * add(-0.0)
                                                                     * ;
                                                                     * add(-0.0)
                                                                     * ;
                                                                     * add(-0.0)
                                                                     * ;
                                                                     * add(-0.0)
                                                                     * ;
                                                                     * add(-0.0)
                                                                     * ;
                                                                     * add(-0.0)
                                                                     * ;
                                                                     * add(-0.0)
                                                                     * ;
                                                                     * add(-0.0)
                                                                     * ;
                                                                     * add(-0.0)
                                                                     * ;
                                                                     * add(-0.0)
                                                                     * ;
                                                                     * add(-0.0)
                                                                     * ;
                                                                     * add(-0.0)
                                                                     * ;
                                                                     * add(-0.0)
                                                                     * ;
                                                                     * add(-0.0)
                                                                     * ;
                                                                     * add(-0.0)
                                                                     * ;
                                                                     * add(-0.0)
                                                                     * ;
                                                                     * add(-0.0)
                                                                     * ;
                                                                     * add(-0.0)
                                                                     * ;
                                                                     * add(-0.0)
                                                                     * ;
                                                                     * add(-0.0)
                                                                     * ;
                                                                     * add(-0.0)
                                                                     * ;
                                                                     * add(-0.0)
                                                                     * ;
                                                                     * add(-0.0)
                                                                     * ;
                                                                     * add(-0.0)
                                                                     * ;
                                                                     * add(-0.0)
                                                                     * ;
                                                                     * add(-0.0)
                                                                     * ;
                                                                     * add(-0.0)
                                                                     * ;
                                                                     * add(-0.0)
                                                                     * ;
                                                                     * add(-0.0)
                                                                     * ; } }
                                                                     */;

    public Drivetrain() {

        m_leftFollowerMotor = new CANTalon(1);
        m_leftMasterMotor = new CANTalon(3);
        m_rightFollowerMotor = new CANTalon(2);
        m_rightMasterMotor = new CANTalon(4);

        m_leftMasterMotor.changeControlMode(TalonControlMode.PercentVbus);
        m_leftFollowerMotor.changeControlMode(TalonControlMode.Follower);
        m_leftFollowerMotor.set(m_leftMasterMotor.getDeviceID());

        m_rightMasterMotor.changeControlMode(TalonControlMode.PercentVbus);
        m_rightFollowerMotor.changeControlMode(TalonControlMode.Follower);
        m_rightFollowerMotor.set(m_rightMasterMotor.getDeviceID());

        m_robotDrive = new RobotDrive(m_leftMasterMotor, m_rightMasterMotor);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new DriveCommand(this, OI.joystick));
    }

    public void drive(double forward, double rotation) {
        if (recording) {
            m_forwardHistory.add(forward);
            m_rotationHistory.add(rotation);
        }
        m_robotDrive.arcadeDrive(forward, rotation);
    }

    public void startRecording() {
        recording = true;
    }

    public void stopRecording() {
        recording = false;
        System.out.println("f: " + Arrays.toString(m_forwardHistory.toArray()));
        System.out.println("r: " + Arrays.toString(m_rotationHistory.toArray()));
    }

    public List<Double> getForwardHistory() {
        return m_forwardHistory;
    }

    public List<Double> getRotationHistory() {
        return m_rotationHistory;
    }

}
