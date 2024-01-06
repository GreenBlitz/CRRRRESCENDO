package edu.greenblitz.robotName.subsystems.Gyros;


import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;
import edu.wpi.first.math.geometry.Rotation2d;

public class SimulationGyro implements IAngleMeasurementGyro {

    private GyroInputsAutoLogged lastInputs = new GyroInputsAutoLogged();

    @Override
    public void updateYaw(Rotation2d yaw) {
        lastInputs.yaw = yaw.getRadians();
    }

    @Override
    public void updatePitch(Rotation2d pitch) {
        lastInputs.pitch = pitch.getRadians();
    }

    @Override
    public void updateRoll(Rotation2d roll) {
        lastInputs.roll = roll.getRadians();
    }

    @Override
    public void updateInputs(GyroInputsAutoLogged inputs) {
        inputs.roll = 0;
        inputs.pitch = 0;
        inputs.yaw += SwerveChassis.getInstance().getChassisSpeeds().omegaRadiansPerSecond * RobotConstants.SimulationConstants.TIME_STEP;

        lastInputs = inputs;
    }

}
