package edu.greenblitz.robotName.commands.arm.roller;

public class RollerDefaultCommand extends RollerCommand {

    @Override
    public void execute() {
        roller.setPower(-0.005);
    }
}
