package edu.greenblitz.robotName.commands;

import edu.greenblitz.robotName.commands.intake.NoteFromIntakeToShooter;
import edu.greenblitz.robotName.commands.intake.NoteToIntake;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class NoteToShooterForAuto extends SequentialCommandGroup {

	public NoteToShooterForAuto() {
		super(
				new NoteToIntake(),
				new NoteFromIntakeToShooter()
		);
	}
}
