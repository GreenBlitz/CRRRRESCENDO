package edu.greenblitz.robotName.subsystems.Arm.EndEffector.WristUtils.NeoWrist;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.SparkMaxAbsoluteEncoder;
import edu.greenblitz.robotName.subsystems.Arm.EndEffector.WristUtils.IWrist;
import edu.greenblitz.robotName.subsystems.Arm.EndEffector.WristUtils.WristConstants;
import edu.greenblitz.robotName.subsystems.Arm.EndEffector.WristUtils.WristInputsAutoLogged;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;
import edu.wpi.first.math.filter.Debouncer;
import edu.wpi.first.wpilibj.DigitalInput;

import static edu.greenblitz.robotName.subsystems.Arm.EndEffector.WristUtils.NeoWrist.NeoWristConstants.BEAM_BREAKER_CHANNEL;
import static edu.greenblitz.robotName.subsystems.Arm.EndEffector.WristUtils.WristConstants.RELATIVE_POSITION_CONVERSION_FACTOR;
import static edu.greenblitz.robotName.subsystems.Arm.EndEffector.WristUtils.WristConstants.RELATIVE_VELOCITY_CONVERSION_FACTOR;

public class NeoWrist implements IWrist {

    private final GBSparkMax motor;

    private final Debouncer debouncer;

    private final DigitalInput beamBreaker;

    public NeoWrist() {
        motor = new GBSparkMax(NeoWristConstants.MOTOR_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
        motor.config(NeoWristConstants.WRIST_CONFIG_OBJECT);

        motor.getAbsoluteEncoder(SparkMaxAbsoluteEncoder.Type.kDutyCycle).setPositionConversionFactor(WristConstants.ABSOLUTE_POSITION_CONVERSION_FACTOR);
        motor.getAbsoluteEncoder(SparkMaxAbsoluteEncoder.Type.kDutyCycle).setVelocityConversionFactor(WristConstants.ABSOLUTE_VELOCITY_CONVERSION_FACTOR);

        motor.getEncoder().setPositionConversionFactor(RELATIVE_POSITION_CONVERSION_FACTOR);
        motor.getEncoder().setVelocityConversionFactor(RELATIVE_VELOCITY_CONVERSION_FACTOR);

        motor.getPIDController().setFeedbackDevice(motor.getAbsoluteEncoder(SparkMaxAbsoluteEncoder.Type.kDutyCycle));
        motor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
        motor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);
        motor.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, WristConstants.BACKWARD_ANGLE_LIMIT);
        motor.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, WristConstants.FORWARD_ANGLE_LIMIT);

        beamBreaker = new DigitalInput(BEAM_BREAKER_CHANNEL);
        debouncer = new Debouncer(NeoWristConstants.DEBOUNCE_TIME_FOR_LIMIT_SWITCH);
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
    public void resetAngle(double position) {
        motor.getEncoder().setPosition(position);
    }

    @Override
    public void moveToAngle(double goalAngle) {
        motor.getPIDController().setReference(goalAngle, CANSparkMax.ControlType.kPosition, 0, motor.getPIDController().getFF());
    }

    @Override
    public void updateInputs(WristInputsAutoLogged inputs) {
        inputs.appliedOutput = motor.getAppliedOutput();
        inputs.outputCurrent = motor.getOutputCurrent();
        inputs.position = motor.getEncoder().getPosition();
        inputs.velocity = motor.getEncoder().getVelocity();
        inputs.absoluteEncoderPosition = motor.getAbsoluteEncoder(SparkMaxAbsoluteEncoder.Type.kDutyCycle).getPosition();
        inputs.temperature = motor.getMotorTemperature();
        inputs.hasReachedBackwardLimit = inputs.position == WristConstants.BACKWARD_ANGLE_LIMIT;
        inputs.hasReachedForwardLimit = inputs.position == WristConstants.FORWARD_ANGLE_LIMIT;
        inputs.isObjectInArm = debouncer.calculate(beamBreaker.get());
    }

}
