package edu.greenblitz.robotName.commands.arm.elbow.MoveElbowToAngle;

import edu.greenblitz.robotName.commands.arm.elbow.ElbowCommand;

public class SimulationMoveElbowToAngle extends ElbowCommand {

    private double targetAngle;

    public SimulationMoveElbowToAngle(double targetAngle){
        super();
        this.targetAngle = targetAngle;
    }

    @Override
    public void execute() {
        elbow.moveToAngle(targetAngle);
    }

    @Override
    public boolean isFinished() {
        return elbow.isAtAngle(targetAngle);
    }
}
