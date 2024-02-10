package edu.greenblitz.robotName.subsystems.Intake;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.Intake.neoIntake.NeoIntake;
import edu.greenblitz.robotName.subsystems.Intake.simulationIntake.SimulationIntake;

public class IntakeFactory {
	public static IIntake create() {
        return switch (Robot.getRobotType()) {
            case ROBOT_NAME, PEGA_SWERVE -> new NeoIntake();
			case REPLAY -> new ReplayIntake();
			case SIMULATION -> new SimulationIntake();
        };
		
	}
}
