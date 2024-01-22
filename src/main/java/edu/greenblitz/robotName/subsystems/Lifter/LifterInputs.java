package edu.greenblitz.robotName.subsystems.Lifter;

import com.revrobotics.CANSparkMax;
import org.littletonrobotics.junction.AutoLog;

@AutoLog
public class LifterInputs {

    public double appliedOutput;
    public double outputCurrent;
    public double position;
    public double velocity;
    public boolean isSwitchPressed;

    public double kP;
    public double kI;
    public double kD;

}
