package edu.greenblitz.robotName.subsystems.climber.solenoid;

import edu.greenblitz.robotName.utils.GBSubsystem;
import org.littletonrobotics.junction.Logger;

public class Solenoid extends GBSubsystem {
	private static Solenoid instance;
	private ISolenoid solenoid;
	private SolenoidInputsAutoLogged solenoidInputs;

	private Solenoid() {
		solenoid = SolenoidFactory.create();
		solenoidInputs = new SolenoidInputsAutoLogged();
		solenoid.updateInputs(solenoidInputs);
	}

	public static Solenoid getInstance() {
		init();
		return instance;
	}

	public static void init() {
		if (instance == null) {
			instance = new Solenoid();
		}
	}

	@Override
	public void periodic() {
		super.periodic();
		solenoid.updateInputs(solenoidInputs);
		Logger.processInputs("Climbing/Solenoid", solenoidInputs);
	}


	public void holdSolenoid() {
		solenoid.holdSolenoid();
	}

	public void moveSolenoidByPower(double power) {
		solenoid.setPowerToSolenoid(power);
	}


	public void openSolenoid() {
		solenoid.openSolenoid();
	}

	public void closeSolenoid() {
		solenoid.closeSolenoid();
	}

}
