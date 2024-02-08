package edu.greenblitz.robotName.commands.arm.roller;

public class RollOut extends RollerCommand{
    @Override
    public void initialize() {
        roller.rollCounterClockWise();
    }

}