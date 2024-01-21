package edu.greenblitz.robotName.subsystems.Arm.Wrist;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.SparkMaxAbsoluteEncoder;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;
import edu.wpi.first.math.filter.Debouncer;

import static edu.greenblitz.robotName.subsystems.Arm.Wrist.WristConstants.NeoConfigs.SWITCH_TYPE;
import static edu.greenblitz.robotName.subsystems.Arm.Wrist.WristConstants.RELATIVE_POSITION_CONVERSION_FACTOR;
import static edu.greenblitz.robotName.subsystems.Arm.Wrist.WristConstants.RELATIVE_VELOCITY_CONVERSION_FACTOR;

public class NeoWrist implements IWrist {

    GBSparkMax motor;

    private Debouncer debouncer;
    public NeoWrist() {
        motor = new GBSparkMax(WristConstants.NeoConfigs.MOTOR_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
        motor.config(WristConstants.NeoConfigs.WRIST_CONFIG_OBJECT);

        motor.getAbsoluteEncoder(SparkMaxAbsoluteEncoder.Type.kDutyCycle).setPositionConversionFactor(WristConstants.ABSOLUTE_POSITION_CONVERSION_FACTOR);
        motor.getAbsoluteEncoder(SparkMaxAbsoluteEncoder.Type.kDutyCycle).setVelocityConversionFactor(WristConstants.ABSOLUTE_VELOCITY_CONVERSION_FACTOR);

        motor.getEncoder().setPositionConversionFactor(RELATIVE_POSITION_CONVERSION_FACTOR);
        motor.getEncoder().setVelocityConversionFactor(RELATIVE_VELOCITY_CONVERSION_FACTOR);

        motor.getPIDController().setFeedbackDevice(motor.getAbsoluteEncoder(SparkMaxAbsoluteEncoder.Type.kDutyCycle));
        motor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
        motor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);
        motor.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, WristConstants.BACKWARD_ANGLE_LIMIT);
        motor.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, WristConstants.FORWARD_ANGLE_LIMIT);

        debouncer = new Debouncer(WristConstants.NeoConfigs.DEBOUNCE_TIME_FOR_LIMIT_SWITCH);

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
        inputs.hasHitBackwardsLimit = inputs.position == WristConstants.BACKWARD_ANGLE_LIMIT;
        inputs.hasHitForwardLimit = inputs.position == WristConstants.FORWARD_ANGLE_LIMIT;
        inputs.isObjectInside = debouncer.calculate(motor.getReverseLimitSwitch(SWITCH_TYPE).isPressed());
        inputs.kP = motor.getPIDController().getP();
        inputs.kI = motor.getPIDController().getI();
        inputs.kD = motor.getPIDController().getD();
    }

}
