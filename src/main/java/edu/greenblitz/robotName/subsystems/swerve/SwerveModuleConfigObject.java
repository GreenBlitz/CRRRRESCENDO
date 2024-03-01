package edu.greenblitz.robotName.subsystems.swerve;

import edu.wpi.first.math.geometry.Rotation2d;

public class SwerveModuleConfigObject {
	
	public int angleMotorID;

	public String canbusChain;
	
	public int linearMotorID;
	
	public int AbsoluteEncoderID;
	
	public boolean linearInverted;

	public boolean angularInverted;

	public Rotation2d encoderOffset;
	
	public SwerveModuleConfigObject(String canbusChain, int angleMotorID, int linearMotorID, int AbsoluteEncoderID, Rotation2d encoderOffset, boolean linearInverted) {
		this.canbusChain = canbusChain;
		this.angleMotorID = angleMotorID;
		this.linearMotorID = linearMotorID;
		this.AbsoluteEncoderID = AbsoluteEncoderID;
		this.linearInverted = linearInverted;
		this.encoderOffset = encoderOffset;
	}
	public SwerveModuleConfigObject(String canbusChain, int angleMotorID, int linearMotorID, int AbsoluteEncoderID, Rotation2d encoderOffset, boolean linearInverted, boolean angularInverted) {
		this.canbusChain = canbusChain;
		this.angleMotorID = angleMotorID;
		this.linearMotorID = linearMotorID;
		this.AbsoluteEncoderID = AbsoluteEncoderID;
		this.linearInverted = linearInverted;
		this.encoderOffset = encoderOffset;
		this.angularInverted = angularInverted;
	}

	public SwerveModuleConfigObject(String canbusChain, int angleMotorID, int linearMotorID, int AbsoluteEncoderID, boolean linearInverted, boolean angularInverted) {
		this(
				canbusChain,
				angleMotorID,
				linearMotorID,
				AbsoluteEncoderID,
				Rotation2d.fromRadians(0),
				linearInverted,
				angularInverted
		);
	}
	public SwerveModuleConfigObject(String canbusChain, int angleMotorID, int linearMotorID, int AbsoluteEncoderID, boolean linearInverted) {
		this(
				canbusChain,
				angleMotorID,
				linearMotorID,
				AbsoluteEncoderID,
				Rotation2d.fromRadians(0),
				linearInverted,
				false
		);
	}
}
