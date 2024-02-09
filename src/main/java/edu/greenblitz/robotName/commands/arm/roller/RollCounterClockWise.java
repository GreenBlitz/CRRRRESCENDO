package edu.greenblitz.robotName.commands.arm.roller;

public class RollCounterClockWise extends RollerCommand{

    @Override
    public void execute() {
        roller.rollCounterClockWise();
    }

}