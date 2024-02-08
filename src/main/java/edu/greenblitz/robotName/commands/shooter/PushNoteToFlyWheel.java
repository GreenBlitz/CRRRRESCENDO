package edu.greenblitz.robotName.commands.shooter;

import edu.greenblitz.robotName.commands.shooter.flyWheel.RunFlyWheelByVelocity;
import edu.greenblitz.robotName.commands.shooter.flyWheel.StopFlyWheel;
import edu.greenblitz.robotName.commands.shooter.funnel.runByPowerAndCondition.RunFunnelByPower;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelConstants;
import edu.greenblitz.robotName.subsystems.shooter.Funnel.FunnelConstants;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class PushNoteToFlyWheel extends SequentialCommandGroup {

    public PushNoteToFlyWheel() {
        super(
                new ParallelCommandGroup(
                        new WaitCommand(FlyWheelConstants.DELAY_SECONDS_UNTIL_EXIT),
                        new RunFlyWheelByVelocity(FlyWheelConstants.SHOOTING_VELOCITY)
                ),
                new StopFlyWheel()
        );
    }
}
