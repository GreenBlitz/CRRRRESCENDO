package edu.greenblitz.robotName.commands.Arm;

import edu.wpi.first.math.geometry.Rotation2d;

public class moveArmToPositionByAngles extends ArmCommand {

    private final double elbowAngle;
    private final double pivotAngle;

    public moveArmToPositionByAngles(double elbowGoalAngle, double pivotGoalAngle) {
        super();
        elbowAngle = elbowGoalAngle;
        pivotAngle = pivotGoalAngle;
    }

    @Override
    public void initialize() {
        arm.moveArmBy2Angles(elbowAngle,pivotAngle);
    }

}
