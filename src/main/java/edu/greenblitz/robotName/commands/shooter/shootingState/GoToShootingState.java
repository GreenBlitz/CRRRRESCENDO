package edu.greenblitz.robotName.commands.shooter.shootingState;

import edu.greenblitz.robotName.commands.shooter.flyWheel.RunFlyWheel;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class GoToShootingState extends ParallelCommandGroup {

    public GoToShootingState() {
        super(
                new MoveRobotToShootingPosition(),
                new GoToShooterAngle(),
                new RunFlyWheel()
        );
    }
}