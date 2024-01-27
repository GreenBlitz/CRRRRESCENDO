package edu.greenblitz.robotName.commands.GetNoteToSystem;

import edu.greenblitz.robotName.commands.arm.MoveArmBy2Angles;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.subsystems.arm.Elbow;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.EndEffector.WristUtils.WristConstants;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.PivotConstants;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class MoveShooterToAngle extends ParallelCommandGroup {

    public MoveShooterToAngle(Rotation2d targetAngle) {
        super(
                new ConditionalCommand(new MoveArmBy2Angles(
                        ElbowConstants.ImportantPlaces.SAFE.ANGLE,
                        WristConstants.ImportantPlaces.TRANSFER.ANGLE
                )
                        ,new GBCommand() {},
                        () -> Elbow.getInstance().isInDangerZone()
                ),
                new SequentialCommandGroup(
                        new WaitCommand(PivotConstants.NO_COLLISION_DELAY_SECONDS),
                        new MovePivotToAngle(targetAngle)
                        )
        );
    }

}
