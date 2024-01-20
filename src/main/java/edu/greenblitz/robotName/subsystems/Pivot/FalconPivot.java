package edu.greenblitz.robotName.subsystems.Pivot;

import com.ctre.phoenix6.controls.MotionMagicDutyCycle;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class FalconPivot implements IPivot{

    private TalonFX motor;

    public FalconPivot() {
        motor = new TalonFX(PivotConstants.FalconConfigs.MOTOR_ID);
        motor.getConfigurator().apply(PivotConstants.FalconConfigs.MOTION_MAGIC_CONFIGS);
        motor.getConfigurator().apply(PivotConstants.FalconConfigs.CURRENT_LIMITS_CONFIGS);
        motor.getConfigurator().apply(PivotConstants.FalconConfigs.CLOSED_LOOP_RAMPS_CONFIGS);
        motor.getConfigurator().apply(PivotConstants.FalconConfigs.SWITCH_CONFIGS);
        motor.setNeutralMode(PivotConstants.FalconConfigs.NEUTRAL_MODE_VALUE);
        motor.optimizeBusUtilization();
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
    public void resetAngle(double position) {
        motor.setPosition(position);
    }

    @Override
    public void moveToAngle(double goalAngle) {
        motor.setControl(new MotionMagicDutyCycle(
                goalAngle/ PivotConstants.RELATIVE_POSITION_CONVERSION_FACTOR,
                true,
                PivotConstants.FalconConfigs.SIMPLE_MOTOR_FF.calculate(0),
                0,
                true,
                true,
                true
        ));
    }

    @Override
    public void updateInputs(PivotInputsAutoLogged inputs) {
        inputs.outputCurrent = motor.getSupplyCurrent().getValue();
        inputs.appliedOutput = motor.getMotorVoltage().getValue();
        inputs.position = motor.getPosition().getValue() * PivotConstants.RELATIVE_POSITION_CONVERSION_FACTOR;
        inputs.velocity = motor.getVelocity().getValue() * PivotConstants.RELATIVE_VELOCITY_CONVERSION_FACTOR;
        inputs.absoluteEncoderPosition = motor.getDutyCycle().getValue() * PivotConstants.ABSOLUTE_POSITION_CONVERSION_FACTOR;
        inputs.hasHitForwardLimit = motor.getFault_ForwardSoftLimit().getValue();
        inputs.hasHitBackwardsLimit = motor.getFault_ReverseSoftLimit().getValue();
    }
}
