package edu.greenblitz.robotName.commands.shooter.shootingState;

import edu.greenblitz.robotName.commands.shooter.RunFlyWheel;
import edu.greenblitz.robotName.commands.shooter.ShootByPower;
import edu.greenblitz.robotName.shootingStateService.ShootingState;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class GoToShootingState extends GBCommand {

    @Override
    public void initialize() {
        new ParallelCommandGroup(
                new GoToRobotTargetPosition(),
                new GoToShooterAngle(),
                new RunFlyWheel()
        );
    }

    @Override
    public void end(boolean interrupted) {
        if (ShootingState.isReadyToShoot()) {
            new ShootByPower();
        }
    }
}
