package edu.greenblitz.robotName.commands.shooter.funnel;

import edu.greenblitz.robotName.subsystems.shooter.Funnel.FunnelConstants;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class RunFunnelWithSafetyRotations extends SequentialCommandGroup {

    public RunFunnelWithSafetyRotations() {
        super(
                new RunFunnelByConstant(),
                new RunFunnelByRotations(FunnelConstants.SAFETY_ROTATIONS_TILL_OBJECT_EXITED)
        );
    }

}
