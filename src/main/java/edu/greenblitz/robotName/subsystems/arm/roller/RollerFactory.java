package edu.greenblitz.robotName.subsystems.arm.roller;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.arm.roller.BagRoller.BagRoller;
import edu.greenblitz.robotName.subsystems.arm.roller.NeoRoller.NeoRoller;
import edu.greenblitz.robotName.subsystems.arm.roller.SimulationRoller.SimulationRoller;

public class RollerFactory {
	public static IRoller create() {
		return switch (Robot.getRobotType()) {
			case ROBOT_NAME -> new NeoRoller();
			case REPLAY -> new ReplayRoller();
			default -> new SimulationRoller();
		};
	}
}
