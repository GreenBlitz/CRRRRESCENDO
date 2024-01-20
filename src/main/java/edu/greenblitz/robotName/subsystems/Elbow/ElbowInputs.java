package edu.greenblitz.robotName.subsystems.Elbow;

import org.littletonrobotics.junction.AutoLog;

@AutoLog
public class ElbowInputs {

    public double appliedOutput;

    public double outputCurrent;

    public double position;

    public double velocity;

    public double absoluteEncoderPosition;

    public boolean hasHitForwardLimit;

    public boolean hasHitBackwardsLimit;

}
