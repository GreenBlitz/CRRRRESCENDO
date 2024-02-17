package edu.greenblitz.robotName.commands.arm.elbow;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowConstants;
import edu.greenblitz.robotName.subsystems.swerve.chassis.SwerveChassis;
import edu.wpi.first.math.geometry.Rotation2d;

public class MoveElbowToAngle extends ElbowCommand {

    private Rotation2d targetAngle;

    private boolean isRobotNearFieldWalls;

    public MoveElbowToAngle(Rotation2d targetAngle) {
        this.targetAngle = targetAngle;
    }

    public MoveElbowToAngle(ElbowConstants.PresetPositions targetAngle) {
        this(targetAngle.ANGLE);
    }

    @Override
    public void initialize() {
        isRobotNearFieldWalls = SwerveChassis.getInstance().isRobotNearBoundsOfField();
        if (isRobotNearFieldWalls) {
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
            isRobotNearFieldWalls = true;
        }
        else if (isRobotNearFieldWalls) {
            elbow.moveToAngle(targetAngle);
            isRobotNearFieldWalls = false;
        }
        else if (Robot.isSimulation()) {
            elbow.moveToAngle(targetAngle);
        }

    }

    private void elbowStandInPlaceOnce() {
        if (!isRobotNearFieldWalls || Robot.isSimulation()) {
            elbow.setCurrentAngle();
            elbow.standInPlace();
        }
    }

    @Override
    public boolean isFinished() {
        return elbow.isAtAngle(targetAngle);
    }
}