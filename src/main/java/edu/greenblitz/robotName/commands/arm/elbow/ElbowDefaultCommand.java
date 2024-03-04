package edu.greenblitz.robotName.commands.arm.elbow;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowConstants;

public class ElbowDefaultCommand extends ElbowCommand {

    @Override
    public void initialize() {
        elbow.setCurrentAngle();
    }

    @Override
    public void execute() {
        elbow.standInPlace();
    }
}