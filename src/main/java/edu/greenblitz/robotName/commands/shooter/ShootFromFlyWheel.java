package edu.greenblitz.robotName.commands.shooter;

import edu.greenblitz.robotName.commands.shooter.flyWheel.StopFlyWheel;
import edu.greenblitz.robotName.commands.shooter.funnel.runByPowerAndCondition.RunFunnelByPower;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelConstants;
import edu.greenblitz.robotName.subsystems.shooter.Funnel.FunnelConstants;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class ShootFromFlyWheel extends SequentialCommandGroup {

    public ShootFromFlyWheel() {
        super(
                new ParallelRaceGroup(
                        new WaitCommand(FlyWheelConstants.DELAY_SECONDS_TILL_EXIT),
                        new RunFunnelByPower(FunnelConstants.ROLL_POWER)
                ),
                new StopFlyWheel()
        );
    }
}
