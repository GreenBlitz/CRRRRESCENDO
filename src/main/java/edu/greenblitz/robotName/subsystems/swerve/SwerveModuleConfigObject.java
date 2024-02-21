package edu.greenblitz.robotName.subsystems.swerve;

import edu.wpi.first.math.geometry.Rotation2d;

public class SwerveModuleConfigObject {
	
	public int angleMotorID;

	public String canbusChain;
	
	public int linearMotorID;
	
	public int AbsoluteEncoderID;
	
	public boolean linInverted;
	
	public Rotation2d encoderOffset;
	
	public SwerveModuleConfigObject(String canbusChain, int angleMotorID, int linearMotorID, int AbsoluteEncoderID, Rotation2d encoderOffset, boolean linInverted) {
		this.canbusChain = canbusChain;
		this.angleMotorID = angleMotorID;
		this.linearMotorID = linearMotorID;
		this.AbsoluteEncoderID = AbsoluteEncoderID;
		this.linInverted = linInverted;
		this.encoderOffset = encoderOffset;
	}

	public SwerveModuleConfigObject(String canbusChain, int angleMotorID, int linearMotorID, int AbsoluteEncoderID, boolean linInverted) {
		this(
				canbusChain,
				angleMotorID,
				linearMotorID,
				AbsoluteEncoderID,
				Rotation2d.fromRadians(0),
				linInverted
		);
	}
}
