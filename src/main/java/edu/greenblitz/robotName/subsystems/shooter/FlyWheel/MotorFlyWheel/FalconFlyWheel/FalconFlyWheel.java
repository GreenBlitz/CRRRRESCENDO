package edu.greenblitz.robotName.subsystems.shooter.FlyWheel.MotorFlyWheel.FalconFlyWheel;

import com.ctre.phoenix6.controls.MotionMagicDutyCycle;
import com.ctre.phoenix6.controls.MotionMagicExpoDutyCycle;
import com.ctre.phoenix6.controls.MotionMagicVelocityDutyCycle;
import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.IFlyWheel;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.MotorFlyWheel.MotorFlyWheelConstants;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheelInputsAutoLogged;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;

public class FalconFlyWheel implements IFlyWheel {
	private TalonFX motor;
	public FalconFlyWheel() {
		motor = new TalonFX(MotorFlyWheelConstants.MOTOR_ID);
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
		motor.setControl(new MotionMagicVelocityDutyCycle(
				velocity
		));
	}
	
	@Override
	public void updateInputs(FlyWheelInputsAutoLogged inputs) {
		inputs.appliedOutput = motor.getMotorVoltage().getValue();
		inputs.outputCurrent = motor.getSupplyCurrent().getValue();
		inputs.velocity = motor.getVelocity().getValue();
	}
}
