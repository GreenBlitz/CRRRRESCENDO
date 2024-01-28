package edu.greenblitz.robotName.commands.GetNoteToSystem;

import edu.greenblitz.robotName.commands.arm.MoveElbowAndWrist;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.EndEffector.WristUtils.WristConstants;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.PivotConstants;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class GetToTransferNotePosition extends ParallelCommandGroup {

    public GetToTransferNotePosition() {
//        super(
//                new MoveElbowAndWrist(
//                        ElbowConstants.ImportantPlaces.TRANSFER.ANGLE,
//                        WristConstants.ImportantPlaces.TRANSFER.ANGLE
//                ),
//                new MovePivotToAngle(PivotConstants.ImportantPlaces.TRANSFER.ANGLE)
//        );
    }
}
