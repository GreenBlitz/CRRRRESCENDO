package edu.greenblitz.robotName.commands.systemCheck;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.subsystems.shooter.pivot.Pivot;
import edu.wpi.first.math.geometry.Rotation2d;

import java.util.function.Supplier;

public class CheckPivotToAngle extends SystemCheckCommand {

    protected Pivot pivot;

    private Supplier<Rotation2d> targetAngle;

    private Rotation2d suppliedTargetAngle;

    public CheckPivotToAngle(Supplier<Rotation2d> targetAngle) {
        pivot = Pivot.getInstance();
        require(pivot);
        this.targetAngle = targetAngle;
    }

    public CheckPivotToAngle(Rotation2d targetAngle) {
        pivot = Pivot.getInstance();
        require(pivot);
        this.targetAngle = () -> targetAngle;
    }

    @Override
    public void initialize() {
        suppliedTargetAngle = targetAngle.get();
        pivot.moveToAngle(suppliedTargetAngle);
    }

    @Override
    public void execute() {
        if (Robot.isSimulation()) {
            pivot.moveToAngle(suppliedTargetAngle);
        }
    }

    @Override
    public boolean isFinished() {
        return pivot.isAtAngle(suppliedTargetAngle);
    }

    public void end(boolean interrupted) {
        super.end(interrupted);
        pivot.setCurrentAngle();
        pivot.standInPlace();
    }
}
