package edu.greenblitz.robotName.subsystems.lifter.neoLifter;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.subsystems.lifter.ILifter;
import edu.greenblitz.robotName.subsystems.lifter.LifterConstants;
import edu.greenblitz.robotName.subsystems.lifter.LifterInputsAutoLogged;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;
import edu.wpi.first.math.geometry.Rotation2d;

public class NeoLifter implements ILifter {
	
	private GBSparkMax motor;
	
	public NeoLifter() {
		motor = new GBSparkMax(NeoLifterConstants.MOTOR_ID, CANSparkLowLevel.MotorType.kBrushless);
		motor.config(NeoLifterConstants.CONFIG);
		
		motor.getReverseLimitSwitch(NeoLifterConstants.BACKWARD_LIMIT_SWITCH_TYPE)
				.enableLimitSwitch(NeoLifterConstants.IS_BACKWARD_LIMIT_SWITCH_ENABLED);
		motor.getForwardLimitSwitch(NeoLifterConstants.FORWARD_LIMIT_SWITCH_TYPE)
				.enableLimitSwitch(NeoLifterConstants.IS_FORWARD_LIMIT_SWITCH_ENABLED);
		
		motor.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, LifterConstants.BACKWARD_LIMIT.getRadians());
		motor.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, LifterConstants.FORWARD_LIMIT.getRadians());
		motor.setIdleMode(CANSparkMax.IdleMode.kBrake);
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
	public void resetEncoder(Rotation2d position) {
		motor.getEncoder().setPosition(position.getRadians());
	}
	
	@Override
	public void stopMotor() {
		this.setPower(0);
	}
	
	@Override
	public void setIdleMode(CANSparkMax.IdleMode idleMode) {
		motor.setIdleMode(idleMode);
	}
	
	@Override
	public void goToPosition(Rotation2d position) {
		motor.getPIDController().setReference(
				position.getRadians(),
				CANSparkMax.ControlType.kPosition,
				NeoLifterConstants.PID_SLOT,
				NeoLifterConstants.FEED_FORWARD.calculate(position.getRadians())
		);
	}
	
	@Override
	public void updateInputs(LifterInputsAutoLogged inputs) {
		inputs.appliedOutput = motor.getAppliedOutput();
		inputs.outputCurrent = motor.getOutputCurrent();
		inputs.position = motor.getEncoder().getPosition();
		inputs.velocity = motor.getEncoder().getVelocity();
		inputs.isForwardSwitchPressed = motor.getForwardLimitSwitch(NeoLifterConstants.FORWARD_LIMIT_SWITCH_TYPE).isPressed();
		inputs.isBackwardSwitchPressed = motor.getReverseLimitSwitch(NeoLifterConstants.BACKWARD_LIMIT_SWITCH_TYPE).isPressed();
	}
}