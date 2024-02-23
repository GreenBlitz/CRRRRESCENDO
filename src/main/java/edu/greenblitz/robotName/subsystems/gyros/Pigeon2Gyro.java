package edu.greenblitz.robotName.subsystems.gyros;

import com.ctre.phoenix6.configs.Pigeon2Configuration;
import com.ctre.phoenix6.hardware.Pigeon2;
import com.ctre.phoenix6.hardware.core.CorePigeon2;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.swerve.chassis.SwerveChassis;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Pigeon2Gyro implements IAngleMeasurementGyro {
	
	private Pigeon2 pigeon2;
	
	private Rotation2d yawOffset;
	
	private Rotation2d pitchOffset;
	
	private Rotation2d rollOffset;
	
	private GyroInputsAutoLogged lastInputs;
	
	public Pigeon2Gyro(int deviceID) {
		pigeon2 = new Pigeon2(deviceID, RobotConstants.General.CANIVORE_NAME);
		lastInputs = new GyroInputsAutoLogged();

		this.pitchOffset = new Rotation2d();
		this.rollOffset = new Rotation2d();
		this.yawOffset = new Rotation2d();
	}

	public double getAngle(){
		return pigeon2.getYaw().getValue();
	}

	@Override
	public void updateYaw(Rotation2d yaw) {
		pigeon2.setYaw(yaw.getDegrees());
	}

	
	@Override
	public void updatePitch(Rotation2d pitch) {
//		pitchOffset.plus(pitch.plus(Rotation2d.fromRadians(lastInputs.pitch)));
		pitchOffset = Rotation2d.fromDegrees(pigeon2.getPitch().getValue() - pitch.getDegrees());
	}
	
	@Override
	public void updateRoll(Rotation2d roll) {
//		rollOffset.plus(roll.plus(Rotation2d.fromRadians(lastInputs.roll)));
		rollOffset = Rotation2d.fromDegrees(pigeon2.getRoll().getValue() - roll.getDegrees());
	}
	
	@Override
	public void updateInputs(GyroInputsAutoLogged inputs) {
		inputs.yaw = (Units.degreesToRadians(pigeon2.getYaw().getValue()) - yawOffset.getRadians()) % (2 * Math.PI);
		inputs.pitch = (Units.degreesToRadians(pigeon2.getPitch().getValue()) - pitchOffset.getRadians()) % (2 * Math.PI);
		inputs.roll = (Units.degreesToRadians(pigeon2.getRoll().getValue()) - rollOffset.getRadians()) % (2 * Math.PI);
		
		lastInputs = inputs;
	}
}