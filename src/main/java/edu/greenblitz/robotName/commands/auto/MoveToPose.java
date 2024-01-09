package edu.greenblitz.robotName.commands.auto;

import com.pathplanner.lib.commands.PathfindThenFollowPathHolonomic;
import com.pathplanner.lib.path.GoalEndState;
import com.pathplanner.lib.path.PathConstraints;
import com.pathplanner.lib.path.PathPlannerPath;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.ChassisConstants;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.Command;

public class MoveToPose {
    public static Command getPathCommand(Pose2d startPoint, Pose2d endPoint, PathConstraints constraints){
        return new PathfindThenFollowPathHolonomic(
                new PathPlannerPath(
                        PathPlannerPath.bezierFromPoses(startPoint,endPoint),
                        constraints,
                        new GoalEndState(0,endPoint.getRotation())
                ),
                constraints,
                SwerveChassis.getInstance()::getRobotPose,
                SwerveChassis.getInstance()::getRobotRelativeChassisSpeeds,
                SwerveChassis.getInstance()::moveByRobotRelativeSpeeds,
                ChassisConstants.PATH_FOLLOWER_CONFIG,
                0,
                SwerveChassis.getInstance()
        );
    }
}
