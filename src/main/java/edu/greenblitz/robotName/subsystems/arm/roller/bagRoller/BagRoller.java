package edu.greenblitz.robotName.subsystems.arm.roller.bagRoller;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.greenblitz.robotName.subsystems.Battery;
import edu.greenblitz.robotName.subsystems.arm.roller.IRoller;
import edu.greenblitz.robotName.subsystems.arm.roller.RollerInputsAutoLogged;
import edu.greenblitz.robotName.subsystems.arm.roller.neoRoller.NeoRollerConstants;
import edu.greenblitz.robotName.utils.Conversions;
import edu.wpi.first.math.geometry.Rotation2d;

public class BagRoller implements IRoller {
	
	private TalonSRX motor;
	
	private RollerInputsAutoLogged lastInputs;
	
	public BagRoller() {
		motor = new TalonSRX(BagRollerConstants.MOTOR_ID);
		
		lastInputs = new RollerInputsAutoLogged();
	}
	
	@Override
	public void setPower(double power) {
		motor.set(ControlMode.PercentOutput, power);
	}
	
	@Override
	public void setVoltage(double voltage) {
		setPower(voltage / Battery.getInstance().getCurrentVoltage());
	}
	
	@Override
	public void resetEncoder(Rotation2d position) {
		motor.setSelectedSensorPosition(Conversions.MagEncoderConversions.Rotation2DToMotorPosition(position));
	}
	
	@Override
	public void moveToPosition(Rotation2d position) {
		motor.set(TalonSRXControlMode.Position, position.getRotations());
	}
	
	public boolean isObjectInByCurrent(double current){
		return Math.abs(current - lastInputs.outputCurrent) >= NeoRollerConstants.NOTE_IN_CURRENT
				|| current >= NeoRollerConstants.NOTE_IN_CURRENT;
	}
	
	@Override
	public void updateInputs(RollerInputsAutoLogged rollerInputs) {
		rollerInputs.appliedOutput = motor.getMotorOutputVoltage();
		rollerInputs.outputCurrent = motor.getOutputCurrent();
		rollerInputs.position = Rotation2d.fromRotations(motor.getSelectedSensorPosition());
		rollerInputs.isObjectIn = isObjectInByCurrent(rollerInputs.outputCurrent);
		
		lastInputs = rollerInputs;
	}
}
