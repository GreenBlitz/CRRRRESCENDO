package edu.greenblitz.robotName.subsystems.Gyros;


import edu.wpi.first.math.geometry.Rotation2d;

public interface IAngleMeasurementGyro {

    void updateYaw(Rotation2d yaw);

    void updatePitch(Rotation2d pitch);

    void updateRoll(Rotation2d roll);


    void updateInputs(GyroInputsAutoLogged inputs);


}
