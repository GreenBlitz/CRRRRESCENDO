package edu.greenblitz.robotName.commands.shooter;

import edu.greenblitz.robotName.commands.shooter.flyWheel.StopFlyWheel;
import edu.greenblitz.robotName.commands.shooter.funnel.RunFunnelWithSafetyRotations;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelConstants;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class Shoot extends SequentialCommandGroup {

    public Shoot() {
        super(
                new ParallelRaceGroup(
                        new WaitCommand(FlyWheelConstants.DELAY_SECONDS_TILL_EXIT),
                        new RunFunnelWithSafetyRotations()
                ),
                new StopFlyWheel()
        );
    }
}
