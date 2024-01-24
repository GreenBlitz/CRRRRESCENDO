package edu.greenblitz.robotName.commands.arm.wrist.MoveWristToAngle;

import edu.greenblitz.robotName.commands.arm.wrist.WristCommand;

public class MotorMoveWristToAngle extends WristCommand {

    double goalAngle;

    public MotorMoveWristToAngle(double goalAngle){
        super();
        this.goalAngle = goalAngle;
    }

    @Override
    public void initialize() {
        wrist.moveToAngle(goalAngle);
    }

    @Override
    public boolean isFinished() {
        return wrist.isAtAngle(goalAngle);
    }
}
