package edu.greenblitz.robotName.commands.swerve;

import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelConstants;
import edu.wpi.first.math.geometry.Rotation2d;
import java.util.function.Supplier;

public class RotateToAngle extends SwerveCommand {

    private Supplier<Rotation2d> angleSetPointSupplier;
    
    private boolean preperd;
    private double timeInShootingSpeed;

    public RotateToAngle(Rotation2d angleSetPoint) {
        super();
        this.angleSetPointSupplier = () -> angleSetPoint.plus(Rotation2d.fromRotations(0.5));
        timeInShootingSpeed = 0;
        preperd = false;
    }

    public RotateToAngle(Supplier<Rotation2d> angleSetPointSupplier) {
        super();
        this.angleSetPointSupplier = () -> angleSetPointSupplier.get().plus(Rotation2d.fromRotations(0.5));
        timeInShootingSpeed = 0;
        preperd = false;
    }
    
    @Override
    public void execute() {
        swerveChassis.rotateToAngle(angleSetPointSupplier.get());
        if (swerveChassis.isAtAngle(angleSetPointSupplier.get())) {
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