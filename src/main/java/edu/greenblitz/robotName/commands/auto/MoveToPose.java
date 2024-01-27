package edu.greenblitz.robotName.commands.auto;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.path.PathConstraints;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.ChassisConstants;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.Command;

public class MoveToPose extends GBCommand {

    private Command moveToFoundPose;

    public MoveToPose(Pose2d endPoint) {
        this(endPoint, ChassisConstants.CONSTRAINTS);
    }

    public MoveToPose(Pose2d endPoint, PathConstraints constraints) {
        moveToFoundPose = AutoBuilder.pathfindToPose(
                endPoint,
                constraints
        );
    }

    @Override
    public void initialize() {
        moveToFoundPose.initialize();
    }

    @Override
    public void execute() {
        moveToFoundPose.execute();
    }

    @Override
    public boolean isFinished() {
        return moveToFoundPose.isFinished();
    }

    @Override
    public void end(boolean interrupted) {
        moveToFoundPose.end(interrupted);
    }
}
