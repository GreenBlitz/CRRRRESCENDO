package edu.greenblitz.robotName.subsystems.shooter.Funnel;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.shooter.Funnel.NeoFunnel.NeoFunnel;
import edu.greenblitz.robotName.subsystems.shooter.Funnel.SimulationFunnel.SimulationFunnel;

public class FunnelFactory {
	public static IFunnel create() {
		return switch (Robot.getRobotType()) {
			case ROBOT_NAME, PEGA_SWERVE -> new NeoFunnel();
			case REPLAY -> new ReplayFunnel();
			case SIMULATION -> new SimulationFunnel();
		};
	}
}
