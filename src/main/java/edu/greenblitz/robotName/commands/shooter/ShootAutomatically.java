package edu.greenblitz.robotName.commands.shooter;

import edu.greenblitz.robotName.commands.shooter.pivot.RotatePivotToAngleForAutonomousShooting;
import edu.greenblitz.robotName.commands.swerve.RotateChassisToAngleForAutoShooting;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ShootAutomatically extends SequentialCommandGroup {
    public ShootAutomatically(){
        super(
                new RotateChassisToAngleForAutoShooting(),
                new RotatePivotToAngleForAutonomousShooting(),
                new Shoot()
        );
    }
}
