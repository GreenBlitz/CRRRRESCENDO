package edu.greenblitz.robotName.commands.getNoteToSystem;

import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class CollectNoteToScoringModeWithPivotForJoystick extends ParallelCommandGroup {

    public CollectNoteToScoringModeWithPivotForJoystick(){
        super(
                new MovePivotToAngle(PivotConstants.PresetPositions.PICK_UP),
                new CollectNoteToScoringModeForJoystick()
        );
    }

}
