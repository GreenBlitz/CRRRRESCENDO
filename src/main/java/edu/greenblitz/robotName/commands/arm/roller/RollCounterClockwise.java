package edu.greenblitz.robotName.commands.arm.roller;

public class RollCounterClockwise extends RollerCommand{

    @Override
    public void execute() {
        roller.rollCounterClockwise();
    }

}