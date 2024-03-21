package edu.greenblitz.robotName.commands;

import edu.greenblitz.robotName.commands.shooter.funnel.FunnelCommand;
import edu.greenblitz.robotName.shootingStateService.ShootingStateCalculations;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheel;
import edu.greenblitz.robotName.subsystems.shooter.funnel.Funnel;
import edu.greenblitz.robotName.subsystems.shooter.pivot.Pivot;
import edu.greenblitz.robotName.subsystems.swerve.chassis.SwerveChassis;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jdk.swing.interop.SwingInterOpUtils;
import org.littletonrobotics.junction.Logger;

public class ShootOnReady extends FunnelCommand {

    private boolean isSwerveAngleCorrect;

    private boolean isPivotAngleCorrect;

    private boolean isRobotStanding;

    private boolean isFlyWheelAtVelocity;

    private final Timer TIMER = new Timer();

    private boolean hasTimerStarted = false;

    @Override
    public void execute() {
        isSwerveAngleCorrect = SwerveChassis.getInstance().isAtAngle(ShootingStateCalculations.getTargetRobotAngle());
        isPivotAngleCorrect = Pivot.getInstance().isAtAngle(ShootingStateCalculations.getTargetShooterAngle());
        isRobotStanding = SwerveChassis.getInstance().isRobotStanding();
        isFlyWheelAtVelocity = FlyWheel.getInstance().getPreparedToShoot();
        Logger.recordOutput("Shootingg/isFlyWheelAtVelocity", isFlyWheelAtVelocity);
        Logger.recordOutput("Shootingg/isRobotStanding", isRobotStanding);
        Logger.recordOutput("Shootingg/isSwerveAngleCorrect", isSwerveAngleCorrect);
        Logger.recordOutput("Shootingg/isPivotAngleCorrect", isPivotAngleCorrect);
        if (isRobotStanding && isPivotAngleCorrect && isSwerveAngleCorrect && isFlyWheelAtVelocity) {
            if (!hasTimerStarted){
                hasTimerStarted = true;
                TIMER.restart();
            }
            funnel.setPower(0.8);
        }
    }

    @Override
    public boolean isFinished() {
        return !funnel.isObjectIn() && TIMER.hasElapsed(1.5);
    }

}
