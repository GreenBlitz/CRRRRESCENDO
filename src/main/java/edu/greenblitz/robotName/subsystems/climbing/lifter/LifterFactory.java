package edu.greenblitz.robotName.subsystems.climbing.lifter;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.subsystems.climbing.lifter.falconLifter.FalconLifter;
import edu.greenblitz.robotName.subsystems.climbing.lifter.replayLifter.ReplayLifter;
import edu.greenblitz.robotName.subsystems.climbing.lifter.simulationLifter.SimulationLifter;

public class LifterFactory {

	public static ILifter create() {
		return switch (Robot.getRobotType()) {
			case SYNCOPA -> new FalconLifter();
			case REPLAY -> new ReplayLifter();
			case SIMULATION, PEGA_SWERVE -> new SimulationLifter();
		};
	}
}