package edu.greenblitz.robotName.commands.intake;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class NoteToShooterWithArm extends SequentialCommandGroup {

    public NoteToShooterWithArm() {
        super(
                new NoteToIntake(),
                new NoteFromIntakeToShooterWithArm()
        );
    }
}
