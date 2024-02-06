package edu.greenblitz.robotName.commands.arm.wrist;

import edu.greenblitz.robotName.Robot;

public class WristDefaultCommand extends WristCommand{

    @Override
    public void initialize() {
        wrist.standInPlace();
    }

    @Override
    public void execute() {
        if (Robot.isSimulation()) {
            wrist.standInPlace();
        }
    }
}
