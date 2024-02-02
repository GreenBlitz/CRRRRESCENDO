package edu.greenblitz.robotName.subsystems.arm.EndEffector.WristUtils.NeoWrist;

import com.revrobotics.*;
import edu.greenblitz.robotName.subsystems.Battery;
import edu.greenblitz.robotName.subsystems.arm.EndEffector.WristUtils.IWrist;
import edu.greenblitz.robotName.subsystems.arm.EndEffector.WristUtils.WristConstants;
import edu.greenblitz.robotName.subsystems.arm.EndEffector.WristUtils.WristInputsAutoLogged;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;
import edu.wpi.first.math.geometry.Rotation2d;

import static edu.greenblitz.robotName.subsystems.arm.EndEffector.WristUtils.NeoWrist.NeoWristConstants.PID_SLOT;
import static edu.greenblitz.robotName.subsystems.arm.EndEffector.WristUtils.WristConstants.*;

public class NeoWrist implements IWrist {

    private GBSparkMax motor;


    public NeoWrist() {
        motor = new GBSparkMax(NeoWristConstants.MOTOR_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
        motor.config(NeoWristConstants.WRIST_CONFIG_OBJECT);

        motor.getPIDController().setFeedbackDevice(motor.getEncoder());

        motor.getAbsoluteEncoder(SparkMaxAbsoluteEncoder.Type.kDutyCycle).setPositionConversionFactor(WristConstants.ABSOLUTE_POSITION_CONVERSION_FACTOR);
        motor.getAbsoluteEncoder(SparkMaxAbsoluteEncoder.Type.kDutyCycle).setVelocityConversionFactor(WristConstants.ABSOLUTE_VELOCITY_CONVERSION_FACTOR);

        motor.getEncoder().setPositionConversionFactor(RELATIVE_CONVERSION_FACTOR);
        motor.getEncoder().setVelocityConversionFactor(RELATIVE_CONVERSION_FACTOR);

        motor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
        motor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);
        motor.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, WristConstants.BACKWARD_ANGLE_LIMIT.getRadians());
        motor.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, WristConstants.FORWARD_ANGLE_LIMIT.getRadians());

        motor.getReverseLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen).enableLimitSwitch(NeoWristConstants.IS_REVERSE_LIMIT_SWITCH_ENABLE);
        motor.getForwardLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen).enableLimitSwitch(NeoWristConstants.IS_FORWARD_LIMIT_SWITCH_ENABLE);

        resetAngle(Rotation2d.fromRotations(motor.getAbsoluteEncoder(SparkMaxAbsoluteEncoder.Type.kDutyCycle).getPosition()));
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
    public void setIdleMode(CANSparkMax.IdleMode idleMode) {
        motor.setIdleMode(idleMode);
    }

    @Override
    public void resetAngle(Rotation2d position) {
        motor.getEncoder().setPosition(position.getRadians());
    }

    @Override
    public void moveToAngle(Rotation2d targetAngle) {
        motor.getPIDController().setReference(targetAngle.getRadians(), CANSparkMax.ControlType.kPosition, PID_SLOT, motor.getPIDController().getFF());
    }

    @Override
    public void standInPlace() {
        setVoltage(NeoWristConstants.MOTOR_FEED_FORWARD.calculate(0));
    }

    @Override
    public void updateInputs(WristInputsAutoLogged inputs) {
        inputs.appliedOutput = motor.getAppliedOutput() *  Battery.getInstance().getCurrentVoltage();
        inputs.outputCurrent = motor.getOutputCurrent();
        inputs.position = motor.getEncoder().getPosition();
        inputs.velocity = motor.getEncoder().getVelocity();
        inputs.absoluteEncoderPosition = motor.getAbsoluteEncoder(SparkMaxAbsoluteEncoder.Type.kDutyCycle).getPosition();
        inputs.temperature = motor.getMotorTemperature();
        inputs.hasReachedBackwardLimit = Math.abs(inputs.position - BACKWARD_ANGLE_LIMIT.getRadians()) <= TOLERANCE;
        inputs.hasReachedForwardLimit = Math.abs(inputs.position - FORWARD_ANGLE_LIMIT.getRadians()) <= TOLERANCE;

    }

}
