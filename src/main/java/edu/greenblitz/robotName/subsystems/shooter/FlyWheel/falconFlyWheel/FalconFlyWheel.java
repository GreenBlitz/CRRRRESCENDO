package edu.greenblitz.robotName.subsystems.shooter.FlyWheel.falconFlyWheel;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelInputsAutoLogged;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.IFlyWheel;
import edu.greenblitz.robotName.utils.motors.GBTalonFXPro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static edu.greenblitz.robotName.subsystems.shooter.FlyWheel.falconFlyWheel.FalconFlyWheelConstants.leftMotorConstants;
import static edu.greenblitz.robotName.subsystems.shooter.FlyWheel.falconFlyWheel.FalconFlyWheelConstants.rightMotorConstants;

public class FalconFlyWheel implements IFlyWheel {
	
	private GBTalonFXPro rightMotor;
	
	private GBTalonFXPro leftMotor;
	
	private VelocityVoltage rightMotorVelocityVoltage = new VelocityVoltage(0)
			.withSlot(rightMotorConstants.PID_SLOT)
			.withEnableFOC(rightMotorConstants.ENABLE_FOC),
			leftMotorVelocityVoltage = new VelocityVoltage(0)
					.withSlot(leftMotorConstants.PID_SLOT)
					.withEnableFOC(leftMotorConstants.ENABLE_FOC);
	
	public FalconFlyWheel() {
		rightMotor = new GBTalonFXPro(rightMotorConstants.MOTOR_ID, rightMotorConstants.CANBUS_CHAIN);
		rightMotor.applyConfiguration(rightMotorConstants.CONFIGURATION);
		rightMotor.setNeutralMode(rightMotorConstants.NEUTRAL_MODE_VALUE);
		optimizeCanBusUtilization(rightMotor);
		
		leftMotor = new GBTalonFXPro(leftMotorConstants.MOTOR_ID, leftMotorConstants.CANBUS_CHAIN);
		leftMotor.applyConfiguration(leftMotorConstants.CONFIGURATION);
		leftMotor.setNeutralMode(leftMotorConstants.NEUTRAL_MODE_VALUE);
		optimizeCanBusUtilization(leftMotor);
	}
	
	public void optimizeCanBusUtilization(TalonFX motor) {
		BaseStatusSignal.setUpdateFrequencyForAll(
				RobotConstants.General.Motors.SIGNAL_FREQUENCY_HERTZ,
				motor.getVelocity(),
				motor.getMotorVoltage(),
				motor.getSupplyCurrent(),
				motor.getAcceleration()
		);
		motor.optimizeBusUtilization();
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
		inputs.leftFlywheelVoltage = leftMotor.getMotorVoltage().getValue();
		inputs.leftFlywheelVelocity = leftMotor.getLatencyCompensatedValue(leftMotor.getVelocity(), leftMotor.getAcceleration());
		inputs.leftWheelAcceleration = leftMotor.getAcceleration().getValue();
		
		inputs.rightFlywheelCurrent = rightMotor.getSupplyCurrent().getValue();
		inputs.rightFlywheelVoltage = rightMotor.getMotorVoltage().getValue();
		inputs.rightFlywheelVelocity = rightMotor.getLatencyCompensatedValue(rightMotor.getVelocity(), rightMotor.getAcceleration());
		inputs.rightWheelAcceleration = rightMotor.getAcceleration().getValue();
		
		inputs.rightVelocityReferance = rightMotor.getClosedLoopReference().getValue();
		inputs.leftVelocityReferance = leftMotor.getClosedLoopReference().getValue();
	}
}
