package edu.greenblitz.robotName.subsystems;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.RobotController;

public class Battery extends GBSubsystem {
	
	private static final double MIN_VOLTAGE_BATTERY = 0;
	private static Battery instance;
	private static PowerDistribution powerDistribution = new PowerDistribution(RobotConstants.General.POWER_DISTRIBUTION_CAN_ID,RobotConstants.General.POWER_DISTRIBUTION_TYPE);
	private Battery() {
	}
	
	public static Battery getInstance() {
		init();
		return instance;
	}

	public static void init(){
		if (instance == null) {
			instance = new Battery();
		}
	}

	public double getTotalCurrent (){
		return powerDistribution.getTotalCurrent();
	}

	public double getCurrentVoltage() {
		return RobotController.getBatteryVoltage();
	}

	public double getMinimumVoltage() {
		return MIN_VOLTAGE_BATTERY;
	}

}
