package edu.greenblitz.robotName.subsystems.arm.roller;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.arm.roller.BagRoller.BagRoller;
import edu.greenblitz.robotName.subsystems.arm.roller.SimulationRoller.SimulationRoller;

public class RollerFactory {
	public static IRoller create() {
		return switch (RobotConstants.ROBOT_TYPE) {
			case ROBOT_NAME -> new BagRoller();
			case REPLAY -> new ReplayRoller();
			case SIMULATION -> new SimulationRoller();
		};
	}
}
