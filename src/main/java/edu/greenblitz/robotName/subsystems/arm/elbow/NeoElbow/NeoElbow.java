package edu.greenblitz.robotName.subsystems.arm.elbow.NeoElbow;

import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.*;
import edu.greenblitz.robotName.subsystems.Battery;
import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowInputsAutoLogged;
import edu.greenblitz.robotName.subsystems.arm.elbow.IElbow;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;
import edu.wpi.first.math.geometry.Rotation2d;

import static edu.greenblitz.robotName.subsystems.arm.elbow.NeoElbow.NeoElbowConstants.ELBOW_FEEDFORWARD;


public class NeoElbow implements IElbow {

    private GBSparkMax motor;

    private ElbowInputsAutoLogged lastInputs;

    public NeoElbow(){
        motor = new GBSparkMax(NeoElbowConstants.MOTOR_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
        motor.config(NeoElbowConstants.ELBOW_CONFIG_OBJECT);

        motor.getPIDController().setFeedbackDevice(motor.getEncoder());
        motor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
        motor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);
        motor.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, ElbowConstants.BACKWARD_ANGLE_LIMIT.getRadians());
        motor.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, ElbowConstants.FORWARD_ANGLE_LIMIT.getRadians());
        motor.getReverseLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen).enableLimitSwitch(NeoElbowConstants.ENABLE_REVERSE_LIMIT_SWITCH);
        motor.getForwardLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen).enableLimitSwitch(NeoElbowConstants.ENABLE_FORWARD_LIMIT_SWITCH);

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
        motor.setIdleModeByNeutralMode(idleMode);
    }

    @Override
    public void resetAngle(Rotation2d position) {
        motor.getEncoder().setPosition(position.getRadians());
    }

    @Override
    public void moveToAngle(Rotation2d targetAngle) {
        motor.getPIDController().setReference(
                targetAngle.getRadians(),
                CANSparkMax.ControlType.kPosition,
                NeoElbowConstants.PID_SLOT,
                ELBOW_FEEDFORWARD.calculate(targetAngle.getRadians(), 0)
        );
    }

    @Override
    public void updateInputs(ElbowInputsAutoLogged inputs) {
        inputs.appliedOutput = motor.getAppliedOutput() *  Battery.getInstance().getCurrentVoltage();
        inputs.outputCurrent = motor.getOutputCurrent();
        inputs.position = Rotation2d.fromRadians(motor.getEncoder().getPosition());
        inputs.velocity = motor.getEncoder().getVelocity();
        inputs.absoluteEncoderPosition = motor.getAbsoluteEncoder(SparkMaxAbsoluteEncoder.Type.kDutyCycle).getPosition();
        inputs.temperature = motor.getMotorTemperature();
        inputs.hasReachedBackwardLimit = Math.abs(inputs.position.getRadians() - ElbowConstants.BACKWARD_ANGLE_LIMIT.getRadians()) <= ElbowConstants.TOLERANCE;
        inputs.hasReachedForwardLimit = Math.abs(inputs.position.getRadians() - ElbowConstants.FORWARD_ANGLE_LIMIT.getRadians()) <= ElbowConstants.TOLERANCE;

        lastInputs = inputs;
    }
}
