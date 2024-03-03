package edu.greenblitz.robotName.commands.shooter;

import edu.greenblitz.robotName.commands.shooter.flyWheel.RunFlyWheelByVelocityConstant;
import edu.greenblitz.robotName.commands.shooter.flyWheel.StopFlyWheel;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ShootFromInFunnel extends SequentialCommandGroup {

    public ShootFromInFunnel() {
        super(
                new RunFlyWheelByVelocityConstant(),
                new PushNoteToFlyWheel(),
                new StopFlyWheel()
        );
    }
}