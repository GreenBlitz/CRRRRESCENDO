package edu.greenblitz.robotName.subsystems.shooter.FlyWheel.NeoFlyWheel;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.greenblitz.robotName.subsystems.Battery;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelInputsAutoLogged;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.IFlyWheel;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;


public class NeoFlyWheel implements IFlyWheel {
    private GBSparkMax rightMotor, leftMotor;

    public NeoFlyWheel() {
        rightMotor = new GBSparkMax(NeoFlyWheelConstants.RightMotor.MOTOR_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
        leftMotor = new GBSparkMax(NeoFlyWheelConstants.LeftMotor.MOTOR_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
    }

    @Override
    public void setPower(double rightPower, double leftPower) {

    }

    @Override
    public void setVoltage(double rightVoltage, double leftVoltage) {
        rightMotor.setVoltage(rightVoltage);
        leftMotor.setVoltage(leftVoltage);
    }

    @Override
    public void setVelocity(double rightVelocity, double leftVelocity) {
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
    }
}
