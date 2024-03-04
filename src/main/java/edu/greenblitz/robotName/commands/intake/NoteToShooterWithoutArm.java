package edu.greenblitz.robotName.commands.intake;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class NoteToShooterWithoutArm extends SequentialCommandGroup {

    public NoteToShooterWithoutArm() {
        super(
                new NoteToIntake(),
                new NoteFromIntakeToShooterWithArm()
        );
    }
}
