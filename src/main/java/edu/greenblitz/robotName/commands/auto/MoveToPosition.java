package edu.greenblitz.robotName.commands.auto;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.path.PathConstraints;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.ChassisConstants;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ProxyCommand;

import java.util.function.Supplier;

public class MoveToPosition extends ProxyCommand {

    public MoveToPosition(Pose2d endPoint) {
        super(
                AutoBuilder.pathfindToPose(
                        endPoint,
                        ChassisConstants.CONSTRAINTS
                )
        );
    }

    public MoveToPosition(Supplier<Pose2d> endPoint) {
        super(
                AutoBuilder.pathfindToPose(
                        endPoint.get(),
                        ChassisConstants.CONSTRAINTS
                )
        );
    }

}
