package edu.greenblitz.robotName.subsystems.shooter.Pivot;

import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.greenblitz.robotName.subsystems.Battery;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.math.geometry.*;
import org.littletonrobotics.junction.Logger;

import static edu.greenblitz.robotName.subsystems.shooter.Pivot.FalconPivot.FalconPivotConstants.SIMPLE_MOTOR_FF;
import static edu.greenblitz.robotName.subsystems.shooter.Pivot.PivotConstants.TOLERANCE;


public class Pivot extends GBSubsystem {

    private static Pivot instance;

    private PivotInputsAutoLogged pivotInputs;

    private IPivot pivot;


    private Pivot() {
        pivot = PivotFactory.create();
        pivotInputs = new PivotInputsAutoLogged();
        pivot.updateInputs(pivotInputs);
    }

    public static void init() {
        if (instance == null)
            instance = new Pivot();
    }

    public static Pivot getInstance() {
        init();
        return instance;
    }

    @Override
    public void periodic() {
        super.periodic();

        pivot.updateInputs(pivotInputs);
        Logger.processInputs("Shooter/Pivot", pivotInputs);
        Logger.recordOutput("Shooter/Pivot", getPivotPose());
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


    public void resetAngle(double position) {
        pivot.resetAngle(position);
    }

    public void moveToAngle(double goalAngle) {
        pivot.moveToAngle(goalAngle);
    }

    public void standInPlace() {
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

    public Rotation2d getAngle() {
        return Rotation2d.fromRadians(pivotInputs.position);
    }

    public boolean isAtAngle(Rotation2d angle) {
        return Math.abs(angle.getRadians() - getAngle().getRadians()) <= TOLERANCE;
    }

    public Pose3d getPivotPose() {
        return new Pose3d(
                new Translation3d(),
                new Rotation3d(getAngle().getRadians(), 0, 0)
        );
    }
}
