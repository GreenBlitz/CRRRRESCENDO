package edu.greenblitz.robotName.commands.shooter;

import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotIfInDangerZone;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.subsystems.arm.elbow.Elbow;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.PivotConstants;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.*;

public class MoveShooterToAngle {

	public static Command getCommand(Rotation2d targetAngle) {
		if (Elbow.getInstance().isInShooterCollisionRange()){
			return new MovePivotIfInDangerZone(targetAngle);
		}
		else {
			return new MovePivotToAngle(targetAngle);
		}
	}

	public static Command getCommand(PivotConstants.PresetPositions targetPosition) {
		return getCommand(targetPosition.ANGLE);
	}

}
