package edu.greenblitz.robotName.commands.arm.roller;

public class RollIn extends RollerCommand{
    @Override
    public void initialize() {
        roller.rollIn();
    }

    @Override
    public void end(boolean interrupted) {
        roller.stop();
    }
}
