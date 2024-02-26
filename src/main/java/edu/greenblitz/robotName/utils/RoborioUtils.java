package edu.greenblitz.robotName.utils;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.Timer;

public class RoborioUtils {
	
	private static double lastTime;
	
	private static double currentTime;
	
	public static void updateCurrentCycleTime() {
		lastTime = currentTime;
		currentTime = Timer.getFPGATimestamp();
	}
	
	public static double getCurrentRoborioCycle() {
		return currentTime - lastTime;
	}

	public static boolean isCANConnectedToRoborio() {
		return getCANUtilization() > 0;
	}

	public static double getCANUtilization() {
		return RobotController.getCANStatus().percentBusUtilization * 100;
	}
}
