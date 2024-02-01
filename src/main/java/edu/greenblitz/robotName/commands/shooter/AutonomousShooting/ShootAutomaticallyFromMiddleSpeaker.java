package edu.greenblitz.robotName.commands.shooter.AutonomousShooting;

import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.PivotConstants;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ShootAutomaticallyFromMiddleSpeaker extends SequentialCommandGroup {

    super(
            new MovePivotToAngle(PivotConstants.AutoNomousShooting.MIDDLE)
            );

}
