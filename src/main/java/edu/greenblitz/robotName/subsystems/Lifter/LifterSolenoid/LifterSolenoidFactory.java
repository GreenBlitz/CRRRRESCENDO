package edu.greenblitz.robotName.subsystems.Lifter.LifterSolenoid;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.Lifter.LifterSolenoid.SimulationLifterSolenoid.SimulationLifterSolenoid;
import edu.greenblitz.robotName.subsystems.Lifter.LifterSolenoid.RealLifterSolenoid.RealLifterSolenoid;
import edu.greenblitz.robotName.subsystems.Lifter.LifterSolenoid.ReplayLifterSolenoid.ReplayLifterSolenoid;

public class LifterSolenoidFactory {
	public static ILifterSolenoid create() {
		return switch (RobotConstants.ROBOT_TYPE) {
			case ROBOT_NAME -> new RealLifterSolenoid();
			case REPLAY -> new ReplayLifterSolenoid();
			case SIMULATION -> new SimulationLifterSolenoid();
		};
	}
}
