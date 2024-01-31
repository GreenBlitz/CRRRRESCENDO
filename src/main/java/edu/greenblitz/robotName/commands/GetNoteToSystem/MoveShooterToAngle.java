package edu.greenblitz.robotName.commands.GetNoteToSystem;

import edu.greenblitz.robotName.commands.arm.MoveElbowAndWrist;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.subsystems.arm.Elbow;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.EndEffector.WristUtils.WristConstants;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.PivotConstants;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.*;

public class MoveShooterToAngle extends GBCommand {

	private Rotation2d targetAngle;
	private Command selectedCommand;

	public MoveShooterToAngle(Rotation2d targetAngle) {
		this.targetAngle = targetAngle;
	}

	@Override
	public void initialize() {
		if (Elbow.getInstance().isInShooterCollisionRange()){
			selectedCommand = new MoveShooterIfInDangerZone(targetAngle);
		}
		else {
			selectedCommand = new MovePivotToAngle(targetAngle);
		}
		selectedCommand.schedule();
	}

	@Override
	public boolean isFinished() {
		return selectedCommand.isFinished();
	}

	@Override
	public void end(boolean interrupted) {
		selectedCommand.end(interrupted);
	}
}
