package edu.greenblitz.robotName.commands;

import edu.greenblitz.robotName.commands.shooter.flyWheel.RunFlyWheelByVelocityConstant;
import edu.greenblitz.robotName.commands.swerve.MoveByJoystickAndRotateToSpeaker;
import edu.greenblitz.robotName.commands.swerve.RotateByScoringMode;
import edu.greenblitz.robotName.subsystems.swerve.chassis.ChassisConstants;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class PrepareToShoot extends ParallelCommandGroup {

    public PrepareToShoot() {
        super(
                new MoveByJoystickAndRotateToSpeaker(ChassisConstants.DRIVE_MODE),
                new RunFlyWheelByVelocityConstant()
        );
    }

}
