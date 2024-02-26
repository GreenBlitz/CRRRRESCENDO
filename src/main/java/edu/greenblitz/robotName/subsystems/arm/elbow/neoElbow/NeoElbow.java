package edu.greenblitz.robotName.subsystems.arm.elbow.neoElbow;

import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.*;
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
        motor.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, ElbowConstants.BACKWARD_ANGLE_LIMIT.getRotations());
        motor.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, ElbowConstants.FORWARD_ANGLE_LIMIT.getRotations());
        motor.getReverseLimitSwitch(SparkLimitSwitch.Type.kNormallyOpen)
                .enableLimitSwitch(NeoElbowConstants.ENABLE_REVERSE_LIMIT_SWITCH);
        motor.getForwardLimitSwitch(SparkLimitSwitch.Type.kNormallyOpen)
                .enableLimitSwitch(NeoElbowConstants.ENABLE_FORWARD_LIMIT_SWITCH);
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
        SmartDashboard.putNumber("elbow pos refrence", targetAngle.getDegrees());
        double direction = targetAngle.getRotations() >= motor.getEncoder().getPosition() ? 1 : -1;
        SmartDashboard.putNumber("ff", ELBOW_FEEDFORWARD.calculate(targetAngle.getRadians(), direction));
        motor.getPIDController().setReference(
                targetAngle.getRotations(),
                CANSparkMax.ControlType.kPosition,
                NeoElbowConstants.PID_SLOT,
                ELBOW_FEEDFORWARD.calculate(targetAngle.getRadians(), direction)
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
        inputs.hasReachedBackwardLimit = inputs.position.getRadians() <= ElbowConstants.BACKWARD_ANGLE_LIMIT.getRadians();
        inputs.hasReachedForwardLimit = inputs.position.getRadians() >= ElbowConstants.FORWARD_ANGLE_LIMIT.getRadians();

        SmartDashboard.putNumber("elbow pos", inputs.position.getDegrees());
        SmartDashboard.putNumber("voltage elbow", inputs.appliedOutput);
        SmartDashboard.putBoolean("backward limit", inputs.hasReachedBackwardLimit);
        SmartDashboard.putBoolean("forawd limit", inputs.hasReachedForwardLimit);
    }
}