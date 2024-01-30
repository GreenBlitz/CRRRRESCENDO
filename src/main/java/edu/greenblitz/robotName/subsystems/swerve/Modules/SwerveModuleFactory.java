package edu.greenblitz.robotName.subsystems.swerve.Modules;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;
import edu.greenblitz.robotName.subsystems.swerve.Modules.kazaSwerveModule.KazaSwerveModule;
import edu.greenblitz.robotName.subsystems.swerve.Modules.mk4iSwerveModule.MK4ISwerveModule;
import edu.greenblitz.robotName.subsystems.swerve.Modules.replaySwerveModule.ReplaySwerveModule;
import edu.greenblitz.robotName.subsystems.swerve.Modules.simulationSwerveModule.SimulationSwerveModule;

public class SwerveModuleFactory {


    public static ISwerveModule create(SwerveChassis.Module module) {

        return switch (RobotConstants.ROBOT_TYPE) {
            case REPLAY -> new ReplaySwerveModule();
            case ROBOT_NAME -> new KazaSwerveModule(module);
            default -> new SimulationSwerveModule(module);
        };
    }
}
