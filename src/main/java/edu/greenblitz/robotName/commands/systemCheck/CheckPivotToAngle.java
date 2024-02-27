package edu.greenblitz.robotName.commands.systemCheck;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.subsystems.shooter.pivot.Pivot;
import edu.wpi.first.math.geometry.Rotation2d;

public class CheckPivotToAngle extends SystemCheckCommand {

    protected Pivot pivot;

    private Rotation2d targetAngle;

    public CheckPivotToAngle(Rotation2d targetAngle) {
        pivot = Pivot.getInstance();
        require(pivot);
        this.targetAngle = targetAngle;
    }

    @Override
    public void initialize() {
        pivot.moveToAngle(targetAngle);
    }

    @Override
    public void execute() {
        if (Robot.isSimulation()) {
            pivot.moveToAngle(targetAngle);
        }
    }

    @Override
    public boolean isFinished() {
        return pivot.isAtAngle(targetAngle);
    }

    public void end(boolean interrupted) {
        super.end(interrupted);
        pivot.setCurrentAngle();
        pivot.standInPlace();
    }
}
