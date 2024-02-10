package edu.greenblitz.robotName.commands.shooter;

import edu.greenblitz.robotName.commands.shooter.flyWheel.RunFlyWheelByApropriateVelocity;
import edu.greenblitz.robotName.commands.shooter.flyWheel.StopFlyWheel;
import edu.greenblitz.robotName.commands.shooter.funnel.runByPowerUntilCondition.ForwardRunFunnelUntilObjectOut;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelConstants;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class PushNoteToFlyWheel extends SequentialCommandGroup {

    public PushNoteToFlyWheel() {
        super(
                new ForwardRunFunnelUntilObjectOut(),
                new WaitCommand(FlyWheelConstants.DELAY_SECONDS_UNTIL_EXIT),
                new StopFlyWheel()
        );
    }
}
