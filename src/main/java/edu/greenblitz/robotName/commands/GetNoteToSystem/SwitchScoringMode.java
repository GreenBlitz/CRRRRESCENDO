package edu.greenblitz.robotName.commands.GetNoteToSystem;

import edu.greenblitz.robotName.subsystems.CurrentScoringMode;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.greenblitz.robotName.commands.intake.MoveNoteBetweenShooterArm;

public class SwitchScoringMode extends ConditionalCommand {
    public SwitchScoringMode() {
        super(
                new GBCommand() {},

                new SequentialCommandGroup(
                        new GetToTransferNotePosition(),
                        new MoveNoteBetweenShooterArm(
                                () -> CurrentScoringMode.getCurrentScoringMode().equals(CurrentScoringMode.ScoringMode.SPEAKER)
                        )
                ),

                () -> CurrentScoringMode.getCurrentScoringMode().equals(CurrentScoringMode.ScoringMode.IN_BETWEEN)
        );
    }
}
