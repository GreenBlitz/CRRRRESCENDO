package edu.greenblitz.robotName.subsystems.ChassisIntake;

import edu.greenblitz.robotName.utils.GBSubsystem;
import org.littletonrobotics.junction.Logger;

public class ChassisIntake extends GBSubsystem {
	private static ChassisIntake instance;
	private static ChassisIntakeInputsAutoLogged chassisIntakeInputs;
	private static IChassisIntake chassisIntake;
	
	private ChassisIntake() {
		chassisIntake = ChassisIntakeFactory.create();
		chassisIntakeInputs = new ChassisIntakeInputsAutoLogged();
		chassisIntake.updateInputs(chassisIntakeInputs);
	}
	
	public ChassisIntake getInstance() {
		if (instance == null)
			instance = new ChassisIntake();
		return instance;
	}
	
	public void periodic() {
		chassisIntake.updateInputs(chassisIntakeInputs);
		Logger.processInputs("chassisIntake", chassisIntakeInputs);
	}
	
	public static void setPower(double power) {
		chassisIntake.setPower(power);
	}
	
	public static void setVoltage(double voltage) {
		chassisIntake.setVoltage(voltage);
	}
	
	public static double getVoltage() {
		return ChassisIntakeInputs.appliedOutput;
	}
	
	public static double getVelocity() {
		return chassisIntakeInputs.velocity;
	}
	
	public static void stop() {
		setPower(0);
	}
	

	
	
}
