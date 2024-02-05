package edu.greenblitz.robotName.subsystems.shooter.Pivot.FalconPivot;

import com.ctre.phoenix6.controls.MotionMagicDutyCycle;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.IPivot;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.PivotConstants;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.PivotInputsAutoLogged;
import edu.wpi.first.math.geometry.Rotation2d;

import static edu.greenblitz.robotName.subsystems.shooter.Pivot.FalconPivot.FalconPivotConstants.*;


public class FalconPivot implements IPivot {

    private TalonFX motor;

    private PivotInputsAutoLogged lastInputs;

    public FalconPivot() {
        motor = new TalonFX(MOTOR_ID);
        motor.getConfigurator().apply(TALON_FX_CONFIGURATION);
        motor.setNeutralMode(NEUTRAL_MODE_VALUE);
        motor.optimizeBusUtilization();

        lastInputs = new PivotInputsAutoLogged();
    }


    @Override
    public void setPower(double power) {
        motor.set(power);
    }

    @Override
    public void setVoltage(double voltage) {
        motor.setVoltage(voltage);
    }

    @Override
    public void setIdleMode(NeutralModeValue idleMode) {
        motor.setNeutralMode(idleMode);
    }

    @Override
    public void resetAngle(Rotation2d position) {
        motor.setPosition(position.getRadians());
    }

    @Override
    public void moveToAngle(Rotation2d targetAngle) {
        motor.setControl(new MotionMagicDutyCycle(
                targetAngle.getRadians() / PivotConstants.RELATIVE_POSITION_CONVERSION_FACTOR,
                true,
                PIVOT_FEED_FORWARD.calculate(targetAngle.getRadians(), 0),
                MOTION_MAGIC_PID_SLOT,
                true,
                true,
                true
        ));
    }

    @Override
    public void standInPlace(Rotation2d targetAngle) {
        motor.setControl(new PositionVoltage(
                        targetAngle.getRadians(),
                        0.0,
                        true,
                        PIVOT_FEED_FORWARD.calculate(targetAngle.getRadians(), 0),
                        STAND_IN_PLACE_PID_SLOT,
                        true,
                        true,
                        true
                )
        );
    }

    @Override
    public void updateInputs(PivotInputsAutoLogged inputs) {
        inputs.outputCurrent = motor.getSupplyCurrent().getValue();
        inputs.appliedOutput = motor.getMotorVoltage().getValue();
        inputs.position = Rotation2d.fromRadians(motor.getPosition().getValue() * PivotConstants.RELATIVE_POSITION_CONVERSION_FACTOR);
        inputs.velocity = motor.getVelocity().getValue() * PivotConstants.RELATIVE_VELOCITY_CONVERSION_FACTOR;
        inputs.absoluteEncoderPosition = motor.getDutyCycle().getValue() * PivotConstants.ABSOLUTE_POSITION_CONVERSION_FACTOR;
        inputs.temperature = motor.getDeviceTemp().getValue();
        inputs.hasHitForwardLimit = motor.getFault_ForwardSoftLimit().getValue();
        inputs.hasHitBackwardsLimit = motor.getFault_ReverseSoftLimit().getValue();

        lastInputs = inputs;
    }
}
