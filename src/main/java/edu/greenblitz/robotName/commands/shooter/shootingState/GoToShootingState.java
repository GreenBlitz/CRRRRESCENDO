package edu.greenblitz.robotName.commands.shooter.shootingState;

import edu.greenblitz.robotName.commands.shooter.MoveShooterToAngle;
import edu.greenblitz.robotName.commands.shooter.flyWheel.RunFlyWheelByVelocityConstant;
import edu.greenblitz.robotName.commands.swerve.MoveRobotToShootingPosition;
import edu.greenblitz.robotName.shootingStateService.ShootingStateCalculations;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class GoToShootingState extends ParallelCommandGroup {

    public GoToShootingState() {
        super(
                new MoveRobotToShootingPosition(),
                new MoveShooterToAngle(ShootingStateCalculations::getTargetShooterAngle),
                new RunFlyWheelByVelocityConstant()
        );
    }
}