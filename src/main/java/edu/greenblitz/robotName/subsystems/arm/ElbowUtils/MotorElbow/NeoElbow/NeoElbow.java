package edu.greenblitz.robotName.subsystems.arm.ElbowUtils.MotorElbow.NeoElbow;

import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.SparkMaxAbsoluteEncoder;
import edu.greenblitz.robotName.subsystems.Battery;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.ElbowInputsAutoLogged;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.IElbow;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.MotorElbow.MotorElbowConstants;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Rotation2d;

import static edu.greenblitz.robotName.subsystems.arm.ElbowUtils.MotorElbow.NeoElbow.NeoElbowConstants.PID_SLOT;


public class NeoElbow implements IElbow {

    GBSparkMax motor;

    public NeoElbow(){
        motor = new GBSparkMax(MotorElbowConstants.MOTOR_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
        motor.config(NeoElbowConstants.ELBOW_CONFIG_OBJECT);

        motor.getAbsoluteEncoder(SparkMaxAbsoluteEncoder.Type.kDutyCycle).setPositionConversionFactor(ElbowConstants.ABSOLUTE_POSITION_CONVERSION_FACTOR);
        motor.getAbsoluteEncoder(SparkMaxAbsoluteEncoder.Type.kDutyCycle).setVelocityConversionFactor(ElbowConstants.ABSOLUTE_VELOCITY_CONVERSION_FACTOR);

        motor.getPIDController().setFeedbackDevice(motor.getAbsoluteEncoder(SparkMaxAbsoluteEncoder.Type.kDutyCycle));
        motor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
        motor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);
        motor.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, ElbowConstants.BACKWARD_ANGLE_LIMIT.getRadians());
        motor.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, ElbowConstants.FORWARD_ANGLE_LIMIT.getRadians());
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
        CANSparkMax.IdleMode neoIdleMode = (idleMode == NeutralModeValue.Brake ? CANSparkMax.IdleMode.kBrake : CANSparkMax.IdleMode.kCoast);
        motor.setIdleMode(neoIdleMode);
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
    public SimpleMotorFeedforward getFeedForward() {
        return NeoElbowConstants.SIMPLE_MOTOR_FEED_FORWARD;
    }

    @Override
    public void updateInputs(ElbowInputsAutoLogged inputs) {
        inputs.appliedOutput = motor.getAppliedOutput() *  Battery.getInstance().getCurrentVoltage();
        inputs.outputCurrent = motor.getOutputCurrent();
        inputs.position = motor.getEncoder().getPosition();
        inputs.velocity = motor.getEncoder().getVelocity();
        inputs.absoluteEncoderPosition = motor.getAbsoluteEncoder(SparkMaxAbsoluteEncoder.Type.kDutyCycle).getPosition();
        inputs.temperature = motor.getMotorTemperature();
        inputs.hasReachedBackwardLimit = Math.abs(inputs.position - ElbowConstants.BACKWARD_ANGLE_LIMIT.getRadians()) <= ElbowConstants.TOLERANCE;
        inputs.hasReachedForwardLimit = Math.abs(inputs.position - ElbowConstants.FORWARD_ANGLE_LIMIT.getRadians()) <= ElbowConstants.TOLERANCE;
    }
}
