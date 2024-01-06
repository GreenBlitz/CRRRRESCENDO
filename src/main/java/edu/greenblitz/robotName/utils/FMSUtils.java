package edu.greenblitz.robotName.utils;

import edu.wpi.first.wpilibj.DriverStation;
import org.littletonrobotics.junction.Logger;

public class FMSUtils {


    private static final DriverStation.Alliance DEFAULT_ALLIANCE = DriverStation.Alliance.Blue;

    public static DriverStation.Alliance getAlliance() {

        if (DriverStation.getAlliance().isPresent()) {
            Logger.recordOutput("FMS/Alliance", DriverStation.getAlliance().get().name());
            return DriverStation.getAlliance().get();
        }
        Logger.recordOutput("FMS/Alliance", "Failed to get alliance, returning default");
        return DEFAULT_ALLIANCE;
    }

}
