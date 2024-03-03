package edu.greenblitz.robotName.commands.arm;

import edu.greenblitz.robotName.commands.arm.roller.CollectNoteToRoller;
import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.wrist.WristConstants;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class NoteToArm extends SequentialCommandGroup {

    public NoteToArm(){
        super(
                new MoveElbowAndWrist(ElbowConstants.PresetPositions.SAFE.ANGLE, WristConstants.PresetPositions.SAFE.ANGLE),
                new CollectNoteToRoller()
        );
    }

}
