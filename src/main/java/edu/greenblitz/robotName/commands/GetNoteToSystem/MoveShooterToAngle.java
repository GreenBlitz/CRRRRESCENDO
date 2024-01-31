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

import java.util.function.Supplier;

public class MoveShooterToAngle {

	public static Command getCommand(Rotation2d targetAngle) {
		if (Elbow.getInstance().isInShooterCollisionRange()){
			return new MoveShooterIfInDangerZone(targetAngle);
		}
		else {
			return new MovePivotToAngle(targetAngle);
		}
	}

}
