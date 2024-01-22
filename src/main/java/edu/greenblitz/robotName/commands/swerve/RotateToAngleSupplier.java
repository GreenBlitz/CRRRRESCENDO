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
    public DoubleSupplier feedForwardSupplier;
    public PIDController pidController;


    public RotateToAngleSupplier (Supplier<Rotation2d> targetAngle){
        this(targetAngle,() -> 0);
    }
    public RotateToAngleSupplier (Supplier<Rotation2d> targetAngle,DoubleSupplier feedForwardSupplier){
        this.angleSupplier = targetAngle;
        this.feedForwardSupplier = feedForwardSupplier;
        pidController = new PIDController(0.4,0,0.1);
        pidController.setTolerance(Units.degreesToRadians(10));
        pidController.enableContinuousInput(0,2 * Math.PI);

    }
    @Override
    public double getAsDouble() {
        pidController.setSetpoint(angleSupplier.get().getRadians());
        pidController.setTolerance(Units.degreesToRadians(10));


        double pidOutput = pidController.calculate(
                SwerveChassis.getInstance().getChassisAngle().getRadians()
        );

        SmartDashboard.putNumber("setpoint", angleSupplier.get().getDegrees());
        SmartDashboard.putBoolean("is at setpoint" , isAtSetpoint());
        SmartDashboard.putBoolean("is at setset" , pidController.atSetpoint());

        return isAtSetpoint() || pidController.atSetpoint() ? 0 : pidOutput + feedForwardSupplier.getAsDouble();
    }

    public boolean isAtSetpoint(){
        return Math.abs(Math.IEEEremainder(angleSupplier.get().unaryMinus().getRadians() - SwerveChassis.getInstance().getChassisAngle().getRadians(), 2 * Math.PI)) < Units.degreesToRadians(10);
    }
}
