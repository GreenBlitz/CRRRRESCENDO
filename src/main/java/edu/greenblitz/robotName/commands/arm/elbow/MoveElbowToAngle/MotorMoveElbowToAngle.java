package edu.greenblitz.robotName.commands.arm.elbow.MoveElbowToAngle;

import edu.greenblitz.robotName.commands.arm.elbow.ElbowCommand;

public class MotorMoveElbowToAngle extends ElbowCommand {

    private double targetAngle;

    public MotorMoveElbowToAngle(double targetAngle){
        super();
        this.targetAngle = targetAngle;
    }

    @Override
    public void initialize() {
        elbow.moveToAngle(targetAngle);
    }

    @Override
    public boolean isFinished() {
        return elbow.isAtAngle(targetAngle);
    }
}
