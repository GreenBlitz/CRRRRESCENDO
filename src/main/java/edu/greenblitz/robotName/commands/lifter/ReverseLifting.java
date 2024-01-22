package edu.greenblitz.robotName.commands.lifter;

import com.revrobotics.CANSparkMax;

public class ReverseLifting extends LifterCommand{
    @Override
    public void initialize() {
        lifter.resetEncoderTo(0);
        lifter.setIdleMode(CANSparkMax.IdleMode.kCoast);
    }

    @Override
    public void execute() {

    }

    @Override
    public boolean isFinished() {
        return lifter.isMotorInPosition(0);
    }

    @Override
    public void end(boolean interrupted) {
        lifter.setIdleMode(CANSparkMax.IdleMode.kBrake);
    }
}
