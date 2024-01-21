package edu.greenblitz.robotName.subsystems.Arm.EndEffector;

import com.ctre.phoenix6.controls.MotionMagicDutyCycle;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.SparkMaxAbsoluteEncoder;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;
import edu.wpi.first.math.controller.PIDController;

import static edu.greenblitz.robotName.subsystems.Arm.EndEffector.EndEffectorConstants.RELATIVE_POSITION_CONVERSION_FACTOR;
import static edu.greenblitz.robotName.subsystems.Arm.EndEffector.EndEffectorConstants.RELATIVE_VELOCITY_CONVERSION_FACTOR;

public class NeoEndeffector implements IEndEffector {

    GBSparkMax motor;

    public NeoEndeffector() {
        motor = new GBSparkMax(EndEffectorConstants.NeoConfigs.MOTOR_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
        motor.config(EndEffectorConstants.NeoConfigs.ELBOW_CONFIG_OBJECT);

        motor.getAbsoluteEncoder(SparkMaxAbsoluteEncoder.Type.kDutyCycle).setPositionConversionFactor(EndEffectorConstants.ABSOLUTE_POSITION_CONVERSION_FACTOR);
        motor.getAbsoluteEncoder(SparkMaxAbsoluteEncoder.Type.kDutyCycle).setVelocityConversionFactor(EndEffectorConstants.ABSOLUTE_VELOCITY_CONVERSION_FACTOR);

        motor.getEncoder().setPositionConversionFactor(RELATIVE_POSITION_CONVERSION_FACTOR);
        motor.getEncoder().setVelocityConversionFactor(RELATIVE_VELOCITY_CONVERSION_FACTOR);

        motor.getPIDController().setFeedbackDevice(motor.getAbsoluteEncoder(SparkMaxAbsoluteEncoder.Type.kDutyCycle));
        motor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
        motor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);
        motor.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, EndEffectorConstants.BACKWARD_ANGLE_LIMIT);
        motor.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, EndEffectorConstants.FORWARD_ANGLE_LIMIT);

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
    public void updateInputs(EndEffectorInputsAutoLogged inputs) {
        inputs.appliedOutput = motor.getAppliedOutput();
        inputs.outputCurrent = motor.getOutputCurrent();
        inputs.position = motor.getEncoder().getPosition();
        inputs.velocity = motor.getEncoder().getVelocity();
        inputs.absoluteEncoderPosition = motor.getAbsoluteEncoder(SparkMaxAbsoluteEncoder.Type.kDutyCycle).getPosition();
        inputs.hasHitBackwardsLimit = inputs.position == EndEffectorConstants.BACKWARD_ANGLE_LIMIT;
        inputs.hasHitForwardLimit = inputs.position == EndEffectorConstants.FORWARD_ANGLE_LIMIT;
        inputs.kP = motor.getPIDController().getP();
        inputs.kI = motor.getPIDController().getI();
        inputs.kD = motor.getPIDController().getD();
    }

}
