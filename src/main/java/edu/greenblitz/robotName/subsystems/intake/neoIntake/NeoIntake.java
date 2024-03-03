package edu.greenblitz.robotName.subsystems.intake.neoIntake;

import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkLowLevel;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.Battery;
import edu.greenblitz.robotName.subsystems.intake.IIntake;
import edu.greenblitz.robotName.subsystems.intake.IntakeInputsAutoLogged;
import edu.greenblitz.robotName.subsystems.shooter.funnel.neoFunnel.NeoFunnelConstants;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;
import edu.wpi.first.math.filter.Debouncer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class NeoIntake implements IIntake {

	private GBSparkMax motor;

	private Debouncer debouncer;

	public NeoIntake() {
		motor = new GBSparkMax(NeoIntakeConstants.MOTOR_ID, CANSparkLowLevel.MotorType.kBrushless);
		motor.config(NeoIntakeConstants.INTAKE_CONFIG_OBJECT);
		
		motor.getReverseLimitSwitch(NeoIntakeConstants.BEAM_BREAKER_TYPE)
				.enableLimitSwitch(NeoIntakeConstants.IS_BEAM_BREAKER_LIMITING);
		
		debouncer = new Debouncer(NeoIntakeConstants.DEBOUNCE_TIME_FOR_LIMIT_SWITCH);
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
		motor.getPIDController().setReference(
				velocity,
				CANSparkBase.ControlType.kVelocity,
				0,
				NeoIntakeConstants.SIMPLE_MOTOR_FEEDFORWARD.calculate(velocity)
		);
	}
	
	@Override
	public void updateInputs(IntakeInputsAutoLogged intakeInputs) {
		intakeInputs.outputCurrent = motor.getOutputCurrent();
		intakeInputs.appliedOutput = motor.getAppliedOutput();
		intakeInputs.velocity = motor.getEncoder().getVelocity();
		intakeInputs.beamBreakerValue = debouncer.calculate(motor.getReverseLimitSwitch(NeoIntakeConstants.BEAM_BREAKER_TYPE).isPressed());
	}
}