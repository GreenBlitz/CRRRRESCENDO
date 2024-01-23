package edu.greenblitz.robotName.commands.auto;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.path.PathConstraints;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.Command;

public class MoveToPose {
    public static Command getPathCommand(Pose2d endPoint, PathConstraints constraints) {
        return AutoBuilder.pathfindToPose(
                endPoint,
                constraints
        );
    }
}
