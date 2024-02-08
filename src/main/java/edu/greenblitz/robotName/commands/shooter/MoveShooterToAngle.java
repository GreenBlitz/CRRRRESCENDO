package edu.greenblitz.robotName.commands.shooter;

import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotIfInDangerZone;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.subsystems.arm.elbow.Elbow;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.*;

public class MoveShooterToAngle extends ProxyCommand{

	public MoveShooterToAngle(Rotation2d targetAngle) {
		super(() -> getCommand(targetAngle));
	}

	private static Command getCommand(Rotation2d targetAngle) {
		if (Elbow.getInstance().isInShooterCollisionRange()){
			System.out.println("aaaaaaaaaaaaaa");
			return new MovePivotIfInDangerZone(targetAngle);
		}
		else {
			System.out.println("bbbbbbbbbbbbbb");
			return new MovePivotToAngle(targetAngle);
		}
	}

}
