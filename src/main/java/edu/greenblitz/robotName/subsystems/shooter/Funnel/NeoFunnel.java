package edu.greenblitz.robotName.subsystems.shooter.Funnel;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.math.filter.Debouncer;

import static edu.greenblitz.robotName.subsystems.shooter.Funnel.FunnelConstants.SparkMaxConfigs.SWITCH_TYPE;

public class NeoFunnel implements IFunnel{
	
	private CANSparkMax motor;
	private Debouncer debouncer;
	public NeoFunnel() {
		motor = new CANSparkMax(FunnelConstants.SparkMaxConfigs.FUNNEL_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
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
		funnelInputs.isObjectIn = debouncer.calculate(motor.getReverseLimitSwitch(SWITCH_TYPE).isPressed());
	}
}
