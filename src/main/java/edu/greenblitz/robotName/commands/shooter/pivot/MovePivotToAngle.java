package edu.greenblitz.robotName.commands.shooter.pivot;

import edu.greenblitz.robotName.Robot;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.function.Supplier;

public class MovePivotToAngle extends PivotCommand {

    private Supplier<Rotation2d> targetAngle;

    private Rotation2d supplliedTargetAngle;
    
    private boolean shouldRunForever;

    public MovePivotToAngle(Rotation2d targetAngle, boolean shouldRunForever) {
        this.shouldRunForever = shouldRunForever;
        this.targetAngle = () -> targetAngle;
    }

    public MovePivotToAngle(Supplier<Rotation2d> targetAngle, boolean shouldRunForever) {
        this.shouldRunForever = shouldRunForever;
        this.targetAngle = targetAngle;
    }

    @Override
    public void initialize() {
        supplliedTargetAngle = targetAngle.get();
        pivot.moveToAngle(supplliedTargetAngle);
    }

    @Override
    public void execute() {
        if (Robot.isSimulation()) {
            pivot.moveToAngle(supplliedTargetAngle);
        }
    }

    @Override
    public boolean isFinished() {
        if (!shouldRunForever) {
            return pivot.isAtAngle(supplliedTargetAngle);
        }
        return false;
    }
}