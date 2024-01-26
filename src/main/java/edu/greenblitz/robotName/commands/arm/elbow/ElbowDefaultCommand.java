package edu.greenblitz.robotName.commands.arm.elbow;

import edu.greenblitz.robotName.Robot;

public class ElbowDefaultCommand extends ElbowCommand {

    @Override
    public void execute() {
        if (!Robot.isSimulation()) {
            elbow.standInPlace();
        } else {
            elbow.setPower(0);
        }
    }
}
