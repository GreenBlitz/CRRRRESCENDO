package edu.greenblitz.robotName.commands.arm.wrist;

import edu.greenblitz.robotName.subsystems.arm.wrist.WristConstants;

public class WristDefaultCommand extends WristCommand {

    @Override
    public void initialize() {
        wrist.setCurrentAngle();
    }

    @Override
    public void execute() {
        wrist.standInPlace();
    }
}