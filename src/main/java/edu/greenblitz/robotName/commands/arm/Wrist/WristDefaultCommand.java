package edu.greenblitz.robotName.commands.arm.Wrist;

public class WristDefaultCommand extends WristCommand{

    @Override
    public void execute() {
        wrist.standInPlace();
    }
}
