package edu.greenblitz.robotName.subsystems;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.RobotController;

public class Battery extends GBSubsystem {
	
	private static final double MIN_VOLTAGE_BATTERY = 11.97;
	private static Battery instance;

	private static PowerDistribution pdp = new PowerDistribution();
	private static PneumaticsControlModule pcm = new PneumaticsControlModule(RobotConstants.Pneumatics.PCM_ID);

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

	public double getCurrentUsage (){
		return  pdp.getTotalCurrent() + pcm.getCompressorCurrent();
	}

	public double getCurrentVoltage() {
		return RobotController.getBatteryVoltage();
	}

	public double getMinVoltage() {
		return MIN_VOLTAGE_BATTERY;
	}

}
