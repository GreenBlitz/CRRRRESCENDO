package edu.greenblitz.robotName.subsystems.shooter.pivot.falconPivot;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.shooter.pivot.IPivot;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotInputsAutoLogged;
import edu.greenblitz.robotName.utils.motors.GBTalonFXPro;
import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static edu.greenblitz.robotName.RobotConstants.General.Motors.FALCON_REVOLUTIONS_PER_RADIAN;
import static edu.greenblitz.robotName.RobotConstants.General.Motors.IS_SWITCH_CLOSED;

public class FalconPivot implements IPivot {
	
	private GBTalonFXPro motor;
	
	private DutyCycleEncoder absoluteEncoder;
	
	private PositionVoltage positionVoltage;
	
	public FalconPivot() {
		motor = new GBTalonFXPro(FalconPivotConstants.MOTOR_ID, FalconPivotConstants.CANBUS_CHANNEL);
		motor.getConfigurator().apply(FalconPivotConstants.TALON_FX_CONFIGURATION);
		motor.setNeutralMode(FalconPivotConstants.NEUTRAL_MODE_VALUE);
		motor.setInverted(FalconPivotConstants.INVERTED);
		optimizeCanBusUtilization();
		
		absoluteEncoder = new DutyCycleEncoder(FalconPivotConstants.ABSOLUTE_ENCODER_CHANNEL);

		getAbsoluteEncoderPosition();
		resetAngle(getAbsoluteEncoderPosition());

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
		motor.setPosition(position.getRotations());
	}
	
	@Override
	public void moveToAngle(Rotation2d targetAngle) {
		motor.setControl(
				positionVoltage
						.withPosition(targetAngle.getRotations())
						.withSlot(FalconPivotConstants.PID_SLOT)
						.withLimitForwardMotion(false)
						.withLimitReverseMotion(false)
						.withEnableFOC(true)
						.withOverrideBrakeDurNeutral(true)
		);
	}

	public Rotation2d getAbsoluteEncoderPosition() {
		Rotation2d encoderPosition = Rotation2d.fromRotations(absoluteEncoder.getAbsolutePosition() - FalconPivotConstants.ABSOLUTE_ENCODER_OFFSET);
		if (encoderPosition.getRotations() < 0) {
			return Rotation2d.fromRotations(encoderPosition.getRotations() + 1);
		}

		return encoderPosition;
	}
	
	@Override
	public void updateInputs(PivotInputsAutoLogged inputs) {
		inputs.outputCurrent = motor.getSupplyCurrent().getValue();
		inputs.appliedOutput = motor.getMotorVoltage().getValue();
		inputs.position = Rotation2d.fromRotations(motor.getPosition().getValue());
		inputs.velocity = motor.getVelocity().getValue();
		inputs.acceleration = motor.getAcceleration().getValue();
		inputs.absoluteEncoderPosition = getAbsoluteEncoderPosition();
		inputs.temperature = motor.getDeviceTemp().getValue();
		inputs.hasHitForwardLimit = motor.getForwardLimit().getValue().value == IS_SWITCH_CLOSED;
		inputs.hasHitBackwardsLimit = motor.getReverseLimit().getValue().value == IS_SWITCH_CLOSED;

		SmartDashboard.putNumber("abs pos", inputs.absoluteEncoderPosition.getDegrees());
		SmartDashboard.putNumber("position", inputs.position.getDegrees());
	}
}