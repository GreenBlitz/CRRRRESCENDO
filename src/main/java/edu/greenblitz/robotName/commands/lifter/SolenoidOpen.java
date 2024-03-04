package edu.greenblitz.robotName.commands.lifter;

public class SolenoidOpen extends LifterCommand{
    @Override
    public void execute() {
        lifter.openSolenoid();
    }
    @Override
    public void end(boolean interrupted) {
        lifter.openSolenoid();
    }
}
