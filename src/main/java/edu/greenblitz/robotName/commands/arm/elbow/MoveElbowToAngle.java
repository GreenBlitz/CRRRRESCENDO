package edu.greenblitz.robotName.commands.arm.elbow;

import edu.greenblitz.robotName.Robot;

public class MoveElbowToAngle extends ElbowCommand {

    private double targetAngle;

    public MoveElbowToAngle(double targetAngle) {
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

    public boolean isFinished() {
        return elbow.isAtAngle(targetAngle);
    }
}
