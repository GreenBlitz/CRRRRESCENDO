package edu.greenblitz.robotName.subsystems.Lifter.LifterSolenoid;

import edu.greenblitz.robotName.utils.GBSubsystem;
import org.littletonrobotics.junction.Logger;

public class LifterSolenoid extends GBSubsystem {

	private static LifterSolenoid instance;
	private ILifterSolenoid lifterSolenoid;
	private LifterSolenoidInputsAutoLogged lifterSolenoidInputs;

	private LifterSolenoid(){
		lifterSolenoid = LifterSolenoidFactory.create();
		lifterSolenoidInputs = new LifterSolenoidInputsAutoLogged();
		lifterSolenoid.updateInputs(lifterSolenoidInputs);
	}

	public static LifterSolenoid getInstance() {
		init();
		return instance;
	}

	public static void init() {
		if (instance == null) {
			instance = new LifterSolenoid();
		}
	}

	public void periodic() {
		super.periodic();
		lifterSolenoid.updateInputs(lifterSolenoidInputs);

		Logger.processInputs("Lifter/Solenoid",lifterSolenoidInputs);
	}

	public boolean isOpen(){
		return lifterSolenoidInputs.isOpen;
	}

	public void setPower(double power){
		lifterSolenoid.setPower(power);
	}
}
