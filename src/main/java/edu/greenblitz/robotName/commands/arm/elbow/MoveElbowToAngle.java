package edu.greenblitz.robotName.commands.arm.elbow;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowConstants;
import edu.greenblitz.robotName.subsystems.swerve.chassis.SwerveChassis;
import edu.wpi.first.math.geometry.Rotation2d;

public class MoveElbowToAngle extends ElbowCommand {

    private Rotation2d targetAngle;

    private boolean isElbowCrashableIntoWall;

    public MoveElbowToAngle(Rotation2d targetAngle) {
        this.targetAngle = targetAngle;
    }

    public MoveElbowToAngle(ElbowConstants.PresetPositions targetAngle) {
        this(targetAngle.ANGLE);
    }

    @Override
    public void initialize() {
        isElbowCrashableIntoWall = SwerveChassis.getInstance().isRobotNearBoundsOfField();
        if (isElbowCrashableIntoWall) {
            elbow.setCurrentAngle();
            elbow.standInPlace();
        }
        else
            elbow.moveToAngle(targetAngle);
    }

    @Override
    public void execute() {
        if (SwerveChassis.getInstance().isRobotNearBoundsOfField()) {
            elbowStandInPlaceOnce();
            isElbowCrashableIntoWall = true;
        }
        else if (isElbowCrashableIntoWall) {
            elbow.moveToAngle(targetAngle);
            isElbowCrashableIntoWall = false;
        }
        else if (Robot.isSimulation()) {
            elbow.moveToAngle(targetAngle);
        }

    }

    private void elbowStandInPlaceOnce() {
        if (!isElbowCrashableIntoWall || Robot.isSimulation()) {
            elbow.setCurrentAngle();
            elbow.standInPlace();
        }
    }

    @Override
    public boolean isFinished() {
        return elbow.isAtAngle(targetAngle);
    }
}