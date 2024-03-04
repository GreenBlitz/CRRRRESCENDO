package edu.greenblitz.robotName.commands.lifter;

public class SolenoidClose extends LifterCommand{
    @Override
    public void execute() {
        lifter.closeSolenoid();
    }

    @Override
    public void end(boolean interrupted) {
        lifter.openSolenoid();
    }
}
