package edu.greenblitz.robotName.commands.shooter;

import edu.greenblitz.robotName.commands.shooter.flyWheel.RunFlyWheelByVelocityConstant;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class DefaultShooting extends SequentialCommandGroup {
    public DefaultShooting(){
        super(
                new RunFlyWheelByVelocityConstant(),
                new PushNoteToFlyWheel()
        );
    }
}
