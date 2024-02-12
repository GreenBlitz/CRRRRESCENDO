package edu.greenblitz.robotName.subsystems.swerve.modules;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.subsystems.swerve.chassis.SwerveChassis;
import edu.greenblitz.robotName.subsystems.swerve.modules.kazaSwerveModule.KazaSwerveModule;
import edu.greenblitz.robotName.subsystems.swerve.modules.mk4iSwerveModule.MK4ISwerveModule;
import edu.greenblitz.robotName.subsystems.swerve.modules.replaySwerveModule.ReplaySwerveModule;
import edu.greenblitz.robotName.subsystems.swerve.modules.simulationSwerveModule.SimulationSwerveModule;

public class SwerveModuleFactory {
	
	public static ISwerveModule create(SwerveChassis.Module module) {
		return switch (Robot.getRobotType()) {
			case REPLAY -> new ReplaySwerveModule();
			case ROBOT_NAME -> new MK4ISwerveModule(module);
			case PEGA_SWERVE -> new KazaSwerveModule(module);
			case SIMULATION -> new SimulationSwerveModule(module);
		};
	}
}
