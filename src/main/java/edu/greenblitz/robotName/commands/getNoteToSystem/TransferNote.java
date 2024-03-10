package edu.greenblitz.robotName.commands.getNoteToSystem;

import edu.greenblitz.robotName.ScoringModeSelector;
import edu.greenblitz.robotName.commands.arm.NoteToArm;
import edu.greenblitz.robotName.commands.shooter.NoteToShooterFromArm;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class TransferNote extends SequentialCommandGroup {
	
	public TransferNote() {
		super(
				new MoveToTransferNotePosition(),
				new WaitCommand(0.5),
				new ConditionalCommand(
						new NoteToArm(),
						new NoteToShooterFromArm(),
						ScoringModeSelector::isAmpMode
				)
		);
	}
}