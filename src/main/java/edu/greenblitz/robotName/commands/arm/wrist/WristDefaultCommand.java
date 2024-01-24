package edu.greenblitz.robotName.commands.arm.wrist;

public class WristDefaultCommand extends WristCommand{

    @Override
    public void execute() {
        wrist.standInPlace();
    }
}
