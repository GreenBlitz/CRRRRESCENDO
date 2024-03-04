package edu.greenblitz.robotName.commands.lifter;

public class SolenoidHold extends LifterCommand{
    @Override
    public void execute() {
        lifter.holdSolenoid();
    }
    @Override
    public void end(boolean interrupted) {
        lifter.openSolenoid();
    }
}
