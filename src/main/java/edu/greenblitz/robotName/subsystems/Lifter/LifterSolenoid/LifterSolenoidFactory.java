package edu.greenblitz.robotName.subsystems.Lifter.LifterSolenoid;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.Lifter.LifterSolenoid.RealLifterSolenoid.RealLifterSolenoid;
import edu.greenblitz.robotName.subsystems.Lifter.LifterSolenoid.ReplayLifterSolenoid.ReplayLifterSolenoid;
import edu.greenblitz.robotName.subsystems.Lifter.LifterSolenoid.SimulationLifterSolenoid.SimulationLifterSolenoid;

public class LifterSolenoidFactory {
	public static ILifterSolenoid create() {
		return switch (RobotConstants.ROBOT_TYPE) {
			case ROBOT_NAME -> new RealLifterSolenoid();
			case REPLAY -> new ReplayLifterSolenoid();
			case SIMULATION, PEGA_SWERVE -> new SimulationLifterSolenoid();
		};
	}
}
