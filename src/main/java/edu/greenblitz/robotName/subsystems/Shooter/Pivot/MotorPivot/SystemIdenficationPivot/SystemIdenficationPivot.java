package edu.greenblitz.robotName.subsystems.Shooter.Pivot.MotorPivot.SystemIdenficationPivot;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.SignalLogger;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.units.Measure;
import edu.wpi.first.units.Voltage;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;

import static edu.greenblitz.robotName.subsystems.Shooter.Pivot.MotorPivot.MotorConstants.MOTOR_ID;
import static edu.greenblitz.robotName.subsystems.Shooter.Pivot.MotorPivot.MotorConstants.SWITCH_CONFIGS;
import static edu.wpi.first.units.Units.Volts;

public class SystemIdenficationPivot extends GBSubsystem {
	
	private final TalonFX motor;
	
	private final DutyCycleOut m_joystickControl;
	
	private final VoltageOut m_sysidControl;
	
	private final SysIdRoutine.Config config;
	private final SysIdRoutine.Mechanism mechanism;
	private SysIdRoutine m_SysIdRoutine;

	
	public SystemIdenficationPivot() {
		motor = new TalonFX(MOTOR_ID);
		motor.getConfigurator().apply(SWITCH_CONFIGS);
		m_joystickControl = new DutyCycleOut(0);
		m_sysidControl = new VoltageOut(0);
		config = new SysIdRoutine.Config(
				null,
				Volts.of(4),
				null,
				(state) -> SignalLogger.writeString("state", state.toString())
		);
		mechanism = new SysIdRoutine.Mechanism(
				(Measure<Voltage> volts) -> motor.setControl(m_sysidControl.withOutput(volts.in(Volts))),
				null,
				this
		);
		m_SysIdRoutine = new SysIdRoutine(config, mechanism);

		BaseStatusSignal.setUpdateFrequencyForAll(250,
				motor.getPosition(),
				motor.getVelocity(),
				motor.getMotorVoltage()
		);

		motor.optimizeBusUtilization();

		SignalLogger.start();
	}
}
