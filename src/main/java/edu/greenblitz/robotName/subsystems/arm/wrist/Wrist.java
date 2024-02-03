package edu.greenblitz.robotName.subsystems.arm.wrist;

import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.subsystems.arm.elbow.Elbow;
import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.elbow.SimulationElbow.SimulationElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.wrist.NeoWrist.NeoWristConstants;
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


    private Wrist() {
        wrist = WristFactory.create();
        wristInputs = new WristInputsAutoLogged();
        wrist.updateInputs(wristInputs);
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

    public void standInPlace() {
        wrist.standInPlace();
    }

    public double getVoltage() {
        return wristInputs.appliedOutput;
    }

    public double getVelocity() {
        return wristInputs.velocity;
    }

    public double getAngleInRadians() {
        return wristInputs.position;
    }



    public boolean isAtAngle(Rotation2d angle) {
        return Math.abs(angle.getRadians() - getAngleInRadians()) <= TOLERANCE;
    }

    public Pose3d getPose3D() {
        Translation3d elbowTranslation = Elbow.getInstance().getPose3D().getTranslation();
        double trueElbowAngle = Elbow.getInstance().getAngleInRadians() + Math.PI / 2 + SimulationElbowConstants.MECHANISM_NAME_TO_ROBOT_TRANSLATION;

        double relativeWristY = ElbowConstants.ARM_LENGTH * Math.sin(-trueElbowAngle);
        double relativeWristZ = ElbowConstants.ARM_LENGTH * Math.cos(trueElbowAngle);

        return new Pose3d(
                elbowTranslation.minus(new Translation3d(0, relativeWristY, relativeWristZ)),
                new Rotation3d(wristInputs.position + trueElbowAngle, 0, 0)
        );
    }
}
