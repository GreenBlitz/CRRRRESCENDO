package edu.greenblitz.robotName.commands.arm.wrist;

import edu.greenblitz.robotName.Robot;

public class WristDefaultCommand extends WristCommand{

    @Override
    public void execute() {
        if (Robot.isSimulation()) {
            wrist.setPower(0);
        }
        else{
            wrist.standInPlace();
        }
    }
}
