package edu.greenblitz.robotName.commands.shooter;

import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotIfInDangerZone;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.subsystems.arm.elbow.Elbow;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;

import java.util.function.Supplier;

public class MoveShooterToAngle extends ConditionalCommand {

    public MoveShooterToAngle(Supplier<Rotation2d> targetAngle) {
        super(
                new MovePivotIfInDangerZone(targetAngle).asProxy(),
                new MovePivotToAngle(targetAngle,false),
                () -> Elbow.getInstance().isInShooterCollisionRange()
        );
    }

    public MoveShooterToAngle(Rotation2d targetAngle) {
        this(() -> targetAngle);
    }
}