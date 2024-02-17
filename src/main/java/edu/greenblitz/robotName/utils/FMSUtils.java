package edu.greenblitz.robotName.utils;

import edu.greenblitz.robotName.subsystems.arm.roller.Roller;
import edu.greenblitz.robotName.subsystems.intake.Intake;
import edu.greenblitz.robotName.subsystems.shooter.funnel.Funnel;
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
	
	public static boolean isRedAlliance() {
		return getAlliance() == DriverStation.Alliance.Red;
	}
	
	public static boolean isBlueAlliance() {
		return getAlliance() == DriverStation.Alliance.Blue;
	}
	
	public static String getNotePlacement() {
		if (Roller.getInstance().isObjectIn()) {
			return "Roller";
		}
		if (Funnel.getInstance().isObjectIn()) {
			return "Funnel";
		}
		if (Intake.getInstance().getEntranceBeamBreakerValue()) {
			return "Intake Entrance";
		}
		if (Intake.getInstance().getExitBeamBreakerValue()) {
			return "Intake Exit";
		}
		return "No Note In Robot";
	}
}
