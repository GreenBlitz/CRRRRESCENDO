package edu.greenblitz.robotName.commands;

import edu.greenblitz.robotName.commands.NoteToShooter;
import edu.greenblitz.robotName.commands.intake.NoteToIntake;
import edu.greenblitz.robotName.commands.shooter.MoveShooterToAngle;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.subsystems.shooter.pivot.Pivot;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class CollectNote extends ParallelCommandGroup {

    public CollectNote() {
        super(
                new NoteToShooter(),
                new MovePivotToAngle(PivotConstants.PresetPositions.PICK_UP.ANGLE)
        );
    }
}