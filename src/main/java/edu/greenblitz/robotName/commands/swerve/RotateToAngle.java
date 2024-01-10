package edu.greenblitz.robotName.commands.swerve;

import edu.greenblitz.robotName.subsystems.swerve.Chassis.ChassisConstants;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;
import edu.wpi.first.math.geometry.Rotation2d;

public class RotateToAngle extends SwerveCommand{
    private Rotation2d rotation2d;

    public RotateToAngle(Rotation2d rotation2d){
        super();
        this.rotation2d = rotation2d;
    }

    @Override
    public void initialize(){
        ChassisConstants.ROTATION_PID_CONTROLLER.setSetpoint(rotation2d.getRadians());
    }

    @Override
    public void execute(){
        SwerveChassis.getInstance().moveByChassisSpeeds(
                0,
                0,
                ChassisConstants.ROTATION_PID_CONTROLLER.calculate(
                        SwerveChassis.getInstance().getChassisAngle().getRadians()),
                SwerveChassis.getInstance().getChassisAngle());
    }

    @Override
    public void end(boolean interrupted){
        SwerveChassis.getInstance().stop();
    }
}
