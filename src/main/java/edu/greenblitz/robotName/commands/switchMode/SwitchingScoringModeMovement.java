package edu.greenblitz.robotName.commands.switchMode;

import edu.greenblitz.robotName.commands.arm.roller.RollerCommand;
import edu.greenblitz.robotName.commands.getNoteToSystem.TransferNote;
import edu.greenblitz.robotName.commands.shooter.MoveShooterToAngle;
import edu.greenblitz.robotName.commands.shooter.funnel.RunFunnelByVelocity;
import edu.greenblitz.robotName.subsystems.arm.roller.Roller;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;
import edu.greenblitz.robotName.ScoringMode;
import edu.wpi.first.wpilibj2.command.*;

import java.util.function.BooleanSupplier;

public class SwitchingScoringModeMovement {

    public static Command getCommand(ScoringMode mode, BooleanSupplier isNoteInOtherSystem) {
        if (isNoteInOtherSystem.getAsBoolean()) {
            return new ParallelCommandGroup(
                    new SetScoringMode(mode),
                    new TransferNote().beforeStarting(
                            (new RunFunnelByVelocity(-30).raceWith(new WaitCommand(0.5))).andThen(new InstantCommand(() -> Roller.getInstance().setObjectOut()))
                    )
            );
        } else {
            return new ParallelCommandGroup(
                    new SetScoringMode(mode)
            );
        }
    }
}