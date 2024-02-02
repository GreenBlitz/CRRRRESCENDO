package edu.greenblitz.robotName.commands.switchMode;

import edu.greenblitz.robotName.ScoringModeSelector;
import edu.greenblitz.robotName.subsystems.arm.Roller;
import edu.greenblitz.robotName.subsystems.shooter.Funnel.Funnel;
import edu.greenblitz.robotName.utils.ScoringMode;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.ProxyCommand;

public class FullySwitchScoringMode extends ProxyCommand {
    public FullySwitchScoringMode() {
        super(
                () -> new ConditionalCommand(
                        SwitchingScoringModeMovement.getCommand(ScoringMode.AMP, () -> Funnel.getInstance().isObjectIn()),
                        SwitchingScoringModeMovement.getCommand(ScoringMode.SPEAKER, () -> Roller.getInstance().isObjectInside()),
                        ScoringModeSelector::isSpeakerMode
                )
        );
    }
}