package edu.greenblitz.robotName.commands.arm.wrist;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.subsystems.arm.wrist.WristConstants;
import edu.wpi.first.math.geometry.Rotation2d;

public class MoveWristToAngle extends WristCommand {

    private Rotation2d targetAngle;

    public MoveWristToAngle(Rotation2d targetAngle) {
        this.targetAngle = targetAngle;
    }

    public MoveWristToAngle(WristConstants.PresetPositions targetAngle) {
        this(targetAngle.ANGLE);
    }

    @Override
    public void initialize() {
        wrist.moveToAngle(targetAngle);
    }

    @Override
    public void execute() {
        if (Robot.isSimulation()) {
            wrist.moveToAngle(targetAngle);
        }
    }

    @Override
    public boolean isFinished() {
        return wrist.isAtAngle(targetAngle);
    }
}