package edu.greenblitz.robotName.commands.switchMode;

import edu.greenblitz.robotName.ScoringModeSelector;
import edu.greenblitz.robotName.subsystems.arm.Wrist;
import edu.greenblitz.robotName.subsystems.shooter.Funnel.Funnel;
import edu.greenblitz.robotName.utils.ScoringMode;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.ProxyCommand;

public class SwitchBetweenScoringModes extends ProxyCommand {
    public SwitchBetweenScoringModes() {
        super(
                () -> new ConditionalCommand(
                        SwitchModeMotion.getCommand(ScoringMode.AMP, () -> Funnel.getInstance().isObjectIn()).get(),
                        SwitchModeMotion.getCommand(ScoringMode.SPEAKER, () -> Wrist.getInstance().isObjectInside()).get(),
                        ScoringModeSelector::isSpeakerMode
                )
        );
    }
}
