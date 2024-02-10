package edu.greenblitz.robotName.commands.shooter.pivot;

import edu.greenblitz.robotName.commands.arm.MoveElbowAndWrist;
import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.wrist.WristConstants;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import java.util.function.Supplier;


public class MovePivotIfInDangerZone extends SequentialCommandGroup {

    public MovePivotIfInDangerZone(Rotation2d targetAngle) {
        super(
                new MoveElbowAndWrist(
                        ElbowConstants.PresetPositions.SAFE.ANGLE,
                        WristConstants.PresetPositions.TRANSFER.ANGLE
                ),
                new MovePivotToAngle(targetAngle)
        );
    }

    public MovePivotIfInDangerZone(Supplier<Rotation2d> targetAngle) {
        super(
                new MoveElbowAndWrist(
                        ElbowConstants.PresetPositions.SAFE.ANGLE,
                        WristConstants.PresetPositions.TRANSFER.ANGLE
                ),
                new MovePivotToAngle(targetAngle)
        );
    }
}
