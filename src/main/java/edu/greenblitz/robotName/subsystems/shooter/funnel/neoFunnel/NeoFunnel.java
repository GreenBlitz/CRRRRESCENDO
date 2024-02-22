package edu.greenblitz.robotName.subsystems.shooter.funnel.neoFunnel;

import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.subsystems.shooter.funnel.FunnelInputsAutoLogged;
import edu.greenblitz.robotName.subsystems.shooter.funnel.IFunnel;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;
import edu.wpi.first.math.filter.Debouncer;

public class NeoFunnel implements IFunnel {
	
	private GBSparkMax motor;
	
	private Debouncer debouncer;

	public NeoFunnel() {
		motor = new GBSparkMax(NeoFunnelConstants.MOTOR_ID, CANSparkMax.MotorType.kBrushless);
		motor.config(NeoFunnelConstants.FUNNEL_CONFIG_OBJECT);
		motor.getReverseLimitSwitch(NeoFunnelConstants.BEAM_BREAKER_TYPE)
				.enableLimitSwitch(NeoFunnelConstants.IS_BEAM_BREAKER_ENABLE);
		debouncer = new Debouncer(NeoFunnelConstants.DEBOUNCE_TIME_FOR_LIMIT_SWITCH);
	}
	
	@Override
	public void setPower(double power) {
		motor.set(power);
	}
	
	@Override
	public void updateInputs(FunnelInputsAutoLogged inputs) {
		inputs.outputCurrent = motor.getOutputCurrent();
		inputs.appliedOutput = motor.getAppliedOutput();
		inputs.isObjectIn = debouncer.calculate(motor.getReverseLimitSwitch(NeoFunnelConstants.BEAM_BREAKER_TYPE).isPressed());
	}
}