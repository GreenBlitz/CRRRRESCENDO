package edu.greenblitz.robotName.commands.swerve;

import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

public class RotateToAngleSupplier implements DoubleSupplier {


    public Supplier<Rotation2d> angleSupplier;
    public PIDController pidController;


    public RotateToAngleSupplier (Supplier<Rotation2d> targetAngle){
        this.angleSupplier = targetAngle;
        pidController = new PIDController(0.6,0,0);
        pidController.enableContinuousInput(0,2 * Math.PI);
    }
    @Override
    public double getAsDouble() {
        pidController.setSetpoint(angleSupplier.get().getRadians());
        pidController.setTolerance(Units.degreesToRadians(10));


        double pidOutput = pidController.calculate(
                SwerveChassis.getInstance().getChassisAngle().getRadians()
        );

        return isAtSetpoint() ? 0 : pidOutput;
    }

    public boolean isAtSetpoint(){
        return Math.abs(SwerveChassis.getInstance().getChassisAngle().getRadians() + angleSupplier.get().getRadians()) < Units.degreesToRadians(5);
    }
}
