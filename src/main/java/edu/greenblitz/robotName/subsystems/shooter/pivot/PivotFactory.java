package edu.greenblitz.robotName.subsystems.shooter.pivot;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.subsystems.shooter.pivot.falconPivot.FalconPivot;
import edu.greenblitz.robotName.subsystems.shooter.pivot.simulationPivot.SimulationPivot;

public class PivotFactory {
	
	public static IPivot create() {
		return switch (Robot.getRobotType()) {
			case SYNCOPA -> new FalconPivot();
			case REPLAY -> new ReplayPivot();
			case SIMULATION, PEGA_SWERVE -> new SimulationPivot();
		};
	}
}