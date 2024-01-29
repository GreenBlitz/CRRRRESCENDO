package edu.greenblitz.robotName.commands.GetNoteToSystem;

import edu.greenblitz.robotName.commands.arm.MoveElbowAndWrist;
import edu.greenblitz.robotName.commands.intake.MoveNoteBetweenShooterArm;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import java.util.function.Supplier;

public class TransferNotes extends SequentialCommandGroup {

    public TransferNotes() {
        super(
                new GetToTransferNotePosition(),
                new MoveNoteBetweenShooterArm(),
                new MoveElbowAndWrist()
        );
    }
}
