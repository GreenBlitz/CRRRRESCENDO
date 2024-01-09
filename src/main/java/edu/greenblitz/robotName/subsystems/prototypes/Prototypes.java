package edu.greenblitz.robotName.subsystems.prototypes;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;

import java.util.HashMap;

public class Prototypes {
	public static final HashMap<Integer, GBSparkMax> sparks = new HashMap<>();
	public static final HashMap<Integer, TalonFX> talonFXs = new HashMap<>();

	public static final HashMap<Integer, TalonSRX> talonSRX = new HashMap<>();

	public static GBSparkMax getSparkMax(int id){
		if (!sparks.containsKey(id)){
			sparks.put(id, new GBSparkMax(id, CANSparkMaxLowLevel.MotorType.kBrushless));
		}
		return sparks.get(id);
	}

	public static TalonFX getTalonFX(int id){
		if (!talonFXs.containsKey(id)){
			talonFXs.put(id, new TalonFX(id));
		}
		return talonFXs.get(id);
	}

	public static TalonSRX getTalonSRX(int id){
		if (!talonSRX.containsKey(id)){
			talonSRX.put(id, new TalonSRX(id));
		}
		return talonSRX.get(id);
	}
}