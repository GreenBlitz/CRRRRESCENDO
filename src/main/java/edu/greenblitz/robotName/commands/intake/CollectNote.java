package edu.greenblitz.robotName.commands.intake;

import edu.greenblitz.robotName.commands.shooter.MoveShooterToAngle;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.Pivot;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.PivotConstants;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class CollectNote extends ConditionalCommand {

    public CollectNote() {
        super(
                new NoteToShooter(),
                new NoteToIntake()
                        .alongWith(new MoveShooterToAngle(PivotConstants.PresetPositions.PICK_UP.ANGLE))
                        .andThen(new NoteToShooter()),
                () -> Pivot.getInstance().isAtAngle(PivotConstants.PresetPositions.PICK_UP.ANGLE)
        );
    }
}
