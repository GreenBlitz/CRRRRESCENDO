package edu.greenblitz.robotName.commands.GetNoteToSystem;

import edu.greenblitz.robotName.commands.arm.MoveNoteBetweenShooterAndArm;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class TransferNotes extends SequentialCommandGroup {

    public TransferNotes() {
        super(
                new GetToTransferNotePosition(),
                new MoveNoteBetweenShooterAndArm()
        );
    }
}
