package edu.greenblitz.robotName.subsystems.shooter.Funnel.NeoFunnel;

import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.subsystems.shooter.Funnel.FunnelInputsAutoLogged;
import edu.greenblitz.robotName.subsystems.shooter.Funnel.IFunnel;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;
import edu.wpi.first.math.filter.Debouncer;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.DigitalInput;

import static edu.greenblitz.robotName.subsystems.shooter.Funnel.NeoFunnel.NeoFunnelConstants.*;

public class NeoFunnel implements IFunnel {
	
	private GBSparkMax motor;

	private Debouncer debouncer;
	
	private DigitalInput beamBreaker;

	public NeoFunnel() {
		motor = new GBSparkMax(MOTOR_ID, CANSparkMax.MotorType.kBrushless);
		motor.config(FUNNEL_CONFIG_OBJECT);
		beamBreaker = new DigitalInput(BEAM_BREAKER_CHANNEL);
		debouncer = new Debouncer(DEBOUNCE_TIME_FOR_LIMIT_SWITCH);
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
	public void moveToPosition(Rotation2d position) {
		motor.getPIDController().setReference(position.getRotations(), CANSparkMax.ControlType.kPosition);
	}

	@Override
	public void updateInputs(FunnelInputsAutoLogged inputs) {
		inputs.outputCurrent = motor.getOutputCurrent();
		inputs.appliedOutput = motor.getAppliedOutput();
		inputs.temperature = motor.getMotorTemperature();
		inputs.isObjectIn = debouncer.calculate(beamBreaker.get());
		inputs.position = Rotation2d.fromRotations(motor.getEncoder().getPosition());
	}
}
