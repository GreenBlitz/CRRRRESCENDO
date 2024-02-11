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
	public void updateInputs(FunnelInputsAutoLogged inputs) {
		inputs.outputCurrent = motor.getOutputCurrent();
		inputs.appliedOutput = motor.getAppliedOutput();
		inputs.isObjectIn = debouncer.calculate(beamBreaker.get());
	}
}
