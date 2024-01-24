package edu.greenblitz.robotName.commands.arm.wrist.MoveWristToAngle;

import edu.greenblitz.robotName.commands.arm.wrist.WristCommand;

public class MotorMoveWristToAngle extends WristCommand {

    private double targetAngle;

    public MotorMoveWristToAngle(double targetAngle){
        super();
        this.targetAngle = targetAngle;
    }

    @Override
    public void initialize() {
        wrist.moveToAngle(targetAngle);
    }

    @Override
    public boolean isFinished() {
        return wrist.isAtAngle(targetAngle);
    }
}
