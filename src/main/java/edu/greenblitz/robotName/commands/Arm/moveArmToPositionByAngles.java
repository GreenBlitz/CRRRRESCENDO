package edu.greenblitz.robotName.commands.Arm;

import edu.wpi.first.math.geometry.Rotation2d;

public class moveArmToPositionByAngles extends ArmCommand {

    private Rotation2d elbowAngle;
    private Rotation2d pivotAngle;
    public moveArmToPositionByAngles(double elbowGoalAngle, double pivotGoalAngle) {
        super();
        elbowAngle = new Rotation2d(elbowGoalAngle);
        pivotAngle = new Rotation2d(pivotGoalAngle);
    }

    @Override
    public void initialize() {
        arm.moveArmBy2Angles(elbowAngle,pivotAngle);
    }

}
