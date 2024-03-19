package edu.greenblitz.robotName.commands;

import edu.greenblitz.robotName.commands.shooter.flyWheel.RunFlyWheelByVelocity;
import edu.greenblitz.robotName.commands.shooter.flyWheel.RunFlyWheelByVelocityUntilInterrupted;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelConstants;
import edu.greenblitz.robotName.subsystems.shooter.funnel.Funnel;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class ShootToAmpZone extends ParallelCommandGroup {

    public ShootToAmpZone(SmartJoystick joystick) {
        super(
                new RunFlyWheelByVelocityUntilInterrupted(FlyWheelConstants.SHOOTING_AMP_ZONE_VELOCITY, joystick),
                new MovePivotToAngle(PivotConstants.PresetPositions.SHOOT_TO_US)
        );
    }

}
