package edu.greenblitz.robotName.subsystems.arm.roller.NeoRoller;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.greenblitz.robotName.subsystems.arm.roller.IRoller;
import edu.greenblitz.robotName.subsystems.arm.roller.RollerInputsAutoLogged;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;
import edu.wpi.first.math.filter.Debouncer;
import edu.wpi.first.wpilibj.DigitalInput;

import static edu.greenblitz.robotName.subsystems.arm.roller.NeoRoller.NeoRollerConstants.*;
import static edu.greenblitz.robotName.subsystems.shooter.Funnel.NeoFunnel.NeoFunnelConstants.BEAM_BREAKER_CHANNEL;

public class NeoRoller implements IRoller {

	private GBSparkMax motor;

	private Debouncer debouncer;

	private DigitalInput beamBreaker;

	public NeoRoller() {
		motor = new GBSparkMax(MOTOR_ID, CANSparkLowLevel.MotorType.kBrushless);
		motor.config(ROLLER_CONFIG_OBJECT);
		beamBreaker = new DigitalInput(BEAM_BREAKER_CHANNEL);
		debouncer = new Debouncer(DEBOUNCE_TIME_FOR_LIMIT_SWITCH);
	}

	@Override
	public void setPower(double power) {
		motor.set(power);
	}

	@Override
	public void updateInputs(RollerInputsAutoLogged rollerInputs) {
		rollerInputs.outputCurrent = motor.getOutputCurrent();
		rollerInputs.appliedOutput = motor.getAppliedOutput();
		rollerInputs.isObjectInArm = debouncer.calculate(beamBreaker.get());
	}
}
