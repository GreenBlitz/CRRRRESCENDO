package edu.greenblitz.robotName.commands.arm.elbow;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowConstants;
import edu.greenblitz.robotName.subsystems.swerve.chassis.SwerveChassis;
import edu.wpi.first.math.geometry.Rotation2d;

public class SafeMoveElbowToAngle extends ElbowCommand {

    private Rotation2d targetAngle;

    private boolean isElbowCrashableIntoWall;

    public SafeMoveElbowToAngle(Rotation2d targetAngle) {
        this.targetAngle = targetAngle;
    }

    public SafeMoveElbowToAngle(ElbowConstants.PresetPositions targetAngle) {
        this(targetAngle.ANGLE);
    }

    @Override
    public void initialize() {
        isElbowCrashableIntoWall = SwerveChassis.getInstance().isRobotNearBoundsOfField();
        if (isElbowCrashableIntoWall) {
            elbow.setCurrentAngle();
            elbow.standInPlace();
        } else {
            elbow.moveToAngle(targetAngle);
        }
    }

    @Override
    public void execute() {
        if (SwerveChassis.getInstance().isRobotNearBoundsOfField()) {
            elbowStandInPlaceAccordingToRobotType();
        } else {
            elbowMoveToAngleAccordingToRobotType();
        }
    }

    private void elbowStandInPlaceAccordingToRobotType() {
        if (!isElbowCrashableIntoWall || Robot.isSimulation()) {
            elbow.setCurrentAngle();
            elbow.standInPlace();
            isElbowCrashableIntoWall = true;
        }
    }

    private void elbowMoveToAngleAccordingToRobotType() {
        if (isElbowCrashableIntoWall || Robot.isSimulation()) {
            elbow.moveToAngle(targetAngle);
            isElbowCrashableIntoWall = false;
        }
    }

    @Override
    public boolean isFinished() {
        return elbow.isAtAngle(targetAngle);
    }

    @Override
    public void end(boolean interrupted) {
        elbow.setCurrentAngle();
    }
}
