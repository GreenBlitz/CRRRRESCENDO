package edu.greenblitz.robotName.commands.arm.roller;

public class RollClockwise extends RollerCommand{

    @Override
    public void execute() {
        roller.rollClockwise();
    }

}