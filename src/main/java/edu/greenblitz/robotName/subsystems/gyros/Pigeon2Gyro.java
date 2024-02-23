package edu.greenblitz.robotName.subsystems.gyros;

import com.ctre.phoenix6.hardware.Pigeon2;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;

public class Pigeon2Gyro implements IAngleMeasurementGyro {
	
	private Pigeon2 pigeon2;
	
	private Rotation2d yawOffset;
	
	private Rotation2d pitchOffset;
	
	private Rotation2d rollOffset;
	
	private GyroInputsAutoLogged lastInputs;
	
	public Pigeon2Gyro(int deviceID) {
		pigeon2 = new Pigeon2(deviceID);
		lastInputs = new GyroInputsAutoLogged();
		
		this.pitchOffset = new Rotation2d();
		this.rollOffset = new Rotation2d();
		this.yawOffset = new Rotation2d();
	}
	
	@Override
	public void updateYaw(Rotation2d yaw) {
		pigeon2.setYaw(yaw.getDegrees());
	}
	
	@Override
	public void updatePitch(Rotation2d pitch) {
		pitchOffset = Rotation2d.fromDegrees(pigeon2.getPitch().getValue() - pitch.getDegrees());
	}
	
	@Override
	public void updateRoll(Rotation2d roll) {
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