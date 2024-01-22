package edu.greenblitz.robotName.subsystems.shooter.Funnel;

import com.revrobotics.CANSparkMaxLowLevel;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;
import edu.wpi.first.math.filter.Debouncer;

import static edu.greenblitz.robotName.subsystems.shooter.Funnel.FunnelConstants.SparkMaxConfigs.FUNNEL_CONFIG_OBJECT;
import static edu.greenblitz.robotName.subsystems.shooter.Funnel.FunnelConstants.SparkMaxConfigs.SWITCH_TYPE;

public class NeoFunnel implements IFunnel{
	
	private GBSparkMax motor;

	private Debouncer debouncer;

	public NeoFunnel() {
		motor = new GBSparkMax(FunnelConstants.SparkMaxConfigs.FUNNEL_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
		motor.config(FUNNEL_CONFIG_OBJECT);
		motor.getReverseLimitSwitch(SWITCH_TYPE).enableLimitSwitch(true);
		debouncer = new Debouncer(FunnelConstants.SparkMaxConfigs.DEBOUNCE_TIME_FOR_LIMIT_SWITCH);
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
	public void updateInputs(FunnelInputsAutoLogged funnelInputs) {
		funnelInputs.outputCurrent = motor.getOutputCurrent();
		funnelInputs.appliedOutput = motor.getAppliedOutput();
		funnelInputs.velocity = motor.getEncoder().getVelocity();
		funnelInputs.temperature = motor.getMotorTemperature();
		funnelInputs.isObjectIn = debouncer.calculate(motor.getReverseLimitSwitch(SWITCH_TYPE).isPressed());
	}
}
