package edu.greenblitz.robotName.subsystems.Arm.Elbow;

import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.greenblitz.robotName.subsystems.Battery;
import edu.greenblitz.robotName.utils.GBSubsystem;
import org.littletonrobotics.junction.Logger;

import static edu.greenblitz.robotName.subsystems.Arm.Elbow.ElbowConstants.Falcon.SIMPLE_MOTOR_FF;

public class Elbow extends GBSubsystem {
    private static Elbow instance;
    private IElbow elbow;
    private final ElbowInputsAutoLogged elbowInputs;
    public double goalAngle;

    public static Elbow getInstance() {
        init();
        return instance;
    }

    public static void init(){
        if (instance == null)
            instance = new Elbow();
    }

    private Elbow() {
        elbowInputs = new ElbowInputsAutoLogged();
        elbow = ElbowFactory.create();
        elbow.updateInputs(elbowInputs);
        goalAngle = getAngleInRadians();
    }

    @Override
    public void periodic() {
        super.periodic();
        if (isAtAngle(goalAngle))
            standInPlace();
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



    public void resetLength(double position) {
        elbow.resetPosition(position);
        goalAngle = position;
    }
    public void moveToAngle(double targetPos){
        elbow.moveToAngle(targetPos);
    }

    public void standInPlace() {
        elbow.setPower(getStaticFF());
    }
    public void stop() {
        elbow.setPower(0);
    }



    public static double getStaticFF(){
        return SIMPLE_MOTOR_FF.calculate(0);
    }
    public static double getDynamicFF(double velocity){
        return SIMPLE_MOTOR_FF.calculate(velocity);
    }
    public double getVoltage() {
        return elbowInputs.appliedOutput * Battery.getInstance().getCurrentVoltage();
    }
    public double getAngleInRadians() {
        return elbowInputs.position;
    }
    public double getVelocity() {
        return elbowInputs.velocity;
    }
    public boolean isAtAngle(double targetHeight) {
        return Math.abs(targetHeight - getAngleInRadians()) <= ElbowConstants.TOLERANCE;
    }

}
