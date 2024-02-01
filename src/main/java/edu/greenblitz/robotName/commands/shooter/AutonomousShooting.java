package edu.greenblitz.robotName.commands.shooter;

import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.Pivot;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutonomousShooting extends SequentialCommandGroup {
    public AutonomousShooting(){
        addCommands(
                new MovePivotToAngle(Pivot.getInstance().getAutonomousShootingPosition()),
                new Shoot()
        );
    }
}