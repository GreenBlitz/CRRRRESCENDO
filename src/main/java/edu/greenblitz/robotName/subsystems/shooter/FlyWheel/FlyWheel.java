package edu.greenblitz.robotName.subsystems.shooter.FlyWheel;

import edu.greenblitz.robotName.utils.GBSubsystem;
import org.littletonrobotics.junction.Logger;

public class FlyWheel extends GBSubsystem {
 
	private static FlyWheel instance;
	
    private IFlyWheel flyWheel;
	
    private FlyWheelInputsAutoLogged flyWheelInputs;
	
    private boolean isPreparedToShoot;
	
	private FlyWheel() {
		flyWheel = FlyWheelFactory.create();
		flyWheelInputs = new FlyWheelInputsAutoLogged();
		flyWheel.updateInputs(flyWheelInputs);
		isPreparedToShoot = false;
	}
	
	public static void init() {
		if (instance == null) {
			instance = new FlyWheel();
		}
	}
	
	public static FlyWheel getInstance() {
		init();
		return instance;
	}
	
	public void setPower(double leftPower, double rightPower) {
		flyWheel.setPower(rightPower, leftPower);
	}
	
	public void setVoltage(double leftVoltage, double rightVoltage) {
		flyWheel.setVoltage(rightVoltage, leftVoltage);
	}
	
	public void setVelocity(double leftVelocity, double rightVelocity) {
		flyWheel.setVelocity(rightVelocity, leftVelocity);
	}
	
	public void stop() {
		flyWheel.setPower(0, 0);
	}
	
	public void setPreparedToShoot(boolean isPreparedToShoot) {
		this.isPreparedToShoot = isPreparedToShoot;
	}
	
	public double getRightSideVelocity() {
		return flyWheelInputs.rightFlywheelVelocity;
	}
	
	public double getRightSideCurrent() {
		return flyWheelInputs.rightFlywheelCurrent;
	}
	
	public double getRightSideVoltage() {
		return flyWheelInputs.rightFlywheelVoltage;
	}
	
	public double getLeftSideVelocity() {
		return flyWheelInputs.leftFlywheelVelocity;
	}
	
	public double getLeftSideCurrent() {
		return flyWheelInputs.leftFlywheelCurrent;
	}
	
	public double getLeftSideVoltage() {
		return flyWheelInputs.leftFlywheelVoltage;
	}
	
	public boolean getPreparedToShoot() {
		return isPreparedToShoot;
	}
	
	public boolean isRightWheelAtVelocity(double velocity) {
		return Math.abs(getRightSideVelocity() - velocity) < FlyWheelConstants.EPSILON_RPM;
	}
	
	public boolean isLeftWheelAtVelocity(double velocity) {
		return Math.abs(getLeftSideVelocity() - velocity) < FlyWheelConstants.EPSILON_RPM;
	}

	public boolean isAtVelocity(double rightWheelVelocity, double leftWheelVelocity) {
		return isRightWheelAtVelocity(rightWheelVelocity) && isLeftWheelAtVelocity(leftWheelVelocity);
	}
	
	@Override
	public void periodic() {
		flyWheel.updateInputs(flyWheelInputs);
		Logger.processInputs("FlyWheel", flyWheelInputs);
	}
}