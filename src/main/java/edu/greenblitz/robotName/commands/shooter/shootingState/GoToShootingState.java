package edu.greenblitz.robotName.commands.shooter.shootingState;

import edu.greenblitz.robotName.commands.shooter.RunFlyWheel;
import edu.greenblitz.robotName.commands.shooter.flyWheel.RunFlyWheelByShootingPower;
import edu.greenblitz.robotName.commands.shooter.ShootByShootingPower;
import edu.greenblitz.robotName.shootingStateService.ShootingState;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class GoToShootingState extends SequentialCommandGroup {

    public GoToShootingState() {
        super(
                new ParallelCommandGroup(
                        new MoveRobotToShootingPosition(),
                        new GoToShooterAngle(),
                        new RunFlyWheel()
                ),
                new ConditionalCommand(new RunFlyWheelByShootingPower(), new GBCommand() {}, ShootingState::isReadyToShoot)
        );
    }
}