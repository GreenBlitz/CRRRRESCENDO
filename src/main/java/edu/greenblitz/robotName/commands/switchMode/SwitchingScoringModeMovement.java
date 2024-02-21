package edu.greenblitz.robotName.commands.switchMode;

import edu.greenblitz.robotName.commands.getNoteToSystem.TransferNote;
import edu.greenblitz.robotName.commands.shooter.MoveShooterToAngle;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;
import edu.greenblitz.robotName.utils.ScoringMode;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

import java.util.function.BooleanSupplier;

public class SwitchingScoringModeMovement {

    public static Command getCommand(ScoringMode mode, BooleanSupplier isNoteInOtherSystem) {
        if (isNoteInOtherSystem.getAsBoolean()) {
            return new ParallelCommandGroup(
                    new SetScoringMode(mode),
                    new TransferNote()
            );
        } else {
            return new ParallelCommandGroup(
                    new SetScoringMode(mode),
                    new MoveShooterToAngle(PivotConstants.PresetPositions.PICK_UP.ANGLE)
            );
        }
    }
}