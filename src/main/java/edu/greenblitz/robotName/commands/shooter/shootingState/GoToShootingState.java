package edu.greenblitz.robotName.commands.shooter.shootingState;

import edu.greenblitz.robotName.commands.shooter.RunFlyWheel;
import edu.greenblitz.robotName.commands.shooter.flyWheel.RunFlyWheelByShootingPower;
import edu.greenblitz.robotName.commands.shooter.ShootByShootingPower;
import edu.greenblitz.robotName.shootingStateService.ShootingState;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class GoToShootingState extends ParallelCommandGroup {

    public GoToShootingState() {
        super(
                new MoveRobotToShootingPosition(),
                new GoToShooterAngle(),
                new RunFlyWheel()
        );
    }
}