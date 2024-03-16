package edu.greenblitz.robotName.commands.getNoteToSystem;

import edu.greenblitz.robotName.ScoringModeSelector;
import edu.greenblitz.robotName.commands.intake.IntakeCommand;
import edu.greenblitz.robotName.commands.intake.NoteFromIntakeToShooterWithArm;
import edu.greenblitz.robotName.commands.shooter.funnel.AlignNoteInFunnel;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class CollectNoteToScoringModeForJoystick extends IntakeCommand {

    @Override
    public void execute() {
        intake.rollIn();
    }

    @Override
    public boolean isFinished() {
        return intake.isObjectIn();
    }

    @Override
    public void end(boolean interrupted) {
        intake.stop();
        if (!interrupted) {
            new NoteFromIntakeToShooterWithArm()
                    .andThen(new AlignNoteInFunnel())
                    .andThen(new ConditionalCommand(
                                    new TransferNote(),
                                    new InstantCommand(),
                                    ScoringModeSelector::isAmpMode
                            )
                    ).schedule();
        }
    }

}
