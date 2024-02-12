package edu.greenblitz.robotName.subsystems.shooter.funnel;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.subsystems.shooter.funnel.bagFunnel.BagFunnel;
import edu.greenblitz.robotName.subsystems.shooter.funnel.simulationFunnel.SimulationFunnel;

public class FunnelFactory {
	
	public static IFunnel create() {
		return switch (Robot.getRobotType()) {
			case ROBOT_NAME, PEGA_SWERVE -> new BagFunnel();
			case REPLAY -> new ReplayFunnel();
			case SIMULATION -> new SimulationFunnel();
		};
	}
}