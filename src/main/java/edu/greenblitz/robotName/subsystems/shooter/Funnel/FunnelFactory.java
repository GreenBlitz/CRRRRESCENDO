package edu.greenblitz.robotName.subsystems.shooter.Funnel;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.shooter.Funnel.NeoFunnel.NeoFunnel;
import edu.greenblitz.robotName.subsystems.shooter.Funnel.SimulationFunnel.SimulationFunnel;

public class FunnelFactory {
	public static IFunnel create() {
		return switch (RobotConstants.ROBOT_TYPE) {
			case ROBOT_NAME -> new NeoFunnel();
			case REPLAY -> new ReplayFunnel();
			default -> new SimulationFunnel();
		};
	}
}
