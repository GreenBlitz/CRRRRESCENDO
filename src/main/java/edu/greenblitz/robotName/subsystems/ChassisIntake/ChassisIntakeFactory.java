package edu.greenblitz.robotName.subsystems.ChassisIntake;

import edu.greenblitz.robotName.RobotConstants;

public class ChassisIntakeFactory{
	public static IChassisIntake create() {
		switch (RobotConstants.ROBOT_TYPE) {
			case ROBOT_NAME:
				return new RealChassisIntake();
			case REPLAY:
				return new ReplayChassisIntake();
			case SIMULATION:
			default:
				return new SimulationChassisIntake();
		}
		
	}
}
