package edu.greenblitz.robotName.commands;

import edu.greenblitz.robotName.commands.arm.MoveArmToIntakePosition;
import edu.greenblitz.robotName.commands.arm.roller.runByPower.RollClockwise;
import edu.greenblitz.robotName.commands.intake.NoteFromIntakeToShooter;
import edu.greenblitz.robotName.commands.intake.NoteToIntake;
import edu.greenblitz.robotName.commands.intake.NoteToShooter;
import edu.greenblitz.robotName.commands.shooter.MoveShooterToAngle;
import edu.greenblitz.robotName.subsystems.shooter.pivot.Pivot;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;

public class CollectNote extends ConditionalCommand {

    public CollectNote() {
        super(
                new NoteToShooter()
                        .alongWith(new MoveArmToIntakePosition())
                        .andThen(new RollClockwise()),
                new NoteToIntake()
                        .alongWith(new MoveShooterToAngle(PivotConstants.PresetPositions.PICK_UP.ANGLE))
                        .andThen(new MoveArmToIntakePosition())
                        .alongWith(new RollClockwise())
                        .alongWith(new NoteFromIntakeToShooter()),
                () -> Pivot.getInstance().isAtAngle(PivotConstants.PresetPositions.PICK_UP.ANGLE)
        );
    }
}