package edu.greenblitz.robotName.commands.arm.elbow;

import edu.greenblitz.robotName.Robot;
import edu.wpi.first.math.geometry.Rotation2d;

public class MoveElbowToAngle extends ElbowCommand {

    private Rotation2d targetAngle;

    public MoveElbowToAngle(Rotation2d targetAngle) {
        this.targetAngle = targetAngle;
    }

    @Override
    public void initialize() {
        elbow.moveToAngle(targetAngle);
    }

    @Override
    public void execute() {
        if (Robot.isSimulation()) {
            elbow.moveToAngle(targetAngle);
        }
    }

    @Override
    public boolean isFinished() {
        return elbow.isAtAngle(targetAngle);
    }

}
