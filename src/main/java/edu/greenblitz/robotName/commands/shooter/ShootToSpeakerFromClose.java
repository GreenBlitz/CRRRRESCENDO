package edu.greenblitz.robotName.commands.shooter;

import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ShootToSpeakerFromClose extends SequentialCommandGroup {

    public ShootToSpeakerFromClose() {
        super(
//                new MovePivotToAngle(PivotConstants.PresetPositions.CLOSE_SHOOTING.ANGLE),
                new ShootFromInFunnel()
        );
    }
}
