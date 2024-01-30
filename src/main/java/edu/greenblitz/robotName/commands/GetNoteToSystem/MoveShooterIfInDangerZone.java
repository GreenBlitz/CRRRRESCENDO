package edu.greenblitz.robotName.commands.GetNoteToSystem;

import edu.greenblitz.robotName.commands.arm.MoveElbowAndWrist;
import edu.greenblitz.robotName.commands.arm.WaitUntilArmIsSafe;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.EndEffector.WristUtils.WristConstants;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.PivotConstants;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;


public class MoveShooterIfInDangerZone extends ParallelCommandGroup {

    public MoveShooterIfInDangerZone(Rotation2d targetAngle) {
        super(
                new MoveElbowAndWrist(
                        ElbowConstants.PresetPositions.SAFE.ANGLE,
                        WristConstants.PresetPositions.TRANSFER.ANGLE
                ),
                new SequentialCommandGroup(
                        new WaitUntilArmIsSafe(),
                        new MovePivotToAngle(targetAngle)
                )
        );
    }
}
