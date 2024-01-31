package edu.greenblitz.robotName.commands.switchMode;

import edu.greenblitz.robotName.commands.shooter.MoveShooterToAngle;
import edu.greenblitz.robotName.commands.getNoteToSystem.TransferNote;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.PivotConstants;
import edu.greenblitz.robotName.utils.ScoringMode;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public class SwitchModeMotion {

    public static Supplier<Command> getCommand(ScoringMode mode, BooleanSupplier isNoteInOtherSystem){
        if (isNoteInOtherSystem.getAsBoolean()){
            return () -> new ParallelCommandGroup(new SetScoringMode(mode), new TransferNote());
        }
        else {
            return () -> new ParallelCommandGroup(new SetScoringMode(mode), MoveShooterToAngle.getCommand(PivotConstants.PresetPositions.PICK_UP.ANGLE));
        }
    }

}
