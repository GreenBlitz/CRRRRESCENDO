package edu.greenblitz.robotName.subsystems.Arm.Wrist;

import org.littletonrobotics.junction.AutoLog;

@AutoLog
public class WristInputs {

    public double appliedOutput;

    public double outputCurrent;

    public double position;

    public double velocity;

    public double absoluteEncoderPosition;

    public boolean hasHitForwardLimit;

    public boolean hasHitBackwardsLimit;

    public boolean isObjectInside;

    public double kP;
    public double kI;
    public double kD;
}
