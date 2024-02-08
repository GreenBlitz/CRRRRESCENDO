package edu.greenblitz.robotName.subsystems.arm.roller;

import edu.greenblitz.robotName.utils.GBSubsystem;
import org.littletonrobotics.junction.Logger;

import static edu.greenblitz.robotName.subsystems.arm.roller.RollerConstants.ROLL_BACKWARD_POWER;
import static edu.greenblitz.robotName.subsystems.arm.roller.RollerConstants.ROLL_FORWARD_POWER;

public class Roller extends GBSubsystem {
	
	private static Roller instance;
	private RollerInputsAutoLogged rollerInputs;
	private IRoller roller;
	
	private Roller() {
		roller = RollerFactory.create();
		rollerInputs = new RollerInputsAutoLogged();
		roller.updateInputs(rollerInputs);
	}

	public static void init() {
		if (instance == null) {
			instance = new Roller();
		}
	}

	public static Roller getInstance() {
		init();
		return instance;
	}
	
	public void periodic() {
		super.periodic();

		roller.updateInputs(rollerInputs);
		Logger.processInputs("Roller", rollerInputs);
	}

	public void setPower(double power) {
		roller.setPower(power);
	}

	public void rollClockWise(){
		setPower(ROLL_FORWARD_POWER);
	}

	public void rollCounterClockWise(){
		setPower(ROLL_BACKWARD_POWER);
	}

	public void stop() {
		setPower(0);
	}

	public boolean isObjectInside() {
		return rollerInputs.isObjectInArm;
	}
}
