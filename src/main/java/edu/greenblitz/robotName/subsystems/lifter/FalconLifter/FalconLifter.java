package edu.greenblitz.robotName.subsystems.lifter.FalconLifter;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.lifter.ILifter;
import edu.greenblitz.robotName.subsystems.lifter.LifterConstants;
import edu.greenblitz.robotName.subsystems.lifter.LifterInputsAutoLogged;
import edu.greenblitz.robotName.subsystems.lifter.neoLifter.NeoLifterConstants;
import edu.greenblitz.robotName.subsystems.shooter.pivot.falconPivot.FalconPivotConstants;
import edu.greenblitz.robotName.utils.motors.GBTalonFXPro;
import edu.wpi.first.math.geometry.Rotation2d;

import static edu.greenblitz.robotName.RobotConstants.General.Motors.IS_SWITCH_CLOSED;

public class FalconLifter implements ILifter {
	
	private GBTalonFXPro motor;
	
	private TalonSRX solenoid;
	
	private PositionVoltage positionVoltage;
	
	
	public FalconLifter() {
		motor = new GBTalonFXPro(FalconLifterConstants.MOTOR_ID, FalconLifterConstants.CANBUS_CHAIN);
		solenoid = new TalonSRX(NeoLifterConstants.SOLENOID_ID);
		motor.applyConfiguration(FalconLifterConstants.TALON_FX_CONFIGURATION);
		motor.setNeutralMode(FalconLifterConstants.NEUTRAL_MODE_VALUE);
		motor.setInverted(FalconLifterConstants.INVERTED);
		optimizeCanBusUtilization();
		positionVoltage = new PositionVoltage(motor.getPosition().getValue());
	}
	
	public void optimizeCanBusUtilization() {
		BaseStatusSignal.setUpdateFrequencyForAll(
				RobotConstants.General.Motors.SIGNAL_FREQUENCY_HERTZ,
				motor.getPosition(),
				motor.getVelocity(),
				motor.getMotorVoltage(),
				motor.getStatorCurrent(),
				motor.getForwardLimit(),
				motor.getReverseLimit(),
				motor.getClosedLoopReference()
		);
		motor.optimizeBusUtilization();
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
		motor.setPosition(position.getRotations());
	}
	
	@Override
	public void stop() {
		motor.set(0);
	}
	
	@Override
	public void goToPosition(Rotation2d position) {
		motor.setControl(
				positionVoltage
						.withPosition(position.getRotations())
						.withSlot(FalconLifterConstants.PID_SLOT)
						.withLimitForwardMotion(true)
						.withLimitReverseMotion(true)
						.withEnableFOC(false)
						.withOverrideBrakeDurNeutral(true)
		);
	}
	
	@Override
	public void openSolenoid() {
		setPowerToSolenoid(LifterConstants.POWER_TO_OPEN_SOLENOID);
	}
	
	@Override
	public void closeSolenoid() {
		setPowerToSolenoid(LifterConstants.POWER_TO_CLOSE_SOLENOID);
	}
	
	@Override
	public void holdSolenoid() {
		setPowerToSolenoid(LifterConstants.POWER_TO_HOLD_SOLENOID);
	}
	
	@Override
	public void setPowerToSolenoid(double powerSolenoid) {
		solenoid.set(TalonSRXControlMode.PercentOutput, powerSolenoid);
	}
	
	@Override
	public void setIdleMode(CANSparkMax.IdleMode idleMode) {
		if (idleMode == CANSparkBase.IdleMode.kBrake) {
			motor.setNeutralMode(NeutralModeValue.Brake);
		} else {
			motor.setNeutralMode(NeutralModeValue.Coast);
		}
	}
	
	@Override
	public void updateInputs(LifterInputsAutoLogged inputs) {
		inputs.appliedOutput = motor.getMotorVoltage().getValue();
		inputs.outputCurrent = motor.getStatorCurrent().getValue();
		inputs.position = motor.getPosition().getValue();
		inputs.velocity = motor.getVelocity().getValue();
		inputs.isForwardSwitchPressed = motor.getForwardLimit().getValue().value == IS_SWITCH_CLOSED;
		inputs.isBackwardSwitchPressed = motor.getReverseLimit().getValue().value == IS_SWITCH_CLOSED;
		inputs.positionReference = Rotation2d.fromRotations(motor.getClosedLoopReference().getValue());
		inputs.currentSolenoid = solenoid.getStatorCurrent();
		inputs.voltageSolenoid = solenoid.getMotorOutputVoltage();
		inputs.isOpenSolenoid = inputs.voltageSolenoid > 0;
	}
}
