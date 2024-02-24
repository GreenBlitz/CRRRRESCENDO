package edu.greenblitz.robotName.commands.shooter;

import edu.greenblitz.robotName.commands.shooter.flyWheel.RunFlyWheelByVelocityConstant;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ShootToSpeakerFromClose extends SequentialCommandGroup {

    public ShootToSpeakerFromClose() {
        super(
                new RunFlyWheelByVelocityConstant().alongWith(new MovePivotToAngle(Rotation2d.fromDegrees(45))),
                new ShootFromInFunnel());
    }
}
