package edu.greenblitz.robotName.commands.lifter;

public class SolenoidOpen extends LifterCommand{
    @Override
    public void initialize() {
        lifter.openSolenoid();
    }
}
