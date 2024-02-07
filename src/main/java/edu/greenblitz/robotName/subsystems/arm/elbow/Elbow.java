package edu.greenblitz.robotName.subsystems.arm.elbow;

import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import org.littletonrobotics.junction.Logger;

public class Elbow extends GBSubsystem {

    private static Elbow instance;

    private IElbow elbow;

    private ElbowInputsAutoLogged elbowInputs;

    private Rotation2d currentAngle;

    public static void init() {
        if (instance == null) {
            instance = new Elbow();
        }
    }

    public static Elbow getInstance() {
        init();
        return instance;
    }

    private Elbow() {
        elbow = ElbowFactory.create();
        elbowInputs = new ElbowInputsAutoLogged();
        elbow.updateInputs(elbowInputs);
        currentAngle = elbowInputs.position;
    }

    @Override
    public void periodic() {
        super.periodic();

        elbow.updateInputs(elbowInputs);
        Logger.processInputs("Elbow", elbowInputs);
        Logger.recordOutput("Elbow", getPose3D());
    }


    public void setPower(double power) {
        elbow.setPower(power);
    }

    public void setMotorVoltage(double voltage) {
        elbow.setVoltage(voltage);
    }

    public void setIdleMode(NeutralModeValue idleMode) {
        elbow.setIdleMode(idleMode);
    }


    public void resetAngle(Rotation2d position) {
        elbow.resetAngle(position);
    }

    public void moveToAngle(Rotation2d targetAngle) {
        elbow.moveToAngle(targetAngle);
    }

    public void standInPlaceWithSet() {
        currentAngle = getAngle();
        elbow.standInPlace(currentAngle);
    }

    public void standInPlace() {
        elbow.standInPlace(currentAngle);
    }

    public void setCurrentAngle(){
        currentAngle = getAngle();
    }

    public double getVoltage() {
        return elbowInputs.appliedOutput;
    }

    public double getVelocity() {
        return elbowInputs.velocity;
    }

    public Rotation2d getAngle() {
        return elbowInputs.position;
    }

    public boolean isAtAngle(Rotation2d targetAngle) {
        return Math.abs(targetAngle.getRadians() - getAngle().getRadians()) <= ElbowConstants.TOLERANCE.getRadians();
    }

    public boolean isInShooterCollisionRange() {
        return elbowInputs.position.getRadians() > ElbowConstants.SHOOTER_COLLISION_RANGE.getFirst().getRadians() &&
                elbowInputs.position.getRadians() < ElbowConstants.SHOOTER_COLLISION_RANGE.getSecond().getRadians();
    }

    public Pose3d getPose3D (){
        return new Pose3d(
                ElbowConstants.ELBOW_POSITION_RELATIVE_TO_ROBOT,
                new Rotation3d(elbowInputs.position.getRadians() ,0, 0)
        );
    }

}
