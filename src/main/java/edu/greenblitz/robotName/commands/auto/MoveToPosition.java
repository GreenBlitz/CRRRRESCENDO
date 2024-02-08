package edu.greenblitz.robotName.commands.auto;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.path.PathConstraints;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.ChassisConstants;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.ProxyCommand;

public class MoveToPosition extends ProxyCommand {

    public MoveToPosition(Pose2d endPoint) {
        this(endPoint, ChassisConstants.CONSTRAINTS);
    }

    public MoveToPosition(Pose2d endPoint, PathConstraints constraints) {
        super(() -> AutoBuilder.pathfindToPose(
                endPoint,
                constraints
        ));
    }

}
