package edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FalconFlyWheel;


import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.NeoFlyWheel.NeoFlyWheelConstants;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelInputsAutoLogged;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.IFlyWheel;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.NeoFlyWheel.NeoFlyWheelConstants;


import static edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FalconFlyWheel.FalconFlyWheelConstants.*;


public class FalconFlyWheel implements IFlyWheel {

    private TalonFX rightMotor;

    private TalonFX leftMotor;

    private VelocityVoltage rightMotorVelocityVoltage = new VelocityVoltage(0).withEnableFOC(true),
            leftMotorVelocityVoltage = new VelocityVoltage(0).withEnableFOC(true);

    public FalconFlyWheel() {
        rightMotor = new TalonFX(rightMotorConstants.ID);
        rightMotor.getConfigurator().apply(rightMotorConstants.CURRENT_LIMITS_CONFIGS);
        rightMotor.getConfigurator().apply(rightMotorConstants.CLOSED_LOOP_RAMPS_CONFIGS);
        rightMotor.getConfigurator().apply(rightMotorConstants.MOTION_MAGIC_CONFIGS);
        rightMotor.setNeutralMode(rightMotorConstants.NEUTRAL_MODE_VALUE);

        rightMotor = new TalonFX(leftMotorConstants.ID);
        rightMotor.getConfigurator().apply(leftMotorConstants.CURRENT_LIMITS_CONFIGS);
        rightMotor.getConfigurator().apply(leftMotorConstants.CLOSED_LOOP_RAMPS_CONFIGS);
        rightMotor.getConfigurator().apply(leftMotorConstants.MOTION_MAGIC_CONFIGS);
        rightMotor.setNeutralMode(leftMotorConstants.NEUTRAL_MODE_VALUE);
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

    }
}
