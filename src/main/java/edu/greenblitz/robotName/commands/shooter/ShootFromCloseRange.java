package edu.greenblitz.robotName.commands.shooter;

import edu.greenblitz.robotName.commands.shooter.shootingState.GoToShootingStateAndShoot;
import edu.greenblitz.robotName.commands.swerve.RotateToAngle;
import edu.greenblitz.robotName.shootingStateService.ShootingPositionConstants;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.PivotConstants;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.ChassisConstants;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ShootFromCloseRange extends GoToShootingStateAndShoot {
    public ShootFromCloseRange(){
        super(
                ShootingPositionConstants.OPTIMAL_SHOOTING_ZONE,
                ShootingPositionConstants.CLOSE_SHOOTING_ZONE
        );
    }
}
