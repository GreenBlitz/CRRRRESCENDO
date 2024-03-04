package edu.greenblitz.robotName.commands.arm;

import edu.greenblitz.robotName.commands.arm.roller.CollectNoteToRoller;
import edu.greenblitz.robotName.commands.arm.roller.MoveNoteToMiddleOfRoller;
import edu.greenblitz.robotName.commands.arm.roller.runByPower.RunRollerCounterClockwiseUntilNoteIsInside;
import edu.greenblitz.robotName.commands.shooter.funnel.RunFunnelByVelocity;
import edu.greenblitz.robotName.subsystems.shooter.funnel.FunnelConstants;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class NoteToArm extends SequentialCommandGroup {

    public NoteToArm() {
        super(
                new ParallelDeadlineGroup(
                        new RunRollerCounterClockwiseUntilNoteIsInside(),
                        new RunFunnelByVelocity(-FunnelConstants.INTAKE_VELOCITY)
                ),
                new ParallelDeadlineGroup(
                        new MoveNoteToMiddleOfRoller(),
                        new RunFunnelByVelocity(-30)
                )
        );
    }

}
