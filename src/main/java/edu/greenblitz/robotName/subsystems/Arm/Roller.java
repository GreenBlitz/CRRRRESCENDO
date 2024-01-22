package edu.greenblitz.robotName.subsystems.Arm;

import edu.greenblitz.robotName.subsystems.Arm.EndEffector.RollerUtils.IRoller;
import edu.greenblitz.robotName.subsystems.Arm.EndEffector.RollerUtils.RollerFactory;
import edu.greenblitz.robotName.subsystems.Arm.EndEffector.RollerUtils.RollerInputsAutoLogged;
import edu.greenblitz.robotName.utils.GBSubsystem;
import org.littletonrobotics.junction.Logger;

import static edu.greenblitz.robotName.subsystems.Arm.EndEffector.RollerUtils.RollerConstants.ROLL_BACKWARD_POWER;
import static edu.greenblitz.robotName.subsystems.Arm.EndEffector.RollerUtils.RollerConstants.ROLL_FORWARD_POWER;

public class Roller extends GBSubsystem {
	
	private static Roller instance;
	private static RollerInputsAutoLogged rollerInputs;
	private static IRoller roller;
	
	private Roller() {
		roller = RollerFactory.create();
		rollerInputs = new RollerInputsAutoLogged();
		roller.updateInputs(rollerInputs);
	}

	protected static void init() {
		if (instance == null)
			instance = new Roller();
	}

	protected static Roller getInstance() {
		init();
		return instance;
	}
	
	public void periodic() {
		super.periodic();

		roller.updateInputs(rollerInputs);
		Logger.processInputs("roller", rollerInputs);
	}

	protected static void setPower(double power) {
		roller.setPower(power);
	}

	protected static void rollForward(){
		setPower(ROLL_FORWARD_POWER);
	}

	protected static void rollBackward(){
		setPower(ROLL_BACKWARD_POWER);
	}

	protected static void stop() {
		setPower(0);
	}
}
