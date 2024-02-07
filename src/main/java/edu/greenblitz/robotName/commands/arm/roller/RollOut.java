package edu.greenblitz.robotName.commands.arm.roller;

public class RollOut extends RollerCommand{
    @Override
    public void initialize() {
        roller.rollOut();
    }

    @Override
    public void end(boolean interrupted) {
        roller.stop();
    }
}
