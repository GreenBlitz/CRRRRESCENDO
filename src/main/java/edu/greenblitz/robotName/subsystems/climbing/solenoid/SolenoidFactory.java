package edu.greenblitz.robotName.subsystems.climbing.solenoid;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.subsystems.climbing.solenoid.simulationSolenoid.SimulationSolenoid;
import edu.greenblitz.robotName.subsystems.climbing.solenoid.talonSRXSolenoid.TalonSRXSolenoid;

public class SolenoidFactory {

	public static ISolenoid create() {
		return switch (Robot.getRobotType()) {
			case SYNCOPA -> new TalonSRXSolenoid();
			case REPLAY -> new ReplaySolenoid();
			case SIMULATION, PEGA_SWERVE -> new SimulationSolenoid();
		};
	}
}
