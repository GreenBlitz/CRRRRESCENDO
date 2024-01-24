package edu.greenblitz.robotName.commands.arm.wrist.MoveWristToAngle;

import edu.greenblitz.robotName.commands.arm.wrist.WristCommand;

public class SimulationMoveWristToAngle extends WristCommand {

    private double targetAngle;

    public SimulationMoveWristToAngle(double targetAngle){
        super();
        this.targetAngle = targetAngle;
    }

    @Override
    public void execute() {
        wrist.moveToAngle(targetAngle);
    }

    @Override
    public boolean isFinished() {
        return wrist.isAtAngle(targetAngle);
    }
}
