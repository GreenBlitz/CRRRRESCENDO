package edu.greenblitz.robotName.subsystems.lifter;

import edu.wpi.first.math.geometry.Rotation2d;
import org.littletonrobotics.junction.AutoLog;

@AutoLog
public class LifterInputs {

    public double appliedOutput;

    public double outputCurrent;
    
    public Rotation2d positionReference;

    public Rotation2d position;

    public double velocity;

    public boolean isForwardSwitchPressed;

    public boolean isBackwardSwitchPressed;

    public boolean isOpenSolenoid;

    public double currentSolenoid;

    public double voltageSolenoid;
}