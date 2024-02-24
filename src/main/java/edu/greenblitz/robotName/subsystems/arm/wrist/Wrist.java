package edu.greenblitz.robotName.subsystems.arm.wrist;

import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.subsystems.arm.elbow.Elbow;
import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowConstants;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Translation3d;
import org.littletonrobotics.junction.Logger;

import static edu.greenblitz.robotName.subsystems.arm.wrist.WristConstants.TOLERANCE;

public class Wrist extends GBSubsystem {

    private static Wrist instance;

    private WristInputsAutoLogged wristInputs;

    private IWrist wrist;

    private Rotation2d currentAngle;

    private Wrist() {
        wrist = WristFactory.create();
        wristInputs = new WristInputsAutoLogged();

        wrist.updateInputs(wristInputs);
        wrist.resetAngleByAbsoluteEncoder();
        currentAngle = wristInputs.position;
    }

    public static void init() {
        if (instance == null) {
            instance = new Wrist();
        }
    }

    public static Wrist getInstance() {
        init();
        return instance;
    }

    @Override
    public void periodic() {
        super.periodic();

        wrist.updateInputs(wristInputs);
        Logger.processInputs("Wrist", wristInputs);
        Logger.recordOutput("Wrist", getPose3D());
    }

    public void setPower(double power) {
        wrist.setPower(power);
    }

    public void setMotorVoltage(double voltage) {
        wrist.setVoltage(voltage);
    }

    public void setIdleMode(CANSparkMax.IdleMode idleMode) {
        wrist.setIdleMode(idleMode);
    }

    public void resetAngle(Rotation2d position) {
        wrist.resetAngle(position);
    }

    public void moveToAngle(Rotation2d targetAngle) {
        wrist.moveToAngle(Rotation2d.fromRadians(targetAngle.getRadians() % (2 * Math.PI)));
    }

    public void setCurrentAngle() {
        currentAngle = getAngle();
    }

    public void setCurrentAngle(Rotation2d angle) {
        currentAngle = angle;
    }

    public void standInPlace() {
        wrist.moveToAngle(currentAngle);
    }

    public double getVoltage() {
        return wristInputs.appliedOutput;
    }

    public double getVelocity() {
        return wristInputs.velocity;
    }

    public Rotation2d getAngle() {
        return wristInputs.position;
    }

    public boolean isAtAngle(Rotation2d angle) {
        return Math.abs(angle.getRadians() - getAngle().getRadians()) <= TOLERANCE.getRadians();
    }

    public Pose3d getPose3D() {
        Translation3d elbowTranslation = Elbow.getInstance().getPose3D().getTranslation();
        double trueElbowAngle = Elbow.getInstance().getAngle().getRadians() + Math.PI / 2;

        double relativeWristX = -ElbowConstants.ARM_LENGTH * Math.sin(-trueElbowAngle);
        double relativeWristZ = ElbowConstants.ARM_LENGTH * Math.cos(trueElbowAngle);

        return new Pose3d(
                elbowTranslation.minus(new Translation3d(relativeWristX, 0, relativeWristZ)),
                new Rotation3d(0, wristInputs.position.getRadians() + trueElbowAngle, 0)
        );
    }

    public static Rotation2d getAngleRelativeToGround(Rotation2d elbowRelativeAngle) {
        return Elbow.getInstance().getAngle().minus(elbowRelativeAngle);
    }

    public Rotation2d getAngleRelativeToGround() {
        return getAngleRelativeToGround(wristInputs.position);
    }
}