package edu.greenblitz.robotName.subsystems.shooter.Pivot;

import edu.wpi.first.math.geometry.Rotation2d;
import org.littletonrobotics.junction.AutoLog;

@AutoLog
public class PivotInputs {

    public double appliedOutput;

    public double outputCurrent;

    public Rotation2d position;

    public double velocity;
    
    public double acceleration;

    public double absoluteEncoderPosition;

    public double temperature;

    public boolean hasHitForwardLimit;

    public boolean hasHitBackwardsLimit;

}
