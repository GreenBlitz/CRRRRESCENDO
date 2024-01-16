package edu.greenblitz.robotName.subsystems.shooter.neoFlyWheel;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.greenblitz.robotName.subsystems.shooter.IFlyWheel;
import edu.greenblitz.robotName.subsystems.shooter.ShooterInputs;
import edu.greenblitz.robotName.subsystems.shooter.ShooterInputsAutoLogged;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;

import static edu.greenblitz.robotName.subsystems.shooter.neoFlyWheel.NeoFlyWheelConstants.FEEDFORWARD;
import static edu.greenblitz.robotName.subsystems.shooter.neoFlyWheel.NeoFlyWheelConstants.MOTOR_ID;

public class NeoFlyWheel implements IFlyWheel {
	private GBSparkMax motor;
	private SimpleMotorFeedforward feedforward;

	public NeoFlyWheel (){
		motor = new GBSparkMax(MOTOR_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
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
		motor.getPIDController().setReference(velocity, CANSparkMax.ControlType.kVelocity,0,FEEDFORWARD.calculate(velocity));
	}

	@Override
	public void updateInputs(ShooterInputsAutoLogged inputs) {
		inputs.appliedOutput = motor.getAppliedOutput();
		inputs.outputCurrent = motor.getOutputCurrent();
		inputs.velocity = motor.getEncoder().getVelocity();
	}
}
