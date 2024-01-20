package edu.greenblitz.robotName.commands.Pivot;

public class movePivotToPosition extends PivotCommand {

    private final double pivotAngle;
    public movePivotToPosition(double goalAngle) {
        super();
        pivotAngle = goalAngle;
    }

    @Override
    public void initialize() {
        pivot.setGoalAngle(pivotAngle);
    }

}
