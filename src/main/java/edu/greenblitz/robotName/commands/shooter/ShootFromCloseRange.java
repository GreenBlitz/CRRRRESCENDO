package edu.greenblitz.robotName.commands.shooter;

import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.commands.swerve.RotateToAngle;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.PivotConstants;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.ChassisConstants;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ShootFromCloseRange extends SequentialCommandGroup {
    public ShootFromCloseRange(){
        super(
                new RotateToAngle(ChassisConstants.CLOSE_RANGE_SHOOTING_ANGLE),
                new MoveShooterToAngle(PivotConstants.CLOSE_RANGE_SHOOTING_ANGLE),
                new DefaultShooting()
        );
    }
}
