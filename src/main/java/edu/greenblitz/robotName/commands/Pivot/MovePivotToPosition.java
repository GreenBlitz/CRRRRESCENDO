package edu.greenblitz.robotName.commands.Pivot;

public class MovePivotToPosition extends PivotCommand {

    private final double pivotAngle;
    public MovePivotToPosition(double goalAngle) {
        super();
        pivotAngle = goalAngle;
    }

    @Override
    public void initialize() {
        pivot.setGoalAngle(pivotAngle);
    }

}
