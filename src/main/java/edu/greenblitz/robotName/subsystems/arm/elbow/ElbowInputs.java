package edu.greenblitz.robotName.subsystems.arm.elbow;

import edu.wpi.first.math.geometry.Rotation2d;
import org.littletonrobotics.junction.AutoLog;

@AutoLog
public class ElbowInputs {

    public double appliedOutput;

    public double outputCurrent;

    public Rotation2d position;

    public double velocity;

    public double acceleration;

    public boolean hasReachedForwardLimit;

    public boolean hasReachedBackwardLimit;

}