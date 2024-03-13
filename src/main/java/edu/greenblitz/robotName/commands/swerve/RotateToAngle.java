package edu.greenblitz.robotName.commands.swerve;

import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelConstants;
import edu.wpi.first.math.geometry.Rotation2d;
import java.util.function.Supplier;

public class RotateToAngle extends SwerveCommand {

    private Supplier<Rotation2d> angleSetPointSupplier;

    private Rotation2d angleSetPoint;
    
    private boolean preperd;
    private double timeInShootingSpeed;

    public RotateToAngle(Rotation2d angleSetPoint) {
        super();
        this.angleSetPointSupplier = () -> angleSetPoint;
        timeInShootingSpeed = 0;
        preperd = false;
    }

    public RotateToAngle(Supplier<Rotation2d> angleSetPointSupplier) {
        super();
        this.angleSetPointSupplier = angleSetPointSupplier;
        timeInShootingSpeed = 0;
        preperd = false;
    }

    @Override
    public void initialize() {
        angleSetPoint = angleSetPointSupplier.get();
    }

    @Override
    public void execute() {
        swerveChassis.rotateToAngle(angleSetPoint);
        if (swerveChassis.isAtAngle(angleSetPoint)) {
            timeInShootingSpeed++;
        } else {
            timeInShootingSpeed = 0;
        }
        preperd = timeInShootingSpeed >= 5;
        
    }

    @Override
    public boolean isFinished() {
        return preperd;
    }

    @Override
    public void end(boolean interrupted) {
        swerveChassis.stop();
    }
}