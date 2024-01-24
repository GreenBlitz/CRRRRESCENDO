package edu.greenblitz.robotName.commands.auto;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.path.PathConstraints;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.ChassisConstants;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.Command;

public class MoveToPose {
    public static Command getPathCommand(Pose2d endPoint){
        return getPathCommand(endPoint, ChassisConstants.CONSTRAINTS);
    }
    public static Command getPathCommand(Pose2d endPoint, PathConstraints constraints) {
        return AutoBuilder.pathfindToPose(
                endPoint,
                constraints
        );
    }
}
