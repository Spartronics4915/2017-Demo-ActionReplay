package org.usfirst.frc.team4915.robot.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.usfirst.frc.team4915.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

public class ReplayCommand extends Command {

    private Drivetrain m_drivetrain;
    private List<Double> m_forwardHistory = new ArrayList<Double>();
    private List<Double> m_rotationHistory = new ArrayList<Double>();
    private int m_i;

    public ReplayCommand(Drivetrain drivetrain) {
        m_drivetrain = drivetrain;

        if (m_forwardHistory.size() != m_rotationHistory.size()) {
            System.out.println("ERROR: Size of replay lists are different");
        }

        m_i = 0;
        requires(m_drivetrain);
    }

    @Override
    protected void initialize() {
        m_forwardHistory = m_drivetrain.getForwardHistory();
        m_rotationHistory = m_drivetrain.getRotationHistory();
        System.out.println("Replaying f: " + Arrays.toString(m_forwardHistory.toArray()));
        System.out.println("Replaying r: " + Arrays.toString(m_rotationHistory.toArray()));
    }

    @Override
    protected void execute() {
        if (m_i >= m_forwardHistory.size()) {
            return;
        }
        double forwardAmount = m_forwardHistory.get(m_i);
        double rotationAmount = m_rotationHistory.get(m_i);

        m_drivetrain.drive(forwardAmount, rotationAmount);

        System.out.println("Replayed #" + m_i + "/" + m_forwardHistory.size()
                + " <" + forwardAmount + ", " + rotationAmount + ">\r\n");

        m_i++;
    }

    @Override
    protected boolean isFinished() {
        return m_i >= m_forwardHistory.size();
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }
}
