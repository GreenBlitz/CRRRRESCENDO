package edu.greenblitz.robotName.subsystems.arm.roller.NeoRoller;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.subsystems.arm.roller.IRoller;
import edu.greenblitz.robotName.subsystems.arm.roller.RollerInputsAutoLogged;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;
import edu.wpi.first.math.filter.Debouncer;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.DigitalInput;

public class NeoRoller implements IRoller {

	private GBSparkMax motor;

	private Debouncer debouncer;

	private DigitalInput beamBreaker;

	public NeoRoller() {
		motor = new GBSparkMax(NeoRollerConstants.MOTOR_ID, CANSparkLowLevel.MotorType.kBrushless);

		motor.config(NeoRollerConstants.ROLLER_CONFIG_OBJECT);
		motor.getPIDController().setFeedbackDevice(motor.getEncoder());

		beamBreaker = new DigitalInput(NeoRollerConstants.BEAM_BREAKER_CHANNEL);
		debouncer = new Debouncer(NeoRollerConstants.DEBOUNCE_TIME_FOR_LIMIT_SWITCH);
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
	public void updateInputs(RollerInputsAutoLogged inputs) {
		inputs.outputCurrent = motor.getOutputCurrent();
		inputs.appliedOutput = motor.getAppliedOutput();
		inputs.isObjectIn = debouncer.calculate(beamBreaker.get());
		inputs.position = Rotation2d.fromRotations(motor.getEncoder().getPosition());
	}
}
