package edu.greenblitz.robotName.subsystems.shooter.pivot.falconPivot;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.shooter.pivot.IPivot;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotInputsAutoLogged;
import edu.greenblitz.robotName.utils.motors.GBTalonFXPro;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.DutyCycleEncoder;

import static edu.greenblitz.robotName.RobotConstants.General.Motors.IS_SWITCH_CLOSED;
import static edu.greenblitz.robotName.subsystems.shooter.pivot.falconPivot.FalconPivotConstants.*;

public class FalconPivot implements IPivot {
	
	private GBTalonFXPro motor;
	
	private DutyCycleEncoder absoluteEncoder;
	
	private PositionVoltage positionVoltage;
	
	public FalconPivot() {
		motor = new GBTalonFXPro(FalconPivotConstants.MOTOR_ID);
		motor.getConfigurator().apply(TALON_FX_CONFIGURATION);
		motor.setNeutralMode(FalconPivotConstants.NEUTRAL_MODE_VALUE);
		optimizeCanBusUtilization();
		
		absoluteEncoder = new DutyCycleEncoder(ABSOLUTE_ENCODER_CHANNEL);
		
		resetAngle(Rotation2d.fromRotations(absoluteEncoder.getAbsolutePosition()));
		
		positionVoltage = new PositionVoltage(motor.getPosition().getValue());
	}
	
	public void optimizeCanBusUtilization() {
		BaseStatusSignal.setUpdateFrequencyForAll(
				RobotConstants.General.Motors.SIGNAL_FREQUENCY_HERTZ,
				motor.getPosition(),
				motor.getVelocity(),
				motor.getMotorVoltage(),
				motor.getSupplyCurrent(),
				motor.getDeviceTemp(),
				motor.getAcceleration(),
				motor.getForwardLimit(),
				motor.getReverseLimit()
		);
		motor.optimizeBusUtilization();
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
		motor.setNeutralMode(idleMode);
	}
	
	@Override
	public void resetAngle(Rotation2d position) {
		motor.setPosition(position.getRadians());
	}
	
	@Override
	public void moveToAngle(Rotation2d targetAngle) {
		motor.setControl(
				positionVoltage
						.withPosition(targetAngle.getRotations())
						.withSlot(FalconPivotConstants.PID_SLOT)
						.withLimitForwardMotion(true)
						.withLimitReverseMotion(true)
						.withEnableFOC(true)
						.withOverrideBrakeDurNeutral(true)
		);
	}
	
	@Override
	public void updateInputs(PivotInputsAutoLogged inputs) {
		inputs.outputCurrent = motor.getSupplyCurrent().getValue();
		inputs.appliedOutput = motor.getMotorVoltage().getValue();
		inputs.position = Rotation2d.fromRotations(motor.getPosition().getValue());
		inputs.velocity = motor.getVelocity().getValue();
		inputs.acceleration = motor.getAcceleration().getValue();
		inputs.absoluteEncoderPosition = absoluteEncoder.getAbsolutePosition();
		inputs.temperature = motor.getDeviceTemp().getValue();
		inputs.hasHitForwardLimit = motor.getForwardLimit().getValue().value == IS_SWITCH_CLOSED;
		inputs.hasHitBackwardsLimit = motor.getReverseLimit().getValue().value == IS_SWITCH_CLOSED;
	}
}