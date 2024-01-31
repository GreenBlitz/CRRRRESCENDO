package edu.greenblitz.robotName.commands.switchMode;

import edu.greenblitz.robotName.commands.shooter.pivot.RequirePivot;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class SwitchModeWrapper extends SequentialCommandGroup {

    public SwitchModeWrapper(){
        super(new RequirePivot(), new SwitchBetweenScoringModes());
    }

}
