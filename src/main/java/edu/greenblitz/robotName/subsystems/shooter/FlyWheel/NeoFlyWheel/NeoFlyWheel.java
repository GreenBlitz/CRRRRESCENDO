package edu.greenblitz.robotName.subsystems.shooter.FlyWheel.NeoFlyWheel;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelInputsAutoLogged;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.IFlyWheel;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;

public class NeoFlyWheel implements IFlyWheel {

    private GBSparkMax leftMotor;
    private GBSparkMax rightMotor;

    public NeoFlyWheel() {
        rightMotor = new GBSparkMax(NeoFlyWheelConstants.RightMotor.MOTOR_ID, CANSparkLowLevel.MotorType.kBrushless);
        rightMotor.config(NeoFlyWheelConstants.RightMotor.CONFIG);
        leftMotor = new GBSparkMax(NeoFlyWheelConstants.LeftMotor.MOTOR_ID, CANSparkLowLevel.MotorType.kBrushless);
        leftMotor.config(NeoFlyWheelConstants.LeftMotor.CONFIG);
    }

    @Override
    public void setPower(double leftPower, double rightPower) {
        rightMotor.set(rightPower);
        leftMotor.set(leftPower);
    }

    @Override
    public void setVoltage(double leftVoltage, double rightVoltage) {
        rightMotor.setVoltage(rightVoltage);
        leftMotor.setVoltage(leftVoltage);
    }

    @Override
    public void setVelocity(double leftVelocity, double rightVelocity) {
        rightMotor.getPIDController().setReference(
                rightVelocity,
                CANSparkMax.ControlType.kVelocity,
                NeoFlyWheelConstants.RightMotor.PID_SLOT,
                NeoFlyWheelConstants.RightMotor.FEEDFORWARD.calculate(rightVelocity)
        );
        leftMotor.getPIDController().setReference(
                leftVelocity,
                CANSparkMax.ControlType.kVelocity,
                NeoFlyWheelConstants.LeftMotor.PID_SLOT,
                NeoFlyWheelConstants.LeftMotor.FEEDFORWARD.calculate(leftVelocity)
        );
    }

    @Override
    public void updateInputs(FlyWheelInputsAutoLogged inputs) {
        inputs.leftFlywheelCurrent = leftMotor.getOutputCurrent();
        inputs.leftFlywheelTemperature = leftMotor.getMotorTemperature();
        inputs.leftFlywheelVelocity = leftMotor.getEncoder().getVelocity();

        inputs.rightFlywheelCurrent = rightMotor.getOutputCurrent();
        inputs.rightFlywheelTemperature = rightMotor.getMotorTemperature();
        inputs.rightFlywheelVelocity = rightMotor.getEncoder().getVelocity();

    }
}
