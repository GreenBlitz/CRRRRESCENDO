package edu.greenblitz.robotName.subsystems.Arm.Pivot;

import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.greenblitz.robotName.subsystems.Arm.Elbow.ElbowConstants;
import edu.greenblitz.robotName.subsystems.Battery;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.math.util.Units;
import org.littletonrobotics.junction.Logger;

import static edu.greenblitz.robotName.subsystems.Arm.Pivot.PivotConstants.FalconConfigs.*;
import static edu.greenblitz.robotName.subsystems.Arm.Pivot.PivotConstants.TOLERANCE;

public class Pivot extends GBSubsystem {
    private static Pivot instance;
    private PivotInputsAutoLogged pivotInputs;
    private IPivot pivot;
    private double goalAngle;
    public double startingValueOfEncoder;

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
        pivot.updateInputs(pivotInputs);
        goalAngle = 0;
        startingValueOfEncoder = pivotInputs.absoluteEncoderPosition;
    }

    @Override
    public void periodic() {
        super.periodic();
        if (isAtAngle(goalAngle))
            standInPlace();
        pivot.updateInputs(pivotInputs);
        Logger.processInputs("Pivot", pivotInputs);
    }
    public boolean isAtAngle(double angle) {
        return Math.abs(angle-getAngleInRadians()) <= TOLERANCE;
    }
    public void standInPlace() {
        pivot.setPower(getStaticFF());
    }

    public double getAngleInRadians() {
        return pivotInputs.position;
    }

    public static double getStaticFF() {
        return SIMPLE_MOTOR_FF.calculate(0);
    }

    public static double getDynamicFF(double velocity) {
        return SIMPLE_MOTOR_FF.calculate(velocity);
    }

    public void moveToAngle(double goalAngle) {
        this.goalAngle = goalAngle;
        pivot.moveToAngle(Units.radiansToRotations(goalAngle));
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

    public double getVoltage() {
        return pivotInputs.appliedOutput * Battery.getInstance().getCurrentVoltage();
    }

    public double getVelocity() {
        return pivotInputs.velocity;
    }

    public void resetPosition(double position) {
        pivot.resetPosition(position);
        goalAngle = position;
    }

    public void setGoalAngle(double angle) {
        goalAngle = angle;
    }


}
