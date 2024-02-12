package edu.greenblitz.robotName.commands.swerve;

import com.pathplanner.lib.path.PathConstraints;
import edu.greenblitz.robotName.subsystems.swerve.chassis.ChassisConstants;
import edu.greenblitz.robotName.subsystems.swerve.chassis.SwerveChassis;
import edu.greenblitz.robotName.utils.GBPathFinding.GBAutoBuilder;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.*;

import java.util.Set;
import java.util.function.Supplier;

public class MoveToPosition extends DeferredCommand {

    public MoveToPosition(Pose2d endPoint) {
        this(endPoint,ChassisConstants.CONSTRAINTS);
    }

    public MoveToPosition(Supplier<Pose2d> endPoint, PathConstraints constraints) {
        super(getPoseFinding(endPoint, constraints),Set.of(SwerveChassis.getInstance()));
    }

    public MoveToPosition(Supplier<Pose2d> endPoint) {
        super(getPoseFinding(endPoint,ChassisConstants.CONSTRAINTS),Set.of(SwerveChassis.getInstance()));
    }

    public MoveToPosition(Pose2d endPoint, PathConstraints constraints) {
        this(() -> endPoint, constraints);
    }

    private static Supplier<Command> getPoseFinding(Supplier<Pose2d> endPoint, PathConstraints constraints) {
        return () -> GBAutoBuilder.pathfindToPose(
                    endPoint.get(),
                    constraints
        );
    }

}
