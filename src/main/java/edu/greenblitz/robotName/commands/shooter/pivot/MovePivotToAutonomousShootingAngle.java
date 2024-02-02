package edu.greenblitz.robotName.commands.shooter.pivot;

import edu.greenblitz.robotName.subsystems.shooter.Pivot.Pivot;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;
import edu.wpi.first.math.geometry.Rotation2d;

public class MovePivotToAutonomousShootingAngle extends MovePivotToAngle{

    public MovePivotToAutonomousShootingAngle(){
        super(SwerveChassis.getInstance().DefinedAngleForAutonomousShootingInDefinedPosition());
    }

}
