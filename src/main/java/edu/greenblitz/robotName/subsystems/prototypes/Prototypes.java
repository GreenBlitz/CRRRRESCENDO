package edu.greenblitz.robotName.subsystems.prototypes;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix6.configs.ClosedLoopRampsConfigs;
import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;

import java.util.HashMap;

public class Prototypes {
	public static final HashMap<Integer, GBSparkMax> sparks = new HashMap<>();
	public static final HashMap<Integer, TalonFX> talonFXs = new HashMap<>();
	public static final HashMap<Integer, TalonSRX> talonSRXs = new HashMap<>();
	public static double CLOSED_LOOP_RAMP_RATE = 1.5;
	public static GBSparkMax getSparkMax(int id){
		if (!sparks.containsKey(id)){
			GBSparkMax motor = new GBSparkMax(id, CANSparkMaxLowLevel.MotorType.kBrushless);
			motor.setOpenLoopRampRate(CLOSED_LOOP_RAMP_RATE);
			sparks.put(id, motor);
		}
		return sparks.get(id);
	}

	public static TalonFX getTalonFX(int id){
		if (!talonFXs.containsKey(id)){
			TalonFX motor = new TalonFX(id);
			ClosedLoopRampsConfigs closedLoopRampsConfigs = new ClosedLoopRampsConfigs();
			closedLoopRampsConfigs.DutyCycleClosedLoopRampPeriod = CLOSED_LOOP_RAMP_RATE;
			closedLoopRampsConfigs.VoltageClosedLoopRampPeriod = CLOSED_LOOP_RAMP_RATE;
			motor.getConfigurator().apply(closedLoopRampsConfigs);
			talonFXs.put(id, motor);
		}
		return talonFXs.get(id);
	}

	public static TalonSRX getTalonSRX(int id){
		if (!talonSRXs.containsKey(id)){
			TalonSRX motor = new TalonSRX(id);
			motor.configClosedloopRamp(CLOSED_LOOP_RAMP_RATE);
			talonSRXs.put(id, motor);
		}
		return talonSRXs.get(id);
	}
}