package edu.greenblitz.robotName.commands.GetNoteToSystem;

import edu.greenblitz.robotName.subsystems.shooter.Pivot.PivotConstants;
import edu.greenblitz.robotName.utils.ScoringMode;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import java.util.function.BooleanSupplier;

public class SwitchModeMotion extends SequentialCommandGroup {

    public SwitchModeMotion(ScoringMode mode, BooleanSupplier isNoteInOtherSystem){
        super(
                new SetScoringMode(mode),
                new ConditionalCommand(
                        new TransferNote(),
                        new MoveShooterToAngle(PivotConstants.PresetPositions.PICK_UP.ANGLE),
                        isNoteInOtherSystem
                )
        );
    }
}
