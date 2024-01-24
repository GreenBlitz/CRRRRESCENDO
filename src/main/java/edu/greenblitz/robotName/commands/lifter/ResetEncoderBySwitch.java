package edu.greenblitz.robotName.commands.lifter;

import com.revrobotics.CANSparkMax;

public class ResetEncoderBySwitch extends LifterCommand{
    @Override
    public void initialize() {
        lifter.setIdleMode(CANSparkMax.IdleMode.kCoast);
    }
    @Override
    public void execute() {

    }

    @Override
    public boolean isFinished() {
        return lifter.isSwitchPressed();
    }
    @Override
    public void end(boolean interrupted) {
        lifter.resetEncoder(0);
        lifter.setIdleMode(CANSparkMax.IdleMode.kBrake);
    }
}
