package edu.greenblitz.robotName.commands.arm.Elbow.MoveElbowToAngle;

import edu.greenblitz.robotName.commands.arm.Elbow.ElbowCommand;

public class MotorMoveElbowToAngle extends ElbowCommand {

    private double goalAngle;

    public MotorMoveElbowToAngle(double goalAngle){
        super();
        this.goalAngle = goalAngle;
    }

    @Override
    public void initialize() {
        elbow.moveToAngle(goalAngle);
    }

    @Override
    public boolean isFinished() {
        return elbow.isAtAngle(goalAngle);
    }
}
