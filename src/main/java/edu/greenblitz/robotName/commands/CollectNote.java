package edu.greenblitz.robotName.commands;

import edu.greenblitz.robotName.commands.arm.MoveArmToIntakePosition;
import edu.greenblitz.robotName.commands.intake.NoteFromIntakeToShooter;
import edu.greenblitz.robotName.commands.intake.NoteToIntake;
import edu.greenblitz.robotName.commands.shooter.MoveShooterToAngle;
import edu.greenblitz.robotName.subsystems.shooter.pivot.Pivot;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;

public class CollectNote extends ConditionalCommand {

    public CollectNote() {
        super(
                new MoveArmToIntakePosition().andThen(new NoteFromIntakeToShooter()),
                new NoteToIntake()
                        .alongWith(new MoveShooterToAngle(PivotConstants.PresetPositions.PICK_UP.ANGLE))
                        .alongWith(new MoveArmToIntakePosition())
                        .andThen(new NoteFromIntakeToShooter()),
                () -> Pivot.getInstance().isAtAngle(PivotConstants.PresetPositions.PICK_UP.ANGLE)
        );
    }
}