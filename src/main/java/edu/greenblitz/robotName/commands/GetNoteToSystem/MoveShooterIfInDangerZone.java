package edu.greenblitz.robotName.commands.GetNoteToSystem;

import edu.greenblitz.robotName.commands.arm.MoveElbowAndWrist;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.EndEffector.WristUtils.WristConstants;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;


public class MoveShooterIfInDangerZone extends SequentialCommandGroup {

    public MoveShooterIfInDangerZone(Rotation2d targetAngle) {
        super(
                new MoveElbowAndWrist(
                        ElbowConstants.PresetPositions.SAFE.ANGLE,
                        WristConstants.PresetPositions.TRANSFER.ANGLE
                ),
                new MovePivotToAngle(targetAngle)
        );
    }
}
