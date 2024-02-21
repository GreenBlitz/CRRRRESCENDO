package edu.greenblitz.robotName.subsystems.shooter.FlyWheel;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.falconFlyWheel.FalconFlyWheel;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.simulationFlyWheel.SimulationFlyWheel;

public class FlyWheelFactory {
	
	public static IFlyWheel create() {
		return switch (Robot.getRobotType()) {
			case SYNCOPA, PEGA_SWERVE -> new FalconFlyWheel();
			case REPLAY -> new ReplayFlyWheel();
			case SIMULATION -> new SimulationFlyWheel();
		};
	}
}