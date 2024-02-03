package edu.greenblitz.robotName.commands.auto;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.path.PathConstraints;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.ChassisConstants;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.Command;
import org.littletonrobotics.junction.Logger;

import java.util.function.Supplier;

public class MoveToPosition extends GBCommand {

    private Command moveToFoundPose;
    private Supplier<Pose2d> end;

    public MoveToPosition(Pose2d endPoint) {
        end = () -> endPoint;
    }

    public MoveToPosition(Supplier<Pose2d> endPoint) {
        end = endPoint;
    }

    @Override
    public void initialize() {
        moveToFoundPose = AutoBuilder.pathfindToPose(
                end.get(),
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
