package edu.greenblitz.robotName.subsystems.Arm.Elbow;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix6.controls.MotionMagicDutyCycle;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.greenblitz.robotName.utils.motors.GBFalcon;

import static edu.greenblitz.robotName.subsystems.Arm.Elbow.ElbowConstants.Falcon.*;
import static edu.greenblitz.robotName.subsystems.Arm.Elbow.ElbowConstants.*;

public class FalconElbow implements IElbow {

    TalonFX motor;

    public FalconElbow() {
        motor = new TalonFX(1);
        motor.getConfigurator().apply(SWITCH_CONFIGS);
        motor.getConfigurator().apply(CURRENT_LIMITS_CONFIGS);
        motor.getConfigurator().apply(MOTION_MAGIC_CONFIGS);
        motor.getConfigurator().apply(CLOSED_LOOP_RAMPS_CONFIGS);
        motor.setNeutralMode(NEUTRAL_MODE_VALUE);
        motor.optimizeBusUtilization();
    }

    @Override
    public void resetPosition(double position) {
        motor.setPosition(position);
    }

    @Override
    public void setPower(double power) {
        motor.set(power);
    }

    @Override
    public void moveToAngle(double goalAngle) {
        motor.setControl(new MotionMagicDutyCycle(
                goalAngle / RELATIVE_POSITION_CONVERSION_FACTOR,
                true,
                SIMPLE_MOTOR_FF.calculate(0),
                0,
                true,
                true,
                true
        ));
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
    public void updateInputs(ElbowInputsAutoLogged inputs) {
        inputs.position = motor.getRotorPosition().getValue() * RELATIVE_POSITION_CONVERSION_FACTOR;
        inputs.velocity = motor.getVelocity().getValue() * RELATIVE_VELOCITY_CONVERSION_FACTOR;
        inputs.outputCurrent = motor.getSupplyCurrent().getValue();
        inputs.appliedOutput = motor.getMotorVoltage().getValue();
        inputs.hasHitForwardLimit = motor.getFault_ForwardSoftLimit().getValue();
        inputs.hasHitBackwardsLimit = motor.getFault_ReverseSoftLimit().getValue();
        inputs.absoluteEncoderPosition = motor.getDutyCycle().getValue() * ABSOLUTE_POSITION_CONVERSION_FACTOR;
    }
}
