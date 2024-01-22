package edu.greenblitz.robotName.subsystems.Pivot;

import org.littletonrobotics.junction.AutoLog;

@AutoLog
public class PivotInputs {

    public double appliedOutput;

    public double outputCurrent;

    public double position;

    public double velocity;

    public double absoluteEncoderPosition;

    public double temperature;

    public boolean hasHitForwardLimit;

    public boolean hasHitBackwardsLimit;

}
