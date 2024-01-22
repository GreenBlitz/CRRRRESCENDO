package edu.greenblitz.robotName.subsystems.shooter.Funnel;

import edu.greenblitz.robotName.RobotConstants;

public class FunnelFactory {
	public static IFunnel create() {
		return switch (RobotConstants.ROBOT_TYPE) {
			case ROBOT_NAME -> new NeoFunnel();
			case SIMULATION -> new SimulationFunnel();
			default -> new ReplayFunnel();
		};
	}
}
