package edu.greenblitz.robotName.commands.GetNoteToSystem;

import edu.greenblitz.robotName.ScoringModeSelector;
import edu.greenblitz.robotName.subsystems.arm.Wrist;
import edu.greenblitz.robotName.subsystems.shooter.Funnel.Funnel;
import edu.greenblitz.robotName.utils.ScoringMode;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;

public class SwitchBetweenScoringModes extends ConditionalCommand {
    public SwitchBetweenScoringModes() {
        super(
                new SwitchModeMotion(ScoringMode.AMP, () -> Funnel.getInstance().isObjectIn()),
                new SwitchModeMotion(ScoringMode.SPEAKER, () -> Wrist.getInstance().isObjectInside()),
                () -> ScoringModeSelector.isSpeakerToAmp()
        );
    }
}
