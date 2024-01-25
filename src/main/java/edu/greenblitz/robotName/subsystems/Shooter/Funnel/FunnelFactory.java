package edu.greenblitz.robotName.subsystems.Shooter.Funnel;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.Shooter.Funnel.NeoFunnel.NeoFunnel;
import edu.greenblitz.robotName.subsystems.Shooter.Funnel.SimulationFunnel.SimulationFunnel;

public class FunnelFactory {
	public static IFunnel create() {
		return switch (RobotConstants.ROBOT_TYPE) {
			case ROBOT_NAME -> new NeoFunnel();
			case REPLAY -> new ReplayFunnel();
			case SIMULATION -> new SimulationFunnel();
		};
	}
}
