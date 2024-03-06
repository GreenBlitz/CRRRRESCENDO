package edu.greenblitz.robotName.commands.getNoteToSystem;

import edu.greenblitz.robotName.commands.arm.MoveElbowAndWrist;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.wrist.WristConstants;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class MoveToTransferNotePosition extends ParallelCommandGroup {

    public MoveToTransferNotePosition() {
        super(
                new MoveElbowAndWrist(
                        ElbowConstants.PresetPositions.TRANSFER.ANGLE,
                        WristConstants.PresetPositions.TRANSFER.ANGLE
                )
                , new MovePivotToAngle(PivotConstants.PresetPositions.TRANSFER.ANGLE,false)
        );
    }
}