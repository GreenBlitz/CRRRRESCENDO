package edu.greenblitz.robotName.commands.shooter;

import edu.greenblitz.robotName.commands.shooter.shootingState.GoToShootingStateAndShoot;
import edu.greenblitz.robotName.commands.swerve.RotateToAngle;
import edu.greenblitz.robotName.shootingStateService.ShootingPositionConstants;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.PivotConstants;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.ChassisConstants;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;
import edu.greenblitz.robotName.subsystems.swerve.chassis.ChassisConstants;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ShootFromCloseRange extends GoToShootingStateAndShoot {
    public ShootFromCloseRange(){
public class ShootFromCloseRange extends SequentialCommandGroup {

    public ShootFromCloseRange() {
        super(
                ShootingPositionConstants.CLOSE_SHOOTING_ZONE
        );
    }
}