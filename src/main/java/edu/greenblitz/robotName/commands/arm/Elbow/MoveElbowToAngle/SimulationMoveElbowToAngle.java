package edu.greenblitz.robotName.commands.arm.Elbow.MoveElbowToAngle;

import edu.greenblitz.robotName.commands.arm.Elbow.ElbowCommand;

public class SimulationMoveElbowToAngle extends ElbowCommand {

    double goalAngle;

    public SimulationMoveElbowToAngle(double goalAngle){
        super();
        this.goalAngle = goalAngle;
    }

    @Override
    public void execute() {
        elbow.moveToAngle(goalAngle);
    }

    @Override
    public boolean isFinished() {
        return elbow.isAtAngle(goalAngle);
    }
}
