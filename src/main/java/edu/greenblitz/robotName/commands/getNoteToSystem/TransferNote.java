package edu.greenblitz.robotName.commands.getNoteToSystem;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class TransferNote extends SequentialCommandGroup {

    public TransferNote() {
        super(
                new MoveToTransferNotePosition(),
                new SafeMoveNoteBetweenShooterAndArm()
        );
    }
}
