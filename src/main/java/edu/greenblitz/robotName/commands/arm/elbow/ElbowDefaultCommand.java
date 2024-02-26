package edu.greenblitz.robotName.commands.arm.elbow;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.subsystems.arm.elbow.Elbow;
import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowConstants;
import edu.wpi.first.math.geometry.Rotation2d;

public class ElbowDefaultCommand extends ElbowCommand {

    @Override
    public void initialize() {
        elbow.setCurrentAngle(Elbow.getInstance().getAngle());
        elbow.standInPlace();
    }

    @Override
    public void execute() {
        if (Robot.isSimulation()) {
            elbow.standInPlace();
        }
    }
}