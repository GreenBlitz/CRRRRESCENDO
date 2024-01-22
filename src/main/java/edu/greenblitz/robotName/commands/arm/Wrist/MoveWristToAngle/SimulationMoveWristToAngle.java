package edu.greenblitz.robotName.commands.arm.Wrist.MoveWristToAngle;

import edu.greenblitz.robotName.commands.arm.Wrist.WristCommand;

public class SimulationMoveWristToAngle extends WristCommand {

    double goalAngle;


    public SimulationMoveWristToAngle(double goalAngle){
        super();
        this.goalAngle = goalAngle;
    }

    @Override
    public void execute() {
        wrist.moveToAngle(goalAngle);
    }

    @Override
    public boolean isFinished() {
        return wrist.isAtAngle(goalAngle);
    }
}
