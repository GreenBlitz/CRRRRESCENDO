package edu.greenblitz.robotName.commands.shooterArmCoordination.transfer;

import edu.greenblitz.robotName.commands.arm.MoveArmBy2Angles;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.EndEffector.WristUtils.WristConstants;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.PivotConstants;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class MoveShooterArmToTransferPosition extends ParallelCommandGroup {
    public MoveShooterArmToTransferPosition() {
        super(
                new MoveArmBy2Angles(ElbowConstants.ImportantPlaces.TRANSFER.angle, WristConstants.ImportantPlaces.TRANSFER.angle),
                new MovePivotToAngle(PivotConstants.ImportantPlaces.TRANSFER.angle)
        );
    }
}
