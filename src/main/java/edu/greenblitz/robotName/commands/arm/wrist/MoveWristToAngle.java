package edu.greenblitz.robotName.commands.arm.wrist;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.commands.arm.wrist.WristCommand;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;

public class MoveWristToAngle extends WristCommand {

    private double targetAngle;

    public MoveWristToAngle(double targetAngle, boolean isSimulation) {
        this.targetAngle = targetAngle;
    }

    @Override
    public void initialize() {
        wrist.moveToAngle(targetAngle);
    }

    @Override
    public void execute() {
        if (Robot.isSimulation())
            wrist.moveToAngle(targetAngle);
    }

    public boolean isFinished() {
        return wrist.isAtAngle(targetAngle);
    }

}
