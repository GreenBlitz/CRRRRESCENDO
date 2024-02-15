package edu.greenblitz.robotName.commands;

import edu.greenblitz.robotName.ScoringModeSelector;
import edu.greenblitz.robotName.commands.arm.ScoreToAmp;
import edu.greenblitz.robotName.commands.shooter.shootingState.GoToShootingStateAndShoot;
import edu.greenblitz.robotName.commands.shooter.shootingState.ShootAutomatically;
import edu.greenblitz.robotName.shootingStateService.ShootingPositionConstants;
import edu.greenblitz.robotName.shootingStateService.ShootingStateCalculations;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;

public class ShootOrScoreToAmp extends ConditionalCommand {
    public ShootOrScoreToAmp(){
        super(
                new ScoreToAmp(),
                new ShootAutomatically(),
                ScoringModeSelector::isAmpMode
        );
    }
}
