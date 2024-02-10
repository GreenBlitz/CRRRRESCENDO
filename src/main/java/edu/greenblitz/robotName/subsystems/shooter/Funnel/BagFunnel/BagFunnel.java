package edu.greenblitz.robotName.subsystems.shooter.Funnel.BagFunnel;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.greenblitz.robotName.subsystems.arm.roller.BagRoller.BagRollerConstants;
import edu.greenblitz.robotName.subsystems.arm.roller.IRoller;
import edu.greenblitz.robotName.subsystems.arm.roller.RollerInputsAutoLogged;
import edu.greenblitz.robotName.subsystems.shooter.Funnel.FunnelInputsAutoLogged;
import edu.greenblitz.robotName.subsystems.shooter.Funnel.IFunnel;
import edu.wpi.first.math.filter.Debouncer;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.DigitalInput;

public class BagFunnel implements IFunnel {
	
	private TalonSRX motor;

	private Debouncer debouncer;

	private DigitalInput beamBreaker;

	public BagFunnel(){
		motor = new TalonSRX(BagFunnelConstants.MOTOR_ID);

		debouncer = new Debouncer(BagRollerConstants.DEBOUNCE_TIME_FOR_LIMIT_SWITCH);
		beamBreaker = new DigitalInput(BagRollerConstants.BEAM_BREAKER_CHANNEL);
	}

	@Override
	public void setPower(double power) {
		motor.set(ControlMode.PercentOutput, power);
	}

	@Override
	public void setVoltage(double voltage) {

	}

	@Override
	public void updateInputs(FunnelInputsAutoLogged funnelInputs) {

	}

	@Override
	public void resetEncoder(Rotation2d position) {

	}

	@Override
	public void moveToPosition(Rotation2d position) {

	}

	@Override
	public void updateInputs(RollerInputsAutoLogged rollerInputs) {
		rollerInputs.outputCurrent = motor.getOutputCurrent();
		rollerInputs.appliedOutput = motor.getMotorOutputVoltage();
	}
}
