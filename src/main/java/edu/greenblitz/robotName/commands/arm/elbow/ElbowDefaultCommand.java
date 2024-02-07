package edu.greenblitz.robotName.commands.arm.elbow;

import edu.greenblitz.robotName.Robot;

public class ElbowDefaultCommand extends ElbowCommand {

    @Override
    public void initialize() {
       elbow.standInPlaceForDefault();
    }

    @Override
    public void execute() {
        if (Robot.isSimulation()) {
            elbow.standInPlaceForDefault();
        }
    }
}
