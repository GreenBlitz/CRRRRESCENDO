package edu.greenblitz.robotName.subsystems.intake;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.subsystems.intake.neoIntake.NeoIntake;
import edu.greenblitz.robotName.subsystems.intake.simulationIntake.SimulationIntake;

public class IntakeFactory {
	
	public static IIntake create() {
		return switch (Robot.getRobotType()) {
			case ROBOT_NAME, PEGA_SWERVE -> new NeoIntake();
			case REPLAY -> new ReplayIntake();
			case SIMULATION -> new SimulationIntake();
		};
	}
}
