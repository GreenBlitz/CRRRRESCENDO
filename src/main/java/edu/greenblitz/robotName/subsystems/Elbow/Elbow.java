package edu.greenblitz.robotName.subsystems.Elbow;

import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.greenblitz.robotName.subsystems.Battery;
import edu.greenblitz.robotName.subsystems.Pivot.Pivot;
import edu.greenblitz.robotName.subsystems.Pivot.PivotConstants;
import edu.greenblitz.robotName.utils.GBSubsystem;
import org.littletonrobotics.junction.Logger;

import static edu.greenblitz.robotName.subsystems.Elbow.ElbowConstants.*;
import static edu.greenblitz.robotName.subsystems.Elbow.ElbowConstants.Falcon.SIMPLE_MOTOR_FF;

public class Elbow extends GBSubsystem {

    private static Elbow instance;

    private IElbow elbow;

    private final ElbowInputsAutoLogged elbowInputs;

    private double goalAngle;



    public static void init(){
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
        goalAngle = getAngleInRadians();
    }

    @Override
    public void periodic() {
        super.periodic();
        if (isAtAngle(goalAngle)) {
            standInPlace();
        }
        else {
            moveToAngle();
        }
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

    public void setGoalAngle(double angle) {
        goalAngle = angle;
        if ((BRODER > goalAngle && BRODER < getAngleInRadians())  || (BRODER < goalAngle && BRODER > getAngleInRadians()))
            Pivot.getInstance().setGoalAngle(PivotConstants.BRODER);
    }

    public void resetAngle(double position) {
        elbow.resetAngle(position);
        goalAngle = position;
    }

    private void moveToAngle(){
    if (Pivot.getInstance().isAtSafeAngle())
        elbow.moveToAngle(goalAngle);
    }

    private void standInPlace() {
        elbow.setPower(getStaticFF());
    }



    public double getStaticFF(){
        return SIMPLE_MOTOR_FF.calculate(0);
    }

    public double getDynamicFF(double velocity){
        return SIMPLE_MOTOR_FF.calculate(velocity);
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

    public double getGoalAngleRadians() {
        return goalAngle;
    }

    public boolean isAtAngle(double targetHeight) {
        return Math.abs(targetHeight - getAngleInRadians()) <= ElbowConstants.TOLERANCE;
    }

    public boolean isAtGoalAngle() {
        return isAtAngle(goalAngle);
    }
    public boolean isAtBroderAngle() {
        return Math.abs(BRODER - getAngleInRadians()) <= BRODER_TOLERANCE;
    }


}
