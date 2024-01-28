package edu.greenblitz.robotName.commands.lifter;

import edu.greenblitz.robotName.subsystems.Lifter.LifterConstants;

public class ReverseLifting extends LifterCommand {
    @Override
    public void initialize() {
        lifter.setPosition(LifterConstants.LIFTER_RETRACTED_POSITION);
    }

    @Override
    public void execute() {
        lifter.goToPosition();
    }

    @Override
    public boolean isFinished() {
        return lifter.isMotorAtPosition();
    }

    @Override
    public void end(boolean interrupted) {
        lifter.stopMotor();
        new ResetEncoderBySwitch();
    }
}
