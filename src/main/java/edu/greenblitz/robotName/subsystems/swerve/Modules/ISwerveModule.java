package edu.greenblitz.robotName.subsystems.swerve.Modules;


import edu.wpi.first.math.geometry.Rotation2d;

public interface ISwerveModule {

    void setLinearVelocity(double speed);


    void rotateToAngle(Rotation2d angle);
    void setLinearVoltage(double voltage);
     void setAngularVoltage(double voltage);


     void setLinearIdleModeBrake(boolean isBrake);


     void setAngularIdleModeBrake(boolean isBrake);


     void resetAngle(Rotation2d angle);



     void stop();

     void updateInputs(SwerveModuleInputsAutoLogged inputs);





}
