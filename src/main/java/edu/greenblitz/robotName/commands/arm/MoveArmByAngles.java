package edu.greenblitz.robotName.commands.arm;

public class MoveArmByAngles extends ArmCommand {


    public MoveArmByAngles(double elbowGoalAngle, double wristGoalAngle) {
        super(elbowGoalAngle, wristGoalAngle);
    }

    @Override
    public void initialize() {
        arm.moveToAngles(elbowAngle, wristAngle);
    }
}
