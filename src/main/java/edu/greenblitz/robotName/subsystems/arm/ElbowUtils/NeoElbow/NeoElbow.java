package edu.greenblitz.robotName.subsystems.arm.ElbowUtils.NeoElbow;

import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.*;
import edu.greenblitz.robotName.subsystems.Battery;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.ElbowInputsAutoLogged;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.IElbow;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Rotation2d;


public class NeoElbow implements IElbow {

    private GBSparkMax motor;

    public NeoElbow(){
        motor = new GBSparkMax(NeoElbowConstants.NEO_MOTOR_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
        motor.config(NeoElbowConstants.ELBOW_CONFIG_OBJECT);

        motor.getPIDController().setFeedbackDevice(motor.getEncoder());
        motor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
        motor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);
        motor.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, ElbowConstants.BACKWARD_ANGLE_LIMIT.getRadians());
        motor.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, ElbowConstants.FORWARD_ANGLE_LIMIT.getRadians());
        motor.getReverseLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen).enableLimitSwitch(NeoElbowConstants.IS_REVERSE_LIMIT_SWITCH_ENABLE);
        motor.getForwardLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen).enableLimitSwitch(NeoElbowConstants.IS_FORWARD_LIMIT_SWITCH_ENABLE);

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
        motor.getPIDController().setReference(targetAngle.getRadians(), CANSparkMax.ControlType.kPosition, NeoElbowConstants.PID_SLOT, motor.getPIDController().getFF());
    }

    @Override
    public void standInPlace() {
        setVoltage(NeoElbowConstants.SIMPLE_MOTOR_FEED_FORWARD.calculate(0));
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
