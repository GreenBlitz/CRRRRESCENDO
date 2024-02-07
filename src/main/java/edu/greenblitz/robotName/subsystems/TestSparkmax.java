package edu.greenblitz.robotName.subsystems;

import com.revrobotics.CANSparkMaxLowLevel;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;

public class TestSparkmax extends GBSubsystem{
	
	public static TestSparkmax instance;
	private int ID;
	public GBSparkMax motor;
	public static TestSparkmax getInstance() {
		init();
		return instance;
	}
	
	public static void init(){
		if (instance == null) {
			instance = new TestSparkmax();
		}
	}
	private TestSparkmax() {
		ID = 8;
		motor = new GBSparkMax(ID, CANSparkMaxLowLevel.MotorType.kBrushless);
	}
	public void moveMotor(double power){
		motor.set(power);
	}
}
