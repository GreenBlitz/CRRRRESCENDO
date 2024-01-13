package edu.greenblitz.robotName.subsystems.Gyros;

import com.ctre.phoenix6.hardware.Pigeon2;
import edu.wpi.first.math.geometry.Rotation2d;

public class Pigeon2Gyro implements IAngleMeasurementGyro{
	private Pigeon2 pigeon2;
	Rotation2d yawOffset;
	Rotation2d pitchOffset;
	Rotation2d rollOffset;
	private GyroInputsAutoLogged lastInputs = new GyroInputsAutoLogged();
	public void Pigeon2Gyro(int deviceID){
		pigeon2 = new Pigeon2(deviceID);
		
		this.pitchOffset = new Rotation2d();
		this.rollOffset = new Rotation2d();
		this.yawOffset = new Rotation2d();
	}
	@Override
	public void updateYaw(Rotation2d yaw) {
		yawOffset.minus(yaw.plus(Rotation2d.fromRadians(lastInputs.yaw)));
	}
	
	@Override
	public void updatePitch(Rotation2d pitch) {
		pitchOffset.plus(pitch.plus(Rotation2d.fromRadians(lastInputs.pitch)));
	}
	
	@Override
	public void updateRoll(Rotation2d roll) {
		rollOffset.plus(roll.plus(Rotation2d.fromRadians(lastInputs.roll)));
	}
	
	@Override
	public void updateInputs(GyroInputsAutoLogged inputs) {
		inputs.yaw = -(Math.toRadians(pigeon2.getYaw().getValue()) - yawOffset.getRadians()) % (2 * Math.PI);
		inputs.pitch = (Math.toRadians(pigeon2.getPitch().getValue()) - pitchOffset.getRadians()) % (2 * Math.PI);
		inputs.roll = (Math.toRadians(pigeon2.getRoll().getValue()) - rollOffset.getRadians()) % (2 * Math.PI);
		
		lastInputs = inputs;
	}
}