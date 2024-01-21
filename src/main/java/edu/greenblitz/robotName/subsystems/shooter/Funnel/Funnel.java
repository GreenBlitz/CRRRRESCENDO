package edu.greenblitz.robotName.subsystems.shooter.Funnel;

import edu.greenblitz.robotName.utils.GBSubsystem;
import org.littletonrobotics.junction.Logger;

public class Funnel extends GBSubsystem {
	private static Funnel instance;
	private static FunnelInputsAutoLogged funnelInputs;
	private static IFunnel funnel;
	
	private Funnel() {
		funnel = FunnelFactory.create();
		funnelInputs = new FunnelInputsAutoLogged();
		funnel.updateInputs(funnelInputs);
	}
	
	public static void init() {
		if (instance == null)
			instance = new Funnel();
	}
	
	public static Funnel getInstance() {
		init();
		return instance;
	}
	
	public void periodic() {
		funnel.updateInputs(funnelInputs);
		Logger.processInputs("funnel", funnelInputs);
	}
	
	public static void setPower(double power) {
		funnel.setPower(power);
	}
	
	public static void setVoltage(double voltage) {
		funnel.setVoltage(voltage);
	}
	
	public static double getVoltage() {
		return funnelInputs.appliedOutput;
	}
	
	public static double getVelocity() {
		return funnelInputs.velocity;
	}
	
	public static boolean isObjectIn() {
		return funnelInputs.isObjectIn;
	}
	
	public static void stop() {
		setPower(0);
	}
	
	
}
