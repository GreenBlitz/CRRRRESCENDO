package edu.greenblitz.robotName.commands.auto;

import com.pathplanner.lib.commands.PathfindThenFollowPathHolonomic;
import com.pathplanner.lib.path.GoalEndState;
import com.pathplanner.lib.path.PathConstraints;
import com.pathplanner.lib.path.PathPlannerPath;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.ChassisConstants;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;
import edu.greenblitz.robotName.utils.FMSUtils;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.Command;

public class MoveToPose {
    public static Command getPathCommand(Pose2d endPoint, PathConstraints constraints) {
        return new PathfindThenFollowPathHolonomic(
                new PathPlannerPath(
                        PathPlannerPath.bezierFromPoses(SwerveChassis.getInstance().getRobotPose(), new Pose2d(4,4, Rotation2d.fromDegrees(100)),endPoint),
                        constraints,
                        new GoalEndState(0, endPoint.getRotation())
                ),
                constraints,
                SwerveChassis.getInstance()::getRobotPose,
                SwerveChassis.getInstance()::getRobotRelativeChassisSpeeds,
                SwerveChassis.getInstance()::moveByRobotRelativeSpeeds,
                ChassisConstants.PATH_FOLLOWER_CONFIG,
                () -> FMSUtils.getAlliance() == DriverStation.Alliance.Red,
                SwerveChassis.getInstance()
        );
    }
}
