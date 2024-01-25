package edu.greenblitz.robotName.commands.shooter.pivot.movePivotToAngle;

import edu.greenblitz.robotName.commands.shooter.pivot.PivotCommand;

public class SimulationMovePivotToAngle extends PivotCommand {

    private double goalAngle;

    public SimulationMovePivotToAngle(double goalAngle){
        super();
        this.goalAngle = goalAngle;
    }

    @Override
    public void execute() {
        pivot.moveToAngle(goalAngle);
    }

    @Override
    public boolean isFinished() {
        return pivot.isAtAngle(goalAngle);
    }
}