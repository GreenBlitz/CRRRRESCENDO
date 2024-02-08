package edu.greenblitz.robotName.commands.arm.roller;

public class RollClockWise extends RollerCommand{
    @Override
    public void initialize() {
        roller.rollClockWise();
    }

}