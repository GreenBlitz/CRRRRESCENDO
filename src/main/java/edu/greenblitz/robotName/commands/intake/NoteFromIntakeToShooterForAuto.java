package edu.greenblitz.robotName.commands.intake;

import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;

public class NoteFromIntakeToShooterForAuto extends ParallelCommandGroup {
	
	public NoteFromIntakeToShooterForAuto(){
		super(
				new NoteFromIntakeToShooterWithArm(),
				new MovePivotToAngle(PivotConstants.PresetPositions.PICK_UP)
		);
	}
	
	
}
