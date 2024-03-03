package edu.greenblitz.robotName.commands.arm;

import edu.greenblitz.robotName.commands.arm.roller.CollectNoteToRoller;
import edu.greenblitz.robotName.commands.shooter.funnel.RunFunnelByVelocity;
import edu.greenblitz.robotName.subsystems.shooter.funnel.FunnelConstants;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;

public class NoteToArm extends ParallelDeadlineGroup {

    public NoteToArm() {
        super(
                new CollectNoteToRoller(),
                new RunFunnelByVelocity(-FunnelConstants.INTAKE_VELOCITY)
        );
    }

}
