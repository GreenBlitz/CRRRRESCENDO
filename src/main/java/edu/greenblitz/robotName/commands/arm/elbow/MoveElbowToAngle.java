package edu.greenblitz.robotName.commands.arm.elbow;

import edu.greenblitz.robotName.commands.arm.elbow.ElbowCommand;

public class MoveElbowToAngle extends ElbowCommand {

    private double targetAngle;

    private boolean isSimulation;

    public MoveElbowToAngle(double targetAngle, boolean isSimulation) {
        this.isSimulation = isSimulation;
        this.targetAngle = targetAngle;
    }

    @Override
    public void initialize() {
        elbow.moveToAngle(targetAngle);
    }

    @Override
    public void execute() {
        if (isSimulation)
            elbow.moveToAngle(targetAngle);
    }

    public boolean isFinished() {
        return elbow.isAtAngle(targetAngle);
    }
}
