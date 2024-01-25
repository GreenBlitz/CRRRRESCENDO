package edu.greenblitz.robotName.subsystems.arm;


import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.subsystems.arm.EndEffector.WristUtils.IWrist;
import edu.greenblitz.robotName.subsystems.arm.EndEffector.WristUtils.NeoWrist.NeoWristConstants;
import edu.greenblitz.robotName.subsystems.arm.EndEffector.WristUtils.WristFactory;
import edu.greenblitz.robotName.subsystems.arm.EndEffector.WristUtils.WristInputsAutoLogged;
import edu.greenblitz.robotName.subsystems.Battery;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.math.geometry.Rotation2d;
import org.littletonrobotics.junction.Logger;

import static edu.greenblitz.robotName.subsystems.arm.EndEffector.WristUtils.WristConstants.TOLERANCE;

public class Wrist extends GBSubsystem {

    private static Wrist instance;

    private WristInputsAutoLogged wristInputs;

    private IWrist wrist;


    public static void init() {
        if (instance == null) {
            instance = new Wrist();
        }
    }

    public static Wrist getInstance() {
        init();
        return instance;
    }

    private Wrist() {
        wrist = WristFactory.create();
        wristInputs = new WristInputsAutoLogged();
        wrist.updateInputs(wristInputs);
    }

    @Override
    public void periodic() {
        super.periodic();

        wrist.updateInputs(wristInputs);
        Logger.processInputs("Wrist", wristInputs);
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

    public void moveToAngle(Rotation2d goalAngle) {
        wrist.moveToAngle(goalAngle);
    }

    public void standInPlace() {
        wrist.setPower(getStaticFF());
    }


    public double getStaticFF() {
        return NeoWristConstants.WRIST_FEEDFORWARD.calculate(0);
    }

    public double getDynamicFF(double velocity) {
        return NeoWristConstants.WRIST_FEEDFORWARD.calculate(velocity);
    }

    public double getVoltage() {
        return wristInputs.appliedOutput * Battery.getInstance().getCurrentVoltage();
    }

    public double getVelocity() {
        return wristInputs.velocity;
    }

    public double getAngleInRadians() {
        return wristInputs.position;
    }

    public boolean isObjectInside() {
        return wristInputs.isObjectInArm;
    }

    public boolean isAtAngle(Rotation2d angle) {
        return Math.abs(angle.getRadians() - getAngleInRadians()) <= TOLERANCE;
    }


}
