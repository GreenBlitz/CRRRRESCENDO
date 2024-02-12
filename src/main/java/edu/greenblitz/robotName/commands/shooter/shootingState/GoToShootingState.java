package edu.greenblitz.robotName.commands.shooter.shootingState;

import edu.greenblitz.robotName.commands.shooter.MoveShooterToAngle;
import edu.greenblitz.robotName.commands.shooter.flyWheel.RunFlyWheelByVelocityConstant;
import edu.greenblitz.robotName.commands.swerve.MoveRobotToShootingPosition;
import edu.greenblitz.robotName.shootingStateService.ShootingPositionConstants;
import edu.greenblitz.robotName.shootingStateService.ShootingStateCalculations;
import edu.greenblitz.robotName.utils.shootingCalculations.ShootingZone;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class GoToShootingState extends ParallelCommandGroup {

    public GoToShootingState(ShootingZone bigZone, ShootingZone smallZone) {
        super(
                new MoveRobotToShootingPosition(bigZone, smallZone),
                new MoveShooterToAngle(ShootingStateCalculations.getTargetShooterAngle(bigZone)),
                new RunFlyWheelByVelocityConstant()
        );
    }

    public GoToShootingState() {
        this(ShootingPositionConstants.LEGAL_SHOOTING_ZONE,ShootingPositionConstants.OPTIMAL_SHOOTING_ZONE);
    }
}