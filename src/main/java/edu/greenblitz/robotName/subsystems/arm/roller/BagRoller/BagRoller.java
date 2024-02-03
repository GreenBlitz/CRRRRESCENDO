package edu.greenblitz.robotName.subsystems.arm.roller.BagRoller;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.greenblitz.robotName.subsystems.arm.roller.IRoller;
import edu.greenblitz.robotName.subsystems.arm.roller.RollerInputsAutoLogged;
import edu.wpi.first.math.filter.Debouncer;
import edu.wpi.first.wpilibj.DigitalInput;

public class BagRoller implements IRoller {
	
	private TalonSRX motor;

	private Debouncer debouncer;

	private DigitalInput beamBreaker;

	public BagRoller(){
		motor = new TalonSRX(BagRollerConstants.ROLLER_ID);

		debouncer = new Debouncer(BagRollerConstants.DEBOUNCE_TIME_FOR_LIMIT_SWITCH);
		beamBreaker = new DigitalInput(BagRollerConstants.BEAM_BREAKER_CHANNEL);
	}

	@Override
	public void setPower(double power) {
		motor.set(ControlMode.PercentOutput, power);
	}

	@Override
	public void updateInputs(RollerInputsAutoLogged rollerInputs) {
		rollerInputs.outputCurrent = motor.getOutputCurrent();
		rollerInputs.appliedOutput = motor.getMotorOutputVoltage();
		rollerInputs.isObjectInArm = debouncer.calculate(beamBreaker.get());
	}
}
