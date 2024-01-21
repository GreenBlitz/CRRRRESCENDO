package edu.greenblitz.robotName.subsystems.Arm.EndEffector;

import org.littletonrobotics.junction.AutoLog;

@AutoLog
public class EndEffectorInputs {

    public double appliedOutput;

    public double outputCurrent;

    public double position;

    public double velocity;

    public double absoluteEncoderPosition;

    public boolean hasHitForwardLimit;

    public boolean hasHitBackwardsLimit;

    public double kP;
    public double kI;
    public double kD;
}
