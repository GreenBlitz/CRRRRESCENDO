package edu.greenblitz.robotName.subsystems.Lifter;

import org.littletonrobotics.junction.AutoLog;

@AutoLog
public class LifterInputs {

    public double appliedOutput;

    public double outputCurrent;

    public double position;

    public double velocity;

    public boolean isForwardSwitchPressed;

    public boolean isBackwardSwitchPressed;
    public boolean isOpenSolenoid;
    public double currentSolenoid;

    public double voltageSolenoid;
}
