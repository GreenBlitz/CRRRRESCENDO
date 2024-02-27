package edu.greenblitz.robotName.subsystems.swerve.modules;

import edu.greenblitz.robotName.subsystems.Battery;
import edu.greenblitz.robotName.subsystems.swerve.chassis.SwerveChassis;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import org.littletonrobotics.junction.Logger;

public class SwerveModule {

	ISwerveModule swerveModule;
	
	SwerveModuleInputsAutoLogged swerveModuleInputs;
	
	SwerveChassis.Module module;
	
	public Rotation2d targetAngle;
	
	public double targetVelocity;
	
	public SwerveModule(SwerveChassis.Module module) {
		this.module = module;
		
		this.swerveModule = SwerveModuleFactory.create(module);
		
		swerveModuleInputs = new SwerveModuleInputsAutoLogged();
		this.periodic();
	}
	
	public void periodic() {
		swerveModule.updateInputs(swerveModuleInputs);
		Logger.processInputs("DriveTrain/Module" + this.module.toString(), swerveModuleInputs);
	}
	
	public void rotateToAngle(Rotation2d angle) {
		targetAngle = angle;
		
		double rotationDifferance = angle.getRadians() - getModuleAngle().getRadians();
		rotationDifferance -= rotationDifferance > Math.PI ? 2 * Math.PI : 0;
		
		swerveModule.rotateToAngle(Rotation2d.fromRadians(getModuleAngle().getRadians() + rotationDifferance));
	}
	
	public Rotation2d getModuleAngle() {
		return Rotation2d.fromRadians(swerveModuleInputs.angularPositionRadians);
		//return Rotation2d.fromRadians(Math.IEEEremainder(swerveModuleInputs.angularPositionRadians, 2 * Math.PI));
	}
	
	public double getCurrentVelocity() {
		return swerveModuleInputs.linearVelocity;
	}
	
	public double getCurrentMeters() {
		return swerveModuleInputs.linearMetersPassed;
	}
	
	public SwerveModulePosition getCurrentPosition() {
		return new SwerveModulePosition(getCurrentMeters(), getModuleAngle());
	}
	
	public void resetEncoderToValue(Rotation2d angle) {
		swerveModule.resetAngle(angle);
	}
	
	public void resetEncoderToValue() {
		swerveModule.resetAngle(Rotation2d.fromRadians(0));
	}
	
	public void resetEncoderByAbsoluteEncoder() {
		resetEncoderToValue(Rotation2d.fromRadians(swerveModuleInputs.absoluteEncoderPosition));
	}
	
	public void setLinearVelocity(double speed) {
		targetVelocity = speed;
		swerveModule.setLinearVelocity(speed);
	}
	
	public void stop() {
		swerveModule.stop();
	}
	
	public SwerveModuleState getModuleState() {
		return new SwerveModuleState(
				getCurrentVelocity(),
				getModuleAngle()
		);
	}
	
	public boolean isAtAngle(Rotation2d targetAngle, Rotation2d errorAngleTolerance) {
		return (getModuleAngle().getRadians() - targetAngle.getRadians()) % (2 * Math.PI) < errorAngleTolerance.getRadians() ||
				(targetAngle.getRadians() - getModuleAngle().getRadians()) % (2 * Math.PI) < errorAngleTolerance.getRadians();
	}
	
	public boolean isAtAngle(Rotation2d errorAngleTolerance) {
		return isAtAngle(targetAngle, errorAngleTolerance);
	}

	public static Rotation2d  calculateContinuousInputSetpoint(Rotation2d currentAngle, Rotation2d targetAngleSetpoint) {
		double remainder = currentAngle.getRadians() % (Math.PI * 2);
		double adjustedAngleSetpoint = targetAngleSetpoint.getRadians() + (currentAngle.getRadians() - remainder);
		if (adjustedAngleSetpoint - currentAngle.getRadians() > Math.PI) {
			adjustedAngleSetpoint -= Math.PI * 2;
		} else if (adjustedAngleSetpoint - currentAngle.getRadians() < -Math.PI) {
			adjustedAngleSetpoint += Math.PI * 2;
		}

		return Rotation2d.fromRadians(adjustedAngleSetpoint);
	}
	
	public void setModuleState(SwerveModuleState moduleState) {
		SwerveModuleState optimizedModuleState = SwerveModuleState.optimize(moduleState, getModuleAngle());
		optimizedModuleState.angle = calculateContinuousInputSetpoint(getModuleAngle(), optimizedModuleState.angle);
		setLinearVelocity(optimizedModuleState.speedMetersPerSecond);
		rotateToAngle(optimizedModuleState.angle);
	}
	
	public Rotation2d getAbsoluteEncoderPosition() {
		return Rotation2d.fromRadians(swerveModuleInputs.absoluteEncoderPosition);
	}
	
	public double getLinearCurrent() {
		return swerveModuleInputs.linearCurrent;
	}
	
	public void setRotationalPower(double power) {
		swerveModule.setAngularVoltage(power * Battery.getInstance().getCurrentVoltage());
	}
	
	public void setLinearPower(double power) {
		swerveModule.setLinearVoltage(power * Battery.getInstance().getCurrentVoltage());
	}
	
	public void setLinearIdleModeBrake() {
		swerveModule.setLinearIdleModeBrake(true);
	}
	
	public void setLinearIdleModeCoast() {
		swerveModule.setLinearIdleModeBrake(false);
	}
	
	public void setAngularIdleModeBrake() {
		swerveModule.setAngularIdleModeBrake(true);
	}
	
	public void setRotIdleModeCoast() {
		swerveModule.setAngularIdleModeBrake(false);
	}
	
	public boolean isEncoderBroken() {
		return !swerveModuleInputs.isAbsoluteEncoderConnected;
	}
	
	public double getLinVoltage() {
		return swerveModuleInputs.linearVoltage;
	}
}

