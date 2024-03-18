package edu.greenblitz.robotName.commands.auto;

import edu.greenblitz.robotName.commands.intake.NoteFromIntakeToShooterWithArm;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.subsystems.intake.Intake;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;

public class NoteFromIntakeToShooterForAuto extends ConditionalCommand {
	
	public NoteFromIntakeToShooterForAuto(){
		super(
				new ParallelDeadlineGroup(
						new NoteFromIntakeToShooterWithArm(),
						new MovePivotToAngle(PivotConstants.PresetPositions.PICK_UP)
				),
				new InstantCommand(),
				() -> Intake.getInstance().isObjectIn()
		);
	}
	
	
}
