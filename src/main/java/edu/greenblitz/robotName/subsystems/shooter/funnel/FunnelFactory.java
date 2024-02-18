package edu.greenblitz.robotName.subsystems.shooter.funnel;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.subsystems.shooter.funnel.bagFunnel.BagFunnel;
import edu.greenblitz.robotName.subsystems.shooter.funnel.neoFunnel.NeoFunnel;
import edu.greenblitz.robotName.subsystems.shooter.funnel.simulationFunnel.SimulationFunnel;

public class FunnelFactory {
	
	public static IFunnel create() {
		return switch (Robot.getRobotType()) {
			case SYNCOPA -> new NeoFunnel();
			case REPLAY -> new ReplayFunnel();
			case SIMULATION, PEGA_SWERVE -> new SimulationFunnel();
		};
	}
}