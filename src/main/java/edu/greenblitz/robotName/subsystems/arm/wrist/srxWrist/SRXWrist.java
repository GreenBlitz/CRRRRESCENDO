package edu.greenblitz.robotName.subsystems.arm.wrist.srxWrist;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.Battery;
import edu.greenblitz.robotName.subsystems.arm.roller.Roller;
import edu.greenblitz.robotName.subsystems.arm.wrist.IWrist;
import edu.greenblitz.robotName.subsystems.arm.wrist.WristInputsAutoLogged;
import edu.greenblitz.robotName.utils.Conversions;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SRXWrist implements IWrist {

    private TalonSRX motor;

    public SRXWrist() {
        motor = new TalonSRX(SRXWristConstants.MOTOR_ID);

        motor.configAllSettings(SRXWristConstants.TALON_SRX_CONFIGURATION);
        motor.configSelectedFeedbackSensor(
                FeedbackDevice.CTRE_MagEncoder_Relative,
                SRXWristConstants.PID_SLOT,
                SRXWristConstants.TIMEOUT_FOR_CONFIG_SET
        );
        resetAngle(Rotation2d.fromDegrees(180));
    }

    @Override
    public void setPower(double power) {
        motor.set(TalonSRXControlMode.PercentOutput, power);
    }

    @Override
    public void setVoltage(double voltage) {
        setPower(voltage / Battery.getInstance().getCurrentVoltage());
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
        int PID_SLOT = Roller.getInstance().isObjectIn() ? 1 : 0;
        motor.selectProfileSlot(PID_SLOT, 0);
        motor.set(ControlMode.Position, targetAngle.getRotations() * SRXWristConstants.MAG_ENCODER_CONVERSION_FACTOR);
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
