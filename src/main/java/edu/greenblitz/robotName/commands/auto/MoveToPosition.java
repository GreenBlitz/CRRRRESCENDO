package edu.greenblitz.robotName.commands.auto;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.path.PathConstraints;
import edu.greenblitz.robotName.shootingStateService.ShootingState;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.ChassisConstants;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.Command;
import org.littletonrobotics.junction.Logger;

public class MoveToPosition extends GBCommand {

    private Command moveToFoundPose;
    private Pose2d end;

    public MoveToPosition(Pose2d endPoint) {
        this(endPoint, ChassisConstants.CONSTRAINTS);
        end = endPoint;
    }

    public MoveToPosition(Pose2d endPoint, PathConstraints constraints) {

    }

    @Override
    public void initialize() {
        end = ShootingState.getTargetRobotPosition();
        moveToFoundPose = AutoBuilder.pathfindToPose(
                end,
                ChassisConstants.CONSTRAINTS
        );
        moveToFoundPose.initialize();
        Logger.recordOutput("target", end);
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
