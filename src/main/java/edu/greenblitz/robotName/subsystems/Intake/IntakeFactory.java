package edu.greenblitz.robotName.subsystems.Intake;

import edu.greenblitz.robotName.RobotConstants;

public class IntakeFactory {
	public static IIntake create() {
		switch (RobotConstants.ROBOT_TYPE) {
			case ROBOT_NAME:
				return new RealIntake();
			case REPLAY:
				return new ReplayIntake();
			case SIMULATION:
			default:
				return new SimulationIntake();
		}
		
	}
}
