package edu.greenblitz.robotName.subsystems.arm;

import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.greenblitz.robotName.subsystems.arm.ArmMechanism.ArmMechanism;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.ElbowInputsAutoLogged;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.FalconElbow.FalconElbowConstants;
import edu.greenblitz.robotName.subsystems.Battery;
import edu.greenblitz.robotName.utils.GBSubsystem;
import org.littletonrobotics.junction.Logger;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.ElbowFactory;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.IElbow;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.ElbowConstants;
public class Elbow extends GBSubsystem {

    private static Elbow instance;

    private IElbow elbow;

    private final ElbowInputsAutoLogged elbowInputs;


    public static void init() {
        if (instance == null)
            instance = new Elbow();
    }

    public static Elbow getInstance() {
        init();
        return instance;
    }

    private Elbow() {
        elbow = ElbowFactory.create();
        elbowInputs = new ElbowInputsAutoLogged();
        elbow.updateInputs(elbowInputs);
    }

    @Override
    public void periodic() {
        super.periodic();
        ArmMechanism.getInstance().periodic();

        elbow.updateInputs(elbowInputs);
        Logger.processInputs("Elbow", elbowInputs);
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


    public void resetAngle(double position) {
        elbow.resetAngle(position);
    }

    public void moveToAngle(double goalAngle) {
        elbow.moveToAngle(goalAngle);
    }

    public void standInPlace() {
        elbow.setPower(getStaticFF());
    }



    public double getStaticFF() {
        return FalconElbowConstants.SIMPLE_MOTOR_FF.calculate(0);
    }

    public double getDynamicFF(double velocity) {
        return FalconElbowConstants.SIMPLE_MOTOR_FF.calculate(velocity);
    }

    public double getVoltage() {
        return elbowInputs.appliedOutput * Battery.getInstance().getCurrentVoltage();
    }

    public double getVelocity() {
        return elbowInputs.velocity;
    }

    public double getAngleInRadians() {
        return elbowInputs.position;
    }

    public boolean isAtAngle(double targetHeight) {
        return Math.abs(targetHeight - getAngleInRadians()) <= ElbowConstants.TOLERANCE;
    }


}
