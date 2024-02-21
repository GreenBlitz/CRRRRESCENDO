package edu.greenblitz.robotName.commands.arm.wrist;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.subsystems.arm.wrist.WristConstants;

public class WristDefaultCommand extends WristCommand {

    @Override
    public void initialize() {
        wrist.setCurrentAngle(WristConstants.PresetPositions.SAFE.ANGLE);
    }

    @Override
    public void execute() {
        if (Robot.isSimulation()) {
            wrist.standInPlace();
        }
    }
}