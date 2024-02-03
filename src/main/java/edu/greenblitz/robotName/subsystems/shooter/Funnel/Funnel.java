package edu.greenblitz.robotName.subsystems.shooter.Funnel;

import edu.greenblitz.robotName.utils.GBSubsystem;
import org.littletonrobotics.junction.Logger;

import static edu.greenblitz.robotName.subsystems.shooter.Funnel.FunnelConstants.EJECT_POWER;
import static edu.greenblitz.robotName.subsystems.shooter.Funnel.FunnelConstants.ROLL_POWER;

public class Funnel extends GBSubsystem {
	private static Funnel instance;
	private FunnelInputsAutoLogged funnelInputs;
	private IFunnel funnel;
	boolean isObjectIn;
	private Funnel() {
		funnel = FunnelFactory.create();
		funnelInputs = new FunnelInputsAutoLogged();
		funnel.updateInputs(funnelInputs);
	}
	
	public static void init() {
		if (instance == null) {
			instance = new Funnel();
		}
	}
	
	public static Funnel getInstance() {
		init();
		return instance;
	}
	
	public void periodic() {
		funnel.updateInputs(funnelInputs);
		Logger.processInputs("funnel", funnelInputs);
	}

	
	public void setPower(double power) {
		funnel.setPower(power);
	}
	
	public void setVoltage(double voltage) {
		funnel.setVoltage(voltage);
	}


	public void rollIn(){
		setPower(ROLL_POWER);
	}

	public void rollOut(){
		setPower(EJECT_POWER);
	}

	public void stop() {
		setPower(0);
	}


	public double getVoltage() {
		return funnelInputs.appliedOutput;
	}
	
	public boolean isObjectIn() {
		return isObjectIn;
	}

	public void toggleObjectInside() {
		isObjectIn = !isObjectIn;
	}
	
}
