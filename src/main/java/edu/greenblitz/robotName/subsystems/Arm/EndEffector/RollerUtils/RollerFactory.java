package edu.greenblitz.robotName.subsystems.Arm.EndEffector.RollerUtils;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.Arm.EndEffector.RollerUtils.BagRoller.BagRoller;
import edu.greenblitz.robotName.subsystems.Arm.EndEffector.RollerUtils.SimulationRoller.SimulationRoller;

public class RollerFactory {
	public static IRoller create() {
		return switch (RobotConstants.ROBOT_TYPE) {
			case ROBOT_NAME -> new BagRoller();
			case REPLAY -> new ReplayRoller();
			default -> new SimulationRoller();
		};
	}
}
