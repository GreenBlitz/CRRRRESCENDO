package edu.greenblitz.robotName.subsystems.prototypes;

import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;

import java.util.HashMap;

public class Prototypes {
	public static final HashMap<Integer, GBSparkMax> sparks = new HashMap<>();
	public static final HashMap<Integer, TalonFX> talons = new HashMap<>();
	
	public static GBSparkMax getSparkMax(int id){
		if (!sparks.containsKey(id)){
			sparks.put(id, new GBSparkMax(id, CANSparkMaxLowLevel.MotorType.kBrushless));
		}
		return sparks.get(id);
	}
	
	public static TalonFX getTalon(int id){
		if (!talons.containsKey(id)){
			talons.put(id, new TalonFX(id));
		}
		return talons.get(id);
	}
}