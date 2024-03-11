package edu.greenblitz.robotName.subsystems.climbing.lifter;

import org.littletonrobotics.junction.AutoLog;

@AutoLog
public class LifterInputs {

    public double appliedOutput;

    public double outputCurrent;
    
    public double positionReference;

    public double position;

    public double velocity;

    public boolean isForwardSwitchPressed;

    public boolean isBackwardSwitchPressed;

}