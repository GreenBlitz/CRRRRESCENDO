package edu.greenblitz.robotName.subsystems.shooter.Pivot.FalconPivot;

import com.ctre.phoenix6.controls.MotionMagicDutyCycle;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.IPivot;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.PivotConstants;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.PivotInputsAutoLogged;

import static edu.greenblitz.robotName.subsystems.shooter.Pivot.FalconPivot.FalconPivotConstants.*;


public class FalconPivot implements IPivot {

    private TalonFX motor;

    public FalconPivot() {
        motor = new TalonFX(MOTOR_ID);
        motor.getConfigurator().apply(MOTION_MAGIC_CONFIGS);
        motor.getConfigurator().apply(CURRENT_LIMITS_CONFIGS);
        motor.getConfigurator().apply(CLOSED_LOOP_RAMPS_CONFIGS);
        motor.getConfigurator().apply(SWITCH_CONFIGS);
        motor.setNeutralMode(NEUTRAL_MODE_VALUE);
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
                SIMPLE_MOTOR_FF.calculate(0),
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
        inputs.temperature = motor.getDeviceTemp().getValue();
        inputs.hasHitForwardLimit = motor.getFault_ForwardSoftLimit().getValue();
        inputs.hasHitBackwardsLimit = motor.getFault_ReverseSoftLimit().getValue();
    }
}
