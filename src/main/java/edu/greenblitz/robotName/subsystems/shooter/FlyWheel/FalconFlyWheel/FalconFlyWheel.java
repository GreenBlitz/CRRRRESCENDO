package edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FalconFlyWheel;


import com.ctre.phoenix6.controls.MotionMagicVelocityDutyCycle;
import com.ctre.phoenix6.controls.VelocityDutyCycle;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.IFlyWheel;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.NeoFlyWheel.NeoFlyWheelConstants;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelInputsAutoLogged;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.IFlyWheel;
import edu.greenblitz.robotName.utils.motors.GBTalonFXPro;


import static edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FalconFlyWheel.FalconFlyWheelConstants.*;


public class FalconFlyWheel implements IFlyWheel {

    private GBTalonFXPro rightMotor;

    private GBTalonFXPro leftMotor;

    private MotionMagicVelocityDutyCycle rightMotorVelocityVoltage = new MotionMagicVelocityDutyCycle(0).withEnableFOC(true),
            leftMotorVelocityVoltage = new MotionMagicVelocityDutyCycle(0).withEnableFOC(true);

    public FalconFlyWheel() {
        rightMotor = new GBTalonFXPro(rightMotorConstants.ID);
        rightMotor.getConfigurator().apply(rightMotorConstants.CONFIGURATION);

        rightMotor = new GBTalonFXPro(leftMotorConstants.ID);
        rightMotor.getConfigurator().apply(leftMotorConstants.CONFIGURATION);

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
        rightMotor.setControl(rightMotorVelocityVoltage.withVelocity(rightVelocity));
        leftMotor.setControl(leftMotorVelocityVoltage.withVelocity(leftVelocity));
    }

    @Override
    public void updateInputs(FlyWheelInputsAutoLogged inputs) {
        inputs.leftFlywheelCurrent = leftMotor.getSupplyCurrent().getValue();
        inputs.rightFlywheelCurrent = rightMotor.getSupplyCurrent().getValue();
        inputs.leftFlywheelVoltage = leftMotor.getMotorVoltage().getValue();
        inputs.rightFlywheelVoltage = rightMotor.getMotorVoltage().getValue();
        inputs.leftFlywheelVelocity = leftMotor.getVelocity().getValue();
        inputs.rightFlywheelVelocity = rightMotor.getVelocity().getValue();
        inputs.leftWheelAcceleration = leftMotor.getAcceleration().getValue();
        inputs.rightWheelAcceleration = rightMotor.getAcceleration().getValue();
    }
}
