package edu.greenblitz.robotName.commands.Climb.lifter;

public class ExtendLifter extends LifterCommand {

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        lifter.setPower(0.3);
    }

    @Override
    public boolean isFinished() {
//        return lifter.isAtPosition(LifterConstants.LIFTER_EXTENDED_POSITION);
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        lifter.stopMotor();
    }
}
