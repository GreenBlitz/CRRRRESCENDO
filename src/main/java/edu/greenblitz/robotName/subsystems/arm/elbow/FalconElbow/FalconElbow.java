package edu.greenblitz.robotName.subsystems.arm.elbow.FalconElbow;

import com.ctre.phoenix6.controls.MotionMagicDutyCycle;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowInputsAutoLogged;
import edu.greenblitz.robotName.subsystems.arm.elbow.IElbow;
import edu.wpi.first.math.geometry.Rotation2d;

public class FalconElbow implements IElbow {

    private TalonFX motor;

    private ElbowInputsAutoLogged lastInputs;

    public FalconElbow() {
        lastInputs = new ElbowInputsAutoLogged();

        motor = new TalonFX(FalconElbowConstants.MOTOR_ID);
        motor.getConfigurator().apply(FalconElbowConstants.TALON_FX_CONFIGURATION);
        motor.setNeutralMode(FalconElbowConstants.NEUTRAL_MODE_VALUE);
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
    public void resetAngle(Rotation2d position) {
        motor.setPosition(position.getRadians());
    }

    @Override
    public void moveToAngle(Rotation2d targetAngle) {
        motor.setControl(
                new MotionMagicDutyCycle(
                        targetAngle.getRadians() / ElbowConstants.GEAR_RATIO,
                        true,
                        FalconElbowConstants.ELBOW_FEED_FORWARD.calculate(lastInputs.position.getRadians(), lastInputs.velocity * ElbowConstants.GEAR_RATIO),
                        FalconElbowConstants.MOTION_MAGIC_PID_SLOT,
                        true,
                        true,
                        true
                )
        );
    }

    @Override
    public void updateInputs(ElbowInputsAutoLogged inputs) {
        inputs.outputCurrent = motor.getSupplyCurrent().getValue();
        inputs.appliedOutput = motor.getMotorVoltage().getValue();
        inputs.position = Rotation2d.fromRadians(motor.getPosition().getValue() * ElbowConstants.GEAR_RATIO);
        inputs.velocity = motor.getVelocity().getValue() * ElbowConstants.GEAR_RATIO;
        inputs.absoluteEncoderPosition = motor.getDutyCycle().getValue() * ElbowConstants.GEAR_RATIO;
        inputs.temperature = motor.getDeviceTemp().getValue();
        inputs.hasReachedForwardLimit = motor.getFault_ForwardSoftLimit().getValue();
        inputs.hasReachedBackwardLimit = motor.getFault_ReverseSoftLimit().getValue();

        lastInputs = inputs;
    }
}
