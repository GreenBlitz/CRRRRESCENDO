package edu.greenblitz.robotName.commands.shooter;

import edu.greenblitz.robotName.commands.swerve.RotateToAngle;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;
import edu.greenblitz.robotName.subsystems.swerve.chassis.ChassisConstants;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ShootFromCloseRange extends SequentialCommandGroup {
    public ShootFromCloseRange(){
        super(
                new ParallelCommandGroup(
                        new RotateToAngle(ChassisConstants.CLOSE_RANGE_SHOOTING_ANGLE),
                        new MoveShooterToAngle(PivotConstants.CLOSE_RANGE_SHOOTING_ANGLE)
                ),
                new ShootFromInFunnel()
        );
    }
}
