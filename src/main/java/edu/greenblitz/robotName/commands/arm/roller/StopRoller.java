package edu.greenblitz.robotName.commands.arm.roller;

public class StopRoller extends RollerCommand{

    @Override
    public void initialize() {
        roller.stop();
    }
}
