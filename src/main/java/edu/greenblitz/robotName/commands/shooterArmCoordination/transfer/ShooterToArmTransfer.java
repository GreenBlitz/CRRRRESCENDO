package edu.greenblitz.robotName.commands.shooterArmCoordination.transfer;

import edu.greenblitz.robotName.commands.arm.MoveArmBy2Angles;
import edu.greenblitz.robotName.commands.shooter.funnel.RollFunnelOut;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.EndEffector.WristUtils.WristConstants;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.PivotConstants;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ShooterToArmTransfer extends SequentialCommandGroup {
    public ShooterToArmTransfer(boolean isShooterToArm) {
        super(
                new MoveArmBy2Angles(ElbowConstants.TRANSFER_ANGLE, WristConstants.TRANSFER_ANGLE),
                new MovePivotToAngle(PivotConstants.TRANSFER_ANGLE)
                //new FunnelToRoller(isShooterToArm)
        );
    }
}
