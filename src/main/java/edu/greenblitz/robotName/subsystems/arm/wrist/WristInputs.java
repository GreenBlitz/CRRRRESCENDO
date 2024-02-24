package edu.greenblitz.robotName.subsystems.arm.wrist;

import edu.wpi.first.math.geometry.Rotation2d;
import org.littletonrobotics.junction.AutoLog;

@AutoLog
public class WristInputs {

    public double appliedOutput;

    public double outputCurrent;

    public Rotation2d position;

    public double velocity;

    public Rotation2d absoluteEncoderPosition;

    public boolean hasReachedForwardLimit;

    public boolean hasReachedBackwardLimit;
}