package edu.greenblitz.robotName.commands;

import edu.greenblitz.robotName.commands.shooter.funnel.FunnelCommand;
import edu.greenblitz.robotName.shootingStateService.ShootingStateCalculations;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheel;
import edu.greenblitz.robotName.subsystems.shooter.funnel.Funnel;
import edu.greenblitz.robotName.subsystems.shooter.pivot.Pivot;
import edu.greenblitz.robotName.subsystems.swerve.chassis.SwerveChassis;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShootOnReady extends FunnelCommand {

    private boolean isSwerveAngleCorrect;

    private boolean isPivotAngleCorrect;

    private boolean isRobotStanding;

    private boolean isFlyWheelAtVelocity;


    @Override
    public void execute() {
        isSwerveAngleCorrect = SwerveChassis.getInstance().isAtAngle(ShootingStateCalculations.getTargetRobotAngle());
        isPivotAngleCorrect = Pivot.getInstance().isAtAngle(ShootingStateCalculations.getTargetShooterAngle());
        isRobotStanding = SwerveChassis.getInstance().isRobotStanding();
        isFlyWheelAtVelocity = FlyWheel.getInstance().getPreparedToShoot();
        SmartDashboard.putBoolean("flywhhel", isFlyWheelAtVelocity);
        SmartDashboard.putBoolean("robotStantd", isRobotStanding);
        SmartDashboard.putBoolean("angle", isSwerveAngleCorrect);
        SmartDashboard.putBoolean("pivot", isPivotAngleCorrect);
        if (isRobotStanding && isPivotAngleCorrect && isSwerveAngleCorrect && isFlyWheelAtVelocity) {
            funnel.setPower(0.8);
        }
    }

    @Override
    public boolean isFinished() {
        return !funnel.isObjectIn();
    }

}
