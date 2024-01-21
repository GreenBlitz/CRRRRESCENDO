package edu.greenblitz.robotName.subsystems.Lifter;

import com.revrobotics.CANSparkMax;
import org.littletonrobotics.junction.AutoLog;

@AutoLog
public class LifterInputs {

    public double power;

    public double voltage;
    public double position;

    public CANSparkMax.IdleMode idleMode;

}
