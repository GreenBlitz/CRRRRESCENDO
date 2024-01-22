package edu.greenblitz.robotName.commands.arm;

import edu.greenblitz.robotName.subsystems.Arm.Arm;
import edu.greenblitz.robotName.utils.GBCommand;

public class ArmCommand extends GBCommand {

    protected Arm arm;

    protected double wristAngle;

    protected double elbowAngle;

    public ArmCommand(double elbowGoalAngle, double wristGoalAngle){
        arm = Arm.getInstance();
        require(arm);
        wristAngle = wristGoalAngle;
        elbowAngle = elbowGoalAngle;
    }

    @Override
    public boolean isFinished() {
        return arm.isAtGoalAngles();
    }
}
