package edu.greenblitz.robotName.subsystems.Arm.EndEffector.WristUtils;

import org.littletonrobotics.junction.AutoLog;

@AutoLog
public class WristInputs {

    public double appliedOutput;

    public double outputCurrent;

    public double position;

    public double velocity;

    public double absoluteEncoderPosition;

    public double temperature;

    public boolean hasReachedForwardLimit;

    public boolean hasReachedBackwardLimit;

    public boolean isObjectInArm;
}
