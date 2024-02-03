package edu.greenblitz.robotName.subsystems.arm;

import edu.greenblitz.robotName.subsystems.arm.EndEffector.RollerUtils.IRoller;
import edu.greenblitz.robotName.subsystems.arm.EndEffector.RollerUtils.RollerFactory;
import edu.greenblitz.robotName.subsystems.arm.EndEffector.RollerUtils.RollerInputsAutoLogged;
import edu.greenblitz.robotName.utils.GBSubsystem;
import org.littletonrobotics.junction.Logger;

import static edu.greenblitz.robotName.subsystems.arm.EndEffector.RollerUtils.RollerConstants.ROLL_BACKWARD_POWER;
import static edu.greenblitz.robotName.subsystems.arm.EndEffector.RollerUtils.RollerConstants.ROLL_FORWARD_POWER;

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

	public void rollIn(){
		setPower(ROLL_FORWARD_POWER);
	}

	public void rollOut(){
		setPower(ROLL_BACKWARD_POWER);
	}

	public void stop() {
		setPower(0);
	}

	public boolean isObjectInside() {
		return rollerInputs.isObjectInArm;
	}
}
