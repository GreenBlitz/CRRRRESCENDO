package edu.greenblitz.robotName.subsystems.Elbow;

import com.ctre.phoenix6.controls.MotionMagicDutyCycle;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class FalconElbow implements IElbow {

    private TalonFX motor;

    public FalconElbow() {
        motor = new TalonFX(ElbowConstants.Falcon.MOTOR_ID);
        motor.getConfigurator().apply(ElbowConstants.Falcon.MOTION_MAGIC_CONFIGS);
        motor.getConfigurator().apply(ElbowConstants.Falcon.CURRENT_LIMITS_CONFIGS);
        motor.getConfigurator().apply(ElbowConstants.Falcon.CLOSED_LOOP_RAMPS_CONFIGS);
        motor.getConfigurator().apply(ElbowConstants.Falcon.SWITCH_CONFIGS);
        motor.setNeutralMode(ElbowConstants.Falcon.NEUTRAL_MODE_VALUE);
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
                goalAngle / ElbowConstants.RELATIVE_POSITION_CONVERSION_FACTOR,
                true,
                ElbowConstants.Falcon.SIMPLE_MOTOR_FF.calculate(0),
                0,
                true,
                true,
                true
        ));
    }

    @Override
    public void updateInputs(ElbowInputsAutoLogged inputs) {
        inputs.outputCurrent = motor.getSupplyCurrent().getValue();
        inputs.appliedOutput = motor.getMotorVoltage().getValue();
        inputs.position = motor.getPosition().getValue() * ElbowConstants.RELATIVE_POSITION_CONVERSION_FACTOR;
        inputs.velocity = motor.getVelocity().getValue() * ElbowConstants.RELATIVE_VELOCITY_CONVERSION_FACTOR;
        inputs.absoluteEncoderPosition = motor.getDutyCycle().getValue() * ElbowConstants.ABSOLUTE_POSITION_CONVERSION_FACTOR;
        inputs.hasHitForwardLimit = motor.getFault_ForwardSoftLimit().getValue();
        inputs.hasHitBackwardsLimit = motor.getFault_ReverseSoftLimit().getValue();
    }
}
