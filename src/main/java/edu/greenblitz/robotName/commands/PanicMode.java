package edu.greenblitz.robotName.commands;

import edu.greenblitz.robotName.commands.arm.MoveElbowAndWristToSafe;
import edu.greenblitz.robotName.commands.shooter.MoveShooterToAngle;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.subsystems.arm.elbow.Elbow;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class PanicMode extends ConditionalCommand {

    public PanicMode() {
        super(
                new MoveShooterToAngle(PivotConstants.PresetPositions.SAFE.ANGLE),
                new InstantCommand(),
//                new MoveElbowAndWristToSafe().alongWith(new MovePivotToAngle(PivotConstants.PresetPositions.SAFE.ANGLE)),
                () -> true
//               () -> Elbow.getInstance().isInShooterCollisionRange()() -> false
        );
    }
}