package edu.greenblitz.robotName.commands.arm.roller;

public class RollCounterClockWise extends RollerCommand{
    @Override
    public void initialize() {
        roller.rollCounterClockWise();
    }

}