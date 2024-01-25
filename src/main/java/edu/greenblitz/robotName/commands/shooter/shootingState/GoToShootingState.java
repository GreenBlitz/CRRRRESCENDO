package edu.greenblitz.robotName.commands.shooter.shootingState;

import edu.greenblitz.robotName.commands.shooter.ShootByPower;
import edu.greenblitz.robotName.shootingStateService.ShootingState;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelConstants;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.Pivot;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

import java.util.Objects;

public class GoToShootingState extends GBCommand {

    @Override
    public void initialize() {
        new ParallelCommandGroup(
                new GoToRobotTargetPosition(),
                new GoToShooterAngle(),
                new ShootByPower(FlyWheelConstants.SHOOTING_POWER)
        );
    }

    @Override
    public void end(boolean interrupted) {
        if (ShootingState.isReadyToShoot()) {
            new ShootByPower(FlyWheelConstants.SHOOTING_POWER);
        }
    }
}
