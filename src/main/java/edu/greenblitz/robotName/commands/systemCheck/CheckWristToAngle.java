package edu.greenblitz.robotName.commands.systemCheck;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.subsystems.arm.wrist.Wrist;
import edu.wpi.first.math.geometry.Rotation2d;

public class CheckWristToAngle extends SystemCheckCommand {
    protected Wrist wrist;

    private final Rotation2d targetAngle;

    public CheckWristToAngle(Rotation2d targetAngle) {
        wrist = Wrist.getInstance();
        require(wrist);
        this.targetAngle = targetAngle;
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

    public void end(boolean interrupted) {
        super.end(interrupted);
        wrist.setCurrentAngle();
        wrist.standInPlace();
    }
}
