package edu.greenblitz.robotName.subsystems.Arm.Elbow;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.SparkMaxAbsoluteEncoder;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;

public class NeoElbow implements IElbow {



    GBSparkMax motor;

    public NeoElbow() {
        motor = new GBSparkMax(RobotMap.TelescopicArm.Elbow.MOTOR_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
        motor.config(RobotMap.TelescopicArm.Elbow.ELBOW_CONFIG_OBJECT);

        motor.getAbsoluteEncoder(SparkMaxAbsoluteEncoder.Type.kDutyCycle).setPositionConversionFactor(RobotMap.TelescopicArm.Elbow.ABSOLUTE_POSITION_CONVERSION_FACTOR);
        motor.getAbsoluteEncoder(SparkMaxAbsoluteEncoder.Type.kDutyCycle).setVelocityConversionFactor(RobotMap.TelescopicArm.Elbow.ABSOLUTE_VELOCITY_CONVERSION_FACTOR);

        motor.getEncoder().setPositionConversionFactor(RELATIVE_POSITION_CONVERSION_FACTOR);//not the actual gear ratio, weird estimation
        motor.getEncoder().setVelocityConversionFactor(RELATIVE_VELOCITY_CONVERSION_FACTOR);

        motor.getPIDController().setFeedbackDevice(motor.getAbsoluteEncoder(SparkMaxAbsoluteEncoder.Type.kDutyCycle));
        motor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
        motor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);
        motor.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, RobotMap.TelescopicArm.Elbow.BACKWARD_ANGLE_LIMIT);
        motor.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, RobotMap.TelescopicArm.Elbow.FORWARD_ANGLE_LIMIT);

    }

    @Override
    public void setPosition(double position) {
        motor.getEncoder().setPosition(position);
    }

    @Override
    public void setPower(double power) {
        motor.set(power);
    }


    @Override
    public void setAngleRadiansByPID(double goalAngle, double feedForward) {
        motor.getPIDController().setReference(goalAngle, CANSparkMax.ControlType.kPosition, 0, feedForward);
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
    public void setSoftLimit(CANSparkMax.SoftLimitDirection direction, double limit) {
        motor.setSoftLimit(direction, limit);
    }

    @Override
    public void updateInputs(ElbowInputsAutoLogged inputs) {
        inputs.appliedOutput = motor.getAppliedOutput();
        inputs.outputCurrent = motor.getOutputCurrent();
        inputs.position = motor.getEncoder().getPosition();
        inputs.velocity = motor.getEncoder().getVelocity();
        inputs.absoluteEncoderPosition = motor.getAbsoluteEncoder(SparkMaxAbsoluteEncoder.Type.kDutyCycle).getPosition();
        inputs.absoluteEncoderVelocity = motor.getAbsoluteEncoder(SparkMaxAbsoluteEncoder.Type.kDutyCycle).getVelocity();

        inputs.hasHitBackwardsLimit = inputs.position == RobotMap.TelescopicArm.Elbow.BACKWARD_ANGLE_LIMIT;
        inputs.hasHitForwardLimit = inputs.position == RobotMap.TelescopicArm.Elbow.FORWARD_ANGLE_LIMIT;

        inputs.kP = motor.getPIDController().getP();
        inputs.kI = motor.getPIDController().getI();
        inputs.kD = motor.getPIDController().getD();
    }


}
