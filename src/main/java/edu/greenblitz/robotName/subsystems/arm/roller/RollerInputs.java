package edu.greenblitz.robotName.subsystems.arm.roller;

import edu.wpi.first.math.geometry.Rotation2d;
import org.littletonrobotics.junction.AutoLog;

@AutoLog
public class RollerInputs {

    public double appliedOutput;

    public double outputCurrent;
    
    public boolean isObjectIn;
    
    public Rotation2d position;
}