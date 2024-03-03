package edu.greenblitz.robotName.commands.lifter;

public class SolenoidHold extends LifterCommand{
    @Override
    public void initialize() {
        lifter.holdSolenoid();
    }
}
