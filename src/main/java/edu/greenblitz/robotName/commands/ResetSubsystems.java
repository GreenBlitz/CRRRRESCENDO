package edu.greenblitz.robotName.commands;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.subsystems.arm.wrist.Wrist;
import edu.greenblitz.robotName.subsystems.shooter.pivot.Pivot;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;
import edu.greenblitz.robotName.subsystems.swerve.chassis.SwerveChassis;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.greenblitz.robotName.subsystems.arm.elbow.Elbow;
import edu.wpi.first.math.geometry.Rotation2d;

public class ResetSubsystems extends GBCommand {


    private void resetSubsystems() {
        Pivot.getInstance().moveToAngle(PivotConstants.PresetPositions.SAFE.ANGLE);
        Elbow.getInstance().moveToAngle(Rotation2d.fromRadians(0));
        SwerveChassis.getInstance().moveWheelsToAngleZero();
        Wrist.getInstance().moveToAngle(Rotation2d.fromRadians(0));
    }

    @Override
    public void initialize() {
        resetSubsystems();
    }

    @Override
    public void execute() {
        if (Robot.isSimulation()) {
            resetSubsystems();
        }
    }
}
