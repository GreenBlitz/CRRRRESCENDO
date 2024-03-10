package edu.greenblitz.robotName.commands.intake;

import edu.greenblitz.robotName.commands.arm.MoveElbowAndWrist;
import edu.greenblitz.robotName.commands.arm.roller.runByPower.RollCounterClockwise;
import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.wrist.WristConstants;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;

public class NoteFromIntakeToShooterWithArm extends ParallelDeadlineGroup {
	
	public NoteFromIntakeToShooterWithArm() {
		super(
				new NoteFromIntakeToShooter(),
				new MoveElbowAndWrist(
						ElbowConstants.PresetPositions.INTAKE,
						WristConstants.PresetPositions.INTAKE
				),
				new RollCounterClockwise()
		);
	}
	
}
