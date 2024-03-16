package edu.greenblitz.robotName.commands;

import edu.greenblitz.robotName.commands.shooter.funnel.FunnelCommand;
import edu.greenblitz.robotName.shootingStateService.ShootingStateCalculations;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheel;
import edu.greenblitz.robotName.subsystems.shooter.funnel.Funnel;
import edu.greenblitz.robotName.subsystems.shooter.pivot.Pivot;
import edu.greenblitz.robotName.subsystems.swerve.chassis.SwerveChassis;

public class ShootOnReady extends FunnelCommand {

    private boolean isSwerveAngleCorrect;

    private boolean isPivotAngleCorrect;

    private boolean isRobotStanding;

    private boolean isFlyWheelAtVelocity;

    private boolean isNoteInFunnel;


    @Override
    public void execute() {
        isSwerveAngleCorrect = SwerveChassis.getInstance().isAtAngle(ShootingStateCalculations.getTargetRobotAngle());
        isPivotAngleCorrect = Pivot.getInstance().isAtAngle(ShootingStateCalculations.getTargetShooterAngle());
        isRobotStanding = SwerveChassis.getInstance().isRobotStanding();
        isFlyWheelAtVelocity = FlyWheel.getInstance().getPreparedToShoot();
        isNoteInFunnel = Funnel.getInstance().isObjectIn();
        if (isRobotStanding && isPivotAngleCorrect && isSwerveAngleCorrect && isFlyWheelAtVelocity && isNoteInFunnel) {
            funnel.setPower(0.8);
        }
    }

    @Override
    public boolean isFinished() {
        return !funnel.isObjectIn();
    }

}
