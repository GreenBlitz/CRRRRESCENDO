package edu.greenblitz.robotName.commands.GetNoteToSystem;

import edu.greenblitz.robotName.commands.shooter.pivot.RequirePivot;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class SwitchWrapper extends SequentialCommandGroup {

    public SwitchWrapper(){
        super(new RequirePivot(), new SwitchBetweenScoringModes());
    }
}
