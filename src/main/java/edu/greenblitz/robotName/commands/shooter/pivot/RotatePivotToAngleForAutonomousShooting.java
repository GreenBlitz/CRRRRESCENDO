package edu.greenblitz.robotName.commands.shooter.pivot;

import edu.greenblitz.robotName.subsystems.shooter.Pivot.Pivot;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;

public class RotatePivotToAngleForAutonomousShooting extends MovePivotToAngle{
    public RotatePivotToAngleForAutonomousShooting(){
        super(Pivot.getInstance().getPivotAngleForAutoShooting());
    }
}
