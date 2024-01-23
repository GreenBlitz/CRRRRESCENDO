package edu.greenblitz.robotName.subsystems.Shooter.Pivot.MotorPivot.SystemIdenficationPivot;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;

import static edu.greenblitz.robotName.subsystems.Shooter.Pivot.MotorPivot.MotorConstants.MOTOR_ID;
import static edu.greenblitz.robotName.subsystems.Shooter.Pivot.MotorPivot.MotorConstants.SWITCH_CONFIGS;

public class SystemIdenficationPivot extends GBSubsystem {

	private static SystemIdenficationPivot instance;
	
	private final TalonFX motor;

//	private final DutyCycleOut m_joystickControl;
//
//	private final VoltageOut m_sysidControl;
//
//	private final SysIdRoutine.Config config;
//	private final SysIdRoutine.Mechanism mechanism;
//	private SysIdRoutine m_SysIdRoutine;

	
	private SystemIdenficationPivot() {
		motor = new TalonFX(MOTOR_ID, "rio");
		TalonFXConfiguration a = new TalonFXConfiguration();
		motor.getConfigurator().apply(a);
//		m_joystickControl = new DutyCycleOut(0);
//		m_sysidControl = new VoltageOut(0);
//		config = new SysIdRoutine.Config(
//				null,
//				Volts.of(DYNAMIC_VOLTAGE),
//				null,
//				(state) -> SignalLogger.writeString("state", state.toString())
//		);
//		mechanism = new SysIdRoutine.Mechanism(
//				(Measure<Voltage> volts) -> motor.setControl(m_sysidControl.withOutput(volts.in(Volts))),
//				null,
//				this
//		);
//		m_SysIdRoutine = new SysIdRoutine(config, mechanism);
//
//		BaseStatusSignal.setUpdateFrequencyForAll(SIGNAL_SPEED,
//				motor.getPosition(),
//				motor.getVelocity(),
//				motor.getMotorVoltage()
//		);
//
//		motor.optimizeBusUtilization();
//
//		SignalLogger.start();
	}

	public static void init(){
		if (instance==null)
			instance = new SystemIdenficationPivot();
	}

	public static SystemIdenficationPivot getInstance(){
		init();
		return instance;
	}

//	public SysIdRoutine getM_SysIdRoutine() {
//		return m_SysIdRoutine;
//	}
//
//	public DutyCycleOut getM_Joystick(){
//		return m_joystickControl;
//	}

	public void setPower(double power){
		motor.set(power);
	}

}
