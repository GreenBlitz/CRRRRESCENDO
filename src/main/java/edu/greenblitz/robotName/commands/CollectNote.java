package edu.greenblitz.robotName.commands;

import edu.greenblitz.robotName.commands.NoteToShooter;
import edu.greenblitz.robotName.commands.intake.NoteToIntake;
import edu.greenblitz.robotName.commands.shooter.MoveShooterToAngle;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.subsystems.shooter.pivot.Pivot;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class CollectNote extends SequentialCommandGroup {

    public CollectNote() {
        super(
                new NoteToShooter()
                        .alongWith(new MovePivotToAngle(PivotConstants.PresetPositions.PICK_UP.ANGLE))
        );
    }
}