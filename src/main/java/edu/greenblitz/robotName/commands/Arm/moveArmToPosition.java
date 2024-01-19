package edu.greenblitz.robotName.commands.Arm;

import edu.greenblitz.robotName.subsystems.Arm.Elbow.Elbow;
import edu.greenblitz.robotName.subsystems.Arm.Pivot.Pivot;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.math.util.Units;

public class moveArmToPosition extends GBCommand {
    private Elbow elbow;
    private Pivot pivot;
    private double goalAngleElbow;
    private double goalAnglePivot;
    public moveArmToPosition(double angleElbow,double anglePivot) {
        goalAngleElbow = angleElbow;
        goalAnglePivot = anglePivot;
        elbow = Elbow.getInstance();
        pivot = Pivot.getInstance();
        require(elbow);
        require(pivot);
    }

    @Override
    public void execute() {
        elbow.moveToAngle(goalAngleElbow);
        pivot.moveToAngle(goalAnglePivot);
    }

    @Override
    public boolean isFinished() {
        return elbow.isAtAngle(goalAngleElbow) && pivot.isAtAngle(goalAnglePivot);
    }
}
