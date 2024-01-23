package edu.greenblitz.robotName.subsystems.Shooter.Pivot.MotorPivot.SystemIdenficationPivot;

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
	
	private SysIdRoutine m_SysIdRoutine =
			new SysIdRoutine(
					new SysIdRoutine.Config(
							null,         // Default ramp rate is acceptable
							Volts.of(4), // Reduce dynamic voltage to 4 to prevent motor brownout
							null,          // Default timeout is acceptable
							// Log state with Phoenix SignalLogger class
							(state) -> SignalLogger.writeString("state", state.toString())),
					new SysIdRoutine.Mechanism(
							(Measure<Voltage> volts) -> m_motorToTest.setControl(m_sysidControl.withOutput(volts.in(Volts))),
							null,
							this));
	
	public SystemIdenficationPivot() {
		motor = new TalonFX(MOTOR_ID);
		motor.getConfigurator().apply(SWITCH_CONFIGS);
		m_joystickControl = new DutyCycleOut(0);
		m_sysidControl = new VoltageOut(0);
//		config = new SysIdRoutine.Config(
//				null,         // Default ramp rate is acceptable
//				Volts.of(4), // Reduce dynamic voltage to 4 to prevent motor brownout
//				null,          // Default timeout is acceptable
//				// Log state with Phoenix SignalLogger class
//				(state) -> SignalLogger.writeString("state", "")
//		);
//		mechanism = new SysIdRoutine.Mechanism(
//				(Measure<Voltage> volts) -> motor.setControl(m_sysidControl.withOutput(volts.in(Volts))),
//				null,
//				this);
//		m_SysIdRoutine = new SysIdRoutine(config, mechanism);
		SysIdRoutine m_SysIdRoutine =
				new SysIdRoutine(
						new SysIdRoutine.Config(
								null,         // Default ramp rate is acceptable
								Volts.of(4), // Reduce dynamic voltage to 4 to prevent motor brownout
								null,          // Default timeout is acceptable
								// Log state with Phoenix SignalLogger class
								(state) -> SignalLogger.writeString("state", state.toString())),
						new SysIdRoutine.Mechanism(
								(Measure<Voltage> volts) -> motor.setControl(m_sysidControl.withOutput(volts.in(Volts))),
								null,
								this));
	}
}
