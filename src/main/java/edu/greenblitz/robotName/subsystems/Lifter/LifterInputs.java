package edu.greenblitz.robotName.subsystems.Lifter;

import edu.wpi.first.math.geometry.Rotation2d;
import org.littletonrobotics.junction.AutoLog;

@AutoLog
public class LifterInputs {

    public double appliedOutput;

    public double outputCurrent;

    public Rotation2d position;

    public double velocity;
    public boolean isForwardSwitchPressed;
    public boolean isBackwardSwitchPressed;
}
