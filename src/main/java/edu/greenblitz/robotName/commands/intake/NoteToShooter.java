package edu.greenblitz.robotName.commands.intake;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class NoteToShooter extends SequentialCommandGroup {

    public NoteToShooter() {
        super(
                new NoteToIntake(),
                new NoteFromIntakeToShooter()
        );
    }
}
