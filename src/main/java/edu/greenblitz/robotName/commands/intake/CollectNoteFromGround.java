package edu.greenblitz.robotName.commands.intake;

import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class CollectNoteFromGround extends ParallelCommandGroup {
	
	public CollectNoteFromGround(){
		super(
				new MovePivotToAngle(PivotConstants.PresetPositions.PICK_UP.ANGLE),
				new NoteToShooterWithArm()
		);
	}
	
}
