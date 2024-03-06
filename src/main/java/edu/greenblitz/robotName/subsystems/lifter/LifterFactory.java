package edu.greenblitz.robotName.subsystems.lifter;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.subsystems.lifter.FalconLifter.FalconLifter;
import edu.greenblitz.robotName.subsystems.lifter.neoLifter.NeoLifter;
import edu.greenblitz.robotName.subsystems.lifter.replayLifter.ReplayLifter;
import edu.greenblitz.robotName.subsystems.lifter.simulationLifter.SimulationLifter;

public class LifterFactory {
 
	public static ILifter create() {
		return switch (Robot.getRobotType()) {
			case SYNCOPA -> new FalconLifter();
			case REPLAY -> new ReplayLifter();
			case SIMULATION, PEGA_SWERVE -> new SimulationLifter();
		};
	}
}