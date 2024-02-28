package edu.greenblitz.robotName.subsystems.arm.elbow.neoElbow;

import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkAbsoluteEncoder;
import com.revrobotics.SparkLimitSwitch;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.Battery;
import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowInputsAutoLogged;
import edu.greenblitz.robotName.subsystems.arm.elbow.IElbow;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static edu.greenblitz.robotName.subsystems.arm.elbow.neoElbow.NeoElbowConstants.ELBOW_FEEDFORWARD;

public class NeoElbow implements IElbow {

    private GBSparkMax motor;

    public NeoElbow() {
        motor = new GBSparkMax(NeoElbowConstants.MOTOR_ID, CANSparkLowLevel.MotorType.kBrushless);
        motor.config(NeoElbowConstants.ELBOW_CONFIG_OBJECT);

        motor.getPIDController().setFeedbackDevice(motor.getEncoder());
        motor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
        motor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);
        motor.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, ElbowConstants.BACKWARD_ANGLE_LIMIT.getRadians());
        motor.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, ElbowConstants.FORWARD_ANGLE_LIMIT.getRadians());
        motor.getReverseLimitSwitch(SparkLimitSwitch.Type.kNormallyOpen)
                .enableLimitSwitch(NeoElbowConstants.ENABLE_REVERSE_LIMIT_SWITCH);
        motor.getForwardLimitSwitch(SparkLimitSwitch.Type.kNormallyOpen)
                .enableLimitSwitch(NeoElbowConstants.ENABLE_FORWARD_LIMIT_SWITCH);

        resetAngle(Rotation2d.fromRotations(motor.getAbsoluteEncoder(SparkAbsoluteEncoder.Type.kDutyCycle).getPosition()));
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
        motor.getEncoder().setPosition(position.getRotations());
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
    public void standInPlace(Rotation2d targetAngle) {
        moveToAngle(targetAngle);
    }

    @Override
    public void updateInputs(ElbowInputsAutoLogged inputs) {
        inputs.appliedOutput = motor.getAppliedOutput() * Battery.getInstance().getCurrentVoltage();
        inputs.outputCurrent = motor.getOutputCurrent();
        inputs.position = Rotation2d.fromRotations(motor.getEncoder().getPosition());
        inputs.acceleration = (motor.getEncoder().getVelocity() - inputs.velocity) / RobotConstants.SimulationConstants.TIME_STEP;
        inputs.velocity = motor.getEncoder().getVelocity();
        inputs.absoluteEncoderPosition = motor.getAbsoluteEncoder(SparkAbsoluteEncoder.Type.kDutyCycle).getPosition();
        inputs.hasReachedBackwardLimit = Math.abs(inputs.position.getRadians() - ElbowConstants.BACKWARD_ANGLE_LIMIT.getRadians()) <= ElbowConstants.TOLERANCE.getRadians();
        inputs.hasReachedForwardLimit = Math.abs(inputs.position.getRadians() - ElbowConstants.FORWARD_ANGLE_LIMIT.getRadians()) <= ElbowConstants.TOLERANCE.getRadians();
        
        SmartDashboard.putNumber("pos elbow", inputs.position.getDegrees());
    }
}