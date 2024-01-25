package edu.greenblitz.robotName.commands.shooter.shootingState;

import edu.greenblitz.robotName.commands.auto.MoveToPose;
import edu.greenblitz.robotName.shootingStateService.ShootingState;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Translation2d;

public class GoToShootingPosition extends GBCommand {
    @Override
    public void execute() {
        new MoveToPose(new Pose2d(ShootingState.getRobotTargetPosition(), ));
        Translation2d relativePosition = ShootingState.getRobotTargetPosition().minus(SwerveChassis.getInstance().getRobotPose().getTranslation());
        Math.atan2(relativePosition.getY(),relativePosition.getX());
    }
}
