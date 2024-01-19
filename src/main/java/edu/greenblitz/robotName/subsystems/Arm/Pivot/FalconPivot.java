package edu.greenblitz.robotName.subsystems.Arm.Pivot;

import com.ctre.phoenix6.controls.MotionMagicDutyCycle;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;
import edu.wpi.first.math.util.Units;

import static edu.greenblitz.robotName.subsystems.Arm.Pivot.PivotConstants.*;
import static edu.greenblitz.robotName.subsystems.Arm.Pivot.PivotConstants.FalconConfigs.*;

public class FalconPivot implements IPivot{

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
    public void resetPosition(double position) {
        motor.setPosition(position);
    }

    @Override
    public void moveToAngle(double goalAngle) {
        motor.setControl(new MotionMagicDutyCycle(
                goalAngle/RELATIVE_POSITION_CONVERSION_FACTOR,
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
        inputs.position = motor.getPosition().getValue() * RELATIVE_POSITION_CONVERSION_FACTOR;
        inputs.velocity = motor.getVelocity().getValue() * RELATIVE_VELOCITY_CONVERSION_FACTOR;
        inputs.absoluteEncoderPosition = motor.getDutyCycle().getValue() * ABSOLUTE_POSITION_CONVERSION_FACTOR;
        inputs.hasHitForwardLimit = motor.getFault_ForwardSoftLimit().getValue();
        inputs.hasHitBackwardsLimit = motor.getFault_ReverseSoftLimit().getValue();
    }
}
