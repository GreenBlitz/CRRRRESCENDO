package edu.greenblitz.robotName.commands.switchMode;

import edu.greenblitz.robotName.commands.shooter.pivot.InterruptPivot;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ToggleScoringMode extends SequentialCommandGroup {

    public ToggleScoringMode(){
        super(new InterruptPivot(), new FullySwitchScoringMode());
    }

}
