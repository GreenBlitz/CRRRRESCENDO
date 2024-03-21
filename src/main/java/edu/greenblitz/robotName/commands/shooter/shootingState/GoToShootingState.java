package edu.greenblitz.robotName.commands.shooter.shootingState;

import edu.greenblitz.robotName.commands.shooter.flyWheel.RunFlyWheelByVelocityConstant;
import edu.greenblitz.robotName.commands.swerve.moveTo.MoveRobotToShootingPosition;
import edu.greenblitz.robotName.utils.shootingCalculations.ShootingZone;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class GoToShootingState extends ParallelCommandGroup {

    public GoToShootingState(ShootingZone zone) {
        super(
                new MoveRobotToShootingPosition(zone),
                new MoveShooterToShootingAngle(zone),
                new RunFlyWheelByVelocityConstant()
        );
    }
}