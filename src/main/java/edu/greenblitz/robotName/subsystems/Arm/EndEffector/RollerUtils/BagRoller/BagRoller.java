package edu.greenblitz.robotName.subsystems.Arm.EndEffector.RollerUtils.BagRoller;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.greenblitz.robotName.subsystems.Arm.EndEffector.RollerUtils.IRoller;
import edu.greenblitz.robotName.subsystems.Arm.EndEffector.RollerUtils.RollerInputsAutoLogged;

public class BagRoller implements IRoller {
	
	private TalonSRX motor;

	public BagRoller(){
		motor = new TalonSRX(BagRollerConstants.ROLLER_ID);
	}

	@Override
	public void setPower(double power) {
		motor.set(ControlMode.PercentOutput, power);
	}

	@Override
	public void updateInputs(RollerInputsAutoLogged rollerInputs) {
		rollerInputs.outputCurrent = motor.getOutputCurrent();
		rollerInputs.appliedOutput = motor.getMotorOutputVoltage();
	}
}
