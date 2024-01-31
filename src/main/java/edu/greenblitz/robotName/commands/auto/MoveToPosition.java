package edu.greenblitz.robotName.commands.auto;

import com.pathplanner.lib.auto.AutoBuilder;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.ChassisConstants;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.Command;

import java.util.function.Supplier;

public class MoveToPosition extends GBCommand {

    private Command moveToFoundPose;
    private Supplier<Pose2d> endPosition;

    public MoveToPosition(Pose2d endPoint) {
        endPosition = () -> endPoint;
    }

    public MoveToPosition(Supplier<Pose2d> endPoint) {
        endPosition = endPoint;
    }

    @Override
    public void initialize() {
        moveToFoundPose = AutoBuilder.pathfindToPose(
                endPosition.get(),
                ChassisConstants.CONSTRAINTS
        );
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
