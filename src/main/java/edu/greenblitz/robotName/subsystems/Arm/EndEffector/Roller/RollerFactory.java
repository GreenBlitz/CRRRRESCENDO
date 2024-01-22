package edu.greenblitz.robotName.subsystems.Arm.EndEffector.Roller;

import edu.greenblitz.robotName.RobotConstants;

public class RollerFactory {
	public static IRoller create() {
		return switch (RobotConstants.ROBOT_TYPE) {
			case ROBOT_NAME -> new BagRoller();
			case REPLAY -> new ReplayRoller();
			default -> new SimulationRoller();
		};
	}
}
