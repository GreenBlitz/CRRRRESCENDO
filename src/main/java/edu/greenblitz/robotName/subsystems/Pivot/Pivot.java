package edu.greenblitz.robotName.subsystems.Pivot;

import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.greenblitz.robotName.subsystems.Battery;
import edu.greenblitz.robotName.subsystems.Arm.Elbow.Elbow;
import edu.greenblitz.robotName.subsystems.Arm.Elbow.ElbowConstants;
import edu.greenblitz.robotName.utils.GBSubsystem;
import org.littletonrobotics.junction.Logger;

import static edu.greenblitz.robotName.subsystems.Pivot.PivotConstants.BRODER;
import static edu.greenblitz.robotName.subsystems.Pivot.PivotConstants.FalconConfigs.SIMPLE_MOTOR_FF;
import static edu.greenblitz.robotName.subsystems.Pivot.PivotConstants.TOLERANCE;

public class Pivot extends GBSubsystem {

    private static Pivot instance;

    private PivotInputsAutoLogged pivotInputs;

    private IPivot pivot;

    private double goalAngle;



    public static void init() {
        if (instance == null)
            instance = new Pivot();
    }

    public static Pivot getInstance() {
        init();
        return instance;
    }

    private Pivot() {
        pivot = PivotFactory.create();
        pivotInputs = new PivotInputsAutoLogged();
        pivot.updateInputs(pivotInputs);
        goalAngle = getAngleInRadians();
    }

    @Override
    public void periodic() {
        super.periodic();
        if (isAtAngle(goalAngle))
            standInPlace();
        else
            moveToAngle();
        pivot.updateInputs(pivotInputs);
        Logger.processInputs("Pivot", pivotInputs);
    }


    public void setPower(double power) {
        pivot.setPower(power);
    }

    public void setMotorVoltage(double voltage) {
        pivot.setVoltage(voltage);
    }

    public void setIdleMode(NeutralModeValue idleMode) {
        pivot.setIdleMode(idleMode);
    }

    public void setGoalAngle(double targetAngle) {
        goalAngle = targetAngle;
    }

    public void resetAngle(double position) {
        pivot.resetAngle(position);
        goalAngle = position;
    }

    private void moveToAngle() {
        pivot.moveToAngle(goalAngle);
    }

    private void standInPlace() {
        pivot.setPower(getStaticFF());
    }


    public double getStaticFF() {
        return SIMPLE_MOTOR_FF.calculate(0);
    }

    public double getDynamicFF(double velocity) {
        return SIMPLE_MOTOR_FF.calculate(velocity);
    }

    public double getVoltage() {
        return pivotInputs.appliedOutput * Battery.getInstance().getCurrentVoltage();
    }

    public double getVelocity() {
        return pivotInputs.velocity;
    }

    public double getAngleInRadians() {
        return pivotInputs.position;
    }

    public double getGoalAngleRadians() {
        return goalAngle;
    }

    public boolean isAtAngle(double angle) {
        return Math.abs(angle - getAngleInRadians()) <= TOLERANCE;
    }

    public boolean isAtGoalAngle() {
        return isAtAngle(goalAngle);
    }



}
