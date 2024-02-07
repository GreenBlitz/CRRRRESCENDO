package edu.greenblitz.robotName.commands.arm.roller;

public class RollIn extends RollerCommand{
    @Override
    public void execute() {
        roller.rollIn();
    }

}
