package edu.greenblitz.robotName.subsystems.shooter.funnel.neoFunnel;

import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import edu.greenblitz.robotName.subsystems.shooter.funnel.FunnelInputsAutoLogged;
import edu.greenblitz.robotName.subsystems.shooter.funnel.IFunnel;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;
import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.filter.Debouncer;

public class NeoFunnel implements IFunnel {
	
	private GBSparkMax motor;
	
	private Debouncer debouncer;
	
	private PIDController pidController;

	public NeoFunnel() {
		motor = new GBSparkMax(NeoFunnelConstants.MOTOR_ID, CANSparkMax.MotorType.kBrushless);
		motor.config(NeoFunnelConstants.FUNNEL_CONFIG_OBJECT);
		motor.getReverseLimitSwitch(NeoFunnelConstants.BEAM_BREAKER_TYPE)
				.enableLimitSwitch(NeoFunnelConstants.IS_BEAM_BREAKER_ENABLE);
		debouncer = new Debouncer(NeoFunnelConstants.DEBOUNCE_TIME_FOR_LIMIT_SWITCH);
		motor.getPIDController().setP(NeoFunnelConstants.kP, NeoFunnelConstants.VELOCITY_PID_SLOT);
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
	public void setVelocity(double velocity) {
		pidController.setSetpoint(velocity);
		motor.getPIDController().setReference(
				pidController.calculate(motor.getEncoder().getVelocity()),
				CANSparkBase.ControlType.kVelocity,
				0,
				NeoFunnelConstants.SIMPLE_MOTOR_FEED_FORWARD.calculate(velocity)
		);
	}
	
	@Override
	public void updateInputs(FunnelInputsAutoLogged inputs) {
		inputs.outputCurrent = motor.getOutputCurrent();
		inputs.appliedOutput = motor.getAppliedOutput();
		inputs.velocity = motor.getEncoder().getVelocity();
		inputs.isObjectIn = debouncer.calculate(motor.getReverseLimitSwitch(NeoFunnelConstants.BEAM_BREAKER_TYPE).isPressed());
	}
}