package edu.greenblitz.robotName.subsystems.arm.wrist.bagWrist;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.subsystems.arm.wrist.IWrist;
import edu.greenblitz.robotName.subsystems.arm.wrist.WristInputsAutoLogged;
import edu.greenblitz.robotName.utils.Conversions;
import edu.wpi.first.math.geometry.Rotation2d;

public class BagWrist implements IWrist {

    private TalonSRX motor;

    public BagWrist(){
        motor = new TalonSRX(BagWristConstants.MOTOR_ID);
        motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, BagWristConstants.PID_SLOT, BagWristConstants.TIMEOUT_FOR_CONFIG_SET);
        motor.configAllSettings(BagWristConstants.TALON_SRX_CONFIGURATION);
    }

    @Override
    public void setPower(double power) {
        motor.set(TalonSRXControlMode.PercentOutput, power);
    }

    @Override
    public void setVoltage(double voltage) {
        motor.set(TalonSRXControlMode.PercentOutput, voltage / 12);
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
    }

}
