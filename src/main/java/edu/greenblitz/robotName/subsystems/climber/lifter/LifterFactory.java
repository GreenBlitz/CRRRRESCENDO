package edu.greenblitz.robotName.subsystems.climber.lifter;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.subsystems.climber.lifter.falconLifter.FalconLifter;
import edu.greenblitz.robotName.subsystems.climber.lifter.replayLifter.ReplayLifter;
import edu.greenblitz.robotName.subsystems.climber.lifter.simulationLifter.SimulationLifter;

public class LifterFactory {

	public static ILifter create() {
		return switch (Robot.getRobotType()) {
			case SYNCOPA -> new FalconLifter();
			case REPLAY -> new ReplayLifter();
			case SIMULATION, PEGA_SWERVE -> new SimulationLifter();
		};
	}
}