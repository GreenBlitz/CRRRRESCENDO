package edu.greenblitz.robotName.subsystems.shooter.Funnel;

import edu.greenblitz.robotName.RobotConstants;

public class FunnelFactory {
	
	public static IFunnel create() {
		switch (RobotConstants.ROBOT_TYPE) {
			case ROBOT_NAME:
				return new NeoFunnel();
			case SIMULATION:
				return new SimulationFunnel();
			case REPLAY:
			default:
				return new ReplayFunnel();
		}
	}
}
