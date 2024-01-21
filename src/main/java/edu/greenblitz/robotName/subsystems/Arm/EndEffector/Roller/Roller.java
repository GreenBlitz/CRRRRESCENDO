package edu.greenblitz.robotName.subsystems.Arm.EndEffector.Roller;

import edu.greenblitz.robotName.utils.GBSubsystem;
import org.littletonrobotics.junction.Logger;

public class Roller extends GBSubsystem {
	
	private static Roller instance;
	private static RollerInputsAutoLogged rollerInputs;
	private static IRoller roller;
	
	private Roller() {
		roller = RollerFactory.create();
		rollerInputs = new RollerInputsAutoLogged();
		roller.updateInputs(rollerInputs);
	}
	
	public static void init() {
		if (instance == null)
			instance = new Roller();
	}
	
	public static Roller getInstance() {
		init();
		return instance;
	}
	
	public void periodic() {
		roller.updateInputs(rollerInputs);
		Logger.processInputs("roller", rollerInputs);
	}
	
	public static void setPower(double power) {
		roller.setPower(power);
	}
	
	public static void stop() {
		setPower(0);
	}
}
