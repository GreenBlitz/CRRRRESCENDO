package edu.greenblitz.robotName.subsystems.swerve;

import edu.wpi.first.math.geometry.Rotation2d;

public class SwerveModuleConfigObject {

    public int angleMotorID;
    public int linearMotorID;
    public int AbsoluteEncoderID;
    public boolean linInverted;
    public Rotation2d encoderOffset;

    public SwerveModuleConfigObject(int angleMotorID, int linearMotorID, int AbsoluteEncoderID, Rotation2d encoderOffset, boolean linInverted) {
        this.angleMotorID = angleMotorID;
        this.linearMotorID = linearMotorID;
        this.AbsoluteEncoderID = AbsoluteEncoderID;
        this.linInverted = linInverted;
        this.encoderOffset = encoderOffset;
    }

    public SwerveModuleConfigObject(int angleMotorID, int linearMotorID, int AbsoluteEncoderID, boolean linInverted) {
        this(
                angleMotorID,
                linearMotorID,
                AbsoluteEncoderID,
                Rotation2d.fromRadians(0),
                linInverted
        );
    }


}
