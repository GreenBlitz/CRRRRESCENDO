package edu.greenblitz.robotName.subsystems.arm.wrist.srxWrist;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.arm.wrist.IWrist;
import edu.greenblitz.robotName.subsystems.arm.wrist.WristInputsAutoLogged;
import edu.greenblitz.robotName.utils.Conversions;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SRXWrist implements IWrist {

    private TalonSRX motor;

    public SRXWrist() {
        motor = new TalonSRX(SRXWristConstants.MOTOR_ID);
        motor.configSelectedFeedbackSensor(
                FeedbackDevice.CTRE_MagEncoder_Absolute,
                SRXWristConstants.PID_SLOT,
                SRXWristConstants.TIMEOUT_FOR_CONFIG_SET
        );
        motor.configAllSettings(SRXWristConstants.TALON_SRX_CONFIGURATION);
    }

    @Override
    public void setPower(double power) {
        motor.set(TalonSRXControlMode.PercentOutput, power);
    }

    @Override
    public void setVoltage(double voltage) {
        setPower(voltage / RobotConstants.SimulationConstants.BATTERY_VOLTAGE);
    }

    @Override
    public void setIdleMode(CANSparkMax.IdleMode idleMode) {
        if (idleMode == CANSparkBase.IdleMode.kBrake)
            motor.setNeutralMode(NeutralMode.Brake);
        else {
            motor.setNeutralMode(NeutralMode.Coast);
        }
    }

    @Override
    public void resetAngle(Rotation2d position) {
        motor.setSelectedSensorPosition(Conversions.MagEncoderConversions.Rotation2DToMotorPosition(position));
    }

    @Override
    public void resetAngleByAbsoluteEncoder() {

    }

    @Override
    public void moveToAngle(Rotation2d targetAngle) {
        SmartDashboard.putNumber("set", targetAngle.getDegrees());
        motor.set(TalonSRXControlMode.Position, targetAngle.getRotations());
    }

    @Override
    public void updateInputs(WristInputsAutoLogged inputs) {
        inputs.appliedOutput = motor.getMotorOutputVoltage();
        inputs.outputCurrent = motor.getSupplyCurrent();
        inputs.absoluteEncoderPosition = Conversions.MagEncoderConversions.MotorPositionToRotation2D(motor.getSelectedSensorPosition());
        inputs.position = Conversions.MagEncoderConversions.MotorPositionToRotation2D(motor.getSelectedSensorPosition());
        inputs.velocity = motor.getSelectedSensorVelocity();
        inputs.hasReachedBackwardLimit = motor.isRevLimitSwitchClosed() == 1;
        inputs.hasReachedForwardLimit = motor.isFwdLimitSwitchClosed() == 1;

        SmartDashboard.putNumber("wrist position", inputs.absoluteEncoderPosition.getDegrees());
    }

}
